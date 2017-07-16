<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Login BaBeQ</title>
	</head>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script language="JavaScript" src="<%=request.getContextPath()%>/frontend/js/login.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/css/babeq.css">
	
	<!-- Add fancyBox -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.1.20/jquery.fancybox.css"/>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/fancybox/3.1.20/jquery.fancybox.min.js"></script>
    
    <style>
    .fancybox-slide--iframe .fancybox-content {
	
	max-width  : 80%;
	max-height : 80%;
	scrolling : no;
}
</style>
<body>
	
	<header>
			<nav class="navbar navbar-default navbar-fixed-top " role="navigation">
				<div class="container">
					<!-- ������ÿ��� -->
					<div><a class="navbar-brand" href="index.html"><img src="<%=request.getContextPath()%>/frontend/pic/babeq3.png" width="230"></a></div>
					<div class="collapse navbar-collapse navbar-ex1-collapse">
						<!-- �k��� -->
			
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="#" class="navbarIcon dropdown-toggle dropdownColor" data-toggle="dropdown"><img class="headerIcon" src="<%=request.getContextPath()%>/frontend/pic/list_01.png" width="80"></a>
								<ul class="dropdown-menu menuList arrow_box">
								    <li class="dropdown-submenu">
								    	<a href="#">�|���M��</a>
								    	<ul class="dropdown-menu submenuList">
								    		<li><a href="#">�|����ƭק�</a></li>
								    		<li><a data-fancybox data-type="iframe" 
								    		data-src="form-test.html" href="javascript:;">�ӽи�`�����������</a></li>
								    		<li><a href="#">�ӽбM�a���</a></li>
								    		<li><a href="#">�q��޲z</a></li>
								    		<li><a href="#">�n��/�s�պ޲z</a></li>
								    		<li><a href="#">���`�@��</a></li>
								    		<li><a href="#">���`�峹</a></li>
								    	</ul>
								    </li>
										<li class="dropdown-submenu"><a href="#">������x�޲z</a>
											<ul class="dropdown-menu submenuList">
								    		<li><a href="diary.html">������x</a></li>
								    		<li><a href='<%=request.getContextPath()%>/PhotoServlet.do?mem_no=${mem_no}&action=getMemBaby_For_Display'>�ۤ�</a></li>
								    		<li><a href="#">�v��</a></li>
								    	</ul>
										</li>
										<li class="dropdown-submenu"><a href="#">�峹�M��</a>
											<ul class="dropdown-menu submenuList">
								    		<li><a href="#">�M�a�峹�M��</a></li>
								    		<li><a href="#">��`���������g�����</a></li>
								    	</ul>
										</li>
									<li><a href="#">���ݱM��</a></li>
									<li><a href="#">�ʪ��M��</a></li>
									<li><a href="#">�a�ϱM��</a></li>
									<li><a href="#">�̷s����</a></li>
								</ul>
							</li>
							<li><a href="#" class="navbarIcon"><img class="headerIcon" src="<%=request.getContextPath()%>/frontend/pic/ring_01.png" width="80"></a></li>
							
								<li>
									<a href="#" class="navbarIcon">
										<img class="headerIcon" id="inOutPic" src="<%=request.getContextPath()%>/frontend/pic/login_01.png" width="80" title="�n�J">
									</a>
								</li>
						</ul>
					</div>
					<img src="<%=request.getContextPath()%>/frontend/pic/pencilline.png" class="navbarLine" style="margin-top: 0px">
					<!-- ������ÿ��ϵ��� -->
				</div>
			</nav>
		</header>
		
		
		<section>
			<h3>�ݤ@�U�쩳���S���n�X${sessionScope.adm_no}</h3>
			<h3>�ݤ@�U�쩳���S���n�X${mem_no}</h3>
			<h3>�ݤ@�U�쩳���S���n�X${mem_name}</h3>
			
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
											<form id="login-form" action="<%=request.getContextPath()%>/member/member.do" method="post" style="display: block;" enctype="multipart/form-data">
											  <div class="form-horizontal">
											    <div class="form-group error">
											    	<c:if test="${not empty errorMsgs}">
														<font>�Эץ��H�U���~:
															<ul>
																<c:forEach var="message" items="${errorMsgs}">
																	<li>${message}</li>
																</c:forEach>
															</ul>
														</font>
													</c:if>
											    </div>
												<div class="form-group">
													<label for="l_mem_acct" class="col-xs-12 col-sm-3 control-label">�|���b��</label>
													<div class="col-xs-12 col-sm-9">
														<input type="text" name="mem_acct" id="l_mem_acct" class="form-control" value="${memberVO.mem_acct}" maxlength="20">
													</div>
												</div>
												<div class="form-group">
													<label for="l_mem_pwd" class="col-xs-12 col-sm-3 control-label">�|���K�X</label>
													<div class="col-xs-12 col-sm-9">
														<input type="password" name="mem_pwd" id="l_mem_pwd" class="form-control" value="${memberVO.mem_pwd}" maxlength="20"> 
													</div>
												</div>
		
												<div class="form-group">
													<div class="row">
														<div class="col-sm-6 col-sm-offset-3 text-center">				
															<a href="#" class="btn btn-default" onclick="document.getElementById('login-form').submit();"><i class="glyphicon glyphicon-ok"></i> �n�J</a>
															<a href="#" class="btn btn-default btn-cancel"><i class="glyphicon glyphicon-remove"></i> ����</a>
														</div>
													</div>
												</div>
												<div class="form-group">
													<div class="row">
														<div class="col-lg-12">
															<div class="text-center">
																<a href="#" class="forgot-password comic">Forgot Password?</a>
															</div>
														</div>
													</div>
												</div>	
												<input type="hidden" name="action" value="login">
												<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
											  </div>
											</form>
											<form id="register-form" action="<%=request.getContextPath()%>/member/member.do" method="post" style="display: none;" enctype="multipart/form-data">
											  <div class="form-horizontal">
										  		<c:if test="${not empty errorMsgs_r}">
													<font color='blue'>�Эץ��H�U���~:
														<ul>
															<c:forEach var="message" items="${errorMsgs_r}">
																<li>${errorMsgs_r}</li>
															</c:forEach>
														</ul>
													</font>
												</c:if>
												<div class="form-group">
													<label for="r_mem_acct" class="col-xs-12 col-sm-3 control-label">�|���b��</label>
													<div class="col-xs-12 col-sm-9">
														<input type="TEXT" name="mem_acct" id="r_mem_acct" class="form-control" value="${memberVO.mem_acct}" maxlength="20">
													</div>
												</div>
												<div class="form-group">
													<label for="r_mem_pwd" class="col-xs-12 col-sm-3 control-label">�|���K�X</label>
													<div class="col-xs-12 col-sm-9">
														<input type="password" name="mem_pwd" id="r_mem_pwd" class="form-control" placeholder="please enter password" size="20" value="${memberVO.mem_pwd}" maxlength="20">
													</div>
												</div>
												<div class="form-group">
													<label for="r_mem_name" class="col-xs-12 col-sm-3 control-label">�|���m�W</label>
													<div class="col-xs-12 col-sm-9">
														<input type="TEXT" name="mem_name" id="r_mem_name" class="form-control" placeholder="please enter your name" size="20" value="${memberVO.mem_name}" maxlength="20">
													</div>
												</div>
												<div class="form-group">
													<label for="zone1" class="col-xs-12 col-sm-3 control-label">�|���a�}</label>
													<div class="col-xs-12 col-sm-9">
														<select id="zone1" name="mem_adds" class="form-control" value="${memberVO.mem_adds}" style="width: 110px;"></select>
													</div>
												</div>
												
												<div class="form-group">
													<div class="row">
														<div class="col-sm-6 col-sm-offset-3 text-center">
															
															<a href="#" class="btn btn-default" onclick="document.getElementById('register-form').submit();"><i class="glyphicon glyphicon-ok"></i> �n�J</a>
															<a href="#" class="btn btn-default btn-cancel"><i class="glyphicon glyphicon-remove"></i> ����</a>
														</div>
													</div>
												</div>
												<br>
												<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
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

		</section>
		
<!--  <h1>${sessionScope.mem_no}</h1>-->

		<footer>
			<div class="container">
				<img src="<%=request.getContextPath()%>/frontend/pic/pencilline.png" class="navbarLine">
				
				<c:if test="${sessionScope.mem_no!=null}">
					<div class="footerBottom navbar-right navbar-nav">
						<a href="#" class="navbarIcon"><img class="headerIcon" src="<%=request.getContextPath()%>/frontend/pic/chat_01.png" width="80"></a>
					</div>
				</c:if>
			</div>
		</footer>

</body>

<script>
    $("[data-fancybox]").fancybox({
        iframe : {
            css : {
             
                scrolling:'no' 
            }
        }
    });
</script>
</html>