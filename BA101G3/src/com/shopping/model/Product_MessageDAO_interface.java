package com.shopping.model;

import java.util.*;

public interface Product_MessageDAO_interface {
	      public void insert(Product_MessageVO product_messageVO);
          public void update(Product_MessageVO product_messageVO);
          public Product_MessageVO findByPrimaryKey(String pmsg_no);
	      public List<Product_MessageVO> getAll();
}
