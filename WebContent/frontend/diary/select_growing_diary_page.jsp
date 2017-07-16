<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.diary.model.*"%>
<%@ page import="java.util.*"%>

<html>
<head>
	<title>BaBeQ Growing_Diary: Home</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='400'>
  <tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
    <td><h3>BaBeQ Growing_Diary: Home</h3><font color=red>( MVC )</font></td>
  </tr>
</table>

<p>This is the Home page for BaBeQ Growing_Diary: Home</p>

<h3>資料查詢:</h3>
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

<ul>
  <li><a href='<%=request.getContextPath()%>/frontend/diary/listAllGrowing_Diary.jsp'>List</a> all Growing_Diary. </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/diary/growing_diary.do" >
        <b>輸入成長日誌編號 (如GD0000001):</b>
        <input type="text" name="gd_no">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>

  <jsp:useBean id="growing_diarySvc" scope="page" class="com.diary.model.Growing_DiaryService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/diary/growing_diary.do" >
       <b>選擇成長日誌編號:</b>
       <select size="1" name="gd_no"> 
          <c:forEach var="growing_diaryVO" items="${growing_diarySvc.all}" > 
          <option value="${growing_diaryVO.gd_no}">${growing_diaryVO.gd_no}
         </c:forEach> 
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getOne_For_Display">
    </FORM>
  </li>
  
<h3>成長日誌管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/frontend/diary/addGrowing_Diary.jsp'>Add</a> a new Growing_Diary.</li>
</ul>

</body>

</html>
