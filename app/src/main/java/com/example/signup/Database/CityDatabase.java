package com.example.signup.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.signup.G;
import com.example.signup.ModelH.StractCity;
import com.example.signup.ModelH.StractState;

import java.util.ArrayList;

public class CityDatabase extends SQLiteOpenHelper {
    public static String DBNAME;
    public static String TABLE;
    public static String CITY;
    public static String STATE;
    public static String DBLOCATION = "/data/data/" + G.context.getPackageName() + "/databases"+"/" ;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private String where;
    public CityDatabase(Context context, String DBname){
        super(context,DBNAME,null,1);
        DBNAME = DBname + ".db";
        TABLE = "MST_Hospital" ;
        CITY = "District";
        STATE = "State";
        mContext = context;

    }


    @Override
    public void onCreate(SQLiteDatabase db) {


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
    public  void OpenDatabase(){

        String DBPath = mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase!= null && mDatabase.isOpen()){
            return;
        }

        mDatabase = SQLiteDatabase.openDatabase(DBPath,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public void CloseDatabase(){
        if(mDatabase!=null){
            mDatabase.close();
        }
        //Get record
    }
    public ArrayList<StractCity> GetCiity() {

        StractCity stractCity = null;
        ArrayList<StractCity> arrayListHospital = new ArrayList<StractCity>();
        // SELECT DISTINCT District FROM MST_Hospital
        OpenDatabase();

//        Cursor cursor = mDatabase.rawQuery( "select * from " + TABLE , null);
        Cursor cursor = mDatabase.query(true, TABLE,null, null, null, CITY, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            stractCity = new StractCity(cursor.getString(9));
            arrayListHospital.add(stractCity);
            cursor.moveToNext();
        }
        cursor.close();
        CloseDatabase();
        ArrayList<StractCity> newList = removeDuplicates(arrayListHospital);

        return newList;
    }

    public ArrayList<StractCity> GetCityFromState(String name) {
        String a = name;
        String[] columns = {CITY};
        StractCity stractCity = null;
        ArrayList<StractCity> arrayListHospital = new ArrayList<StractCity>();
        // SELECT DISTINCT District FROM MST_Hospital
        OpenDatabase();
// SELECT * FROM MST_Hospital WHERE State = 'Gujarat'
//        Cursor cursor = mDatabase.rawQuery( "select * from " + TABLE , null);
           //Cursor cursor = mDatabase.rawQuery( "SELECT * FROM MST_Hospital WHERE State = " + "Goa" , null);
      //  Cursor cursor = mDatabase.query(true, TABLE,null, null, null, CITY, null, null, null);
       Cursor cursor = mDatabase.rawQuery("select distinct * from " + TABLE + " where State =? group by " + CITY , new String[]{name});
       // Cursor cursor = mDatabase.query(true, TABLE,columns, STATE, new String[]{name}, CITY, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            stractCity = new StractCity(cursor.getString(9));
            arrayListHospital.add(stractCity);
            cursor.moveToNext();
        }
        cursor.close();
        CloseDatabase();
        ArrayList<StractCity> newList = removeDuplicates(arrayListHospital);

        return newList;
    }


    public ArrayList<StractState> Getstatee() {

        StractState stractState = null;
        ArrayList<StractState> arrayListHospital = new ArrayList<StractState>();
        // SELECT DISTINCT District FROM MST_Hospital
        OpenDatabase();

//        Cursor cursor = mDatabase.rawQuery( "select * from " + TABLE , null);
        Cursor cursor = mDatabase.query(true, TABLE,null, null, null, STATE, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            stractState = new StractState(cursor.getString(8));
            arrayListHospital.add(stractState);
            cursor.moveToNext();
        }
        cursor.close();
        CloseDatabase();
        ArrayList<StractState> newList = removeDuplicatess(arrayListHospital);

        return newList;
    }


    private ArrayList<StractCity> removeDuplicates(ArrayList<StractCity> arrayListHospital) {
        ArrayList<StractCity> newList = new ArrayList<StractCity>();
        for (StractCity element : arrayListHospital) {

            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        return newList;
    }


    private ArrayList<StractState> removeDuplicatess(ArrayList<StractState> arrayListHospital) {
        ArrayList<StractState> newList = new ArrayList<StractState>();
        for (StractState element : arrayListHospital) {

            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        return newList;
    }

}

