<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<title>게시판</title>

<script type="text/javascript">
$(function() {
	var formObj = $("form[role='form']");

	//수정
	$("#modify_btn").click(function(){
		formObj.attr("action","/geonsu/board/modify");
		formObj.attr("method","get");
		formObj.submit();
	});

	//삭제
	$("#delete_btn").click(function() {
		if(confirm("정말삭제하시겠어요?")){
		formObj.attr("action","/geonsu/board/delete");
		formObj.attr("method","post");
		formObj.submit();
		}else{
			return false;
		}
	});
	
});



</script>

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

			<form role="form" method="post" autocomplete="off">

				<p>
					<label for="bno">글 번호</label><input type="text" id="bno" name="bno"
						value="${read.bno}" readonly="readonly" />
				</p>
			</form>
			<p>
				<label for="title">글 제목</label><input type="text" id="title"
					name="title" value="${read.title}" readonly="readonly" />
			</p>
			<p>
				<label for="content">글 내용</label>
				<textarea id="content" name="content" readonly="readonly">${read.content}</textarea>
			</p>
			<p>
				<label for="writer">작성자</label><input type="text" id="writer"
					name="writer" value="${read.writer}" readonly="readonly" /><br />
				<label>작성 날짜</label> <span><fmt:formatDate
						value="${read.regDate}" pattern="yyyy-MM-dd" /></span>
			</p>
			<p>
				<button id="modify_btn">수정</button>
				<button id="delete_btn">삭제</button>
			</p>

		</section>

		<hr />

	<footer>
			<%@include file="../include/footer.jsp" %>
		</footer>

	</div>

</body>
</html>