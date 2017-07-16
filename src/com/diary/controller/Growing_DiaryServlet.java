package com.diary.controller;

import java.io.*;
import java.sql.Timestamp;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.diary.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)


public class Growing_DiaryServlet extends HttpServlet {

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
				String gd_no = req.getParameter("gd_no");
				if (gd_no == null || (gd_no.trim()).length() == 0) {
					errorMsgs.add("請輸入成長日誌編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/diary/select_growing_diary_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/diary/select_growing_diary_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				Growing_DiaryService growing_diarySvc = new Growing_DiaryService();
				Growing_DiaryVO growing_diaryVO = growing_diarySvc.getOneGrowing_Diary(gd_no);
				if (growing_diaryVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/diary/select_growing_diary_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("growing_diaryVO", growing_diaryVO); // 資料庫取出的empVO物件,存入req
				String url = "/frontend/diary/listOneGrowing_Diary.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/diary/select_growing_diary_page.jsp");
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
				String gd_no = req.getParameter("gd_no");
				
				/***************************2.開始查詢資料****************************************/
				Growing_DiaryService growing_diarySvc = new Growing_DiaryService();
				Growing_DiaryVO growing_diaryVO = growing_diarySvc.getOneGrowing_Diary(gd_no);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("growing_diaryVO", growing_diaryVO); // 資料庫取出的empVO物件,存入req
				String url = "/frontend/diary/update_growing_diary_input.jsp";
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
				String gd_no = req.getParameter("gd_no").trim();
				String baby_no = req.getParameter("baby_no").trim();
				String gd_title = req.getParameter("gd_title").trim();	
				String gd_cnt = req.getParameter("gd_cnt").trim();
				Timestamp gd_date = java.sql.Timestamp.valueOf(req.getParameter("gd_date").trim());
				String gd_shr = toRealNull(req.getParameter("gd_shr").trim());
				
				Growing_DiaryVO growing_diaryVO = new Growing_DiaryVO();
				
				growing_diaryVO.setGd_no(gd_no);
				growing_diaryVO.setBaby_no(baby_no);
				growing_diaryVO.setGd_title(gd_title);
				growing_diaryVO.setGd_cnt(gd_cnt);
				growing_diaryVO.setGd_date(gd_date);
				growing_diaryVO.setGd_shr(gd_shr);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("growing_diaryVO", growing_diaryVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/diary/update_growing_diary_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				Growing_DiaryService growing_diarySvc = new Growing_DiaryService();
				growing_diaryVO = growing_diarySvc.updateGrowing_Diary(gd_no, gd_title, gd_date, gd_cnt, gd_shr);
				growing_diaryVO = growing_diarySvc.getOneGrowing_Diary(gd_no);
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("growing_diaryVO", growing_diaryVO);
				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url);   // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/***************************其他可能的錯誤處理*************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:"+e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/diary/update_growing_diary_input.jsp");
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
				java.sql.Timestamp gd_date = null;
				// 取得目前時分秒
				/*Calendar calendar = Calendar.getInstance();
				int timeNow = (calendar.get(Calendar.HOUR_OF_DAY) * 60 * 60 * 1000) + 
						      (calendar.get(Calendar.MINUTE) * 60 * 1000) +
						      (calendar.get(Calendar.SECOND) * 1000);*/
				try {
					gd_date = Timestamp.valueOf(req.getParameter("gd_date").trim());
				} catch (IllegalArgumentException e) {
					gd_date = new java.sql.Timestamp(System.currentTimeMillis());
					errorMsgs.add("請輸入日期!");
				}
				String baby_no = req.getParameter("baby_no").trim();
				String gd_title = req.getParameter("gd_title").trim();
				String gd_cnt = req.getParameter("gd_cnt").trim();
				String gd_shr = req.getParameter("gd_shr").trim();
				
				

				Growing_DiaryVO growing_diaryVO = new Growing_DiaryVO();
				
				growing_diaryVO.setBaby_no(baby_no);
				growing_diaryVO.setGd_title(gd_title);
				growing_diaryVO.setGd_date(gd_date);
				growing_diaryVO.setGd_cnt(gd_cnt);
				growing_diaryVO.setGd_shr(gd_shr);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("growing_diaryVO", growing_diaryVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/frontend/diary/addGrowing_Diary.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				Growing_DiaryService growing_diarySvc = new Growing_DiaryService();
				growing_diaryVO = growing_diarySvc.addGrowing_Diary(baby_no, gd_title, gd_date, gd_cnt, gd_shr);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/frontend/diary/listAllGrowing_Diary.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
				successView.forward(req, res);				
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/frontend/diary/addGrowing_Diary.jsp");
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
				String[] deleteDiaryList = req.getParameterValues("deleteDiaryList");
				
				/***************************2.開始刪除資料***************************************/
				Growing_DiaryService growing_diarySvc = new Growing_DiaryService();
				if(deleteDiaryList != null) {
					if(deleteDiaryList.length != 0) {
						for(String gd_no:deleteDiaryList) {
						Growing_DiaryVO growing_diaryVO = growing_diarySvc.getOneGrowing_Diary(gd_no);
						growing_diarySvc.deleteGrowing_Diary(gd_no);
						}
					}
				}else {
					String gd_no = req.getParameter("gd_no");
					Growing_DiaryVO growing_diaryVO = growing_diarySvc.getOneGrowing_Diary(gd_no);
					growing_diarySvc.deleteGrowing_Diary(gd_no);
				}
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/
//				DeptService deptSvc = new DeptService();
//				if(requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
//					req.setAttribute("listEmps_ByDeptno",deptSvc.getEmpsByDeptno(empVO.getDeptno())); // 資料庫取出的list物件,存入request
				
				String url = "";
				if(requestURL == null) {
					url = "/frontend/diary/listAllGrowing_Diary.jsp";
				}else {
					url = requestURL;
				}
					
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
	
	public String toRealNull(String str) {
		if(str.equals("null")) {
			return null;
		}else {
			return str;
		}
		
	}
}
