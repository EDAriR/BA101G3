package com.shopping.model;


public class Seller_RatingVO  implements java.io.Serializable{
	private String pro_no; // Not Null (PK) (FK)
	private String mem_no; // Not Null (PK) (FK)
	private Integer sell_rating; // Not Null 1-5¤À
	
	public Seller_RatingVO() {
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

	public Integer getSell_rating() {
		return sell_rating;
	}

	public void setSell_rating(Integer sell_rating) {
		this.sell_rating = sell_rating;
	}


}
