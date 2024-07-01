package com.kh.jdbc.day03.pstmt.imployee.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day03.pstmt.imployee.model.vo.Employee;

public class EmployeeDAO {
	private final String Driver_name = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private final String USERNAME = "CHEIFJDBC";
	private final String PASSWORD = "CHEIFJDBC";
	
	public List<Employee> selectList(){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		List<Employee> eList = null;
		String query = "SELECT * FROM EMPLOYEE";
		try {
			Class.forName(Driver_name);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			//rsetToEmployee
			eList = new ArrayList<Employee>();
			while (rset.next()) {
				Employee emp = rsetToEmployee(rset);
				eList.add(emp);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eList;
	}

	private Employee rsetToEmployee(ResultSet rset) {
		Employee employee = new Employee();
		try {
			employee.setEMP_ID(rset.getString("EMP_ID"));
			employee.setEMP_NAME(rset.getString("EMP_NAME"));
			employee.setEMP_NO(rset.getString("EMP_NO"));
			employee.setEMAIL(rset.getString("EMAIL"));
			employee.setPHONE(rset.getString("PHONE"));
			employee.setDEPT_CODE(rset.getString("DEPT_CODE"));
			employee.setJOB_CODE(rset.getString("JOB_CODE"));
			employee.setSAL_LEVEL(rset.getString("SAL_LEVEL"));
			employee.setSALARY(rset.getInt("SALARY"));
			employee.setBONUS(rset.getDouble("BONUS"));
			employee.setMANAGER_ID(rset.getString("MANAGER_ID"));
			employee.setHIRE_DATE(rset.getDate("HIRE_DATE"));
			employee.setENT_DATE(rset.getDate("ENT_DATE"));
			employee.setENT_YN(rset.getString("ENT_YN"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}
}
