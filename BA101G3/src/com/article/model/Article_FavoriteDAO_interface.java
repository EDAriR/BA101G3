package com.article.model;

import java.util.*;

public interface Article_FavoriteDAO_interface {
	      public void insert(Article_FavoriteVO article_favoriteVO);
          public void delete(String mem_no, String art_no);
          public Article_FavoriteVO findByPrimaryKey(String mem_no, String art_no);
	      public List<Article_FavoriteVO> getAll();
	      public List<Article_FavoriteVO> findByMemNo(String mem_no);
	      public List<Article_FavoriteVO> findByArtNo(String art_no);
}
