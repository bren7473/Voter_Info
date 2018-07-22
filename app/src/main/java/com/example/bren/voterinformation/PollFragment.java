package com.example.bren.voterinformation;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bren.voterinformation.Login.PREFERENCE;

public class PollFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_poll_home, container, false);
        Button make = view.findViewById(R.id.buttonMake);
        make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PollCreatorFragment fragmentHome = new PollCreatorFragment();

                fragmentTransaction.replace(R.id.fragment, fragmentHome);
                fragmentTransaction.commit();

            }
        });
        Button houseButton = view.findViewById(R.id.buttonTake);
        houseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PollListFragment fragmentHome = new PollListFragment();

                fragmentTransaction.replace(R.id.fragment, fragmentHome);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}