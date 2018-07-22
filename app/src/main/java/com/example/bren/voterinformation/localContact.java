package com.example.bren.voterinformation;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;
import static com.example.bren.voterinformation.Login.Email;
import static com.example.bren.voterinformation.Login.PREFERENCE;
import static com.example.bren.voterinformation.Login.Phone;
public class localContact extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        FrameLayout view = (FrameLayout) inflater.inflate(R.layout.fragment_local_contact, container, false);
        DatabaseHelper helper = new DatabaseHelper(this.getActivity());

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(PREFERENCE, MODE_PRIVATE);

        String email = sharedPreferences.getString(Email, "missing");
        String number = sharedPreferences.getString(Phone, "missing");


        TextView textEmail = view.findViewById(R.id.textEmail);
        TextView textNumber = view.findViewById(R.id.textNumber);


        textEmail.setText(email);
        textNumber.setText(number);


        return view;
    }
}
