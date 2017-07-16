package com.baby.model;

import java.io.Serializable;

public class Growing_IntervalVO implements Serializable{

	private String gint_no; // Not Null (PK)
	private Integer gint_int; // Not Null
	
	public Growing_IntervalVO() {
		super();
	}
	public String getGint_no() {
		return gint_no;
	}
	public void setGint_no(String gint_no) {
		this.gint_no = gint_no;
	}
	public Integer getGint_int() {
		return gint_int;
	}
	public void setGint_int(Integer gint_int) {
		this.gint_int = gint_int;
	}
	
}
