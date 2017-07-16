package com.article.model;

public class Article_FavoriteVO  implements java.io.Serializable{
	private String mem_no;   // Not Null (PK) (FK)
	private String art_no; // Not Null (PK) (FK)
	
	public Article_FavoriteVO() {
		super();
	}

	public String getMem_no() {
		return mem_no;
	}

	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}

	public String getArt_no() {
		return art_no;
	}

	public void setArt_no(String art_no) {
		this.art_no = art_no;
	}


}
