import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

// class JPanel which holds all the individual CalendarDayPanels as a collective
public class MonthPanel extends JPanel
{
    int width, height;
    JPanel CalendarHeader;
    JPanel daysPanel;
    ArrayList<CalendarDayPanel> days;
    public MonthPanel(int width, int height, int firstOfMonth, LocalDate currentMonth)
    {
        days = new ArrayList<>();
        this.height = height;
        this.width = width;

        setLayout(new BorderLayout());
        CalendarHeader = new JPanel();
        CalendarHeader.setLayout(new GridLayout(1, 7));

        daysPanel = new JPanel() {
            // add a unique background image for each month
            String file = "./images/" + currentMonth.getMonth() + ".jpg";
            private final Image backgroundImage = new ImageIcon(file).getImage();

            @Override
            protected void paintComponent(Graphics g) { //override paintComponetns to allow for transparency
                super.paintComponent(g);
                // Draw the background image
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };


        daysPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 1,2));
        daysPanel.setPreferredSize(new Dimension(width, 1080));
        daysPanel.setBackground(new Color(224, 224, 224));

        // draw the Days of the week across the top of the calendar
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

    // draw each CalendaryDayPanel
    void drawCalendar(int firstOfMonth, LocalDate currentMonth)
    {
        if (days.isEmpty())
        {
            daysPanel.removeAll();
            firstOfMonth = (firstOfMonth + 7 ) % 7;

            // draw blank spaces to account for days that are a part of the previous month in the first week
            for (int j = 0; j < firstOfMonth; j++) {

                JPanel blank = new JPanel();
                blank.setOpaque(false);
                blank.setBackground(new Color(255,255,255, 0));
                blank.setPreferredSize(new Dimension(width/7, 200));
                blank.setBorder(new LineBorder(Color.darkGray, 2));
                daysPanel.add(blank);
            }

            // draw the panels. Note that the currentMonth.lengOfMonth() returns the length of the particular month
            for (int i = 1; i < currentMonth.lengthOfMonth() + 1; i++) {
                LocalDate date = currentMonth.plusDays(i-1);
                CalendarDayPanel dayPanel = new CalendarDayPanel(width/7, 200, i, date);
                daysPanel.add(dayPanel);
                days.add(dayPanel);
                dayPanel.addListener(EventListPanel.getInstance());
            }
        }
    }
}
