<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="http://bit.ly/3WJ5ilK" />
</head>
<body>
    <!-- ex03.jsp -->
    <h1>Ajax</h1>
    
    <div>
        <input type="button" value="클릭" id="btn1">
    </div>
     
    <div id="result" class="message"></div>
    
    <div>
        <input type="text">
    </div>
 
    <script src="https://code.jquery.com/jquery-4.0.0.js"></script>    
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        /*
        
            동기(Synchronous) vs 비동기(Asynchronous)
            
            동기 방식: 카페 > 음료 주문
            1. 매장 방문
            2. 주문
            3. 음료 제조
            4. 전달
            5. 마시기
            
            비동기 방식: 카페 > 음료 주문
            1. 매장 방문
            2. 주문
            3. 음료 제조
            (시간 소요)
            4. 전달
            5. 마시기
            
        
        */
        $('#btn1').click(() => {
        	
        	//기본 > 비동기 통신
        	const ajax = new XMLHttpRequest();
        	  
        	ajax.onreadystatechange = function() {
            	
            	if (ajax.readyState == 4 && ajax.status == 200) {
            		$('.message').append(`<div>데이터 수신 완료</div>`);
            		$('.message').append(`<div>레코드수: \${ajax.responseText}</div>`);
            	} else if (ajax.readyState == 4 && ajax.status != 200) {
            		$('.message').append(`<div>오류 발생</div>`);
            	}
            	
            }; 
            
            //true(비동기), false(동기)
            ajax.open('GET', '/ajax/ex03ok.do');
            
            ajax.send();
            
        });
    
    </script>
</body>
</html>






