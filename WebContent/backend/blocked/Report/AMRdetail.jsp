<%@ page language="java" contentType="text/html; charset=BIG5"
         pageEncoding="BIG5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="amrVO" scope="request" type="com.article.model.Article_Message_ReportVO"/>
<jsp:useBean id="amVO" scope="request" type="com.article.model.Article_MessageVO"/>
<jsp:useBean id="amrSvc" scope="page" class="com.article.model.Article_Message_ReportService"/>
<jsp:useBean id="amSvc" scope="page" class="com.article.model.Article_MessageService"/>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=BIG5">
    <title>Insert title here</title>
    <style>
        p {
            font-size: 16px;
        }

        textarea {
            resize: none;
        }
    </style>
</head>
<body>

<div class="col-sm-12">
    <div class="content-panel" style="padding: 3px">

        <div class="panel panel-default">

            <div class="panel-heading">
                <h3 class="panel-title">檢舉人:${memSvc.getOneMember(amrVO.mem_no).mem_name}</h3>
            </div>

            <div class="media">

                <div class="media-body">
                    <div class="col-sm-10" style="width: 100%">
                        <h2 class="media-heading">檢舉原因</h2>
                        <p>${amrVO.amrpt_rsn}</p>
                        <hr>

                        <h3 class="media-heading">被檢舉留言日期</h3>
                        <p>${amVO.amsg_date}</p>
                        <hr>

                        <h3 class="media-heading">被檢舉內容</h3>
                        <p>${amVO.amsg_cnt}</p>
                    </div>


                </div>

                <div class="col-sm-12" style="height: 10px"></div>

            </div>

        </div>

        <fieldset class="form-group">

            <form method="post">
                <div class="form-group" action="<%=request.getContextPath()%>/amrServlet">
                    <label for="comment">不通過原因:</label>
                    <textarea class="form-control" rows="5" id="comment" name="amrpt_unrsn"></textarea>
                </div>
                <%--${amrVO.amrpt_unrsn}--%>
                <input type="hidden" name="amrpt_is_cert" value="2">
                <input type="hidden" name="amsg_no" value="${amrVO.amsg_no}">
                <input type="hidden" name="mem_no" value="${amrVO.mem_no}">
                <input type="hidden" name="action" value="update">

                <button class="btn btn-danger btn-block btn-lg"><span
                        class="glyphicon glyphicon-remove-circle"></span> 檢舉不過
                </button>

            </form>
            <form method="post" action="<%=request.getContextPath()%>/amrServlet">
                <input type="hidden" name="amrpt_is_cert" value="1">
                <input type="hidden" name="amrpt_unrsn" value="檢舉通過">
                <input type="hidden" name="amsg_no" value="${amrVO.amsg_no}">
                <input type="hidden" name="mem_no" value="${amrVO.mem_no}">
                <input type="hidden" name="action" value="update">
                <button class="btn btn-block btn-warning btn-lg" type="submit">
                    <span class="glyphicon glyphicon-ok-sign"></span> 檢舉通過
                </button>
            </form>
        </fieldset>


    </div><!-- /content-panel -->
</div><!-- /col-md-12 -->

</body>
</html>