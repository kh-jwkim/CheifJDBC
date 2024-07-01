package com.kh.jdbc.day03.pstmt.member.view;

import java.util.Scanner;

import com.kh.jdbc.day03.pstmt.member.controller.MemberController;
import com.kh.jdbc.day03.pstmt.member.model.vo.Member;

public class MemberView {
	MemberController mController;
	
	

	public MemberView() {
		mController = new MemberController();
	}

	public void startProgram() {
		finish :
		while(true) {
			int choice = this.mainMenu();
			switch (choice) {
			case 1 : 
				//회원정보를 입력받은 후
				//입력받은 정보를 객체 저장한 후
				Member member = this.inputInfo();
				//객체를 컨트롤러로 전달
				int result = mController.registerMember(member);
				if(result > 0) {
					printMessage("회원가입 성공!");
				}else {
					printMessage("회원가입 실패!");
				}
				break;
			case 2 : 
				Member loginMember = this.inputLoginInfo();
				loginMember = mController.checkLogin(loginMember);
				if(loginMember != null) {
					printMessage("로그인 성공!");
					printOneMember(loginMember);
				} else {
					printMessage("로그인 실패!");
				}
				break;
			case 3 : 
				String modID = inputMemberId("변경");
				Member modMember = this.modInfo(modID);
				int modResult = mController.modifyMember(modMember);
				if(modResult > 0) {
					printMessage("변경 성공!");
				} else {
					printMessage("변경 실패!");
				}
				break;
			case 4 : 
				String delID = inputMemberId("삭제");
				int removeResult = mController.removeMember(delID);
				if(removeResult > 0) {
					printMessage("삭제 성공!");
				} else {
					printMessage("삭제 실패!");
				}
				break;
			case 5 : break;
			case 6 : break;
			case 9 : 
				printMessage("프로그램 종료");
				break finish;

			default:
				break;
			}
		}
	}
	
	private Member modInfo(String modID) {
		Scanner sc = new Scanner(System.in);
//		Member member = new Member();
		Member member = null;
		System.out.println("====== 변경할 정보 입력 ======");
		String memberID = modID;
		System.out.print("비밀번호 : ");
		String memberPW = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.nextLine();
		member = new Member(memberID, memberPW, email, phone, address, hobby);
//		member.setMemberID(modID);
//		System.out.print("비밀번호 : ");
//		member.setMemberPW(sc.next());
//		System.out.print("이름 : ");
//		member.setMemberName(sc.next());
//		System.out.print("이메일 : ");
//		member.setEmail(sc.next());
//		System.out.print("전화번호 : ");
//		member.setPhone(sc.next());
//		System.out.print("주소 : ");
//		sc.nextLine();
//		member.setAddress(sc.nextLine());
//		System.out.print("취미 : ");
//		member.setHobby(sc.nextLine());
		//리턴 여러개가 안되니까 객체 이용
		return member;
	}

	private String inputMemberId(String category) {
		Scanner sc = new Scanner(System.in);
		System.out.print(category+"할 계정 : ");
		String memID = sc.next();
		
		return memID;
	}

	private int mainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 회원 관리 프로그램 ======");
		System.out.println("1. 회원가입");
		System.out.println("2. 로 그 인");
		System.out.println("3. 정보수정");
		System.out.println("4. 회원탈퇴");
		System.out.println("5. ");
		System.out.println("6. ");
		System.out.println("9. 프로그램종료");
		System.out.print("메뉴 선택 : ");
		int input = sc.nextInt();
		// 호출하는 곳에서 쓰니까 return input;
		return input;
	}

	private void printOneMember(Member member) {
		System.out.printf("아 이 디 : %s\n", member.getMemberID());
		System.out.printf("비밀번호 : %s\n", member.getMemberPW());
		System.out.printf("이    름 : %s\n", member.getMemberName());
		System.out.printf("성    별 : %s\n", member.getGender());
		System.out.printf("나    이 : %d\n", member.getAge());
		System.out.printf("이 메 일 : %s\n", member.getEmail());
		System.out.printf("전화번호 : %s\n", member.getPhone());
		System.out.printf("주    소 : %s\n", member.getAddress());
		System.out.printf("취    미 : %s\n", member.getHobby());
		System.out.println("================================");
	}

	private Member inputLoginInfo() {
		Scanner sc = new Scanner(System.in);
		Member member = new Member();
		System.out.println("====== 로그인 정보 입력 ======");
		System.out.print("아이디 : ");
		member.setMemberID(sc.next());
		System.out.print("비밀번호 : ");
		member.setMemberPW(sc.next());
		
		return member;
	}

	private Member inputInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 회원 정보 입력 ======");
		System.out.print("아이디 : ");
		String memberID = sc.next();
		System.out.print("비밀번호 : ");
		String memberPW = sc.next();
		System.out.print("이름 : ");
		String memberName = sc.next();
		System.out.print("성별 : ");
		String gender = sc.next();
		System.out.print("나이 : ");
		int age = sc.nextInt();
		System.out.print("이메일 : ");
		String email = sc.next();
		System.out.print("전화번호 : ");
		String phone = sc.next();
		System.out.print("주소 : ");
		sc.nextLine();
		String address = sc.nextLine();
		System.out.print("취미 : ");
		String hobby = sc.nextLine();
		//리턴 여러개가 안되니까 객체 이용
		Member member = new Member(memberID, memberPW, memberName, gender, age, email, phone, address, hobby, null);
		return member;
	}

	private void printMessage(String msg) {
		System.out.println(msg);
	}

	
	

}
