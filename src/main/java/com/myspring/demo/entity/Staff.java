package com.myspring.demo.entity;


import javax.persistence.*;

@Entity
@Table(name="staff")
public class Staff {
    @Id
    @GeneratedValue
    private int staffId;

    @Column(length = 255)
    private int staffType;

    @Column(length = 255)
    private String name;


    @Column(length = 255)
    private String password;

    @Column
    private int salary;

    @Column
    private int departmentId;
    public int getStaffId() {
        return staffId;
    }

    public int getStaffType() {
        return staffType;
    }

    public String getName() {
        return name;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public void setStaffType(int staffType) {
        this.staffType = staffType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String title) {
        this.password = title;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }

    public int getSalary() {
        return salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
