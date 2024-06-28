package com.kh.jdbc.day02.stmt.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day02.stmt.member.controller.MemberController;
import com.kh.jdbc.day02.stmt.member.model.vo.Member;

public class MemberView {
	//View 클래스에서 계속 쓸거니 인스턴스의 참조변수를 필드에 둔다
	MemberController mController;

	public MemberView() {
		// 생성자에서 초기화(인스턴스 생성)
		mController= new MemberController();
	}
	
	public void startProgram() {
		System.out.println("호출완료");
//		mController.insertMember();
		int choice = 0;
		int res = 0;
		end:
		while(true) {
			choice = this.printMainMenu();
			switch (choice) {
			case 0: break end;
			case 1:
				//1을 눌렀다면 회원의 정보를 입력 받아야 함
				Member newMember = this.inputMember(); //inputMember()를 실행시켜 정보 받기
				//ID 부터 취미까지 저장된 member 객체를 컨트롤러로 전달
				res = mController.insertMember(newMember);
				this.resCheck("입력", res);
				break;
			case 2:
				//2를 눌렀다면 회원의 전체 정보를 출력해야함.
				//1. DB에서 데이터 가져오기, 전체 회원 정보니까 여러개, 여러개니까 리스트 List, 멤버니까 List<Member>
				List<Member> mList = mController.printAllMember();
				this.printAllMembers(mList);
				break;
			case 3:
				String searchID = this.inputID("검색");
				Member selectMember = mController.searchMember(searchID);
				this.displayMember(selectMember);
				break;
				
			case 4:
				//4를 눌렀다면 회원의 정보를 수정해야 함(아이디로 정보가 존재하는지 확인 후 있으면 수정 없으면 안함)
				//사용자가 수정할 아이디 입력받아야 되니까 inputID();
				String modifyID = this.inputID("변경");
				if(mController.searchMember(modifyID) != null) {
					Member modMember = this.modifyMember();
					res = mController.modifyMember(modifyID, modMember);
					this.resCheck("입력", res);
				}else {
					this.displayMessage("존재하지 않는 정보입니다.");
				}
				break;
				
			case 5:
				//5를 눌렀다면 회원의 정보를 삭제해야함(아이디로 삭제)
				//사용자가 삭제할 아이디 입력받아야 되니까 inputID()
				String delID = this.inputID("삭제");
				//입력받은 아이디로 DB에서 삭제 해야되니까 removeMember();
				//컨트롤러로 전달해야되니까 removeMember(memberId);
				//DML의 결과는 int니까 int result
				res = mController.removeMember(delID);
				this.resCheck("입력", res);
				break;
				
			case 6:
				//6을 눌렀다면 아이디와 비밀번호를 입력받아 일치 여부 출력
				//사용자가 로그인할 아이디 입력받아야 되니까 inputID()
				String loginID = this.inputID("로그인");
				
			default: this.displayMessage("메뉴를 다시 선택해주세요.");
				break;
			}
		}
	}
	
	
	private Member modifyMember() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Member member = new Member();
		System.out.println("====== 변경할 정보 입력 ======");
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

	private void displayMember(Member member) {
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

	private String inputID(String category) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.print(category+"할 ID : ");
		String userID = sc.next();
		return userID;
	}

	private void printAllMembers(List<Member> mList) {
		// TODO Auto-generated method stub
		for(Member member : mList) {
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
	}

	private void displayMessage(String msg) {
		// TODO Auto-generated method stub
		System.out.println(msg);
	}

	private Member inputMember() {
		// TODO Auto-generated method stub
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

	public int printMainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 회원 관리 프로그램 ======");
		System.out.println("1. 회원 가입");
		System.out.println("2. 회원 전체 조회");
		System.out.println("3. 회원 검색(아이디로 조회)");
		System.out.println("4. 회원 정보 수정");
		System.out.println("5. 회원 탈퇴");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		return choice;
	}
	
	private void resCheck(String category, int res) {
		if(res > 0) {
			this.displayMessage(category + " 성공!");
		}else {
			this.displayMessage(category + " 실패!");
		}
	}
}
