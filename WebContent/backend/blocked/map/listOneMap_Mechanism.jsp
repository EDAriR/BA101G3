<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.map.model.*"%>
<%-- 此頁練習採用 Script 的寫法取值 --%>

<%-- 取出 Concroller EmpServlet.java已存入request的EmpVO物件--%>
<%Map_MechanismVO map_mechanismVO = (Map_MechanismVO) request.getAttribute("map_mechanismVO");%>
<html>
<head>
<title>地圖機構資料 - listOneMap_Mechanism.jsp</title>
</head>
<body bgcolor='white'>
<b><font color=red>此頁練習採用 Script 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='1000'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>地圖機構資料 - listOneMap_Mechanism.jsp</h3>
		<a href="<%=request.getContextPath()%>/backend/map/select_map_mechanism_page.jsp">回首頁</a>
		</td>
	</tr>
</table>

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
	<tr align='center' valign='middle'>
			<td>${map_mechanismVO.map_no}</td>
			<td><c:choose>
					<c:when test="${map_mechanismVO.mapc_no == 0}">親子餐廳</c:when>
					<c:when test="${map_mechanismVO.mapc_no == 1}">嬰幼兒用品店</c:when>
					<c:when test="${map_mechanismVO.mapc_no == 2}">醫院、衛生所</c:when>	
				</c:choose>
			</td>
			<td>${map_mechanismVO.map_name}</td>
			<td>${map_mechanismVO.map_adds}${map_mechanismVO.map_addc}</td>
			<td>${map_mechanismVO.map_phone}</td>
			<td>${map_mechanismVO.map_mail}</td>			
			<td>${map_mechanismVO.map_cnt}</td>
			<td>${map_mechanismVO.map_photo}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/map/map_mechanism.do">
			     <input type="submit" value="修改"> 
			     <input type="hidden" name="map_no" value="${map_mechanismVO.map_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/map/map_mechanism.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="map_no" value="${map_mechanismVO.map_no}">
			    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
	</tr>
</table>

</body>
</html>
