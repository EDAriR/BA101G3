package com.shopping.model;

import java.sql.Timestamp;

public class OrderlistVO  implements java.io.Serializable{
	private String ord_no; // Not Null (PK)
	private String pro_no; // Not Null (FK)
	private String mem_no; // Not Null (FK)
	private Integer ord_amt; // Not Null
	private Timestamp ord_startd; // Not Null
	private Timestamp ord_endd; 
	private Timestamp ord_payd;
	private String ord_getadd; // Not Null 


	
	public OrderlistVO() {
		super();
	}



	public String getOrd_no() {
		return ord_no;
	}



	public void setOrd_no(String ord_no) {
		this.ord_no = ord_no;
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



	public Integer getOrd_amt() {
		return ord_amt;
	}



	public void setOrd_amt(Integer ord_amt) {
		this.ord_amt = ord_amt;
	}



	public Timestamp getOrd_startd() {
		return ord_startd;
	}



	public void setOrd_startd(Timestamp ord_startd) {
		this.ord_startd = ord_startd;
	}



	public Timestamp getOrd_endd() {
		return ord_endd;
	}



	public void setOrd_endd(Timestamp ord_endd) {
		this.ord_endd = ord_endd;
	}



	public Timestamp getOrd_payd() {
		return ord_payd;
	}



	public void setOrd_payd(Timestamp ord_payd) {
		this.ord_payd = ord_payd;
	}



	public String getOrd_getadd() {
		return ord_getadd;
	}



	public void setOrd_getadd(String ord_getadd) {
		this.ord_getadd = ord_getadd;
	}

}
