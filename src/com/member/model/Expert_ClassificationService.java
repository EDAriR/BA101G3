package com.member.model;

import java.util.List;


public class Expert_ClassificationService {
	
	private Expert_ClassificationDAO dao;
	
	public Expert_ClassificationService(){
		
		dao = new Expert_ClassificationDAO();
		
	}
	
	public Expert_ClassificationVO addExpert_Classification(String exp_cname){
		
		Expert_ClassificationVO expert_classificationVO = new Expert_ClassificationVO();
		
		expert_classificationVO.setExp_cname(exp_cname);
		dao.insert(expert_classificationVO);
		
		return expert_classificationVO;
	}
	
	public Expert_ClassificationVO updateExpert_Classification(String exp_no, String exp_cname){
		
		Expert_ClassificationVO expert_classificationVO = new Expert_ClassificationVO();
		expert_classificationVO.setExp_no(exp_no);
		expert_classificationVO.setExp_cname(exp_cname);
		dao.update(expert_classificationVO);
		return expert_classificationVO;
	}
	
	public Expert_ClassificationVO getOneExpert_Classification(String exp_no) {
		return dao.findByPrimaryKey(exp_no);
	}

	public List<Expert_ClassificationVO> getAll() {
		return dao.getAll();
	}
	
}
