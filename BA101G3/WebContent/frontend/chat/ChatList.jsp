<%@ page import="com.chat.model.Chat_FriendVO" %>
<%@ page import="com.chat.model.Chat_Group_ItemVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- �����m�߱ĥ� EL ���g�k���� --%>
<%
    List<Chat_Group_ItemVO> cglsit = (List<Chat_Group_ItemVO>) request.getAttribute("cglsit");
    pageContext.setAttribute("cglsit", cglsit);
    List<Chat_FriendVO> cflist = (List<Chat_FriendVO>) request.getAttribute("oneMemCF");
    pageContext.setAttribute("cflist", cflist);
%>

<jsp:useBean id="cgSvc" scope="page" class="com.chat.model.Chat_GroupService"/>
<jsp:useBean id="cgiSvc" scope="page" class="com.chat.model.Chat_Group_ItemService"/>
<jsp:useBean id="memSvc" scope="page" class="com.member.model.MemberService"/>


<!DOCTYPE html>
<html>
    <head>
        <title>Chat Room</title>
        <meta http-equiv="Content-Type" content="text/html; charset=Big5">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <title>ChatButton</title>
   		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/chat/css/main.css">
  	 <link href="<%=request.getContextPath()%>/frontend/chat/assets/font-awesome/css/font-awesome.css" rel="stylesheet"/>
    	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/frontend/chat/assets/lineicons/style.css">
    	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
	    <style>
	        .btn, .btn-default {
	            border: solid 10px;
	            border-image: url("<%=request.getContextPath()%>/frontend/pic/borderimg100.png") 20 20 stretch;
	        }
	        .img-circle{
	            border-image: url("<%=request.getContextPath()%>/frontend/pic/circleborder100.png") 1 1 stretch;
	        }
	    </style>    
    </head>
    
    <body>
    
	   	<!--  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
	    <h1> Chat Room </h1>
	    <h3 id="statusOutput" class="statusOutput"></h3>
	       <div class="panel input-area">
		    <input type="button" id="connect"     class="button" value="�s�u" onclick="connect();"/>
		    <input type="button" id="disconnect"  class="button" value="���u" onclick="disconnect();"/>
	    </div>
	    <!--  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
	    
	    

	<div id="divArea" class="btn-group" style="position: fixed; bottom:0px; right:5px;">
	    <button id="controllBtn" type="button" class="btn btn-default"  aria-haspopup="true"
	            aria-expanded="false">
	        <i class="fa fa-comment fa-3x"></i>
	    </button>

        <!--�n�ͦC��̰����� �H�α��b-->
	    <ul class="dropdown-menu dropdown-menu-right" style="font-size: 16px;font-family:'Microsoft JhengHei';  max-height:600px; height:600px; overflow-y: scroll;"> 
	        <!-- �n�ͦC��-�ӤH�� -->
	        <li class="drop-down-header">
	            <form class="navbar-form navbar-left" role="search">
      				<c:forEach var="memVO" items="${memSvc.all}" >
                        <c:if test="${sessionScope.mem_no==memVO.mem_no}">
<!--�j�Y��-->  				<img src="<%=request.getContextPath()%>/frontend/DBPicReader/DBPicReader.do?id_no=${memVO.mem_no}&action=member" class="img-circle" width="50">
<!--�m�W--> 					<span id="inputUserName">${memVO.mem_name}</span>
						</c:if>
                    </c:forEach>
					<input type="hidden" name="mem_name" id="mem_name" value="${memVO.mem_name}">  <!--���F��ɧ�n�J�|�����|���W�� �nSHOW�b��ѵ�����-->	
