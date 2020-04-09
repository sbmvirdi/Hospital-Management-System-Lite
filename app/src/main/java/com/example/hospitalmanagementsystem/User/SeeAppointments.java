package com.example.hospitalmanagementsystem.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.hospitalmanagementsystem.Adapters.AppointmentAdapter;
import com.example.hospitalmanagementsystem.Database.HMSDatabase;
import com.example.hospitalmanagementsystem.ModelClasses.Appointment;
import com.example.hospitalmanagementsystem.R;
import com.example.hospitalmanagementsystem.Utils.Constants;

import org.w3c.dom.Text;

import java.util.List;

public class SeeAppointments extends AppCompatActivity {

    private RecyclerView appointmentrec;
    private HMSDatabase mDatabase;
    private TextView noappointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_appointments);

        appointmentrec = findViewById(R.id.appointmentrec);
        appointmentrec.setLayoutManager(new LinearLayoutManager(this));
        mDatabase = new HMSDatabase(this);
        noappointments = findViewById(R.id.noappointments);

        SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        int uid = Integer.parseInt(preferences.getString("uid","0"));

        List<Appointment> list = mDatabase.getUserAppointments(uid);

        if (list.isEmpty()){
            noappointments.setVisibility(View.VISIBLE);
        }
        AppointmentAdapter appointmentAdapter = new AppointmentAdapter(this,list);
        appointmentrec.setAdapter(appointmentAdapter);


    }
}
