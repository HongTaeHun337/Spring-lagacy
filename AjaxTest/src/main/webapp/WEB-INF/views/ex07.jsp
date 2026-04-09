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
    <!-- ex07.jsp -->
    <h1>Ajax <small>전송 데이터 & 수신 데이터</small></h1>
    
    <h2>수신(응답) 데이터</h2>
    
    <h3>Text <small>ajax.responseText, 단일값</small></h3>
    <div>
        <input type="button" value="확인" id="btn1">
        <div class="message" id="result1"></div>
    </div>
    
    <h3>Text <small>ajax.responseText, 다중값</small></h3>
    <div>
        <input type="button" value="확인" id="btn2">
        <div class="message" id="result2"></div>
    </div>
    
    <script src="https://code.jquery.com/jquery-4.0.0.js"></script>   
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        $('#btn1').click(()=>{
            
        	$.ajax({
        		type: 'GET',
        		url: '/ajax/ex07ok.do',
        		//data: 전송하는 데이터,
        		//dataType: 수신하는 데이터의 형식(text, xml, json)
        		dataType: 'text',
        		success: function(result) {
        			$('#result1').text(result);
        		},
        		error: function(a,b,c) {
        			console.log(a,b,c);
        		}
        	});
        	
        });
        
        
        $('#btn2').click(()=>{
            
            $.ajax({
                type: 'GET',
                url: '/ajax/ex07_2ok.do',
                dataType: 'json',
                success: function(result) {
                    //console.log(typeof result);
                    //console.log(result.length);
                    //console.log(result[0]);
                    
                    result.forEach(obj => {
                    	
                    	$('#result2').append(`
                    		<div>\${obj.name}(\${obj.id},\${obj.pw})</div>		
                    	`);
                    	
                    });
                    
                },
                error: function(a,b,c) {
                    console.log(a,b,c);
                }
            });
            
        });
        
    
    </script>
</body>
</html>







