/**
 * Created by Elnette on 2016/08/21.
 */
public class Insurance
{
    public static String getInsureInfo(String insure)
    {
        String output;
        String smlName;
        String online = ". For your convenience you can also apply for this type of insurance online at the url on the screen ";
        //String appointment = ". would you like to make an appointment for a ";
        String appointment = ". If you would like to make an appointment. Confirm by saying if you agree or if you don't agree ";


        System.out.println(insure);
        if(insure.equalsIgnoreCase("Home insurance")||insure.equalsIgnoreCase("House insurance"))
        {
            smlName = "No documentation is required to apply for ";
            output = smlName + insure + appointment + insure + online;
            return output;
        }
        else if(insure.equalsIgnoreCase("Travel insurance"))
        {
            smlName = "basic cover comes standard if tickets are purchased with a standard bank master or visa card";
            output = smlName + "From additional cover schedule an appointment with one of our staff members" + appointment;
            return output;
        }
        else if(insure.equalsIgnoreCase("funeral insurance"))
        {
            smlName = "No documentation is required to apply for ";
            output = smlName + appointment /*+ online*/;
            return output;
        }
        else if(insure.equalsIgnoreCase("life insurance"))
        {
            output = "To apply for life insurance, give us your number and we will contact you to schedule a meeting";
            return output;
        }
        else
        {
           return "It seems we do not have this insurance. I can give you information on home, Vehicle, travel, funeral or life insurance. alternatively you can contact our loans department manager at 0 1 2. 5 1 7. 6 8 0 8 ";
        }
    }
}
