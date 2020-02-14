<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<title>게시판</title>
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
			<h2>글 목록</h2>

			<table>
				<tr>
					<th>글 번호</th>
					<th>글 제목</th>
					<th>작성자</th>
					<th>작성일자</th>
				</tr>
				
				<!-- 목록 시작 -->
				<c:forEach items="${list}" var="count"  varStatus="status">
					<tr>
						 <%-- <td>${fn:length(list)-status.count+1}</td> --%> 
						 <td>${count.bno}</td>
						<td><a href="read?bno=${count.bno }">
						${count.title}</a>
						</td>
						<td>${count.writer}</td>
						<td><fmt:formatDate value="${count.regDate}"
								pattern="yyyy-MM-dd" /></td>
					</tr>
				</c:forEach>
				<!-- 목록 끝 -->

			</table>
			<div>
			<ul>
			<c:if test="${pageMaker.prev}">
				<li><a href="list${pageMaker.makeQuery(pageMaker.startPage-1)}">이전</a></li>
			</c:if>			
			
			<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage }" var="idx">
			<li><a href="list${pageMaker.makeQuery(idx)}">${idx}</a></li>			
			</c:forEach>
			
			<c:if test="${pageMaker.next && pageMaker.endPage>0 }">			
				<li><a href="list${pageMaker.makeQuery(pageMaker.endPage +1)}">다음</a></li>			
			</c:if>
						
			</ul>
			</div>
			

		</section>

		<hr />

		<footer>
			<%@include file="../include/footer.jsp" %>
		</footer>

	</div>

</body>
</html>