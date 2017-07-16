<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.admin.model.*" %>
<%@ page import="com.admin.controller.SerialNumGenerator" %>
<%
    AdminVO adminVO = (AdminVO) request.getAttribute("adminVO");
    SerialNumGenerator sng = new SerialNumGenerator();

%>

<html>
<head>
    <title>員工資料新增 - addEmp.jsp</title></head>

<body bgcolor='white'>

<h3>資料員工:</h3>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/backend/admin/adminServlet.do" name="form1">
    <table border="0">

        <tr>
            <td>員工名稱:</td>
            <td><input type="TEXT" name="adm_name" size="45"
                       value="<%= (adminVO==null)? "吳永志" : adminVO.getAdm_name()%>"/></td>
        </tr>
        <tr>
            <td>員工帳號:</td>
            <td><input type="TEXT" name="adm_acct" size="45"
                       value="<%= (adminVO==null)? "adcde123" : adminVO.getAdm_acct()%>"/></td>
        </tr>
        <tr>
            <td>員工密碼:</td>
            <td><input type="TEXT" name="adm_pwd" size="45"
                       value="<%= sng.generator(6)%>"/></td>
        </tr>
        <tr>
            <td>員工信箱:</td>
            <td><input type="TEXT" name="adm_mail" size="45"
                       value="<%= (adminVO==null)? "aaa@xxx.com" : adminVO.getAdm_mail()%>"/></td>
        </tr>

    </table>
    <br>
    <input type="hidden" name="action" value="insert">
    <input type="submit" value="送出新增"></FORM>
</body>

</html>
