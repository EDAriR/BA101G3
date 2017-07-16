<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@ page import="com.member.model.*" %>


<%
    MemberService memberSvc = new MemberService();
    List<MemberVO> list = memberSvc.getAll();
    pageContext.setAttribute("list", list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=BIG5">
    
    <title>BBQ Admin ���f��</title>
    <!-- Bootstrap core CSS -->
    <link href="<%=request.getContextPath()%>/backend/blocked/assets/css/bootstrap.css" rel="stylesheet">
    <!--external css  ICON-->
    <link href="<%=request.getContextPath()%>/backend/blocked/assets/font-awesome/css/font-awesome.css"
          rel="stylesheet"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/backend/blocked/assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/backend/blocked/assets/js/gritter/css/jquery.gritter.css"/>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/backend/blocked/assets/lineicons/style.css">
    <link href="<%=request.getContextPath()%>/backend/blocked/assets/css/style-responsive.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/backend/blocked/assets/css/table-responsive.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="<%=request.getContextPath()%>/backend/blocked/assets/css/style.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/backend/blocked/assets/css/style-responsive.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<section id="container">

    <!-- **********************************************************************************************************************************************************
    MAIN SIDEBAR MENU
    *********************************************************************************************************************************************************** -->

    <!--sidebar start ������-->
    <aside>
        <div id="sidebar" class="nav-collapse ">
            <!-- sidebar menu start-->
            <ul class="sidebar-menu" id="nav-accordion">
                <p class="centered">
                    <a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">
                        <img src="<%=request.getContextPath()%>/backend/blocked/assets/img/babeq3.png" width="190">
                    </a>
                </p>
                <h5 class="centered" style="font-size: 24px;">
                    <form id="logout" method="POST"
                          action="<%=request.getContextPath()%>/backend/admin/adminServlet.do">
                        <input type="hidden" name="action" value="logout">
                        <a href="javascript:void()" onclick="document.getElementById('logout').submit();">
                    <span style="color: #636363">
                        <i class="fa fa-sign-out" aria-hidden="true"></i>
                        ${adm_name}</span>
                        </a>
                    </form>
                </h5>

                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-desktop"></i>
                        <span>�|����ƺ޲z</span>
                    </a>
                    <ul class="sub">
                        <li><a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">�M�a�f��</a></li>
                        <li><a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">��`�����f��</a></li>
                        <!-- <li><a href="member/buyer_report.html">�R�a���|</a></li>
                        <li><a href="member/seller_reprot.html">��a���|</a></li>
                        <li><a href="member/product_report.html">�ӫ~���|</a></li>
                        <li><a href="member/product_message_report.html">�ӫ~�d�����|</a></li> -->
                        <li><a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">�^�а��D���|</a></li>
                        <li><a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">�d�����|</a></li>
                        <li><a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">��ѫǥΤ����|</a></li>
                        <li><a href="panels.html">�|����ƺ޲z</a></li>
                    </ul>
                </li>
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-car"></i>
                        <span>���˸�ƺ޲z</span>
                    </a>
                    <ul class="sub">
                        <li><a href="map.html">���˸�ƺ�</a></li>
                        <li><a href="todo_list.html">�ƭ��~</a></li>
                    </ul>
                </li>
                <li class="sub-menu">
                    <a href="javascript:;">
                        <i class="fa fa-book"></i>
                        <span>�����޲z</span>
                    </a>
                    <ul class="sub">
                        <li><a href="blank.html">�ʪ��Ϥ���</a></li>
                        <li><a href="backend/login.html">�峹����</a></li>
                        <li><a href="lock_screen.html">�ӫ~����</a></li>
                        <li><a href="lock_screen.html">���D����</a></li>
                        <li><a href="lock_screen.html">�M�a����</a></li>
                    </ul>
                </li>
                <li class="sub-menu">
                    <a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">
                        <i class="fa fa-bell"></i>
                        <span>�̷s�����޲z</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">
                        <i class=" fa fa-legal"></i>
                        <span>���u�b��޲z</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">
                        <i class=" fa fa-database"></i>
                        <span>�t�ΰѼƺ޲z</span>
                    </a>
                </li>
                <li class="sub-menu">
                    <a href="<%=request.getContextPath()%>/backend/blocked/index.jsp">
                        <i class="fa fa-bar-chart-o"></i>
                        <span>��x���R�޲z</span>
                    </a>
                </li>
            </ul>
            <!-- sidebar menu end-->
        </div>
    </aside>
    <!--sidebar end-->

    <!--main content start-->
    <section id="main-content">
        <section class="wrapper">
            <h3><i class="fa fa-angle-right"></i>�٦� 9487 �� �ݼf�֡F �ݤ@�U�쩳���S���n�X${sessionScope.adm_no}</h3>
            <div class="row">
                <div class="col-md-12">
                    <div class="content-panel">
                        <table class="table table-striped table-advance table-hover">
                        <%@ include file="page1.file" %>
                            <h4><i class="fa fa-angle-right"></i> ��<%=whichPage%>/<%=pageNumber%>��</h4>
                            <hr>
                            <thead>
                            <tr>
                                <th><i class="fa fa-bullhorn"></i> �|���s��</th>
                                <th class="hidden-phone"><i class="fa fa-question-circle"></i>�|�����Žs��</th>
                                <th><i class="fa fa-bookmark"></i> �|���m�W</th>
                                <th><i class=" fa fa-edit"></i> Status</th>
                                <th><i class=" fa fa-edit"></i> Status</th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            

                            <c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>"
                                       end="<%=pageIndex+rowsPerPage-1%>">
                            <tr>

                                <td>${memberVO.mem_no}</td>
                                <td>${memberVO.memg_gr}</td>
                                <td>${memberVO.mem_name}</td>

                                <td>
                                    <FORM METHOD="post"
                                          ACTION="<%=request.getContextPath()%>/frontend/member/member.do">
                                        <!-- �[�Wfrontend���|,��controller path �Pupdate_member_input.jsp���|�@�P -->
                                        <input type="submit" value="�ק�">
                                        <input type="hidden" name="mem_no" value="${memberVO.mem_no}">
                                        <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
                                        <!--�e�X�����������|��Controller-->
                                        <input type="hidden" name="whichPage" value="<%=whichPage%>">
                                        <!--�e�X��e�O�ĴX����Controller-->
                                        <input type="hidden" name="action" value="getOne_For_Update"></FORM>
                                </td>
                            </tr>
                            </c:forEach>
                        </table>
                        <nav aria-label="Page navigation">
                            <ul class="pagination pagination pagination-lg">
                                <%if (rowsPerPage < rowNumber) {%>
                                <%if (pageIndex >= rowsPerPage) {%>
                                <li>
                                    <a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage-1%>">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <%}%>
                                <%if (pageNumber > 1) {%>
                                <%for (int i = 1; i <= pageNumber; i++) {%>
                                <li><a href="<%=request.getRequestURI()%>?whichPage=<%=i%>"><%=i%>
                                </a></li>
                                <%}%>
                                <%}%>

                                <%if (pageIndex < pageIndexArray[pageNumber - 1]) {%>
                                <li>
                                    <a href="<%=request.getRequestURI()%>?whichPage=<%=whichPage+1%>">
                                        <span aria-hidden="true">&raquo;</span>
                                    </a>
                                </li>
                                <%}%>
                                <%}%>
                            </ul>
                        </nav>

                        <%@ include file="page2.file" %>


                        <!--footer start-->
                        <footer class="site-footer">
                            <div class="text-center">
                                2017 - BaBeQ
                                <a href="#main-content" class="go-top">
                                    <i class="fa fa-angle-up"></i>
                                </a>
                            </div>
                        </footer>
                        <!--footer end-->
        </section>
        <!-- js placed at the end of the document so the pages load faster -->
        <script src="<%=request.getContextPath()%>/backend/blocked/assets/js/jquery.js"></script>
        <script src="<%=request.getContextPath()%>/backend/blocked/assets/js/bootstrap.min.js"></script>
        <script class="include" type="text/javascript"
                src="<%=request.getContextPath()%>/backend/blocked/assets/js/jquery.dcjqaccordion.2.7.js"></script>
        <script src="<%=request.getContextPath()%>/backend/blocked/assets/js/jquery.scrollTo.min.js"></script>
        <script src="<%=request.getContextPath()%>/backend/blocked/assets/js/jquery.nicescroll.js"
                type="text/javascript"></script>
        <!--common script for all pages �@��JS-->
        <script src="<%=request.getContextPath()%>/backend/blocked/assets/js/common-scripts.js"></script>
        <!--script for this page-->
        <script>
            //�R�� custom select box
        </script>

</body>
</html>