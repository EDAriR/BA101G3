<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.diary.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Growing_DiaryService growing_diarySvc = new Growing_DiaryService();
	List<Growing_DiaryVO> list = growing_diarySvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有成長日誌資料 - listAllGrowing_Diary.jsp</title>
</head>
<body bgcolor='white'>
<table border='1' cellpadding='5' cellspacing='0' width='1000'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有成長日誌資料 - listAllGrowing_Diary.jsp</h3>
		<a href="<%=request.getContextPath()%>/frontend/diary/select_growing_diary_page.jsp">回首頁</a>
		</td>
	</tr>
</table>

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

<table border='1' bordercolor='#CCCCFF' width='1000'>
	<tr>
		<th>成長日誌編號</th>
		<th>嬰兒編號</th>
		<th>成長日誌標題</th>
		<th>成長日誌日期</th>
		<th>成長日誌內容</th>
		<th>分享範圍</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="growing_diaryVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<c:set var="growing_diaryVO" value="${growing_diaryVO}" />
		<tr align='center' valign='middle' ${(growing_diaryVO.gd_no==param.gd_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${growing_diaryVO.gd_no}</td>
			<td>${growing_diaryVO.baby_no}</td>
			<td>${growing_diaryVO.gd_title}</td>
			<% java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			   Growing_DiaryVO growing_diaryVO = (Growing_DiaryVO)pageContext.getAttribute("growing_diaryVO");
			   java.sql.Timestamp gd_date = growing_diaryVO.getGd_date();
			   String gd_date_toString = sdf.format(gd_date);
			%>
			<td><%= gd_date_toString %></td>
			<td>${growing_diaryVO.gd_cnt}</td>			
			<td>
				<c:choose>
					<c:when test="${growing_diaryVO.gd_shr == '0'}">僅限自己</c:when>
					<c:when test="${growing_diaryVO.gd_shr == '1'}">僅限好友</c:when>
					<c:when test="${growing_diaryVO.gd_shr == '2'}">公開</c:when>	
				</c:choose>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/diary/growing_diary.do">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="gd_no" value="${growing_diaryVO.gd_no}">
			     <input type="hidden" name="baby_no" value="${growing_diaryVO.baby_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/diary/growing_diary.do">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="gd_no" value="${growing_diaryVO.gd_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="pages/page2.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>
</body>
</html>
