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
    <!-- customlogin.jsp -->
    <%@include file="/WEB-INF/views/inc/header.jsp" %>
    
    <h2>Custom Login Page</h2>
    
    <form method="POST" action="/java/login">
    <table class="vertical content">
        <tr>
            <th>아이디</th>
            <td><input type="text" name="username" required></td>
        </tr>
        <tr>
            <th>암호</th>
            <td><input type="password" name="password" required></td>
        </tr>
    </table>
    <div>
        <button class="in">로그인</button>
    </div>
    
    <!-- 
    <input type="hidden" name="_csrf" value="d612a570-6559-4710-a489-ba3b7d4cde07"> 
    -->
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>    
 
    <script src="https://code.jquery.com/jquery-4.0.0.js"></script>    
    <script src="https://bit.ly/4cMuheh"></script>
    <script>
    
    </script>
</body>
</html>






