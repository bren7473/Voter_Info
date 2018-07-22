package com.example.bren.voterinformation;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bren.voterinformation.Login.Email;
import static com.example.bren.voterinformation.Login.PREFERENCE;
import static com.example.bren.voterinformation.Login.Phone;
import static com.example.bren.voterinformation.Login.Site;

public class MainViewController extends Fragment {
    String stateElection = null;
    String localElection = null;
    String localEmail = null;
    String localNumber  = "(555) 555-5555";


    public WebView mWebView;
    public MainViewController() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);


        VoterModel voter;
        final SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        String username = sharedPreferences.getString(Login.Name, "missing");
        DatabaseHelper dh = new DatabaseHelper(this.getActivity());
        voter = dh.getVoter(username);
        String address = voter.getAddress().toString();
        JsonBuilder JSON = new JsonBuilder();
        final String mJSONURLString = JSON.civicApiHome(address);
        System.out.println(mJSONURLString);





        // Empty the TextView

        // Initialize a new RequestQueue instance
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        // Initialize a new JsonObjectRequest instance
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                mJSONURLString,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try {
                            // Get the JSON array
                            JSONArray array = response.getJSONArray("state");

                            // Loop through the array elements
                            for (int i = 0; i < array.length(); i++) {
                                // Get current json object
                                JSONObject electionAdministration = array.getJSONObject(i);

                                JSONObject electionInfo = (electionAdministration.getJSONObject("electionAdministrationBody"));
                                stateElection = (electionInfo.getString("electionInfoUrl"));
                                System.out.println(stateElection);

                                JSONObject local_jurisdiction = electionAdministration.getJSONObject("local_jurisdiction");
                                JSONObject electionAdministration1 = local_jurisdiction.getJSONObject("electionAdministrationBody");
                                JSONArray contactInfo = electionAdministration1.getJSONArray("electionOfficials");
                                for (int n = 0; n <contactInfo.length();n++){
                                    JSONObject localContact = contactInfo.getJSONObject(n);
                                    localEmail = localContact.getString("emailAddress");
                                    localNumber = localContact.getString("officePhoneNumber");
                                }
                                localElection = (electionAdministration1.getString("electionInfoUrl"));
                                System.out.println(localElection);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred

                    }
                }
        );

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjectRequest);


        Button register = view.findViewById(R.id.mainRegister2);

        register.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                            public void onClick(View v) {
                                                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFERENCE, MODE_PRIVATE);
                                                SharedPreferences.Editor editor = sharedPreferences.edit();

                                                editor.putString(Site,stateElection);
                                                editor.commit();
                                                FragmentManager fragmentManager = getFragmentManager();
                                                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                                WebviewFragment fragmentHome = new WebviewFragment();

                                                fragmentTransaction.replace(R.id.fragment, fragmentHome);
                                                fragmentTransaction.commit();

                                            }
                                        });
                Button localElections = view.findViewById(R.id.mainRegister3);

                localElections.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFERENCE, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString(Site,localElection);
                        editor.commit();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        WebviewFragment fragmentHome = new WebviewFragment();

                        fragmentTransaction.replace(R.id.fragment, fragmentHome);
                        fragmentTransaction.commit();

                    }
                });
        Button localInformation = view.findViewById(R.id.mainRegister);

        localInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFERENCE, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(Phone,localNumber);
                editor.putString(Email,localEmail);
                editor.commit();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                localContact fragmentHome = new localContact();

                fragmentTransaction.replace(R.id.fragment, fragmentHome);
                fragmentTransaction.commit();

            }
        });
            // Inflate the layout for this fragment
        return view;
        }
    }
