package com.baby.model;

import java.util.List;

public interface No_Staple_FoodDAO_interface {

	public void insert(No_Staple_FoodVO no_staple_foodVO);
	public void update(No_Staple_FoodVO no_staple_foodVO);
	public void delete(String nf_no);
    public No_Staple_FoodVO findByPrimaryKey(String nf_no);
    List<No_Staple_FoodVO> getAll(); 
	
}
