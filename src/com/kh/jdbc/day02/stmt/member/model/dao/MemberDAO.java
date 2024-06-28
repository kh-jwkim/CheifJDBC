package com.kh.jdbc.day02.stmt.member.model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberDAO {
	//JDBC 코딩 절차
	//JDBC를 통해 DB의 데이터를 가져옴
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private final String USERNAME = "CHEIFJDBC";
	private final String PASSWORD = "CHEIFJDBC";

	public int insertMember(Member member) {
		/*
		 * 1. 드라이버 등록
		 * 2. 연결 생성
		 * 3. Statement 생성
		 * 4. SQL문 전송
		 * 5. 결과받기
		 * 6. 자원해제
		 */
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			stmt = conn.createStatement();	//워크시트 열기
			//쿼리문 작성, ; 오타 조심!, '(홑따옴표) 조심!
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
			//DML의 경우 성공한 행의 갯수가 리턴, 메서드는 executeUpdate() 사용
			result = stmt.executeUpdate(query);
			
			if(result > 0) { //지금은 자동커밋상태
				//commit
			}else {
				//rollback
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//finally는 예외 발생 여부에 상관없이 무조건 실행
			//다 쓴 자원을 반납하여 오류 발생 방지
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	//MemberController 클래스에서 호출
	public List<Member> selectList() {
		// TODO Auto-generated method stub
		/*
		 * 1. 드라이버 등록
		 * 2. 연결 생성
		 * 3. Statement 생성
		 * 4. SQL문 전송
		 * 5. 결과받기
		 * 6. 자원해제
		 */
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		//DB에서 가져온 값 넘겨줘야 하니까
		List<Member> mList = null;
		try {
			Class.forName(DRIVER_NAME); // 드라이버 등록
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 연결생성
			stmt = conn.createStatement();	//워크시트 열기
			//쿼리문 작성, ; 오타 조심!, '(홑따옴표) 조심!
			String query = "SELECT * FROM MEMBER_TBL"; //쿼리문 작성
			// 실행 CTRL_ENTER
			rset = stmt.executeQuery(query); // SELECT는 esecuteQuery(query)
			// 후처리, 여러개니까 while, 전부 가져올때까지 돈다
			mList = new ArrayList<Member>();
			while(rset.next()) {
				//rset은 바로 못쓰니까 Member
				Member member = new Member();
				//비어있으면 안되니까 setter
				member = this.rsetToMember(rset);
				
				// member에 다 담고 List에 담아야되니까
				mList.add(member);
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//finally는 예외 발생 여부에 상관없이 무조건 실행
			//다 쓴 자원을 반납하여 오류 발생 방지
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mList;
	}

	public Member idSearch(String userID) {
		// TODO Auto-generated method stub
		/*
		 * 1. 드라이버 등록
		 * 2. 연결 생성
		 * 3. Statement 생성
		 * 4. SQL문 전송
		 * 5. 결과받기
		 * 6. 자원해제
		 */
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		//DB에서 가져온 값 넘겨줘야 하니까
		Member member = new Member();
		try {
			Class.forName(DRIVER_NAME); // 드라이버 등록
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD); // 연결생성
			stmt = conn.createStatement();	//워크시트 열기
			//쿼리문 작성, ; 오타 조심!, '(홑따옴표) 조심!
			String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = '"+userID+"'"; //쿼리문 작성
			// 실행 CTRL_ENTER
			rset = stmt.executeQuery(query); // SELECT는 esecuteQuery(query)
			// 후처리, 여러개니까 while, 전부 가져올때까지 돈다
			if(rset.next()) {
				//rset은 바로 못쓰니까 Member
				//비어있으면 안되니까 setter
				//resultset에서 값을 가져와야되니까 rset.getString("컬럼명")
				member = this.rsetToMember(rset);
				// member에 다 담고 아래쪽에서 리턴 해버린다
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//finally는 예외 발생 여부에 상관없이 무조건 실행
			//다 쓴 자원을 반납하여 오류 발생 방지
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return member;
	}
	
	public Member rsetToMember(ResultSet rset) throws SQLException{
		Member member = new Member();
		//resultset에서 값을 가져와야되니까 rset.getString("컬럼명")
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
		
		return member;
	}

	public int modifyMember(String modifyID, Member modMember) {
		// TODO Auto-generated method stub
		/*
		 * 1. 드라이버 등록
		 * 2. 연결 생성
		 * 3. Statement 생성
		 * 4. SQL문 전송
		 * 5. 결과받기
		 * 6. 자원해제
		 */
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false); // 자동커밋 on/off 설정, 디폴트 값은 on이다
			stmt = conn.createStatement();	//워크시트 열기
			//쿼리문 작성, ; 오타 조심!, '(홑따옴표) 조심!
			String query = "UPDATE MEMBER_TBL SET MEMBER_PW = '"+modMember.getMemberPW()
					+"', MEMBER_NAME = '"+modMember.getMemberName()
					+"', GENDER = '"+modMember.getGender()
					+"', AGE = '"+modMember.getAge()
					+"', EMAIL = '"+modMember.getEmail()
					+"', PHONE = '"+modMember.getPhone()
					+"', ADDRESS = '"+modMember.getAddress()
					+"', HOBBY = '"+modMember.getHobby()+"' WHERE MEMBER_ID = '"+modifyID+"'";
			//DML의 경우 성공한 행의 갯수가 리턴, 메서드는 executeUpdate() 사용
			result = stmt.executeUpdate(query);
			
			if(result > 0) { //지금은 자동커밋상태
				//commit
				conn.commit();
			}else {
				//rollback
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//finally는 예외 발생 여부에 상관없이 무조건 실행
			//다 쓴 자원을 반납하여 오류 발생 방지
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public int removeMember(String delID) {
		// TODO Auto-generated method stub
		/*
		 * 1. 드라이버 등록
		 * 2. 연결 생성
		 * 3. Statement 생성
		 * 4. SQL문 전송
		 * 5. 결과받기
		 * 6. 자원해제
		 */
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//			conn.setAutoCommit(false); // 자동커밋 on/off 설정, 디폴트 값은 on이다
			stmt = conn.createStatement();	//워크시트 열기
			//쿼리문 작성, ; 오타 조심!, '(홑따옴표) 조심!
			String query = "DELETE FROM MEMBER_TBL WHERE MEMBER_ID = '"+delID+"'";
			//DML의 경우 성공한 행의 갯수가 리턴, 메서드는 executeUpdate() 사용
			result = stmt.executeUpdate(query);
			
			if(result > 0) { //지금은 자동커밋상태
				//commit
			}else {
				//rollback
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//finally는 예외 발생 여부에 상관없이 무조건 실행
			//다 쓴 자원을 반납하여 오류 발생 방지
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	
}
