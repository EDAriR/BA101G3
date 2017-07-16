package com.article.model;

import java.util.*;

public interface Article_MessageDAO_interface {
	      public void insert(Article_MessageVO article_messageVO);
          public void delete(String amsg_no);
          public Article_MessageVO findByPrimaryKey(String amsg_no);
	      public List<Article_MessageVO> getAll();
}
