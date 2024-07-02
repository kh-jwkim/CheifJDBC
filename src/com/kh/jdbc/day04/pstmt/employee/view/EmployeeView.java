package com.kh.jdbc.day04.pstmt.employee.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day04.pstmt.employee.controller.EmployeeController;
import com.kh.jdbc.day04.pstmt.employee.model.vo.Employee;

public class EmployeeView {
	
	EmployeeController empController;
	
	
	
	public EmployeeView() {
		super();
		empController = new EmployeeController();
	}

	public void startApp() {
		end:
		while(true) {
			int menu = mainMenu();
			switch (menu) {
			case 1 :
				List<Employee> eList = empController.printAllEmp();
				if (eList.size()!=0) {
					printColumn();
					for(Employee emp: eList) {
	//					System.out.println(emp.toString());
						printEmp(emp);
					}
				}
				break;
			case 2 :
				Employee newEmp = inputEmpInfo();
				int insertRes = empController.insertEmployee(newEmp);
				checkResult(insertRes, "사원 등록");
				break;
			case 3 :
				String modEmpId = inputEmpId();
				Employee modEmp = empController.selectOneById(modEmpId);
				if(modEmp != null) {
					printEmp(modEmp);
					modEmp = modifyEmpInfo(modEmp);
				}
				int modRes = empController.updateEmployee(modEmp);
				checkResult(modRes, "사원 정보 수정");
				break;
			case 4 :
				String empId = inputEmpId();
				int delRes = empController.deleteEmployee(empId);
				checkResult(delRes, "사원 삭제");
				break;
			case 0 :
				printMessage("프로그램이 종료되었습니다.");
				break end;
	
			default:
				break;
			}
		}
	}
	

	private Employee modifyEmpInfo(Employee employee) {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 사원 정보 수정 ======");
		System.out.print("이메일 : ");
		employee.setEMAIL(sc.next());
		System.out.print("전화번호 : ");
		employee.setPHONE(sc.next());
		System.out.print("부서코드 : ");
		employee.setDEPT_CODE(sc.next());
		System.out.print("급여 : ");
		employee.setSALARY(sc.nextInt());
		System.out.print("보너스율 : ");
		employee.setBONUS(sc.nextDouble());
		System.out.print("관리자 : ");
		employee.setMANAGER_ID(sc.next());
		return employee;
	}

	private void checkResult(int result, String category) {
		if(result>0) {
			System.out.println(category+" 성공!");
		}else {
			System.out.println(category+" 실패!");
		}
	}

	private String inputEmpId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("사번 입력 : ");
		return sc.next();
	}

	private Employee inputEmpInfo() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 사원 정보 등록 ======");
		System.out.print("사번 : ");
		String empId = sc.next();
		System.out.print("사원명 : ");
		String empName = sc.next();
		System.out.print("주민번호 : ");
		String empNo = sc.next();
		System.out.print("직급코드 : ");
		String jobCode = sc.next();
		System.out.print("급여등급 : ");
		String salLevel = sc.next();
		Employee emp = new Employee(empId, empName, empNo, jobCode, salLevel);
		return emp;
	}

	private void printColumn() {
		// TODO Auto-generated method stub
		System.out.printf("%s\t%s\t%s\t\t%s\t\t\t%s\t\t%s\t%s\t%s\t%s\t\t%s\t%s\t\n", "EMP_ID", "EMP_NAME", "EMP_NO", "EMAIL", "PHONE", "DEPT_CODE", "JOB_CODE", "SAL_LEVEL", "SALARY", "BONUS", "MANAGER_ID");
	}

	private void printEmp(Employee emp) {
//		System.out.printf("EMP_ID : %s\tEMP_NAME : %s\tEMP_NO : %s\tEMAIL : %s\tPHONE : %s\tDEPT_CODE : %s\tJOB_CODE : %s\tSAL_LEVEL : %s\tSALARY : %d\t BONUS : %f\tMANAGER_ID : %s\t\n", emp.getEMP_ID(), emp.getEMP_NAME(), emp.getEMP_NO(), emp.getEMAIL(), emp.getPHONE(), emp.getDEPT_CODE(), emp.getJOB_CODE(), emp.getSAL_LEVEL(), emp.getSALARY(), emp.getBONUS(), emp.getMANAGER_ID());
		System.out.printf("%s\t%s\t\t%s\t%s\t%-12s\t%s\t\t%s\t\t%s\t\t%d\t\t%.2f\t%s\t\n", emp.getEMP_ID(), emp.getEMP_NAME(), emp.getEMP_NO(), emp.getEMAIL(), emp.getPHONE(), emp.getDEPT_CODE(), emp.getJOB_CODE(), emp.getSAL_LEVEL(), emp.getSALARY(), emp.getBONUS(), emp.getMANAGER_ID());
	}

	private void printMessage(String msg) {
		System.out.println(msg);
	}

	private int mainMenu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("====== 사원 관리 프로그램 ======");
		System.out.println("1. 사원 전체 출력");
		System.out.println("2. 사원 정보 등록");
		System.out.println("3. 사원 정보 수정");
		System.out.println("4. 사원 정보 삭제");
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		return choice;
	}

}
