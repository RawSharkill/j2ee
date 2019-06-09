package com.myspring.demo.entity;

import javax.persistence.*;

@Entity
@Table(name="monthattendance")
public class MothAttendance {
    @Id
    @GeneratedValue
    private int monthId;

    @Column
    private int departmentId;

    @Column
    private int staffId;

    @Column
    private int attendanceDay;

    @Column
    private int absenceDay;

    @Column
    private int mothSalary;

    public int getMonthId() {
        return monthId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public int getStaffId() {
        return staffId;
    }

    public int getAttendanceDay() {
        return attendanceDay;
    }

    public int getAbsenceDay() {
        return absenceDay;
    }

    public int getMothSalary() {
        return mothSalary;
    }

    public void setMonthId(int monthId) {
        this.monthId = monthId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setAttendanceDay(int attendanceDay) {
        this.attendanceDay = attendanceDay;
    }

    public void setAbsenceDay(int absenceDay) {
        this.absenceDay = absenceDay;
    }

    public void setMothSalary(int mothSalary) {
        this.mothSalary = mothSalary;
    }
}
