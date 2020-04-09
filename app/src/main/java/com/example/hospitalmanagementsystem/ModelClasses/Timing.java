package com.example.hospitalmanagementsystem.ModelClasses;

public class Timing {
    private int tid,t1,t2,t3,pmcid;

    public Timing(int tid, int t1, int t2, int t3, int pmcid) {
        this.tid = tid;
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.pmcid = pmcid;
    }

    public Timing() {
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getT1() {
        return t1;
    }

    public void setT1(int t1) {
        this.t1 = t1;
    }

    public int getT2() {
        return t2;
    }

    public void setT2(int t2) {
        this.t2 = t2;
    }

    public int getT3() {
        return t3;
    }

    public void setT3(int t3) {
        this.t3 = t3;
    }

    public int getPmcid() {
        return pmcid;
    }

    public void setPmcid(int pmcid) {
        this.pmcid = pmcid;
    }
}