<!--�j�MBar	                <div class="input-group mb-2 mr-sm-2 mb-sm-0" >
	                    <div class="input-group-addon"><i class="fa fa-search"></i></div>
	                    <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Username" >
	                </div>--> 
	            </form>
	        </li>
			<!-- �n�ͦC��-�ӤH�� -->
			
            <li><a href="#"><span class="glyphicons glyphicons-map-marker"></span></a></li>  <!--���F�h�@�I�d��,�R���|�]��-->

            <!-- �n�ͦC��-�n�Ͱ� -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="panel1">
                    <h4 class="panel-title">
                    	<a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="aaa">
                      		 �n�ͦC��
                    	</a>
                    </h4>
                </div>
                <div id="aaa" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel1">
                    <ul class="list-group">
                    	<%--��ܷ|���n�ͦC��--%>
						<c:forEach var="cflsit" items="${cflist}">
							<%--�P�_�n�ͬO�_���--%>
							<c:if test="${cflsit.cf_is_del==0}">
								<c:forEach var="memVO" items="${memSvc.all}" >
	                                <c:if test="${cflsit.mem_no_o==memVO.mem_no}">
	                        			<li class="list-group-item"> <a href="#" id="addClass_${memVO.mem_no}"><img src="<%=request.getContextPath()%>/frontend/DBPicReader/DBPicReader.do?id_no=${memVO.mem_no}&action=member" class="img-circle" width="40">&nbsp;${memVO.mem_name} </a></li>
	                        			<input type="hidden" name="fmem_name" id="fmem_name" value="${memVO.mem_no}">  <!--���F��ɧ�n�J�|�����|���W�� �nSHOW�b��ѵ�����-->	
									</c:if>
	                            </c:forEach>
	                         </c:if>   
	                         <%--end �P�_�n�ͬO�_���--%>
                         </c:forEach> 
                         <%--��ܷ|���n�ͦC��--%>   
                    </ul>
                </div>
            </div>
            <!-- �n�ͦC��-�n�Ͱ� -->

	        <!-- �n�ͦC��-�s�հ� -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="panel2">
                    <h4 class="panel-title">
                        <a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
                      		  �s�զC��
                        </a>
                    </h4>
                </div>
                <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
                    <ul class="list-group">
                    	<%--��ܷ|���s�զC��--%>
            			<c:forEach var="cgVO" items="${cglsit}">
			                <c:forEach var="cg1VO" items="${cgSvc.all}">
			                	<c:if test="${cgVO.cg_no==cg1VO.cg_no}">            			
                       				<li class="list-group-item">&nbsp;${cg1VO.cg_name}</li>
                       			</c:if>
              				</c:forEach>
                    	</c:forEach>
                    	<%--��ܷ|���s�զC��--%>
                    </ul>
                </div>
            </div>
            <!-- �n�ͦC��-�s�հ� -->                  
	    </ul>
	    <!--�n�ͦC��̰����� �H�α��b-->
	</div>

	<!--�n�ͦC���e��-->     
	<style>
	    .dropdown-menu {
	        min-width: 320px;
	    }
	</style>
	    
</body>
    
   

