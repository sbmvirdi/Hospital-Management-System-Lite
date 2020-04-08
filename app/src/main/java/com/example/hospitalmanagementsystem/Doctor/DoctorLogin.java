package com.example.hospitalmanagementsystem.Doctor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospitalmanagementsystem.Database.HMSDatabase;
import com.example.hospitalmanagementsystem.R;
import com.example.hospitalmanagementsystem.User.UserLogin;
import com.example.hospitalmanagementsystem.User.UserMain;

public class DoctorLogin extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button button_login;
    private TextView gotouserlogin;
    private HMSDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        button_login = findViewById(R.id.button_login);
        gotouserlogin = findViewById(R.id.gotouserlogin);
        mDatabase = new HMSDatabase(this);

        button_login.setOnClickListener(v->{
            if (!TextUtils.isEmpty(username.getText().toString()) && !TextUtils.isEmpty(password.getText().toString())){

                long loggedin =   mDatabase.logindoctor(username.getText().toString(),password.getText().toString(),this);

                if (loggedin == 1){
                    Intent i = new Intent(DoctorLogin.this,DoctorMain.class);
                    startActivity(i);
                    finish();
                }else if (loggedin == -2){
                    // USER ENTERED WRONG DETAILS
                    Toast.makeText(DoctorLogin.this, "Incorrect Email/Pass", Toast.LENGTH_SHORT).show();
                } else if (loggedin == -3){
                    // IF USER DOES NOT EXISTS
                    Toast.makeText(DoctorLogin.this, "Doctor does not exist!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(this, "Fill All Details", Toast.LENGTH_SHORT).show();
            }
        });

        gotouserlogin.setOnClickListener(v->{
            Intent i = new Intent(DoctorLogin.this,UserLogin.class);
            startActivity(i);
            finish();
        });
    }
}
