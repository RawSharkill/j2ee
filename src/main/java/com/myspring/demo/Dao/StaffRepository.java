package com.myspring.demo.Dao;

import com.myspring.demo.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff,Integer>{
    public Staff findAllByStaffId(int staffId);
    public Staff findAllByName(String name);
}
