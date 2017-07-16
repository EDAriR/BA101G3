package com.article.model;

import java.sql.Timestamp;

public class Article_Message_ReportVO implements java.io.Serializable{
	private String amsg_no;    // Not Null (PK) (FK)
	private String mem_no;    // Not Null (PK) (FK)
	private Timestamp amrpt_date; // Not Null
	private String amrpt_rsn; // Not Null
	private String amrpt_is_cert; // Not Null 0:«Ý¼f®Ö 1:§_ 2:¬O
	private String amrpt_unrsn;
	
	public Article_Message_ReportVO() {
		super();
	}

	public String getAmsg_no() {
		return amsg_no;
	}

	public void setAmsg_no(String amsg_no) {
		this.amsg_no = amsg_no;
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public Timestamp getAmrpt_date() {
		return amrpt_date;
	}

	public void setAmrpt_date(Timestamp amrpt_date) {
		this.amrpt_date = amrpt_date;
	}

	public String getAmrpt_rsn() {
		return amrpt_rsn;
	}

	public void setAmrpt_rsn(String amrpt_rsn) {
		this.amrpt_rsn = amrpt_rsn;
	}

	public String getAmrpt_is_cert() {
		return amrpt_is_cert;
	}

	public void setAmrpt_is_cert(String amrpt_is_cert) {
		this.amrpt_is_cert = amrpt_is_cert;
	}

	public String getAmrpt_unrsn() {
		return amrpt_unrsn;
	}

	public void setAmrpt_unrsn(String amrpt_unrsn) {
		this.amrpt_unrsn = amrpt_unrsn;
	}


}
