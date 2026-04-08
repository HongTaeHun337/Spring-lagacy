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
    <!-- map05.jsp -->
    <h1>Kakao Map</h1>
    
    <div>
        <div id="map"></div>
    </div>
    
    <form action="/api/add.do" method="POST">
    <div>
    	<input type="submit" value="마커 추가하기">
    </div>
    <input type="hidden" name="lat">
    <input type="hidden" name="lng">
    </form>
    
    <script src="https://code.jquery.com/jquery-4.0.0.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=dc1e081e1128d0cad387915ccc0bad29"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        var container = document.getElementById('map');
        
        var options = {
            center: new kakao.maps.LatLng(37.504742, 127.053156),
            level: 3
        };
    
        var map = new kakao.maps.Map(container, options);
        
        let m = null;
        
        // 콜백 함수 > 인자로 전달되는 함수
        kakao.maps.event.addListener(map, 'click', evt => {
        	
        	if (m != null) m.setMap(null);
        	
        	const img = new kakao.maps.MarkerImage(
        		'/api/resources/marker/favorite.png',
        		new kakao.maps.Size(64,64),
        		{ offset: new kakao.maps.Point(32, 64) }
        	);
        	
        	m = new kakao.maps.Marker({
        		position: evt.latLng,
        		image: img
        	});
        	
        	m.setMap(map);
        
        	//클릭 좌표 > 히든 태그
        	$('input[name=lat]').val(evt.latLng.getLat());
        	$('input[name=lng]').val(evt.latLng.getLng());
        });
        
        
    
    </script>
</body>
</html>