package com.question.model;

import java.sql.Timestamp;

public class Answer_ReportVO implements java.io.Serializable{
	private String ans_no;    // Not Null (PK) (FK)
	private String mem_no;    // Not Null (PK) (FK)
	private Timestamp ansrpt_date; // Not Null
	private String ansrpt_rsn; // Not Null
	private String ansrpt_is_cert; // Not Null
	private String ansrpt_unrsn;
	
	public Answer_ReportVO() {
		super();
	}

	public String getAns_no() {
		return ans_no;
	}

	public void setAns_no(String ans_no) {
		this.ans_no = ans_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public Timestamp getAnsrpt_date() {
		return ansrpt_date;
	}

	public void setAnsrpt_date(Timestamp ansrpt_date) {
		this.ansrpt_date = ansrpt_date;
	}

	public String getAnsrpt_rsn() {
		return ansrpt_rsn;
	}

	public void setAnsrpt_rsn(String ansrpt_rsn) {
		this.ansrpt_rsn = ansrpt_rsn;
	}

	public String getAnsrpt_is_cert() {
		return ansrpt_is_cert;
	}

	public void setAnsrpt_is_cert(String ansrpt_is_cert) {
		this.ansrpt_is_cert = ansrpt_is_cert;
	}

	public String getAnsrpt_unrsn() {
		return ansrpt_unrsn;
	}

	public void setAnsrpt_unrsn(String ansrpt_unrsn) {
		this.ansrpt_unrsn = ansrpt_unrsn;
	}

	
}
