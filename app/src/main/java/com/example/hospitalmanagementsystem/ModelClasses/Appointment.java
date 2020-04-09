package com.example.hospitalmanagementsystem.ModelClasses;

public class Appointment {
    private int apid,uid,pmcid,timing;
    private String pname,dname,dept;

    public Appointment(int apid, int uid, int pmcid, String pname, String dname, String dept,int timing) {
        this.apid = apid;
        this.uid = uid;
        this.pmcid = pmcid;
        this.pname = pname;
        this.dname = dname;
        this.dept = dept;
        this.timing = timing;
    }

    public Appointment() {
    }

    public int getTiming() {
        return timing;
    }

    public void setTiming(int timing) {
        this.timing = timing;
    }

    public int getApid() {
        return apid;
    }

    public void setApid(int apid) {
        this.apid = apid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPmcid() {
        return pmcid;
    }

    public void setPmcid(int pmcid) {
        this.pmcid = pmcid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
