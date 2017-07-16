<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.diary.model.*"%>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Controller EmpServlet.java已存入request的EmpVO物件--%>
<% Growing_DiaryVO growing_diaryVO = (Growing_DiaryVO) request.getAttribute("growing_diaryVO"); %>
<html>
<head>
<title>成長日誌資料 - listOneMap_Mechanism.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 Script 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='1000'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>成長日誌資料 - listOneMap_Mechanism.jsp</h3>
		<a href="<%=request.getContextPath()%>/frontend/diary/select_growing_diary_page.jsp">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='1000'>
	<tr>
		<th>成長日誌編號</th>
		<th>嬰兒編號</th>
		<th>成長日誌標題</th>
		<th>成長日誌日期</th>
		<th>成長日誌內容</th>
		<th>分享範圍</th>
	</tr>
	<tr align='center' valign='middle'>
			<td>${growing_diaryVO.gd_no}</td>
			<%-- <td>${growing_diaryVO.baby_no}</td> --%>
			<td><%= growing_diaryVO.getBaby_no() %></td>
			<td>${growing_diaryVO.gd_title}</td>
			<% java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/diary/growing_diary.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="gd_no" value="${growing_diaryVO.gd_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
	</tr>
</table>

</body>
</html>
