package com.example.hospitalmanagementsystem.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.hospitalmanagementsystem.ModelClasses.Appointment;
import com.example.hospitalmanagementsystem.ModelClasses.Doctor;
import com.example.hospitalmanagementsystem.ModelClasses.Room;
import com.example.hospitalmanagementsystem.ModelClasses.Timing;
import com.example.hospitalmanagementsystem.Utils.Constants;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class HMSDatabase extends SQLiteOpenHelper {

    // DATABASE NAME
    private static final String DATABASE_NAME = "HMS";
    private static final int DATABASE_VERSION = 1;
    //TABLE USERS
    private static final String USERTABLENAME = "USERS";
    private static final String USER_COLUMN_1 = "UID";
    private static final String USER_COLUMN_2 = "PNAME";
    private static final String USER_COLUMN_3 = "EMAIL";
    private static final String USER_COLUMN_4 = "PASSWORD";


    //TABLE DOCTORS

    private static final String DOCTORTABLENAME = "DOCTORS";
    private static final String DOCTOR_COLUMN_1 = "PMCID";
    private static final String DOCTOR_COLUMN_2 = "DNAME";
    private static final String DOCTOR_COLUMN_3 = "DEPT";
    private static final String DOCTOR_COLUMN_4 = "EMAIL";
    private static final String DOCTOR_COLUMN_5 = "PASSWORD";


    // TABLE APPOINTMENTS

    private static final String APPOINTMENTTABLENAME = "APPS";
    private static final String APP_COLUMN_1 = "APID";
    private static final String APP_COLUMN_2 = "PNAME";
    private static final String APP_COLUMN_3 = "DNAME";
    private static final String APP_COLUMN_4 = "DEPT";
    private static final String APP_COLUMN_5 = "PMCID";
    private static final String APP_COLUMN_6 = "UID";
    private static final String APP_COLUMN_7 = "TIMING";


    // TABLE ROOMS

    private static final String ROOMSTABLENAME = "ROOMS";
    private static final String ROOM_COLUMN_1 = "RID";
    private static final String ROOM_COLUMN_2 = "ROOMNO";
    private static final String ROOM_COLUMN_3 = "AVAIL";
    private static final String ROOM_COLUMN_4 = "PNAME";


    // TABLE TIMINGS

    private static final String TIMINGSTABLENAME = "TIMINGS";
    private static final String TIMING_COLUMN_1 = "TID";
    private static final String TIMING_COLUMN_2 = "PMCID";
    private static final String TIMING_COLUMN_3 = "T1";
    private static final String TIMING_COLUMN_4 = "T2";
    private static final String TIMING_COLUMN_5 = "T3";

    //CREATE TABLE COMMANDS
    private static final String CREATE_TABLE_USERS = "CREATE TABLE "+USERTABLENAME+"("+USER_COLUMN_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+USER_COLUMN_2+" TEXT,"+USER_COLUMN_3+" TEXT,"+USER_COLUMN_4+" TEXT)";
    private static final String CREATE_TABLE_DOCTORS = "CREATE TABLE "+DOCTORTABLENAME+"("+DOCTOR_COLUMN_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+DOCTOR_COLUMN_2+" TEXT,"+DOCTOR_COLUMN_3+" TEXT,"+DOCTOR_COLUMN_4+" TEXT,"+ DOCTOR_COLUMN_5+" TEXT)";
    private static final String CREATE_TABLE_APPS = "CREATE TABLE "+APPOINTMENTTABLENAME+"("+APP_COLUMN_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+APP_COLUMN_2+" TEXT,"+APP_COLUMN_3+" TEXT,"+APP_COLUMN_4+" TEXT,"+ APP_COLUMN_5+" INTEGER,"+APP_COLUMN_6+" INTEGER,"+APP_COLUMN_7+" INTEGER)";
    private static final String CREATE_TABLE_ROOMS = "CREATE TABLE "+ROOMSTABLENAME+"("+ROOM_COLUMN_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+ROOM_COLUMN_2+" INTEGER,"+ROOM_COLUMN_3+" INTEGER,"+ROOM_COLUMN_4+" TEXT)";
    private static final String CREATE_TABLE_TIMINGS = "CREATE TABLE "+TIMINGSTABLENAME+"("+TIMING_COLUMN_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+TIMING_COLUMN_2+" INTEGER,"+TIMING_COLUMN_3+" INTEGER,"+TIMING_COLUMN_4+" INTEGER,"+ TIMING_COLUMN_5+" INTEGER)";



    // CONSTRUCTOR
    public HMSDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_DOCTORS);
        db.execSQL(CREATE_TABLE_APPS);
        db.execSQL(CREATE_TABLE_ROOMS);
        db.execSQL(CREATE_TABLE_TIMINGS);
        Log.e("TABLE CREATION::","SUCCESS");

        ContentValues doctor = new ContentValues();
        doctor.put(DOCTOR_COLUMN_2,"Dr.Varun Gupta");
        doctor.put(DOCTOR_COLUMN_3,"Gastrointestinology");
        doctor.put(DOCTOR_COLUMN_4,"doctor@gmail.com");
        doctor.put(DOCTOR_COLUMN_5,"123456789a");

        ContentValues doctor1 = new ContentValues();
        doctor1.put(DOCTOR_COLUMN_2,"Dr.Shamit Chopra");
        doctor1.put(DOCTOR_COLUMN_3,"Head and Neck Surgery (ENT)");
        doctor1.put(DOCTOR_COLUMN_4,"doctor1@gmail.com");
        doctor1.put(DOCTOR_COLUMN_5,"123456789a");

        db.insert(DOCTORTABLENAME,null, doctor);
        db.insert(DOCTORTABLENAME,null, doctor1);
        Log.e("DOCTOR INSERTION::","SUCCESS");

        ContentValues user = new ContentValues();
        user.put(USER_COLUMN_2,"Shubam Virdi");
        user.put(USER_COLUMN_3,"user@gmail.com");
        user.put(USER_COLUMN_4,"123456789a");

        db.insert(USERTABLENAME,null, user);
        Log.e("USER INSERTION::","SUCCESS");

        ContentValues timingsvarun = new ContentValues();
        timingsvarun.put(TIMING_COLUMN_2,1);
        timingsvarun.put(TIMING_COLUMN_3,1);
        timingsvarun.put(TIMING_COLUMN_4,1);
        timingsvarun.put(TIMING_COLUMN_5,1);


        db.insert(TIMINGSTABLENAME,null, timingsvarun);

        ContentValues timingsshamit = new ContentValues();
        timingsshamit.put(TIMING_COLUMN_2,2);
        timingsshamit.put(TIMING_COLUMN_3,1);
        timingsshamit.put(TIMING_COLUMN_4,1);
        timingsshamit.put(TIMING_COLUMN_5,1);

        db.insert(TIMINGSTABLENAME,null, timingsshamit);

        Log.e("TIMINGS INSERTION::","SUCCESS");


        List<Room> roomList = new ArrayList<>();
        roomList.add(new Room(401,0));
        roomList.add(new Room(402,1));
        roomList.add(new Room(403,1));
        roomList.add(new Room(404,1));
        roomList.add(new Room(405,1));
        roomList.add(new Room(406,1));
        roomList.add(new Room(407,0));
        roomList.add(new Room(408,0));
        roomList.add(new Room(409,1));
        roomList.add(new Room(410,0));
        roomList.add(new Room(411,1));
        roomList.add(new Room(412,1));
        roomList.add(new Room(413,0));
        roomList.add(new Room(414,1));
        roomList.add(new Room(415,0));
        roomList.add(new Room(416,1));
        roomList.add(new Room(417,1));
        roomList.add(new Room(418,1));
        roomList.add(new Room(419,1));
        roomList.add(new Room(420,0));

        for (Room r: roomList){
            ContentValues rooms = new ContentValues();
            rooms.put(ROOM_COLUMN_2,r.getRoomno());
            rooms.put(ROOM_COLUMN_3,r.getAvail());
            db.insert(ROOMSTABLENAME,null, rooms);
        }

        Log.e("Room Insert::","SUCCESS");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ USERTABLENAME);
        db.execSQL("DROP TABLE IF EXISTS "+ DOCTORTABLENAME);
        db.execSQL("DROP TABLE IF EXISTS "+ APPOINTMENTTABLENAME);
        db.execSQL("DROP TABLE IF EXISTS "+ ROOMSTABLENAME);
        Log.e("DOP TABLE EXECUTION::","SUCCESS");
    }



    // FUNCTION TO LOGIN AND CREATE A SESSION IN THE APP
    public long loginuser(String email,String pass,Context mContext) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + USERTABLENAME + " where EMAIL = ? and PASSWORD = ?", new String[]{email, pass});


        // CHECK IF USER ALREADY EXISTS
        boolean isuser = isUser(email);
        if (isuser) {
            //LOGIN AND SAVE DETAILS IN SHARED PREFERENCES

            if (c.getCount() > 0 && c.moveToFirst()) {

                String Email = c.getString(2);
                String Pass = c.getString(3);
                String name = c.getString(1);
                int uid = c.getInt(0);

                if (Email.equals(email) && Pass.equals(pass)) {
                    SharedPreferences preferences = mContext.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email", email);
                    editor.putString("password", pass);
                    editor.putString("name",name);
                    editor.putString("uid",uid+"");
                    editor.putString("login","0");
                    editor.apply();
                    c.close();
                    return 1;
                } else {

                    // INCORRECT EMAIL/PASSWORD
                    return -2;
                }

            } else {
                // INCORRECT EMAIL/PASSWORD
                c.close();
                return -2;
            }

        }else{
            //USER DOES NOT EXIST
            return -3;
        }


    }

    //FUNCTION TO CHECK WHETHER USER IS ALREADY IN THE DATABASE IN USERS TABLE
    public boolean isUser(String email){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c  = db.rawQuery("SELECT * FROM "+USERTABLENAME+" where EMAIL = ?",new String[]{email});

        if (c.getCount()>0 && c.moveToFirst()){
            c.close();
            // USER EXISTS
            return true;
        }else{
            c.close();
            //USER DOES NOT EXIST
            return false;
        }
    }

    //FUNCTION TO CHECK WHETHER USER IS ALREADY IN THE DATABASE IN DOCTORS TABLE
    public boolean isDoctor(String email){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c  = db.rawQuery("SELECT * FROM "+DOCTORTABLENAME+" where EMAIL = ?",new String[]{email});

        if (c.getCount()>0 && c.moveToFirst()){
            c.close();
            // DOCTOR EXISTS
            return true;
        }else{
            c.close();
            //DOCTOR DOES NOT EXIST
            return false;
        }
    }

    // FUNCTION TO LOGIN AND CREATE A SESSION IN THE APP
    public long logindoctor(String email,String pass,Context mContext) {

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + DOCTORTABLENAME + " where EMAIL = ? and PASSWORD = ?", new String[]{email, pass});


        // CHECK IF DOCTOR ALREADY EXISTS
        boolean isdoctor = isDoctor(email);
        if (isdoctor) {
            //LOGIN AND SAVE DETAILS IN SHARED PREFERENCES

            if (c.getCount() > 0 && c.moveToFirst()) {

                String Email = c.getString(3);
                String Pass = c.getString(4);
                String name  = c.getString(1);
                int pmcid = c.getInt(0);

                if (Email.equals(email) && Pass.equals(pass)) {
                    SharedPreferences preferences = mContext.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email", email);
                    editor.putString("password", pass);
                    editor.putString("name",name);
                    editor.putString("pmcid",pmcid+"");
                    editor.putString("login","1");
                    editor.apply();
                    c.close();
                    return 1;
                } else {

                    // INCORRECT EMAIL/PASSWORD
                    return -2;
                }

            } else {
                // INCORRECT EMAIL/PASSWORD
                c.close();
                return -2;
            }

        }else{
            //DOCTOR DOES NOT EXIST
            return -3;
        }


    }


    public List<Doctor> fetchAllDoctors(){

        List<Doctor> mList = new ArrayList<>();
        String mSelectQuery = "SELECT * FROM "+DOCTORTABLENAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor c = db.rawQuery(mSelectQuery,null);
        if (c.moveToFirst()){

            do{
                Doctor model = new Doctor();
                model.setPmcid(c.getInt(c.getColumnIndex(DOCTOR_COLUMN_1)));
                model.setDname(c.getString(c.getColumnIndex(DOCTOR_COLUMN_2)));
                model.setDept(c.getString(c.getColumnIndex(DOCTOR_COLUMN_3)));
                mList.add(model);

            }while (c.moveToNext());
        }

        c.close();

        // RETURN THE LIST OF ALL DOCTORS
        return mList;
    }


    public List<Timing> getDoctorTiming(int pmcid){

        List<Timing> mList = new ArrayList<>();
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + TIMINGSTABLENAME + " where PMCID = ?", new String[]{String.valueOf(pmcid)});
       // Log.e("Timing Rows::",c.getCount()+"");
        if (c.moveToFirst()){
            Timing t =  new Timing();
            t.setTid(c.getInt(c.getColumnIndex(TIMING_COLUMN_1)));
            t.setPmcid(c.getInt(c.getColumnIndex(TIMING_COLUMN_2)));
            t.setT1(c.getInt(c.getColumnIndex(TIMING_COLUMN_3)));
            t.setT2(c.getInt(c.getColumnIndex(TIMING_COLUMN_4)));
            t.setT3(c.getInt(c.getColumnIndex(TIMING_COLUMN_5)));
            mList.add(t);
            Log.e("getDoctorTiming::","Called");
        }

        if (!mList.isEmpty()) {
            Log.e("T1::", mList.get(0).getT1() + "");
            Log.e("T2::", mList.get(0).getT2() + "");
            Log.e("T3::", mList.get(0).getT3() + "");

        }

        c.close();

        // RETURN THE LIST OF ALL DOCTORS
        return mList;

    }



    public boolean setAppointment(Appointment appointment){

        ContentValues AV = new ContentValues();

        SQLiteDatabase db = this.getWritableDatabase();

        AV.put(APP_COLUMN_2,appointment.getPname());
        AV.put(APP_COLUMN_3,appointment.getDname());
        AV.put(APP_COLUMN_4,appointment.getDept());
        AV.put(APP_COLUMN_5,appointment.getPmcid());
        AV.put(APP_COLUMN_6,appointment.getUid());
        AV.put(APP_COLUMN_7,appointment.getTiming());

        long insert = db.insert(APPOINTMENTTABLENAME,null,AV);
        if(insert!=-1){
            //INSERT SUCCESSFUL
            return true;
        }
        else {
            //INSERT FAILED
            return false;

        }
    }


    public boolean updateTiming(int timing, int tid){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues update = new ContentValues();
        if (timing == 1){
            update.put(TIMING_COLUMN_3,0);
        }else if (timing == 2){
            update.put(TIMING_COLUMN_4,0);
        }else if (timing == 3){
            update.put(TIMING_COLUMN_5,0);
        }

        long updated  = db.update(TIMINGSTABLENAME,update,TIMING_COLUMN_1+"="+tid,null);
        if(updated!=-1){
            //INSERT SUCCESSFUL
            return true;
        }
        else {
            //INSERT FAILED
            return false;

        }
    }


    public List<Appointment> getUserAppointments(int uid){
        List<Appointment> list = new ArrayList<>();

        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + APPOINTMENTTABLENAME + " where UID = ?", new String[]{String.valueOf(uid)});
        if (c.moveToFirst()){

            do{
            Appointment t =  new Appointment();
            t.setApid(c.getInt(c.getColumnIndex(APP_COLUMN_1)));
            t.setUid(c.getInt(c.getColumnIndex(APP_COLUMN_6)));
            t.setPmcid(c.getInt(c.getColumnIndex(APP_COLUMN_5)));
            t.setDname(c.getString(c.getColumnIndex(APP_COLUMN_3)));
            t.setPname(c.getString(c.getColumnIndex(APP_COLUMN_2)));
            t.setDept(c.getString(c.getColumnIndex(APP_COLUMN_4)));
            t.setTiming(c.getInt(c.getColumnIndex(APP_COLUMN_7)));
            list.add(t);

            }while (c.moveToNext());
          //  Log.e("getUserAppointments::","Called");
        }

        c.close();

        // RETURN THE LIST OF ALL DOCTORS
        return list;

    }

    public List<Appointment> getDoctorAppointments(int uid){
        List<Appointment> list = new ArrayList<>();

        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + APPOINTMENTTABLENAME + " where PMCID = ?", new String[]{String.valueOf(uid)});
        if (c.moveToFirst()){

            do{
                Appointment t =  new Appointment();
                t.setApid(c.getInt(c.getColumnIndex(APP_COLUMN_1)));
                t.setUid(c.getInt(c.getColumnIndex(APP_COLUMN_6)));
                t.setPmcid(c.getInt(c.getColumnIndex(APP_COLUMN_5)));
                t.setDname(c.getString(c.getColumnIndex(APP_COLUMN_3)));
                t.setPname(c.getString(c.getColumnIndex(APP_COLUMN_2)));
                t.setDept(c.getString(c.getColumnIndex(APP_COLUMN_4)));
                t.setTiming(c.getInt(c.getColumnIndex(APP_COLUMN_7)));
                list.add(t);

            }while (c.moveToNext());
            //  Log.e("getUserAppointments::","Called");
        }

        c.close();

        // RETURN THE LIST OF ALL DOCTORS
        return list;

    }

    public List<Room> getAllRooms(){

        List<Room> list = new ArrayList<>();

        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + ROOMSTABLENAME,null);
        if (c.moveToFirst()){

            do{
                Room t =  new Room();
                t.setAvail(c.getInt(c.getColumnIndex(ROOM_COLUMN_3)));
                t.setRoomno(c.getInt(c.getColumnIndex(ROOM_COLUMN_2)));
                list.add(t);

            }while (c.moveToNext());
            //  Log.e("getUserAppointments::","Called");
        }

        c.close();

        // RETURN THE LIST OF ALL DOCTORS
        return list;

    }


}
