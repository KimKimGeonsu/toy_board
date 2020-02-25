<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
 <title>정보수정</title> 
</head>
<body>

<form role="form" method="post" autocomplete="off">
<p>
<label for="userName">닉네임</label>
<input id="userName" name="userName" value="${member.userName }">
</p>

<p>
<label for="userPass">새로운 패스워드</label>
<input type="password" id="userPass" name="userPass">
</p>
 
<p>
<button type="submit">수정하기</button>
</p>
 
<p>
<a href="/geonsu">처음으로</a>
</p>
</form>
</body>
</html>