import org.json.JSONObject;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZanderMac on 2016/08/21.
 */
public class ClientHelper
{
    public static JSONObject checkAvailibility(String date, String time, String employee)
    {
        System.out.println("Date: " + date);//TODO Remove this test line
        System.out.println("Time: " + time);//TODO Remove this test line

        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

        if(date.equals(""))
            date = dateFormat.format(new Date());

        //TODO Check availibility in calendar
        boolean availible = true;



        if(availible)
        {
            return ResponseService.toSpeak("Please provide me with your contact number", true, "SSML");
        }
        return ResponseService.toSpeak("Sorry. " + employee + " will be unavailable at that time. Do you want to try for another time?", true, "SSML");
    }
}
