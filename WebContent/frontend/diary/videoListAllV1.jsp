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

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Video</title>

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
        
        </div><!-- end row -->
</div><!-- container -->
        


</body>

<!-- fileinput -->
<script>

    $(document).on('ready', function () {
        $("#input-44").fileinput({
            uploadUrl: '<%=request.getContextPath()%>/PhotoUpload?baby_no=<%=(String)session.getAttribute("selected_baby_no")%>',
            maxFilePreviewSize: 10240,
            language: "zh-TW",
            allowedFileExtensions: [".mp4"]
        });
    });
</script>
<!-- fileinput -->
</html>