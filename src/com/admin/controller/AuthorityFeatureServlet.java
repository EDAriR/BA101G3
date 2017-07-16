package com.admin.controller;

import com.admin.model.Authority_FeatureService;
import com.admin.model.Authority_FeatureVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/AuthorityFeatureServlet.do")
public class AuthorityFeatureServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        // 來自select_page.jsp的請求                                  // 來自 dept/listAllDept.jsp的請求
        if ("listAFs_ByAuth_No_A".equals(action) || "listAFs_ByAuth_No_B".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /*************************** 1.接收請求參數 ****************************************/
                String auth_no = new String(req.getParameter("auth_no"));

                /*************************** 2.開始查詢資料 ****************************************/
                Authority_FeatureService authority_FeatureSvc = new Authority_FeatureService();
                Authority_FeatureVO authority_FeatureVO = authority_FeatureSvc.getOneAF(auth_no);

                /*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
                req.setAttribute("listAFs_ByAuth_No", authority_FeatureVO);    // 資料庫取出的list物件,存入request

                String url = null;
                if ("listAFs_ByAuth_No_A".equals(action))
                    url = "/AuthorityFeature/listEmps_ByDeptno.jsp";        // 成功轉交 dept/listEmps_ByDeptno.jsp
                else if ("listAFs_ByAuth_No_B".equals(action))
                    url = "/AuthorityFeature/listAllAF.jsp";              // 成功轉交 dept/listAllAF.jsp

                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 ***********************************/
            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        if ("delete_Dept".equals(action)) {

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                //***************************1.接收請求參數***************************************//*
                String auth_no = new String(req.getParameter("auth_no"));

                //***************************2.開始刪除資料***************************************//*
                Authority_FeatureService authority_FeatureSvc = new Authority_FeatureService();
//                authority_FeatureSvc.deleteAF(auth_no);

                //***************************3.刪除完成,準備轉交(Send the Success view)***********//*
                String url = "/dept/listAllAF.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後, 成功轉交 回到 /dept/listAllAF.jsp
                successView.forward(req, res);

                //***************************其他可能的錯誤處理***********************************//*
            } catch (Exception e) {
                errorMsgs.add("刪除資料失敗:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/AuthorityFeature/listAllAF.jsp");
                failureView.forward(req, res);
            }
        }
    }
}
