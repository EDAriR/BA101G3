package com.chat.controller;

import com.chat.model.Chat_FriendService;
import com.chat.model.Chat_FriendVO;
import com.chat.model.Chat_Group_ItemService;
import com.chat.model.Chat_Group_ItemVO;

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

@WebServlet("/ChatServlet.do")
public class ChatServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String requestUrl = req.getParameter("url");
        System.out.println("requestUrl in ChatServlet=" + requestUrl);

        if ("update".equals(action)) {
            System.out.println("action:" + action);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
                String cf_no = req.getParameter("cf_no");
                String cfdel = req.getParameter("cfdel");
                String mem_no_s = req.getParameter("mem_no_s");//此為傳入對方會員編號 對應對方mem_no_s
                String mem_no_o = req.getParameter("mem_no_o");//此為傳入我的會員編號 對應對方mem_no_o

                System.out.println("cf_no: " + cf_no + "  cfdel: " + cfdel + " 我mem_no_s: " + mem_no_s + " 對方看我mem_no_o: " + mem_no_o);


                /***************************2.開始修改資料*****************************************/
                Chat_FriendService cfSvc = new Chat_FriendService();
                cfSvc.updateChat_Friend(cf_no, cfdel);
                if (cfdel.equals("1")) {//同意成為好友

                    cfSvc.updateChat_Friend(cf_no,cfdel);

                    if (cfSvc.getOneF(mem_no_o,mem_no_s)==null){//查詢自己好友
                        cfSvc.addChat_Friend(mem_no_o, mem_no_s, cfdel);
                        System.out.println("新增一筆好友cf_no: "+"mem_no_o:" );
                    }else {
                        cfSvc.updateChat_Friend(cfSvc.getOneF(mem_no_o,mem_no_s).getCf_no(),cfdel);
                    }
                }
                if (cfdel.equals("2")) {
                    cfSvc.updateChat_Friend(cf_no, cfdel);
                    System.out.println("delete in update : " + cf_no + " cfdel :" + cfdel);
                }
                /***************************3.修改完成,準備轉交(Send the Success view)*************/
                // String url = "/frontend/chat/ChatFriend/listOneMCF.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(requestUrl);
                successView.forward(req, res);

                /***************************其他可能的錯誤處理*************************************/
            } catch (Exception e) {
                errorMsgs.add("修改資料失敗:" + e.getMessage());
                RequestDispatcher failureView = req
                        .getRequestDispatcher(requestUrl);
                failureView.forward(req, res);
            }
        }

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求  
            System.out.println("action in Chat_FrienServlet.do:" + action);
            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);
            try {
                /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
                String mem_no_s = req.getParameter("mem_no_s");
                String mem_no_o = req.getParameter("mem_no_o");
                String cfdel = "0";//0 = 邀請中
                System.out.println("mem_no_s : " + mem_no_s + " mem_no_o : " + mem_no_o);
                /***************************2.開始新增資料***************************************/
                Chat_FriendService chat_FriendSvc = new Chat_FriendService();
                Chat_FriendVO cfVO;

                if (chat_FriendSvc.getOneF(mem_no_s, mem_no_o) == null) {
                    chat_FriendSvc.addChat_Friend(mem_no_s, mem_no_o, cfdel);
                    System.out.println("進入addChat_Friend");                   
                } else {
                    cfVO = chat_FriendSvc.getOneF(mem_no_s, mem_no_o);
                    if (cfVO.getCf_is_del().equals("2")) {
                        System.out.println("進入cfVO.getCf_is_del().equals(\"2\") : ");
                        chat_FriendSvc.updateChat_Friend(cfVO.getCf_no(), "0");
                        System.out.println("<=-----------------------------------=>");
                    }
                }
                
                /***************************3.新增完成,準備轉交(Send the Success view)***********/
                String url = requestUrl;
                RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
                successView.forward(req, res);
                /***************************其他可能的錯誤處理**********************************/
            }catch (Exception e){
                e.printStackTrace();
                RequestDispatcher failView = req.getRequestDispatcher(requestUrl); // 新增成功後轉交listAllEmp.jsp
                failView.forward(req, res);
            }
        }
        
        if ("getOneMemCFCG".equals(action)) {//87%沒用ㄌ

            System.out.println("getOneMemCFCG \"action\" in C :" + action);
            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);
            HttpSession session = req.getSession();

            try {
                /****************************
                 * 1.接收請求參數 - 輸入格式的錯誤處理
                 **********************/
                String mem_no = (String) session.getAttribute("mem_no");
                //mem_no = req.getParameter("mem_no");

                /*************************** 2.開始查詢資料 *****************************************/

                Chat_Group_ItemService cgiSvc = new Chat_Group_ItemService();
                List<Chat_Group_ItemVO> cglsit = cgiSvc.getOneChat_Group_Mem(mem_no);
                if (cglsit == null) {
                    errorMsgs.add("查無資料");
                }
                Chat_FriendService cfSvc = new Chat_FriendService();
                List<Chat_FriendVO> oneMemCF = cfSvc.getOneMCF(mem_no);
                if (oneMemCF == null) {
                    errorMsgs.add("查無資料");
                }
                System.out.println("oneMemCF:" + oneMemCF.size());
                System.out.println("cglsit:" + cglsit.size());

                /***************************
                 * 3.查詢完成,準備轉交(Send the Success view)
                 *************/
                req.setAttribute("cglsit", cglsit);
                req.setAttribute("oneMemCF", oneMemCF);
                System.out.println("cglsit in listCGs_ByMem_no: " + cglsit.size());
                String url = "/frontend/chat/ChatList.jsp";
                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);

                /*************************** 其他可能的錯誤處理 *************************************/
            } catch (Exception e) {
                errorMsgs.add("無法取得資料:" + e.getMessage());
                RequestDispatcher failureView = req.getRequestDispatcher(requestUrl);
                failureView.forward(req, res);
            }
        }
    }
}
