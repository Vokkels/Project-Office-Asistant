/**
 * Created by ZanderMac on 2016/08/21.
 * @author Zander Labuschagne
 * E-Mail: ZANDER.LABUSCHAGNE@PROTONMAIL.CH
 * Class used to help the clients of the bank or any other thing with clients
 * Clients can talk to Alexa(Amazon Echo) to check the availability of an employee and to make an appointment with that employee if he or she is available
 */

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHelper
{
    /**
    * Check the availability of an employee
    * @param date provides the date used to check availability
    * @param time provides the time used to check availability
    * @param employee provides the name of the employee to check
    * @return true if employee is available and false if not
    */
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
            return true;
        return false;
    }

    /**
    * Make an appointment with the employee via Google Calendar
    * @param clientName the name of the client making the appointment
    * @param date Date on which appointment is made
    * @param time Time for which appointment is made
    * @param description provides a description of the appointment
    * @param employee the employee for wich the appointment was made
    * @return the String value the Echo must provide as output via speech 
    */
    public static String makeAppointment(String clientName, String date, String time, String description, String employee)
    {
        //TODO Make appointment with Daniels Google Calendar

        return "Appointment scheduled with " + employee + " on the " + date + " at " + time + ". Please be on time.";
    }
}
