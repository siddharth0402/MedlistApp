package com.example.signup;

public class User {
    private String date;


    private String bp;
    private String notes;

    public void setDate(String date) {
        this.date = date;
    }

    public void setBp(String bp) {
        this.bp = bp;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public User(String date) {
        this.date = date;
    }
    public User(String date, String bp) {
        this.date = date;
        this.bp = bp;
    }

    public User(String date, String bp, String notes) {
        this.date = date;
        this.bp = bp;
        this.notes = notes;
    }

    public String getDate() {
        return date;
    }

    public String getBp() {
        return bp;
    }

    public String getNotes() {
        return notes;
    }
}