<script>
	var strAry, inputUserName, fmem_no,mem_no;
	mem_no = "${sessionScope.mem_no}";																			 //sessionScope���X�ڪ��|���s��
	var moveCount = 0;
	//�ۤv����}��
    $(function(){
    	$("a[id^='addClass']").click(function () {
 				var str;
 				var margin = moveCount * 310  + 280;
 				alert(margin);
 				moveCount++;
				str="<aside id=\"sidebar_secondary\" class=\"tabbed_sidebar ng-scope chat_sidebar\" style=\"margin-right:"+margin+"px\"> ";
				str+="<!--��ѵ���Head-->";
				str+="<div class=\"popup-head\">";
				str+="   <div class=\"popup-head-left pull-left\">";
				str+="		<c\:forEach var=\"memVO\" items=\"${memSvc.all}\" >";
				str+="            <c\:if test=\"${sessionScope.mem_no==memVO.mem_no}\">";
				str+="	 				<img src=\"<%=request.getContextPath()%>/frontend/DBPicReader/DBPicReader.do?id_no=${sessionScope.mem_no}&action=member\" class=\"img-circle\" width=\"50\" id=\"myPhoto\">";
				str+="			</c\:if>";
				str+="        </c\:forEach>";
				str+="		<h1></h1>";
				str+="	</div>";
				str+="	<div class=\"popup-head-right pull-right\">";
				str+="    	<button class=\"chat-header-button\" type=\"button\"><i class=\"glyphicon glyphicon-facetime-video\"></i></button>";
				str+="   		<button class=\"chat-header-button\" type=\"button\"><i class=\"glyphicon glyphicon-earphone\"></i></button>";
				str+="    	<div class=\"btn-group gurdeepoushan\">";
				str+="         <button class=\"chat-header-button\" data-toggle=\"dropdown\" type=\"button\" aria-expanded=\"false\">";
				str+="         	<i class=\"glyphicon glyphicon-paperclip\"></i> ";
				str+="          </button>";
				str+="             <ul role=\"menu\" class=\"dropdown-menu pull-right\">";
				str+="                 <li><a href=\"#\"><span class=\"glyphicon glyphicon-picture\" aria-hidden=\"true\"></span> Gallery</a></li>";
				str+="                 <li><a href=\"#\"><span class=\"glyphicon glyphicon-camera\" aria-hidden=\"true\"></span> Photo</a></li>";
				str+="                 <li><a href=\"#\"><span class=\"glyphicon glyphicon-facetime-video\" aria-hidden=\"true\"></span> Video</a></li>";
				str+="                 <li><a href=\"#\"><span class=\"glyphicon glyphicon-headphones\" aria-hidden=\"true\"></span> Audio</a></li>";
				str+="                 <li><a href=\"#\"><span class=\"glyphicon glyphicon-map-marker\" aria-hidden=\"true\"></span> Location</a></li>";
				str+="                 <li><a href=\"#\"><span class=\"glyphicon glyphicon-user\" aria-hidden=\"true\"></span> Contact</a></li>";
				str+="           </ul>";
				str+="         </div>";
			                    
				str+="          <button data-widget=\"remove\" id=\"removeClass\" class=\"chat-header-button pull-right removeClass\" type=\"button\"><i class=\"glyphicon glyphicon-remove\"></i></button>";
				str+="  	</div>";
				str+=" </div>";
				str+=" <!--��ѦC���e��-->";
				str+=" 	<div id=\"chat\" class=\"chat_box_wrapper chat_box_small chat_box_active\" style=\"opacity\: 1; display\: block; transform\: translateX(0px);\">";
				str+="        <div class=\"chat_box touchscroll chat_box_colors_a\">";
				str+="            <div class=\"chat_message_wrapper\">";

				str+="       			<textarea id=\"messagesArea\" rows=\"14\" cols=\"30\" class=\"panel message-area\" readonly ></textarea>";
				str+="             </div>";

				str+="        </div>";
				str+="     </div>";
				str+=" <!--��ѵ����e�X�T��-->   ";                
				str+="	<div class=\"chat_submit_box\">";
				str+="		<div class=\"uk-input-group\">";
				str+="         <div class=\"gurdeep-chat-box\">";
				str+="      		<span style=\"vertical-align\: sub;\" class=\"uk-input-group-addon\">";
				str+="         			<a href=\"#\"><i class=\"fa fa-smile-o\"></i></a>";
				str+="        		</span>";
				str+="        	<input type=\"text\" placeholder=\"Type a message\" id=\"message\" name=\"message\" class=\"text-field\" onkeydown=\"if (event.keyCode == 13) sendMessage();\"/>";
				str+="         	<input type=\"submit\" id=\"sendMessage\" class=\"button\" value=\"�e�X\" onclick=\"sendMessage();\"/>";
				str+=" 	        <span style=\"vertical-align\: sub;\" class=\"uk-input-group-addon\">";
				str+=" 	      	  <a href=\"#\"><i class=\"fa fa-camera\"></i></a>";
				str+=" 	        </span>";
				str+="        </div>";
				    
				str+=" 	    <span class=\"uk-input-group-addon\">";
				str+=" 	  	  <a href=\"#\"><i class=\"glyphicon glyphicon-send\"></i></a>";
				str+=" 	    </span>";
				str+="     </div>";
				str+=" </div>";
				str+=" </aside>";
				$("#divArea").after(str);
				/////////////////////////////
    			var str=$(this).html();																			 //�Q�I�������qHTML
    			strAry = str.split("&nbsp;");		
    			fmem_no = str.substr(81,8);                                                                      //�qstr�̭���X���|���s���A�n�ǵ��U�����Ϥ�Reader 
    			console.log("mem_no = "+mem_no);    			
    			console.log("fmem_no= "+fmem_no);
    			inputUserName = document.getElementById("inputUserName").innerText;                              //��X��J�̪��m�W
    			$('#sidebar_secondary').find("h1").html(strAry[1]);                                              //�ʺA�[�J���W��
    			$('#sidebar_secondary').find("div").find("img").attr("title",strAry[1]).attr("alt",strAry[1]); 	 //�ʺA�[�Jimg title&alt
    			$('#sidebar_secondary').find("div").find("img").attr("src", "<%=request.getContextPath()%>/frontend/DBPicReader/DBPicReader.do?id_no="+ fmem_no +"&action=member");
    			
    			/////////////////
    			MyPoint = "/MyWebSocketServer/"+ mem_no +"/"+ fmem_no ;
    		    endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    		    connect();
    		    /////////////////
    			$('#sidebar_secondary').addClass('popup-box-on');
    		    
     			$(".removeClass").click(function () {
    				alert("���S���i��remove1");
    				disconnect();
    				document.getElementById("messagesArea").value="";     //��textarea�̭����e�M��
    			    $(this).parents("aside").removeClass('popup-box-on');
    			});
		    });
    	//�j�ݱо�
		  function xx(){
				alert("���S���i��remove");
				disconnect();
				document.getElementById("messagesArea").value="";     //��textarea�̭����e�M��
			    $('#sidebar_secondary').removeClass('popup-box-on');
		  }
		$(".removeClass").click(xx); 
		//�j�ݱо�
    	$("#controllBtn").click(function(){
    		if(!$("#divArea").hasClass("dropup open")){
    			$("#divArea").addClass("dropup open");
    		}else{
    			$("#divArea").removeClass("dropup open");
    		}
    		
    	})
    })
	console.log("��k�᭱="+fmem_no);
    var MyPoint = "/MyWebSocketServer/"+ mem_no +"/"+ fmem_no ;
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	
	function connect() {
		// �إ� websocket ����
		console.log("connect()�̭�= "+ endPointURL)
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			updateStatus("WebSocket ���\�s�u");
			document.getElementById('sendMessage').disabled = false;
			document.getElementById('connect').disabled = true;
			document.getElementById('disconnect').disabled = false;
		};

		webSocket.onmessage = function(event) { //object MessageEvent
			var messagesArea = document.getElementById("messagesArea");
	        var jsonObj = JSON.parse(event.data);

	        var message = jsonObj.userName + ": " + jsonObj.message + "\r\n";
	        messagesArea.value = messagesArea.value + message;
	        messagesArea.scrollTop = messagesArea.scrollHeight;
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket �w���u");
		};
	}
	
	
	//var inputUserName = document.getElementById("mem_name").innerText;  //������line 51��
	//var inputUserName = document.getElementById("addClass").innerText;

	
	
	function sendMessage() {
	    //var userName = inputUserName.toString();
	    var userName = inputUserName;
	    console.log("�ڭn�o�T��userName���S���F��: "+userName);

	    
	    var inputMessage = document.getElementById("message");  //�n���令�ʺA�n���令�ʺA�n���令�ʺA�n���令�ʺA�n���令�ʺA
	    var message = inputMessage.value.trim();
	    
	    if (message === ""){
	        alert ("�T���ФŪť�!");
	        inputMessage.focus();	
	    }else{
	        var jsonObj = {"userName" : userName, "message" : message};
	        webSocket.send(JSON.stringify(jsonObj));
	        inputMessage.value = "";
	        inputMessage.focus();
	    }
	}

	
	function disconnect () {
		webSocket.close();
		document.getElementById('sendMessage').disabled = true;
		document.getElementById('connect').disabled = false;
		document.getElementById('disconnect').disabled = true;
	}

	
	function updateStatus(newStatus) {
		statusOutput.innerHTML = newStatus;
	}
    

    window.onunload=disconnect;
    
    
</script>
</html>
