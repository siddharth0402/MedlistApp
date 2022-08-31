package com.example.signup;

public class ModelPerson {
    private String personName, personEmail;
    private int personProfile;

    public ModelPerson(String personName) {
        this.personName = personName;

    }


    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonEmail() {
        return personEmail;
    }

    public void setPersonEmail(String personEmail) {
        this.personEmail = personEmail;
    }

    public int getPersonProfile() {
        return personProfile;
    }

    public void setPersonProfile(int personProfile) {
        this.personProfile = personProfile;
    }
}