package com.aaa.service.impl;

import com.aaa.dao.EmpDao;
import com.aaa.service.EmpBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class EmpBizImpl  implements EmpBiz{
    /**
     * 查询所有的emp
     */
    @Autowired
    EmpDao empDao;

    @Override
    public List<Map<String, Object>> findAllEmp() {
        return empDao.findAllEmp();
    }
}
