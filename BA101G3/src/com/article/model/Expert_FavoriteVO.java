package com.article.model;

public class Expert_FavoriteVO  implements java.io.Serializable{
	private String mem_no_s;   // Not Null (PK) (FK)
	private String mem_no_e;   // Not Null (PK) (FK)
	
	public Expert_FavoriteVO() {
		super();
	}

	public String getMem_no_s() {
		return mem_no_s;
	}

	public void setMem_no_s(String mem_no_s) {
		this.mem_no_s = mem_no_s;
	}

	public String getMem_no_e() {
		return mem_no_e;
	}

	public void setMem_no_e(String mem_no_e) {
		this.mem_no_e = mem_no_e;
	}




}
