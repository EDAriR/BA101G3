package com.backend.model;
// Generated 2017/7/13 �W�� 02:41:10 by Hibernate Tools 3.5.0.Final

import java.io.Serializable;
import java.sql.Clob;
import java.util.HashSet;
import java.util.Set;

/**
 * Question generated by hbm2java
 */
public class Question implements java.io.Serializable {

	private String quNo;
	private Member member;
	private QuestionClassification questionClassification;
	private String quTitle;
	private Serializable quDate;
	private Clob quCnt;
	private Set<Answer> answers = new HashSet<Answer>(0);

	public Question() {
	}

	public Question(String quNo, Member member, QuestionClassification questionClassification, String quTitle,
			Serializable quDate, Clob quCnt) {
		this.quNo = quNo;
		this.member = member;
		this.questionClassification = questionClassification;
		this.quTitle = quTitle;
		this.quDate = quDate;
		this.quCnt = quCnt;
	}

	public Question(String quNo, Member member, QuestionClassification questionClassification, String quTitle,
			Serializable quDate, Clob quCnt, Set<Answer> answers) {
		this.quNo = quNo;
		this.member = member;
		this.questionClassification = questionClassification;
		this.quTitle = quTitle;
		this.quDate = quDate;
		this.quCnt = quCnt;
		this.answers = answers;
	}

	public String getQuNo() {
		return this.quNo;
	}

	public void setQuNo(String quNo) {
		this.quNo = quNo;
	}

	public Member getMember() {
		return this.member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public QuestionClassification getQuestionClassification() {
		return this.questionClassification;
	}

	public void setQuestionClassification(QuestionClassification questionClassification) {
		this.questionClassification = questionClassification;
	}

	public String getQuTitle() {
		return this.quTitle;
	}

	public void setQuTitle(String quTitle) {
		this.quTitle = quTitle;
	}

	public Serializable getQuDate() {
		return this.quDate;
	}

	public void setQuDate(Serializable quDate) {
		this.quDate = quDate;
	}

	public Clob getQuCnt() {
		return this.quCnt;
	}

	public void setQuCnt(Clob quCnt) {
		this.quCnt = quCnt;
	}

	public Set<Answer> getAnswers() {
		return this.answers;
	}

	public void setAnswers(Set<Answer> answers) {
		this.answers = answers;
	}

}
