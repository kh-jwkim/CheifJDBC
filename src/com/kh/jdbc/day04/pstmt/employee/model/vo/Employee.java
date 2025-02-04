package com.kh.jdbc.day04.pstmt.employee.model.vo;

import java.sql.Date;

public class Employee {
	private String EMP_ID;
	private String EMP_NAME;
	private String EMP_NO;
	private String EMAIL;
	private String PHONE;
	private String DEPT_CODE;
	private String JOB_CODE;
	private String SAL_LEVEL;
	private int SALARY;
	private double BONUS;
	private String MANAGER_ID;
	private Date HIRE_DATE;
	private Date ENT_DATE;
	private String ENT_YN;
	
	
	public Employee() {
		super();
	}
	
	
	public Employee(String eMP_ID, String eMP_NAME, String eMP_NO, String jOB_CODE, String sAL_LEVEL) {
		super();
		EMP_ID = eMP_ID;
		EMP_NAME = eMP_NAME;
		EMP_NO = eMP_NO;
		JOB_CODE = jOB_CODE;
		SAL_LEVEL = sAL_LEVEL;
	}


	public Employee(String eMP_ID, String eMP_NAME, String eMP_NO, String eMAIL, String pHONE, String dEPT_CODE,
			String jOB_CODE, String sAL_LEVEL, int sALARY, double bONUS, String mANAGER_ID, Date hIRE_DATE,
			Date eNT_DATE, String eNT_YN) {
		super();
		EMP_ID = eMP_ID;
		EMP_NAME = eMP_NAME;
		EMP_NO = eMP_NO;
		EMAIL = eMAIL;
		PHONE = pHONE;
		DEPT_CODE = dEPT_CODE;
		JOB_CODE = jOB_CODE;
		SAL_LEVEL = sAL_LEVEL;
		SALARY = sALARY;
		BONUS = bONUS;
		MANAGER_ID = mANAGER_ID;
		HIRE_DATE = hIRE_DATE;
		ENT_DATE = eNT_DATE;
		ENT_YN = eNT_YN;
	}
	
	@Override
	public String toString() {
		return "Employee [EMP_ID=" + EMP_ID + ", EMP_NAME=" + EMP_NAME + ", EMP_NO=" + EMP_NO + ", EMAIL=" + EMAIL
				+ ", PHONE=" + PHONE + ", DEPT_CODE=" + DEPT_CODE + ", JOB_CODE=" + JOB_CODE + ", SAL_LEVEL="
				+ SAL_LEVEL + ", SALARY=" + SALARY + ", BONUS=" + BONUS + ", MANAGER_ID=" + MANAGER_ID + ", HIRE_DATE="
				+ HIRE_DATE + ", ENT_DATE=" + ENT_DATE + ", ENT_YN=" + ENT_YN + "]";
	}

	public String getEMP_ID() {
		return EMP_ID;
	}
	public void setEMP_ID(String eMP_ID) {
		EMP_ID = eMP_ID;
	}
	public String getEMP_NAME() {
		return EMP_NAME;
	}
	public void setEMP_NAME(String eMP_NAME) {
		EMP_NAME = eMP_NAME;
	}
	public String getEMP_NO() {
		return EMP_NO;
	}
	public void setEMP_NO(String eMP_NO) {
		EMP_NO = eMP_NO;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public String getDEPT_CODE() {
		return DEPT_CODE;
	}
	public void setDEPT_CODE(String dEPT_CODE) {
		DEPT_CODE = dEPT_CODE;
	}
	public String getJOB_CODE() {
		return JOB_CODE;
	}
	public void setJOB_CODE(String jOB_CODE) {
		JOB_CODE = jOB_CODE;
	}
	public String getSAL_LEVEL() {
		return SAL_LEVEL;
	}
	public void setSAL_LEVEL(String sAL_LEVEL) {
		SAL_LEVEL = sAL_LEVEL;
	}
	public int getSALARY() {
		return SALARY;
	}
	public void setSALARY(int sALARY) {
		SALARY = sALARY;
	}
	public double getBONUS() {
		return BONUS;
	}
	public void setBONUS(double bONUS) {
		BONUS = bONUS;
	}
	public String getMANAGER_ID() {
		return MANAGER_ID;
	}
	public void setMANAGER_ID(String mANAGER_ID) {
		MANAGER_ID = mANAGER_ID;
	}
	public Date getHIRE_DATE() {
		return HIRE_DATE;
	}
	public void setHIRE_DATE(Date hIRE_DATE) {
		HIRE_DATE = hIRE_DATE;
	}
	public Date getENT_DATE() {
		return ENT_DATE;
	}
	public void setENT_DATE(Date eNT_DATE) {
		ENT_DATE = eNT_DATE;
	}
	public String getENT_YN() {
		return ENT_YN;
	}
	public void setENT_YN(String eNT_YN) {
		ENT_YN = eNT_YN;
	}
	
	
}
