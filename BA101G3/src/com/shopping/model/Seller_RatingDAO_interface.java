package com.shopping.model;

import java.util.*;

public interface Seller_RatingDAO_interface {
	      public void insert(Seller_RatingVO seller_ratingVO);
          public Seller_RatingVO findByPrimaryKey(String pro_no, String mem_no);
	      public List<Seller_RatingVO> getAll();
	      public List<Seller_RatingVO> findByProNo(String pro_no);

}
