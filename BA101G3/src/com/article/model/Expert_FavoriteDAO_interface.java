package com.article.model;

import java.util.*;

public interface Expert_FavoriteDAO_interface {
	      public void insert(Expert_FavoriteVO expert_favoriteVO);
          public void delete(String mem_no_s, String mem_no_e);
          public Expert_FavoriteVO findByPrimaryKey(String mem_no_s, String mem_no_e);
	      public List<Expert_FavoriteVO> getAll();
	      public List<Expert_FavoriteVO> findByMemNoS(String mem_no_s);
	      public List<Expert_FavoriteVO> findByMemNoE(String mem_no_e);
}
