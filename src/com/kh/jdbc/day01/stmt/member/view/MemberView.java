package com.kh.jdbc.day01.stmt.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day01.stmt.member.controller.MemberController;
import com.kh.jdbc.day01.stmt.member.model.vo.Member;

public class MemberView {

	MemberController mController;
	
	public MemberView() {
		mController = new MemberController();
	}
	
	public void startProgram() {
		//mController.insertMember();
//		mController.selectList();
		int choice = 0;
		end:
		while(true) {
			choice = this.printMainMenu();
			switch (choice) {
			case 0: break end;
			case 1:
				Member newMember = this.inputMember();
				mController.insertMember(newMember);
				break;
			case 2:
				List<Member> mList = mController.listMember();
				this.displayMemberList(mList);
				break;
			case 3:
				String userID = this.inputID();
				List<Member> mList1 = mController.searchMember(userID);
				this.displayMemberList(mList1);
				break;
				
			default: this.displayMessage("메뉴를 다시 선택해주세요.");
				break;
			}
		}
	}
	
	private String inputID() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print("검색 대상의 아이디 : ");
		String userID = sc.next();
		return userID;
	}

	private Member inputMember() {
		Scanner sc = new Scanner(System.in);
		Member member = new Member();
		System.out.println("====== 회원 정보 입력 ======");
		System.out.print("아이디 : ");
		member.setMemberID(sc.next());
		System.out.print("비밀번호 : ");
		member.setMemberPW(sc.next());
		System.out.print("이름 : ");
		member.setMemberName(sc.next());
		System.out.print("성별 : ");
		member.setGender(sc.next());
		System.out.print("나이 : ");
		member.setAge(sc.nextInt());
		System.out.print("이메일 : ");
		member.setEmail(sc.next());
		System.out.print("전화번호 : ");
		member.setPhone(sc.next());
		sc.nextLine();
		System.out.print("주소 : ");
		member.setAddress(sc.nextLine());
		System.out.print("취미 : ");
		member.setHobby(sc.nextLine());
		
		return member;
	}

	private int printMainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 회원 관리 프로그램 ======");
		System.out.println("1. 회원가입");
		System.out.println("2. 회원 전체 조회");
		System.out.println("3. 회원 검색(아이디로 조회)");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int value = sc.nextInt();
		return value;
	}
	
	private void displayMessage(String msg) {
		System.out.println(msg);
	}
	
	public void displayMemberList(List<Member> mList) {
		System.out.println("====== 회원 정보 전체 출력 ======");
		for(Member member : mList) {
			System.out.printf("아이디 : %s\n", member.getMemberID());
			System.out.printf("비밀번호 : %s\n", member.getMemberPW());
			System.out.printf("이름 : %s\n", member.getMemberName());
			System.out.printf("성별 : %s\n", member.getGender());
			System.out.printf("나이 : %d\n", member.getAge());
			System.out.printf("이메일 : %s\n", member.getEmail());
			System.out.printf("전화번호 : %s\n", member.getPhone());
			System.out.printf("주소 : %s\n", member.getAddress());
			System.out.printf("취미 : %s\n", member.getHobby());
			System.out.println("================================");
		}
	}
}
