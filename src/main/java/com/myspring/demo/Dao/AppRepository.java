package com.myspring.demo.Dao;

import com.myspring.demo.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppRepository extends JpaRepository<App,Integer> {
    List<App> findAllByStaffId(Integer id);
    List<App> findAllByDepartmentId(Integer id);
    List<App> findAllByRecordId(Integer id);
    List<App>findAllByAppId(Integer id);
}
