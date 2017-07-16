<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.member.model.*" %>


<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list", list);
%>
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

    <!-- **********************************************************************************************************************************************************
    MAIN SIDEBAR MENU
    *********************************************************************************************************************************************************** -->

    <!--sidebar start 側邊欄-->
    <aside>
        <div id="sidebar" class="nav-collapse ">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu" id="nav-accordion">
                <p class="centered">
                    <a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">
                        <img src="<%=request.getContextPath()%>/backend/blocked/assets/img/babeq3.png" width="190">
                    </a>
                </p>
                <h5 class="centered" style="font-size: 24px;">
                    <form id="logout" method="POST"
                          action="<%=request.getContextPath()%>/backend/admin/adminServlet.do">
                        <input type="hidden" name="action" value="logout">
                        <a href="javascript:void()" onclick="document.getElementById('logout').submit();">
                    <span style="color: #636363">
                        <i class="fa fa-sign-out" aria-hidden="true"></i>
                        ${adm_name}</span>
                        </a>
                    </form>
                </h5>

                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-desktop"></i>
                        <span>會員資料管理</span>
                    </a>
                    <ul class="sub">
                        <li><a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">專家審核</a></li>
                        <li><a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">資深爸媽審核</a></li>
                        <!-- <li><a href="member/buyer_report.html">買家檢舉</a></li>
                        <li><a href="member/seller_reprot.html">賣家檢舉</a></li>
                        <li><a href="member/product_report.html">商品檢舉</a></li>
                        <li><a href="member/product_message_report.html">商品留言檢舉</a></li> -->
                        <li><a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">回覆問題檢舉</a></li>
                        <li><a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">留言檢舉</a></li>
                        <li><a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">聊天室用戶檢舉</a></li>
                        <li><a href="panels.html">會員資料管理</a></li>
                    </ul>
                </li>
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-car"></i>
                        <span>推薦資料管理</span>
                    </a>
                    <ul class="sub">
                        <li><a href="map.html">推薦資料管</a></li>
                        <li><a href="todo_list.html">副食品</a></li>
                    </ul>
                </li>
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-book"></i>
                        <span>分類管理</span>
                    </a>
                    <ul class="sub">
                        <li><a href="blank.html">購物區分類</a></li>
                        <li><a href="backend/login.html">文章分類</a></li>
                        <li><a href="lock_screen.html">商品分類</a></li>
                        <li><a href="lock_screen.html">問題分類</a></li>
                        <li><a href="lock_screen.html">專家分類</a></li>
                    </ul>
                </li>
                <li class="sub-menu">
                    <a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">
                        <i class="fa fa-bell"></i>
                        <span>最新消息管理</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">
                        <i class=" fa fa-legal"></i>
                        <span>員工帳戶管理</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">
                        <i class=" fa fa-database"></i>
                        <span>系統參數管理</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">
                        <i class="fa fa-bar-chart-o"></i>
                        <span>後台分析管理</span>
                    </a>
                </li>
            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>
    <!--sidebar end-->

    <!--main content start-->
    <section id="main-content">
        <section class="wrapper">
            <h3><i class="fa fa-angle-right"></i>還有 9487 個 待審核； 看一下到底有沒有登出${sessionScope.adm_no}</h3>
            <div class="row">
                <div class="col-md-12">
                    <div class="content-panel">
                        <table class="table table-striped table-advance table-hover">
                        <%@ include file="page1.file" %>
                            <h4><i class="fa fa-angle-right"></i> 第<%=whichPage%>/<%=pageNumber%>頁</h4>
                            <hr>
                            <thead>
                            <tr>
                                <th><i class="fa fa-bullhorn"></i> 會員編號</th>
                                <th class="hidden-phone"><i class="fa fa-question-circle"></i>會員等級編號</th>
                                <th><i class="fa fa-bookmark"></i> 會員姓名</th>
                                <th><i class=" fa fa-edit"></i> Status</th>
                                <th><i class=" fa fa-edit"></i> Status</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            

                            <c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>"
                                       end="<%=pageIndex+rowsPerPage-1%>">
                            <tr>

                                <td>${memberVO.mem_no}</td>
                                <td>${memberVO.memg_gr}</td>
                                <td>${memberVO.mem_name}</td>

                                <td>
                                    <FORM METHOD="post"
                                          ACTION="<%=request.getContextPath()%>/frontend/member/member.do">
                                        <!-- 加上frontend路徑,使controller path 與update_member_input.jsp路徑一致 -->
                                        <input type="submit" value="修改">
                                        <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
                                        <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                                        <!--送出本網頁的路徑給Controller-->
                                        <input type="hidden" name="whichPage" value="<%=whichPage%>">
                                        <!--送出當前是第幾頁給Controller-->
                                        <input type="hidden" name="action" value="getOne_For_Update"></FORM>
                                </td>
                            </tr>
                            </c:forEach>
                        </table>
                        <nav aria-label="Page navigation">
                            <ul class="pagination pagination pagination-lg">
                                <%if (rowsPerPage < rowNumber) {%>
                                <%if (pageIndex >= rowsPerPage) {%>
                                <li>
                                    <a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <%}%>
                                <%if (pageNumber > 1) {%>
                                <%for (int i = 1; i <= pageNumber; i++) {%>
                                <li><a href="<%=request.getRequestURI()%>?whichPage=<%=i%>"><%=i%>
                                </a></li>
                                <%}%>
                                <%}%>

                                <%if (pageIndex < pageIndexArray[pageNumber - 1]) {%>
                                <li>
                                    <a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                                <%}%>
                                <%}%>
                            </ul>
                        </nav>

                        <%@ include file="page2.file" %>


                        <!--footer start-->
                        <footer class="site-footer">
                            <div class="text-center">
                                2017 - BaBeQ
                                <a href="#main-content" class="go-top">
                                    <i class="fa fa-angle-up"></i>
                                </a>
                            </div>
                        </footer>
                        <!--footer end-->
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