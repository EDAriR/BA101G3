function doFirst() {
		navigator.geolocation.getCurrentPosition(succCallback);
}

function succCallback(e) {
	var lati = e.coords.latitude;
	var longi = e.coords.longitude;

	var myMap = document.getElementById("myMap");
	var myPosition = new google.maps.LatLng(lati,longi);
	
	var map = new google.maps.Map(myMap, {
		zoom : 16,
		center : myPosition,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	});

	var marker = new google.maps.Marker({
		position : myPosition,
		map : map,
		title : "目前位置"
	})

}

// function showInfo(shop){
// 	switch(shop.id){
// 		case 'sb':
// 			getLocation(sb, 'STARBUCKS');
// 			break; 
// 		case 'se':
// 			getLocation(se, '7-11');
// 			break ; 
// 		case 'family':
// 			getLocation(family, '全家');
// 			break ;
// 		case 'md':
// 			getLocation(md, '麥當勞');
// 			break ;
// 	}
// }
// var markers = new Array(); 
// function getLocation(as,title){
// 	var i = 0;
// 	for(var k in markers){
// 		markers[k].setVisible(false);
// 	}
// 	for(var k in as){
// 		var lati = as[k].split(',')[0]; 
// 		var longi = as[k].split(',')[1]; 
// 		var xy = new google.maps.LatLng(lati, longi);
// 		var marker = new google.maps.Marker({ 
// 			position: xy, 
// 			map: map 
// 		});
// 		marker.setTitle(title); 
// 		markers[i]  = marker;i++ ;           
// 	}           
// }

window.addEventListener("load", doFirst, false);
