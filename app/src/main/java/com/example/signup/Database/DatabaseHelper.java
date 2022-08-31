package com.example.signup.Database;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.signup.G;
import com.example.signup.ModelH.StractHospital;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DBNAME;
    public static String TABLE;
    public static String DBLOCATION = "/data/data/" + G.context.getPackageName() + "/databases" + "/";
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context, String DBname) {
        super(context, DBNAME, null, 1);
        DBNAME = DBname + ".db";
        TABLE = "MST_Hospital";

        mContext = context;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public void OpenDatabase() {

        String DBPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }

        mDatabase = SQLiteDatabase.openDatabase(DBPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void CloseDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
        //Get record
    }

    public ArrayList<StractHospital> GetHospitals() {

        StractHospital stractHospital = null;
        ArrayList<StractHospital> arrayListHospital = new ArrayList<StractHospital>();
        OpenDatabase();
        Cursor cursor = mDatabase.rawQuery("select * from " + TABLE ,  null);
        cursor.moveToFirst();
      //  String[] columnNames = new String[] {HospitalID, Latitude, Longitude, Location, HospitalName, HospitalType,HospitalCareType,Address,State,District,Pincode,Telephone,Mobile,EmergencyNumber,AmbulancePhoneNumber,BloodBankPhoneNumber,Tollfree,Helpline,Fax,Email1,Email2,Website,Speciality,IsFavourite};
      //  String whereClause = "State=Bhopal";

        //return mDb.query(DATABASE_TABLE_TIMETABLE, columnNames, whereClause, null, null, null, null);
        while (!cursor.isAfterLast()) {
            stractHospital = new StractHospital(cursor.getString(4),cursor.getString(0));
//            StractHospital model = new StractHospital();
//            model.HospitalName = cursor.getString(4);
//           // model.State = cursor.getString(0);
            arrayListHospital.add(stractHospital);
            cursor.moveToNext();
        }
        cursor.close();
        CloseDatabase();
        return arrayListHospital;
    }
//    public ArrayList<StractHospital> GetCity()
//    {
//
//        StractHospital stractHospital = null;
//        ArrayList<StractHospital> arrayListHospital = new ArrayList<StractHospital>();
//        OpenDatabase();
//        //SELECT DISTINCT SALARY FROM CUSTOMERS
//        //   ORDER BY SALARY
//        Cursor cursorr = cDatabase.rawQuery(" SELECT DISTINCT DISTRICT FROM MST_HOSPITAL  ORDER BY DISTRICT " ,null);
//        cursorr.moveToFirst();
//        while (!cursorr.isAfterLast()){
//            stractHospital = new StractHospital(cursorr.getString(9));
//            arrayListHospital.add(stractHospital);
//            cursorr.moveToNext();
//        }
//        cursorr.close();
//        CloseDatabase();
//        return arrayListHospital;
//    }
}
