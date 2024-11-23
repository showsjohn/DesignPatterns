import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EventPanel extends JPanel
{

    public EventPanel(String eventType)
    {
        Color color;
        if (eventType.equals("Deadline"))
        {
            color = new Color(242, 139, 130);
        }
        else if(eventType.equals("Meeting"))
        {
            color = new Color(174, 203, 250);
        }
        else{
            color = Color.white;
        }
        setBackground(color);
        setBorder(new LineBorder(color, 30, true));
        Font font = FontFactory.createFont(20);

        JLabel event = new JLabel(eventType);
        JLabel name = new JLabel("Name");
        JLabel time = new JLabel("Date/Time");
        JLabel duration = new JLabel("Location");
        event.setFont(FontFactory.createFont(24));

        name.setFont(font);
        time.setFont(font);
        duration.setFont(font);
        setLayout(new GridLayout(3, 3, 5, 10));

        add(new JLabel());
        add(event);
        add(new JLabel());
        add(name);
        add(time);
        add(duration);
    }
}
