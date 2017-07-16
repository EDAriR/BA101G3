<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- 此頁練習採用 EL 的寫法取值 --%>

<jsp:useBean id="listAFs_ByAuth_No" scope="request" type="java.util.Set"/>
<jsp:useBean id="authority_FeatureSvc" scope="page" class="com.admin.model.Authority_FeatureService"/>
<html>
<head>
    <title>員工權限 - listAFs_ByAuth_No.jsp</title>
</head>
<body bgcolor='white'>


<h3>員工權限 - listAFs_ByAuth_No.jsp</h3>
<h3><a href="<%=request.getContextPath()%>/select_page.jsp">
    <img src="images/babeq3.png" width="100" height="32" border="0">回首頁
</a>
</h3>


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
    <ul>
        <h5 color='red'>請修正以下錯誤:</h5>
        <c:forEach var="message" items="${errorMsgs}">
            <li>${message}</li>
        </c:forEach>
    </ul>
    </font>
</c:if>

<table border='1' bordercolor='#CCCCFF' width='800'>
    <tr>
        <th>權限編號</th>
        <th>權限名稱</th>

    </tr>

    <c:forEach var="authority_FeatureVO" items="${listAFs_ByAuth_No}">
        <tr align='center' valign='middle' ${(authority_FeatureVO.auth_no==param.auth_no) ? 'bgcolor=#CCCCFF':''}>
            <!--將修改的那一筆加入對比色而已-->
            <td>${authority_FeatureVO.auth_no}</td>
            <td>${authority_FeatureVO.auth_name}</td>
            <td color=orange>
                <c:forEach var="authority_FeatureVO" items="${admin_AuthorityService.all}">
                    <c:if test="${admin_AuthorityVO.auth_no==authority_FeatureVO.auth_no}">
                        【${admin_AuthorityVO.adm_name} - ${authority_FeatureVO.auth_name}】
                    </c:if>
                </c:forEach>
            </td>
            <td>
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do">
                    <input type="submit" value="修改">
                    <input type="hidden" name="empno" value="${authority_FeatureVO.empno}">
                    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                    <!--送出本網頁的路徑給Controller-->
                    <input type="hidden" name="action" value="getOne_For_Update"></FORM>
            </td>

        </tr>
    </c:forEach>
</table>

<br>本網頁的路徑:<br><b>

    <p color=blue>
        request.getServletPath():<%= request.getServletPath()%>
    </p>
    <p color=blue>
        request.getRequestURI():<%= request.getRequestURI()%>
    </p>

</body>
</html>
