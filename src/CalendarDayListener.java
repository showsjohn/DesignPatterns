import java.time.LocalDate;
import java.util.ArrayList;

public interface CalendarDayListener {

    void onDaySelected(ArrayList<Event> events, LocalDate date, CalendarDayPanel calendarDayPanel);
}
