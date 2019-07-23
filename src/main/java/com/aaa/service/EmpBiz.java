package com.aaa.service;

import java.util.List;
import java.util.Map;

public interface EmpBiz {
    /**
     * 查询所有的emp
     * */
    List<Map<String,Object>> findAllEmp();
}
