package com.example.signup;

public class USERSUGAR {
    private String date;


    private String sugar;
    private String notes;

    public void setDate(String date) {
        this.date = date;
    }

    public void setsugar(String sugar) {
        this.sugar = sugar;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public USERSUGAR(String date) {
        this.date = date;
    }

    public USERSUGAR(String date, String sugar) {
        this.date = date;
        this.sugar = sugar;
    }

    public USERSUGAR(String date, String sugar, String notes) {
        this.date = date;
        this.sugar = sugar;
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public String getsugar() {
        return sugar;
    }

    public String getNotes() {
        return notes;
    }
}

