package com.shopping.model;

import java.util.*;

public interface ProductDAO_interface {
	      public void insert(ProductVO productVO);
          public void update(ProductVO productVO);
          public void delete(String pro_no);
          public ProductVO findByPrimaryKey(String pro_no);
	      public List<ProductVO> getAll();
}
