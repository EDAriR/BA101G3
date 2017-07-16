<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.map.model.*"%>
<%
	Map_MechanismVO map_mechanismVO = (Map_MechanismVO) request.getAttribute("map_mechanismVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
	<script src="/BA101G3/utility/ckeditor/ckeditor.js"></script>
	<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
	<link rel="stylesheet" href="/BA101G3/utility/ckeditor/contents.css">
	<script src="/BA101G3/utility/ckfinder/ckfinder.js"></script>
	<script>
		$(document).ready(function() {
			var editor = CKEDITOR.replace( 'map_cnt', {
				width:680,
				filebrowserBrowseUrl : '/BA101G3/utility/ckfinder/ckfinder.html',
				filebrowserImageBrowseUrl : '/BA101G3/utility/ckfinder/ckfinder.html?type=Images', 
				filebrowserFlashBrowseUrl : '/BA101G3/utility/ckfinder/ckfinder.html?type=Flash',
				filebrowserUploadUrl : '/BA101G3/utility/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files', 
				filebrowserImageUploadUrl : '/BA101G3/utility/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images', 
				filebrowserFlashUploadUrl : '/BA101G3/utility/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash' 
			});
			CKFinder.setupCKEditor( editor, '/ckfinder/' );
			//CKEDITOR.instances.map_cnt.setData( '<p>This is the editor data.</p>' );
		});
		
	</script>
<title>地圖機構資料修改 - update_map_mechanism_input.jsp</title></head>

<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='800'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>地圖機構資料修改 - update_map_mechanism_input.jsp</h3>
		<a href="<%=request.getContextPath()%>/backend/map/select_map_mechanism_page.jsp">回首頁</a></td>
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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/map/map_mechanism.do" name="form1" enctype="multipart/form-data">
<table border="0">
	<tr>
		<td>地圖機構編號:<font color=red><b>*</b></font></td>
		<td><%=map_mechanismVO.getMap_no()%></td>
	</tr>
<%-- 	<tr>
		<td>機構種類:
		<td><select size="1" name="mapc_no">
			<c:forEach var="map_mechanismVO" items="${map_mechanismSvc.all}">
				<option value="${map_mechanismVO.mapc_no}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname}
			</c:forEach>
		    <option value="${map_mechanismVO.mapc_no == '0'}">親子餐廳
            <option value="${map_mechanismVO.mapc_no == '1'}">嬰幼兒用品店
            <option value="${map_mechanismVO.mapc_no == '2'}">醫院、衛生所
		</select></td>
	</tr> --%>
	<tr>
		<td>機構名稱:</td>
		<td><input type="TEXT" name="map_name" size="45" value="${map_mechanismVO.map_name}" /></td>
	</tr>
	<tr>
		<td>機構地址(縣市):</td>
		<td><input type="TEXT" name="map_adds" size="45"	value="<%=map_mechanismVO.getMap_adds()%>" /></td>
	</tr>
	<tr>
		<td>機構地址(詳細):</td>
		<td><input type="TEXT" name="map_addc" size="45"	value="<%=map_mechanismVO.getMap_addc()%>" /></td>
	</tr>
	<tr>
		<td>機構電話:</td>
		<td><input type="TEXT" name="map_phone" size="45" value="<%=map_mechanismVO.getMap_phone()%>" /></td>
	</tr>
	<tr>
		<td>機構電子信箱:</td>
		<td><input type="TEXT" name="map_mail" size="45" value="${map_mechanismVO.map_mail}" /></td>
	</tr>
	<tr>
		<td>機構介紹:</td>
		<td><textarea rows="10" cols="80" name="map_cnt" id="map_cnt" >
			<%=map_mechanismVO.getMap_cnt()%>
			</textarea>
		</td>
	</tr>
	<tr>
		<td>機構照片:</td>
		<td>
			<input type="file" name="map_photo">
			<img src="<%=request.getContextPath()%>/utility/map_mechanismreader?map_no=${map_mechanismVO.map_no}" width='250'>
		</td>
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="map_no" value="<%=map_mechanismVO.getMap_no()%>">
<input type="hidden" name="mem_no" value="<%=map_mechanismVO.getMem_no()%>">
<input type="hidden" name="mapc_no" value="<%=map_mechanismVO.getMapc_no()%>">
<input type="hidden" name="map_long" value="<%=map_mechanismVO.getMap_long()%>">
<input type="hidden" name="map_lat" value="<%=map_mechanismVO.getMap_lat()%>">
<input type="hidden" name="map_photo1" value="<%=map_mechanismVO.getMap_photo1()%>">
<input type="hidden" name="map_photo2" value="<%=map_mechanismVO.getMap_photo2()%>">
<input type="hidden" name="map_photo3" value="<%=map_mechanismVO.getMap_photo3()%>">
<input type="hidden" name="map_photo4" value="<%=map_mechanismVO.getMap_photo4()%>">
<input type="hidden" name="map_photo5" value="<%=map_mechanismVO.getMap_photo5()%>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"><!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
<input type="hidden" name="whichPage" value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
<input type="submit" value="送出修改"></FORM>

<br>送出修改的來源網頁路徑:<br><b>
   <font color=blue>request.getParameter("requestURL"):</font> <%= request.getParameter("requestURL")%><br>
   <font color=blue>request.getParameter("whichPage"):</font> <%= request.getParameter("whichPage")%> (此範例目前只用於:istAllEmp.jsp))</b>
</body>
</html>
