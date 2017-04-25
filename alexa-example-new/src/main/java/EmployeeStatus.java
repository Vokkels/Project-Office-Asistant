import org.json.JSONObject;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by ZanderMac on 2016/08/20.
 */
public class EmployeeStatus
{
    public static JSONObject updateEmployeeStatus(String employeeID, String status)
    {
        //TODO read next appointment from calendar
        MySQLDatabase.setEmpStatus(employeeID, status);
        String name = MySQLDatabase.getEmpName(employeeID);
        return ResponseService.toSpeak("Hello " + name + "Your next appointment is with " + "" + " at ", false, "SSML");
    }

    public static JSONObject getEmployeeStatus(String employee)
    {
        String location = "";
        List<String[]> locationList = MySQLDatabase.getEmpData(employee);
        int size = locationList.size();
        if(size == 1)
        {
            AlexaFileOps.write("OK", "person.mem");
            location = locationList.get(0)[0];
            if(location.startsWith("Sorry"))
                return ResponseService.toSpeak("Employee not found. I am sorry. You should rather ask a human", false, "SSML");
            return ResponseService.toSpeak((employee + " is located in " + location), false, "SSML");
        }
        else
        {
            String s = "";
            for(int i = 0; i < size; i++)
            {
                s += locationList.get(i)[1] + ". ";
            }
            AlexaFileOps.write("search", "person.mem");
            return ResponseService.toSpeak(("There are " + size + " " + employee + "'s found. They are. " + s + "Who are you looking for?"), true, "SSML");
        }
    }

    public static JSONObject getEmployeeLocation(String employeeNum)
    {
        return ResponseService.toSpeak(MySQLDatabase.getEmpName(employeeNum) + " is currently " + MySQLDatabase.getEmpStatus(employeeNum), false, "SSML");
    }
}
