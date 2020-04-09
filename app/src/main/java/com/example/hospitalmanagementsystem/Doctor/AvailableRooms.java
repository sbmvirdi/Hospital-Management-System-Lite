package com.example.hospitalmanagementsystem.Doctor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.hospitalmanagementsystem.Adapters.AppointmentAdapter;
import com.example.hospitalmanagementsystem.Adapters.RoomAdapter;
import com.example.hospitalmanagementsystem.Database.HMSDatabase;
import com.example.hospitalmanagementsystem.ModelClasses.Appointment;
import com.example.hospitalmanagementsystem.ModelClasses.Room;
import com.example.hospitalmanagementsystem.R;
import com.example.hospitalmanagementsystem.Utils.Constants;

import java.util.List;

public class AvailableRooms extends AppCompatActivity {
    private RecyclerView roomrec;
    private HMSDatabase mDatabase;
    private TextView noroom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_rooms);

        roomrec = findViewById(R.id.roomrec);
        roomrec.setLayoutManager(new GridLayoutManager(this,4));
        mDatabase = new HMSDatabase(this);
        noroom = findViewById(R.id.norooms);

        SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        int uid = Integer.parseInt(preferences.getString("pmcid","0"));

        Log.e("PMC ID:",uid+"");
        List<Room> list = mDatabase.getAllRooms();

        for (Room r:list)
        Log.e("Room No::",r.getRoomno()+"");

        if (list.isEmpty()){
            noroom.setVisibility(View.VISIBLE);
        }
        RoomAdapter roomAdapter = new RoomAdapter(this,list);
        roomrec.setAdapter(roomAdapter);
    }
}
