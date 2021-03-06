<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.chat.model.*"%>
<%
Chat_GroupVO chat_GroupVO = (Chat_GroupVO) request.getAttribute("chat_GroupVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<title>員工資料修改 - update_emp_input.jsp</title></head>
<link rel="stylesheet" type="text/css" href="js/calendar.css">
<script language="JavaScript" src="js/calendarcode.js"></script>
<div id="popupcalendar" class="text"></div>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>員工資料修改 - update_CG_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
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

<FORM METHOD="post" ACTION="Chat_GroupServlet.do" name="form1">
<table border="0">
	<tr>
		<td>群組編號:<font color=red><b>*</b></font></td>
		<td><%=chat_GroupVO.getCg_no()%></td>
	</tr>
	<tr>
		<td>群組名稱:</td>
		<td><input type="TEXT" name="cg_name" size="45" value="<%=chat_GroupVO.getCg_name()%>" /></td>
	</tr>
	<tr>
		<td>嬰兒年分:</td>
		<td><input type="TEXT" name="cg_year" size="45"	value="<%=chat_GroupVO.getCg_year()%>" /></td>
	</tr>
	<tr>
		<td>是否有過敏性鼻炎:</td>
		<td><input type="TEXT" name="cg_is_ar" size="45"	value="<%=chat_GroupVO.getCg_is_ar()%>" /></td>
	</tr>
	<tr>
		<td>是否有氣喘:</td>
		<td><input type="TEXT" name="cg_is_ab" size="45"	value="<%=chat_GroupVO.getCg_is_ab()%>" /></td>
	</tr>
	<tr>
		<td>是否有過敏性結膜炎:</td>
		<td><input type="TEXT" name="cg_is_ac" size="45" value="<%=chat_GroupVO.getCg_is_ac()%>" /></td>
	</tr>
	<tr>
		<td>是否有過敏性結膜炎:</td>
		<td><input type="TEXT" name="cg_is_sf" size="45" value="<%=chat_GroupVO.getCg_is_sf()%>" /></td>
	</tr>
	<tr>
		<td>是否有過敏性結膜炎:</td>
		<td><input type="TEXT" name="cg_is_ad" size="45" value="<%=chat_GroupVO.getCg_is_ad()%>" /></td>
	</tr>
	<tr>
		<td>其他罕見疾病:</td>
		<td><input type="TEXT" name="cg_baby_rd" size="45" value="<%=chat_GroupVO.getCg_baby_rd()%>" /></td>
	</tr>

	<jsp:useBean id="cgiSvc" scope="page" class="com.chat.model.Chat_Group_ItemService" />
	<tr>
		<td>部門:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="deptVO" items="${cgiSvc.all}">
				<option value="${chat_Group_ItemVO.deptno}" ${(chat_GroupVO.deptno==chat_Group_ItemVO.deptno)?'selected':'' } >${chat_Group_ItemVO.dname}
			</c:forEach>
		</select></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="chat_GroupVO" value="<%=chat_GroupVO.getCg_no()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--用於:istAllEmp.jsp 與 複合查詢 listEmps_ByCompositeQuery.jsp-->
<input type="submit" value="送出修改"></FORM>

<br>送出修改的來源網頁路徑:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br>
   <font color=blue>request.getParameter("whichPage"):</font> <%= request.getParameter("whichPage")%> (此範例目前用於:istAllEmp.jsp 與 複合查詢 listEmps_ByCompositeQuery.jsp)</b>
</body>
</html>
