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
    <!-- ex05.jsp -->
    <h1>Ajax</h1>
    
    <div>
        <input type="text" id="seq" name="seq" value="1">
        <input type="button" value="클릭" id="btn1">
    </div>
     
    <div id="result" class="message"></div>
    
 
    <script src="https://code.jquery.com/jquery-4.0.0.js"></script>    
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        
        /* $('#btn1').click(() => {
        	
        	//$('CSS').aaa();
        	//$(document).aaa();
        	
        	$.ajax({
        		type: 'GET',                      //ajax.open() 설정
        		url: '/ajax/ex05ok.do',           //ajax.open() 설정
        		data: 'seq=' + $('#seq').val(),   //전송할 데이터
        		success: function(result) {       //콜백 > result(ajax.responseText)
        			$('#result').text(result);
        		},
        		error: function(a,b,c) {
        			console.log(a,b,c);
        		}
        	}); //ajax.send() 호출
            
        }); */
        
        
        $('#btn1').click(() => {
                        
            $.ajax({
                type: 'POST',                     //ajax.open('POST') 설정
                url: '/ajax/ex05ok.do',           //ajax.open() 설정
                data: 'seq=' + $('#seq').val(),   //전송할 데이터
                success: function(result) {       //콜백 > result(ajax.responseText)
                    $('#result').text(result);
                },
                error: function(a,b,c) {
                    console.log(a,b,c);
                }
            }); //ajax.send() 호출
            
        });
    
    </script>
</body>
</html>






