package com.kh.jdbc.day04.pstmt.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//DAO 클래스의 역할 중 연결에 대한 부분을 이 클래스에 위임
public class JDBCTemplate_Old {
	private static final String Driver_name = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String USERNAME = "CHEIFJDBC";
	private static final String PASSWORD = "CHEIFJDBC";
	
	private static Connection conn = null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
//		conn을 private static으로 선언하길래 싱글톤 패턴으로 구성할 줄 알았는데 맞았음
//		싱글톤 아닐때는 EmployeeDAO에서 conn.close() 써주고 싱글톤이 맞으면 닫으면 안됨!
		if(conn == null || conn.isClosed()) {	//Connection.isClosed() 메서드로 인스턴스의 생사를 체크할 수 있다!!
			Class.forName(Driver_name);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		return conn;
	}
}
