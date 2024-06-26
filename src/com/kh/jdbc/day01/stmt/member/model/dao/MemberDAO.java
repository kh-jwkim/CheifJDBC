package com.kh.jdbc.day01.stmt.member.model.dao;

import java.sql.*;
import java.util.*;

import com.kh.jdbc.day01.stmt.member.model.vo.Member;

public class MemberDAO {
	//JDBC를 이용하여
	//Oracle DB에 접속하는 클래스
	//JDBC 코딩이 있어야 함.
	
	public List<Member> selectList() {
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원해제
		 */
		// 1. 왜 mList에 member가 들어가나요?
		// 2. rset은 왜 mList에 못들어가나요?
		// 3. rset을 member로 어떻게 바꾸나요?
		// 3.1 Member 클래스에는 필드와 게터/세터 필요
		// 3.2 ResultSet의 getString(), getInt(), getDate() 필요
		List<Member> mList = new ArrayList<Member>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			// 1.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "CHEIFJDBC", "CHEIFJDBC");
			// 3.
			stmt = conn.createStatement();
			// 4.
			String query = "SELECT * FROM MEMBER_TBL";
			rset = stmt.executeQuery(query);
			// 후처리
			while(rset.next()) {
				//rsetToMember
				Member member = new Member();
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
				
				mList.add(member);
			}
			// 6.
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mList;
	}
	
	public void insertMember(Member member) {
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원해제
		 */
		try {
			// 1. 드라이버 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. DBMS 연결 생성
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "CHEIFJDBC", "CHEIFJDBC");
			// 3. Statement 생성
			Statement stmt = conn.createStatement();
			// 4. 쿼리문 전송
//			String query = "INSERT INTO MEMBER_TBL(MEMBER_ID, MEMBER_PW, MEMBER_NAME, GENDER) VALUES('KH01', 'KH01', '일용자', '남')";
			String query = "INSERT INTO MEMBER_TBL VALUES('"
							+member.getMemberID()+"', '"
							+member.getMemberPW()+"', '"
							+member.getMemberName()+"', '"
							+member.getGender()+"', '"
							+member.getAge()+"', '"
							+member.getEmail()+"', '"
							+member.getPhone()+"', '"
							+member.getAddress()+"', '"
							+member.getHobby()+"', DEFAULT)";
			// 5. 
			int result = stmt.executeUpdate(query);
			// 후처리
			if(result > 0) {
				//성공 메시지 출력
				//commit;
				System.out.println("성공");
			}else {
				//실패 메시지 출력
				//rollback;
				System.out.println("실패");
			}
			stmt.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Member> selectID(String userID) {
		/*
		 * 1. 드라이버 등록
		 * 2. DBMS 연결 생성
		 * 3. Statement 생성
		 * 4. 쿼리문 전송
		 * 5. 결과값 받기
		 * 6. 자원해제
		 */
		// 1. 왜 mList에 member가 들어가나요?
		// 2. rset은 왜 mList에 못들어가나요?
		// 3. rset을 member로 어떻게 바꾸나요?
		// 3.1 Member 클래스에는 필드와 게터/세터 필요
		// 3.2 ResultSet의 getString(), getInt(), getDate() 필요
		List<Member> mList = new ArrayList<Member>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		try {
			// 1.
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2.
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "CHEIFJDBC", "CHEIFJDBC");
			// 3.
			stmt = conn.createStatement();
			// 4.
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+userID+"'";
			rset = stmt.executeQuery(query);
			// 후처리
			while(rset.next()) {
				//rsetToMember
				Member member = new Member();
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
				
				mList.add(member);
			}
			// 6.
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mList;
	}
}
