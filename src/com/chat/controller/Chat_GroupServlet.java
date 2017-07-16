package com.chat.controller;

import com.chat.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@WebServlet("/chat/Chat_Group/Chat_GroupServlet.do")
public class Chat_GroupServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("getOneMemCFCG".equals(action)) {
            System.out.println("getOneMemCFCG \"action\" in C :" + action);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            HttpSession session = req.getSession();

            try {
                /***************************
                 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
                 **********************/
                String mem_no = req.getParameter("mem_no");
                session.setAttribute("mem_no", mem_no);

                /*************************** 2.�}�l�d�߸�� *****************************************/

                Chat_Group_ItemService cgiSvc = new Chat_Group_ItemService();
                List<Chat_Group_ItemVO> cglsit = cgiSvc.getOneChat_Group_Mem(mem_no);
                if (cglsit == null) {
                    errorMsgs.add("�d�L���");
                }
                Chat_FriendService cfSvc = new Chat_FriendService();
                List<Chat_FriendVO> oneMemCF = cfSvc.getOneMCF(mem_no);

                System.out.println("cfSvc:" + cfSvc + "\n" + "oneMemCF:" + oneMemCF);
                if (oneMemCF == null) {
                    errorMsgs.add("�d�L���");
                }
                /***************************
                 * 3.�d�ߧ���,�ǳ����(Send the Success view)
                 *************/
                req.setAttribute("cglsit", cglsit);
                req.setAttribute("oneMemCF", oneMemCF);
                System.out.println("cglsit in listCGs_ByMemNo: " + cglsit.size());
                String url = "/frontend/chat/listCGsCFs_ByMemNo.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
                successView.forward(req, res);

                /*************************** ��L�i�઺���~�B�z *************************************/
            } catch (Exception e) {
                errorMsgs.add("�L�k���o���:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/listtAllCG.jsp");
                failureView.forward(req, res);
            }
        }

        if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD
            System.out.println("getOne_For_Display \"action\" in C :" + action);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************
                 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
                 **********************/
                String str = req.getParameter("cg_no");
                if (str == null || (str.trim()).length() == 0) {
                    errorMsgs.add("�s�սs��");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("/listtAllCG.jsp");
                    failureView.forward(req, res);
                    return;// �{�����_
                }

                String cg_no = null;
                try {
                    cg_no = new String(str);
                } catch (Exception e) {
                    errorMsgs.add("�s�սs���榡�����T");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("chat/Chat_Group/listtAllCG.jsp");
                    failureView.forward(req, res);
                    return;// �{�����_
                }

                /*************************** 2.�}�l�d�߸�� *****************************************/
                Chat_GroupService cgSvc = new Chat_GroupService();
                Chat_GroupVO chat_GroupVO = cgSvc.getOneCG(cg_no);
                if (chat_GroupVO == null) {
                    errorMsgs.add("�d�L���");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("chat/listtAllCG.jsp");
                    failureView.forward(req, res);
                    return;// �{�����_
                }

                /***************************
                 * 3.�d�ߧ���,�ǳ����(Send the Success view)
                 *************/
                req.setAttribute("chat_GroupVO", chat_GroupVO); // ��Ʈw���X��empVO����,�s�Jreq
                String url = "/frontend/chat/Chat_Group/listOneCG.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
                successView.forward(req, res);

                /*************************** ��L�i�઺���~�B�z *************************************/
            } catch (Exception e) {
                errorMsgs.add("�L�k���o���:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/listtAllCG.jsp");
                failureView.forward(req, res);
            }
        }

        if ("listCGs_ByCgno".equals(action)) { // �Ӧ�select_page.jsp���ШD
            System.out.println("listCGs_ByCgno \"action\" in C :" + action);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************
                 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
                 **********************/
                String cg_no = req.getParameter("cg_no");

                /*************************** 2.�}�l�d�߸�� *****************************************/
                Chat_Group_ItemService cgiSvc = new Chat_Group_ItemService();
                List<Chat_Group_ItemVO> cgilsit = cgiSvc.getOneChat_Group_No(cg_no);
                System.out.println("cgNo in listCGs_ByCgno: " + cg_no);
                if (cgilsit == null) {
                    errorMsgs.add("�d�L���");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("cgiiiiiilsit == null/listtAllCG.jsp");
                    failureView.forward(req, res);
                    return;// �{�����_
                }

                /***************************
                 * 3.�d�ߧ���,�ǳ����(Send the Success view)
                 *************/
                req.setAttribute("cgilsit", cgilsit); // ��Ʈw���X��empVO����,�s�Jreq
                System.out.println("cgilsit in listCGs_ByCgno: " + cgilsit.size());
                String url = "/frontend/chat/Chat_Group_Item/listCGs_ByCgno.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
                successView.forward(req, res);

                /*************************** ��L�i�઺���~�B�z *************************************/
            } catch (Exception e) {
                errorMsgs.add("�L�k���o���:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/listtAllCG.jsp");
                failureView.forward(req, res);
            }
        }

        if ("listCGs_ByMemNo".equals(action)) { // �Ӧ�select_page.jsp���ШD
            System.out.println("listCGs_ByMemNo \"action\" in C :" + action);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            HttpSession session = req.getSession();

            try {
                /***************************
                 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
                 **********************/
                String mem_no = req.getParameter("mem_no");
                session.setAttribute("mem_no", mem_no);
                System.out.println("listCGs_ByMemNo \"memNo\" in C :" + mem_no);
                /*************************** 2.�}�l�d�߸�� *****************************************/
                Chat_Group_ItemService cgiSvc = new Chat_Group_ItemService();
                List<Chat_Group_ItemVO> cglsit = cgiSvc.getOneChat_Group_Mem(mem_no);
                if (cglsit == null) {
                    errorMsgs.add("�d�L���");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("cglsit == null/listtAllCG.jsp");
                    failureView.forward(req, res);
                    return;// �{�����_
                }

                /***************************
                 * 3.�d�ߧ���,�ǳ����(Send the Success view)
                 *************/
                req.setAttribute("cglsit", cglsit); // ��Ʈw���X��empVO����,�s�Jreq
                System.out.println("cglsit in listCGs_ByMemNo: " + cglsit.size());
                String url = "/frontend/chat/Chat_Group_Item/listCGs_ByMemNo.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
                successView.forward(req, res);

                /*************************** ��L�i�઺���~�B�z *************************************/
            } catch (Exception e) {
                errorMsgs.add("�L�k���o���:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("�L�k���o���/listtAllCG.jsp");
                failureView.forward(req, res);
            }
        }

        if ("getOne_For_Update".equals(action)) { // �Ӧ�listAllEmp.jsp ��

            List<String> errorMsgs = new LinkedList<String>();

            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|:

            try {
                /*************************** 1.�����ШD�Ѽ� ****************************************/
                String cg_no = new String(req.getParameter("cg_no"));

                /*************************** 2.�}�l�d�߸�� ****************************************/
                Chat_GroupService cgSvc = new Chat_GroupService();
                Chat_GroupVO chat_GroupVO = cgSvc.getOneCG(cg_no);

                /***************************
                 * 3.�d�ߧ���,�ǳ����(Send the Success view)
                 ************/
                req.setAttribute("chat_GroupVO", chat_GroupVO); // ��Ʈw���X��empVO����,�s�Jreq
                String url = "/chat/update_CG_input.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���update_emp_input.jsp
                successView.forward(req, res);

                /*************************** ��L�i�઺���~�B�z ************************************/
            } catch (Exception e) {
                errorMsgs.add("�ק��ƨ��X�ɥ���:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }

        if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // �e�X�ק諸�ӷ��������|:

            try {
                /***************************
                 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
                 **********************/
                String cg_no = req.getParameter("cg_no").trim();
                String cg_name = req.getParameter("cg_name").trim();
                Date cg_year = java.sql.Date.valueOf(req.getParameter("cg_year").trim());
                String cg_is_ar = req.getParameter("cg_is_ar").trim();
                String cg_is_ab = req.getParameter("cg_is_ab").trim();
                String cg_is_ac = req.getParameter("cg_is_ac").trim();
                String cg_is_sf = req.getParameter("cg_is_sf").trim();
                String cg_is_ad = req.getParameter("cg_is_ad").trim();
                String cg_baby_rd = req.getParameter("cg_baby_rd").trim();

                String mem_no = new String(req.getParameter("mem_no").trim());

                Chat_GroupVO chat_GroupVO = new Chat_GroupVO();
                chat_GroupVO.setCg_no(cg_no);
                chat_GroupVO.setCg_name(cg_name);
                chat_GroupVO.setCg_year(cg_year);
                chat_GroupVO.setCg_is_ar(cg_is_ar);
                chat_GroupVO.setCg_is_ab(cg_is_ab);
                chat_GroupVO.setCg_is_ac(cg_is_ac);
                chat_GroupVO.setCg_is_sf(cg_is_sf);
                chat_GroupVO.setCg_is_ad(cg_is_ad);
                chat_GroupVO.setCg_baby_rd(cg_baby_rd);

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("chat_GroupVO", chat_GroupVO); // �t����J�榡���~��empVO����,�]�s�Jreq
                    RequestDispatcher failureView = req.getRequestDispatcher("/emp/update_emp_input.jsp");
                    failureView.forward(req, res);
                    return; // �{�����_
                }

                /*************************** 2.�}�l�ק��� *****************************************/
                Chat_GroupService cgSvc = new Chat_GroupService();
                chat_GroupVO = cgSvc.updateCG(cg_no, cg_name, cg_year, cg_is_ar, cg_is_ab, cg_is_ac, cg_is_sf, cg_is_ad,
                        cg_baby_rd);

                /***************************
                 * 3.�ק粒��,�ǳ����(Send the Success view)
                 *************/
                Chat_Group_ItemService cgiSvc = new Chat_Group_ItemService();
                if (requestURL.equals("/chat/Chat_Group/listCGs_Bycg_no.jsp")
                        || requestURL.equals("/chat/Chat_group/listtAllCG.jsp"))
                    req.setAttribute("listEmps_ByDeptno", cgiSvc.getOneChat_Group_Mem(mem_no)); // ��Ʈw���X��list����,�s�Jrequest

                if (requestURL.equals("/chat/Chat_group/listEmps_ByCompositeQuery.jsp")) {
                    HttpSession session = req.getSession();
                    List<String[]> map = (List<String[]>) session.getAttribute("map");
                    List<Chat_GroupVO> list = cgSvc.getAll();
                    req.setAttribute("listEmps_ByCompositeQuery", list); // �ƦX�d��,
                    // ��Ʈw���X��list����,�s�Jrequest
                }

                String url = requestURL;
                RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���^�e�X�ק諸�ӷ�����
                successView.forward(req, res);

                /*************************** ��L�i�઺���~�B�z *************************************/
            } catch (Exception e) {
                errorMsgs.add("�ק��ƥ���:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/chat/Chat_group/update_CG_input.jsp");
                failureView.forward(req, res);
            }
        }

        if ("cgmInsert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***********************
                 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
                 *************************/
                String mem_no = req.getParameter("mem_no");
                String cgNo = req.getParameter("cgNo");

                Chat_Group_ItemVO cgiVO = new Chat_Group_ItemVO();

                cgiVO.setCg_no(cgNo);
                cgiVO.setMem_no(mem_no);

                /*************************** 2.�}�l�s�W��� ***************************************/
                Chat_Group_ItemService cgiSvc = new Chat_Group_ItemService();
                cgiSvc.addChat_Group_Item(cgNo, mem_no);

                /***************************
                 * 3.�s�W����,�ǳ����(Send the Success view)
                 ***********/
                List<Chat_Group_ItemVO> cglsit = cgiSvc.getOneChat_Group_Mem(mem_no);
                req.setAttribute("cglsit", cglsit);
                String url = "/frontend/chat/Chat_Group_Item/listCGs_ByMemNo.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
                successView.forward(req, res);

                /*************************** ��L�i�઺���~�B�z **********************************/
            } catch (Exception e) {
                errorMsgs.add("cgmInsert Exception:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
                failureView.forward(req, res);
            }
        }

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***********************
                 * 1.�����ШD�Ѽ� - ��J�榡�����~�B�z
                 *************************/
                String cg_name = req.getParameter("cg_name").trim();
                Date cg_year = Date.valueOf(req.getParameter("cg_year").trim());
                String cg_is_ar = req.getParameter("cg_is_ar").trim();
                String cg_is_ab = req.getParameter("cg_is_ab").trim();
                String cg_is_ac = req.getParameter("cg_is_ac").trim();
                String cg_is_sf = req.getParameter("cg_is_sf").trim();
                String cg_is_ad = req.getParameter("cg_is_ad").trim();
                String cg_baby_rd = req.getParameter("cg_baby_rd").trim();

                java.sql.Date hiredate = null;

                Double sal = null;
                try {
                    sal = new Double(req.getParameter("cg_name").trim());
                } catch (NumberFormatException e) {
                    sal = 0.0;
                    errorMsgs.add("�п�J�s�զW��.");
                }

                try {
                    hiredate = java.sql.Date.valueOf(req.getParameter("cg_year").trim());
                } catch (IllegalArgumentException e) {
                    hiredate = new java.sql.Date(System.currentTimeMillis());
                    errorMsgs.add("�п�J���!");
                }

                Chat_GroupVO chat_GroupVO = new Chat_GroupVO();
                chat_GroupVO.setCg_name(cg_name);
                chat_GroupVO.setCg_year(cg_year);
                chat_GroupVO.setCg_is_ar(cg_is_ar);
                chat_GroupVO.setCg_is_ab(cg_is_ab);
                chat_GroupVO.setCg_is_ac(cg_is_ac);
                chat_GroupVO.setCg_is_sf(cg_is_sf);
                chat_GroupVO.setCg_is_ad(cg_is_ad);
                chat_GroupVO.setCg_baby_rd(cg_baby_rd);

                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    req.setAttribute("chat_GroupVO", chat_GroupVO); // �t����J�榡���~��empVO����,�]�s�Jreq
                    RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
                    failureView.forward(req, res);
                    return;
                }

                /*************************** 2.�}�l�s�W��� ***************************************/
                Chat_GroupService cgSvc = new Chat_GroupService();
                chat_GroupVO = cgSvc.addCG(cg_name, cg_year, cg_is_ar, cg_is_ab, cg_is_ac, cg_is_sf, cg_is_ad,
                        cg_baby_rd);

                /***************************
                 * 3.�s�W����,�ǳ����(Send the Success view)
                 ***********/
                String url = "/emp/listAllEmp.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
                successView.forward(req, res);

                /*************************** ��L�i�઺���~�B�z **********************************/
            } catch (Exception e) {
                errorMsgs.add(e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher("/emp/addEmp.jsp");
                failureView.forward(req, res);
            }
        }

        if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp ��

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|:

            try {
                /*************************** 1.�����ШD�Ѽ� ***************************************/
                String cg_no = new String(req.getParameter("cg_no"));

                /*************************** 2.�}�l�R����� ***************************************/
                Chat_GroupService cgSvc = new Chat_GroupService();
                Chat_GroupVO chat_GroupVO = cgSvc.getOneCG(cg_no);
                cgSvc.deleteCG(cg_no);

                /***************************
                 * 3.�R������,�ǳ����(Send the Success view)
                 ***********/
                Chat_Group_ItemService cgiSvc = new Chat_Group_ItemService();
                if (requestURL.equals("/dept/listEmps_ByDeptno.jsp") || requestURL.equals("/dept/listAllDept.jsp"))
                    req.setAttribute("listEmps_ByDeptno", cgiSvc.getOneChat_Group_No(chat_GroupVO.getCg_no())); // ��Ʈw���X��list����,�s�Jrequest

                if (requestURL.equals("/emp/listEmps_ByCompositeQuery.jsp")) {
                    HttpSession session = req.getSession();
                    Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");
                    List<Chat_GroupVO> list = cgSvc.getAll();
                    req.setAttribute("listEmps_ByCompositeQuery", list); // �ƦX�d��,
                    // ��Ʈw���X��list����,�s�Jrequest
                }

                String url = requestURL;
                RequestDispatcher successView = req.getRequestDispatcher(url); // �R�����\��,���^�e�X�R�����ӷ�����
                successView.forward(req, res);

                /*************************** ��L�i�઺���~�B�z **********************************/
            } catch (Exception e) {
                errorMsgs.add("�R����ƥ���:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }

        if ("cgmdelete".equals(action)) { // �Ӧ�listAllEmp.jsp ��
            System.out.println("cgmdelete in C action: " + action);
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|:

            try {
                /*************************** 1.�����ШD�Ѽ� ***************************************/
                String cgNo = new String(req.getParameter("cgNo"));
                String mem_no = new String(req.getParameter("mem_no"));
                req.getSession().setAttribute("mem_no", mem_no);

                /*************************** 2.�}�l�R����� ***************************************/
                Chat_Group_ItemService cgiSvc = new Chat_Group_ItemService();
                cgiSvc.deleteChat_Group_Item(cgNo, mem_no);
                /***************************
                 * 3.�R������,�ǳ����(Send the Success view)
                 ***********/
                List<Chat_Group_ItemVO> cglsit = cgiSvc.getOneChat_Group_Mem(mem_no);
                ;
                System.out.println("memNo in listCGs_ByCgno: " + mem_no);
                if (cglsit == null) {
                    errorMsgs.add("�d�L���");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("cgiiiiiilsit == null/listtAllCG.jsp");
                    failureView.forward(req, res);
                    return;// �{�����_
                }

                req.setAttribute("cglsit", cglsit); // ��Ʈw���X��empVO����,�s�Jreq
                System.out.println("cglsit in cgmdelete: " + cglsit.size());
                String url = "/frontend/chat/Chat_Group_Item/listCGs_ByMemNo.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
                successView.forward(req, res);

                /*************************** ��L�i�઺���~�B�z **********************************/
            } catch (Exception e) {
                errorMsgs.add("�R����ƥ���:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }            
    }
}