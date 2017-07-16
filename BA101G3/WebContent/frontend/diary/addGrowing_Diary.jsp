<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.diary.model.*"%>
<%
	Growing_DiaryVO growing_diaryVO = (Growing_DiaryVO) request.getAttribute("growing_diaryVO");
%>

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
	<script>
		$(document).ready(function() {
			var editor = CKEDITOR.replace( 'gd_cnt', {
				width:'100%',
				filebrowserBrowseUrl : '/BA101G3/utility/ckfinder/ckfinder.html',
				filebrowserImageBrowseUrl : '/BA101G3/utility/ckfinder/ckfinder.html?type=Images', 
				filebrowserFlashBrowseUrl : '/BA101G3/utility/ckfinder/ckfinder.html?type=Flash',
				filebrowserUploadUrl : '/BA101G3/utility/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files', 
				filebrowserImageUploadUrl : '/BA101G3/utility/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images', 
				filebrowserFlashUploadUrl : '/BA101G3/utility/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash' 
			});
			CKFinder.setupCKEditor( editor, '/ckfinder/' );
		});
		
	</script>
	<!-- datepicker -->
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<!-- 	<script>
		$(function() {
			$("#datepicker").datepicker({
				showOn : "button",
				buttonImage : "/BA101G3/pic/icon/calendar_02.png",
				buttonImageOnly : true,
				changeMonth: true,
				changeYear: true,
				dateFormat: 'yy-mm-dd',
			});
		});
	</script>
	<style>
		img.ui-datepicker-trigger{
			width: 21px;
			position: relative;
			margin-left: 8px;
			vertical-align: middle;
		}
	</style> -->
	<!-- datepicker -->
	<!-- cxCalendar -->
	<link rel="stylesheet" href="/BA101G3/utility/jQuery.cxCalendar-1.5.3/css/jquery.cxcalendar.css">
	<!-- cxCalendar -->
<title>成長日誌資料新增 - addGrowing_Diary.jsp</title></head>

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
			<div class="col-xs-12 col-sm-10 col-sm-offset-1 centerTable">
				<table border='1' cellpadding='5' cellspacing='0' style='width: 100%'>
					<tr align='center' valign='middle' height='20'>
						<td>
							<div class='tableTitleBorder'>
								<h3>成長日誌資料新增 - addGrowing_Diary.jsp</h3>
						   		<a href="<%=request.getContextPath()%>/frontend/diary/select_growing_diary_page.jsp">回首頁</a>
							</div>
					    </td>
					</tr>
				</table>
				
				<h3>成長日誌資料:</h3>
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
				
				<FORM NAME="insertConfirm" METHOD="post" ACTION="<%=request.getContextPath()%>/diary/growing_diary.do" name="form1" enctype="multipart/form-data">
				<table border="0" style='width: 100%'>
					<!-- <tr>
						<td>成長日誌日期:</td>
						<td><input type="text" id="datepicker" name="gd_date" /></td>
					</tr> -->
					<tr>
						<td>成長日誌日期:</td>
						<td><input type="text" id="cxCalendar" name="gd_date"></td>
					</tr>
					<!-- cxCalendar -->
					<script src="http://cdn.staticfile.org/jquery/2.1.1-rc2/jquery.min.js"></script>
					<script src="/BA101G3/utility/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.min.js"></script>
					<script src="/BA101G3/utility/jQuery.cxCalendar-1.5.3/js/jquery.cxcalendar.languages.js"></script>
					<script>
				
					$("#cxCalendar").cxCalendar({
				  		type: 'datetime',
				  		format: 'YYYY-MM-DD HH:mm:ss'
					});
				
					</script>
					<!-- cxCalendar -->
					<tr>
						<td>嬰兒編號:</td>
						<td><input type="TEXT" name="baby_no" size="45" 
							value="<%= (growing_diaryVO==null)? "" : growing_diaryVO.getBaby_no()%>" /></td>
					</tr>
					<tr>
						<td>成長日誌標題:</td>
						<td><input type="TEXT" name="gd_title" size="45"
							value="<%= (growing_diaryVO==null)? "" : growing_diaryVO.getGd_title()%>" /></td>
					</tr>
					<tr>
						<td style="vertical-align: middle;">成長日誌內容:</td>
						<td><textarea rows="10" cols="80" name="gd_cnt" id="gd_cnt" style="max-width: 100%;">
							<%= (growing_diaryVO==null)? "" : growing_diaryVO.getGd_cnt()%>
							</textarea>
						</td>
					</tr>
					<tr>
						<td>分享範圍:</td>
						<td>
							<input type="radio" name="gd_shr" value="0" id="0" checked><label for="0">僅限自己</label><br>
				  			<input type="radio" name="gd_shr" value="1" id="1"><label for="1">僅限好友</label><br>
				 			<input type="radio" name="gd_shr" value="2" id="2"><label for="2">公開</label>
						</td>
					</tr>
				</table>
				<br>
				<input type="hidden" name="action" value="insert">
				</FORM>
			</div>
			<div class="col-xs-12 col-sm-10 col-sm-offset-1 updateConfirmPadding">
				<div class="btn-group btnDiaryControl">
					<a href="javascript:document.insertConfirm.submit()" class="btn btn-default updateConfirm" role="button">送出新增</a>
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
	
</body>

</html>
