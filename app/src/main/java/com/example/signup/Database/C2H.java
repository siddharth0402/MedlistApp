package com.example.signup.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.signup.G;
import com.example.signup.ModelH.C2HM;

import java.util.ArrayList;

public class C2H extends SQLiteOpenHelper {
    public static String DBNAME;
    public static String TABLE;
    public static String CITY;
    public static String DBLOCATION = "/data/data/" + G.context.getPackageName() + "/databases"+"/" ;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public C2H(Context context, String DBname){
        super(context,DBNAME,null,1);
        DBNAME = DBname + ".db";
        TABLE = "MST_Hospital" ;
        CITY = "District";
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
    public ArrayList<C2HM> Gethosc(String xyz) {

        C2HM C2HM = null;
        ArrayList<C2HM> arrayListHospital = new ArrayList<C2HM>();
        // SELECT DISTINCT District FROM MST_Hospital
        OpenDatabase();
        //    String query = "Select HospitalID from MST_Hospital Where State = ("Andhra Pradesh")
//        Cursor cursor = mDatabase.rawQuery( "select * from " + TABLE , null);
       // Cursor cursor = mDatabase.rawQuery(" Select HospitalName from MST_Hospital Where District = 'xyz' " ,null);
       // Cursor cursor = mDatabase.rawQuery("select * from " + TABLE + " where " + CITY + " = ? " , new String[]{xyz});

       // Cursor cursor = mDatabase.rawQuery("select distinct * from " + TABLE + " where State =? group by " + CITY , new String[]{xyz});

        Cursor cursor = mDatabase.rawQuery("select * from " + TABLE + " where District = ?",  new String[]{xyz});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
           C2HM = new C2HM(cursor.getString(4),cursor.getString(0));
            arrayListHospital.add(C2HM);
            cursor.moveToNext();
        }
        cursor.close();
        CloseDatabase();
        ArrayList<C2HM> newList = removeDuplicates(arrayListHospital);

        return newList;
    }


    private ArrayList<C2HM> removeDuplicates(ArrayList<C2HM> arrayListHospital) {
        ArrayList<C2HM> newList = new ArrayList<C2HM>();
        for (C2HM element : arrayListHospital) {

            if (!newList.contains(element)) {

                newList.add(element);
            }
        }

        return newList;
    }

}


