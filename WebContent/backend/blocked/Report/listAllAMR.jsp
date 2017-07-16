<%@ page language="java" contentType="text/html; charset=BIG5"
         pageEncoding="BIG5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.article.model.*" %>
<% int rowsPerPage = 1;  //每頁的筆數
    int rowNumber = 0;      //總筆數
    int pageNumber = 0;     //總頁數
    int whichPage = 1;      //第幾頁
    int pageIndexArray[] = null;
    int pageIndex = 0;
    Article_Message_ReportService amrSvc = new Article_Message_ReportService();
    List<Article_Message_ReportVO> list = amrSvc.getAll();
    pageContext.setAttribute("list", list);
%>

<%
    rowNumber = list.size();
    if (rowNumber % rowsPerPage != 0)
        pageNumber = rowNumber / rowsPerPage + 1;
    else pageNumber = rowNumber / rowsPerPage;

    pageIndexArray = new int[pageNumber];
    for (int i = 1; i <= pageIndexArray.length; i++)
        pageIndexArray[i - 1] = i * rowsPerPage - rowsPerPage;
%>

<% try {
    whichPage = Integer.parseInt(request.getParameter("whichPage"));
    pageIndex = pageIndexArray[whichPage - 1];
} catch (NumberFormatException e) { //第一次執行的時候
    whichPage = 1;
    pageIndex = 0;
} catch (ArrayIndexOutOfBoundsException e) { //總頁數之外的錯誤頁數
    if (pageNumber > 0) {
        whichPage = pageNumber;
        pageIndex = pageIndexArray[pageNumber - 1];
    }
}
%>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=BIG5">

    <title>BBQ Admin ARTICLE MESSAGE REPORT</title>
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
                    <a href="index.html">
                        <img src="../pic/babeq3.png" width="200">
                    </a>
                </p>
                <h5 class="centered" style="font-size: 24px; font-family: 'Microsoft JhengHei'">
                    <a href="javascript:;">
                        <span style="color: #636363">
                        <i class="fa fa-sign-out" aria-hidden="true"></i>
                        阿肥</span>
                    </a>
                </h5>
                </li>

                <li class="sub-menu">
                    <a class="active" href="javascript:;">
                        <i class="fa fa-desktop"></i>
                        <span>會員資料管理</span>
                    </a>
                    <ul class="sub">
                        <li><a href="../member/general.html">專家審核</a></li>
                        <li><a href="../LinkExample.html">資深爸媽審核</a></li>
                        <li><a href="panels.html">買家檢舉</a></li>
                        <li><a href="panels.html">商家檢舉</a></li>
                        <li><a href="panels.html">商品檢舉</a></li>
                        <li><a href="panels.html">留言檢舉</a></li>
                        <li><a href="basic_report.html">聊天室用戶檢舉</a></li>
                        <li><a href="panels.html">會員資料管理-文章</a></li>
                        <li><a href="panels.html">會員資料管理-商家</a></li>
                        <li><a href="panels.html">會員資料管理-聊天室</a></li>
                    </ul>
                </li>

                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-car"></i>
                        <span>推薦資料管理</span>
                    </a>
                    <ul class="sub">
                        <li><a href="basic_map.html">地圖機構</a></li>

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
                        <li><a href="login.html">文章分類</a></li>
                        <li><a href="lock_screen.html">商品分類</a></li>
                        <li><a href="lock_screen.html">問題分類</a></li>
                        <li><a href="lock_screen.html">專家分類</a></li>
                    </ul>
                </li>
                <li class="sub-menu">
                    <a href="basic_news.html">
                        <i class="fa fa-bell"></i>
                        <span>最新消息管理</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-credit-card"></i>
                        <span>員工帳戶管理</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class=" fa fa-legal"></i>
                        <span>權限管理</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class=" fa fa-database"></i>
                        <span>系統參數管理</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-bar-chart-o"></i>
                        <span>後台分析管理</span>
                    </a>
                </li>

            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>
    <!--sidebar end-->

    <!-- **********************************************************************************************************************************************************
    MAIN CONTENT  這邊開始改 BBQ
    *********************************************************************************************************************************************************** -->
    <!--main content start-->
    <section id="main-content" style="width: 97%">
        <section class="wrapper">

            <h3><i class="fa fa-angle-right"></i> USER REPORT</h3>
            <div class="row">

                <style>
                    .table-striped tbody tr:nth-of-type(odd) {
                        background-color: #ffeba3;
                    }
                </style>

                <div class="row mt">

                    <div class="col-sm-12">
                        <div class="content-panel">
                            <table class="table table-striped table-advance table-hover table-bordered">
                                <h4><i class="fa fa-angle-right"></i> 文章留言檢舉</h4>
                                <hr>
                                <thead>
                                <tr>
                                    <th><i class="fa fa-bullhorn"></i> 被檢舉人</th>
                                    <th class="hidden-phone"><i class="fa fa-question-circle"></i>檢舉日期</th>
                                    <th><i class="fa fa-bookmark"></i> 檢舉人</th>
                                    <th><i class=" fa fa-edit"></i>審核狀態</th>
                                    <th><i class=" fa fa-edit"></i>審核</th>

                                </tr>

                                </thead>
                                <tbody>
                                <c:forEach items="${list}" var="amrVO" begin="<%=pageIndex%>"
                                           end="<%=pageIndex+rowsPerPage-1%>">
                                    <tr>
                                        <form method="post" action="<%=request.getContextPath()%>/amrServlet">
                                            <td><a href="basic_table.html#">${amrVO.amsg_no}文章留言編號</a></td>
                                            <td class="hidden-phone">${amrVO.amrpt_date}</td>
                                            <td>${memSvc.getOneMember(amrVO.mem_no).mem_name}</td>
                                                <%--會員編號(檢舉人)--%>
                                            <td>$${(amrVO.amrpt_is_cert eq "0") ? '待審核':'已審核'}</td>
                                            <td>
                                                <input type="hidden" name="mem_no" value="${amrVO.mem_no}">
                                                <input type="hidden" name="amsg_no" value="${amrVO.amsg_no}">
                                                <input type="hidden" name="action" value="listAMRD">
                                                <button type="submit" class="btn btn-secondary">展開</button>
                                            </td>
                                        </form>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>

                        </div><!-- /content-panel -->
                    </div><!-- /col-md-12 -->
                    <div class="col-sm-12">
                        <div class="col-sm-12" style="height: 10px"></div>
                    </div>

                    <%if (request.getAttribute("amrVO") != null) {%>
                    <jsp:include page="AMRdetail.jsp"/>
                    <%} %>


                </div><!-- /row -->
            </div>

        </section>
        <! --/wrapper -->

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

        </div>


    </section><!-- /MAIN CONTENT -->

    <!--main content end-->


    <!--footer start-->
    <footer class="site-footer">
        <div class="text-center">
            2017 - BaBeQ
            <a href="basic_table.html#" class="go-top">
                <i class="fa fa-angle-up"></i>
            </a>
        </div>
    </footer>
    <!--footer end-->
</section>


<!-- js placed at the end of the document so the pages load faster -->
<script src="../assets/js/jquery.js"></script>
<script src="../assets/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="../assets/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="../assets/js/jquery.scrollTo.min.js"></script>
<script src="../assets/js/jquery.nicescroll.js" type="text/javascript"></script>


<!--common script for all pages 共用JS-->
<script src="../assets/js/common-scripts.js"></script>

<!--script for this page-->

<script>
    //刪除 custom select box

</script>
</body>
</html>