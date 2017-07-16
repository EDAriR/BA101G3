<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.member.model.*"%>


<%
	MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>所有員工資料 - listAllmember.jsp</title>
</head>
<body bgcolor='white'>
--${param.mem_no}----${param.whichPage}--
<b><font color=red>此頁練習採用 EL 的寫法取值:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='900'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>所有員工資料 - ListAllmember.jsp</h3>
		<a href="<%=request.getContextPath()%>/frontend/member/select_page.jsp"><img src="<%=request.getContextPath()%>/frontend/member/images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
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

<table border='1' bordercolor='#CCCCFF' width='900'>
	<tr>
		<td>會員編號</td>
		<td>會員等級編號</td>
		<td>專家分類編號</td>
		<td>會員帳號</td>
		<td>會員姓名</td>
		<th>會員照片</th>
		<td>會員地址(縣市)</td>
		<td>會員電話</td>
		<td>會員電子信箱</td>

	</tr>
	<%@ include file="page1.file" %> 	
	
	<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>
		
			<td>${memberVO.mem_no}</td>
			<td>${memberVO.memg_gr}</td>
			<td>${memberVO.exp_no}</td>
			<td>${memberVO.mem_acct}</td>
			<td>${memberVO.mem_name}</td>
			<td><img src="<%=request.getContextPath()%>/frontend/MemberPhotoAllReader/MemberPhotoReader.do?mem_no=${memberVO.mem_no}"></td>
			<td>${memberVO.mem_adds}</td>
			<td>${memberVO.mem_phone}</td>
			<td>${memberVO.mem_mail}</td>
		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/member/member.do"><!-- 加上frontend路徑,使controller path 與update_member_input.jsp路徑一致 -->
			     <input type="submit" value="修改">
			     <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<br>本網頁的路徑:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>
</body>
</html>
