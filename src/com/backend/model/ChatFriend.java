package com.backend.model;
// Generated 2017/7/13 �W�� 02:41:10 by Hibernate Tools 3.5.0.Final

/**
 * ChatFriend generated by hbm2java
 */
public class ChatFriend implements java.io.Serializable {

	private String cfNo;
	private Member memberByMemNoO;
	private Member memberByMemNoS;
	private String cfIsDel;

	public ChatFriend() {
	}

	public ChatFriend(String cfNo, Member memberByMemNoO, Member memberByMemNoS, String cfIsDel) {
		this.cfNo = cfNo;
		this.memberByMemNoO = memberByMemNoO;
		this.memberByMemNoS = memberByMemNoS;
		this.cfIsDel = cfIsDel;
	}

	public String getCfNo() {
		return this.cfNo;
	}

	public void setCfNo(String cfNo) {
		this.cfNo = cfNo;
	}

	public Member getMemberByMemNoO() {
		return this.memberByMemNoO;
	}

	public void setMemberByMemNoO(Member memberByMemNoO) {
		this.memberByMemNoO = memberByMemNoO;
	}

	public Member getMemberByMemNoS() {
		return this.memberByMemNoS;
	}

	public void setMemberByMemNoS(Member memberByMemNoS) {
		this.memberByMemNoS = memberByMemNoS;
	}

	public String getCfIsDel() {
		return this.cfIsDel;
	}

	public void setCfIsDel(String cfIsDel) {
		this.cfIsDel = cfIsDel;
	}

}
