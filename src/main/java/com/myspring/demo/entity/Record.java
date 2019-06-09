package com.myspring.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="record")
public class Record {
    @Id
    @GeneratedValue
    private int recordId;

    @Column
    private int staffId;

    @Column
    private String intime;

    @Column
    private String outtime;

    @Column
    private String date;

    @Column
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }


    public void setDate(String date) {
        this.date = date;
    }


    public int getRecordId() {
        return recordId;
    }

    public int getStaffId() {
        return staffId;
    }

    public String getDate() {
        return date;
    }

    public String getIntime() {
        return intime;
    }

    public String getOuttime() {
        return outtime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public void setOuttime(String outtime) {
        this.outtime = outtime;
    }
}
