<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
 <title>kuzuro 게시판</title>
</head>
<body>

<div id="root">
 <header>
  <h1>kuzuro 게시판</h1>
 </header>

<hr />
 
 <nav>
  처음화면 - 글쓰기 - 로그인
 </nav>

<hr />

 <section id="container">
 
  <form role="form" method="post" autocomplete="off">
  
   <p>
    <label for="bno">글 번호</label><input type="text" id="bno" name="bno" value="${read.bno}" readonly="readonly" />
   </p>
   
   <p>
    <label for="title">글 제목</label><input type="text" id="title" name="title" value="${read.title}"  />
   </p>
   <p>
    <label for="content">글 내용</label><textarea id="content" name="content" >${read.content}</textarea>
   </p>
   <p>
    <label for="writer">작성자</label><input type="text" id="writer" name="writer" value="${read.writer}" readonly="readonly" /><br />
    <label>작성 날짜</label> <span><fmt:formatDate value="${read.regDate}" pattern="yyyy-MM-dd" /></span>
   </p>
   <p>
    
   </p>  
  </form>

 </section>

<hr />

 <footer>
  <p>만든이 : kuzuro</p>  
 </footer>

</div>

</body>
</html>