package com.example.hospitalmanagementsystem.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hospitalmanagementsystem.Doctor.DoctorMain;
import com.example.hospitalmanagementsystem.Doctor.SeeAppointment;
import com.example.hospitalmanagementsystem.R;
import com.example.hospitalmanagementsystem.Utils.Constants;

public class UserMain extends AppCompatActivity {

    private CardView appointmentsCard,seeroomscard;
    private TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        seeroomscard = findViewById(R.id.seeroomscard);
        appointmentsCard  = findViewById(R.id.appointmentsCard);
        logout = findViewById(R.id.logout);

        appointmentsCard.setOnClickListener(v->{
            Intent i = new Intent(UserMain.this, BookAppointment.class);
            startActivity(i);
        });

        logout.setOnClickListener(v->{

            // logout user from shared preferences
            SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("email","null");
            editor.putString("password","null");
            editor.apply();
            // move to user login
            Intent i = new Intent(UserMain.this, UserLogin.class);
            startActivity(i);
            finish();
        });

        seeroomscard.setOnClickListener(v->{
            Intent i = new Intent(UserMain.this, AvailableRooms.class);
            startActivity(i);
        });
    }
}
