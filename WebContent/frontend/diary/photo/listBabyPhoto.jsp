<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.diary.model.PhotoService" %>
<%@ page import="com.diary.model.PhotoVO" %>
<%@ page import="com.diary.model.BabyVO" %>
<%@ page import="java.util.*" %>


<%
    PhotoService photoSvc = new PhotoService();

    List<PhotoVO> list = (List<PhotoVO>) request.getAttribute("photoList");
    List<BabyVO> babylist = (List<BabyVO>) request.getAttribute("babylist");

    pageContext.setAttribute("photoSvc", photoSvc);
    pageContext.setAttribute("list", list);
    session.setAttribute("babylist", babylist);
%>

<!DOCTYPE html>
<html lang="zh-TW">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Title Page</title>

    <!-- babeq -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/css/babeq.css">

    <!-- bootstrap -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- Add fancyBox -->
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/frontend/diary/photo/fancyBox3/jquery.fancybox.css">
    <script src="<%=request.getContextPath()%>/frontend/diary/photo/fancyBox3/jquery.fancybox.min.js"></script>

    <%------------Masonry------------------%>
    <script src="http://masonry.desandro.com/masonry.pkgd.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery.imagesloaded/3.0.4/jquery.imagesloaded.js"></script>

    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/css/fileinput.min.css">
    <script src="<%=request.getContextPath()%>/frontend/js/bootstrap-fileinput/piexif.min.js"
            type="text/javascript"></script>
    <!-- sortable.min.js is only needed if you wish to sort / rearrange files in initial preview.
         This must be loaded before fileinput.min.js -->
    <script src="<%=request.getContextPath()%>/frontend/js/bootstrap-fileinput/sortable.min.js"
            type="text/javascript"></script>
    <!-- purify.min.js is only needed if you wish to purify HTML content in your preview for HTML files.
         This must be loaded before fileinput.min.js -->
    <script src="<%=request.getContextPath()%>/frontend/js/bootstrap-fileinput/purify.min.js"
            type="text/javascript"></script>
    <!-- the main fileinput plugin file -->
    <script src="<%=request.getContextPath()%>/frontend/js/bootstrap-fileinput/fileinput.min.js"></script>
    <script src="<%=request.getContextPath()%>/frontend/js/bootstrap-fileinput/zh-TW.js"></script>


    <!--[if lt IE 9]>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <style>
        /*----------Masonry----------------*/
        * {
            box-sizing: border-box;
        }

        /* force scrollbar */
        html {
            overflow-y: scroll;
        }

        body {
            font-family: sans-serif;
        }

        /* ---- grid ---- */

        /* clear fix */
        .grid:after {
            content: '';
            display: block;
            clear: both;
        }

        /* ---- .grid-item ---- */
        .grid-sizer,
        .grid-item {
            width: 33.333%;
        }

        .grid-item {
            float: left;
        }

        .grid-item img {
            display: block;
            max-width: 100%;
        }

        /*----------Masonry----------------*/
        .file-drop-zone {
            height: 30%;
        }

        .file-drop-zone-title {
            z-index: 1000;
        }

        .nav > li > a {
            padding: 10px 0px;
        }

    </style>
</head>
<body>
<header>
    <nav class="navbar navbar-default navbar-fixed-top " role="navigation">
        <div class="container">
            <!-- 手機隱藏選單區 -->
            <div><a class="navbar-brand" href="index.html"><img
                    src="<%=request.getContextPath()%>/frontend/pic/babeq3.png" width="230"></a></div>
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <!-- 右選單 -->

                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="#" class="navbarIcon dropdown-toggle dropdownColor"
                                            data-toggle="dropdown"><img class="headerIcon"
                                                                        src="<%=request.getContextPath()%>/frontend/pic/list_01.png"
                                                                        width="80"></a>
                        <ul class="dropdown-menu menuList arrow_box">
                            <li class="dropdown-submenu">
                                <a href="#">會員專區</a>
                                <ul class="dropdown-menu submenuList">
                                    <li><a href="#">會員資料修改</a></li>
                                    <li><a href="#">申請資深爸爸媽媽資格</a></li>
                                    <li><a href="#">申請專家資格</a></li>
                                    <li><a href="#">訂單管理</a></li>
                                    <li><a href="#">好友/群組管理</a></li>
                                    <li><a href="#">關注作者</a></li>
                                    <li><a href="#">關注文章</a></li>
                                </ul>
                            </li>
                            <li class="dropdown-submenu"><a href="#">成長日誌管理</a>
                                <ul class="dropdown-menu submenuList">
                                    <li><a href="diary.html">成長日誌</a></li>
                                    <li><a href="#">相片</a></li>
                                    <li><a href="#">影片</a></li>
                                </ul>
                            </li>
                            <li class="dropdown-submenu"><a href="#">文章專欄</a>
                                <ul class="dropdown-menu submenuList">
                                    <li><a href="#">專家文章專欄</a></li>
                                    <li><a href="#">資深爸爸媽媽經驗分享</a></li>
                                </ul>
                            </li>
                            <li><a href="#">提問專區</a></li>
                            <li><a href="#">購物專區</a></li>
                            <li><a href="#">地圖專區</a></li>
                            <li><a href="#">最新消息</a></li>
                        </ul>
                    </li>
                    <li><a href="#" class="navbarIcon"><img class="headerIcon"
                                                            src="<%=request.getContextPath()%>/frontend/pic/ring_01.png"
                                                            width="80"></a></li>

                    <li>
                        <a href="#" class="navbarIcon">
                            <img class="headerIcon" id="inOutPic"
                                 src="<%=request.getContextPath()%>/frontend/pic/login_01.png" width="80" title="登入">
                        </a>
                    </li>
                </ul>
            </div>
            <img src="<%=request.getContextPath()%>/frontend/pic/pencilline.png" class="navbarLine"
                 style="margin-top: 0px">
            <!-- 手機隱藏選單區結束 -->
        </div>
    </nav>
