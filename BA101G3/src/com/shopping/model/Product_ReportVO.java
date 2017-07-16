package com.shopping.model;

import java.sql.Timestamp;

public class Product_ReportVO  implements java.io.Serializable{
	private String pro_no; // Not Null (PK) (FK)
	private String mem_no; // Not Null (PK) (FK)
	private Timestamp prorpt_date; // Not Null
	private String prorpt_rsn; // Not Null
	private String prorpt_is_cert;// Not Null 0¡G«Ý¼f®Ö 1¡G§_ 2¡G¬O
	private String prorpt_unrsn;


	
	public Product_ReportVO() {
		super();
	}



	public String getPro_no() {
		return pro_no;
	}



	public void setPro_no(String pro_no) {
		this.pro_no = pro_no;
	}



	public String getMem_no() {
		return mem_no;
	}



	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}



	public Timestamp getProrpt_date() {
		return prorpt_date;
	}



	public void setProrpt_date(Timestamp prorpt_date) {
		this.prorpt_date = prorpt_date;
	}



	public String getProrpt_rsn() {
		return prorpt_rsn;
	}



	public void setProrpt_rsn(String prorpt_rsn) {
		this.prorpt_rsn = prorpt_rsn;
	}



	public String getProrpt_is_cert() {
		return prorpt_is_cert;
	}



	public void setProrpt_is_cert(String prorpt_is_cert) {
		this.prorpt_is_cert = prorpt_is_cert;
	}



	public String getProrpt_unrsn() {
		return prorpt_unrsn;
	}



	public void setProrpt_unrsn(String prorpt_unrsn) {
		this.prorpt_unrsn = prorpt_unrsn;
	}



}
