/**
 * Created by Deltamike276 on 21/08/2016.
 */

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.batch.BatchRequest;
import com.google.api.client.googleapis.batch.json.JsonBatchCallback;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.json.GoogleJsonError;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Lists;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.*;
import com.google.api.services.calendar.model.*;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.client.util.DateTime;

import com.google.api.services.calendar.model.Event;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.joda.time.DateTimeUtils;

import java.io.*;

import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;
import java.util.Arrays;

public class GoogleCal
{
    private static final String APPLICATION_NAME = "Office Assistant";

    private static final java.io.File DATA_STORE_DIR =
            new java.io.File(System.getProperty("user.home"), ".store/calendar_sample");

    private static FileDataStoreFactory dataStoreFactory;
    private static HttpTransport httpTransport;
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static com.google.api.services.calendar.Calendar client;
    static final java.util.List<Calendar> addedCalendarsUsingBatch = Lists.newArrayList();

    /** Authorizes the installed application to access user's protected data. */
    private static Credential authorize() throws Exception {
        // load client secrets
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
                new InputStreamReader(GoogleCal.class.getResourceAsStream("/client_secrets.json")));
        if (clientSecrets.getDetails().getClientId().startsWith("Enter")
                || clientSecrets.getDetails().getClientSecret().startsWith("Enter ")) {
            System.out.println(
                    "Enter Client ID and Secret from https://code.google.com/apis/console/?api=calendar "
                            + "into calendar-cmdline-sample/src/main/resources/client_secrets.json");
            System.exit(1);
        }
        // set up authorization code flow
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets,
                Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(dataStoreFactory)
                .build();
        // authorize
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public static void main(String[] args)
    {
        /*
        try {
            // initialize the transport
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();

            // initialize the data store factory
            dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

            // authorization
            Credential credential = authorize();

            // set up global Calendar instance
            client = new com.google.api.services.calendar.Calendar.Builder(
                    httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();

            // run commands
            //showCalendars();
            //makeAppointment();
            //addCalendar();

            */

            GCalendaraObj cal = new GCalendaraObj();
            cal.setDescription("Meeting with blablaba");
            cal.setSummary("Last Last Dev");
            cal.setCalendarId("primary");
            cal.setOffset(2);
            cal.setLocation("Standard Bank Room G554");
            cal.setAttendees("elnette.moller@gmail.com");
            makeAppointment(cal);

        /*}
        catch (Exception x)
        {
            System.out.println(x.getMessage());
        }*/
    }

    private static void showCalendars() throws IOException
    {
        //View.header("Show Calendars");
        CalendarList feed = client.calendarList().list().execute();
        //View.display(feed);
        System.out.println("Feed: " + feed.toString());
    }

    public static void makeAppointment(GCalendaraObj calander)
    {
        try {
            // initialize the transport
            httpTransport = GoogleNetHttpTransport.newTrustedTransport();

            // initialize the data store factory
            dataStoreFactory = new FileDataStoreFactory(DATA_STORE_DIR);

            // authorization
            Credential credential = authorize();

            // set up global Calendar instance
            client = new com.google.api.services.calendar.Calendar.Builder(
                    httpTransport, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();

            Event newEvent = calander.getEvent();
            client.events().insert(calander.getCalendarId(), newEvent).setSendNotifications(true).execute();
            System.out.println("Appointment made!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addCalendar() throws IOException
    {
        //View.header("Add Calendar");
        Calendar entry = new Calendar();

        Event event = new Event();
        entry.setSummary("Calendar for Testing 3");

        Date startDate = new Date();
        Date endDate = new Date(startDate.getTime() + 3600000);
        DateTime start = new DateTime(startDate, TimeZone.getTimeZone("CAT"));
        event.setStart(new EventDateTime().setDateTime(start));
        DateTime end = new DateTime(endDate, TimeZone.getTimeZone("CAT"));
        event.setEnd(new EventDateTime().setDateTime(end));

        EventAttendee[] attendees = new EventAttendee[]{new EventAttendee().setEmail("13danielmalan@gmail.com")};
        event.setAttendees(Arrays.asList(attendees));
        String calendarId = "primary";

        event = client.events().insert(calendarId, event).execute();
        //Event importedEvent = entry.events().calendarImport('primary', event).execute();
        //Calendar result = client.calendars().insert(entry).execute();
        //View.display(result)}
    }
}
