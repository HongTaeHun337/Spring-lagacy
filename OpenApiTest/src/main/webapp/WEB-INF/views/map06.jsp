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
    <!-- map06.jsp -->
    <h1>Kakao Map <small>좌표와 주소간의 변환</small></h1>
    
    <div>
        <div id="map"></div>
    </div>
    <hr>
    <div>
        <input type="text" id="address" class="full"
            placeholder="주소를 입력하세요.">
    </div>
    
    <script src="https://code.jquery.com/jquery-4.0.0.js"></script>
    <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=bc11f2c1d9ac19ad396b5861eb348a12&libraries=services"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        var container = document.getElementById('map');
        
        var options = {
            center: new kakao.maps.LatLng(37.504742, 127.053156),
            level: 3
        };
    
        var map = new kakao.maps.Map(container, options);
        
        $('#address').keydown(evt => {
        	if (evt.keyCode == 13) {
        		search();
        	}
        });
        
        
        let m = null;
        
        function search() {
        	
        	const geocoder = new kakao.maps.services.Geocoder();
        	
        	geocoder.addressSearch($('#address').val(), function(result, status) {
        		
        		//console.log(result);
        		//result[0].y
        		
        		//console.log(status);
        		
        		if (status == kakao.maps.services.Status.OK) {
        			
        			if (m != null) m.setMap(null);
        			
        			m = new kakao.maps.Marker({
        				position: new kakao.maps.LatLng(result[0].y, result[0].x)
        			});
        			m.setMap(map);
        			map.panTo(m.getPosition());
        			
        		} else {
        			alert('잘못된 주소를 입력했습니다.');
        			$('#address').select();
        		}
        		
        	});
        	
        }
        
        kakao.maps.event.addListener(map, 'click', evt => {
        	
        	if (m != null) m.setMap(null);
            
            m = new kakao.maps.Marker({
                position: evt.latLng
            });
            m.setMap(map);
            map.panTo(m.getPosition());
            
            
            //좌표 > 주소
            const geocoder = new kakao.maps.services.Geocoder();
            
            geocoder.coord2Address(evt.latLng.getLng(), evt.latLng.getLat(), function(result, status) {
            	
            	if (status == kakao.maps.services.Status.OK) {
            		//console.log(result);
            		
            		$('#address').val(result[0].address.address_name);
            		
            	} else {
            		alert('해당 위치의 주소를 찾지 못했습니다.');
            	}
            	
            });
        	
        });
    
    </script>
</body>
</html>














