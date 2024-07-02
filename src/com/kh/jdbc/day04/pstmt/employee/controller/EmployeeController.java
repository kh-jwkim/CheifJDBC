package com.kh.jdbc.day04.pstmt.employee.controller;

import java.util.List;

import com.kh.jdbc.day04.pstmt.employee.model.service.EmployeeService;
import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

public class EmployeeController {

	private EmployeeService eService;
	
	public EmployeeController() {
		super();
		eService = new EmployeeService();
	}

	public List<Employee> printAllEmp() {
		List<Employee> resList = eService.selectList();
		return resList;
	}

	public int insertEmployee(Employee newEmp) {
		int result = eService.insertEmployee(newEmp);
		return result;
	}

	public int deleteEmployee(String empId) {
		int result = eService.deleteEmployee(empId);
		return result;
	}

	public Employee selectOneById(String modEmpId) {
		Employee employee = eService.selectOne(modEmpId);
		return employee;
	}

	public int updateEmployee(Employee modEmp) {
		int result = eService.updateEmployee(modEmp);
		return result;
	}

}
