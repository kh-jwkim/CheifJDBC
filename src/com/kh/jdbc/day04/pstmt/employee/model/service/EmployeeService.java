package com.kh.jdbc.day04.pstmt.employee.model.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.kh.jdbc.day04.pstmt.common.JDBCTemplate;
import com.kh.jdbc.day04.pstmt.employee.model.dao.EmployeeDAO;
import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

public class EmployeeService {
//	private JDBCTemplate jdbcTemplate;	//싱글톤으로 구조 변경하면서 static메서드로 Connection을 받아올 수 있게됨
	private EmployeeDAO eDao;
	
	public EmployeeService() {
//		jdbcTemplate = new JDBCTemplate();	//싱글톤으로 구조 변경하면서 static메서드로 Connection을 받아올 수 있게됨
		eDao = new EmployeeDAO();
	}
	
	public List<Employee> selectList(){
		Connection conn = null;
		List<Employee> eList = null;
		try {
			conn = JDBCTemplate.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		eList = eDao.selectList(conn);
		return eList;
	}
	
	public int insertEmployee(Employee emp) {
		Connection conn = null;
		int result = 0;
		try {
			try {
				conn = JDBCTemplate.getConnection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			result = eDao.insertEmployee(conn, emp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int updateEmployee(Employee modEmp) {
		Connection conn = null;
		int result = 0;
		try {
			try {
				conn = JDBCTemplate.getConnection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			result = eDao.updateEmployee(conn, modEmp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int deleteEmployee(String empId) {
		Connection conn = null;
		int result = 0;
		
		try {
			try {
				conn = JDBCTemplate.getConnection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			result = eDao.deleteEmployee(conn,empId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Employee selectOne(String modEmpId) {
		Connection conn = null;
		Employee emp = null;
		try {
			try {
				conn = JDBCTemplate.getConnection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			emp = eDao.selectOne(conn, modEmpId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}
}
