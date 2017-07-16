package com.diary.controller;

import com.diary.model.BabyService;
import com.diary.model.BabyVO;
import com.diary.model.PhotoService;
import com.diary.model.PhotoVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@WebServlet("/PhotoServlet.do")
public class PhotoServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String requestUrl = req.getParameter("url");
        System.out.println("requestUrl in PhotoServlet=" + requestUrl);
        HttpSession session = req.getSession();


        if ("getMemBaby_For_Display".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            System.out.println("getMemBaby_For_Display in PhotoServlet action: " + action);

            try {
                /***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
                String mem_no = req.getParameter("mem_no");

                System.out.println("getMemBaby_For_Display mem_no  ===>" + mem_no);

                /***************************2.�}�l�d�߸��*****************************************/

                List<PhotoVO> photoList = null;
                List<List<PhotoVO>> babyPhotoList = null;
                BabyService babySvc = new BabyService();

                List<BabyVO> babylist = babySvc.getAll(mem_no);
                System.out.println("babylist: " + babylist.size());

                if (babylist.size() == 0) {
                    errorMsgs.add("�z�S���p�ħ֥h�ͤ@�ӧa!");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("/frontend/diary/select_page.jsp");
                    failureView.forward(req, res);
                    return;//�{�����_
                }

                session.setAttribute("selected_baby_no", "0");

                /***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/

                req.setAttribute("babylist", babylist);
                session.setAttribute("babylist", babylist);

                String url = "/frontend/diary/photo/listBabyPhoto.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
                successView.forward(req, res);

                /***************************��L�i�઺���~�B�z*************************************/
            } catch (Exception e) {
                errorMsgs.add("�L�k���o���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/frontend/diary/select_page.jsp");
                failureView.forward(req, res);
            }
        }

        if ("getbaby_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            System.out.println("getbaby_For_Display in PhotoServlet action: " + action);

            try {
                /***************************1.�����ШD�Ѽ� - ��J�榡�����~�B�z**********************/
                String baby_no = req.getParameter("baby_no");
                System.out.println("baby_no : " + baby_no);
                session.getAttribute("babylist");
                session.setAttribute("selected_baby_no", baby_no);

                /***************************2.�}�l�d�߸��*****************************************/
                PhotoService photoSvc = new PhotoService();
                List<PhotoVO> photoList = photoSvc.findBybaby(baby_no);
                System.out.println("baby_no: " + baby_no);
                System.out.println("photoList: " + photoList.size());
                if (photoList == null) {
                    errorMsgs.add("�d�L���");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/diary/select_page.jsp");
                    failureView.forward(req, res);
                    return;//�{�����_
                }

                /***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
                req.setAttribute("photoList", photoList); // ��Ʈw���X��empVO����,�s�Jreq
                String url = "/frontend/diary/photo/listBabyPhoto.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
                successView.forward(req, res);

                /***************************��L�i�઺���~�B�z*************************************/
            } catch (Exception e) {
                errorMsgs.add("�L�k���o���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/frontend/diary/select_page.jsp");
                failureView.forward(req, res);
            }
        }


        if ("getOne".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            System.out.println("getOne in PhotoServlet action: " + action);

            try {
                String pho_no = req.getParameter("pho_no");
                System.out.println(pho_no);

                PhotoService photoSvc = new PhotoService();
                PhotoVO photoVO = photoSvc.getOnePhoto(pho_no);
                /***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
                req.setAttribute("photoVO", photoVO);
                String url = "/frontend/diary/photo/showPhoto.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
                successView.forward(req, res);

            } catch (Exception e) {
                errorMsgs.add("�L�k���o���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/frontend/diary/photo/showPhoto.jsp");
                failureView.forward(req, res);
            }

        }


        if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD
        	
        	System.out.println("update action in PhotoServlet: " + action);

            /***************************1.�����ШD�Ѽ�***************************************/
            String pho_no = req.getParameter("pho_no");
//            String baby_no = "BABY0007";
            String baby_no = req.getParameter("baby_no");
            String pho_title = new String(req.getParameter("pho_title").getBytes("ISO-8859-1"), "UTF-8");
            String pho_annot = new String(req.getParameter("pho_annot").getBytes("ISO-8859-1"), "UTF-8");
//            String pho_shr = "1";
            String pho_shr = req.getParameter("pho_shr");
            System.out.println("update pho_no: " + pho_no + " pho_title : " + pho_title + " pho_annot" + pho_annot);

            /***************************2.�}�l�d�߸��***************************************/
            PhotoService photoSvc = new PhotoService();
            photoSvc.updatePhoto(baby_no, pho_title, pho_annot, pho_shr, pho_no);
            PhotoVO photoVO = photoSvc.getOnePhoto(pho_no);
            System.out.println("photoVO " + photoVO.getPho_no());
            /***************************3.�d�ߧ���,�ǳ����(Send the Success view)*************/
            req.setAttribute("photoVO", photoVO);
            String url = "/frontend/diary/photo/showPhoto.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���listOneEmp.jsp
            successView.forward(req, res);

        }

        if ("insert".equals(action)) { // �Ӧ�addEmp.jsp���ШD  

        }


        if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp ��  /dept/listEmps_ByDeptno.jsp���ШD

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // �e�X�R�����ӷ��������|: �i�ର�i/emp/listAllEmp.jsp�j ��  �i/dept/listEmps_ByDeptno.jsp�j �� �i /dept/listAllDept.jsp�j �� �i /emp/listEmps_ByCompositeQuery.jsp�j

            try {
                /***************************1.�����ШD�Ѽ�***************************************/
                String pho_no = req.getParameter("pho_no");
                System.out.println(pho_no);

                /***************************2.�}�l�R�����***************************************/
                PhotoService photoSvc = new PhotoService();
                PhotoVO photoVO = photoSvc.getOnePhoto(pho_no);
                photoSvc.deletePhoto(pho_no);
                System.out.println(photoVO);

                /***************************3.�R������,�ǳ����(Send the Success view)***********/

                String url = "/frontend/diary/photo/deleteImg.html";
                RequestDispatcher successView = req.getRequestDispatcher(url); // �R�����\��,���^�e�X�R�����ӷ�����
                successView.forward(req, res);
                System.out.println(url);

                /***************************��L�i�઺���~�B�z**********************************/
            } catch (Exception e) {
                errorMsgs.add("�R����ƥ���:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }
    }
}
