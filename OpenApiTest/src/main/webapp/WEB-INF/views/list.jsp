<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="http://bit.ly/3WJ5ilK" />
    <style>
        table td:nth-child(1) { width: 120px; text-align: center; }
        table td:nth-child(1) img { width: 110px; }
    </style>
</head>
<body>
    <!-- list.jsp -->
    <h1 class="main">네이버 도서 검색 <small>Open API</small></h1>
    
    <form method="GET" action="/api/search.do">
    <div class="message">
        <div class="group" style="margin-left: 20px;">
            <label>검색어</label>
            <input type="text" name="word" class="long" required value="${word}">
            <input type="submit" value="검색하기">
            <input type="button" value="초기화" 
                onclick="location.href='/api/list.do';">            
        </div>
    </div>
    <input type="hidden" name="page" value="${page}">
    </form>
    
    <c:if test="${not empty word}">
    <div>'${word}'(으)로 검색한 결과입니다.</div>
    </c:if>
    
    <table>
        <c:forEach items="${list}" var="dto">
        <tr>
            <td><img src="${dto.image}"></td>
            <td>${dto.title}</td>
            <td>${dto.author}</td>
            <td>${dto.discount}원</td>
            <td>${dto.publisher}</td>
        </tr>
        </c:forEach>
    </table>
    
    <c:if test="${not empty word}">
    <div>
        <input type="button" value="이전 책보기" onclick="move(-1);">
        <span>${(page + 4) / 5}페이지</span>
        <input type="button" value="다음 책보기" onclick="move(1);">
    </div>
    </c:if>
    
    <script src="https://code.jquery.com/jquery-4.0.0.js"></script>
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
        function move(n) {
        	
        	if (n > 0) {
        		//다음 책보기
        		//1 > 6
        		
        		//if (현재페이지 > 최대 검색 개수) return
        		
        		$('input[name=page]').val( parseInt($('input[name=page]').val()) + 5);        		
        		
        	} else {
        		//이전 책보기
        		
        		if (parseInt($('input[name=page]').val()) == 1) {
        			return;
        		}
        		
        		$('input[name=page]').val( parseInt($('input[name=page]').val()) - 5);                
        	}
        	
        	$('form').submit();
        	
        }
    
    </script>
</body>
</html>



