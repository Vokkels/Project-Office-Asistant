/**
 * Created by Deltamike276 on 22/08/2016.
 */
public class test
{
    public static void main(String[] args) {
        //TEST database connection
        //MySQLDatabase.printAllNames();

        //MySQLDatabase.setEmpStatus("3", "Home");
        System.out.println(MySQLDatabase.getEmpStatus("25131699"));
    }
}
