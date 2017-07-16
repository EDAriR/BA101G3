package com.question.model;

import java.sql.Clob;
import java.sql.Timestamp;

import oracle.sql.CLOB;
import oracle.sql.DATE;



public class QuestionVO implements java.io.Serializable{
	
	private String qu_no;           //問題編號 NOT NULL (PK)
	private String mem_no;          //會員編號(提問者) NOT NULL (FK)
	private String quec_no;         //問題分類編號 NOT NULL (FK)
	private String qu_title;        //問題主旨 NOT NULL
	private Timestamp qu_date;      //問題詢問時間 NOT NULL
	private String qu_cnt;          //問題內容 NOT NULL
	

	public QuestionVO(){super();}  //空的建構子
	
	
	public String getQu_no() {
		return qu_no;
	}
	public void setQu_no(String qu_no) {
		this.qu_no = qu_no;
	}
	public String getMem_no() {
		return mem_no;
	}
	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}
	public String getQuec_no() {
		return quec_no;
	}
	public void setQuec_no(String quec_no) {
		this.quec_no = quec_no;
	}
	public String getQu_title() {
		return qu_title;
	}
	public void setQu_title(String qu_title) {
		this.qu_title = qu_title;
	}
	public Timestamp getQu_date() {
		return qu_date;
	}
	public void setQu_date(Timestamp qu_date) {
		this.qu_date = qu_date;
	}
	public String getQu_cnt() {
		return qu_cnt;
	}
	public void setQu_cnt(String qu_cnt) {
		this.qu_cnt =  qu_cnt;
	}
	
}

