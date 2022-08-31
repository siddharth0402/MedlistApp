package com.example.signup.ModelH;

public class C2HM {
    public String Hosc;
    public String id;

    public C2HM(String hosc, String id) {
        Hosc = hosc;
        this.id = id;
    }

    public String getHosc() {
        return Hosc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHosc(String city) {
        Hosc = city;
    }
    public C2HM(String hosc) {
        Hosc = hosc;
    }
}
