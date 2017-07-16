package com.shopping.model;

import java.sql.Timestamp;
import java.util.List;

public class OrderlistService {
	private OrderlistDAO_interface dao;
	
	public OrderlistService(){
		dao= new OrderlistDAO();
	}
	
	public OrderlistVO addOrderlist(String ord_no,String pro_no,
			String mem_no,Integer ord_amt,Timestamp ord_startd,Timestamp ord_endd,
			Timestamp ord_payd,String ord_getadd){
		
		OrderlistVO orderlistVO= new OrderlistVO();
		
		orderlistVO.setOrd_no(ord_no);
		orderlistVO.setPro_no(pro_no);
		orderlistVO.setMem_no(mem_no);
		orderlistVO.setOrd_amt(ord_amt);
		orderlistVO.setOrd_startd(ord_startd);
		orderlistVO.setOrd_endd(ord_endd);
		orderlistVO.setOrd_payd(ord_payd);
		orderlistVO.setOrd_getadd(ord_getadd);
		dao.insert(orderlistVO);
		
		return orderlistVO;
		
	}
	
	public OrderlistVO updateOrderlist(String ord_no,String pro_no,
			String mem_no,Integer ord_amt,Timestamp ord_startd,Timestamp ord_endd,
		Timestamp ord_payd,String ord_getadd){
		
		OrderlistVO orderlistVO= new OrderlistVO();

		orderlistVO.setOrd_no(ord_no);
		orderlistVO.setPro_no(pro_no);
		orderlistVO.setMem_no(mem_no);
		orderlistVO.setOrd_amt(ord_amt);
		orderlistVO.setOrd_startd(ord_startd);
		orderlistVO.setOrd_endd(ord_endd);
		orderlistVO.setOrd_payd(ord_payd);
		orderlistVO.setOrd_getadd(ord_getadd);
		dao.update(orderlistVO);
		
		return orderlistVO;
	}
	
	public OrderlistVO getOneOrderlist(String ord_no){
		return dao.findByPrimaryKey(ord_no);
		
	}
	public List<OrderlistVO> getAll(){
		return dao.getAll();
		
	}
}
