package com.article.model;

import java.sql.Timestamp;

public class ArticleVO  implements java.io.Serializable{
	private String art_no;      // Not Null (PK)
	private String mem_no;      // Not Null (FK)
	private String artc_no;     // Not Null (FK)
	private String art_title; // Not Null
	private Timestamp art_date; // Not Null
	private String art_cnt;     // Not Null
	private Integer art_vcnt;   // Not Null
	
	
	public ArticleVO() {
		super();
	}


	public String getArt_no() {
		return art_no;
	}


	public void setArt_no(String art_no) {
		this.art_no = art_no;
	}


	public String getMem_no() {
		return mem_no;
	}


	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}


	public String getArtc_no() {
		return artc_no;
	}


	public void setArtc_no(String artc_no) {
		this.artc_no = artc_no;
	}


	public String getArt_title() {
		return art_title;
	}


	public void setArt_title(String art_title) {
		this.art_title = art_title;
	}


	public Timestamp getArt_date() {
		return art_date;
	}


	public void setArt_date(Timestamp art_date) {
		this.art_date = art_date;
	}


	public String getArt_cnt() {
		return art_cnt;
	}


	public void setArt_cnt(String art_cnt) {
		this.art_cnt = art_cnt;
	}


	public Integer getArt_vcnt() {
		return art_vcnt;
	}


	public void setArt_vcnt(Integer art_vcnt) {
		this.art_vcnt = art_vcnt;
	}





	
}
