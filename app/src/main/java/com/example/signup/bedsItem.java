package com.example.signup;

public class bedsItem {

    private String mState;
    private int mRuralHospital;
    private int mRuralBed;
    private String mUrbanHospital;
    private int mUrbanBed;
    private int mTotalHospital;

    public bedsItem(String state, int ruralHospital, int ruralBed, String urbanHospital, int urbanBed, int totalHospital){
        mState = state;
        mRuralHospital = ruralHospital;
        mRuralBed = ruralBed;
        mUrbanHospital = urbanHospital;
        mUrbanBed = urbanBed;
        mTotalHospital = totalHospital;
    }

    public String getState(){
        return mState;
    }

    public int getRuralHospital(){
        return mRuralHospital;
    }

    public int getRuralBed(){
        return mRuralBed;
    }

    public String getUrbanHospital(){
        return mUrbanHospital;
    }

    public int getUrbanBed(){
        return mUrbanBed;
    }

    public int getTotalHospital(){
        return mTotalHospital;
    }

}
