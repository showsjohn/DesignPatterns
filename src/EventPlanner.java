import javax.swing.*;
import java.awt.*;

public class EventPlanner
{
    //Main GUI method which holds the JFrame class
    public EventPlanner()
    {
        JFrame jframe = new JFrame();
        jframe.setLayout(new BorderLayout());

        EventListPanel eventListPanel = new EventListPanel(440, 1180);
        CalendarPanel calendarPanel = new CalendarPanel(1440, 1180);

        jframe.setResizable(false);
        jframe.add(calendarPanel, BorderLayout.CENTER);
        jframe.add(eventListPanel, BorderLayout.EAST);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(1920, 1400);
        jframe.setVisible(true);

        jframe.revalidate();
        jframe.repaint();

    }
}
