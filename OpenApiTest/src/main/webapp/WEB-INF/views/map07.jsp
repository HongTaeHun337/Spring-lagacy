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
    <!-- map07.jsp -->
    <h1>Kakao Map <small>길찾기</small></h1>
    
    <div>
        <div id="map"></div>
    </div>
    <hr>
    <div>
        <input type="button" value="길찾기" id="btnSearch">
        <input type="button" value="초기화" id="btnReset">
        <input type="button" value="새로고침"
            onclick="location.href='/api/map07.do';">
    </div>
    
    <form method="POST" action="/api/map07ok.do">
        <input type="hidden" name="startLat">
        <input type="hidden" name="startLng">
        <input type="hidden" name="endLat">
        <input type="hidden" name="endLng">
    </form>    
    
    <script src="https://code.jquery.com/jquery-4.0.0.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bc11f2c1d9ac19ad396b5861eb348a12"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        var container = document.getElementById('map');
        
        var options = {
            center: new kakao.maps.LatLng(37.504742, 127.053156),
            level: 3
        };
    
        var map = new kakao.maps.Map(container, options);
        
        
        let startMarker = null; //출발지
        let endMarker = null;   //종착지
        
        kakao.maps.event.addListener(map, 'click', evt => {
        	
        	if (startMarker == null) {
        		
        		const path = '/api/resources/marker/navigator.png';
        		const size = new kakao.maps.Size(64, 64);
        		const op = { offset: new kakao.maps.Point(32, 64) };
        		const img = new kakao.maps.MarkerImage(path, size, op);
        		
        		startMarker = new kakao.maps.Marker({
        			position: evt.latLng,
        			image: img
        		});
        		
        		startMarker.setMap(map);
        		
        		$('input[name=startLat]').val(evt.latLng.getLat());
        		$('input[name=startLng]').val(evt.latLng.getLng());
        		
        	} else if (startMarker != null && endMarker == null) {
        		
        		const path = '/api/resources/marker/bus-stop.png';
                const size = new kakao.maps.Size(64, 64);
                const op = { offset: new kakao.maps.Point(32, 64) };
                const img = new kakao.maps.MarkerImage(path, size, op);
                
                endMarker = new kakao.maps.Marker({
                    position: evt.latLng,
                    image: img
                });
                
                endMarker.setMap(map);
                
                $('input[name=endLat]').val(evt.latLng.getLat());
                $('input[name=endLng]').val(evt.latLng.getLng());
        		
        	}
        	        	
        });
        
        
        $('#btnReset').click(() => {
        	
        	if (startMarker != null) startMarker.setMap(null);
        	if (endMarker != null) endMarker.setMap(null);
        	startMarker = null;
        	endMarker = null;
        	
        	$('input[name=startLat]').val('');
            $('input[name=startLng]').val('');
            $('input[name=endLat]').val('');
            $('input[name=endLng]').val('');
        	
        });
        
        $('#btnSearch').click(() => {
        	
        	$('form').submit();
        	
        });
        
        
        
        <c:if test="${not empty routeData}">
        
        var routeData = ${routeData};
        
        function drawPath(result) {
        	
            var linePath = [];
            var bounds = new kakao.maps.LatLngBounds();

            result.routes[0].sections.forEach(function(section) {
                section.roads.forEach(function(road) {
                    for (var i = 0; i < road.vertexes.length; i += 2) {
                        var point = new kakao.maps.LatLng(road.vertexes[i+1], road.vertexes[i]);
                        linePath.push(point);
                        bounds.extend(point);
                    }
                });
            });

            /*
            	dashdot
            	longdashdot
            	longdash
            */
            
            var polyline = new kakao.maps.Polyline({
                path: linePath,
                strokeWeight: 5,
                strokeColor: 'cornflowerblue',
                strokeOpacity: .7,
                strokeStyle: 'solid'
            });
   
            polyline.setMap(map);
            map.setBounds(bounds);
            
            
            
            //시작/도착 마커 표시
            
            const path = '/api/resources/marker/navigator.png';
            const size = new kakao.maps.Size(64, 64);
            const op = { offset: new kakao.maps.Point(32, 64) }
            const img = new kakao.maps.MarkerImage(path, size, op);
            
            var startMarker = new kakao.maps.Marker({
                position: linePath[0],
                map: map,
                image: img
            });
            
            
            const path2 = '/api/resources/marker/bus-stop.png';
            const size2 = new kakao.maps.Size(64, 64);
            const op2 = { offset: new kakao.maps.Point(32, 64) }
            const img2 = new kakao.maps.MarkerImage(path2, size2, op2);

            // 도착지 마커
            var endMarker = new kakao.maps.Marker({
                position: linePath[linePath.length - 1],
                map: map,
                image: img2
            });
            
            
            
        }

        drawPath(routeData);
        </c:if>
        
    
    </script>
</body>
</html>





















