package com.backend.model;
// Generated 2017/7/13 �W�� 02:41:10 by Hibernate Tools 3.5.0.Final

import java.io.Serializable;
import java.sql.Clob;

/**
 * Article generated by hbm2java
 */
public class Article implements java.io.Serializable {

	private String artNo;
	private Member member;
	private String artcNo;
	private String artTitle;
	private Serializable artDate;
	private Clob artCnt;
	private int artVcnt;

	public Article() {
	}

	public Article(String artNo, Member member, String artcNo, String artTitle, Serializable artDate, Clob artCnt,
			int artVcnt) {
		this.artNo = artNo;
		this.member = member;
		this.artcNo = artcNo;
		this.artTitle = artTitle;
		this.artDate = artDate;
		this.artCnt = artCnt;
		this.artVcnt = artVcnt;
	}

	public String getArtNo() {
		return this.artNo;
	}

	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public String getArtcNo() {
		return this.artcNo;
	}

	public void setArtcNo(String artcNo) {
		this.artcNo = artcNo;
	}

	public String getArtTitle() {
		return this.artTitle;
	}

	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}

	public Serializable getArtDate() {
		return this.artDate;
	}

	public void setArtDate(Serializable artDate) {
		this.artDate = artDate;
	}

	public Clob getArtCnt() {
		return this.artCnt;
	}

	public void setArtCnt(Clob artCnt) {
		this.artCnt = artCnt;
	}

	public int getArtVcnt() {
		return this.artVcnt;
	}

	public void setArtVcnt(int artVcnt) {
		this.artVcnt = artVcnt;
	}

}
