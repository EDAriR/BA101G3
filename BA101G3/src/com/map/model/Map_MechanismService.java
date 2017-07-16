package com.map.model;

import java.util.List;

public class Map_MechanismService {

	private Map_MechanismDAO_interface dao;

	public Map_MechanismService() {
		dao = new Map_MechanismDAO();
	}

	public Map_MechanismVO addMap_Mechanism(String mem_no, String mapc_no,
			String map_name, String map_adds, String map_addc, Double map_long, Double map_lat,
			String map_phone, String map_mail, String map_cnt, byte[] map_photo, byte[] map_photo1, 
			byte[] map_photo2, byte[] map_photo3, byte[] map_photo4, byte[] map_photo5) {

		Map_MechanismVO map_mechanismVO = new Map_MechanismVO();

		map_mechanismVO.setMem_no(mem_no);
		map_mechanismVO.setMapc_no(mapc_no);
		map_mechanismVO.setMap_name(map_name);
		map_mechanismVO.setMap_adds(map_adds);
		map_mechanismVO.setMap_addc(map_addc);
		map_mechanismVO.setMap_long(map_long);
		map_mechanismVO.setMap_lat(map_lat);
		map_mechanismVO.setMap_phone(map_phone);
		map_mechanismVO.setMap_mail(map_mail);
		map_mechanismVO.setMap_cnt(map_cnt);
		map_mechanismVO.setMap_photo(map_photo);
		map_mechanismVO.setMap_photo1(map_photo1);
		map_mechanismVO.setMap_photo2(map_photo2);
		map_mechanismVO.setMap_photo3(map_photo3);
		map_mechanismVO.setMap_photo4(map_photo4);
		map_mechanismVO.setMap_photo5(map_photo5);
		dao.insert(map_mechanismVO);

		return map_mechanismVO;
	}

	public Map_MechanismVO updateMap_Mechanism(String map_no, String mem_no, String mapc_no,
			String map_name, String map_adds, String map_addc, Double map_long, Double map_lat,
			String map_phone, String map_mail, String map_cnt, byte[] map_photo, byte[] map_photo1, 
			byte[] map_photo2, byte[] map_photo3, byte[] map_photo4, byte[] map_photo5) {

		Map_MechanismVO map_mechanismVO = new Map_MechanismVO();

		map_mechanismVO.setMap_no(map_no);
		map_mechanismVO.setMem_no(mem_no);
		map_mechanismVO.setMapc_no(mapc_no);
		map_mechanismVO.setMap_name(map_name);
		map_mechanismVO.setMap_adds(map_adds);
		map_mechanismVO.setMap_addc(map_addc);
		map_mechanismVO.setMap_long(map_long);
		map_mechanismVO.setMap_lat(map_lat);
		map_mechanismVO.setMap_phone(map_phone);
		map_mechanismVO.setMap_mail(map_mail);
		map_mechanismVO.setMap_cnt(map_cnt);
		map_mechanismVO.setMap_photo(map_photo);
		map_mechanismVO.setMap_photo1(map_photo1);
		map_mechanismVO.setMap_photo2(map_photo2);
		map_mechanismVO.setMap_photo3(map_photo3);
		map_mechanismVO.setMap_photo4(map_photo4);
		map_mechanismVO.setMap_photo5(map_photo5);
		dao.update(map_mechanismVO);

		return map_mechanismVO;
	}

	public void deleteMap_Mechanism(String map_no) {
		dao.delete(map_no);
	}

	public Map_MechanismVO getOneMap_Mechanism(String map_no) {
		return dao.findByPrimaryKey(map_no);
	}

	public List<Map_MechanismVO> getAll() {
		return dao.getAll();
	}
}
