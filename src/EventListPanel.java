import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

// Class to display detailed event information to the screen
public class EventListPanel extends JPanel implements CalendarDayListener
{
    JPanel eventPanelHolder;
    JLabel dateLabel;
    JButton addEvent;
    CalendarDayPanel currentlySelectedDay;
    LocalDate date;
    public static EventListPanel instance;

    public EventListPanel(int width, int height)
    {
        instance = this;
        eventPanelHolder = new JPanel();
        eventPanelHolder.setPreferredSize(new Dimension(width-20, 1000));

        setBorder(new LineBorder(new Color(224, 224, 224), 10));
        setPreferredSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));

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

                onDaySelected(currentlySelectedDay.getEvents(), currentlySelectedDay.getDate(), currentlySelectedDay);
                revalidate();
                repaint();
            }
        });

        add(dateLabel);
        add(Box.createRigidArea(new Dimension(-1,30)));
        add(addEvent);
        add(eventPanelHolder);

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
        eventPanelHolder.removeAll();

        dateLabel.setText(date.getDayOfWeek().toString() +" - " + date.getMonth() + " " + date.getDayOfMonth() + ", " + date.getYear());
        dateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateLabel.setBorder(new EmptyBorder(20, 0, 20, 0));
        dateLabel.setFont(FontFactory.createFont(24));
        revalidate();
        repaint();

        for (Event event: events)
        {
            EventPanel eventPanel = new EventPanel(event.getClass().toString().split(" ")[1]);
            eventPanel.add(new JLabel(event.getName()));
            String[] time = event.getDateTime().toString().split("T");
            eventPanel.add(new JLabel(time[0] +" @ " + time[1]));
            JLabel location = new JLabel();
            if (event instanceof Meeting)
            {
                location.setText(((Meeting) event).getLocation());
            }
            else
                location.setText("N/A");

            eventPanel.add(location);

            eventPanelHolder.add(eventPanel);
        }
    }
}
