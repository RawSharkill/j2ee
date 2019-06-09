package com.myspring.demo.Dao;

import com.myspring.demo.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Integer> {
    public List<Record> findAllByStaffIdAndAndDate(Integer id,String date);
    public List<Record> findAllByStaffId(Integer id);
    public List<Record> findAllByRecordId(Integer id);
}
