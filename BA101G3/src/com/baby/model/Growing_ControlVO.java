package com.baby.model;

import java.io.Serializable;

public class Growing_ControlVO implements Serializable{
	
	private String ctrl_gen; // NOT NULL (PK) 0:男性 1:女性
	private String gint_no; // NOT NULL (PK) (FK)
	private Double ctrl_hc; // NOT NULL
	private Double ctrl_ht; // NOT NULL
	private Double ctrl_wt; // NOT NULL
	private Double ctrl_diet;
	private Double ctrl_sleep; // NOT NULL
	
	public Growing_ControlVO() {
		super();
	}

	public String getCtrl_gen() {
		return ctrl_gen;
	}

	public void setCtrl_gen(String ctrl_gen) {
		this.ctrl_gen = ctrl_gen;
	}

	public String getGint_no() {
		return gint_no;
	}

	public void setGint_no(String gint_no) {
		this.gint_no = gint_no;
	}

	public Double getCtrl_hc() {
		return ctrl_hc;
	}

	public void setCtrl_hc(Double ctrl_hc) {
		this.ctrl_hc = ctrl_hc;
	}

	public Double getCtrl_ht() {
		return ctrl_ht;	
	}

	public void setCtrl_ht(Double ctrl_ht) {
		this.ctrl_ht = ctrl_ht;
	}

	public Double getCtrl_wt() {
		return ctrl_wt;
	}

	public void setCtrl_wt(Double ctrl_wt) {
		this.ctrl_wt = ctrl_wt;
	}

	public Double getCtrl_diet() {
		return ctrl_diet;
	}

	public void setCtrl_diet(Double ctrl_diet) {
		this.ctrl_diet = ctrl_diet;
	}

	public Double getCtrl_sleep() {
		return ctrl_sleep;
	}

	public void setCtrl_sleep(Double ctrl_sleep) {
		this.ctrl_sleep = ctrl_sleep;
	}
	
}
