package com.myspring.demo.controller;

import com.myspring.demo.Dao.DepartmentRepository;
import com.myspring.demo.Dao.StaffRepository;
import com.myspring.demo.entity.Department;
import com.myspring.demo.entity.Staff;
import com.myspring.demo.model.UsrSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @RequestMapping("/dologin")
    public String dologin(HttpServletRequest request,HttpSession httpSession){
        String name=request.getParameter("name");
        String password=request.getParameter("password");

        Staff staff=staffRepository.findAllByName(name);
        if(password.equals(staff.getPassword()))
        {
            UsrSession usrSession=new UsrSession(staff.getStaffId(),staff.getName(),staff.getStaffType());
            httpSession.setAttribute("usr",usrSession);
            return "my";
        }

        else
            return "login";

    }
    @RequestMapping("/doregister")
    public String doregister(HttpServletRequest request){
        String name=request.getParameter("name");
        String password=request.getParameter("password");
        String department=request.getParameter("部门名称");
        String postion=request.getParameter("position");
        Staff staff=new Staff();
        staff.setName(name);
        staff.setPassword(password);
        if(postion.equals("staff"))
            staff.setStaffType(0);
        else
            staff.setStaffType(1);
        Department d= departmentRepository.findAllByDepartmentName(department);
        int id;
        if(d!=null)
            id=d.getDepartmentId();
        else
            id=0;
        staff.setDepartmentId(id);
        staffRepository.save(staff);
        return "login";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/register")
    public ModelAndView register(){
        List<Department>departments=departmentRepository.findAll() ;
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("/register");
        modelAndView.addObject("departments",departments);
        return modelAndView;
    }
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
}
