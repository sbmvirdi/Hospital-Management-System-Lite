package com.example.hospitalmanagementsystem.ModelClasses;

public class Room {
    private int rid,roomno,avail;
    private String pname;

    public Room(int rid, int roomno, int avail, String pname) {
        this.rid = rid;
        this.roomno = roomno;
        this.avail = avail;
        this.pname = pname;
    }

    public Room(int roomno, int avail) {
        this.roomno = roomno;
        this.avail = avail;
    }

    public Room() {
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getRoomno() {
        return roomno;
    }

    public void setRoomno(int roomno) {
        this.roomno = roomno;
    }

    public int getAvail() {
        return avail;
    }

    public void setAvail(int avail) {
        this.avail = avail;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }
}
