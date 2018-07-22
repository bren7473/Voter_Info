package com.example.bren.voterinformation;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class CongressFragment extends Fragment {


    public CongressFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_congress, container, false);
        ImageButton senateButton = view.findViewById(R.id.senateButton);
        senateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MySenateFragment fragmentHome = new MySenateFragment();

                fragmentTransaction.replace(R.id.fragment, fragmentHome);
                fragmentTransaction.commit();

            }
        });
        ImageButton houseButton = view.findViewById(R.id.houseButton);
        houseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MyHouseFragment fragmentHome = new MyHouseFragment();

                fragmentTransaction.replace(R.id.fragment, fragmentHome);
                fragmentTransaction.commit();
            }
        });
        return view;
    }
}
