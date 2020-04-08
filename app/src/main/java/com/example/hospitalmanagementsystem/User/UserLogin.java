package com.example.hospitalmanagementsystem.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospitalmanagementsystem.Database.HMSDatabase;
import com.example.hospitalmanagementsystem.Doctor.DoctorLogin;
import com.example.hospitalmanagementsystem.Doctor.DoctorMain;
import com.example.hospitalmanagementsystem.R;
import com.example.hospitalmanagementsystem.Utils.Constants;
import com.google.android.material.navigation.NavigationView;

public class UserLogin extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button button_login;
    private TextView gotodoctorlogin;
    private HMSDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        button_login = findViewById(R.id.button_login);
        gotodoctorlogin = findViewById(R.id.gotodoctorlogin);

        // INITIALIZING DB OBJECT
        mDatabase = new HMSDatabase(this);

        // CHECKING LOGIN STATUS
        SharedPreferences preferences  = getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String email = preferences.getString("email","null");
        String login = preferences.getString("login","0");
        if (!email.equals("null") ){
            // LOGGED IN
            Intent i;
            if (login.equals("0")){
                i = new Intent(UserLogin.this,UserMain.class);
                Log.e("AUTH::","USER");
            }else{
                i = new Intent(UserLogin.this, DoctorMain.class);
                Log.e("AUTH::","DOCTOR");
            }

            startActivity(i);

        }else{
            Log.e("AUTH::","NOT LOGGED IN");
        }

        button_login.setOnClickListener(v->{
            if (!TextUtils.isEmpty(username.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())){
                // login user

              long loggedin =   mDatabase.loginuser(username.getText().toString(),password.getText().toString(),this);

                if (loggedin == 1){
                    Intent i = new Intent(UserLogin.this,UserMain.class);
                    startActivity(i);
                    finish();
                }else if (loggedin == -2){
                    // USER ENTERED WRONG DETAILS
                    Toast.makeText(UserLogin.this, "Incorrect Email/Pass", Toast.LENGTH_SHORT).show();
                } else if (loggedin == -3){
                    // IF USER DOES NOT EXISTS
                    Toast.makeText(UserLogin.this, "User does not exist!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Fill All Details", Toast.LENGTH_SHORT).show();
            }
        });

        gotodoctorlogin.setOnClickListener(v->{
            Intent i = new Intent(UserLogin.this,DoctorLogin.class);
            startActivity(i);
            finish();
        });
    }
}
