package com.example.signup.ModelH;

public class StractHospital {
    public String HospitalName;
    public String State;


    public StractHospital(String hospitalName) {
        HospitalName = hospitalName;
    }

    public StractHospital(String hospitalName, String state) {
        HospitalName = hospitalName;
        State = state;
    }

    public String getHospitalName() {
        return HospitalName;
    }

    public void setHospitalName(String hospitalName) {
        HospitalName = hospitalName;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public boolean toLowerCase() {
        return true;
    }


}
