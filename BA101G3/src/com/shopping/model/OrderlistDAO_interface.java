package com.shopping.model;

import java.util.*;

public interface OrderlistDAO_interface {
	      public void insert(OrderlistVO orderlistVO);
          public void update(OrderlistVO orderlistVO);
          public OrderlistVO findByPrimaryKey(String ord_no);
	      public List<OrderlistVO> getAll();
}
