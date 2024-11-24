import java.time.LocalDate;
import java.util.ArrayList;

// Interface to allow any listener to subscribe to CalendarDayPanel and retrieve their data
public interface CalendarDayListener {

    void onDaySelected(ArrayList<Event> events, LocalDate date, CalendarDayPanel calendarDayPanel);
}
