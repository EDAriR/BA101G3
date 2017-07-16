package com.shopping.model;

import java.sql.Timestamp;

public class ProductVO  implements java.io.Serializable{
	private String pro_no; // Not Null (PK)
	private String mem_no; // Not Null (FK)
	private String proc_no; // Not Null (FK)
	private Timestamp pro_date; // Not Null
	private String pro_name; // Not Null
	private Integer pro_price; // Not Null
	private String pro_intro; // Not Null
	private byte[] pro_photo; // Not Null
	private byte[] pro_photo1;
	private byte[] pro_photo2;
	private byte[] pro_photo3;
	private byte[] pro_photo4;
	private byte[] pro_photo5;
	private String pro_stat; // Not Null 0：上架中 1：下架 2：完成交易未付款 3：完成交易已付款
	private String pro_pay	; // Not Null 0：貨到付款 1：ATM轉帳 2：兩者皆可
	private String pro_get	; // Not Null 0：便利商店取貨 1：快遞送貨 2：兩者皆可

	
	public ProductVO() {
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


	public String getProc_no() {
		return proc_no;
	}


	public void setProc_no(String proc_no) {
		this.proc_no = proc_no;
	}


	public Timestamp getPro_date() {
		return pro_date;
	}


	public void setPro_date(Timestamp pro_date) {
		this.pro_date = pro_date;
	}


	public String getPro_name() {
		return pro_name;
	}


	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}


	public Integer getPro_price() {
		return pro_price;
	}


	public void setPro_price(Integer pro_price) {
		this.pro_price = pro_price;
	}


	public String getPro_intro() {
		return pro_intro;
	}


	public void setPro_intro(String pro_intro) {
		this.pro_intro = pro_intro;
	}


	public byte[] getPro_photo() {
		return pro_photo;
	}


	public void setPro_photo(byte[] pro_photo) {
		this.pro_photo = pro_photo;
	}


	public byte[] getPro_photo1() {
		return pro_photo1;
	}


	public void setPro_photo1(byte[] pro_photo1) {
		this.pro_photo1 = pro_photo1;
	}


	public byte[] getPro_photo2() {
		return pro_photo2;
	}


	public void setPro_photo2(byte[] pro_photo2) {
		this.pro_photo2 = pro_photo2;
	}


	public byte[] getPro_photo3() {
		return pro_photo3;
	}


	public void setPro_photo3(byte[] pro_photo3) {
		this.pro_photo3 = pro_photo3;
	}


	public byte[] getPro_photo4() {
		return pro_photo4;
	}


	public void setPro_photo4(byte[] pro_photo4) {
		this.pro_photo4 = pro_photo4;
	}


	public byte[] getPro_photo5() {
		return pro_photo5;
	}


	public void setPro_photo5(byte[] pro_photo5) {
		this.pro_photo5 = pro_photo5;
	}


	public String getPro_stat() {
		return pro_stat;
	}


	public void setPro_stat(String pro_stat) {
		this.pro_stat = pro_stat;
	}


	public String getPro_pay() {
		return pro_pay;
	}


	public void setPro_pay(String pro_pay) {
		this.pro_pay = pro_pay;
	}


	public String getPro_get() {
		return pro_get;
	}


	public void setPro_get(String pro_get) {
		this.pro_get = pro_get;
	}

}
