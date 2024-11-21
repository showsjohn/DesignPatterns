import javax.swing.*;
import java.awt.*;

public class EventPlanner
{
    public EventPlanner()
    {
        JFrame jframe = new JFrame();
        jframe.setLayout(new BorderLayout());


        EventListPanel eventListPanel = new EventListPanel(1920-1480, 1180);
        CalendarPanel calendarPanel = new CalendarPanel(1440, 1180, eventListPanel);


        jframe.setResizable(false);
        jframe.add(calendarPanel, BorderLayout.CENTER);
        jframe.add(eventListPanel, BorderLayout.EAST);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(1920, 1180);
        jframe.setVisible(true);
        jframe.revalidate();
        jframe.repaint();

    }
}
