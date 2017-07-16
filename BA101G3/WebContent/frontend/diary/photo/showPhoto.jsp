<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.diary.model.*" %>
<%@ page import="java.util.*" %>
<%
    PhotoVO photoVO = (PhotoVO) request.getAttribute("photoVO");
    pageContext.setAttribute("photoVO", photoVO);
%>
<jsp:useBean id="babySvc" scope="page" class="com.diary.model.BabyService"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=BIG5">
    <title>${photoVO.pho_title}</title>

    <!-- bootstrap -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- babeq -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/css/babeq.css">

    <style>
        .btn-default {
            border: solid 10px;
            border-image: url("<%=request.getContextPath()%>/frontend/pic/borderimg100.png") 20 20 stretch;
        }
        body{
            background-color: #0d0d0d;
            color: #f6f6f6;
        }
    </style>

</head>
<%--style="width: 70%;height: 70%"--%>
<body>
<div class="container">
    <div class="row">
        <div class="col-sm-12">

            <img src="<%=request.getContextPath()%>/PhotoReader?pho_no=${photoVO.pho_no}"
                 alt="${photoVO.pho_title}" style="max-width: 100%;max-height: 100%;">
            <form>
                <div class="form-group">
                    <div class="caption">
                        <div class="form-group">
                            <label for="pho_title">相片標題</label>
                            <input type="text" class="form-control" name="pho_title" id="pho_title" value="${photoVO.pho_title}">
                        </div>
                        <div class="form-group">
                            <label for="baby_no">寶寶暱稱</label>
                            <select class="form-control" name="baby_no" id="baby_no" aria-valuenow="${photoVO.baby_no}">
                                <c:forEach var="BabyVO" items="${babylist}">
                                    <option value="${BabyVO.baby_no}">${BabyVO.baby_aka}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="pho_annot">相片註記</label>
                            <textarea class="form-control" rows="3" id="pho_annot" style="resize: none" name="pho_annot"
                                      placeholder="為您的記憶留下備註吧">${photoVO.pho_annot}</textarea>
                        </div>
                        <div class="form-group">
                            <label for="pho_shr">分享權限</label>
                            <div data-toggle="buttons">
                                <label class="btn btn-default btn-circle btn-lg active">
                                    <c:if test="${photoVO.pho_shr.equals('0')}">
                                        <input type="radio" name="pho_shr" id="pho_shr" value="${photoVO.pho_shr}"><i
                                            class="glyphicon glyphicon-user" checked></i>
                                    </c:if>
                                    <c:if test="${!photoVO.pho_shr.equals('0')}">
                                        <input type="radio" name="pho_shr" id="pho_shr" value="0"><i
                                            class="glyphicon glyphicon-user"></i>
                                    </c:if>
                                </label>
                                <label class="btn btn-default btn-circle btn-lg">
                                    <c:if test="${photoVO.pho_shr.equals('1')}">
                                        <input type="radio" name="pho_shr" id="pho_shr" value="${photoVO.pho_shr}" checked><i
                                            class="glyphicon glyphicon-home"></i>
                                    </c:if>
                                    <c:if test="${!photoVO.pho_shr.equals('1')}">
                                        <input type="radio" name="pho_shr" id="pho_shr" value="1"><i
                                            class="glyphicon glyphicon-home"></i>
                                    </c:if>
                                </label>
                                <label class="btn btn-default btn-circle btn-lg">
                                    <c:if test="${photoVO.pho_shr.equals('2')}">
                                        <input type="radio" name="pho_shr" id="pho_shr" value="${photoVO.pho_shr}" checked><i
                                            class="glyphicon glyphicon-share"></i>
                                    </c:if>
                                    <c:if test="${!photoVO.pho_shr.equals('2')}">
                                        <input type="radio" name="pho_shr" id="pho_shr" value="2"><i
                                            class="glyphicon glyphicon-share"></i>
                                    </c:if>
                                </label>
                            </div>
                        </div>
                    </div>

                    <input type="hidden" class="form-control" name="pho_no" value="${photoVO.pho_no}">
                    <input type="hidden" class="form-control" name="action" value="update">
                    <input type="hidden" class="form-control" name="requestURL"
                           value="<%=request.getServletPath()%>">
                    <button class="btn btn-default btn-lg btn-block" type="submit">
                        確認修改 <span class="glyphicon glyphicon-ok"></span>
                    </button>
                    <a href="<%=request.getContextPath()%>/PhotoServlet.do?pho_no=${photoVO.pho_no}&action=delete&requestURL=<%=request.getServletPath()%>"
                       class="btn btn-default btn-lg btn-block">
                        <span class="glyphicon glyphicon-remove"></span> 確認刪除
                    </a>
                </div>
        </div>
        </form>
    </div>
</div>
</div>
</div>
</div>

</body>
</html>