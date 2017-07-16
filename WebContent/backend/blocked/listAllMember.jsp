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
<title>�Ҧ����u��� - listAllmember.jsp</title>
</head>
<body bgcolor='white'>
--${param.mem_no}----${param.whichPage}--
<b><font color=red>�����m�߱ĥ� EL ���g�k����:</font></b>
<table border='1' cellpadding='5' cellspacing='0' width='900'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>�Ҧ����u��� - ListAllmember.jsp</h3>
		<a href="<%=request.getContextPath()%>/frontend/member/select_page.jsp"><img src="<%=request.getContextPath()%>/frontend/member/images/back1.gif" width="100" height="32" border="0">�^����</a></td>
		</td>
	</tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>�Эץ��H�U���~:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='900'>
	<tr>
		<td>�|���s��</td>
		<td>�|�����Žs��</td>
		<td>�M�a�����s��</td>
		<td>�|���b��</td>
		<td>�|���m�W</td>
		<th>�|���Ӥ�</th>
		<td>�|���a�}(����)</td>
		<td>�|���q��</td>
		<td>�|���q�l�H�c</td>

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
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/frontend/member/member.do"><!-- �[�Wfrontend���|,��controller path �Pupdate_member_input.jsp���|�@�P -->
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--�e�X��e�O�ĴX����Controller-->
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

<br>�����������|:<br><b>
   <font color=blue>request.getServletPath():</font> <%= request.getServletPath()%><br>
   <font color=blue>request.getRequestURI(): </font> <%= request.getRequestURI()%> </b>
</body>
</html>
