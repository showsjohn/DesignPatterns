import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarDayPanel extends JPanel
{
    ArrayList<CalendarDayListener> listeners;
    ArrayList<Event> events;
    LocalDate currentDate;
    int day;

    public CalendarDayPanel(int width, int height, int day, LocalDate date)
    {
        listeners = new ArrayList<>();
        addListener(EventListPanel.getInstance());

        this.day = day;
        this.currentDate = date;
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        events = new ArrayList<>();
        setPreferredSize(new Dimension(width, height));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel dayLabel = new JLabel(String.valueOf(day));
        dayLabel.setHorizontalAlignment(JLabel.CENTER);
        dayLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(dayLabel);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                notifyListenersOnClick();
            }
        });

    }

    public final ArrayList<Event> getEvents()
    {
        return events;
    }

    public void addEvent(Event event)
    {
        events.add(event);
    }

    public void addListener(CalendarDayListener listener)
    {
        listeners.add(listener);
    }

    public void notifyListenersOnClick()
    {
        for (CalendarDayListener listener: listeners)
        {
            if (listener != null)
                listener.onDaySelected(events, currentDate, this);
        }
    }

}
