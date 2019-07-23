package com.aaa.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Mapper
public interface EmpDao {
    /**
     * 查询所有的emp
    * */
   List<Map<String,Object>> findAllEmp();
}
