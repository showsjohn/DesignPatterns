import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class EventListPanel extends JPanel implements CalendarDayListener
{
    EventPanel eventPanel;
    JLabel dateLabel;
    JButton addEvent;
    CalendarDayPanel currentlySelectedDay;
    LocalDate date;
    public static EventListPanel instance;

    public EventListPanel(int width, int height)
    {
        instance = this;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new LineBorder(new Color(224, 224, 224), 10));
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));

        eventPanel = new EventPanel();
        dateLabel = new JLabel();

        addEvent = new JButton("Add Event +");
        addEvent.setAlignmentX(Component.CENTER_ALIGNMENT);
        addEvent.setBackground(new Color(13, 110, 253));
        addEvent.setForeground(Color.white);
        addEvent.setFont(FontFactory.createFont(20));
        addEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEventModal modal = new AddEventModal(date);
                modal.setModal(true);
                modal.setVisible(true);
                Event event = modal.getData();
                currentlySelectedDay.addEvent(event);
                add(new JLabel(event.getName()));
                add(new JLabel(event.getDateTime().toString()));
                revalidate();
                repaint();
            }
        });

        add(dateLabel);
        add(Box.createRigidArea(new Dimension(-1,30)));
        add(addEvent);
        add(eventPanel);
    }

    public static EventListPanel getInstance()
    {
        if (instance == null)
        {
            instance = new EventListPanel(ScreenSize.getScreenSize().x, ScreenSize.getScreenSize().y);
        }
        return instance;
    }

    public void onDaySelected(ArrayList<Event> events, LocalDate date, CalendarDayPanel day)
    {
        currentlySelectedDay = day;
        this.date = date;
        eventPanel.removeAll();

        dateLabel.setText(date.getDayOfWeek().toString() +" - " + date.getMonth() + " " + date.getDayOfMonth() + ", " + date.getYear());
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        dateLabel.setFont(FontFactory.createFont(24));
        revalidate();
        repaint();

        for (Event event: events)
        {

            eventPanel.add(new JLabel(event.getName()));
            eventPanel.add(new JLabel(event.getDateTime().toString()));
            if (event instanceof Meeting)
            {
                eventPanel.add(new JLabel(((Meeting) event).getLocation()));
            }
        }
    }
}
