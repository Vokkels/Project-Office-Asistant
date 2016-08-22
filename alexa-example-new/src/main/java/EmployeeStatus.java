import java.sql.Time;
import java.util.Date;


/**
 * Created by ZanderMac on 2016/08/20.
 */


public class EmployeeStatus

{

    public static String updateEmployeeStatus(String employee, String status)

    {

        //TODO update database on employee status change

        return "";

    }



    public static String getEmployeeStatus(String employee)

    {

        //TODO Replace with information from database

        //String location = "G 1. 1 0 1";

        //String location = "G 1. 1 1 0";



        if (employee.equalsIgnoreCase("Ms Moller"))

            return "There are two Ms. Moller's. Are you looking for Ms. Elnette Moller or Ms. Jane Moller. Please Reply with. Alexa. Ask Office Assistant where is. Followed by the correct first name.";

        else if (employee.equalsIgnoreCase("Gunther") || employee.equalsIgnoreCase("Prof Gunther") || employee.equalsIgnoreCase("Prof Drevin"))

            return employee + " is at Building G 1. Room 2 0 1.";

        else if (employee.equalsIgnoreCase("Elnette") || employee.equalsIgnoreCase("Ms Elnette"))

            return employee + " is at Building G 1. Room 34";

        else if (employee.equalsIgnoreCase("Jane") || employee.equalsIgnoreCase("Ms Jane"))

            return employee + " is out for lunch.";

        else if (employee.equalsIgnoreCase("Zander") || employee.equalsIgnoreCase("Mr. Zander") || employee.equalsIgnoreCase("Mr. Labuschagne"))

            return employee + " is at Building G 1. Room 2 1 5.";

        else if (employee.equalsIgnoreCase("Thato") || employee.equalsIgnoreCase("Mr. Thato") || employee.equalsIgnoreCase("Mr. Maake"))

            return employee + " is at Building G 1. Room 2 1 2.";
 
       else if (employee.equalsIgnoreCase("Daniel") || employee.equalsIgnoreCase("Mr. Daniel") || employee.equalsIgnoreCase("Mr. Malan"))

            return employee + " is at Building F 1. Room G 12.";

       else//TODO Remove else when everything is working

        {

            System.out.println(employee);//TODO remove this test preview line

            return employee;

        }

    }



    public static String getEmployeeTimeRemaing(String employee)

    {

        return employee + " will return in 30 minutes.";


    }



    public static String scheduleMeeting(String ClientName, Date date, Time time, String description, String employee)

    {

        return "";

    }



    private static String getEmployeeLocation(String employee)

    {

        //TODO add code to retrieve employee location data from database

        return "";

    }

 

   private static String getEmployeeTimeAway(String employee)

   {

        //TODO add code to retrieve employee "time until he/she will be back"

        return "";

    }



    public static String toSpeak(String sentence)

    {

        return "<speak>" + sentence + "</speak>";

    }

}
