<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
 <title> 게시판</title>
</head>
<body>

<div id="root">
 	<header>
			<%@include file="../include/header.jsp" %>
		</header>
		<hr />
		<%@include file="../include/nav.jsp" %>
		<hr />

 <section id="container">
 	<c:if test="${msg ne 'login_error' }">
  <form role="form" method="post" autocomplete="off">
   <p>
    <label for="title">글 제목</label><input type="text" id="title" name="title" />
   </p>
   <p>
    <label for="content">글 내용</label><textarea id="content" name="content"></textarea>
   </p>
   <p>
    <label for="writer">작성자</label><input type="text" id="writer" name="writer" value="${member.userName }" readonly="readonly"/>
   </p>
   <p>
    <button type="submit">작성</button>
   </p>  
  </form>
</c:if>

<c:if test="${msg eq 'login_error' }">
<p>로그인해야 작성하실수 있습니다</p>
<p><a href="/geonsu">홈으로</a></p>
</c:if>
 </section>

<hr />

	<footer>
			<%@include file="../include/footer.jsp" %>
		</footer>

</div>

</body>
</html>