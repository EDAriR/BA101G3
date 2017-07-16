<%@ page import="com.chat.model.Chat_FriendVO" %>
<%@ page import="com.chat.model.Chat_Group_ItemVO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%-- 此頁練習採用 EL 的寫法取值 --%>
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
		    <input type="button" id="connect"     class="button" value="連線" onclick="connect();"/>
		    <input type="button" id="disconnect"  class="button" value="離線" onclick="disconnect();"/>
	    </div>
	    <!--  XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX -->
	    
	    

	<div id="divArea" class="btn-group" style="position: fixed; bottom:0px; right:5px;">
	    <button id="controllBtn" type="button" class="btn btn-default"  aria-haspopup="true"
	            aria-expanded="false">
	        <i class="fa fa-comment fa-3x"></i>
	    </button>

        <!--好友列表最高高度 以及捲軸-->
	    <ul class="dropdown-menu dropdown-menu-right" style="font-size: 16px;font-family:'Microsoft JhengHei';  max-height:600px; height:600px; overflow-y: scroll;"> 
	        <!-- 好友列表-個人區 -->
	        <li class="drop-down-header">
	            <form class="navbar-form navbar-left" role="search">
      				<c:forEach var="memVO" items="${memSvc.all}" >
                        <c:if test="${sessionScope.mem_no==memVO.mem_no}">
<!--大頭照-->  				<img src="<%=request.getContextPath()%>/frontend/DBPicReader/DBPicReader.do?id_no=${memVO.mem_no}&action=member" class="img-circle" width="50">
<!--姓名--> 					<span id="inputUserName">${memVO.mem_name}</span>
						</c:if>
                    </c:forEach>
					<input type="hidden" name="mem_name" id="mem_name" value="${memVO.mem_name}">  <!--為了到時抓登入會員的會員名稱 要SHOW在聊天視窗內-->	
<!--搜尋Bar	                <div class="input-group mb-2 mr-sm-2 mb-sm-0" >
	                    <div class="input-group-addon"><i class="fa fa-search"></i></div>
	                    <input type="text" class="form-control" id="inlineFormInputGroup" placeholder="Username" >
	                </div>--> 
	            </form>
	        </li>
			<!-- 好友列表-個人區 -->
			
            <li><a href="#"><span class="glyphicons glyphicons-map-marker"></span></a></li>  <!--為了多一點留白,刪掉會跑版-->

            <!-- 好友列表-好友區 -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="panel1">
                    <h4 class="panel-title">
                    	<a href="#aaa" data-parent="#accordion2" data-toggle="collapse" role="button" aria-expanded="true" aria-controls="aaa">
                      		 好友列表
                    	</a>
                    </h4>
                </div>
                <div id="aaa" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="panel1">
                    <ul class="list-group">
                    	<%--顯示會員好友列表--%>
						<c:forEach var="cflsit" items="${cflist}">
							<%--判斷好友是否顯示--%>
							<c:if test="${cflsit.cf_is_del==0}">
								<c:forEach var="memVO" items="${memSvc.all}" >
	                                <c:if test="${cflsit.mem_no_o==memVO.mem_no}">
	                        			<li class="list-group-item"> <a href="#" id="addClass_${memVO.mem_no}"><img src="<%=request.getContextPath()%>/frontend/DBPicReader/DBPicReader.do?id_no=${memVO.mem_no}&action=member" class="img-circle" width="40">&nbsp;${memVO.mem_name} </a></li>
	                        			<input type="hidden" name="fmem_name" id="fmem_name" value="${memVO.mem_no}">  <!--為了到時抓登入會員的會員名稱 要SHOW在聊天視窗內-->	
									</c:if>
	                            </c:forEach>
	                         </c:if>   
	                         <%--end 判斷好友是否顯示--%>
                         </c:forEach> 
                         <%--顯示會員好友列表--%>   
                    </ul>
                </div>
            </div>
            <!-- 好友列表-好友區 -->

	        <!-- 好友列表-群組區 -->
            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="panel2">
                    <h4 class="panel-title">
                        <a href="#bbb" data-parent="#accordion2" data-toggle="collapse" role="button" class="collapsed" aria-expanded="false" aria-controls="bbb">
                      		  群組列表
                        </a>
                    </h4>
                </div>
                <div id="bbb" class="panel-collapse collapse" role="tabpanel" aria-labelledby="panel2">
                    <ul class="list-group">
                    	<%--顯示會員群組列表--%>
            			<c:forEach var="cgVO" items="${cglsit}">
			                <c:forEach var="cg1VO" items="${cgSvc.all}">
			                	<c:if test="${cgVO.cg_no==cg1VO.cg_no}">            			
                       				<li class="list-group-item">&nbsp;${cg1VO.cg_name}</li>
                       			</c:if>
              				</c:forEach>
                    	</c:forEach>
                    	<%--顯示會員群組列表--%>
                    </ul>
                </div>
            </div>
            <!-- 好友列表-群組區 -->                  
	    </ul>
	    <!--好友列表最高高度 以及捲軸-->
	</div>

	<!--好友列表的寬度-->     
	<style>
	    .dropdown-menu {
	        min-width: 320px;
	    }
	</style>
	    
</body>
    
   

