<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.member.model.*"%>
<%
    Object mem_no = session.getAttribute("mem_no");                                         // 從 session內取出 (key) account的值
    if (mem_no == null) {                                             						// 如為 null, 代表此user未登入過 , 才做以下工作
      session.setAttribute("location", request.getRequestURI());       						//*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java)
      response.sendRedirect(request.getContextPath()+"/frontend/member/member/login.jsp");  //*工作2 : 請該user去登入網頁(login.html) , 進行登入
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
					<!-- 手機隱藏選單區 -->
					<div><a class="navbar-brand" href="index.html"><img src="<%=request.getContextPath()%>/frontend/pic/babeq3.png" width="230"></a></div>
					
					<div class="collapse navbar-collapse navbar-ex1-collapse">
						<!-- 右選單 -->
						<ul class="nav navbar-nav navbar-right">
							<li>
								<%
									if((session.getAttribute("mem_no") != null)) {
							    %>
										<div class="welcome">
												<h3>${sessionScope.mem_name}您好,歡迎回來</h3>
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
							<li><a href="#" class="navbarIcon"><img class="headerIcon" src="<%=request.getContextPath()%>/frontend/pic/ring_01.png" width="80"></a></li>
							<li>
								<a href="<%=request.getContextPath()%>/frontend/member/member/logout.jsp" class="navbarIcon">
									<img class="headerIcon" id="inOutPic" src="<%=request.getContextPath()%>/frontend/pic/logout_01.png" width="80" title="登出">
								</a>
							</li>
						</ul>
					</div>
					<img src="<%=request.getContextPath()%>/frontend/pic/pencilline.png" class="navbarLine" style="margin-top: 0px">
					<!-- 手機隱藏選單區結束 -->
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