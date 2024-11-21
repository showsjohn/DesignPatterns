import java.time.LocalDateTime;
import java.util.Comparator;

public abstract class Event implements Comparable<Event>
{
    protected String name;
    protected LocalDateTime dateTime;


    public String getName()
    {
        return name;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setDateTime(LocalDateTime dt)
    {
        this.dateTime = dt;
    }

    public int compareTo(Event e)
    {
        return this.dateTime.compareTo(e.getDateTime());
    }
}
