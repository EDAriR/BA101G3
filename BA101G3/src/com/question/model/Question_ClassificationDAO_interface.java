package com.question.model;

import java.util.*;

import com.question.model.QuestionVO;

public interface Question_ClassificationDAO_interface {
	      public void insert(Question_ClassificationVO question_classificationVO);
          public void update(Question_ClassificationVO question_classificationVO);
          public Question_ClassificationVO findByPrimaryKey(String quec_no);
	      public List<Question_ClassificationVO> getAll();
	      //查詢某類問題的問題(一對多)(回傳set)
	      public Set<QuestionVO> getQuestionsByQuec_no(String quec_no);
}
