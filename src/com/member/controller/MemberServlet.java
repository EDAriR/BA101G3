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
		System.out.println("�i��Member�����,action = "+ action);
		
		if ("login".equals(action)) { // �Ӧ�login.jsp���ШD

			/* session */
			HttpSession session = req.getSession();
			/* session */

			//System.out.println("action in c: " + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); 

			try {
				/***********************
				 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z(����)
				 *************************/

				// �i���o�ϥΪ� �b��(account) �K�X(password)�j
				String mem_acct = req.getParameter("mem_acct");
				String mem_pwd = req.getParameter("mem_pwd");

				if (mem_acct == null || (mem_acct.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���b��");
				} else if (mem_pwd == null || (mem_pwd.trim()).length() == 0) {
					errorMsgs.add("�п�J�|���K�X");
				}
				
				// �榡���~��memberVO����,forward��login.jsp
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}
				
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMemberByAcct(mem_acct);
				if (memberVO == null) {
					errorMsgs.add("�L���b��");
				} else if (!memberVO.getMem_pwd().equals(mem_pwd)) {
					errorMsgs.add("�K�X�����T");
				} else if (memberVO.getMem_is_stop().equals("1")) {
					errorMsgs.add("�z���b���w�ᵲ");
				}

				// �榡���~��memberVO����,forward��login.jsp
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.�}�l�s�W���.����h�s��.�I�smodel(MemberService.java) *********************/
			
				String mem_no = memberVO.getMem_no();
				String mem_name = memberVO.getMem_name();
			
				/* session */
				session.setAttribute("mem_no", mem_no);
				session.setAttribute("mem_name", mem_name);
				session.setAttribute("memberVO", memberVO);
				/* session */

				/***************************
				 * 3.�s�W����,�ǳ����(Send the Success view)
				 ***********/
				 try {
				 String location = (String) session.getAttribute("location");
					 if (location != null) {
					 session.removeAttribute("location"); //*�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
					 res.sendRedirect(location);
					 return;
					 }
				 }catch (Exception ignored) { }

				res.sendRedirect(req.getContextPath() + "/frontend/member/member/welcome.jsp"); // *�u�@3:(-->�p�L�ӷ�����:�h���ɦ�welcome.jsp)
				//System.out.println("redirect to: welcome.jsp");																			
				/*************************** ��L�i�઺���~�B�z **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage());
				// errorMsgs.add("�b���K�X�����T");
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
			 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z(����)
			 *************************/

			String mem_acct = req.getParameter("mem_acct").trim();
			if (mem_acct == "" || (mem_acct.trim()).length() == 0) {
				errorMsgs_r.add("�п�J�b��");
				// System.out.println(errorMsgs);
			}

			String mem_pwd = req.getParameter("mem_pwd").trim();
			if (mem_pwd == "" || (mem_pwd.trim()).length() == 0) {
				errorMsgs_r.add("�п�J�K�X");
				// System.out.println(errorMsgs);
			}

			String mem_name = req.getParameter("mem_name").trim();
			if (mem_name == "" || (mem_name.trim()).length() == 0) {
				errorMsgs_r.add("�п�J�m�W");
				// System.out.println(errorMsgs);
			}

		
			String mem_adds = req.getParameter("mem_adds");
System.out.println(mem_adds);
			if (mem_adds == null) {
				errorMsgs_r.add("�п�ܿ���");
				// System.out.println(errorMsgs);
			}

			MemberVO memberVO = new MemberVO();
			memberVO.setMem_acct(mem_acct);
			memberVO.setMem_pwd(mem_pwd);
			memberVO.setMem_name(mem_name);
			memberVO.setMem_adds(mem_adds);

			if (!errorMsgs_r.isEmpty()) {
				//System.out.println("error");
				req.setAttribute("memberVO", memberVO); // �t����J�榡���~��memberVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
				return; // �榡���~��memberVO����,forward��login.jsp
			}

			/*************************** 2.�}�l�s�W���.����h�s��.�I�smodel(MemberService.java) *********************/
			MemberService memberSvc = new MemberService();
			memberVO = memberSvc.addMember(mem_acct, mem_pwd, mem_name, mem_adds);// ��ե��T��:�q��Ʈw���XmemberVO(���O�OMemberVO)
			// System.out.println("memberVO: " + memberVO.toString());
			String mem_no = memberVO.getMem_no();
			/* session */
			session.setAttribute("mem_no", mem_no);
			session.setAttribute("mem_name", mem_name);
			session.setAttribute("memberVO", memberVO);
			/* session */

			/***************************
			 * 3.�s�W����,�ǳ����(Send the Success view)
			 ***********/
			//���U��ɦ^�n�J�����ШϥΪ̵n�J
			 try {
			 String location = (String) session.getAttribute("location");
			 if (location != null) {
			 session.removeAttribute("location"); //*�u�@2: �ݬݦ��L�ӷ�����(-->�p���ӷ�����:�h���ɦܨӷ�����)
			 res.sendRedirect(location);
			 return;
			 }
			 }catch (Exception ignored) { }

			 res.sendRedirect(req.getContextPath() + "/frontend/member/member/updateMember.jsp"); // *�u�@3:(-->�p�L�ӷ�����:�h���ɦ�updateMember.jsp)
			 //System.out.println("nooo");													
		}

		if ("update".equals(action)) { // �Ӧ�updateMember.jsp���ШD
			
			/* session */
			HttpSession session = req.getSession();
			String mem_no = (String)session.getAttribute("mem_no");
			//System.out.println("mem_no: " + mem_no);
			/* session */
			
			//System.out.println("action in c: " + action);
			List<String> errorMsgs_u = new LinkedList<String>();
			req.setAttribute("errorMsgs_u", errorMsgs_u);
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|:
																// �i�ର�i/member/listAllMember.jsp�j
			/***************************
			 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
			 **********************/

			String mem_pwd = req.getParameter("mem_pwd").trim();
			if (mem_pwd == null || (mem_pwd.trim()).length() == 0) {
				errorMsgs_u.add("�п�J�K�X");
				// System.out.println(errorMsgs);
			}

			String mem_name = req.getParameter("mem_name").trim();
			//System.out.println(mem_name);
			if (mem_name == null || (mem_name.trim()).length() == 0) {
				errorMsgs_u.add("�п�J�m�W");
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
				errorMsgs_u.add("�п�ܿ���");
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
				req.setAttribute("memberVO", memberVO); // �t����J�榡���~��memberVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res); 
				return; // �{�����_
			}

			/*************************** 2.�}�l�ק��� *****************************************/
			MemberService memberSvc = new MemberService();
			// System.out.println("exp_no = "+ exp_no);
			memberVO = memberSvc.updateMember(mem_pwd, mem_name, mem_aka, mem_photo, mem_adds, mem_addc,
					mem_phone, mem_mail, mem_intro_b,mem_acct_s, mem_intro_s, mem_no);

			/***************************
			 * 3.�ק粒��,�ǳ����(Send the Success view)
			 *************/
		    try {
		    	String location = (String) session.getAttribute("location");
				if (location != null) {
				session.removeAttribute("location"); //*�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
				res.sendRedirect(location);
				return;
				}
			 }catch (Exception ignored) { }

		    res.sendRedirect(req.getContextPath() + "/frontend/member/member/listOneMember.jsp"); // *�u�@3:(-->�p�L�ӷ�����:�h���ɦ�listOneMember.jsp
																								
			/*************************** ��L�i�઺���~�B�z *************************************/
			
		}

		
		if ("updateExpert".equals(action)) { // �Ӧ�updateExpert.jsp���ШD
			
			/* session */
			HttpSession session = req.getSession();
			/* session */
			
			//System.out.println("action in c: " + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|
																
			/***************************
			 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
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
				return; // �{�����_
			}

			/*************************** 2.�}�l�ק��� *****************************************/
			MemberService memberSvc = new MemberService();
			// System.out.println("exp_no = "+ exp_no);
memberVO = memberSvc.updateExpert(mem_adds, mem_addc, exp_no, intro_e, mem_no);

			/***************************
			 * 3.�ק粒��,�ǳ����(Send the Success view)
			 *************/
			session.setAttribute("memberVO", memberVO);
			
		    try {
		    	String location = (String) session.getAttribute("location");
				if (location != null) {
				session.removeAttribute("location"); //*�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
				res.sendRedirect(location);
				return;
				}
			 }catch (Exception ignored) { }

		    res.sendRedirect(req.getContextPath() + "/frontend/member/member/welcome.jsp"); // *�u�@3:(-->�p�L�ӷ�����:�h���ɦ�.jsp
																								
			/*************************** ��L�i�઺���~�B�z *************************************/
			
		}

		
