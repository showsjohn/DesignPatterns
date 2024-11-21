import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MonthPanel extends JPanel
{
    int width, height;
    JPanel CalendarHeader;
    JPanel daysPanel;
    final int CHILD_COMPONENT_WIDTH = 200;
    ArrayList<CalendarDayPanel> days;
    EventListPanel eventListPanel;
    public MonthPanel(int width, int height, int firstOfMonth, LocalDate currentMonth, EventListPanel eventListPanel)
    {
        this.eventListPanel = eventListPanel;
        days = new ArrayList<>();
        this.height = height;
        this.width = width;

        setLayout(new BorderLayout());
        CalendarHeader = new JPanel();
        CalendarHeader.setLayout(new GridLayout(1, 7));
        daysPanel = new JPanel();

        daysPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 1,2));
        daysPanel.setPreferredSize(new Dimension(width, 1080));
        daysPanel.setBackground(new Color(224, 224, 224));

        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        for (String day: days)
        {
            JLabel dayLabel = new JLabel(day);
            dayLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            dayLabel.setHorizontalAlignment(JLabel.CENTER);
            CalendarHeader.add(dayLabel);
        }

        drawCalendar(firstOfMonth, currentMonth);
        add(CalendarHeader, BorderLayout.NORTH);
        add(daysPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }

    void drawCalendar(int firstOfMonth, LocalDate currentMonth)
    {
        if (days.isEmpty())
        {
            daysPanel.removeAll();
            firstOfMonth = (firstOfMonth + 7 ) % 7;

            for (int j = 0; j < firstOfMonth; j++) {
                JPanel blank = new JPanel();
                blank.setBackground(new Color(224,224,224));
                blank.setPreferredSize(new Dimension(width/7, 200));
                daysPanel.add(blank);
            }

            for (int i = 1; i < currentMonth.lengthOfMonth() + 1; i++) {
                LocalDate date = currentMonth.plusDays(i-1);
                CalendarDayPanel dayPanel = new CalendarDayPanel(width/7, 200, i, date);
                daysPanel.add(dayPanel);
                days.add(dayPanel);
                dayPanel.addListener(eventListPanel);
            }
        }
    }
}
