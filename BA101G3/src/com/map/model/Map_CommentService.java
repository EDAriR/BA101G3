package com.map.model;

import java.util.List;

public class Map_CommentService {

	private Map_CommentDAO_interface dao;

	public Map_CommentService() {
		dao = new Map_CommentDAO();
	}

	public Map_CommentVO addMap_Comment(String mem_no, String map_no, String mcmt_cnt) {

		Map_CommentVO map_commentVO = new Map_CommentVO();
		
		map_commentVO.setMem_no(mem_no);
		map_commentVO.setMap_no(map_no);
		map_commentVO.setMcmt_cnt(mcmt_cnt);
		
		dao.insert(map_commentVO);

		return map_commentVO;
	}


	public void deleteMap_Comment(String mcmt_no) {
		dao.delete(mcmt_no);
	}

	public Map_CommentVO getOneMap_Comment(String mcmt_no) {
		return dao.findByPrimaryKey(mcmt_no);
	}

	public List<Map_CommentVO> getAll() {
		return dao.getAll();
	}
}
