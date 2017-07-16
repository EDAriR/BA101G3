package com.baby.model;

import java.util.*;

public interface Growing_ControlDAO_interface {

	public void insert(Growing_ControlVO growin_controlVO);
	public void update(Growing_ControlVO growin_controlVO);
    public Growing_ControlVO findByPrimaryKey(String ctrl_gen, String gint_no);
    List<Growing_ControlVO> getAll();    
}
