package com.shopping.model;

import java.sql.Timestamp;

public class Product_MessageVO  implements java.io.Serializable{
	private String pmsg_no; // Not Null (PK) (FK)
	private String pro_no; // Not Null (FK)
	private String mem_no; // Not Null (FK)
	private Timestamp pmsg_date; // Not Null
	private String pmsg_cnt; // Not Null
	private String pmsg_is_hide;// Not Null 0¡G§_ 1¡G¬O


	
	public Product_MessageVO() {
		super();
	}



	public String getPmsg_no() {
		return pmsg_no;
	}



	public void setPmsg_no(String pmsg_no) {
		this.pmsg_no = pmsg_no;
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



	public Timestamp getPmsg_date() {
		return pmsg_date;
	}



	public void setPmsg_date(Timestamp pmsg_date) {
		this.pmsg_date = pmsg_date;
	}



	public String getPmsg_cnt() {
		return pmsg_cnt;
	}



	public void setPmsg_cnt(String pmsg_cnt) {
		this.pmsg_cnt = pmsg_cnt;
	}



	public String getPmsg_is_hide() {
		return pmsg_is_hide;
	}



	public void setPmsg_is_hide(String pmsg_is_hide) {
		this.pmsg_is_hide = pmsg_is_hide;
	}

}
