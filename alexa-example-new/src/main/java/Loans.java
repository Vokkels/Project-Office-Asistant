import java.util.Calendar;
import java.util.Date;

public class Loans
{
    public static String getLoanInfo(String loan)
    {
        String output;
        String ssmlName;
        String appointment = ". If you would like to make an appointment. Confirm by saying if you agree or if you don't agree ";
        String ltps = "I can give you information on home loans, student loans, personal loans or Vehicle finance. What loan should I look up";

        if (loan.equalsIgnoreCase("loan")||loan.equalsIgnoreCase("loan types")||loan.equalsIgnoreCase("loan options")||loan.equalsIgnoreCase("types of loans")||loan.equalsIgnoreCase("type of loans"))
        {
            System.out.println(loan);
            return ltps;
        }
        else if (loan.equalsIgnoreCase("Home loan"))
        {
            //office = "Home loan office";
            System.out.println(loan);
            ssmlName = "Your 24 most recent salary advices";
            output = "To apply for a  " + loan + " you will need, " + ssmlName + appointment /*+ loan*/;
            return output;
        }
        else if (loan.equalsIgnoreCase("Student loan"))
        {
            System.out.println(loan);
            ssmlName = "Proof of registration at a tertiary institution, and your academic results for " + (Calendar.getInstance().get(Calendar.YEAR) - 1);
            output = "To apply for a  " + loan + " you will need, " + ssmlName + appointment /*+ loan*/ ;
            return output;
        }
        else if (loan.equalsIgnoreCase("Personal loan"))
        {
            ssmlName = "not need any documents. For your convenience application can also be done online on the standard bank website under personal. borrowing. personal loans";
            output = "To apply for a  " + loan + " you will, " + ssmlName + appointment /*+ loan*/;
            System.out.println(loan);
            return output;
        }
        else if (loan.equalsIgnoreCase("Vehicle"))
        {
            ssmlName = "not need any documents. For your convenience application can also be done online on the standard bank website under personal. borrowing. Vehicle and Asset Finance";
            output = "To apply for a  " + loan + " you will need, " + ssmlName + appointment /*+ loan*/;
            System.out.println(loan);
            return output;
        }
        else
        {
            output = "It seems we do not have such loan. I can give you information on home loans, student loans, personal loans or Vehicle finance. alternatively you can contact our loans department manager at 1 2 3. 4 5 6. 7 8 9 0 ";
            System.out.println(loan);
            return output;
        }
    }
}
