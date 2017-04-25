import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by ZanderMac on 2016/08/21.
 */
public class AlexaFileOps
{
    public static void write(String contents, String filename)
    {
        try
        {
            PrintWriter writer = new PrintWriter(new File(filename));
            writer.println(contents);
            //writer.appendLine(input.nextLine());
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println("");
        }
    }

    public static String read(String filename)
    {
        try
        {
            Scanner input = new Scanner(new File(filename));
            String contents = input.next();;
            input.close();
            return contents;
        }
        catch(Exception ex)
        {
            return "";
        }
    }
    public static String readFile(String filename)
    {
        try
        {
            Scanner input = new Scanner(new File(filename));
            String contents = "";
            while(input.hasNext())
                contents += input.nextLine();
            input.close();
            return contents;
        }
        catch(Exception ex)
        {
            return "";
        }
    }
}
