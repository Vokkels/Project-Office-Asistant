import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZanderMac on 2016/08/21.
 */
public class ClientHelper
{
    public static boolean checkAvailibility(String date, String time, String employee)
    {
        System.out.println("Date: " + date);//TODO Remove this test line
        System.out.println("Time: " + time);//TODO Remove this test line

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        if(date.equals(""))
            date = dateFormat.format(new Date());

        //TODO Check availibility in calendar
        boolean availible = false;

        if(availible)
        {
            return true;
        }
        return false;
    }

    public static String makeAppointment(String ClientName, String date, String time, String description, String employee)
    {
        //TODO Make appointment with Daniels Google Calendar

        return "Appointment scheduled with " + employee + " on the " + date + " at " + time + ". Please be on time.";
    }


}
