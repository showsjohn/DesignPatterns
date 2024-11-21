import java.time.Duration;
import java.time.LocalDateTime;

public class Meeting extends Event implements Completable
{

    private LocalDateTime endDateTime;
    private String location;
    private boolean complete;

    public Meeting(String location)
    {
        this.location = location;
    }

    public LocalDateTime getEndDateTime()
    {
        return endDateTime;
    }

    public int getDuration()
    {
        return (int) Duration.between(dateTime, endDateTime).toMinutes();
    }

    public String getLocation()
    {
        return location;
    }

    public void setEndDateTime(LocalDateTime dt)
    {
        this.endDateTime = dt;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    @Override
    public void complete() {
        complete = true;
    }

    @Override
    public boolean isComplete() {
        return complete;
    }
}
