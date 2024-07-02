package com.kh.jdbc.day04.pstmt.common;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//DAO 클래스의 역할 중 연결에 대한 부분을 이 클래스에 위임
public class JDBCTemplate {
	
	private static final String FILE_NAME = "resources/dev.properties";
	private static Properties prop;
	private static Connection conn;
	
	public static Connection getConnection() throws IOException, ClassNotFoundException, SQLException {
//		conn을 private static으로 선언하길래 싱글톤 패턴으로 구성할 줄 알았는데 맞았음
//		싱글톤 아닐때는 EmployeeDAO에서 conn.close() 써주고 싱글톤이 맞으면 닫으면 안됨!
		prop = new Properties();
		Reader reader = new FileReader(FILE_NAME);
		prop.load(reader);
		String driverName = prop.getProperty("driverName");
		String url		  = prop.getProperty("url");
		String user		  = prop.getProperty("user");
		String password	  = prop.getProperty("password");
		
		if(conn == null || conn.isClosed()) {	//Connection.isClosed() 메서드로 인스턴스의 생사를 체크할 수 있다!!
			Class.forName(driverName);
			conn = DriverManager.getConnection(url, user, password);
		}


		return conn;
	}
}
