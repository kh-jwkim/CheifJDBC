package com.kh.jdbc.day01.stmt.member.controller;

import java.util.List;

import com.kh.jdbc.day01.stmt.member.model.dao.MemberDAO;
import com.kh.jdbc.day01.stmt.member.model.vo.Member;

public class MemberController {
	MemberDAO mDao;
	
	public MemberController() {
		mDao = new MemberDAO();
	}
	
	public void selectOne() {
		
	}
	
	public void selectList() {
		mDao.selectList();
	}
	
	public void insertMember(Member member) {
		mDao.insertMember(member);
	}
	
	public List<Member> listMember(){
		return mDao.selectList();
	}

	public List<Member> searchMember(String userID) {
		// TODO Auto-generated method stub
		return mDao.selectID(userID);
	}
}
