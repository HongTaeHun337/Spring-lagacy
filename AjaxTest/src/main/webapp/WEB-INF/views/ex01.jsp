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
    <!-- ex01.jsp -->
    <h1>Ajax</h1>
    
    <div>
        <input type="button" value="클릭" id="btn1">
    </div>
     
    <div id="result" class="message">${count}</div>
    
    <div>
        <input type="text">
    </div>
 
    <script src="https://code.jquery.com/jquery-4.0.0.js"></script>    
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        /*
            
            브라우저 입장 > 아래의 3가지 행동 전부 동일하다.
            
            A페이지 > (F5)   > A페이지: 새로고침
            A페이지 > (링크) > B페이지: 이동하기
            A페이지 > (submit) > B페이지: 전송하기
        
        */
        $('#btn1').click(() => {
        	
        	//데이터를 달라는 요청을 '브라우저'가 함.
            window.location.href = '/ajax/ex01ok.do';
        	
        });
    
    </script>
</body>
</html>






