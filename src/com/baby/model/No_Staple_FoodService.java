package com.baby.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class No_Staple_FoodService {

	private No_Staple_FoodDAO_interface dao;

	public No_Staple_FoodService() {
		dao = new No_Staple_FoodDAO();
	}

	public No_Staple_FoodVO addNo_Staple_Food(String nf_no, String gint_no, String nf_title, String nf_cnt) {

		No_Staple_FoodVO no_staple_foodVO = new No_Staple_FoodVO();
		
		no_staple_foodVO.setNf_no(nf_no);
		no_staple_foodVO.setGint_no(gint_no);
		no_staple_foodVO.setNf_title(nf_title);
		no_staple_foodVO.setNf_cnt(nf_cnt);
		
		dao.insert(no_staple_foodVO);

		return no_staple_foodVO;
	}
	
	public No_Staple_FoodVO updateNo_Staple_Food(String nf_no, String gint_no, String nf_title, String nf_cnt) {

		No_Staple_FoodVO no_staple_foodVO = new No_Staple_FoodVO();
		
		no_staple_foodVO.setNf_no(nf_no);
		no_staple_foodVO.setGint_no(gint_no);
		no_staple_foodVO.setNf_title(nf_title);
		no_staple_foodVO.setNf_cnt(nf_cnt);
		
		dao.update(no_staple_foodVO);

		return no_staple_foodVO;
	}
	
	public void deleteNo_Staple_Food(String nf_no) {
		dao.delete(nf_no);
	}

	public No_Staple_FoodVO getOneNo_Staple_Food(String nf_no) {
		return dao.findByPrimaryKey(nf_no);
	}

	public List<No_Staple_FoodVO> getAll() {
		return dao.getAll();
	}
}
