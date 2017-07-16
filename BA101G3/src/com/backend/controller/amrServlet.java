package com.backend.controller;

import com.article.model.Article_MessageService;
import com.article.model.Article_MessageVO;
import com.article.model.Article_Message_ReportService;
import com.article.model.Article_Message_ReportVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


@WebServlet("/amrServlet")
public class amrServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);

    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if ("listAMRD".equals(action)) {

            System.out.println("listAMRD in amrSrervlet : " + action);

            List<String> errorMsgs = new LinkedList<String>();
            req.setAttribute("errorMsgs", errorMsgs);

            try {
                String mem_no = req.getParameter("mem_no");
                String amsg_no = req.getParameter("amsg_no");

                Article_Message_ReportVO amrVO = new Article_Message_ReportVO();
                Article_MessageVO amVO = new Article_MessageVO();
                Article_Message_ReportService amrSvc = new Article_Message_ReportService();
                Article_MessageService amSvc = new Article_MessageService();
                System.out.println("mem_no: " + mem_no + " amsg_no: " + amsg_no);

                amrVO = amrSvc.getOneArticle_Message_Report(amsg_no, mem_no);
                amVO = amSvc.getOneArticle_Message(amsg_no);

                req.setAttribute("amrVO", amrVO);
                req.setAttribute("amVO", amVO);

                String url = "backend/blocked/Report/listAllAMR.jsp";

                RequestDispatcher successView = req.getRequestDispatcher(url);
                successView.forward(req, res);

            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

        if ("update".equals(action)) {

            System.out.println("update in amrC :" + action);

            String mem_no = req.getParameter("mem_no");
            String amsg_no = req.getParameter("amsg_no");
            String amrpt_is_cert = req.getParameter("amrpt_is_cert");
            String amrpt_unrsn = new String(req.getParameter("amrpt_unrsn").getBytes("ISO-8859-1"), "UTF-8");

            System.out.println("up mem_no: " + mem_no + " amsg_no : " + amsg_no + " amrpt_is_cert :" + amrpt_is_cert + " amrpt_unrsn : " + amrpt_unrsn);

            Article_Message_ReportVO amrVO = new Article_Message_ReportVO();
            Article_MessageVO amVO = new Article_MessageVO();
            Article_Message_ReportService amrSvc = new Article_Message_ReportService();
            Article_MessageService amSvc = new Article_MessageService();
            MemberVO memberVO = new MemberVO();
            MemberService memSvc = new MemberService();

            String reported_mem_no = amSvc.getOneArticle_Message(amsg_no).getMem_no();
            memberVO = memSvc.getOneMember(reported_mem_no);
            String mem_stop = String.valueOf(Integer.parseInt(memberVO.getMem_is_stop()) + 1);

            System.out.println("reported_mem_no in armC :" + reported_mem_no + " mem_stop : " + mem_stop);

            if (amrpt_is_cert.equals("1")) {

                System.out.println("檢舉通過");

                amrSvc.upDate(amrpt_is_cert, amrpt_unrsn, amsg_no, mem_no);
                memberVO = memSvc.getOneMember(reported_mem_no);
                System.out.println("before update getMem_is_stop in amrC: " + memberVO.getMem_name() + memberVO.getMem_is_stop());


                memSvc.updateStop(mem_stop, reported_mem_no);
                amrVO = amrSvc.getOneArticle_Message_Report(amsg_no, mem_no);
                amVO = amSvc.getOneArticle_Message(amsg_no);
                memberVO = memSvc.getOneMember(reported_mem_no);

                System.out.println("after update getMem_is_stop in amrC: " + memberVO.getMem_name() + memberVO.getMem_is_stop());

            } else if (amrpt_is_cert.equals("2")) {
                System.out.println("檢舉 no 通過");
                amrSvc.upDate(amrpt_is_cert, amrpt_unrsn, amsg_no, mem_no);
            }

            amrVO = amrSvc.getOneArticle_Message_Report(amsg_no, mem_no);
            amVO = amSvc.getOneArticle_Message(amsg_no);
            memberVO = memSvc.getOneMember(reported_mem_no);

            System.out.println("getMem_is_stop in amrC: " + memberVO.getMem_name() + memberVO.getMem_is_stop());

            req.setAttribute("amrVO", amrVO);
            req.setAttribute("amVO", amVO);
            String url = "backend/blocked/Report/listAllAMR.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);
            successView.forward(req, res);
        }
    }

}
