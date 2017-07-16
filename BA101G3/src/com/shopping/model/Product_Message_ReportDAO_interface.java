package com.shopping.model;

import java.util.*;

public interface Product_Message_ReportDAO_interface {
	      public void insert(Product_Message_ReportVO product_message_reportVO);
          public void update(Product_Message_ReportVO product_message_reportVO);
          public Product_Message_ReportVO findByPrimaryKey(String pmsg_no, String mem_no);
	      public List<Product_Message_ReportVO> getAll();
}
