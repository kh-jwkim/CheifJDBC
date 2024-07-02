package com.kh.jdbc.day04.pstmt.employee.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

public class EmployeeDAO {
	
	private final String FILE_NAME = "resources/query.properties";
	private Properties prop;
	private String selectOne_query;
	private String selectList_query;
	private String insertEmployee_query;
	private String updateEmployee_query;
	private String deleteEmployee_query;
	
	public EmployeeDAO() {
		try {
			prop = new Properties();
			Reader reader = new FileReader(FILE_NAME);
			prop.load(reader);
			selectOne_query			= prop.getProperty("selectOne_query");
			selectList_query		= prop.getProperty("selectList");
			insertEmployee_query	= prop.getProperty("insertEmployee");
			updateEmployee_query	= prop.getProperty("updateEmployee");
			deleteEmployee_query	= prop.getProperty("deleteEmployee");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Employee selectOne(Connection conn, String modEmpId) throws SQLException {
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			Employee emp = null;
//			String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ?";
			pstmt = conn.prepareStatement(selectOne_query);
			pstmt.setString(1, modEmpId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				emp = rsetToEmployee(rset);
			}
			pstmt.close();
	//		conn.close();	//conn이 싱글톤 구조로 되어있어서 닫으면 안된다
			rset.close();
			return emp;
		}

	public List<Employee> selectList(Connection conn){
		Statement stmt = null;
		ResultSet rset = null;
		List<Employee> eList = null;
		String query = "SELECT * FROM EMPLOYEE";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(selectList_query);
			//rsetToEmployee
			eList = new ArrayList<Employee>();
			while (rset.next()) {
				Employee emp = rsetToEmployee(rset);
				eList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
//				conn.close();	//conn이 싱글톤 구조로 되어있어서 닫으면 안된다
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return eList;
	}

	public int insertEmployee(Connection conn, Employee emp) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
//		String query = "INSERT INTO EMPLOYEE(EMP_ID, EMP_NAME, EMP_NO, JOB_CODE, SAL_LEVEL, ENT_YN) VALUES(?, ?, ?, ?, ?, DEFAULT)";
		pstmt = conn.prepareStatement(insertEmployee_query);
		pstmt.setString(1, emp.getEMP_ID());
		pstmt.setString(2, emp.getEMP_NAME());
		pstmt.setString(3, emp.getEMP_NO());
		pstmt.setString(4, emp.getJOB_CODE());
		pstmt.setString(5, emp.getSAL_LEVEL());
		result = pstmt.executeUpdate();

		pstmt.close();
//		conn.close();	//conn이 싱글톤 구조로 되어있어서 닫으면 안된다

		return result;
	}

	public int updateEmployee(Connection conn, Employee modEmp) throws SQLException {
			PreparedStatement pstmt = null;
			int result = 0;
//			String query = "UPDATE EMPLOYEE SET EMAIL = ?, PHONE = ?, DEPT_CODE = ?, SALARY = ?, BONUS = ?, MANAGER_ID = ? WHERE EMP_ID = ?";
			pstmt = conn.prepareStatement(updateEmployee_query);
			pstmt.setString(1, modEmp.getEMAIL());
			pstmt.setString(2, modEmp.getPHONE());
			pstmt.setString(3, modEmp.getDEPT_CODE());
			pstmt.setInt(4, modEmp.getSALARY());
			pstmt.setDouble(5, modEmp.getBONUS());
			pstmt.setString(6, modEmp.getMANAGER_ID());
			pstmt.setString(7, modEmp.getEMP_ID());
			result = pstmt.executeUpdate();
			pstmt.close();
	//		conn.close();	//conn이 싱글톤 구조로 되어있어서 닫으면 안된다
			return result;
		}

	public int deleteEmployee(Connection conn, String empId) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
//		String query = "DELETE FROM EMPLOYEE WHERE EMP_ID = ?";
		pstmt = conn.prepareStatement(deleteEmployee_query);
		pstmt.setString(1, empId);
		result = pstmt.executeUpdate();
		pstmt.close();
//		conn.close();	//conn이 싱글톤 구조로 되어있어서 닫으면 안된다
		return result;
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
