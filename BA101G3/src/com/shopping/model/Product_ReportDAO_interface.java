package com.shopping.model;

import java.util.*;

public interface Product_ReportDAO_interface {
	      public void insert(Product_ReportVO product_reportVO);
          public void update(Product_ReportVO product_reportVO);
          public Product_ReportVO findByPrimaryKey(String pro_no, String mem_no);
	      public List<Product_ReportVO> getAll();
	      
}
