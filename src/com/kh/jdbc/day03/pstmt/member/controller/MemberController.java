package com.kh.jdbc.day03.pstmt.member.controller;

import com.kh.jdbc.day03.pstmt.member.model.dao.MemberDAO;
import com.kh.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberController {
	
	MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}

	public int registerMember(Member member) {
		int result = mDao.insertMember(member);
		return result;
	}

	public Member checkLogin(Member member) {
		Member result = mDao.selectOne(member);
		return result;
	}

	public int removeMember(String delID) {
		int result = mDao.deleteOne(delID);
		return result;
	}

	public int modifyMember(Member modMember) {
		int result = mDao.modifyOne(modMember);
		return result;
	}

}
