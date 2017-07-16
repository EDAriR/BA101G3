package com.shopping.model;

import java.sql.Timestamp;

public class Buyer_ReportVO  implements java.io.Serializable{
	private String ord_no; // Not Null (PK) (FK)
	private String mem_no; // Not Null (PK) (FK)
	private Timestamp buyrpt_date; // Not Null
	private String buyrpt_rsn; // Not Null
	private String buyrpt_is_cert;// Not Null 0¡G«Ý¼f®Ö 1¡G§_ 2¡G¬O
	private String buyrpt_unrsn;


	
	public Buyer_ReportVO() {
		super();
	}



	public String getOrd_no() {
		return ord_no;
	}



	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
	}



	public String getMem_no() {
		return mem_no;
	}



	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}



	public Timestamp getBuyrpt_date() {
		return buyrpt_date;
	}



	public void setBuyrpt_date(Timestamp buyrpt_date) {
		this.buyrpt_date = buyrpt_date;
	}



	public String getBuyrpt_rsn() {
		return buyrpt_rsn;
	}



	public void setBuyrpt_rsn(String buyrpt_rsn) {
		this.buyrpt_rsn = buyrpt_rsn;
	}



	public String getBuyrpt_is_cert() {
		return buyrpt_is_cert;
	}



	public void setBuyrpt_is_cert(String buyrpt_is_cert) {
		this.buyrpt_is_cert = buyrpt_is_cert;
	}



	public String getBuyrpt_unrsn() {
		return buyrpt_unrsn;
	}



	public void setBuyrpt_unrsn(String buyrpt_unrsn) {
		this.buyrpt_unrsn = buyrpt_unrsn;
	}

}
