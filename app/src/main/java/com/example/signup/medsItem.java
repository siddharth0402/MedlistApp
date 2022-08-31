package com.example.signup;
public class medsItem {

    private String mMedicine;
    private String mPrice;
    private String mUse;
    private String mSideEffects;


    public medsItem(String medicine, String price, String use, String sideEffects){
        mMedicine = medicine;
        mPrice = price;
        mUse = use;
        mSideEffects= sideEffects;

    }

    public String getMedicine(){
        return mMedicine;
    }

    public String getPrice(){
        return mPrice;
    }

    public String getUse(){
        return mUse;
    }

    public String getSideEffects(){
        return mSideEffects;
    }


}
