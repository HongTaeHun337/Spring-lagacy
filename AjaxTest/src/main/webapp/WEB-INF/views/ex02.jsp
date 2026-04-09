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
    <!-- ex02.jsp -->
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
    
        $('#btn1').click(() => {
            
        	/*
        	
        	   비동기 자바스크립트 통신
        	   1. 순수 자바스크립트 > XMLHttpRequest 구현
        	   2. jQuery Ajax 라이브러리(내부 XMLHttpRequest 사용)
        	   3. axio 라이브러리(내부 XMLHttpRequest 사용)        	   
        	   4. 순수 자바스크립트 > fetch API(XMLHttpRequest 다음 세대)
        	   
        	*/
        	
        	//ajax 객체 역할
        	//- 눈에 안보이는 통신 도구
        	const ajax = new XMLHttpRequest();
        	   
        	//$('.message').append(`<div>이벤트 발생: \${ajax.readyState}</div>`);
        	
        	/*
                readyState 값 변경 이벤트
                0: 객체 생성 직 후 > Uninitialized
                1: open 메서드 호출 > Loading
                2: send 메서드 호출 + 응답이 되기 전 > Loaded
                3: 데이터의 일부가 수신되는 상태 > Interactive
                4: 데이터 전부 받은 상태 > Completed
        	*/
        	ajax.onreadystatechange = function() {
            	
            	//$('.message').append(`<div>이벤트 발생: \${ajax.readyState}</div>`);
            	
            	//HTTP Status Code
            	//- 클라이언트와 서버간의 HTTP 사용한 통신에서 사용하는 상태코드
            	//- 200 > OK
                //- 4XX > 브라우저
                //- 5XX > 서버
            	
            	console.log(ajax.status); //200 OK
            	console.log(ajax.status); //500 
            	
            	if (ajax.readyState == 4 && ajax.status == 200) {
            		$('.message').append(`<div>데이터 수신 완료</div>`);
            		$('.message').append(`<div>레코드수: \${ajax.responseText}</div>`);
            		//console.log(ajax.responseText); //문자열
            	} else if (ajax.readyState == 4 && ajax.status != 200) {
            		$('.message').append(`<div>오류 발생</div>`);
            	}
            	
            }; 
            
            //HTTP Method
            //- GET, POST
            
            //<form method="GET" action="/ajax/ex02ok.do"> 역할
            ajax.open('GET', '/ajax/ex02ok.do'); //연결(X), 설정만(O)
            
            //btn1.submit() 역할
            //ajax 객체가 서버에서 페이지(데이터)를 달라고 요청함.
            ajax.send();
        	
        });
    
    </script>
</body>
</html>






