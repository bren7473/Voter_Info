package com.example.bren.voterinformation;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bren.voterinformation.Login.PREFERENCE;


public class MySenateFragment extends Fragment {

    public MySenateFragment(){

    }
    ListView listView;
    CustomAdapter customAdapter;

    ArrayList<String> rolesList = new ArrayList<>();
    ArrayList<String> namesList = new ArrayList<>();
    ArrayList<String> partysList = new ArrayList<>();
    ArrayList<String> imagesList = new ArrayList<>();
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
        final String mJSONURLString = JSON.civicApiSenate(address);
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
                        // Use response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try{
                            // Get the JSON array
                            JSONArray array = response.getJSONArray("offices");
                            JSONArray array1 = response.getJSONArray("officials");

                            // Loop through the array elements
                            for(int i=0;i<array.length();i++){
                                // Get current json object
                                JSONObject offices = array.getJSONObject(i);
                                JSONObject officials = array1.getJSONObject(i);

                                // Get the current student (json object) data
                                String j = (offices.getString("name"));
                                System.out.println(j);
                                rolesList.add(offices.getString("name"));
                                namesList.add(officials.getString("name"));
                                partysList.add(officials.getString("party"));
                                imagesList.add(officials.getString("photoUrl"));

                                customAdapter.notifyDataSetChanged();


                                // Display the formatted json data in text view

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        // Do something when error occurred

                    }
                }
        );

        // Add JsonObjectRequest to the RequestQueue
        requestQueue.add(jsonObjectRequest);


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
            TextView textViewParty = convertView.findViewById(R.id.repParty);
            System.out.println(imagesList.get(i));
            Ion.with(imageView)
                    .placeholder(R.drawable.ic_portrait)
                    .error(R.drawable.ic_error)
                    //.animateLoad(spinAnimation)
                    //.animateIn(fadeInAnimation)
                    .load(imagesList.get(i));
            textViewRole.setText(rolesList.get(i));
            textViewName.setText(namesList.get(i));
            textViewParty.setText(partysList.get(i));
            return convertView;
        }
    }
}