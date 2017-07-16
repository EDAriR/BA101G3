package com.article.model;

import java.util.*;

public interface Article_Message_ReportDAO_interface {
	      public void insert(Article_Message_ReportVO article_message_reportVO);
	      public void update(Article_Message_ReportVO article_message_reportVO);
          public Article_Message_ReportVO findByPrimaryKey(String amsg_no, String mem_no);
	      public List<Article_Message_ReportVO> getAll();
}
