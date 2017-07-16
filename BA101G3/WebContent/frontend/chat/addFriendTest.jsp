<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.member.model.*" %>


<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list", list);
%>
<jsp:useBean id="cfSvc" scope="page" class="com.chat.model.Chat_FriendService"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=BIG5">

    <title>BBQ Admin 資格審核</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/backend/blocked/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css  ICON-->
    <link href="<%=request.getContextPath()%>/backend/blocked/assets/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/backend/blocked/assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/backend/blocked/assets/js/gritter/css/jquery.gritter.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/backend/blocked/assets/lineicons/style.css">
    <link href="<%=request.getContextPath()%>/backend/blocked/assets/css/style-responsive.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/backend/blocked/assets/css/table-responsive.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/backend/blocked/assets/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/backend/blocked/assets/css/style-responsive.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<section id="container">

    <!--main content start-->
    <section id="main-content">
        <section class="wrapper">
            <h3><i class="fa fa-angle-right">++++</h3>
            <div class="row">
                <div class="col-md-12">
                    <div class="content-panel">
                        <h3>看一下到底有沒有登出${sessionScope.adm_no}</h3>
                        <h3>看一下到底有沒有登出${mem_no}</h3>>
                        <h3>看一下到底有沒有登出${mem_name}</h3>

                        <table class="table table-striped table-advance table-hover">
                            <thead>
                            <th><i class="fa fa-bullhorn"></i> 會員編號</th>
                            <th class="hidden-phone"><i class="fa fa-question-circle"></i>會員等級編號</th>
                            <th><i class="fa fa-bookmark"></i> 會員姓名</th>
                            <th><i class=" fa fa-edit"></i> Status</th>
                            <th><i class=" fa fa-edit"></i> Status</th>
                            <th></th>
                            </thead>
                            <tbody>


                            <c:forEach var="memberVO" items="${list}">
                            <tr>

                                <td>${memberVO.mem_no}</td>
                                <td>${memberVO.memg_gr}</td>
                                <td>${memberVO.mem_name}</td>
                                <td>${cfSvc.getOneF(memberVO.mem_no,mem_no).cf_no}</td>
                                <td>${cfSvc.getOneF(memberVO.mem_no,mem_no).cf_is_del}</td>
                                <td>${cfSvc.getOneF(mem_no, memberVO.mem_no).cf_no}</td>
                                <td>${cfSvc.getOneF(mem_no, memberVO.mem_no).cf_is_del}</td>


                                <td>
                                    <FORM METHOD="post"
                                          ACTION="<%=request.getContextPath()%>/chat/ChatFriend/ChatServlet.do">
                                        <!-- 加上frontend路徑,使controller path 與update_member_input.jsp路徑一致 -->
                                        <input type="submit" value="修改">
                                        <input type="hidden" name="mem_no_o" value="${memberVO.mem_no}">
                                        <input type="hidden" name="mem_no_s" value="${mem_no}">
                                        <input type="hidden" name="url" value="<%=request.getServletPath()%>">
                                        <!--送出當前是第幾頁給Controller-->
                                        <input type="hidden" name="action" value="insert"></FORM>
                                </td>
                            </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
            </div>
        </section>
    </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="<%=request.getContextPath()%>/backend/blocked/assets/js/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/backend/blocked/assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript"
            src="<%=request.getContextPath()%>/backend/blocked/assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="<%=request.getContextPath()%>/backend/blocked/assets/js/jquery.scrollTo.min.js"></script>
    <script src="<%=request.getContextPath()%>/backend/blocked/assets/js/jquery.nicescroll.js"
            type="text/javascript"></script>
    <!--common script for all pages 共用JS-->
    <script src="<%=request.getContextPath()%>/backend/blocked/assets/js/common-scripts.js"></script>
    <!--script for this page-->
    <script>
        //刪除 custom select box
    </script>

</body>
</html>