package com.example.hospitalmanagementsystem.Doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hospitalmanagementsystem.R;
import com.example.hospitalmanagementsystem.User.UserLogin;
import com.example.hospitalmanagementsystem.User.UserMain;
import com.example.hospitalmanagementsystem.Utils.Constants;

public class DoctorMain extends AppCompatActivity {

    private CardView appointmentsCard;
    private TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        appointmentsCard = findViewById(R.id.appointmentsCard);
        logout = findViewById(R.id.logout);

        logout.setOnClickListener(v->{
            // logout doctor from shared preferences
            // logout user from shared preferences
            SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email","null");
            editor.putString("password","null");
            editor.apply();
            // move to user login
            Intent i = new Intent(DoctorMain.this, UserLogin.class);
            startActivity(i);
            finish();
        });

        appointmentsCard.setOnClickListener(v->{
            Intent i = new Intent(DoctorMain.this,SeeAppointment.class);
            startActivity(i);
        });
    }
}
