package com.map.model;


public class Map_MechanismVO  implements java.io.Serializable{
	private String map_no; // Not Null (PK)
	private String mem_no; // (FK)
	private String mapc_no; // 0：親子餐廳 1：嬰幼兒用品店 2：醫院、衛生所
	private String map_name; // Not Null
	private String map_adds; // Not Null
	private String map_addc; // Not Null
	private Double map_long; // Not Null
	private Double map_lat; // Not Null
	private String map_phone; // Not Null
	private String map_mail;
	private String map_cnt; // Not Null
	private byte[] map_photo; // Not Null
	private byte[] map_photo1;
	private byte[] map_photo2;
	private byte[] map_photo3;
	private byte[] map_photo4;
	private byte[] map_photo5;

	
	public Map_MechanismVO() {
		super();
	}


	public String getMap_no() {
		return map_no;
	}


	public void setMap_no(String map_no) {
		this.map_no = map_no;
	}


	public String getMem_no() {
		return mem_no;
	}


	public void setMem_no(String mem_no) {
		this.mem_no = mem_no;
	}


	public String getMapc_no() {
		return mapc_no;
	}


	public void setMapc_no(String mapc_no) {
		this.mapc_no = mapc_no;
	}


	public String getMap_name() {
		return map_name;
	}


	public void setMap_name(String map_name) {
		this.map_name = map_name;
	}


	public String getMap_adds() {
		return map_adds;
	}


	public void setMap_adds(String map_adds) {
		this.map_adds = map_adds;
	}


	public String getMap_addc() {
		return map_addc;
	}


	public void setMap_addc(String map_addc) {
		this.map_addc = map_addc;
	}


	public Double getMap_long() {
		return map_long;
	}


	public void setMap_long(Double map_long) {
		this.map_long = map_long;
	}


	public Double getMap_lat() {
		return map_lat;
	}


	public void setMap_lat(Double map_lat) {
		this.map_lat = map_lat;
	}


	public String getMap_phone() {
		return map_phone;
	}


	public void setMap_phone(String map_phone) {
		this.map_phone = map_phone;
	}


	public String getMap_mail() {
		return map_mail;
	}


	public void setMap_mail(String map_mail) {
		this.map_mail = map_mail;
	}


	public String getMap_cnt() {
		return map_cnt;
	}


	public void setMap_cnt(String map_cnt) {
		this.map_cnt = map_cnt;
	}


	public byte[] getMap_photo() {
		return map_photo;
	}


	public void setMap_photo(byte[] map_photo) {
		this.map_photo = map_photo;
	}


	public byte[] getMap_photo1() {
		return map_photo1;
	}


	public void setMap_photo1(byte[] map_photo1) {
		this.map_photo1 = map_photo1;
	}


	public byte[] getMap_photo2() {
		return map_photo2;
	}


	public void setMap_photo2(byte[] map_photo2) {
		this.map_photo2 = map_photo2;
	}


	public byte[] getMap_photo3() {
		return map_photo3;
	}


	public void setMap_photo3(byte[] map_photo3) {
		this.map_photo3 = map_photo3;
	}


	public byte[] getMap_photo4() {
		return map_photo4;
	}


	public void setMap_photo4(byte[] map_photo4) {
		this.map_photo4 = map_photo4;
	}


	public byte[] getMap_photo5() {
		return map_photo5;
	}


	public void setMap_photo5(byte[] map_photo5) {
		this.map_photo5 = map_photo5;
	}
	
}
