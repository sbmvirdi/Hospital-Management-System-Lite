package com.example.hospitalmanagementsystem.User;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospitalmanagementsystem.Database.HMSDatabase;
import com.example.hospitalmanagementsystem.ModelClasses.Appointment;
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
    private Button book;
    private List<Timing> timings;
    private int uid;
    private int selectedtiming=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        name = findViewById(R.id.name);
        selectdoctor = findViewById(R.id.selectdoctor);
        selecttiming = findViewById(R.id.selecttiming);
        book = findViewById(R.id.book);

        mDatabase  = new HMSDatabase(this);

        SharedPreferences preferences  = getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String nameString = preferences.getString("name","null");
        uid = Integer.parseInt(preferences.getString("uid","0"));
        name.setText(nameString);
        timings = new ArrayList<>();

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
                    timings.clear();
                    setTimings(position,false);
                }
                else{
                    timings.clear();
                    setTimings(position,true);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        book.setOnClickListener(v -> {
            if (selectdoctor.getSelectedItemPosition() == 0 || selecttiming.getSelectedItemPosition() == 0){
                Toast.makeText(this, "Select Doctor/Timing", Toast.LENGTH_SHORT).show();
            }else{
                int doctorindex = selectdoctor.getSelectedItemPosition();
                String dname = DoctorNames.get(doctorindex);
                int timingindex = selecttiming.getSelectedItemPosition(); // 1,2,3
                int pmcid = Dlist.get(doctorindex-1).getPmcid();
                int tid = timings.get(0).getTid();
                Log.e("PMC ID::",pmcid +"");
                Log.e("TID::",tid+"");
                Log.e("DOCTOR NAME::",dname);


                Appointment appointment  = new Appointment();
                appointment.setDept(Dlist.get(doctorindex-1).getDept());
                appointment.setDname(dname);
                appointment.setPmcid(pmcid);
                appointment.setUid(uid);
                appointment.setTiming(timingindex);

                boolean AppointmentDone = mDatabase.setAppointment(appointment);
                if (AppointmentDone){
                    // Update Timing table
                    Log.e("Appointment ::","SUCCESS");
                    boolean updateTimingTable = mDatabase.updateTiming(timingindex,tid);
                    if (updateTimingTable){
                        Log.e("Update Timing ::","SUCCESS");
                        Toast.makeText(this, "Appointment Done!", Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(BookAppointment.this,UserMain.class);
//                        startActivity(i);
//                        finish();
                    }else{
                        Log.e("Update Timing ::","FAILED");
                    }

                }else{
                    Toast.makeText(this, "Failed to set Appointment!", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    public void setTimings(int pmcid,boolean nullval){

        List<String>  DoctorTimings = new ArrayList<>();
        timings = mDatabase.getDoctorTiming(pmcid);
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
