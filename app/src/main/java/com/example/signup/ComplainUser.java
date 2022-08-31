package com.example.signup;

public class ComplainUser {

    private String date;
    private String docname;
    private String hosname;
    private String issue;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getHosname() {
        return hosname;
    }

    public void setHosname(String hosname) {
        this.hosname = hosname;
    }


    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public ComplainUser(String date, String docname, String hosname, String issue) {
        this.date = date;
        this.docname = docname;
        this.hosname = hosname;
        this.issue = issue;
    }
}
