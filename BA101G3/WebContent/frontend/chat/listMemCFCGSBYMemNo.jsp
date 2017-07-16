<%@ page language="java" contentType="text/html; charset=BIG5" pageEncoding="BIG5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="cgSvc" scope="page" class="com.chat.model.Chat_GroupService"/>
<jsp:useBean id="cfSvc" scope="page" class="com.chat.model.Chat_FriendService"/>
<jsp:useBean id="cgiSvc" scope="page" class="com.chat.model.Chat_Group_ItemService"/>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=BIG5">
    <title>Insert title here</title>

    <!-- bootstrap -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <!-- babeq -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/frontend/css/babeq.css">

    <!-- Add fancyBox -->
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/frontend/diary/photo/fancyBox3/jquery.fancybox.css">
    <script src="<%=request.getContextPath()%>/frontend/diary/photo/fancyBox3/jquery.fancybox.min.js"></script>

</head>
<body>

<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-sm-10 col-lg-offset-1">
                <h3>�ݤ@�U�쩳���S���n�X${sessionScope.adm_no}</h3>
                <h3>�ݤ@�U�쩳���S���n�X${mem_no}</h3>
                <h3>�ݤ@�U�쩳���S���n�X${mem_name}</h3>
                <a href="#friend">�n�ͦC��</a>

                <table class="table table-hover">
                    <tbody>

                    <th><h3>�z���s��</h3></th>
                    <c:forEach var="cgiVO" items="${cgiSvc.getOneChat_Group_Mem(mem_no) }">
                        <tr>
                            <td>
                                <a href="<%=request.getContextPath()%>/chat/ChatFriend/Chat_GroupServlet.do?action=getOne_For_Display&cg_no=${cgiVO.getCg_no()}">
                                    ${cgSvc.getOneCG(cgiVO.getCg_no()).cg_name}
                                </a>
                            </td>
                            <td>
                                <form action="<%=request.getContextPath()%>/chat/ChatFriend/ChatServlet.do"
                                      method="post">
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="url" value="<%=request.getServletPath()%>">
                                    <input type="hidden" name="cg_no" value="${cgiVO.getCg_no()}">
                                    <input type="hidden" name="cfdel" value="1">
                                    <button class="btn btn-large" type="submit"><i class="icon-refresh"></i>�h�X�s��
                                    </button>


                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <hr>
                <table class="table table-hover">
                    <tbody>

                    <th><h3>���H�ܽбz�����n��</h3></th>
                    <th></th>
                    <c:forEach var="cfVO" items="${cfSvc.getOtherMCF(mem_no) }">

                        <c:if test="${cfVO.getCf_is_del() eq '0' }"><%-- ��L�|�����ܽЦ��|�������n�ͤ��C�� --%>
                            <tr>
                                <td>${memSvc.getOneMember(cfVO.getMem_no_s()).mem_name}</td>
                                <td>
                                    <form action="<%=request.getContextPath()%>/chat/ChatFriend/ChatServlet.do"
                                          method="post">
                                        <input type="hidden" name="action" value="update">
                                        <input type="hidden" name="url" value="<%=request.getServletPath()%>">
                                        <input type="hidden" name="cf_no" value="${cfVO.getCf_no()}">
                                        <input type="hidden" name="mem_no_s" value="${cfVO.getMem_no_s()}">
                                        <input type="hidden" name="mem_no_o" value="${mem_no}">
                                        <input type="hidden" name="cfdel" value="1">
                                        <button class="btn btn-large" type="submit"><i class="icon-refresh"></i>�P�N�����n��
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:if><%-- end cfVO.getCf_is_del --%>
                    </c:forEach><%-- end cfSvc.getOneMCFromO --%>
                    <%-- other member add you --%>
                    </tbody>
                </table>


                <table class="table table-hover">
                    <tbody>

                    <th><h3 name="friend">�z���n��</h3></th>
                    <c:forEach var="cfVO" items="${cfSvc.getOneMCF(mem_no) }">

                        <c:if test="${cfVO.getCf_is_del() ne '2' }"><%-- 2���w�R���n�ͤ���� --%>
                            <tr>
                                <td>${memSvc.getOneMember(cfVO.getMem_no_o()).mem_name}</td>
                                <td>
                                    <c:if test="${cfVO.getCf_is_del() eq '0' }">
                                        <button class="btn btn-large disabled"><i class="icon-refresh"></i>�n���ܽФ�
                                        </button>
                                    </c:if><%-- �ܽЦ����n�ͤ��C�� --%>

                                    <c:if test="${cfVO.getCf_is_del() eq '1' }"><%-- 1 �{�b���n�� --%>
                                        <form action="<%=request.getContextPath()%>/chat/ChatFriend/ChatServlet.do"
                                              method="POST">
                                            <input type="hidden" name="action" value="update">
                                            <input type="hidden" name="url" value="<%= request.getServletPath()%>">
                                            <input type="hidden" name="cf_no" value="${cfVO.getCf_no()}">
                                            <input type="hidden" name="cfdel" value="2">
                                            <button class="btn btn-large" type="submit"><i class="icon-remove"></i> �R���n��
                                            </button>
                                        </form>
                                    </c:if><%-- end if 1 �{�b���n�� --%>
                                </td>
                            </tr>
                        </c:if><%-- end if 2���w�R���n������� --%>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</section>

</body>
</html>