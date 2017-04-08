/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates6
 * and open the template in the editor.
 */

/**
 *
 * @author Developer
 */

import org.json.JSONObject;

import java.util.*;
import java.lang.*;

public class WorkOder
{
    int OrderNum;


    public String[] sServices = {"Deposit","Card","Account","Loan","Insurance"};
    public String sOutput;
    public String sservice;

    public WorkOder()
    {
        sOutput = "";
    }

    public JSONObject Service(String pService)
    {//loan
        //student loan
        //1 section capabilities
        //2 section
        //end section, why is this cool solution

        int des = ServiceSupport(pService);
        if(des == 0)
        {
            if(pService.toUpperCase().compareTo("CARD") == 0)
            {
                return ResponseService.toSpeak("You will only need your identification card. to be assisted Visit one of the following places " + getStation("Banking"), false, "SSML");
            }
            else if (pService.toUpperCase().compareTo("ACCOUNT") == 0)
            {
                return ResponseService.toSpeak("You will need an identity card. proof of residence and a payslip. Visit one of the following stations " + getStation("Banking"), false, "SSML");
            }

            return ResponseService.toSpeak("Visit one of the following stations for assistance. " + getStation("Banking"), false, "SSML");

        }
        else if(des == 1)
        {
            System.out.println(sservice);
            sOutput = " ";//TODO

            return ResponseService.toSpeak("For " + sservice +  " you will need to make an appointment. If you would like to make an appointment. Confirm by saying if you agree or if you don't agree ", true, "SSML");
        }
        else
        {

            return ResponseService.toSpeak("There is no such service at this bank, Please repeat your enquiry.", false, "SSML");
        }
    }



    public int ServiceSupport(String pService)//Check in with database to see if service is supported
    {

        short assume = 3;
        for(int i = 0; i < 3;i++)
        {
            if(pService.toUpperCase().compareTo(sServices[i].toUpperCase()) == 0)
            {
                sservice =  sServices[i];
                assume = 0;
                return assume;
            }
        }

        for(int i = 3; i < 6;i++)
        {
            if(pService.toUpperCase().compareTo(sServices[i].toUpperCase())== 0)
            {
                sservice =  sServices[i];
                assume = 1;
                return assume;
            }
        }

        return assume;
    }

    public String getStation(String pService)
    {

        String   a , Station , tempS;
        Station = " ";
        a = " ";
        tempS ="";

        List<String> list = MySQLDatabase.getEmployeeRelatedToResponsibilities(pService);
        List<String[]> b;

        for(int i = 0; i < list.size();i++)
        {

            a =  list.get(i);

            b = MySQLDatabase.getEmpData(a);

                if(b.size() == 1)
                tempS = b.get(0)[0];
                else if(b.get(i)[3] == "Banking")
                {
                    tempS = b.get(i)[3];
                }



            Station +=  "  , " + tempS;
        }

        return Station;


    }

    public String getEmpName ()
    {
        String  a , Names;

        Names = " ";
        a  = " ";
        List<String> list = MySQLDatabase.getEmployeeRelatedToResponsibilities(sservice);


        for(int i = 0; i < list.size() - 1;i++)
        {
            a  =  list.get(i);
            Names +=  " "  + a;
        }

        return Names;

    }






}
