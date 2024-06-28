package com.kh.jdbc.day02.stmt.member.controller;

import java.util.List;

import com.kh.jdbc.day02.stmt.member.model.dao.MemberDAO;
import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberController {
	
	MemberDAO mDao;
	
	public MemberController() {
		// TODO Auto-generated constructor stub
		mDao = new MemberDAO();
	}

	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		return mDao.insertMember(member);
	}

	public List<Member> printAllMember() {
		// TODO Auto-generated method stub
		//여러개니까 List, 멤버니까 List<Member>
		List<Member> mList = mDao.selectList();
		//호출한 곳에서 써야되니까 return, MemberView:34
		return mList;
	}

	public Member searchMember(String userID) {
		// TODO Auto-generated method stub
		Member member = mDao.idSearch(userID);
		return member;
	}

	public int modifyMember(String modifyID, Member modMember) {
		// TODO Auto-generated method stub
		return mDao.modifyMember(modifyID, modMember);
	}

	public int removeMember(String delID) {
		// TODO Auto-generated method stub
		return mDao.removeMember(delID);
	}

}
