$(document).ready(function(){

	var title = $("#inOutPic").attr('title');
	var src = $("#inOutPic").attr('src');

	$("#inOutPic").click(function(){
		//alert(title);
		//alert(src);
		if(title === '登入'){
			$("#inOutPic").attr('title','登出');
			$("#inOutPic").attr('src','/BA101G3/frontend/pic/logout_01.png');
			$('#lightBox').fadeIn(1000); 
			$('.panel-login').fadeIn(1000); 
        	$('.form-control').val('');
		}
	});

	
	$(".btn-cancel").click(function(){
		$("#inOutPic").attr('title','登入');
		$("#inOutPic").attr('src','/BA101G3/frontend/pic/login_01.png');	
		$('#lightBox').fadeOut(1200);          
 		$('.panel-login').fadeOut(1200); 
	});
	
	
    $('#login-form-link').click(function(e) {
		$("#login-form").delay(300).fadeIn(300);
 		$("#register-form").fadeOut(300);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
    
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(300).fadeIn(300);
 		$("#login-form").fadeOut(300);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});


});

function $id(id){
	return document.getElementById(id);
}

var zip = [];
//市
zip[0] = ['台北市','基隆市','新北市','宜蘭縣','新竹市','新竹縣','桃園縣','苗栗縣','台中市','彰化縣','南投縣','嘉義市','嘉義縣','雲林縣','台南市','高雄市','屏東縣','台東縣','花蓮縣','澎湖縣','金門縣','連江縣'];

//初始化
function init_address(){
    var zone1 = document.getElementById('zone1');
    zone1.options.add(first());
    //市
	for(var i in zip[0]){
        o=document.createElement('option');
        o.text=zip[0][i];
        o.value=zip[0][i];
        zone1.options.add(o);
	}
}

//第一個選項
function first(){
	var o=document.createElement('option');
	o.text='請選擇';
	o.value="";
	return o;
}

function init(){
	$id("zone1").onclick = init_address;
}
window.onload=init;
