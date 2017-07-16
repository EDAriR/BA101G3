package com.baby.model;

import java.util.List;

public interface Growing_IntervalDAO_interface {

	public void insert(Growing_IntervalVO growing_intervalVO);
	public void update(Growing_IntervalVO growing_intervalVO);
    public Growing_IntervalVO findByPrimaryKey(String gint_no);
    List<Growing_IntervalVO> getAll(); 
	
}