<script>
	var strAry, inputUserName, fmem_no,mem_no;
	mem_no = "${sessionScope.mem_no}";																			 //sessionScope取出我的會員編號
	var moveCount = 0;
	//自己控制開關
    $(function(){
    	$("a[id^='addClass']").click(function () {
 				var str;
 				var margin = moveCount * 310  + 280;
 				alert(margin);
 				moveCount++;
				str="<aside id=\"sidebar_secondary\" class=\"tabbed_sidebar ng-scope chat_sidebar\" style=\"margin-right:"+margin+"px\"> ";
				str+="<!--聊天視窗Head-->";
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
				str+=" <!--聊天列內容區-->";
				str+=" 	<div id=\"chat\" class=\"chat_box_wrapper chat_box_small chat_box_active\" style=\"opacity\: 1; display\: block; transform\: translateX(0px);\">";
				str+="        <div class=\"chat_box touchscroll chat_box_colors_a\">";
				str+="            <div class=\"chat_message_wrapper\">";

				str+="       			<textarea id=\"messagesArea\" rows=\"14\" cols=\"30\" class=\"panel message-area\" readonly ></textarea>";
				str+="             </div>";

				str+="        </div>";
				str+="     </div>";
				str+=" <!--聊天視窗送出訊息-->   ";                
				str+="	<div class=\"chat_submit_box\">";
				str+="		<div class=\"uk-input-group\">";
				str+="         <div class=\"gurdeep-chat-box\">";
				str+="      		<span style=\"vertical-align\: sub;\" class=\"uk-input-group-addon\">";
				str+="         			<a href=\"#\"><i class=\"fa fa-smile-o\"></i></a>";
				str+="        		</span>";
				str+="        	<input type=\"text\" placeholder=\"Type a message\" id=\"message\" name=\"message\" class=\"text-field\" onkeydown=\"if (event.keyCode == 13) sendMessage();\"/>";
				str+="         	<input type=\"submit\" id=\"sendMessage\" class=\"button\" value=\"送出\" onclick=\"sendMessage();\"/>";
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
    			var str=$(this).html();																			 //被點擊的那段HTML
    			strAry = str.split("&nbsp;");		
    			fmem_no = str.substr(81,8);                                                                      //從str裡面拆出對方會員編號，要傳給下面的圖片Reader 
    			console.log("mem_no = "+mem_no);    			
    			console.log("fmem_no= "+fmem_no);
    			inputUserName = document.getElementById("inputUserName").innerText;                              //找出輸入者的姓名
    			$('#sidebar_secondary').find("h1").html(strAry[1]);                                              //動態加入對方名稱
    			$('#sidebar_secondary').find("div").find("img").attr("title",strAry[1]).attr("alt",strAry[1]); 	 //動態加入img title&alt
    			$('#sidebar_secondary').find("div").find("img").attr("src", "<%=request.getContextPath()%>/frontend/DBPicReader/DBPicReader.do?id_no="+ fmem_no +"&action=member");
    			
    			/////////////////
    			MyPoint = "/MyWebSocketServer/"+ mem_no +"/"+ fmem_no ;
    		    endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    		    connect();
    		    /////////////////
    			$('#sidebar_secondary').addClass('popup-box-on');
    		    
     			$(".removeClass").click(function () {
    				alert("有沒有進來remove1");
    				disconnect();
    				document.getElementById("messagesArea").value="";     //把textarea裡面內容清空
    			    $(this).parents("aside").removeClass('popup-box-on');
    			});
		    });
    	//強禾教學
		  function xx(){
				alert("有沒有進來remove");
				disconnect();
				document.getElementById("messagesArea").value="";     //把textarea裡面內容清空
			    $('#sidebar_secondary').removeClass('popup-box-on');
		  }
		$(".removeClass").click(xx); 
		//強禾教學
    	$("#controllBtn").click(function(){
    		if(!$("#divArea").hasClass("dropup open")){
    			$("#divArea").addClass("dropup open");
    		}else{
    			$("#divArea").removeClass("dropup open");
    		}
    		
    	})
    })
	console.log("方法後面="+fmem_no);
    var MyPoint = "/MyWebSocketServer/"+ mem_no +"/"+ fmem_no ;
    var host = window.location.host;
    var path = window.location.pathname;
    var webCtx = path.substring(0, path.indexOf('/', 1));
    var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
    
	var statusOutput = document.getElementById("statusOutput");
	var webSocket;
	
	function connect() {
		// 建立 websocket 物件
		console.log("connect()裡面= "+ endPointURL)
		webSocket = new WebSocket(endPointURL);
		
		webSocket.onopen = function(event) {
			updateStatus("WebSocket 成功連線");
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
			updateStatus("WebSocket 已離線");
		};
	}
	
	
	//var inputUserName = document.getElementById("mem_name").innerText;  //對應到line 51行
	//var inputUserName = document.getElementById("addClass").innerText;

	
	
	function sendMessage() {
	    //var userName = inputUserName.toString();
	    var userName = inputUserName;
	    console.log("我要發訊息userName有沒有東西: "+userName);

	    
	    var inputMessage = document.getElementById("message");  //要怎麼改成動態要怎麼改成動態要怎麼改成動態要怎麼改成動態要怎麼改成動態
	    var message = inputMessage.value.trim();
	    
	    if (message === ""){
	        alert ("訊息請勿空白!");
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
