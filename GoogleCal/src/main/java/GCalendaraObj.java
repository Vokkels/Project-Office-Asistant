import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static java.util.Calendar.*;

/**
 * Created by Deltamike276 on 22/08/2016.
 */
public class GCalendaraObj
{
    private String calendarId;
    private Event.Creator creator;
    private String description;
    private String summary;
    private EventDateTime startDateTime;
    private EventDateTime endDateTime;
    private String location;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    private int offset;
    private EventAttendee[] attendees;

    public String getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(String calendarId) {
        this.calendarId = calendarId;
    }

    public Event.Creator getCreator() {
        return creator;
    }

    public void setCreator(Event.Creator creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public EventDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(int year, int month, int day, int hour, int minute, int second)
    {
        Calendar cal = getInstance();
        cal.set(year, month, day, hour, minute, second);
        Date getDate = cal.getTime();

        DateTime start = new DateTime(getDate,TimeZone.getTimeZone("CAT"));
        this.startDateTime = new EventDateTime().setDateTime(start);
    }

    public EventDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(int year,int month, int day, int hour, int minute, int second)
    {
        Calendar cal = getInstance();
        cal.set(year, month, day, hour, minute, second);
        Date getDate = cal.getTime();

        DateTime end = new DateTime(getDate,TimeZone.getTimeZone("CAT"));
        this.endDateTime = new EventDateTime().setDateTime(end);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public EventAttendee[] getAttendees() {
        return this.attendees;
    }

    public void setAttendees(String email) {

        EventAttendee[] attendants = new EventAttendee[]{new EventAttendee().setEmail(email)};
        this.attendees = attendants;
    }

    public  Event getEvent() {

        Date startDate = new Date();
        startDate = new Date(startDate.getTime() + (3600000 * offset));
        Date endDate = new Date(startDate.getTime() + 3600000);
        DateTime start = new DateTime(startDate, TimeZone.getTimeZone("CAT"));
        DateTime end = new DateTime(endDate, TimeZone.getTimeZone("CAT"));

        Event event = new Event();
            event.setDescription(getDescription());
            event.setSummary(getSummary());
            event.setStart(new EventDateTime().setDateTime(start));
            event.setEnd(new EventDateTime().setDateTime(end));
            event.setAttendees(Arrays.asList(getAttendees()));

         return event;
    }
}
