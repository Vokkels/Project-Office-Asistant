/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Daniel Malan
 */
public class MySQLDatabase 
{ 
    //predefinded final variables
    private static final String USER = "blackvik_speech";
    private static final String PASS = "projectecho4471";
    private static final String SERVER_NAME = "154.0.168.137";
    
    //Database variables
    private static MysqlDataSource dataSource = null;
    private static Connection conn = null;
    
    /*returns a list of string arrays of the room number associated with the param name
    if the list is greater than one, a list of all posible matches are printed out*/
    public static List<String[]> getEmpData(String searchCase)
    {
        List<String[]> outputList = new LinkedList<String[]>(); //contains possible solutions
        int highestProb = 0;
        
        try
       {
           reconfigure();
           Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery("Select Emp_FirstName, Emp_Surname, Prefix, ID, Responsibility FROM blackvik_echo.Employees;");
            
           //Transfer data to a array
           List<String[]> results = new LinkedList<String[]>();
           while(rs.next())
           {
               String[] list = new String[6];
               list[0] = rs.getString("Emp_Surname");
               list[1] = rs.getString("Emp_FirstName");
               list[2] = rs.getString("Prefix");
               list[3] = rs.getString("OfficeNum");
               list[4] = rs.getString("ID");
               list[5] = rs.getString("Responsibility");
               results.add(list);
           }
                
           //System.out.println("Test " + results.get(0)[0]);
                      
           int listsLength = results.size();
           
           //Devides the posible results into probabilities
           int[] probability = new int[listsLength];
          
           String[] cases;
           CharSequence seq;
           seq = " ";
           
           //Checks if the test case contains a space
           if(searchCase.contains(seq))
            cases = searchCase.split(" "); // splits the posible cases
           else
           {
            cases = new String[1];
            cases[0] = searchCase;
           }
               
            int CaseAmount = cases.length;
            for(int i = 0; i < results.size(); i++)
             {
                 for(int j = 0; j < CaseAmount; j++)
                 {
                    if(results.get(i)[0].equalsIgnoreCase(cases[j]))
                        probability[i] = probability[i] + 1;
                    if(results.get(i)[1].equalsIgnoreCase(cases[j]))
                        probability[i] = probability[i] + 1;
                    if(results.get(i)[2].equalsIgnoreCase(cases[j]))
                        probability[i] = probability[i] + 1;

                    //TEST Highest
                    if(probability[i] > highestProb)
                        highestProb = probability[i];
                 }
             }           

            //DEV
            // System.out.println("Prop: " + highestProb);

             //find the highest probability
            for(int i = 0; i < listsLength; i++)
            {
                if(probability[i] == highestProb && highestProb > 0)
                {
                    String[] output = new String[4];
                    output[0] = results.get(i)[3];//RoomID
                    output[1] = results.get(i)[2] +" "+ results.get(i)[1] +" "+ results.get(i)[0];//Full Name
                    output[2] = results.get(i)[4];
                    output[3] = results.get(i)[5];
                    outputList.add(output);
                }
            }
            
           rs.close();
           stmt.close();
           conn.close();
        }
        catch(Exception x)
        {
            String[] tmp = new String[1];
            tmp[0] = "Database error. Please try again\n" + x.getMessage();
            outputList.set(0, tmp);
            return outputList;
        }
        
        if(outputList.size() > 0)
               return outputList;
        else
        {
            String[] tmp = new String[1];
            tmp[0] = "Sorry. employee not found";
            outputList.add(tmp);
            return outputList;
        }
    }
    
    //rebuilds the server configiration
    private static void reconfigure()
    {
        try
        {
            dataSource = new MysqlDataSource();
            //Instansiate and configure the database server
            dataSource.setUser(USER);
            dataSource.setPassword(PASS);
            dataSource.setServerName(SERVER_NAME);
            
            conn = dataSource.getConnection();
        }
        catch(Exception x)
        {
            System.out.println("ERROR! 1001: " + x.getMessage());
        }
    }
    
    public static List<String> getEmployeeRelatedToResponsibilities(String responseibility)
    {
        try
        {
            reconfigure();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Emp_FirstName, Emp_Surname, Prefix, Responsibility FROM blackvik_echo.Employees;");
           
            //get data from the database
            List<String[]> list = new LinkedList<>();
            while(rs.next())
           {
               String[] data = new String[4];
               data[0] = rs.getString("Prefix") +" "+ rs.getString("Emp_FirstName") +" "+ rs.getString("Emp_Surname");
               data[1] = rs.getString("Responsibility");
               list.add(data);
           }
            
            List<String> empList = new LinkedList<String>();
            
            //generate list of all the employees with that responsibility
            for(int i = 0; i < list.size(); i++)
            {
                if(list.get(i)[1].equalsIgnoreCase(responseibility))
                    empList.add(list.get(i)[0]);
            }
            
            return empList;
        }
        catch(Exception x)
        {
            System.out.println("Error 1003: " + x.getMessage());
        }
        
        return null;
    }

    public static void setEmpStatus(String ID, String statUpdate)
    {
        try
        {
            reconfigure();
            Statement stmt = conn.createStatement();
            //ResultSet rs = stmt.executeQuery("SELECT Emp_FirstName, Emp_Surname, Prefix, Responsibility FROM blackvik_echo.Employees;");
            stmt.executeUpdate("UPDATE blackvik_echo.Employees SET Status = '" + statUpdate + "' WHERE blackvik_echo.Employees.ID = '" + ID + "';");
        }
        catch(Exception x)
        {
            System.out.println(x.getMessage());
        }
    }
    
    public static String getEmpStatus(String ID)
    {
        try
        {
            reconfigure();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select ID, Status FROM blackvik_echo.Employees;");
            
            while(rs.next())
            {
                if(ID.equalsIgnoreCase(rs.getString("ID")))
                        return rs.getString("Status");
            }
        }
        catch(Exception x)
        {
            System.out.println(x.getMessage());
        }
        
        return null;
    }

    public static String getEmpName(String id)
    {
        try
        {
            reconfigure();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select ID, Emp_Surname, Prefix FROM blackvik_echo.Employees;");

            while(rs.next())
            {
                if(id.equalsIgnoreCase(rs.getString("ID")))
                    return rs.getString("Prefix") + " " + rs.getString("Emp_Surname");
            }
        }
        catch(Exception x)
        {
            System.out.println(x.getMessage());
        }

        return null;
    }
}
