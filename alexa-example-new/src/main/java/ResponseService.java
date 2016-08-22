/**
 * Created by ZanderMac on 2016/08/20.
 */

import org.json.JSONException;

import org.json.JSONObject;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


import java.util.Date;

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



    // Get slot value for the IntentRequest

    public static String getNameSlotValue(JSONObject obj)

    {


        JSONObject requestTypeObject = obj.getJSONObject("request");

        JSONObject intentObject = requestTypeObject.getJSONObject("intent");

        JSONObject slotsObject = intentObject.getJSONObject("slots");


        String name = slotsObject.getJSONObject("Person").getString("value");
	
	//Default

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


    public static String getAppointmentSubjectSlotValue(JSONObject obj)

    {


        JSONObject requestTypeObject = obj.getJSONObject("request");

        JSONObject intentObject = requestTypeObject.getJSONObject("intent");

        JSONObject slotsObject = intentObject.getJSONObject("slots");


        String subject = slotsObject.getJSONObject("Subject").getString("value");//Default

        return subject;

    }



    // Construct the JSON response sent back to the skill service

    public static JSONObject constructResponseObject(JSONObject obj, String requestType)

    {


        String outputSpeechText = "Error";

        String outputSpeechType;



        if (requestType.equals("LaunchRequest"))

        {
            outputSpeechText = "How can I assist you?";

            outputSpeechType = "PlainText";

        }

        else if (requestType.equals("IntentRequest"))

        {


            //String ssmlName = "<say-as interpret-as=\"spell-out\">" + name + "</say-as>";//Default


            String getLocationPatternString = ".GetLocation.";

            Pattern getLocationPattern = Pattern.compile(getLocationPatternString);

            Matcher getLocationMatcher = getLocationPattern.matcher(obj.toString());



            String getTimePatternString = ".GetTime.";

            Pattern getTimePattern = Pattern.compile(getTimePatternString);

            Matcher getTimeMatcher = getTimePattern.matcher(obj.toString());



            String getAppointmentPatternString = ".MakeAppointment.";

            Pattern getAppointmentPattern = Pattern.compile(getAppointmentPatternString);

            Matcher getAppointmentMatcher = getAppointmentPattern.matcher(obj.toString());



            if(getLocationMatcher.find())

            {

                String name = getNameSlotValue(obj);// get the name of the person as a subject

                outputSpeechText = EmployeeStatus.toSpeak(EmployeeStatus.getEmployeeStatus(name));

            }

            else if(getTimeMatcher.find())

            {

                String name = getNameSlotValue(obj);// get the name of the person as a subject

                outputSpeechText = EmployeeStatus.toSpeak(EmployeeStatus.getEmployeeTimeRemaing(name));

            }

            else if(getAppointmentMatcher.find())

            {

                String client = getClientSlotValue(obj);// get the name of the person as a subject

                String employee = getEmployeeSlotValue(obj);

                String date = getDateSlotValue(obj);

                String time = getTimeSlotValue(obj);


                if(ClientHelper.checkAvailibility(date, time, employee))

                {

                    outputSpeechText = EmployeeStatus.toSpeak(employee + " will be available at that time. What should I say is the reason for the appointment");


                    String subject = "";//getAppointmentSubjectSlotValue(obj);

                    outputSpeechText = EmployeeStatus.toSpeak((ClientHelper.makeAppointment(client, date, time, subject, employee)));

                }


            }

            outputSpeechType = "SSML";//?

        }

        else

        {

            // Blank response for SessionEndRequest

            //outputSpeechText = "";


            outputSpeechText = "I did not understand you. please rephrase the question";

            outputSpeechType = "PlainText";

        }



        // Construct JSON response package

        JSONObject outputSpeechElement = new JSONObject();

        outputSpeechElement.put("type", outputSpeechType);



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


        JSONObject responseObject = new JSONObject();

        responseObject.put("response", outputSpeech);


        return responseObject;

    }


}
