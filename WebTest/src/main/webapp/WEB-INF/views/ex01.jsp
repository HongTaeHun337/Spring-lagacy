<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<!-- ex01.jsp -->
    <h1>ex01.jsp</h1>
    
    <div>count: ${count}</div>
    <div>count: ${pageContextScope.count }</div>
    <div>count: ${requestScope.count}</div>
    <div>count: ${sessionScope.count}</div>
    <div>count: ${applicationScope.count}</div>

</body>
</html>