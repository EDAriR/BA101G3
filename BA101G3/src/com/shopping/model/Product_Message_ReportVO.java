package com.shopping.model;

import java.sql.Timestamp;

public class Product_Message_ReportVO  implements java.io.Serializable{
	private String pmsg_no; // Not Null (PK) (FK)
	private String mem_no; // Not Null (PK) (FK)
	private Timestamp pmrpt_date; // Not Null
	private String pmrpt_rsn; // Not Null
	private String pmrpt_is_cert;// Not Null 0¡G«Ý¼f®Ö 1¡G§_ 2¡G¬O
	private String pmrpt_unrsn;


	
	public Product_Message_ReportVO() {
		super();
	}



	public String getPmsg_no() {
		return pmsg_no;
	}



	public void setPmsg_no(String pmsg_no) {
		this.pmsg_no = pmsg_no;
	}



	public String getMem_no() {
		return mem_no;
	}



	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}



	public Timestamp getPmrpt_date() {
		return pmrpt_date;
	}



	public void setPmrpt_date(Timestamp pmrpt_date) {
		this.pmrpt_date = pmrpt_date;
	}



	public String getPmrpt_rsn() {
		return pmrpt_rsn;
	}



	public void setPmrpt_rsn(String pmrpt_rsn) {
		this.pmrpt_rsn = pmrpt_rsn;
	}



	public String getPmrpt_is_cert() {
		return pmrpt_is_cert;
	}



	public void setPmrpt_is_cert(String pmrpt_is_cert) {
		this.pmrpt_is_cert = pmrpt_is_cert;
	}



	public String getPmrpt_unrsn() {
		return pmrpt_unrsn;
	}



	public void setPmrpt_unrsn(String pmrpt_unrsn) {
		this.pmrpt_unrsn = pmrpt_unrsn;
	}

}
