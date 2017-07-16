<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
    Object mem_no = session.getAttribute("mem_no");                                         // �q session�����X (key) account����
    if (mem_no == null) {                                             						// �p�� null, �N��user���n�J�L , �~���H�U�u�@
      session.setAttribute("location", request.getRequestURI());       						//*�u�@1 : �P�ɰO�U�ثe��m , �H�K��login.html�n�J���\�� , ��������ɦܦ�����(���t�XLoginHandler.java)
      response.sendRedirect(request.getContextPath()+"/frontend/member/member/login.jsp");  //*�u�@2 : �и�user�h�n�J����(login.html) , �i��n�J
      return;
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Welcome BaBeQ</title>
	</head>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/css/babeq.css">
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script language="JavaScript" src="<%=request.getContextPath()%>/frontend/js/login.js"></script>

<body>
	<header>
			<nav class="navbar navbar-default navbar-fixed-top " role="navigation">
				<div class="container">
					<!-- ������ÿ��� -->
					<div><a class="navbar-brand" href="index.html"><img src="<%=request.getContextPath()%>/frontend/pic/babeq3.png" width="230"></a></div>
					
					<div class="collapse navbar-collapse navbar-ex1-collapse">
						<!-- �k��� -->
						<ul class="nav navbar-nav navbar-right">
							<li>
								<%
									if((session.getAttribute("mem_no") != null)) {
							    %>
										<div class="welcome">
												<h3>${sessionScope.mem_name}�z�n,�w��^��</h3>
										</div>		
								<%
									}else{
										session.setAttribute("location", request.getRequestURI());
										response.sendRedirect(request.getContextPath()+ "/frontend/member/member/login.jsp");
									}
								%>
							</li>
							<li class="dropdown"><a href="#" class="navbarIcon dropdown-toggle dropdownColor" data-toggle="dropdown"><img class="headerIcon" src="<%=request.getContextPath()%>/frontend/pic/list_01.png" width="80"></a>
								<ul class="dropdown-menu menuList arrow_box">
								    <li class="dropdown-submenu">
								    	<a href="#">�|���M��</a>
								    	<ul class="dropdown-menu submenuList">
								    		<li><a href="#">�|����ƭק�</a></li>
								    		<li><a href="#">�ӽи�`�����������</a></li>
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
								    		<li><a href="#">�ۤ�</a></li>
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
								<a href="<%=request.getContextPath()%>/frontend/member/member/logout.jsp" class="navbarIcon">
									<img class="headerIcon" id="inOutPic" src="<%=request.getContextPath()%>/frontend/pic/logout_01.png" width="80" title="�n�X">
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
	<h3>	${mem_no}</h3>
	<h3>	${mem_name}</h3>
	</section>
	
	<footer>
			<div class="container">
				<img src="<%=request.getContextPath()%>/frontend/pic/pencilline.png" class="navbarLine">
				
				<c:if test="${sessionScope.mem_no!=null}">
					<div class="footerBottom navbar-right navbar-nav" >
						<a href="#" class="navbarIcon"><img class="headerIcon" src="<%=request.getContextPath()%>/frontend/pic/chat_01.png" width="80"></a>
					</div>
				</c:if>
			</div>
	</footer>
	
</body>
</html>