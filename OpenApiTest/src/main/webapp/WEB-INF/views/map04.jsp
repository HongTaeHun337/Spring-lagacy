<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="http://bit.ly/3WJ5ilK" />
    <style>
        #map {
            width: 770px;
            height: 500px;
        }
    </style>
</head>
<body>
    <!-- map04.jsp -->
    <h1>Kakao Map <small>마커</small></h1>
    
    <div>
        <div id="map"></div>
    </div>
    <hr>
    <div class="message">&nbsp;</div>
    
    <script src="https://code.jquery.com/jquery-4.0.0.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bc11f2c1d9ac19ad396b5861eb348a12"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        var container = document.getElementById('map');
        
        var options = {
            center: new kakao.maps.LatLng(37.499330, 127.033181),
            level: 3
        };
    
        var map = new kakao.maps.Map(container, options);
        
        //자바 데이터 > (매핑) > 자바스크립트 데이터
        const mlist = [];
        
        <c:forEach items="${list}" var="dto">
        mlist.push({
        	seq: ${dto.seq},
        	lat: ${dto.lat},
        	lng: ${dto.lng}
        });
        </c:forEach>
        
        mlist.forEach(item => {
        	const m = new kakao.maps.Marker({
        		position: new kakao.maps.LatLng(item.lat, item.lng)
        	});
        	m.setMap(map);
        });
        
        
        
        //현재 보이는 지도상(영역)의 나타난 마커 개수 세기
        let count = 0;
        
        function countMarker() {
        	
        	count = 0;
        	
        	mlist.forEach(item => {
        		if (containMarker(item)) {
        			count++;
        		}
        	});
        	
        	$('.message').text(`현재 보이는 마커의 개수는 \${count}개입니다.`);
        	
        }
        
        countMarker();
        
        function containMarker(item) {
        	
        	//현재 지도의 영역?
        	const sw = map.getBounds().getSouthWest(); //LatLng
        	const ne = map.getBounds().getNorthEast();
        	
        	if (item.lat >= sw.getLat()
        		&& item.lat <= ne.getLat()
        		&& item.lng >= sw.getLng()
        		&& item.lng <= ne.getLng()) {
        		return true;
        	}
        	
        	return false;        	
        }
        
        
        //지도 이벤트(드래그)
        kakao.maps.event.addListener(map, 'dragend', evt => {
        	countMarker();
        });
        
        //지도 확대/축소
        kakao.maps.event.addListener(map, 'zoom_changed', evt => {
        	countMarker();
        });
        
    
    </script>
</body>
</html>




