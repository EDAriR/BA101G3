package com.map.model;

import java.sql.Timestamp;

public class Map_CommentVO  implements java.io.Serializable{
	private String mcmt_no; // Not Null (PK)
	private String mem_no; // Not Null (FK)
	private String map_no; // Not Null (FK)
	private Timestamp mcmt_date; // Not Null
	private String mcmt_cnt; // Not Null

	
	public Map_CommentVO() {
		super();
	}


	public String getMcmt_no() {
		return mcmt_no;
	}


	public void setMcmt_no(String mcmt_no) {
		this.mcmt_no = mcmt_no;
	}


	public String getMem_no() {
		return mem_no;
	}


	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}


	public String getMap_no() {
		return map_no;
	}


	public void setMap_no(String map_no) {
		this.map_no = map_no;
	}


	public Timestamp getMcmt_date() {
		return mcmt_date;
	}


	public void setMcmt_date(Timestamp mcmt_date) {
		this.mcmt_date = mcmt_date;
	}


	public String getMcmt_cnt() {
		return mcmt_cnt;
	}


	public void setMcmt_cnt(String mcmt_cnt) {
		this.mcmt_cnt = mcmt_cnt;
	}

}
