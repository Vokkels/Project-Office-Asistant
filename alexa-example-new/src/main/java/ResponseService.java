/**
 * Created by ZanderMac on 2016/08/20.
 */
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;

import javax.jnlp.IntegrationService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseService
{
    // Get request type from JSON request
    public static String getRequestType(JSONObject obj)
    {
        JSONObject requestTypeObject = obj.getJSONObject("request");
        String requestType =  requestTypeObject.getString("type");
        return requestType;
    }
    public static String getSlotValue(JSONObject obj)
    {
        JSONObject requestTypeObject = obj.getJSONObject("request");
        JSONObject intentObject = requestTypeObject.getJSONObject("intent");
        JSONObject slotsObject = intentObject.getJSONObject("slots");
        String name = slotsObject.getJSONObject("service").getString("value");
        return name;
    }

    // Get slot value for the IntentRequest
    public static String getNameSlotValue(JSONObject obj)
    {

        JSONObject requestTypeObject = obj.getJSONObject("request");
        JSONObject intentObject = requestTypeObject.getJSONObject("intent");
        JSONObject slotsObject = intentObject.getJSONObject("slots");
        String name = slotsObject.getJSONObject("Person").getString("value");//Default
        return name;
    }

    public static String getClientSlotValue(JSONObject obj)
    {
        JSONObject requestTypeObject = obj.getJSONObject("request");
        JSONObject intentObject = requestTypeObject.getJSONObject("intent");
        JSONObject slotsObject = intentObject.getJSONObject("slots");
        String client = slotsObject.getJSONObject("Client").getString("value");//Default
        return client;
    }
    public static String getEmployeeSlotValue(JSONObject obj)
    {
        JSONObject requestTypeObject = obj.getJSONObject("request");
        JSONObject intentObject = requestTypeObject.getJSONObject("intent");
        JSONObject slotsObject = intentObject.getJSONObject("slots");
        String employee = slotsObject.getJSONObject("Employee").getString("value");//Default
        return employee;
    }
    public static String getDateSlotValue(JSONObject obj)
    {
        String date = (new Date()).toString();
        try
        {
            JSONObject requestTypeObject = obj.getJSONObject("request");
            JSONObject intentObject = requestTypeObject.getJSONObject("intent");
            JSONObject slotsObject = intentObject.getJSONObject("slots");
            date = slotsObject.getJSONObject("Date").getString("value");//Default
        }
        catch(JSONException jsonex)
        {
            return date;
        }
        return date;
    }
    public static String getTimeSlotValue(JSONObject obj)
    {
        JSONObject requestTypeObject = obj.getJSONObject("request");
        JSONObject intentObject = requestTypeObject.getJSONObject("intent");
        JSONObject slotsObject = intentObject.getJSONObject("slots");
        String time = slotsObject.getJSONObject("Time").getString("value");//Default
        return time;
    }
    public static String getAnswerSlotValue(JSONObject obj)
    {
        JSONObject requestTypeObject = obj.getJSONObject("request");
        JSONObject intentObject = requestTypeObject.getJSONObject("intent");
        JSONObject slotsObject = intentObject.getJSONObject("slots");
        String answer = slotsObject.getJSONObject("Answer").getString("value");//Default
        return answer;
    }
    public static String getConfirmationSlotValue(JSONObject obj)
    {
        JSONObject requestTypeObject = obj.getJSONObject("request");
        JSONObject intentObject = requestTypeObject.getJSONObject("intent");
        JSONObject slotsObject = intentObject.getJSONObject("slots");
        String confirmation = slotsObject.getJSONObject("Message").getString("value");//Default
        return confirmation;
    }
    public static String getLocationSlotValue(JSONObject obj)
    {
        JSONObject requestTypeObject = obj.getJSONObject("request");
        JSONObject intentObject = requestTypeObject.getJSONObject("intent");
        JSONObject slotsObject = intentObject.getJSONObject("slots");
        String number = slotsObject.getJSONObject("Location").getString("value");//Default
        return number;
    }
    public static String getNumberSlotValue(JSONObject obj)
    {
        JSONObject requestTypeObject = obj.getJSONObject("request");
        JSONObject intentObject = requestTypeObject.getJSONObject("intent");
        JSONObject slotsObject = intentObject.getJSONObject("slots");
        String answer = slotsObject.getJSONObject("Number").getString("value");//Default
        return answer;
    }
    public static String getLoanSlotValue(JSONObject obj)

    {
        JSONObject requestTypeObject = obj.getJSONObject("request");
        JSONObject intentObject = requestTypeObject.getJSONObject("intent");
        JSONObject slotsObject = intentObject.getJSONObject("slots");

        String lname = slotsObject.getJSONObject("Loan").getString("value");

        return lname;
    }

    public static String getClientNameSlotValue(JSONObject obj)
    {

        JSONObject requestTypeObject = obj.getJSONObject("request");
        JSONObject intentObject = requestTypeObject.getJSONObject("intent");
        JSONObject slotsObject = intentObject.getJSONObject("slots");
        String c_name = slotsObject.getJSONObject("Name").getString("value");//Default
        return c_name;
    }

    public static String getInsuranceSlotValue(JSONObject obj)
    {
        JSONObject requestTypeObject = obj.getJSONObject("request");
        JSONObject intentObject = requestTypeObject.getJSONObject("intent");
        JSONObject slotsObject = intentObject.getJSONObject("slots");
        String iname = slotsObject.getJSONObject("Insure").getString("value");//Default
        return iname;
    }

    // Construct the JSON response sent back to the skill service
    public static JSONObject constructResponseObject(JSONObject obj, String requestType)
    {
        String outputSpeechText = "Error";
        JSONObject responseObject = new JSONObject();
        WorkOder test = new WorkOder();

        //String outputSpeechType;

        if (requestType.equals("LaunchRequest"))
        {
            responseObject = toSpeak("How can I assist you?", true, "SSML");
        }
        else if (requestType.equals("IntentRequest"))
        {
            String getLocationPatternString = ".GetLocation.";
            Pattern getLocationPattern = Pattern.compile(getLocationPatternString);
            Matcher getLocationMatcher = getLocationPattern.matcher(obj.toString());

            String getAppointmentPatternString = ".MakeAppointment.";
            Pattern getAppointmentPattern = Pattern.compile(getAppointmentPatternString);
            Matcher getAppointmentMatcher = getAppointmentPattern.matcher(obj.toString());

            String getDatePatternString = ".GetDate.";
            Pattern getDatePattern = Pattern.compile(getDatePatternString);
            Matcher getDateMatcher = getDatePattern.matcher(obj.toString());

            String getTimePatternString = ".GetTime.";
            Pattern getTimePattern = Pattern.compile(getTimePatternString);
            Matcher getTimeMatcher = getTimePattern.matcher(obj.toString());

            String questionPatternString = ".Question.";
            Pattern questionPattern = Pattern.compile(questionPatternString);
            Matcher questionMatcher = questionPattern.matcher(obj.toString());

            String updateLocationPatternString = ".UpdateLocation.";
            Pattern updateLocationPattern = Pattern.compile(updateLocationPatternString);
            Matcher updateLocationMatcher = updateLocationPattern.matcher(obj.toString());

            String getPurposePatternString = ".GetPurpose.";
            Pattern getPurposePattern = Pattern.compile(getPurposePatternString);
            Matcher getPurposeMatcher = getPurposePattern.matcher(obj.toString());

            String getContactNumberPatternString = ".GetContactNumber.";
            Pattern getContactNumberPattern = Pattern.compile(getContactNumberPatternString);
            Matcher getContactNumberMatcher = getContactNumberPattern.matcher(obj.toString());

            String getEmployeeStatusPatternString = ".GetEmployeeStatus.";
            Pattern getEmployeeStatusPattern = Pattern.compile(getEmployeeStatusPatternString);
            Matcher getEmployeeStatusMatcher = getEmployeeStatusPattern.matcher(obj.toString());

            String getLoanPatternString = ".GetLoan.";
            Pattern getLoanPattern = Pattern.compile(getLoanPatternString);
            Matcher getLoanMatcher = getLoanPattern.matcher(obj.toString());

            String getInsurancePatternString = ".GetInsurance.";
            Pattern getInsurancePattern = Pattern.compile(getInsurancePatternString);
            Matcher getInsuranceMatcher = getInsurancePattern.matcher(obj.toString());

            String getQuestionPatternString = ".Ask.";
            Pattern getQuestionPattern = Pattern.compile(getQuestionPatternString);
            Matcher getQuestionMatcher = getQuestionPattern.matcher(obj.toString());

            String getClientNamePatternString = ".ClientName.";
            Pattern getClientNamePattern = Pattern.compile(getClientNamePatternString);
            Matcher getClientNameMatcher = getClientNamePattern.matcher(obj.toString());

            if(getLocationMatcher.find())
            {
                String name = getNameSlotValue(obj);// get the name of the person as a subject
                return EmployeeStatus.getEmployeeStatus(name);
            }
            else if(getAppointmentMatcher.find())
            {
                AlexaFileOps.write(getClientSlotValue(obj), "client.mem");// get the name of the person as a subject
                AlexaFileOps.write(getEmployeeSlotValue(obj), "employee.mem");

                return toSpeak("Please provide a date for the appointment", true, "SSML");
            }
            else if(getDateMatcher.find())
            {
                DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                /*try
                {
                    if (appointment.YEAR < currentDate.YEAR || (appointment.YEAR == currentDate.YEAR && appointment.MONTH < currentDate.MONTH) || (appointment.YEAR == currentDate.YEAR && appointment.MONTH == currentDate.MONTH && appointment.DAY_OF_YEAR < currentDate.DAY_OF_YEAR))
                        return toSpeak("The date you have provide is in the past. Please provide a date in the future for the appointment", true, "SSML");*/
                    AlexaFileOps.write(getDateSlotValue(obj), "date.mem");
                    return toSpeak("Please provide a time for the appointment", true, "SSML");
               /* }
                catch (ParseException pex)
                {
                    AlexaFileOps.write(getDateSlotValue(obj), "date.mem");
                    return toSpeak("Please provide a time for the appointment", true, "SSML");
                }*/
            }
            else if(getTimeMatcher.find())
            {
                String time = getTimeSlotValue(obj);
                return ClientHelper.checkAvailibility(AlexaFileOps.read("date.mem"), time, AlexaFileOps.read("employee.mem"));
            }
            else if(questionMatcher.find())
            {
                String answer = getAnswerSlotValue(obj);
                if(answer.equalsIgnoreCase("Yes"))
                    return toSpeak("Please provide a date for the appointment", true, "SSML");
                else
                    return toSpeak("Alright then. Have a nice day.", false, "SSML");
            }
            else if(updateLocationMatcher.find())
            {
                String location = getLocationSlotValue(obj);
                String empID = getNumberSlotValue(obj);
                return EmployeeStatus.updateEmployeeStatus(empID, location);
            }
            else if(getPurposeMatcher.find())
            {
                return toSpeak(AlexaFileOps.readFile("alexa_purpose.alexa"), false, "SSML");
            }
            else if(getContactNumberMatcher.find())
            {
                //TODO add contact number to appointment description
                GCalendaraObj o = new GCalendaraObj();
                /*DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-mm-dd HH:mm");
                DateTime now = new DateTime();
                now = formatter.parseDateTime("yyyy/mm/dd HH:mm");
                now = new DateTime();
                System.out.println(now.toString());
                String datetime = AlexaFileOps.read("date.mem") + "T" + AlexaFileOps.read("time.mem") + ":00.068+02:00";
                DateTime dtc = formatter.parseDateTime(datetime);
                System.out.println(dtc.toString());
                Period p = new Period(now, dtc);
                System.out.println(p.getHours());*/

                System.out.println(getNumberSlotValue(obj));
                o.setOffset(28);
                o.setAttendees(MySQLDatabase.getEmpData(AlexaFileOps.read("employee.mem")).get(0)[4]);
                System.out.println(AlexaFileOps.read("employee.mem"));
                System.out.println(MySQLDatabase.getEmpData(AlexaFileOps.read("employee.mem")).get(0)[4]);
                o.setSummary("Business Appointment");
                o.setLocation(MySQLDatabase.getEmpData(AlexaFileOps.read("employee.mem")).get(0)[0]);
                o.setDescription("Contact Number: " + getNumberSlotValue(obj));
                AlexaFileOps.write(getNumberSlotValue(obj), "contact_number.mem");
                System.out.println(getNumberSlotValue(obj));

                return toSpeak("An appointment have been made. Please be on time and good day.", false, "SSML");
            }
            else if(getEmployeeStatusMatcher.find())
            {
                String empNum = getNumberSlotValue(obj);
                return EmployeeStatus.getEmployeeLocation(empNum);
            }
            if(getLoanMatcher.find())
            {
                String name = getLoanSlotValue(obj);// get the name of the person as a subject
                AlexaFileOps.write("Loans", "type.mem");
                return toSpeak(Loans.getLoanInfo(name), true, "SSML");
            }
            else if(getInsuranceMatcher.find())
            {
                String name = getInsuranceSlotValue(obj);// get the name of the person as a subject
                AlexaFileOps.write("Insurance", "type.mem");
                return toSpeak(Insurance.getInsureInfo(name), true, "SSML");
            }
            else if(getQuestionMatcher.find())
            {
                String answer = getConfirmationSlotValue(obj);
                if(answer.equalsIgnoreCase("agree"))
                {
                    AlexaFileOps.write("meeting", "person.mem");
                    return toSpeak("What is your name?", true, "SSML");
                }
                else
                    return toSpeak("Alright then. Have a nice day.", false, "SSML");
            }
            else if(getClientNameMatcher.find())
            {
                if(AlexaFileOps.read("person.mem").equals("meeting"))
                {
                    String c_name = getClientNameSlotValue(obj);// get the name of the person as a subject
                    AlexaFileOps.write(c_name, "client.mem");
                    System.out.println(AlexaFileOps.read("type.mem"));
                    String client = MySQLDatabase.getEmployeeRelatedToResponsibilities(AlexaFileOps.read("type.mem")).get(0);
                    AlexaFileOps.write(client, "employee.mem");
                    AlexaFileOps.write("OK", "person.mem");
                    return toSpeak("Please provide a date for the appointment", true, "SSML");
                }
                else
                {
                    AlexaFileOps.write("person.mem", "OK");
                    return EmployeeStatus.getEmployeeStatus(getClientNameSlotValue(obj));
                }
            }
            else
            {
                String outputSpeechElement = getSlotValue(obj);
                return test.Service(outputSpeechElement);
            }
        }
        else
        {
            responseObject = toSpeak("I did not understand you. please rephrase the question", false, "PlainText");
        }
        return responseObject;
    }
    public static JSONObject toSpeak(String sentence, boolean question, String outputSpeechType)
    {
        question = !question;
        //TODO Remove this test line
        System.out.println(sentence);

        JSONObject outputSpeechElement = new JSONObject();
        outputSpeechElement.put("type", outputSpeechType);
        String outputSpeechText = "<speak>" + sentence + "</speak>";

        if(outputSpeechType.equals("SSML"))
        {
            outputSpeechElement.put("ssml", outputSpeechText);
        }
        else
        {
            outputSpeechElement.put("text", outputSpeechText);
        }

        JSONObject outputSpeech = new JSONObject();
        outputSpeech.put("outputSpeech", outputSpeechElement);
        outputSpeech.put("shouldEndSession", question);

        JSONObject responseObject = new JSONObject();
        responseObject.put("response", outputSpeech);

        return responseObject;
    }

}
