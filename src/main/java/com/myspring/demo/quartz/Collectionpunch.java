package com.myspring.demo.quartz;

import com.myspring.demo.Dao.AttendanceRepository;
import com.myspring.demo.Dao.DepartmentRepository;
import com.myspring.demo.Dao.RecordRepository;
import com.myspring.demo.Dao.StaffRepository;
import com.myspring.demo.entity.Attendance;
import com.myspring.demo.entity.Record;
import com.myspring.demo.entity.Staff;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Collectionpunch implements Job {
    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;

    public void setRecordRepository(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public void setStaffRepository(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public void setDepartmentRepository(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException{
        List<Staff>staffs=staffRepository.findAll();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        for(Staff s : staffs){
            Attendance attendance =new Attendance();
             List<Record>records=recordRepository.findAllByStaffIdAndAndDate(s.getStaffId(),currentTime);
             attendance.setStaffId(s.getStaffId());
             attendance.setDate(currentTime);

            if(records.isEmpty()){
                attendance.setAtt(1);
            }
            else{
               attendance.setAtt(0);
            }
            attendanceRepository.save(attendance);

        }
   }
}
