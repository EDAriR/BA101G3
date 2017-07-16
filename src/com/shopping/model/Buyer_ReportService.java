package com.shopping.model;

import java.sql.Timestamp;
import java.util.List;

public class Buyer_ReportService {
	private Buyer_ReportDAO_interface dao;
	
	public Buyer_ReportService(){
		dao= new Buyer_ReportDAO();
	}
	public Buyer_ReportVO addBuyer_Report(String ord_no,String mem_no,
			Timestamp buyrpt_date,String buyrpt_rsn,String buyrpt_is_cert,
			String buyrpt_unrsn){
		
		Buyer_ReportVO buyer_reportVO = new Buyer_ReportVO();
		
		buyer_reportVO.setOrd_no(ord_no);
		buyer_reportVO.setMem_no(mem_no);
		buyer_reportVO.setBuyrpt_date(buyrpt_date);
		buyer_reportVO.setBuyrpt_rsn(buyrpt_rsn);
		buyer_reportVO.setBuyrpt_is_cert(buyrpt_is_cert);
		buyer_reportVO.setBuyrpt_unrsn(buyrpt_unrsn);
		dao.insert(buyer_reportVO);
		
		return buyer_reportVO;
		
	}
	
	public Buyer_ReportVO updateBuyer_Report(String ord_no,String mem_no,
			Timestamp buyrpt_date,String buyrpt_rsn,String buyrpt_is_cert,
			String buyrpt_unrsn){
		
		Buyer_ReportVO buyer_reportVO = new Buyer_ReportVO();
		
		buyer_reportVO.setOrd_no(ord_no);
		buyer_reportVO.setMem_no(mem_no);
		buyer_reportVO.setBuyrpt_date(buyrpt_date);
		buyer_reportVO.setBuyrpt_rsn(buyrpt_rsn);
		buyer_reportVO.setBuyrpt_is_cert(buyrpt_is_cert);
		buyer_reportVO.setBuyrpt_unrsn(buyrpt_unrsn);
		dao.update(buyer_reportVO);
		
		return buyer_reportVO;
	}
	public Buyer_ReportVO getOneBuyer_Report(String ord_no, String mem_no){
		return dao.findByPrimaryKey(ord_no, mem_no);
	}
	public List<Buyer_ReportVO> getAll(){
		return dao.getAll();
	}
}
