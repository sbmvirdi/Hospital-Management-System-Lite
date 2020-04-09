package com.example.hospitalmanagementsystem.ModelClasses;

public class Doctor {

    private int pmcid;
    private String dname,dept;

    public Doctor(int pmcid, String dname, String dept) {
        this.pmcid = pmcid;
        this.dname = dname;
        this.dept = dept;

    }

    public Doctor() {
    }

    public int getPmcid() {
        return pmcid;
    }

    public void setPmcid(int pmcid) {
        this.pmcid = pmcid;
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
