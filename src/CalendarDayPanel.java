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
    JPanel eventsPanelHolder;

    public CalendarDayPanel(int width, int height, int day, LocalDate date)
    {
        setOpaque(true);
        setBackground(new Color(255,255,255, 100));

        eventsPanelHolder = new JPanel(){
            @Override
            protected void paintComponent(Graphics g)
            {
                g.setColor( getBackground() );
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        eventsPanelHolder.setOpaque(false);
        eventsPanelHolder.setBackground(new Color(255,255,255, 0));
        listeners = new ArrayList<>();
        addListener(EventListPanel.getInstance());

        this.day = day;
        this.currentDate = date;
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        events = new ArrayList<>();
        setPreferredSize(new Dimension(width, height));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel dayLabel = new JLabel(String.valueOf(day));
        dayLabel.removeAll();
        dayLabel.setHorizontalAlignment(JLabel.CENTER);
        dayLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(dayLabel);
        add(eventsPanelHolder);
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

    public LocalDate getDate()
    {
        return currentDate;
    }

    public void addEvent(Event event)
    {
        events.add(event);
        drawEvents();
    }

    public void drawEvents()
    {
        eventsPanelHolder.removeAll();
        for (Event event: events)
        {
            String eventType = event.getClass().toString().split(" ")[1];
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
            JPanel eventsHolder = new JPanel();
            eventsHolder.add(new JLabel(event.getName()));
            eventsHolder.add(new JLabel(event.dateTime.toString()));
            eventsHolder.setBackground(color);
            eventsPanelHolder.add(eventsHolder);
            revalidate();
            repaint();
        }
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

    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor( getBackground() );
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }


}
