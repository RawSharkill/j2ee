package com.myspring.demo.Dao;

import com.myspring.demo.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    public Department findAllByDepartmentId(int departmentId);
    public Department findAllByDepartmentName(String departmentName);
}
