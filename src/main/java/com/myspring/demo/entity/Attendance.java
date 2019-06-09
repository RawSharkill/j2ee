package com.myspring.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="attendance")
public class Attendance {
    @Id
    @GeneratedValue
    private int attendanceId;

    @Column
    private int staffId;

    @Column
    private int att;

    @Column
    private String date;

    public int getAttendanceId() {
        return attendanceId;
    }

    public int getStaffId() {
        return staffId;
    }

    public int getAtt() {
        return att;
    }

    public String getDate() {
        return date;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setAtt(int att) {
        this.att = att;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