</header>


<section><!-- header -->

    <div id="lightBox" style="display:none">
        <div class="container">
            <div class="row">
                <div class="col-md-6 col-md-offset-3">
                    <div class="panel-login">

                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-6">
                                    <a href="#" class="comic" id="login-form-link">Login</a>
                                </div>
                                <div class="col-xs-6">
                                    <a href="#" id="register-form-link" class="active comic">Register</a>
                                </div>
                            </div>
                        </div>

                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="login-form" action="<%=request.getContextPath()%>/member/member.do"
                                          method="post" style="display: block;" enctype="multipart/form-data">
                                        <div class="form-horizontal">
                                            <div class="form-group error">
                                                <c:if test="${not empty errorMsgs}">
                                                    <font>請修正以下錯誤:
                                                        <ul>
                                                            <c:forEach var="message" items="${errorMsgs}">
                                                                <li>${message}</li>
                                                            </c:forEach>
                                                        </ul>
                                                    </font>
                                                </c:if>
                                            </div>
                                            <div class="form-group">
                                                <label for="l_mem_acct"
                                                       class="col-xs-12 col-sm-3 control-label">會員帳號</label>
                                                <div class="col-xs-12 col-sm-9">
                                                    <input type="text" name="mem_acct" id="l_mem_acct"
                                                           class="form-control" value="${memberVO.mem_acct}"
                                                           maxlength="20">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="l_mem_pwd"
                                                       class="col-xs-12 col-sm-3 control-label">會員密碼</label>
                                                <div class="col-xs-12 col-sm-9">
                                                    <input type="password" name="mem_pwd" id="l_mem_pwd"
                                                           class="form-control" value="${memberVO.mem_pwd}"
                                                           maxlength="20">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-6 col-sm-offset-3 text-center">
                                                        <a href="#" class="btn btn-default"
                                                           onclick="document.getElementById('login-form').submit();"><i
                                                                class="glyphicon glyphicon-ok"></i> 登入</a>
                                                        <a href="#" class="btn btn-default btn-cancel"><i
                                                                class="glyphicon glyphicon-remove"></i> 取消</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-lg-12">
                                                        <div class="text-center">
                                                            <a href="#" class="forgot-password comic">Forgot
                                                                Password?</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <input type="hidden" name="action" value="login">
                                            <input type="hidden" name="requestURL"
                                                   value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                                        </div>
                                    </form>
                                    <form id="register-form" action="<%=request.getContextPath()%>/member/member.do"
                                          method="post" style="display: none;" enctype="multipart/form-data">
                                        <div class="form-horizontal">
                                            <c:if test="${not empty errorMsgs_r}">
                                                <font color='blue'>請修正以下錯誤:
                                                    <ul>
                                                        <c:forEach var="message" items="${errorMsgs_r}">
                                                            <li>${errorMsgs_r}</li>
                                                        </c:forEach>
                                                    </ul>
                                                </font>
                                            </c:if>
                                            <div class="form-group">
                                                <label for="r_mem_acct"
                                                       class="col-xs-12 col-sm-3 control-label">會員帳號</label>
                                                <div class="col-xs-12 col-sm-9">
                                                    <input type="TEXT" name="mem_acct" id="r_mem_acct"
                                                           class="form-control" value="${memberVO.mem_acct}"
                                                           maxlength="20">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="r_mem_pwd"
                                                       class="col-xs-12 col-sm-3 control-label">會員密碼</label>
                                                <div class="col-xs-12 col-sm-9">
                                                    <input type="password" name="mem_pwd" id="r_mem_pwd"
                                                           class="form-control" placeholder="please enter password"
                                                           size="20" value="${memberVO.mem_pwd}" maxlength="20">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="r_mem_name"
                                                       class="col-xs-12 col-sm-3 control-label">會員姓名</label>
                                                <div class="col-xs-12 col-sm-9">
                                                    <input type="TEXT" name="mem_name" id="r_mem_name"
                                                           class="form-control" placeholder="please enter your name"
                                                           size="20" value="${memberVO.mem_name}" maxlength="20">
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="zone1" class="col-xs-12 col-sm-3 control-label">會員地址</label>
                                                <div class="col-xs-12 col-sm-9">
                                                    <select id="zone1" name="mem_adds" class="form-control"
                                                            value="${memberVO.mem_adds}" style="width: 110px;"></select>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-6 col-sm-offset-3 text-center">

                                                        <a href="#" class="btn btn-default"
                                                           onclick="document.getElementById('register-form').submit();"><i
                                                                class="glyphicon glyphicon-ok"></i> 登入</a>
                                                        <a href="#" class="btn btn-default btn-cancel"><i
                                                                class="glyphicon glyphicon-remove"></i> 取消</a>
                                                    </div>
                                                </div>
                                            </div>
                                            <br>
                                            <input type="hidden" name="requestURL"
                                                   value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
                                            <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
                                            <input type="hidden" name="action" value="register">
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>

