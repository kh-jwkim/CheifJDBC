package com.kh.jdbc.day03.pstmt.imployee.view;

import java.util.List;
import java.util.Scanner;

import com.kh.jdbc.day03.pstmt.imployee.controller.EmployeeController;
import com.kh.jdbc.day03.pstmt.imployee.model.vo.Employee;

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
			case 0 :
				printMessage("프로그램이 종료되었습니다.");
				break end;
	
			default:
				break;
			}
		}
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
		System.out.println("0. 프로그램 종료");
		System.out.print("메뉴 선택 : ");
		int choice = sc.nextInt();
		return choice;
	}

}
