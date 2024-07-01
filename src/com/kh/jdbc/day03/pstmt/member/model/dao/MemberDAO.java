package com.kh.jdbc.day03.pstmt.member.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kh.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberDAO {
	
	private final String Driver_name = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private final String USERNAME = "CHEIFJDBC";
	private final String PASSWORD = "CHEIFJDBC";

	public int insertMember(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			Class.forName(Driver_name);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
			
			//PreparedStatement 사용
			String query = "INSERT INTO MEMBER_TBL VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, member.getMemberID());
			pstmt.setString(2, member.getMemberPW());
			pstmt.setString(3, member.getMemberName());
			pstmt.setString(4, member.getGender());
			pstmt.setInt(5, member.getAge());
			pstmt.setString(6, member.getEmail());
			pstmt.setString(7, member.getPhone());
			pstmt.setString(8, member.getAddress());
			pstmt.setString(9, member.getHobby());
			result = pstmt.executeUpdate();
			
			//Statement 사용
//			stmt = conn.createStatement();
//			String query = "INSERT INTO MEMBER_TBL VALUES('"+
//					member.getMemberID()+"', '"+
//					member.getMemberPW()+"', '"+
//					member.getMemberName()+"', '"+
//					member.getGender()+"', '"+
//					member.getAge()+"', '"+
//					member.getEmail()+"', '"+
//					member.getPhone()+"', '"+
//					member.getAddress()+"', '"+
//					member.getHobby()+"', DEFAULT)";
//			result = stmt.executeUpdate(query);
			if (result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public Member selectOne(Member member) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member result = null;
		
		try {
			Class.forName(Driver_name);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			//쿼리문을 그대로 실행하는 Statement와는 다르게
			//쿼리문을 이용하여 컴파일을 미리 해서 객체를 생성함
			//쿼리문에는 값이 들어가는 자리를 위치홀더(? 마크)로 표시해줘야함.
			
			//PreparedStatement 사용
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?";
			pstmt = conn.prepareStatement(query);	//pstmt 생성 시 query문이 들어간다!!
			pstmt.setString(1, member.getMemberID());
			pstmt.setString(2, member.getMemberPW());
			rset = pstmt.executeQuery();
			
			//Statement 사용
			//stmt = conn.createStatement();	//stmt는 파라미터 전달 없이 그냥 생성 가능
			//String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+member.getMemberID()+
			//										"' AND MEMBER_PW = '"+member.getMemberPW()+"'";
			//rset = stmt.executeQuery(query);	//stmt는 executeQuery 메서드에서 전달받은 쿼리문을 그대로 실행시킴
			
			if(rset.next()) {
				result = rsetToMember(rset);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int deleteOne(String delID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			Class.forName(Driver_name);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
			
			//PreparedStatement 사용
			String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, delID);
			result = pstmt.executeUpdate();
			

			if (result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int modifyOne(Member modMember) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			Class.forName(Driver_name);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
			
			//PreparedStatement 사용
			String query = "UPDATE MEMBER_TBL SET MEMBER_PW = ?, MEMBER_NAME = ?, EMAIL = ?, PHONE = ?, ADDRESS = ?, HOBBY = ? WHERE MEMBER_ID = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, modMember.getMemberPW());
			pstmt.setString(2, modMember.getMemberName());
			pstmt.setString(3, modMember.getEmail());
			pstmt.setString(4, modMember.getPhone());
			pstmt.setString(5, modMember.getAddress());
			pstmt.setString(6, modMember.getHobby());
			pstmt.setString(7, modMember.getMemberID());
			result = pstmt.executeUpdate();
			
			if (result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	private Member rsetToMember(ResultSet rset) {
		Member member = new Member();
		try {
			member.setMemberID(rset.getString("MEMBER_ID"));
			member.setMemberPW(rset.getString("MEMBER_PW"));
			member.setMemberName(rset.getString("MEMBER_NAME"));
			member.setGender(rset.getString("GENDER"));
			member.setAge(rset.getInt("AGE"));
			member.setEmail(rset.getString("EMAIL"));
			member.setPhone(rset.getString("PHONE"));
			member.setAddress(rset.getString("ADDRESS"));
			member.setHobby(rset.getString("HOBBY"));
			member.setRegDate(rset.getDate("REG_DATE"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return member;
	}


}
