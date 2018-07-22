package com.example.bren.voterinformation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

public class JsonBuilder extends AppCompatActivity{
    String jsonCall = null;
    public String civicApiSenate(String address){

        address = address.replace(",","%20");

        jsonCall = "https://www.googleapis.com/civicinfo/v2/representatives?key=AIzaSyAbtPNve1pwCI126oimKzKXAzdK8lw8u3g&address=" + address + "&roles=legislatorupperbody";
        return jsonCall;
    }

    public String civicApiHouse(String address){

        address = address.replace(",","%20");

        jsonCall = "https://www.googleapis.com/civicinfo/v2/representatives?key=AIzaSyAbtPNve1pwCI126oimKzKXAzdK8lw8u3g&address=" + address + "&roles=legislatorlowerbody";
        return jsonCall;
    }

    public String civicApiUpcomingElections(String address){
        address = address.replace(",","%20");

        jsonCall = "https://www.googleapis.com/civicinfo/v2/voterinfo?key=AIzaSyAbtPNve1pwCI126oimKzKXAzdK8lw8u3g&address=" + address + "&electionId=2000&fields=contests(type,office,district(scope),candidates(name,party,candidateUrl,email),referendumTitle,referendumSubtitle,referendumUrl)";
        return jsonCall;
    }

    public String civicApiHome(String address){
        address = address.replace(",","%20");
        jsonCall = "https://www.googleapis.com/civicinfo/v2/voterinfo?key=AIzaSyAbtPNve1pwCI126oimKzKXAzdK8lw8u3g&address=" + address + "&electionId=2000&fields=state(name,electionAdministrationBody,local_jurisdiction)";
        return jsonCall;
    }
}
