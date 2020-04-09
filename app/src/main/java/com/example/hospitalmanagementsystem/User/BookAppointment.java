package com.example.hospitalmanagementsystem.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hospitalmanagementsystem.Database.HMSDatabase;
import com.example.hospitalmanagementsystem.ModelClasses.Doctor;
import com.example.hospitalmanagementsystem.ModelClasses.Timing;
import com.example.hospitalmanagementsystem.R;
import com.example.hospitalmanagementsystem.Utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class BookAppointment extends AppCompatActivity {
    private TextView name;
    private Spinner selectdoctor,selecttiming;
    private HMSDatabase mDatabase;
    private List<String> DoctorNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        name = findViewById(R.id.name);
        selectdoctor = findViewById(R.id.selectdoctor);
        selecttiming = findViewById(R.id.selecttiming);

        mDatabase  = new HMSDatabase(this);

        SharedPreferences preferences  = getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String nameString = preferences.getString("name","null");
        name.setText(nameString);

        List<Doctor> Dlist = mDatabase.fetchAllDoctors();
        DoctorNames = new ArrayList<>();

        DoctorNames.add("Select Doctor");
        for (Doctor d: Dlist) {
            DoctorNames.add(d.getDname());
        }

        ArrayAdapter<String> doctors  = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,DoctorNames);
        selectdoctor.setAdapter(doctors);

        selectdoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position !=0) {
                    setTimings(position,false);
                }
                else{
                    setTimings(position,true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void setTimings(int pmcid,boolean nullval){

        List<String>  DoctorTimings = new ArrayList<>();
        List<Timing> timings = mDatabase.getDoctorTiming(pmcid);
        DoctorTimings.add("Select Timings");
        if (!nullval) {
            for (Timing t : timings) {
                if (t.getT1() == 1) {
                    DoctorTimings.add("9 AM - 10 AM");
                }
                if (t.getT2() == 1) {
                    DoctorTimings.add("10 AM - 11 AM");
                }
                if (t.getT3() == 1) {
                    DoctorTimings.add("11 AM - 12 PM");
                }
            }

        }
        ArrayAdapter<String> timing = new ArrayAdapter<>(BookAppointment.this,android.R.layout.simple_list_item_1,DoctorTimings);
        selecttiming.setAdapter(timing);
    }


}
