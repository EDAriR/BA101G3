package com.map.model;

import java.util.*;

public interface Map_CommentDAO_interface {
	      public void insert(Map_CommentVO map_commentVO);
          public void delete(String mcmt_no	);
          public Map_CommentVO findByPrimaryKey(String mcmt_no);
	      public List<Map_CommentVO> getAll();
}
