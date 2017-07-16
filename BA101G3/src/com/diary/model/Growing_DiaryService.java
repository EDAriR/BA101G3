package com.diary.model;

import java.sql.Timestamp;
import java.util.List;

public class Growing_DiaryService {

	private Growing_DiaryDAO_interface dao;

	public Growing_DiaryService() {
		dao = new Growing_DiaryDAO();
	}

	public Growing_DiaryVO addGrowing_Diary(String baby_no, String gd_title, Timestamp gd_date, String gd_cnt, String gd_shr) {

		Growing_DiaryVO growing_diaryVO = new Growing_DiaryVO();
		
		growing_diaryVO.setBaby_no(baby_no);
		growing_diaryVO.setGd_title(gd_title);
		growing_diaryVO.setGd_date(gd_date);
		growing_diaryVO.setGd_cnt(gd_cnt);
		growing_diaryVO.setGd_shr(gd_shr);
		
		dao.insert(growing_diaryVO);

		return growing_diaryVO;
	}
	
	public Growing_DiaryVO updateGrowing_Diary(String gd_no, String gd_title, Timestamp gd_date, String gd_cnt, String gd_shr) {

		Growing_DiaryVO growing_diaryVO = new Growing_DiaryVO();
		
		growing_diaryVO.setGd_no(gd_no);
		growing_diaryVO.setGd_title(gd_title);
		growing_diaryVO.setGd_date(gd_date);
		growing_diaryVO.setGd_cnt(gd_cnt);
		growing_diaryVO.setGd_shr(gd_shr);
		
		dao.update(growing_diaryVO);

		return growing_diaryVO;
	}
	
	public void deleteGrowing_Diary(String gd_no) {
		dao.delete(gd_no);
	}

	public Growing_DiaryVO getOneGrowing_Diary(String gd_no) {
		return dao.findByPrimaryKey(gd_no);
	}

	public List<Growing_DiaryVO> getAll() {
		return dao.getAll();
	}
}
