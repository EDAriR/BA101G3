package com.backend.model;
// Generated 2017/7/13 �W�� 02:41:10 by Hibernate Tools 3.5.0.Final

import java.util.HashSet;
import java.util.Set;

/**
 * QuestionClassification generated by hbm2java
 */
public class QuestionClassification implements java.io.Serializable {

	private String quecNo;
	private String quecName;
	private Set<Question> questions = new HashSet<Question>(0);

	public QuestionClassification() {
	}

	public QuestionClassification(String quecNo, String quecName) {
		this.quecNo = quecNo;
		this.quecName = quecName;
	}

	public QuestionClassification(String quecNo, String quecName, Set<Question> questions) {
		this.quecNo = quecNo;
		this.quecName = quecName;
		this.questions = questions;
	}

	public String getQuecNo() {
		return this.quecNo;
	}

	public void setQuecNo(String quecNo) {
		this.quecNo = quecNo;
	}

	public String getQuecName() {
		return this.quecName;
	}

	public void setQuecName(String quecName) {
		this.quecName = quecName;
	}

	public Set<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

}