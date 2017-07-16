package com.baby.model;

import java.util.List;

public class Growing_IntervalService {

	private Growing_IntervalDAO_interface dao;

	public Growing_IntervalService() {
		dao = new Growing_IntervalDAO();
	}

	public Growing_IntervalVO addGrowing_Interval(String gint_no, Integer gint_int) {

		Growing_IntervalVO growing_intervalVO = new Growing_IntervalVO();
		
		growing_intervalVO.setGint_no(gint_no);
		growing_intervalVO.setGint_int(gint_int);
		
		dao.insert(growing_intervalVO);

		return growing_intervalVO;
	}
	
	public Growing_IntervalVO updateGrowing_Interval(String gint_no, Integer gint_int) {

		Growing_IntervalVO growing_intervalVO = new Growing_IntervalVO();
		
		growing_intervalVO.setGint_no(gint_no);
		growing_intervalVO.setGint_int(gint_int);
		
		dao.update(growing_intervalVO);

		return growing_intervalVO;
	}
	
	public Growing_IntervalVO getOneGrowing_Interval(String gint_no) {
		return dao.findByPrimaryKey(gint_no);
	}

	public List<Growing_IntervalVO> getAll() {
		return dao.getAll();
	}
}
