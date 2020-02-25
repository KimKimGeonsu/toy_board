<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- 부가적인 테마 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
<title>게시판</title>

<script type="text/javascript">
	$(function() {
		var formObj = $("form[role='form']");

		//수정
		$("#modify_btn").click(function() {
			formObj.attr("action", "/geonsu/board/modify");
			formObj.attr("method", "get");
			formObj.submit();
		});

		//삭제
		$("#delete_btn").click(function() {
			if (confirm("정말삭제하시겠어요?")) {
				formObj.attr("action", "/geonsu/board/delete");
				formObj.attr("method", "post");
				formObj.submit();
			} else {
				return false;
			}
		});

		//목록
		$("#list_btn")
				.click(
						function() {
							self.location = "/geonsu/board/listSearch?"
									+ "page=${scri.page}&perPageNum=${scri.perPageNum}"
									+ "&searchType=${scri.searchType}&keyword=${scri.keyword}";
						});

		//댓글작성
		var re_formObj = $(".replyForm form[role ='form']");

		$(".repSubmit").click(function() {
			re_formObj.attr("action", "replyWrite");
			re_formObj.submit();
		});

		//댓글수정
		$(".replyUpdate").click(
				function() {
					self.location = '/geonsu/board/replyUpdate?bno=${read.bno}'
							+ "&page=${scri.page}"
							+ "&perPageNum=${scri.perPageNum}"
							+ "&searchType=${scri.searchType}"
							+ "&keyword=${scri.keyword}" + "&rno="
							+ $(this).attr("data-rno");
				});

		//댓글삭제
		$(".replyDelete").click(
				function() {
					self.location = "replyDelete?bno=${read.bno}"
							+ "&page=${scri.page}"
							+ "&perPageNum=${scri.perPageNum}"
							+ "&searchType=${scri.searchType}"
							+ "&keyword=${scri.keyword}" + "&rno="
							+ $(this).attr("data-rno");
				});

	});
</script>

</head>
<body>

	<div class="container">
		<header>
			<%@include file="../include/header.jsp"%>
		</header>
		<nav>
		<%@include file="../include/nav.jsp"%>
		</nav>

		<section id="container">

			<form role="form" method="post" autocomplete="off">
				<input type="hidden" id="page" name="page" value="${scri.page}"
					readonly="readonly" /> <input type="hidden" id="perPageNum"
					name="perPageNum" value="${scri.perPageNum}" readonly="readonly" />
				<input type="hidden" id="searchType" name="searchType"
					value="${scri.searchType}" readonly="readonly" /> <input
					type="hidden" id="keyword" name="keyword" value="${scri.keyword}"
					readonly="readonly" />

				<div class="form-group">
					<label for="bno" class="control-label">글 번호</label>
					<input class="form-control" type="text" id="bno" name="bno" value="${read.bno}" readonly="readonly" />
				</div>
			</form>
			
			
			
			<div class="form-group">
				<label for="title" class="control-label">글 제목</label>
				<input class="form-control" type="text" id="title" name="title" value="${read.title}" readonly="readonly" />	
			</div>
			
			<div class="form-group">
				<label for="content" class="control-label">글 내용</label>
				<textarea class="form-control" id="content" name="content" readonly="readonly">${read.content}</textarea>
			</div>
			
			<div class="form-group">
				<label for="writer" class="control-label">작성자</label>
				<input class="form-control" type="text" id="writer" name="writer" value="${read.writer}" readonly="readonly" />
			</div>
			
			<div class="form-group">
				<label class="control-label">작성 날짜</label> 
				<span><fmt:formatDate value="${read.regDate}" pattern="yyyy-MM-dd" /></span>
			</div>
			
			<div class="form-group">
				<button id="list_btn" class="btn btn-primaty">목록</button>
				<button id="modify_btn" class="btn btn-warning">수정</button>
				<button id="delete_btn" class="btn btn-danger">삭제</button>
			</div>

		</section>

		<!-- 댓글 -->
		<div id="reply" >
			<ol class="replyList">
				<c:forEach items="${re_list }" var="re_list">
					<li>
						<p>
						<span class="glyphicon glyphicon-user"></span>
							${re_list.writer }
							(<fmt:formatDate value="${re_list.regDate }" pattern="yyyy-MM-dd" />)
						</p>
						
						<p class="bg-info">${re_list.content }</p>
						
						<div class="form-group">
							<button class="replyUpdate btn btn-warning btn-xs" data-rno="${re_list.rno }">수정</button>
							<button class="replyDelete btn btn-danger btn-xs" data-rno="${re_list.rno }">삭제</button>
						</div>
					</li>
				</c:forEach>
			</ol>

			<section class="replyForm">
				<form role="form" method="post" autocomplete="off" class="form-horizontal">
					<input type="hidden" id="bno" name="bno" value="${read.bno}" readonly="readonly" /> 
					<input type="hidden" id="page" name="page" value="${scri.page}" readonly="readonly" /> 
					<input type="hidden" id="perPageNum" name="perPageNum" value="${scri.perPageNum}" readonly="readonly" /> 
					<input type="hidden" id="searchType" name="searchType" value="${scri.searchType}" readonly="readonly" />
					<input type="hidden" id="keyword" name="keyword" value="${scri.keyword}" readonly="readonly" />

					<div class="form-group">
						<label for="writer" class="control-label">작성자</label>				
						<input class="form-control" type="text" id="writer" name="writer" class="form-control">
	
					</div>
					
					<div class="form-group">
						<label for="content" class="control-label">댓글내용</label>
						<textarea class="form-control" id="content" name="content" class="form-control"></textarea>
					</div>
					
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
						<button type="button" class="repSubmit btn btn-success">작성</button>
						</div>
					</div>
				</form>
			</section>


		</div>



		<footer>
			<%@include file="../include/footer.jsp"%>
		</footer>

	</div>

</body>
</html>