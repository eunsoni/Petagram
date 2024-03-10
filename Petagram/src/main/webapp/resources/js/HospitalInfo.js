onload = function(){
	createMap(); // 실제 구현할 때 검색 주소에 해당하는 좌표 리스트로 받고 마커 찍기
	searchByAddress();
};

function createMap(){
	let map = new naver.maps.Map('map', {
        center: new naver.maps.LatLng(37.2893525, 127.0535312), // 초기설정: 경기도청
        zoom: 15
    });
};

function updateMap(allInfo, longitude, latitude){
	map = new naver.maps.Map('map', {
        center: new naver.maps.LatLng(latitude, longitude),
        zoom: 15
    });

	let marker = new naver.maps.Marker({
        position: new naver.maps.LatLng(latitude, longitude),
        map: map
    });
	
	let infoWindow = new naver.maps.InfoWindow({
		content: allInfo
	});
	
	naver.maps.Event.addListener(marker, "click", function(e){
		if(infoWindow.getMap()){
			infoWindow.close;
		} else {
			infoWindow.open(map, marker);
		}
	});			
};

function searchByAddress(){
	$("#searchBtn").on("click", function(){
		$.ajax({
			url: "/api/hospital/search",
			data: {address: $("#searchInput").val()},
			type: "post",
			dataType: "json",
			success: function(response){				
				let showInfo = document.getElementById("showInfo");

				let searchInfo = document.createElement("div");
				searchInfo.setAttribute("id", "searchInfo");
				
				for(let i = 0; i <= response.length; i++){
					let info = document.createElement("span");
					info.setAttribute("id", "info"+i);
					
					let nameText = document.createElement("a");
					nameText.setAttribute("href", "javascript:void(0)");
					nameText.setAttribute("id", "name"+i);
					nameText.style.textDecorationLine = "none";
					nameText.style.color = "black";
												
					let addrText = document.createElement("p");
					addrText.setAttribute("id", "commentText");
					
					info.appendChild(nameText);
					info.appendChild(addrText);

					nameText.innerHTML += "<h4>" + response[i].hospitalName + "</h4>";
					addrText.innerHTML += response[i].localAddr;
					
					info.innerHTML += "<hr>";
					searchInfo.appendChild(info);
					showInfo.appendChild(searchInfo);
					
					let allInfo = [
						"<div id='allInfo'>",
						"<h4>"+response[i].hospitalName+"</h4>",
						"<p>"+response[i].localAddr+"</p>",
						"</div>"
					].join("");

					$("#name"+i).on("click", function(){
						updateMap(allInfo, response[i].longitude, response[i].latitude);
					});
				}
			},
			error: function(request, e){
				alert("코드: " + request.status + "메시지: " + request.responseText + "오류: " + e);
			}
		});
	});
};