<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.map.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Map_MechanismService map_mechanismSvc = new Map_MechanismService();
	List<Map_MechanismVO> list = map_mechanismSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有地圖機構資料 - listAllMap_Mechanism.jsp</title>
</head>
<body bgcolor='white'>
<table border='1' cellpadding='5' cellspacing='0' width='1000'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有地圖機構資料 - ListAllMap_Mechanism.jsp</h3>
		<a href="<%=request.getContextPath()%>/backend/map/select_map_mechanism_page.jsp">回首頁</a>
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
		<th>地圖機構編號</th>
		<th>機構種類</th>
		<th>機構名稱</th>
		<th>機構地址</th>
		<th>機構電話</th>
		<th>機構電子信箱</th>
		<th>機構介紹</th>
		<th>機構照片</th>
	</tr>
	<%@ include file="pages/page1.file" %> 
	<c:forEach var="map_mechanismVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle' ${(map_mechanismVO.map_no==param.map_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->
			<td>${map_mechanismVO.map_no}</td>
			<td>
				<c:choose>
					<c:when test="${map_mechanismVO.mapc_no == '0'}">親子餐廳</c:when>
					<c:when test="${map_mechanismVO.mapc_no == '1'}">嬰幼兒用品店</c:when>
					<c:when test="${map_mechanismVO.mapc_no == '2'}">醫院、衛生所</c:when>	
				</c:choose>
			</td>
			<td>${map_mechanismVO.map_name}</td>
			<td>${map_mechanismVO.map_adds}${map_mechanismVO.map_addc}</td>
			<td>${map_mechanismVO.map_phone}</td>
			<td>${map_mechanismVO.map_mail}</td>			
			<td>${map_mechanismVO.map_cnt}</td>
			<td>
			<img src="<%=request.getContextPath()%>/utility/map_mechanismreader?map_no=${map_mechanismVO.map_no}">
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/map/map_mechanism.do">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="map_no" value="${map_mechanismVO.map_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/map/map_mechanism.do">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="map_no" value="${map_mechanismVO.map_no}">
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
