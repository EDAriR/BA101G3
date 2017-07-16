package com.member.model;

import java.io.Serializable;

public class Expert_ClassificationVO implements Serializable{
	
	private String exp_no;// Not Null (PK)
	private String exp_cname;// Not Null
	
	public Expert_ClassificationVO(){
	}
	
	public Expert_ClassificationVO(String exp_no, String exp_cname){
		this.exp_no = exp_no;
		this.exp_cname = exp_cname;
	}

	public String getExp_no() {
		return exp_no;
	}

	public void setExp_no(String exp_no) {
		this.exp_no = exp_no;
	}

	public String getExp_cname() {
		return exp_cname;
	}

	public void setExp_cname(String exp_cname) {
		this.exp_cname = exp_cname;
	}
	
	
}
