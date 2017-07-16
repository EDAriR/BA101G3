<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.admin.model.*" %>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%-- <%
   /*  Authority_FeatureService authority_FeatureSvc = new Authority_FeatureService();
     List<Authority_FeatureVO> list = authority_FeatureSvc.getAll();
     pageContext.setAttribute("list",list); */
%> --%>

<jsp:useBean id="authority_FeatureSvc" scope="page"
             class="com.admin.model.Authority_FeatureService"/>

<html>
<head>
    <title>所有部門 - listAllAF.jsp</title>
    <!-- Bootstrap core CSS -->
    <link href="../assets/css/bootstrap.css" rel="stylesheet">
    <!--external css  ICON-->
    <script src="../assets/js/jquery.js"></script>
    <script src="../assets/js/bootstrap.min.js"></script>
    <link href="../assets/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link
            href="//netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.css"
            rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="../assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css"
          href="../assets/js/gritter/css/jquery.gritter.css"/>
    <link rel="stylesheet" type="text/css"
          href="../assets/lineicons/style.css">
    <link href="../assets/css/style-responsive.css" rel="stylesheet">
    <link href="../assets/css/table-responsive.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="../assets/css/style.css" rel="stylesheet">
    <link href="../assets/css/style-responsive.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        body {
            font-family: Microsoft JhengHei;
        }
    </style>
</head>
<body>

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
                    <img src="images/babeq3.png" width="190">
                </a>
            </p>
            <h5 class="centered" style="font-size: 24px; font-family: 'Microsoft JhengHei';">
                <a href="javascript:;">
                    <span style="color: #636363">
                        <i class="fa fa-sign-out" aria-hidden="true"></i>
                        阿肥</span>
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
<section id="main-content">
    <section class="wrapper">
        <h3>
            <i class="fa fa-angle-right"></i> <a
                href="<%=request.getContextPath()%>/select_page.jsp"> <img
                src="images/babeq3.png" width="378" height="141" border="0">
        </a>
        </h3>
        <%-- 錯誤表列 --%>
        <c:if test="${not empty errorMsgs}">
            <ul>
                請修正以下錯誤:
                <c:forEach var="message" items="${errorMsgs}">
                    <li>${message}</li>
                </c:forEach>
            </ul>
        </c:if>
        <div class="row">
            <div class="col-md-12">
                <div class="content-panel">
                    <table class="table table-striped table-advance table-hover">
                        <h4>
                            <i class="fa fa-angle-right"></i>員工權限管理 - ListAllAF.jsp
                        </h4>
                        <hr>
                        <thead>
                        <tr>
                            <th><i class="fa fa-bullhorn"></i>權限編號</th>
                            <th><i class="fa fa-bullhorn"></i>權限名稱</th>
                            <th class="hidden-phone"><i class="fa fa-question-circle"></i>修改</th>
                            <th class="fa fa-bookmark" style="color: red">刪除</th>
                            <th><i class=" fa fa-edit"></i> 查詢</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="authority_FeatureVO" items="${authority_FeatureSvc.all}">
                            <tr>
                                <td>${authority_FeatureVO.auth_no}</td>
                                <td>${authority_FeatureVO.auth_name}</td>
                                <td>
                                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/AuthorityFeatureServlet.do">
                                    <button class="btn btn-large"><i class="icon-pencil icon-spin"></i> 修改</button>
                                    <input type="submit" value="修改" disabled="true">
                                    <input type="hidden" name="auth_no" value="${authority_FeatureVO.auth_no}">
                                    <%--<input type="hidden" name="action" value="getOne_For_Update_Dept">--%>
                                    </FORM>
                                </td>
                                <td>
                                    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/AuthorityFeatureServlet.do">
                                    <button class="btn btn-large"><i class="icon-ban-circle icon-spin"></i> 刪除</button>
                                        <input type="submit" value="刪除"> 
                                        <input type="hidden" name="auth_no" value="${authority_FeatureVO.auth_no}">
                                        <input type="hidden" name="action" value="deleteAF">
                                    </FORM>
                                </td>
                                <td>
                                    <FORM METHOD="post"
                                          ACTION="<%=request.getContextPath()%>/AuthorityFeatureServlet.do">
                                        <button class="btn btn-large">
                                            <i class="icon-refresh icon-spin"></i> 查詢
                                        </button>
                                        <input type="submit" value="送出查詢"> <input
                                            type="hidden" name="auth_no"
                                            value="${authority_FeatureVO.auth_no}"> <input
                                            type="hidden" name="action" value="listAFs_ByAuth_No_B">
                                    </FORM>
                                </td>
                            </tr>
                           
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- /content-panel -->
            </div>
            <!-- /col-md-12 -->
        </div>
        <!-- /row -->
        <!-- /row -->
        <div class="row mt">
            <div class="col-md-12">
                <div class="content-panel">
                    <table class="table table-striped table-advance table-hover">
                        <h4>
                            <i class="fa fa-angle-right"></i> 待審核檢舉
                        </h4>
                        <hr>
                        <thead>
                        <tr>
                            <th><i class="fa fa-bullhorn"></i> 被檢舉類別</th>
                            <th class="hidden-phone"><i class="fa fa-question-circle"></i>
                                被檢舉人
                            </th>
                            <th><i class="fa fa-bookmark"></i>檢舉內容</th>
                            <th><i class=" fa fa-edit"></i> Status</th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><a href="basic_table.html#">Company Ltd</a></td>
                            <td class="hidden-phone">Lorem Ipsum dolor</td>
                            <td>12000.00$</td>
                            <td><a href="#" class="btn btn-default"><span
                                    class="glyphicon glyphicon-share-alt"></span> 展開</a></td>

                        </tr>
                        <tr>
                            <td><a href="basic_table.html#"> Dashgum co </a></td>
                            <td class="hidden-phone">Lorem Ipsum dolor</td>
                            <td>17900.00$</td>
                            <td><a href="#" class="btn btn-default"><span
                                    class="glyphicon glyphicon-share-alt"></span> 展開</a></td>

                        </tr>
                        <tr>
                            <td><a href="basic_table.html#"> Another Co </a></td>
                            <td class="hidden-phone">Lorem Ipsum dolor</td>
                            <td>14400.00$</td>
                            <td><a href="#" class="btn btn-default"><span
                                    class="glyphicon glyphicon-share-alt"></span> 展開</a></td>

                        </tr>
                        <tr>
                            <td><a href="basic_table.html#"> Dashgum ext </a></td>
                            <td class="hidden-phone">Lorem Ipsum dolor</td>
                            <td>22000.50$</td>
                            <td><a href="#" class="btn btn-default"><span
                                    class="glyphicon glyphicon-share-alt"></span> 展開</a></td>

                        </tr>
                        <tr>
                            <td><a href="basic_table.html#">Total Ltd</a></td>
                            <td class="hidden-phone">Lorem Ipsum dolor</td>
                            <td>12120.00$</td>
                            <td><a href="#" class="btn btn-default"><span
                                    class="glyphicon glyphicon-share-alt"></span> 展開</a></td>

                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /content-panel -->
            </div>
            <!-- /col-md-12 -->
        </div>
        <!-- /row -->
    </section>
    <! --/responsive wrapper -->
</section>
<!--main content end-->
<%
    if (request.getAttribute("listAFs_ByAuth_No") != null) {
%>
<jsp:include page="listAFs_ByAuth_No.jsp"/>
<%
    }
%>

</body>
</html>
