package com.example.hospitalmanagementsystem.Database;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.hospitalmanagementsystem.Utils.Constants;

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


    // TABLE ROOMS

    private static final String ROOMSTABLENAME = "ROOMS";
    private static final String ROOM_COLUMN_1 = "RID";
    private static final String ROOM_COLUMN_2 = "ROOMNO";
    private static final String ROOM_COLUMN_3 = "AVAIL";
    private static final String ROOM_COLUMN_4 = "PNAME";

    //CREATE TABLE COMMANDS
    private static final String CREATE_TABLE_USERS = "CREATE TABLE "+USERTABLENAME+"("+USER_COLUMN_1+" INT PRIMARY KEY,"+USER_COLUMN_2+" TEXT,"+USER_COLUMN_3+" TEXT,"+USER_COLUMN_4+" TEXT)";
    private static final String CREATE_TABLE_DOCTORS = "CREATE TABLE "+DOCTORTABLENAME+"("+DOCTOR_COLUMN_1+" INT PRIMARY KEY,"+DOCTOR_COLUMN_2+" TEXT,"+DOCTOR_COLUMN_3+" TEXT,"+DOCTOR_COLUMN_4+" TEXT,"+ DOCTOR_COLUMN_5+" TEXT)";
    private static final String CREATE_TABLE_APPS = "CREATE TABLE "+APPOINTMENTTABLENAME+"("+APP_COLUMN_1+" INT PRIMARY KEY,"+APP_COLUMN_2+" TEXT,"+APP_COLUMN_3+" TEXT,"+APP_COLUMN_4+" TEXT,"+ APP_COLUMN_5+" TEXT)";
    private static final String CREATE_TABLE_ROOMS = "CREATE TABLE "+ROOMSTABLENAME+"("+ROOM_COLUMN_1+" INT PRIMARY KEY,"+ROOM_COLUMN_2+" INT,"+ROOM_COLUMN_3+" INT,"+ROOM_COLUMN_4+" TEXT)";



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
        Log.e("TABLE CREATION::","SUCCESS");

        ContentValues doctor = new ContentValues();
        doctor.put(DOCTOR_COLUMN_2,"Varun Gupta");
        doctor.put(DOCTOR_COLUMN_3,"Gastrointestinology");
        doctor.put(DOCTOR_COLUMN_4,"doctor@gmail.com");
        doctor.put(DOCTOR_COLUMN_5,"123456789a");

        db.insert(DOCTORTABLENAME,null, doctor);
        Log.e("DOCTOR INSERTION::","SUCCESS");

        ContentValues user = new ContentValues();
        user.put(USER_COLUMN_2,"Shubam Virdi");
        user.put(USER_COLUMN_3,"user@gmail.com");
        user.put(USER_COLUMN_4,"123456789a");

        db.insert(USERTABLENAME,null, user);
        Log.e("USER INSERTION::","SUCCESS");

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

                if (Email.equals(email) && Pass.equals(pass)) {
                    SharedPreferences preferences = mContext.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email", email);
                    editor.putString("password", pass);
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

                if (Email.equals(email) && Pass.equals(pass)) {
                    SharedPreferences preferences = mContext.getSharedPreferences(Constants.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email", email);
                    editor.putString("password", pass);
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


}
