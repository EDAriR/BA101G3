package com.article.model;

import java.sql.Timestamp;

public class Article_MessageVO  implements java.io.Serializable{
	private String amsg_no;      // Not Null (PK)
	private String art_no;      // Not Null (FK)
	private String mem_no;       // Not Null (FK)
	private Timestamp amsg_date; // Not Null
	private String amsg_cnt;     // Not Null
	
	
	public Article_MessageVO() {
		super();
	}


	public String getAmsg_no() {
		return amsg_no;
	}


	public void setAmsg_no(String amsg_no) {
		this.amsg_no = amsg_no;
	}


	public String getArt_no() {
		return art_no;
	}


	public void setArt_no(String art_no) {
		this.art_no = art_no;
	}


	public String getMem_no() {
		return mem_no;
	}


	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}


	public Timestamp getAmsg_date() {
		return amsg_date;
	}


	public void setAmsg_date(Timestamp amsg_date) {
		this.amsg_date = amsg_date;
	}


	public String getAmsg_cnt() {
		return amsg_cnt;
	}


	public void setAmsg_cnt(String amsg_cnt) {
		this.amsg_cnt = amsg_cnt;
	}



	
}
