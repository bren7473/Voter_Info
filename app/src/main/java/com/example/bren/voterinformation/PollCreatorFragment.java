package com.example.bren.voterinformation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bren.voterinformation.Login.PREFERENCE;

public class PollCreatorFragment extends Fragment {

    List<VoterPollModel> pollArray;

    EditText question;
    EditText textA;
    EditText textB;
    EditText textC;
    EditText textD;
    EditText textTarget;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FrameLayout view = (FrameLayout) inflater.inflate(R.layout.fragment_poll_creator, container, false);


        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(PREFERENCE, MODE_PRIVATE);

        final String username = sharedPreferences.getString(Login.Name, "missing");

        final PollModel pollModel = new PollModel();

        question = view.findViewById(R.id.textQuestion);
        textA = view.findViewById(R.id.textA);
        textB = view.findViewById(R.id.textB);
        textC = view.findViewById(R.id.textC);
        textD = view.findViewById(R.id.textD);
        textTarget = view.findViewById(R.id.textTarget);
        final DatabaseHelper helper = new DatabaseHelper(this.getActivity());

        Button createPoll = view.findViewById(R.id.buttonCreatePoll);
        createPoll.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick (View v) {

                pollModel.setTopic(question.getText().toString());
                pollModel.setA(textA.getText().toString());
                pollModel.setB(textB.getText().toString());
                pollModel.setC(textC.getText().toString());
                pollModel.setD(textD.getText().toString());
                pollModel.setTarget(textTarget.getText().toString());
                pollModel.setCreatorName(username);
                pollModel.setPollName(question.getText().toString());

                System.out.println(pollModel.toString());


                Boolean isAdded = helper.insertPoll(pollModel);
                if (isAdded) {
                    Toast.makeText(getActivity(), "Poll created", Toast.LENGTH_LONG).show();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    MainViewController fragmentHome = new MainViewController();

                    fragmentTransaction.replace(R.id.fragment, fragmentHome);
                    fragmentTransaction.commit();
                }
                else{
                    Toast.makeText(getActivity(), "Poll creation failed", Toast.LENGTH_LONG).show();
                }
            }
            });


        return view;
    }

}

