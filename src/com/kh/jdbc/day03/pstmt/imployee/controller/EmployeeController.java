package com.kh.jdbc.day03.pstmt.imployee.controller;

import java.util.List;

import com.kh.jdbc.day03.pstmt.imployee.model.dao.EmployeeDAO;
import com.kh.jdbc.day03.pstmt.imployee.model.vo.Employee;

public class EmployeeController {

	EmployeeDAO eDao;
	
	public EmployeeController() {
		super();
		eDao = new EmployeeDAO();
	}

	public List<Employee> printAllEmp() {
		List<Employee> resList = eDao.selectList();
		return resList;
	}

}
