<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.regex.*" %>
<%@ page import="com.diary.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Growing_DiaryService growing_diarySvc = new Growing_DiaryService();
	List<Growing_DiaryVO> list = growing_diarySvc.getAll();
	pageContext.setAttribute("list", list);
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
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>所有成長日誌資料 - listAllGrowing_Diary.jsp</title>
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
								    		<li><a href='<%=request.getContextPath()%>/PhotoServlet.do?mem_no=${mem_no}&action=getMemBaby_For_Display'>相片</a></li>
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
		<!-- 幻燈片 -->
			<div class="container">
		
				<div id="carousel-id" class="carousel slide " data-ride="carousel">
				    <!-- 幻燈片小圓點區 -->
				    <ol class="carousel-indicators">
				        <li data-target="#carousel-id" data-slide-to="0" class=""></li>
				        <li data-target="#carousel-id" data-slide-to="1" class=""></li>
				        <li data-target="#carousel-id" data-slide-to="2" class="active"></li>
				    </ol>
				    <!-- 幻燈片主圖區 -->
				    <div class="carousel-inner slider">
				        <div class="item">
				            <img class="img-responsive sliderImg" src="/BA101G3/frontend/pic/test/baby01.jpg" alt="">			       
				        </div>
				        <div class="item">
				             <img class="img-responsive sliderImg" src="/BA101G3/frontend/pic/test/baby02.jpg" alt="">		
				        </div>
				        <div class="item active">
					         <img class="img-responsive sliderImg" src="/BA101G3/frontend/pic/test/baby03.png" alt="">
				        </div>
				    </div>
				   
			
			    <!-- 上下頁控制區 -->
				    <a class="left carousel-control" href="#carousel-id" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
				    <a class="right carousel-control" href="#carousel-id" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
				</div>
				<img src="/BA101G3/frontend/pic/pencilline.png" class="navbarLine">
			</div>
		<!-- 成長日誌 -->
			<div class="container hotArtical">
				<div class="topic">
					<ul class="nav navbar-nav navbar-left">
						<li class="dropdown">
							<a href="#" class="navbarIcon dropdown-toggle dropdownColor" data-toggle="dropdown">
								<img class="headerIcon" src="/BA101G3/frontend/pic/list_01.png" width="70">
							</a>
							<ul class="dropdown-menu menuListForDiary">
							    <li>
							    	<a href="#">嬰兒基本資訊</a>
							    	<a href="#">嬰兒狀況分析</a>
							    	<a href="#">成長日誌管理</a>
							    	<a href='<%=request.getContextPath()%>/PhotoServlet.do?mem_no=${mem_no}&action=getMemBaby_For_Display'>相片管理</a>
							    	<a href="#">影片管理</a>
							    	<a href="#">相關資料推薦</a>
							    </li>
							</ul>
						</li>
					</ul>
					<%@ include file="pages/page1.file" %> 
			<%-- 		<%@ include file="pages/page2.file" %> --%>
					   <FORM NAME="changePage" METHOD="post" ACTION="<%=request.getRequestURI()%>">   
					<div class="btn-group btnDiaryControl">
						<a href='<%=request.getContextPath()%>/frontend/diary/addGrowing_Diary.jsp' class="btn btn-default" role="button">新增</a>
						<a href="#" id="deleteBtn" class="btn btn-default" role="button" onclick="deleteCheckboxDisplay()">刪除</a>
					</div>
					<h4 class="pagetext">page:</h4>
							<ul class="nav navbar-nav navbar-right">
								<li class="dropdown pageNumber"><input name="whichPage" type="number" min="1" max="<%=pageNumber%>"
								    id="diaryPage" value="<%=whichPage%>" onchange="javascript:document.changePage.submit()"/></li>
							</ul>
					   </FORM>
					<!-- 成長日誌列表內容 -->
				<form NAME="deleteDiary" METHOD="post" ACTION="<%=request.getContextPath()%>/diary/growing_diary.do">
			     	<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     	<input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
					<c:forEach var="growing_diaryVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
						<c:set var="growing_diaryVO" value="${growing_diaryVO}" />
							<div class="diaryBorder">
								<div class="col-xs-12 col-sm-1 contentHeight checkboxDiv hidden" style="height:280px; text-align: center;">
									<input type="checkbox" name="deleteDiaryList" value="${growing_diaryVO.gd_no}" style="position: absolute; top: 45%;">
								</div>
								<div class="col-xs-12 col-sm-5 contentHeight">
									<div class="diaryImg" style="display: inline-block;">
									<%Growing_DiaryVO growing_diaryVO = (Growing_DiaryVO)pageContext.getAttribute("growing_diaryVO");
									  Pattern p = Pattern.compile("<img[^>]*src=[\\\"']([^\\\"^']*)");
							          Matcher m = p.matcher(growing_diaryVO.getGd_cnt());
							          String srcTag = request.getContextPath()+"/frontend/pic/test/baby03.png";
							          while (m.find()) {
							              String src = m.group();
							              int startIndex = src.indexOf("src=") + 5;
							              srcTag = src.substring(startIndex, src.length());
							              break;
							              }
							        %>
									<img class="img-responsive" src="<%=srcTag%>">
									</div>
								</div>
								<div class="col-xs-12 col-sm-6">
										<h3>${growing_diaryVO.gd_title}</h3>
										<% java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				  						   java.sql.Timestamp gd_date = growing_diaryVO.getGd_date();
										   String gd_date_toString = sdf.format(gd_date);
								  		%>
										<p><%= gd_date_toString %><br><br>
										
										<% String tagPattern = "<{1}[^>]{1,}>{1}";
									       String gd_cnt = growing_diaryVO.getGd_cnt().replaceAll(tagPattern, "");
									       int length = gd_cnt.length();
									       
										   String showPart = null;
										   if(length<250){
											   showPart = gd_cnt;
										   }else{
											   showPart = gd_cnt.substring(0,250);
										   }
										%>
								 		<%=showPart%>
								 		
								 		<input type="hidden" name="action" value="" id="whichAction">
								 		<a href="javascript:document.deleteDiary.submit()" id="${growing_diaryVO.gd_no}" class="listOneDiary">...(閱讀全文)</a>
										<input type="hidden" name="gd_no" value="" id="sendGdNo">
										</p>
								</div>
							</div>
					</c:forEach>
				</form>
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
	function deleteCheckboxDisplay(){
		if($(".checkboxDiv").hasClass("hidden")){
			$(".checkboxDiv").removeClass("hidden");
		}else{
			$("#whichAction").attr("value","delete");
			$("#deleteBtn").attr("href", "javascript:document.deleteDiary.submit()");
		}
	}
	
	$(".listOneDiary").click(function(event){
		  $("#sendGdNo").attr("value", event.target.id);
		  $("#whichAction").attr("value","getOne_For_Display");
	});
	
	</script>


</body>
</html>
