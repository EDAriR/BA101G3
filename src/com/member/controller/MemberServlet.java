package com.member.controller;

import com.member.model.MemberService;
import com.member.model.MemberVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

//@WebServlet("/PhotoUpload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class MemberServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println("進來Member的控制器,action = "+ action);
		
		if ("login".equals(action)) { // 來自login.jsp的請求

			/* session */
			HttpSession session = req.getSession();
			/* session */

			//System.out.println("action in c: " + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); 

			try {
				/***********************
				 * 1.接收請求參數 - 輸入格式的錯誤處理(驗證)
				 *************************/

				// 【取得使用者 帳號(account) 密碼(password)】
				String mem_acct = req.getParameter("mem_acct");
				String mem_pwd = req.getParameter("mem_pwd");

				if (mem_acct == null || (mem_acct.trim()).length() == 0) {
					errorMsgs.add("請輸入會員帳號");
				} else if (mem_pwd == null || (mem_pwd.trim()).length() == 0) {
					errorMsgs.add("請輸入會員密碼");
				}
				
				// 格式有誤的memberVO物件,forward給login.jsp
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMemberByAcct(mem_acct);
				if (memberVO == null) {
					errorMsgs.add("無此帳號");
				} else if (!memberVO.getMem_pwd().equals(mem_pwd)) {
					errorMsgs.add("密碼不正確");
				} else if (memberVO.getMem_is_stop().equals("1")) {
					errorMsgs.add("您的帳號已凍結");
				}

				// 格式有誤的memberVO物件,forward給login.jsp
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始新增資料.永續層存取.呼叫model(MemberService.java) *********************/
			
				String mem_no = memberVO.getMem_no();
				String mem_name = memberVO.getMem_name();
			
				/* session */
				session.setAttribute("mem_no", mem_no);
				session.setAttribute("mem_name", mem_name);
				session.setAttribute("memberVO", memberVO);
				/* session */

				/***************************
				 * 3.新增完成,準備轉交(Send the Success view)
				 ***********/
				 try {
				 String location = (String) session.getAttribute("location");
					 if (location != null) {
					 session.removeAttribute("location"); //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					 res.sendRedirect(location);
					 return;
					 }
				 }catch (Exception ignored) { }

				res.sendRedirect(req.getContextPath() + "/frontend/member/member/welcome.jsp"); // *工作3:(-->如無來源網頁:則重導至welcome.jsp)
				//System.out.println("redirect to: welcome.jsp");																			
				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				// errorMsgs.add("帳號密碼不正確");
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		
		if ("register".equals(action)) { 

			/* session */
			HttpSession session = req.getSession();
			/* session */

			List<String> errorMsgs_r = new LinkedList<String>();
			req.setAttribute("errorMsgs_r", errorMsgs_r);
			String requestURL = req.getParameter("requestURL");

			// try {
			/***********************
			 * 1.接收請求參數 - 輸入格式的錯誤處理(驗證)
			 *************************/

			String mem_acct = req.getParameter("mem_acct").trim();
			if (mem_acct == "" || (mem_acct.trim()).length() == 0) {
				errorMsgs_r.add("請輸入帳號");
				// System.out.println(errorMsgs);
			}

			String mem_pwd = req.getParameter("mem_pwd").trim();
			if (mem_pwd == "" || (mem_pwd.trim()).length() == 0) {
				errorMsgs_r.add("請輸入密碼");
				// System.out.println(errorMsgs);
			}

			String mem_name = req.getParameter("mem_name").trim();
			if (mem_name == "" || (mem_name.trim()).length() == 0) {
				errorMsgs_r.add("請輸入姓名");
				// System.out.println(errorMsgs);
			}

		
			String mem_adds = req.getParameter("mem_adds");
System.out.println(mem_adds);
			if (mem_adds == null) {
				errorMsgs_r.add("請選擇縣市");
				// System.out.println(errorMsgs);
			}

			MemberVO memberVO = new MemberVO();
			memberVO.setMem_acct(mem_acct);
			memberVO.setMem_pwd(mem_pwd);
			memberVO.setMem_name(mem_name);
			memberVO.setMem_adds(mem_adds);

			if (!errorMsgs_r.isEmpty()) {
				//System.out.println("error");
				req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
				return; // 格式有誤的memberVO物件,forward給login.jsp
			}

			/*************************** 2.開始新增資料.永續層存取.呼叫model(MemberService.java) *********************/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.addMember(mem_acct, mem_pwd, mem_name, mem_adds);// 格試正確時:從資料庫拿出memberVO(型別是MemberVO)
			// System.out.println("memberVO: " + memberVO.toString());
			String mem_no = memberVO.getMem_no();
			/* session */
			session.setAttribute("mem_no", mem_no);
			session.setAttribute("mem_name", mem_name);
			session.setAttribute("memberVO", memberVO);
			/* session */

			/***************************
			 * 3.新增完成,準備轉交(Send the Success view)
			 ***********/
			//註冊後導回登入頁面請使用者登入
			 try {
			 String location = (String) session.getAttribute("location");
			 if (location != null) {
			 session.removeAttribute("location"); //*工作2: 看看有無來源網頁(-->如有來源網頁:則重導至來源網頁)
			 res.sendRedirect(location);
			 return;
			 }
			 }catch (Exception ignored) { }

			 res.sendRedirect(req.getContextPath() + "/frontend/member/member/updateMember.jsp"); // *工作3:(-->如無來源網頁:則重導至updateMember.jsp)
			 //System.out.println("nooo");													
		}

		if ("update".equals(action)) { // 來自updateMember.jsp的請求
			
			/* session */
			HttpSession session = req.getSession();
			String mem_no = (String)session.getAttribute("mem_no");
			//System.out.println("mem_no: " + mem_no);
			/* session */
			
			//System.out.println("action in c: " + action);
			List<String> errorMsgs_u = new LinkedList<String>();
			req.setAttribute("errorMsgs_u", errorMsgs_u);
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑:
																// 可能為【/member/listAllMember.jsp】
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/

			String mem_pwd = req.getParameter("mem_pwd").trim();
			if (mem_pwd == null || (mem_pwd.trim()).length() == 0) {
				errorMsgs_u.add("請輸入密碼");
				// System.out.println(errorMsgs);
			}

			String mem_name = req.getParameter("mem_name").trim();
			//System.out.println(mem_name);
			if (mem_name == null || (mem_name.trim()).length() == 0) {
				errorMsgs_u.add("請輸入姓名");
				//System.out.println(errorMsgs_u);
			}

			String mem_aka = req.getParameter("mem_aka").trim();

			byte[] mem_photo = null;
			Part part1 = req.getPart("mem_photo"); 
			mem_photo = getPictureByteArrayFromWeb(part1);
			// when user doesn't upload file,clear mem_photo,because part1 doesn't equal to null
			// try  System.out.println(part1);
			if (getFileNameFromPart(part1) == null || part1.getContentType() == null) {
				mem_photo = null;
			}

			String mem_adds = req.getParameter("mem_adds");
System.out.println(mem_adds);
			if (mem_adds == null) {
				errorMsgs_u.add("請選擇縣市");
				// System.out.println(errorMsgs_u);
			}

			String mem_addc = req.getParameter("mem_addc").trim();
			String mem_phone = req.getParameter("mem_phone").trim();
			String mem_mail = req.getParameter("mem_mail").trim();
			String mem_intro_b = req.getParameter("mem_intro_b").trim();
			String mem_acct_s = req.getParameter("mem_acct_s").trim();
			String mem_intro_s = req.getParameter("mem_intro_s").trim();

			MemberVO memberVO = new MemberVO();

			memberVO.setMem_pwd(mem_pwd);
			memberVO.setMem_name(mem_name);
			memberVO.setMem_aka(mem_aka);
			memberVO.setMem_photo(mem_photo);
			memberVO.setMem_adds(mem_adds);
			memberVO.setMem_addc(mem_addc);
			memberVO.setMem_phone(mem_phone);
			memberVO.setMem_mail(mem_mail);
			memberVO.setMem_intro_b(mem_intro_b);
			memberVO.setMem_acct_s(mem_acct_s);
			memberVO.setMem_intro_s(mem_intro_s);

			if (!errorMsgs_u.isEmpty()) {
				req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res); 
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			MemberService memberSvc = new MemberService();
			// System.out.println("exp_no = "+ exp_no);
			memberVO = memberSvc.updateMember(mem_pwd, mem_name, mem_aka, mem_photo, mem_adds, mem_addc,
					mem_phone, mem_mail, mem_intro_b,mem_acct_s, mem_intro_s, mem_no);

			/***************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 *************/
		    try {
		    	String location = (String) session.getAttribute("location");
				if (location != null) {
				session.removeAttribute("location"); //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
				res.sendRedirect(location);
				return;
				}
			 }catch (Exception ignored) { }

		    res.sendRedirect(req.getContextPath() + "/frontend/member/member/listOneMember.jsp"); // *工作3:(-->如無來源網頁:則重導至listOneMember.jsp
																								
			/*************************** 其他可能的錯誤處理 *************************************/
			
		}

		
		if ("updateExpert".equals(action)) { // 來自updateExpert.jsp的請求
			
			/* session */
			HttpSession session = req.getSession();
			/* session */
			
			//System.out.println("action in c: " + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
																
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			String mem_adds = req.getParameter("mem_adds");
			String mem_addc = req.getParameter("mem_addc");
			
			String exp_no = req.getParameter("exp_no").trim();
			String education = req.getParameter("education").trim();
			String company = req.getParameter("company").trim();
			String mem_intro_e = req.getParameter("mem_intro_e");
			String mem_no = req.getParameter("mem_no");
			String intro_e = education +"+memintroe+"+ mem_intro_e +"+memintroe+"+ company;
			//System.out.println("intro_e===========:" + intro_e);
			
			MemberVO memberVO = new MemberVO();

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res); 
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			MemberService memberSvc = new MemberService();
			// System.out.println("exp_no = "+ exp_no);
memberVO = memberSvc.updateExpert(mem_adds, mem_addc, exp_no, intro_e, mem_no);

			/***************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 *************/
			session.setAttribute("memberVO", memberVO);
			
		    try {
		    	String location = (String) session.getAttribute("location");
				if (location != null) {
				session.removeAttribute("location"); //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
				res.sendRedirect(location);
				return;
				}
			 }catch (Exception ignored) { }

		    res.sendRedirect(req.getContextPath() + "/frontend/member/member/welcome.jsp"); // *工作3:(-->如無來源網頁:則重導至.jsp
																								
			/*************************** 其他可能的錯誤處理 *************************************/
			
		}

		
if ("updateSenior".equals(action)) { // 來自updateExp.jsp的請求
			
			/* session */
			HttpSession session = req.getSession();
			/* session */
			
			//System.out.println("action in c: " + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // 送出修改的來源網頁路徑
																
			/***************************
			 * 1.接收請求參數 - 輸入格式的錯誤處理
			 **********************/
			String mem_intro_e = req.getParameter("mem_intro_e");
			String mem_no = req.getParameter("mem_no");
			MemberVO memberVO = new MemberVO();

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res); 
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			MemberService memberSvc = new MemberService();
			// System.out.println("exp_no = "+ exp_no);
			memberVO = memberSvc.updateSenior(mem_intro_e, mem_no);

			/***************************
			 * 3.修改完成,準備轉交(Send the Success view)
			 *************/
			session.setAttribute("memberVO", memberVO);
			
		    try {
		    	String location = (String) session.getAttribute("location");
				if (location != null) {
				session.removeAttribute("location"); //*工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
				res.sendRedirect(location);
				return;
				}
			 }catch (Exception ignored) { }

		    res.sendRedirect(req.getContextPath() + "/frontend/member/member/welcome.jsp"); // *工作3:(-->如無來源網頁:則重導至.jsp
																								
			/*************************** 其他可能的錯誤處理 *************************************/
			
		}
		
		
	

	}

	public static byte[] getPictureByteArrayFromWeb(Part part) throws IOException {

		InputStream is = part.getInputStream();
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

	public String getFileNameFromPart(Part part) {

		String header = part.getHeader("content-disposition");
		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
