package com.example.bren.voterinformation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bren.voterinformation.Login.PREFERENCE;
import static com.example.bren.voterinformation.Login.Pos;

public class PollTakeFragment extends Fragment {

    List<VoterPollModel> pollArray;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FrameLayout view = (FrameLayout) inflater.inflate(R.layout.fragment_poll, container, false);
        DatabaseHelper helper = new DatabaseHelper(this.getActivity());

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(PREFERENCE, MODE_PRIVATE);

        String username = sharedPreferences.getString(Login.Name, "missing");
        int pos = sharedPreferences.getInt(Pos, 0);

        pollArray = helper.getAllVoterPolls(username);
        VoterPollModel voterPoll;
        System.out.println(pollArray);
        voterPoll = pollArray.get(pos);
        String pollName = voterPoll.getPoll();

        System.out.println(pollName);

        PollModel pollModel = helper.getPoll(pollName);

        TextView question = view.findViewById(R.id.textQuestion);
        TextView textA = view.findViewById(R.id.textA);
        TextView textB = view.findViewById(R.id.textB);
        TextView textC = view.findViewById(R.id.textC);
        TextView textD = view.findViewById(R.id.textD);

        question.setText(pollModel.getPollName());
        textA.setText(pollModel.getA());
        textB.setText(pollModel.getB());
        textC.setText(pollModel.getC());
        textD.setText(pollModel.getD());


        return view;
    }


}
