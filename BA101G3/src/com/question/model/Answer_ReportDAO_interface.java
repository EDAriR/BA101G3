package com.question.model;

import java.util.*;

public interface Answer_ReportDAO_interface {
	      public void insert(Answer_ReportVO answer_reportVO);
	      public void update(Answer_ReportVO answer_reportVO);
          public Answer_ReportVO findByPrimaryKey(String ans_no, String mem_no);
	      public List<Answer_ReportVO> getAll();
}
