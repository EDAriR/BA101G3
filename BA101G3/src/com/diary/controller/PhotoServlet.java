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
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String mem_no = req.getParameter("mem_no");

                System.out.println("getMemBaby_For_Display mem_no  ===>" + mem_no);

                /***************************2.開始查詢資料*****************************************/

                List<PhotoVO> photoList = null;
                List<List<PhotoVO>> babyPhotoList = null;
                BabyService babySvc = new BabyService();

                List<BabyVO> babylist = babySvc.getAll(mem_no);
                System.out.println("babylist: " + babylist.size());

                if (babylist.size() == 0) {
                    errorMsgs.add("您沒有小孩快去生一個吧!");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req.getRequestDispatcher("/frontend/diary/select_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                session.setAttribute("selected_baby_no", "0");

                /***************************3.查詢完成,準備轉交(Send the Success view)*************/

                req.setAttribute("babylist", babylist);
                session.setAttribute("babylist", babylist);

                String url = "/frontend/diary/photo/listBabyPhoto.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/frontend/diary/select_page.jsp");
                failureView.forward(req, res);
            }
        }

        if ("getbaby_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            System.out.println("getbaby_For_Display in PhotoServlet action: " + action);

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String baby_no = req.getParameter("baby_no");
                System.out.println("baby_no : " + baby_no);
                session.getAttribute("babylist");
                session.setAttribute("selected_baby_no", baby_no);

                /***************************2.開始查詢資料*****************************************/
                PhotoService photoSvc = new PhotoService();
                List<PhotoVO> photoList = photoSvc.findBybaby(baby_no);
                System.out.println("baby_no: " + baby_no);
                System.out.println("photoList: " + photoList.size());
                if (photoList == null) {
                    errorMsgs.add("查無資料");
                }
                // Send the use back to the form, if there were errors
                if (!errorMsgs.isEmpty()) {
                    RequestDispatcher failureView = req
                            .getRequestDispatcher("/frontend/diary/select_page.jsp");
                    failureView.forward(req, res);
                    return;//程式中斷
                }

                /***************************3.查詢完成,準備轉交(Send the Success view)*************/
                req.setAttribute("photoList", photoList); // 資料庫取出的empVO物件,存入req
                String url = "/frontend/diary/photo/listBabyPhoto.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage());
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
                /***************************3.查詢完成,準備轉交(Send the Success view)*************/
                req.setAttribute("photoVO", photoVO);
                String url = "/frontend/diary/photo/showPhoto.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
                successView.forward(req, res);

            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/frontend/diary/photo/showPhoto.jsp");
                failureView.forward(req, res);
            }

        }


        if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
        	
        	System.out.println("update action in PhotoServlet: " + action);

            /***************************1.接收請求參數***************************************/
            String pho_no = req.getParameter("pho_no");
//            String baby_no = "BABY0007";
            String baby_no = req.getParameter("baby_no");
            String pho_title = new String(req.getParameter("pho_title").getBytes("ISO-8859-1"), "UTF-8");
            String pho_annot = new String(req.getParameter("pho_annot").getBytes("ISO-8859-1"), "UTF-8");
//            String pho_shr = "1";
            String pho_shr = req.getParameter("pho_shr");
            System.out.println("update pho_no: " + pho_no + " pho_title : " + pho_title + " pho_annot" + pho_annot);

            /***************************2.開始查詢資料***************************************/
            PhotoService photoSvc = new PhotoService();
            photoSvc.updatePhoto(baby_no, pho_title, pho_annot, pho_shr, pho_no);
            PhotoVO photoVO = photoSvc.getOnePhoto(pho_no);
            System.out.println("photoVO " + photoVO.getPho_no());
            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            req.setAttribute("photoVO", photoVO);
            String url = "/frontend/diary/photo/showPhoto.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交listOneEmp.jsp
            successView.forward(req, res);

        }

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  

        }


        if ("delete".equals(action)) { // 來自listAllEmp.jsp 或  /dept/listEmps_ByDeptno.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            String requestURL = req.getParameter("requestURL"); // 送出刪除的來源網頁路徑: 可能為【/emp/listAllEmp.jsp】 或  【/dept/listEmps_ByDeptno.jsp】 或 【 /dept/listAllDept.jsp】 或 【 /emp/listEmps_ByCompositeQuery.jsp】

            try {
                /***************************1.接收請求參數***************************************/
                String pho_no = req.getParameter("pho_no");
                System.out.println(pho_no);

                /***************************2.開始刪除資料***************************************/
                PhotoService photoSvc = new PhotoService();
                PhotoVO photoVO = photoSvc.getOnePhoto(pho_no);
                photoSvc.deletePhoto(pho_no);
                System.out.println(photoVO);

                /***************************3.刪除完成,準備轉交(Send the Success view)***********/

                String url = "/frontend/diary/photo/deleteImg.html";
                RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
                successView.forward(req, res);
                System.out.println(url);

                /***************************其他可能的錯誤處理**********************************/
            } catch (Exception e) {
                errorMsgs.add("刪除資料失敗:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher(requestURL);
                failureView.forward(req, res);
            }
        }
    }
}
