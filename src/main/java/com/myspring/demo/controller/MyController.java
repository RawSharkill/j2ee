package com.myspring.demo.controller;

import com.myspring.demo.Dao.AppRepository;
import com.myspring.demo.Dao.RecordRepository;
import com.myspring.demo.Dao.StaffRepository;
import com.myspring.demo.entity.App;
import com.myspring.demo.entity.Record;
import com.myspring.demo.entity.Staff;
import com.myspring.demo.model.UsrSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/me")
public class MyController {
    @RequestMapping("my")
    public String my(){
        return "my";
    }
    @RequestMapping("punch")
    public String punch(){
        return "punch";
    }
    @RequestMapping("check")
    public ModelAndView check(HttpSession httpSession){
        UsrSession usrSession= (UsrSession) httpSession.getAttribute("usr");
        int id=usrSession.getId();
        String currentMonth = new SimpleDateFormat("yyyy-MM").format(new Date(System.currentTimeMillis()));
        List<Record>records=recordRepository.findAllByStaffId(id);
        List<Record>recordList=new ArrayList<>();
        if(!records.isEmpty()){
             for(Record r : records){
                String date=r.getDate();
                if(date.startsWith(currentMonth)){
                    recordList.add(r);
                }
            }
        }
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/check");
        modelAndView.addObject("records",recordList);
        return modelAndView;
    }
    @RequestMapping("/app")
    public String application(HttpServletRequest request,HttpSession httpSession){
        String recordid=request.getParameter("recordId");
        String reason=request.getParameter("reason");
        int id=Integer.parseInt(recordid);

        App app =new App();
        app.setExcuse(reason);
        app.setRecordId(id);
        UsrSession usrSession= (UsrSession) httpSession.getAttribute("usr");
        int staffid=usrSession.getId();
        int departid=staffRepository.findAllByStaffId(staffid).getDepartmentId();
        app.setStaffId(staffid);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
        app.setAppDay(currentDate);
        app.setState(0);
        app.setDepartmentId(departid);

        appRepository.save(app);
            return "my";
    }
    @RequestMapping("/confirm")
    public ModelAndView confirm(HttpServletRequest request,HttpSession httpSession){
        UsrSession usrSession= (UsrSession) httpSession.getAttribute("usr");
        if(usrSession.getType()==0) //员工
        {
            int id=usrSession.getId();
            List<App>apps=appRepository.findAllByStaffId(id);
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("/checkApp");
            modelAndView.addObject("apps",apps);
             return modelAndView;

        }
        else
        {
             int id=usrSession.getId();
            Staff s=staffRepository.findAllByStaffId(id);
            List<App>apps=appRepository.findAllByDepartmentId(s.getDepartmentId());
            ModelAndView modelAndView=new ModelAndView();
            modelAndView.setViewName("/confirmApp");
            modelAndView.addObject("apps",apps);
            return modelAndView;
        }
    }
    @RequestMapping("/modify")
    public String modify(HttpSession session, HttpServletRequest request){
        String appId=request.getParameter("recordId");
        int aId=Integer.parseInt(appId);
        List<App>apps=appRepository.findAllByAppId(aId);
        if(!apps.isEmpty())
        {
            App app=apps.get(0);
            int rid=app.getRecordId()+1;
            List<Record>records=recordRepository.findAllByRecordId(rid);
           if(!records.isEmpty())
        {
            Record r= records.get(0);
            r.setState("ok");
            recordRepository.save(r);
        }
            apps.get(0).setState(1);
            appRepository.save(app);
        }
        return"my";
//        int id=Integer.parseInt(request.getParameter("recordId"));
//        int mod=Integer.parseInt(request.getParameter("mod"));
//        String m=request.getParameter("rea");
//        /*
//        0-未处理
//        1-通过
//        2-不通过
//         */
//        if(mod==1)
//        {
//            Record record=recordRepository.findAllByRecordId(id).get(0);
//            record.setState("1");
//            recordRepository.save(record);
//            App a=appRepository.findAllByRecordId(id).get(0);
//            a.setState(1);
//            a.setExcuse(m);
//            appRepository.save(a);
//        }
//        else if(mod==2)
//        {
//            Record record=recordRepository.findAllByRecordId(id).get(0);
//            record.setState("2");
//            App a=appRepository.findAllByRecordId(id).get(0);
//            a.setState(2);
//            a.setExcuse(m);
//            appRepository.save(a);
//        }
//        else{
//
//        }
    }
    @Autowired
    RecordRepository recordRepository;
    @Autowired
    AppRepository appRepository;
    @Autowired
    StaffRepository staffRepository;

}
