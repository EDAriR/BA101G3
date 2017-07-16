package com.baby.model;

import java.util.List;

public class Growing_ControlService {

	private Growing_ControlDAO_interface dao;

	public Growing_ControlService() {
		dao = new Growing_ControlDAO();
	}

	public Growing_ControlVO addGrowing_Control(String ctrl_gen, String gint_no, Double ctrl_hc, Double ctrl_ht, Double ctrl_wt, Double ctrl_diet, Double ctrl_sleep) {

		Growing_ControlVO growing_controlVO = new Growing_ControlVO();
		
		growing_controlVO.setCtrl_gen(ctrl_gen);
		growing_controlVO.setGint_no(gint_no);
		growing_controlVO.setCtrl_hc(ctrl_hc);
		growing_controlVO.setCtrl_ht(ctrl_ht);
		growing_controlVO.setCtrl_wt(ctrl_wt);
		growing_controlVO.setCtrl_diet(ctrl_diet);
		growing_controlVO.setCtrl_sleep(ctrl_sleep);
		
		dao.insert(growing_controlVO);

		return growing_controlVO;
	}
	
	public Growing_ControlVO updateGrowing_Control(String ctrl_gen, String gint_no, Double ctrl_hc, Double ctrl_ht, Double ctrl_wt, Double ctrl_diet, Double ctrl_sleep) {

		Growing_ControlVO growing_controlVO = new Growing_ControlVO();
		
		growing_controlVO.setCtrl_gen(ctrl_gen);
		growing_controlVO.setGint_no(gint_no);
		growing_controlVO.setCtrl_hc(ctrl_hc);
		growing_controlVO.setCtrl_ht(ctrl_ht);
		growing_controlVO.setCtrl_wt(ctrl_wt);
		growing_controlVO.setCtrl_diet(ctrl_diet);
		growing_controlVO.setCtrl_sleep(ctrl_sleep);
		
		dao.update(growing_controlVO);

		return growing_controlVO;
	}
	
	public Growing_ControlVO getOneGrowing_Diary(String ctrl_gen, String gint_no) {
		return dao.findByPrimaryKey(ctrl_gen, gint_no);
	}

	public List<Growing_ControlVO> getAll() {
		return dao.getAll();
	}
}
