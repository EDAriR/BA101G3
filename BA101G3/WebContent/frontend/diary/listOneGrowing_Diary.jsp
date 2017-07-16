<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.diary.model.*"%>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Controller EmpServlet.java已存入request的EmpVO物件--%>
<% Growing_DiaryVO growing_diaryVO = (Growing_DiaryVO) request.getAttribute("growing_diaryVO"); %>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
	<title>BaBeQ</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/BA101G3//frontend/css/babeq.css">
	<!--[if lt IE 9]>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="/BA101G3/utility/ckeditor/ckeditor.js"></script>
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<link rel="stylesheet" href="/BA101G3/utility/ckeditor/contents.css">
	<script src="/BA101G3/utility/ckfinder/ckfinder.js"></script>
<title>成長日誌資料 - listOneMap_Mechanism.jsp</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-default navbar-fixed-top " role="navigation">
			<div class="container">
					<!-- 手機隱藏選單區 -->
					<div><a class="navbar-brand" href="index.html"><img src="/BA101G3/frontend/pic/babeq3.png" width="230"></a></div>
					<div class="collapse navbar-collapse navbar-ex1-collapse">
						<!-- 右選單 -->
			
						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown">
								<a href="#" class="navbarIcon dropdown-toggle dropdownColor" data-toggle="dropdown">
									<img class="headerIcon" src="/BA101G3/frontend/pic/list_01.png" width="80">
								</a>
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
							<li><a href="#" class="navbarIcon"><img class="headerIcon" src="/BA101G3/frontend/pic/ring_01.png" width="80"></a></li>
							<li><a href="#" class="navbarIcon"><img class="headerIcon" src="/BA101G3/frontend/pic/login_01.png" width="80"></a></li>
						</ul>
					</div>
					<img src="/BA101G3/frontend/pic/pencilline.png" class="navbarLine" style="margin-top: 0px">
					<!-- 手機隱藏選單區結束 -->
				</div>
			</nav>
		</header>

	<section>
		<div class="container">
			<div class="btn-group btnDiaryControl">
				<FORM NAME="updateOne" METHOD="post" ACTION="<%=request.getContextPath()%>/diary/growing_diary.do">
				     <input type="hidden" name="gd_no" value="${growing_diaryVO.gd_no}">
				     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
				     <input type="hidden" name="action"	value="getOne_For_Update">
				</FORM>
				<FORM NAME="deleteOne" METHOD="post" ACTION="<%=request.getContextPath()%>/diary/growing_diary.do" data-confirm="是否確認刪除成長日誌？">
				    <input type="hidden" name="gd_no" value="${growing_diaryVO.gd_no}">
				    <input type="hidden" name="action"value="delete">
			    </FORM>
					<a href="javascript:document.updateOne.submit()" class="btn btn-default" role="button">修改</a>
			    	<a href="javascript:document.deleteOne.submit()" class="btn btn-default" role="button" id="deleteBtn">刪除</a>	 
			</div>
			<div class="oneDiaryBorder">
				<div class="col-xs-12 col-sm-12">
					<h3>${growing_diaryVO.gd_title}</h3>
					<% java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				  	   java.sql.Timestamp gd_date = growing_diaryVO.getGd_date();
					   String gd_date_toString = sdf.format(gd_date);
					%>
					<p><%= gd_date_toString %><br><br>
					${growing_diaryVO.gd_cnt}
					</p>
				</div>
			</div>
		</div>
	</section>
	
	<footer>
		<div class="container">
			<img src="/BA101G3/frontend/pic/pencilline.png" class="navbarLine">
			<div class="footerBottom navbar-right navbar-nav">
				<a href="#" class="navbarIcon"><img class="headerIcon" src="/BA101G3/frontend/pic/chat_01.png" width="80"></a>
			</div>
		</div>
	</footer>
	
	<script>
		$(document).on('submit', 'form[data-confirm]', function(e){
		    if(!confirm($(this).data('confirm'))){
		        e.stopImmediatePropagation();
		        e.preventDefault();
		    }
		});
	</script>
	
</body>
</html>
