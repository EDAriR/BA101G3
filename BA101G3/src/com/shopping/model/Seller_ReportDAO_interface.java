package com.shopping.model;

import java.util.*;

public interface Seller_ReportDAO_interface {
	      public void insert(Seller_ReportVO seller_reportVO);
          public void update(Seller_ReportVO seller_reportVO);
          public Seller_ReportVO findByPrimaryKey(String pro_no, String mem_no);
	      public List<Seller_ReportVO> getAll();
}
