package com.example.bren.voterinformation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;

public class RegisterVoter extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user_view);

    }
    EditText txtUsername, txtPassword, txtPassword2, txtEmail, txtBirthday, txtAddress;

    public boolean validate(){
        boolean passwordCheck = true;

        String password = txtPassword.getText().toString();

        if(!password.equals(txtPassword2.getText().toString())){
            Toast.makeText(this,"Passwords do not match",Toast.LENGTH_LONG).show();
            passwordCheck = false;
            System.out.println("false");
        }
        return passwordCheck;
    }

    public void touchVoterRegister(View view) {
        VoterModel user = new VoterModel();
        DatabaseHelper dbUser = new DatabaseHelper(this);

        txtUsername = findViewById(R.id.voterUsername);
        txtPassword = findViewById(R.id.voterPassword);
        txtPassword2 = findViewById(R.id.voterPassword2);
        txtEmail = findViewById(R.id.voterEmail);
        txtBirthday = findViewById(R.id.voterBirthday);
        txtAddress = findViewById((R.id.voterAddress));
        user.setUsername(txtUsername.getText().toString());
        user.setPassword(txtPassword.getText().toString());
        user.setEmail(txtEmail.getText().toString());
        user.setBirthday(txtBirthday.getText().toString());
        user.setAddress(txtAddress.getText().toString());
        Boolean isAdded = dbUser.insertVoter(user);
        if (validate()){
            if(isAdded) {
                Toast.makeText(this, "Registered successfully", LENGTH_LONG).show();
                Log.d("Register", "Registration is successful");
                //An Intent is a messaging object you can use to request an action from another app component.
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);

                finish();
        }

        } else {
            Toast.makeText(this, "Error, check entries.", LENGTH_LONG).show();
        }
    }

}
