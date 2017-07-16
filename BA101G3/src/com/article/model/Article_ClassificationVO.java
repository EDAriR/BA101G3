package com.article.model;

public class Article_ClassificationVO  implements java.io.Serializable{
	private String artc_no; // Not Null (PK)
	private String artc_name; // Not Null
	
	public Article_ClassificationVO() {
		super();
	}

	public String getArtc_no() {
		return artc_no;
	}

	public void setArtc_no(String artc_no) {
		this.artc_no = artc_no;
	}

	public String getArtc_name() {
		return artc_name;
	}

	public void setArtc_name(String artc_name) {
		this.artc_name = artc_name;
	}


	
}
