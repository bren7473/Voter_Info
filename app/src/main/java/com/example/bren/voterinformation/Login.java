package com.example.bren.voterinformation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    public static final String PREFERENCE = "com.example.bren.voterinformation";
    public static final String Name = "nameKey";
    public static final String Pos = "position";
    public static final String Site = "website";
    public static final String Email = "email";
    public static final String Phone = "phone";

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
    }

    public void touchGoToVoterRegister(View view){
        Intent intent = new Intent(this, RegisterVoter.class);
        startActivity(intent);

    }
    public void touchLogin(View view) {
        VoterModel user = new VoterModel();
        DatabaseHelper dbUser = new DatabaseHelper(this);
        EditText txtUsername = findViewById(R.id.loginPassword);
        EditText txtPassword = findViewById(R.id.loginUsername);
        user.setUsername(txtUsername.getText().toString());
        user.setPassword(txtPassword.getText().toString());
        Boolean isAdded = dbUser.getLoginInfo(user);
        if (isAdded) {
            Toast.makeText(this, "User is logged sucessfully", Toast.LENGTH_LONG).show();
            Log.d("Login", "Login is successful");
            dbUser.getVoter(txtUsername.getText().toString());

            //An Intent is a messaging object you can use to request an action from another app component.
            Intent intent = new Intent(this, MainActivityBottom.class);
            sharedPreferences = Login.this.getSharedPreferences(PREFERENCE, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            String username = txtUsername.getText().toString();
            editor.putString(Name,username);
            editor.commit();
            startActivity(intent);


            finish();
        }
        else
        {
            Toast.makeText(this, "Wrong username or password, please try again", Toast.LENGTH_LONG).show();

        }
    }
}