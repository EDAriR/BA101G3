package com.question.model;

import java.sql.Timestamp;

public class AnswerVO implements java.io.Serializable {
	private String ans_no; // Not Null (PK)
	private String mem_no; // Not Null (FK)
	private String qu_no; // Not Null (FK)
	private Timestamp ans_date; // Not Null
	private String ans_cnt; // Not Null
	private Integer ans_like; // Not Null
	private String ans_is_hide; // Not Null

	public AnswerVO() {
		super();
	}
	
	public String getAns_is_hide() {
		return ans_is_hide;
	}

	public void setAns_is_hide(String ans_is_hide) {
		this.ans_is_hide = ans_is_hide;
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

	public String getQu_no() {
		return qu_no;
	}

	public void setQu_no(String qu_no) {
		this.qu_no = qu_no;
	}

	public Timestamp getAns_date() {
		return ans_date;
	}

	public void setAns_date(Timestamp ans_date) {
		this.ans_date = ans_date;
	}

	public String getAns_cnt() {
		return ans_cnt;
	}

	public void setAns_cnt(String ans_cnt) {
		this.ans_cnt = ans_cnt;
	}

	public Integer getAns_like() {
		return ans_like;
	}

	public void setAns_like(Integer ans_like) {
		this.ans_like = ans_like;
	}

}