if ("updateSenior".equals(action)) { // �Ӧ�updateExp.jsp���ШD
			
			/* session */
			HttpSession session = req.getSession();
			/* session */
			
			//System.out.println("action in c: " + action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|
																
			/***************************
			 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
			 **********************/
			String mem_intro_e = req.getParameter("mem_intro_e");
			String mem_no = req.getParameter("mem_no");
			MemberVO memberVO = new MemberVO();

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res); 
				return; // �{�����_
			}

			/*************************** 2.�}�l�ק��� *****************************************/
			MemberService memberSvc = new MemberService();
			// System.out.println("exp_no = "+ exp_no);
			memberVO = memberSvc.updateSenior(mem_intro_e, mem_no);

			/***************************
			 * 3.�ק粒��,�ǳ����(Send the Success view)
			 *************/
			session.setAttribute("memberVO", memberVO);
			
		    try {
		    	String location = (String) session.getAttribute("location");
				if (location != null) {
				session.removeAttribute("location"); //*�u�@2: �ݬݦ��L�ӷ����� (-->�p���ӷ�����:�h���ɦܨӷ�����)
				res.sendRedirect(location);
				return;
				}
			 }catch (Exception ignored) { }

		    res.sendRedirect(req.getContextPath() + "/frontend/member/member/welcome.jsp"); // *�u�@3:(-->�p�L�ӷ�����:�h���ɦ�.jsp
																								
			/*************************** ��L�i�઺���~�B�z *************************************/
			
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
		System.out.println("header=" + header); // ���ե�
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
		System.out.println("filename=" + filename); // ���ե�
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}
}
