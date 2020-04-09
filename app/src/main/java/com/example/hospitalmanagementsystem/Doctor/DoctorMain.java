package com.example.hospitalmanagementsystem.Doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.hospitalmanagementsystem.Database.HMSDatabase;
import com.example.hospitalmanagementsystem.ModelClasses.Doctor;
import com.example.hospitalmanagementsystem.R;
import com.example.hospitalmanagementsystem.User.UserLogin;
import com.example.hospitalmanagementsystem.User.UserMain;
import com.example.hospitalmanagementsystem.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class DoctorMain extends AppCompatActivity {

    private CardView appointmentsCard;
    private TextView logout;
    private HMSDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        appointmentsCard = findViewById(R.id.appointmentsCard);
        logout = findViewById(R.id.logout);

        mDatabase  = new HMSDatabase(this);

        List<Doctor> list;
        list = mDatabase.fetchAllDoctors();

        for (Doctor d : list)
        Log.e("DOCTORS",d.getPmcid()+"");

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
