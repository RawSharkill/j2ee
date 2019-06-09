package com.myspring.demo.model;

import com.myspring.demo.Dao.AttendanceRepository;
import com.myspring.demo.Dao.DepartmentRepository;
import com.myspring.demo.Dao.RecordRepository;
import com.myspring.demo.Dao.StaffRepository;
import com.myspring.demo.entity.Attendance;
import com.myspring.demo.entity.Department;
import com.myspring.demo.entity.Record;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class mystaff {
    private int staffId;
    private String name;
    private String type;//職位
    private int salary;

    private RecordRepository recordRepository;
    private DepartmentRepository departmentRepository;
    private StaffRepository staffRepository;
    private AttendanceRepository attendanceRepository;

    public boolean signin() throws Exception {
        Record record = new Record();
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(d);
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        String time = sdf2.format(d);

        List<Record> records = recordRepository.findAllByStaffIdAndAndDate(staffId, date);
        if (records.isEmpty()) {
            record.setStaffId(staffId);
            record.setDate(date);
            record.setIntime(time);
            recordRepository.save(record);
            return true;
        }
        return false;
    }

    public boolean signout() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(d);
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
        String time = sdf2.format(d);
        List<Record> records = recordRepository.findAllByStaffIdAndAndDate(staffId, date);
        if (records.isEmpty()) {
            return false;
        }
        records.get(0).setOuttime(time);
        return true;
    }


    public void application(){

    }

    //d>settime=true;d<settime=false
    public static boolean CompareTime(Date d,String SetTime)throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date Set = sdf.parse(SetTime);
        String dd=sdf.format(d);
        d=sdf.parse(dd);
        return Set.before(d);
    }


}
