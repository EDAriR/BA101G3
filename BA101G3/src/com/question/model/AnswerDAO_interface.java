package com.question.model;

import java.util.*;

public interface AnswerDAO_interface {
	      public void insert(AnswerVO answerVO);
          public void update(AnswerVO answerVO);
          public AnswerVO findByPrimaryKey(String ans_no);
	      public List<AnswerVO> getAll();
}
