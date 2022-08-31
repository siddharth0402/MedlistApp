package com.example.signup;

public class USEROXY {
    private String date;


    private String oxygen;
    private String notes;

    public void setDate(String date) {
        this.date = date;
    }

    public void setOxygen(String oxygen) {
        this.oxygen = oxygen;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public USEROXY(String date) {
        this.date = date;
    }
    public USEROXY(String date, String oxygen) {
        this.date = date;
        this.oxygen = oxygen;
    }

    public USEROXY(String date, String oxygen, String notes) {
        this.date = date;
        this.oxygen = oxygen;
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public String getOxygen() {
        return oxygen;
    }

    public String getNotes() {
        return notes;
    }
}