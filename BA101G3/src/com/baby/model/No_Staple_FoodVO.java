package com.baby.model;

import java.io.Serializable;

public class No_Staple_FoodVO implements Serializable{

	private String nf_no; // NOT NULL (PK)
	private String gint_no; // NOT NULL (FK)
	private String nf_title; // NOT NULL
	private String nf_cnt; // NOT NULL
	
	public No_Staple_FoodVO() {
		super();
	}
	public String getNf_no() {
		return nf_no;
	}
	public void setNf_no(String nf_no) {
		this.nf_no = nf_no;
	}
	public String getGint_no() {
		return gint_no;
	}
	public void setGint_no(String gint_no) {
		this.gint_no = gint_no;
	}
	public String getNf_title() {
		return nf_title;
	}
	public void setNf_title(String nf_title) {
		this.nf_title = nf_title;
	}
	public String getNf_cnt() {
		return nf_cnt;
	}
	public void setNf_cnt(String nf_cnt) {
		this.nf_cnt = nf_cnt;
	}
	
}
