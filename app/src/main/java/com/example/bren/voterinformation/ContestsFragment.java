package com.example.bren.voterinformation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.koushikdutta.ion.Ion;
import com.squareup.okhttp.OkHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bren.voterinformation.Login.PREFERENCE;
import static com.google.gson.internal.bind.TypeAdapters.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContestsFragment extends Fragment {

    ListView listView;
    CustomAdapter customAdapter;

    ArrayList<String> typeList = new ArrayList<>();
    ArrayList<String> officeList = new ArrayList<>();
    ArrayList<String> candidateList = new ArrayList<>();
    ArrayList<String> partyList = new ArrayList<>();
    ArrayList<String> imagesList = new ArrayList<>();
    ArrayList<String> urlList = new ArrayList<>();
    ArrayList<String> emailList = new ArrayList<>();
    private Button mButtonDo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FrameLayout view = (FrameLayout) inflater.inflate(R.layout.fragment_contests, container, false);

        listView = view.findViewById(R.id.list);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        VoterModel voter;

        String username = sharedPreferences.getString(Login.Name, "missing");
        DatabaseHelper dh = new DatabaseHelper(this.getActivity());
        voter = dh.getVoter(username);
        String address = voter.getAddress().toString();
        JsonBuilder JSON = new JsonBuilder();
        final String mJSONURLString = JSON.civicApiUpcomingElections(address);
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
                                    JSONArray array = response.getJSONArray("contests");

                                    // Loop through the array elements
                                    for (int i = 0; i < array.length(); i++) {
                                        // Get current json object
                                        JSONObject contests = array.getJSONObject(i);
                                        typeList.add(contests.getString("type"));
                                        String j = (contests.getString("type"));
                                        System.out.println(j);

                                        imagesList.add("empty");

                                        if (contests.isNull("office")){
                                            officeList.add("Not Available");
                                            String e = "no office";
                                            System.out.println(e);
                                        }else {
                                            officeList.add(contests.getString("office"));
                                            String e = (contests.getString("office"));
                                            System.out.println(e);
                                        }

                                        if(contests.has("candidates")) {
                                            JSONArray array1 = array.getJSONObject(i).getJSONArray("candidates");

                                            for (int n = 0; n < array1.length(); n++) {

                                                JSONObject candidates = array1.getJSONObject(n);

                                                if (candidates.isNull("name")) {
                                                    candidateList.add("Not Available");
                                                    String e = "Not Available";
                                                    System.out.println(e);
                                                } else {
                                                    candidateList.add(candidates.getString("name"));
                                                }
                                                if (candidates.isNull("party")) {
                                                    partyList.add("Not Available");
                                                    String e = "no email";
                                                    System.out.println(e);
                                                } else {
                                                    partyList.add(candidates.getString("party"));
                                                }
                                                if (candidates.isNull("candidateUrl")) {
                                                    urlList.add("Not Available");
                                                    String e = "no email";
                                                    System.out.println(e);
                                                } else {
                                                    urlList.add(candidates.getString("candidateUrl"));
                                                    String e = (candidates.getString("candidateUrl"));
                                                    System.out.println(e);
                                                }
                                                if (candidates.isNull("email")) {
                                                    emailList.add("Not Available");
                                                    String e = "no email";
                                                    System.out.println(e);
                                                } else {
                                                    emailList.add(candidates.getString("email"));
                                                    String e = (candidates.getString("email"));
                                                    System.out.println(e);
                                                }
                                            }
                                        }

                                        // Display the formatted json data in text view

                                        customAdapter.notifyDataSetChanged();
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
/*
        String url = "https://api.propublica.org/congress/v1/statements/search.json?query=MY_SEARCH_TERM_HERE&offset=$i";


        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://api.propublica.org/congress/v1/statements/search.json?query=MY_SEARCH_TERM_HERE&offset=$i")
                .header("Authorization", "\"X-API-KEY: Kjmolj8XJcf4DXcJ0XW5spASVlK3mHEysvmhqQP0\"")
                .url("https://api.github.com/users/vogella")
                .build();
        public static String getResponseFromHttpUrl(URL url) throws IOException {

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.addRequestProperty("Accept","application/json");
            connection.addRequestProperty("Content-Type","application/json");
            connection.addRequestProperty("Authorization","X-API-KEY: Kjmolj8XJcf4DXcJ0XW5spASVlK3mHEysvmhqQP0");

            try {
                InputStream in = connection.getInputStream();

                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                boolean hasInput = scanner.hasNext();
                if (hasInput) {
                    return scanner.next();
                } else {
                    return null;
                }
            } finally {
                connection.disconnect();
            }
        }
        */

                customAdapter = new CustomAdapter();
                listView.setAdapter(customAdapter);

        return view;
    }


    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            System.out.print(imagesList.size());
            return imagesList.size();

        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.customlayout,null);
            ImageView imageView = convertView.findViewById(R.id.repImage);
            TextView textViewRole = convertView.findViewById(R.id.textPoll);
            TextView textViewName = convertView.findViewById(R.id.repName);
            System.out.println(imagesList.get(i));

            Ion.with(imageView)
                    .placeholder(R.drawable.ic_portrait)
                    .error(R.drawable.ic_check)
                    //.animateLoad(spinAnimation)
                    //.animateIn(fadeInAnimation)
                    .load(imagesList.get(i));

            textViewRole.setText(typeList.get(i));
            textViewName.setText(officeList.get(i));
            return convertView;
        }
    }

}