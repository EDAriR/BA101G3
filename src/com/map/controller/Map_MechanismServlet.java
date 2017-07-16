package com.map.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.map.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)


public class Map_MechanismServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String map_no = req.getParameter("map_no");
				if (map_no == null || (map_no.trim()).length() == 0) {
					errorMsgs.add("請輸入地圖機構編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/map/select_map_mechanism_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/map/select_map_mechanism_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Map_MechanismService map_mechanismSvc = new Map_MechanismService();
				Map_MechanismVO map_mechanismVO = map_mechanismSvc.getOneMap_Mechanism(map_no);
				if (map_mechanismVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/map/select_map_mechanism_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("map_mechanismVO", map_mechanismVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/map/listOneMap_Mechanism.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/map/select_map_mechanism_page.jsp");
				failureView.forward(req, res);
			}
		}
		
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp 的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】		
			
			try {
				/***************************1.接收請求參數****************************************/
				String map_no = req.getParameter("map_no");
				
				/***************************2.開始查詢資料****************************************/
				Map_MechanismService map_mechanismSvc = new Map_MechanismService();
				Map_MechanismVO map_mechanismVO = map_mechanismSvc.getOneMap_Mechanism(map_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("map_mechanismVO", map_mechanismVO); // 資料庫取出的empVO物件,存入req
				String url = "/backend/map/update_map_mechanism_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】
		
			try {
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String map_no = req.getParameter("map_no").trim();
				String map_name = req.getParameter("map_name").trim();
				String map_adds = req.getParameter("map_adds").trim();	
				String map_addc = req.getParameter("map_addc").trim();
				String map_phone = req.getParameter("map_phone").trim();
				String map_mail = toRealNull(req.getParameter("map_mail").trim());
				String map_cnt = req.getParameter("map_cnt").trim();
				// TODO 待調整，尚未傳入參數
				String mem_no = toRealNull(req.getParameter("mem_no").trim());
				
				String mapc_no = req.getParameter("mapc_no").trim();
				Double map_long = new Double(req.getParameter("map_long").trim());
				Double map_lat = new Double(req.getParameter("map_lat").trim());
				byte[] map_photo = getPictureByteArray(req.getPart("map_photo"));
				byte[] map_photo1 = getPictureByteArray(req.getPart("map_photo1"));
				byte[] map_photo2 = getPictureByteArray(req.getPart("map_photo2"));
				byte[] map_photo3 = getPictureByteArray(req.getPart("map_photo3"));
				byte[] map_photo4 = getPictureByteArray(req.getPart("map_photo4"));
				byte[] map_photo5 = getPictureByteArray(req.getPart("map_photo5"));
				
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

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("map_mechanismVO", map_mechanismVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/map/update_map_mechanism_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Map_MechanismService map_mechanismSvc = new Map_MechanismService();
				map_mechanismVO = map_mechanismSvc.updateMap_Mechanism(map_no, mem_no, mapc_no,
						map_name, map_adds, map_addc, map_long, map_lat, map_phone, map_mail, 
						map_cnt, map_photo, map_photo1, map_photo2, map_photo3, map_photo4, map_photo5);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/				
                String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/map/update_map_mechanism_input.jsp");
				failureView.forward(req, res);
			}
		}

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String mapc_no = req.getParameter("mapc_no").trim();
				String map_name = req.getParameter("map_name").trim();
				String map_adds = req.getParameter("map_adds").trim();
				String map_addc = req.getParameter("map_addc").trim();
				String map_phone = req.getParameter("map_phone").trim();
				String map_mail = req.getParameter("map_mail").trim();
				String map_cnt = req.getParameter("map_cnt").trim();
				byte[] map_photo = getPictureByteArray(req.getPart("map_photo"));
				
				

				Map_MechanismVO map_mechanismVO = new Map_MechanismVO();
				map_mechanismVO.setMapc_no(mapc_no);
				map_mechanismVO.setMap_name(map_name);
				map_mechanismVO.setMap_adds(map_adds);
				map_mechanismVO.setMap_addc(map_addc);
				map_mechanismVO.setMap_phone(map_phone);
				map_mechanismVO.setMap_mail(map_mail);
				map_mechanismVO.setMap_cnt(map_cnt);
				map_mechanismVO.setMap_photo(map_photo);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("map_mechanismVO", map_mechanismVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/backend/map/addMap_Mechanism.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Map_MechanismService map_mechanismSvc = new Map_MechanismService();
				map_mechanismVO = map_mechanismSvc.addMap_Mechanism(null, mapc_no, map_name, map_adds, 
						          map_addc, 123.123, 11.11, map_phone, map_mail, map_cnt, map_photo, null, null, null, null, null);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/backend/map/listAllMap_Mechanism.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/map/addMap_Mechanism.jsp");
				failureView.forward(req, res);
			}
		}
		
       
		if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】

			try {
				/***************************1.接收請求參數***************************************/
				String map_no = req.getParameter("map_no");
				
				/***************************2.開始刪除資料***************************************/
				Map_MechanismService map_mechanismSvc = new Map_MechanismService();
				Map_MechanismVO map_mechanismVO = map_mechanismSvc.getOneMap_Mechanism(map_no);
				map_mechanismSvc.deleteMap_Mechanism(map_no);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(empVO.getDeptno())); // 資料庫取出的list物件,存入request
				
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
	public static byte[] getPictureByteArray(Part photo) throws IOException {
		InputStream is = photo.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int i;
		while ((i = is.read(buffer)) != -1) {
			baos.write(buffer, 0, i);
		}
		baos.close();
		is.close();

		return baos.toByteArray();
	}
	
	public String toRealNull(String str) {
		if(str.equals("null")) {
			return null;
		}else {
			return str;
		}
		
	}
}
