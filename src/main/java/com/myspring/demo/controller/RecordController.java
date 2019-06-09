package com.myspring.demo.controller;

import com.myspring.demo.Dao.DepartmentRepository;
import com.myspring.demo.Dao.RecordRepository;
import com.myspring.demo.Dao.StaffRepository;
import com.myspring.demo.entity.Department;
import com.myspring.demo.entity.Record;
import com.myspring.demo.entity.Staff;
import com.myspring.demo.model.UsrSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Controller
@RequestMapping("/record")
public class RecordController{

    @RequestMapping("dopunchin")
    public String dopunch(HttpSession httpSession){
        UsrSession usrSession = (UsrSession) httpSession.getAttribute("usr");
        int id=usrSession.getId();
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        String currentTime = new SimpleDateFormat("HH-mm-ss").format(new Date(System.currentTimeMillis()));
        //今天未签到?
        if(recordRepository.findAllByStaffIdAndAndDate(id,currentDate).isEmpty()) {
            Record record = new Record();
            record.setDate(currentDate);
            record.setIntime(currentTime);
            record.setStaffId(usrSession.getId());
            recordRepository.save(record);
            return "my";
        }
        else
        {
            return "my";
        }
    }
    @RequestMapping("dopunchout")
    public String dopunchout(HttpSession httpSession){
        UsrSession usrSession = (UsrSession) httpSession.getAttribute("usr");
        int id=usrSession.getId();
        Staff s=staffRepository.findAllByStaffId(id);
        int depart=s.getDepartmentId();
        Department d=departmentRepository.findAllByDepartmentId(depart);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        String currentTime = new SimpleDateFormat("HH-mm-ss").format(new Date(System.currentTimeMillis()));
        if(recordRepository.findAllByStaffIdAndAndDate(id,currentDate).isEmpty())
            return "你还没签到";
        else{
            Record record=recordRepository.findAllByStaffIdAndAndDate(id,currentDate).get(0);
            record.setOuttime(currentTime);
            String in=record.getIntime();
            String out=currentTime;
            String dein=d.getStime();
            String deout=d.getEtime();
            if(checktime(in,dein)&&checktime(deout,out))
                record.setState("3");
            if(checktime(in,dein))
                record.setState("2");
            if(checktime(deout,out))
                record.setState("1");
            else
                record.setState("0");

            recordRepository.save(record);
            return "my";
        }
    }
    //a晚于b true
    private boolean checktime(String a ,String b){
        String[]aa=a.split("-");
        String[]bb=b.split("-");
        if(aa.length>2 && bb.length>2) {
            int a1 = Integer.parseInt(aa[0]);
            int a2 = Integer.parseInt(aa[1]);
            int a3 = Integer.parseInt(aa[2]);
            int b1 = Integer.parseInt(aa[0]);
            int b2 = Integer.parseInt(aa[1]);
            int b3 = Integer.parseInt(aa[2]);
            if(a1>b1 && a2>b2 && a3>b3)
                return true;
            return false;
        }
        else
            return false;
    }
    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private StaffRepository staffRepository;
}

