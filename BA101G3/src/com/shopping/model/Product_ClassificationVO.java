package com.shopping.model;

public class Product_ClassificationVO  implements java.io.Serializable{
	private String proc_no; // Not Null (PK)
	private String proc_name; // Not Null
	
	public Product_ClassificationVO() {
		super();
	}

	public String getProc_no() {
		return proc_no;
	}

	public void setProc_no(String proc_no) {
		this.proc_no = proc_no;
	}

	public String getProc_name() {
		return proc_name;
	}

	public void setProc_name(String proc_name) {
		this.proc_name = proc_name;
	}
	
}
