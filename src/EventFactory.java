import java.time.LocalDate;
import java.time.LocalDateTime;

// Factory class to create multiple eventTypes
public class EventFactory {

    public static Event createEvent(String name, String location, LocalDateTime dateTime)
    {
        Meeting meeting = new Meeting(location);
        meeting.setDateTime(dateTime);
        meeting.setName(name);
        return meeting;
    }

    public static Event createEvent(String name, LocalDateTime dateTime)
    {
        Deadline deadline = new Deadline();
        deadline.setName(name);
        deadline.setDateTime(dateTime);

        return deadline;
    }
}