</section><!-- end header -->


<div class="container">
    <div class="row">


        <form class="" role="form" METHOD="post" ACTION="<%=request.getContextPath()%>/PhotoServlet.do">
            <div class="form-group">
                <label for="sel1">小孩選擇:</label>
                <select class="form-control" id="sel1" name="baby_no">
                    <c:forEach var="BabyVO" items="${babylist}">
                        <option value="${BabyVO.baby_no}">${BabyVO.baby_aka}</option>
                    </c:forEach>
                </select>
            </div>
            <input type="hidden" name="action" value="getbaby_For_Display">
            <button type="submit" class="btn btn-default btn-lg btn-block">
                <span class="glyphicon glyphicon-picture"></span> 確認
            </button>
        </form>

        <!-- upload -->
        <a href="#" class="btn btn-default btn-lg disabled btn-block"><span
                class="glyphicon glyphicon-hand-down"></span> 快來上傳更多的小寶貝們可愛的照片吧!</a>
        <input id="input-44" name="input44[]" type="file" multiple class="file-loading">
        <!-- end upload -->

        <div style="height: 10px"></div>

        <!-- grid -->
        <div class="grid">
            <div class="grid-sizer"></div>
            <%-- 第一次進來相簿 --%>
            <c:if test="${sessionScope.selected_baby_no.equals('0')}">
                <c:forEach var="BabyVO" items="${babylist}">
                    <c:forEach var="PhotoVO" items="${ photoSvc.findBybaby(BabyVO.baby_no)}">
                        <div class="grid-item">
                            <a data-fancybox data-type="iframe" class="btn btn-default btn-lg btn-block"
                               data-src="<%=request.getContextPath()%>/PhotoServlet.do?action=getOne&pho_no=${PhotoVO.pho_no}"
                               href="javascript:;">
                                    ${PhotoVO.pho_title}
                            </a>
                            <a data-fancybox="images"
                               href="<%=request.getContextPath()%>/PhotoReader?pho_no=${PhotoVO.pho_no}">
                                <img src="<%=request.getContextPath()%>/PhotoReader?pho_no=${PhotoVO.pho_no}"
                                     alt="${PhotoVO.pho_annot}">
                            </a>
                        </div>
                    </c:forEach>
                </c:forEach>
            </c:if>
            <%-- 第一次進來相簿 --%>

            <%--  選擇小孩後進來 --%>
            <c:if test="${!sessionScope.selected_baby_no.equals('0')}">
                <c:forEach var="PhotoVO" items="${list}">
                    <div class="grid-item">
                        <a data-fancybox data-type="iframe" class="btn btn-default btn-lg btn-block"
                           data-src="<%=request.getContextPath()%>/PhotoServlet.do?action=getOne&pho_no=${PhotoVO.pho_no}" href="javascript:;">
                                ${PhotoVO.pho_title}
                        </a>
                        <a data-fancybox="images"
                           href="<%=request.getContextPath()%>/PhotoReader?pho_no=${PhotoVO.pho_no}">
                            <img src="<%=request.getContextPath()%>/PhotoReader?pho_no=${PhotoVO.pho_no}"
                                 alt="${PhotoVO.pho_annot}">
                        </a>
                    </div>
                </c:forEach>
            </c:if>
            <%--  選擇小孩後進來 --%>
        </div><!-- end grid -->

    </div><!-- end row -->
</div><!-- container -->

<%@ include file="/frontend/chat/footer.jsp" %>

</body>
<script>
    <%------------Masonry------------------%>
    $(document).ready(function () {
        $('.box').masonry({
            itemSelector: 'item',
        });
        // external js: masonry.pkgd.js, imagesloaded.pkgd.js
        // init Masonry
        var $grid = $('.grid').masonry({
            itemSelector: '.grid-item',
            percentPosition: true,
            columnWidth: '.grid-sizer'
        });
        // layout Masonry after each image loads
        $grid.imagesLoaded().progress(function () {
            $grid.masonry();
        });
    });
</script>

<%------------Masonry------------------%>

<!-- fileinput -->
<script>

    $(document).on('ready', function () {
        $("#input-44").fileinput({
            uploadUrl: '<%=request.getContextPath()%>/PhotoUpload?baby_no=<%=(String)session.getAttribute("selected_baby_no")%>&action=p',
            maxFilePreviewSize: 10240,
            language: "zh-TW",
            allowedFileExtensions: ["jpg", "png", "gif", "jpeg"]
        });
    });
</script>
<!-- fileinput -->
</html>