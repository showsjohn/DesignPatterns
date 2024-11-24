import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;


// Main class for the program
// CalendarPanel holds a MonthPanel (which contains all the individual CalendarDayPanels)
// As well as the controls for changing the month
public class CalendarPanel extends JPanel
{
    ArrayList<MonthPanel> monthPanels;
    MonthPanel currentMonthPanel;
    int currentMonthIndex = 0;
    int firstOfMonth;
    LocalDate currentDate;
    LocalDate currentMonth;
    JPanel header;
    JPanel monthPanel;
    JPanel monthPanelHolder;
    JLabel monthName;
    JButton previousMonth, nextMonth;
    Months month = Months.November;

    public CalendarPanel(int width, int height)
    {
        setOpaque(false);
        setBackground(new Color(238, 238, 238, 50));
        setLayout(new BorderLayout());
        setBorder(new LineBorder(new Color(224, 224, 224), 10));

        monthPanels = new ArrayList<>();
        currentDate = LocalDate.now();
        currentMonth = LocalDate.of(currentDate.getYear(), currentDate.getMonth(), 1);
        firstOfMonth = currentMonth.getDayOfWeek().getValue();

        header = new JPanel();
        header.setLayout(new BorderLayout());

        monthName = new JLabel(month.toString());
        monthName.setHorizontalAlignment(JLabel.CENTER);
        monthName.setFont(new Font("Serif", Font.PLAIN, 32));

        monthPanel = new JPanel();
        monthPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        monthPanel.setMinimumSize(new Dimension(width, 100));
        monthPanel.setPreferredSize(new Dimension(width, 100));

        // change month to previous month
        previousMonth = new JButton("<");
        previousMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(hasPreviousMonth())
                {
                    month = month.previous();
                    currentMonth = currentMonth.plusMonths(-1);
                    firstOfMonth = currentMonth.getDayOfWeek().getValue();
                    currentMonthPanel = getPreviousMonth();
                    setCurrentMonth(currentMonthPanel);
                    currentMonthPanel.drawCalendar(firstOfMonth, currentMonth);
                    monthName.setText(month.toString() + " " + currentMonth.getYear());
                    revalidate();
                    repaint();
                }
            }
        });

        // change month to next month
        nextMonth = new JButton(">");
        nextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                month = month.next();
                currentMonth = currentMonth.plusMonths(1);
                firstOfMonth = currentMonth.getDayOfWeek().getValue();

                if(hasNextMonth())
                {
                    currentMonthPanel = getNextMonth();
                }
                else
                {
                    currentMonthPanel = new MonthPanel(width, height, firstOfMonth, currentMonth);
                    addMonth(currentMonthPanel);
                }

                setCurrentMonth(currentMonthPanel);
                currentMonthPanel.drawCalendar(firstOfMonth, currentMonth);
                monthName.setText(month.toString() + " " + currentMonth.getYear());
                revalidate();
                repaint();
            }
        });

        monthPanel.add(previousMonth);
        monthPanel.add(monthName);
        monthPanel.add(nextMonth);

        setLayout(new BorderLayout());

        monthPanelHolder = new JPanel();
        monthPanelHolder.setLayout(new BorderLayout());
        currentMonthPanel = new MonthPanel(width, height, firstOfMonth, currentMonth);
        monthPanelHolder.add(currentMonthPanel, BorderLayout.CENTER);

        addMonth(currentMonthPanel);
        add(monthPanel, BorderLayout.NORTH);
        add(monthPanelHolder, BorderLayout.CENTER);
        setPreferredSize(new Dimension(width, height));
    }

    public void setCurrentMonth(MonthPanel month)
    {
        monthPanelHolder.removeAll();
        monthPanelHolder.add(month);
    }

    // add new month to list of months
    public void addMonth(MonthPanel month)
    {
        monthPanels.add(month);
        currentMonthIndex++;
    }

    // retrieve the next month in the MonthPanel from the ArrayList
    public MonthPanel getNextMonth()
    {
        currentMonthIndex++;
        return monthPanels.get(currentMonthIndex-1);
    }

    // retrieve the previous MonthPanel from the ArrayList
    public MonthPanel getPreviousMonth()
    {
        currentMonthIndex--;
        return monthPanels.get(currentMonthIndex-1);
    }


    // booleans to check if previous or next month is in the ArrayList
    public boolean hasPreviousMonth()
    {
        return (currentMonthIndex - 2) > - 1;
    }

    public boolean hasNextMonth()
    {
        return ((currentMonthIndex) < monthPanels.size()) &&  monthPanels.get(currentMonthIndex) != null;
    }
}


