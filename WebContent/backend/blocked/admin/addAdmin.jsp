<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.admin.controller.SerialNumGenerator" %>
<%@ page import="com.admin.model.*" %>
<%
    AdminVO adminVO = (AdminVO) request.getAttribute("adminVO");
    SerialNumGenerator sng = new SerialNumGenerator();

%>

<html>
<head>
    <title>Login BBQ</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/backend/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css  ICON-->
    <link href="<%=request.getContextPath()%>/backend/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/backend/assets/js/gritter/css/jquery.gritter.css"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/backend/assets/lineicons/style.css">
    <link href="<%=request.getContextPath()%>/backend/assets/css/style-responsive.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/backend/assets/css/table-responsive.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/backend/assets/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/backend/assets/css/style-responsive.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <font color='red'>請修正以下錯誤:
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li>${message}</li>
            </c:forEach>
        </ul>
    </font>
</c:if>
<%-- 錯誤表列 --%>

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
                    <a href="index.jsp">
                        <img src="img/babeq3.png" width="190">
                    </a>
                </p>
                <h5 class="centered" style="font-size: 24px;">
                    <a href="javascript:;">
                    <span style="color: #636363">
                        <i class="fa fa-sign-out" aria-hidden="true"></i>
                        ${adm_name}</span>
                    </a>
                </h5>

                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-desktop"></i>
                        <span>會員資料管理</span>
                    </a>
                    <ul class="sub">
                        <li><a href="member/member_expert.html">專家審核</a></li>
                        <li><a href="member/member_grade.html">資深爸媽審核</a></li>
                        <li><a href="member/buyer_report.html">買家檢舉</a></li>
                        <li><a href="member/seller_reprot.html">賣家檢舉</a></li>
                        <li><a href="member/product_report.html">商品檢舉</a></li>
                        <li><a href="member/product_message_report.html">商品留言檢舉</a></li>
                        <li><a href="member/answer_report.html">回覆問題檢舉</a></li>
                        <li><a href="member/article_message_report.html">留言檢舉</a></li>
                        <li><a href="report.html">聊天室用戶檢舉</a></li>
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
                    <a href="news.html">
                        <i class="fa fa-bell"></i>
                        <span>最新消息管理</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class=" fa fa-legal"></i>
                        <span>員工帳戶管理</span>
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
    <section id="main-content">
        <section class="wrapper">
            <h3><i class="fa fa-angle-right"></i> Form Components</h3>

            <!-- BASIC FORM ELELEMNTS -->
            <div class="row mt">
                <div class="col-lg-12">
                    <div class="form-panel">
                        <h4 class="mb"><i class="fa fa-angle-right"></i> Form Elements</h4>
                        <form class="form-horizontal style-form" method="post" action="AdminServlet.do">

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">員工姓名</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control round-form"
                                           name="adm_name" placeholder="placeholder"
                                           value="<%= (adminVO==null)? "吳永志" : adminVO.getAdm_name()%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">員工帳號</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control round-form"
                                           name="adm_acct" placeholder="adcde123"
                                           value="<%= (adminVO==null)? "adcde123" : adminVO.getAdm_acct()%>">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">員工密碼:</label>
                                <div class="col-sm-10">
                                    <input class="form-control" id="disabledInput"
                                           type="text" value="<%= sng.generator(6)%>"
                                           placeholder="Disabled input here..." disabled>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-2 col-sm-2 control-label">員工信箱</label>
                                <div class="col-sm-10">
                                    <input type="text" class="form-control round-form"
                                           name="adm_acct" placeholder="email@example.com"
                                           value="<%= (adminVO==null)? "aaa@xxx.com" : adminVO.getAdm_mail()%>">
                                </div>
                                <button type="button" class="btn btn-round btn-primary">送出新增</button>
                            </div>
                        </form>
                    </div>
                </div><!-- col-lg-12-->
            </div><!-- /row -->

            <!-- INPUT MESSAGES -->
            <div class="row mt">
                <!-- CUSTOM TOGGLES -->
                <div class="col-lg-12">
                    <div class="form-panel">
                        <h4 class="mb"><i class="fa fa-angle-right"></i> Custom Toggles</h4>
                        <div class="row mt">
                            <div class="col-sm-6 text-center">
                                <input type="checkbox" checked="" data-toggle="switch"/>
                            </div>
                            <div class="col-sm-6 text-center">
                                <input type="checkbox" data-toggle="switch"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div><!-- /row -->


        </section>
        <! --/wrapper -->
    </section><!-- /MAIN CONTENT -->
    <!--main content end-->
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
<script src="<%=request.getContextPath()%>/backend/assets/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/assets/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript"
        src="<%=request.getContextPath()%>/backend/assets/js/jquery.dcjqaccordion.2.7.js"></script>
<script src="<%=request.getContextPath()%>/backend/assets/js/jquery.scrollTo.min.js"></script>
<script src="<%=request.getContextPath()%>/backend/assets/js/jquery.nicescroll.js" type="text/javascript"></script>
<!--common script for all pages 共用JS-->
<script src="<%=request.getContextPath()%>/backend/assets/js/common-scripts.js"></script>
<!--script for this page-->
<script>
    //刪除 custom select box
</script>
</body>
</html>
