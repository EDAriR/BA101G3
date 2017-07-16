<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Login BBQ</title>

    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/backend/assets/css/bootstrap.css" rel="stylesheet"/>
    <!--external css-->
    <link href="<%=request.getContextPath()%>/backend/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>

    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/backend/assets/css/style.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/backend/assets/css/style-responsive.css" rel="stylesheet"/>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style>
        .btn-theme {
            border-color: #FF9800;
            background-color: #FFC107;
        }

        .btn-theme:hover {
            background-color: #ff5113;
        }

        .form-login h2.form-login-heading {
            border-color: #FF9800;
            background-color: #FFC107;/* 各種障礙style.css 1575 */
        }
    </style>
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
<!-- **********************************************************************************************************************************************************
MAIN CONTENT
*********************************************************************************************************************************************************** -->

<div id="login-page">
    <div class="container">
        看一下到底有沒有登出${sessionScope.adm_no}
        <form class="form-login" METHOD="post" action="<%=request.getContextPath()%>/backend/admin/adminServlet.do">
            <h2 class="form-login-heading">sign in now</h2>
            <div class="login-wrap">
                <input type="text" class="form-control" name="adminAc" placeholder="Admin ID" autofocus>
                <br>
                <input type="password" class="form-control" name="adminPsd" placeholder="Password">
                <label class="checkbox">
		                <span class="pull-right">
		                    <a data-toggle="modal" href="login.html#myModal"> Forgot Password?</a>

		                </span>
                </label>
                <button class="btn btn-theme btn-block" href="index.html" type="submit">
                    <i class="fa fa-lock"></i> SIGN IN
                </button>

                <input type="hidden" name="action" value="login">

            </div>

            <!-- Modal -->
            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal"
                 class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">Forgot Password ?</h4>
                        </div>
                        <div class="modal-body">
                            <p>Enter your e-mail address below to reset your password.</p>
                            <input type="text" name="email" placeholder="Email" autocomplete="off"
                                   class="form-control placeholder-no-fix">

                        </div>
                        <div class="modal-footer">
                            <button data-dismiss="modal" class="btn btn-default" type="button">Cancel</button>
                            <button class="btn btn-theme" type="button">Submit</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- modal -->

        </form>

    </div>
</div>

<!-- js placed at the end of the document so the pages load faster -->
<script src="<%=request.getContextPath()%>/backend/assets/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/backend/assets/js/bootstrap.min.js"></script>

<!--BACKSTRETCH-->
<!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
<script type="text/javascript"
        src="<%=request.getContextPath()%>/backend/blocked/assets/js/jquery.backstretch.min.js"></script>
<script>
    $.backstretch("<%=request.getContextPath()%>/backend/blocked/assets/img/moteher.jpg", {speed: 500});
</script>


</body>
</html>
