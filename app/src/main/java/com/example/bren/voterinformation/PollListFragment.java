package com.example.bren.voterinformation;

import android.support.v4.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bren.voterinformation.Login.Name;
import static com.example.bren.voterinformation.Login.PREFERENCE;
import static com.example.bren.voterinformation.Login.Pos;

public class PollListFragment extends Fragment {
    ListView listView;
    CustomAdapter customAdapter;
    List<VoterPollModel> pollArray;
    ArrayList<String> pollNames = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FrameLayout view = (FrameLayout) inflater.inflate(R.layout.fragment_poll_list, container, false);

        listView = view.findViewById(R.id.textPoll);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        VoterModel voter;
        DatabaseHelper helper = new DatabaseHelper(this.getActivity());
        String username = sharedPreferences.getString(Name, "missing");
        pollArray = helper.getAllVoterPolls(username);
        VoterPollModel voterPoll;
        for (int i=0; i<pollArray.size(); i++) {

            voterPoll = pollArray.get(i);
            pollNames.add(voterPoll.getPoll());
        }
        customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PREFERENCE, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putInt(Pos, position);
                editor.commit();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PollTakeFragment fragmentHome = new PollTakeFragment();

                fragmentTransaction.replace(R.id.fragment, fragmentHome);
                fragmentTransaction.commit();
            }

        });


        return view;
    }


    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            System.out.print(pollArray.size());
            return pollArray.size();

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
            convertView = getLayoutInflater().inflate(R.layout.custom_poll_list,null);
            TextView textViewRole = convertView.findViewById(R.id.textPoll);

            textViewRole.setText(pollNames.get(i));
            return convertView;
        }
    }

}