<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<script src='https://code.jquery.com/jquery-3.3.1.min.js'></script>
 <title>회원가입</title> 
 <script type="text/javascript">
 
 $(function() {
	 
	 $(".idCheck").click(function() {
		 var query = {userId : $("#userId").val()};
		 
		 $.ajax({
			 url : "idCheck",
			 type : "post",
			 data : query,
			 success : function(data) {
				 console.log("아디확인값"+data);
				if(data ==1){
					$(".result .msg").text("사용불가");
					$(".result .msg").css("color","red");
					$("#submit").attr("disabled","disabled");
				}else{
					$(".result .msg").text("사용가능");
					$(".result .msg").css("color","blue");
					$("#submit").removeAttr("disabled");
				}
				
			},
			error:function(data){
				alert(data+"에러입니다");
			}
			 
		 });//ajax
		
	});//idCheck -function
	
	$("#userId").keyup(function() {
		$(".result .msg").text("아이디를 확인");
		$(".result .msg").css("color","red");
		$("#submit").attr("disabled","disabled");
	});//keyup
	
});
 </script>
</head>
<body>

<form role="form" method="post" autocomplete="off">
 <p>
  <label for="userId">아이디</label>
  <input type="text" id="userId" name="userId" />
  <button class="idCheck" type="button">아이디확인</button>
 </p> 
 
 <p class="result">
 <span class="msg">아이디를 확인해주세요</span>
 </p>
  
 <p>
  <label for="userPass">패스워드</label>
  <input type="password" id="userPass" name="userPass" />
 </p>
 <p>
  <label for="userName">닉네임</label>
  <input type="text" id="userName" name="userName" />
 </p>
 <p>
   <button type="submit" id="submit" disabled="disabled">가입</button>  
 </p>
 <p>
  <a href="/geonsu">처음으로</a>
 </p>
 
</form>
</body>
</html>