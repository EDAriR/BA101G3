package com.shopping.model;

import java.sql.Timestamp;

public class Seller_ReportVO  implements java.io.Serializable{
	private String pro_no; // Not Null (PK) (FK)
	private String mem_no; // Not Null (PK) (FK)
	private Timestamp sellrpt_date; // Not Null
	private String sellrpt_rsn; // Not Null
	private String sellrpt_is_cert;// Not Null 0¡G«Ý¼f®Ö 1¡G§_ 2¡G¬O
	private String sellrpt_unrsn;


	
	public Seller_ReportVO() {
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



	public Timestamp getSellrpt_date() {
		return sellrpt_date;
	}



	public void setSellrpt_date(Timestamp sellrpt_date) {
		this.sellrpt_date = sellrpt_date;
	}



	public String getSellrpt_rsn() {
		return sellrpt_rsn;
	}



	public void setSellrpt_rsn(String sellrpt_rsn) {
		this.sellrpt_rsn = sellrpt_rsn;
	}



	public String getSellrpt_is_cert() {
		return sellrpt_is_cert;
	}



	public void setSellrpt_is_cert(String sellrpt_is_cert) {
		this.sellrpt_is_cert = sellrpt_is_cert;
	}



	public String getSellrpt_unrsn() {
		return sellrpt_unrsn;
	}



	public void setSellrpt_unrsn(String sellrpt_unrsn) {
		this.sellrpt_unrsn = sellrpt_unrsn;
	}

}
