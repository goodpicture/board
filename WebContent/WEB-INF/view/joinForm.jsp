<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.error {
	color: red;
	display: none;
	font-size: 12px;
	text-align: center;
}
.error2{
	color: red;
}

form {
	width: 300px;
	border: 1px solid black;
}

label {
	padding-left: 10px;
	width: 80px;
	float: left;
}

p:LAST-CHILD {
	text-align: center;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script>
	$(function() {
		$("form[name='f1']").submit(function() {
			// 다 숨김
			$(".error").css("display","none");
			
			if ($("input[name='id']").val() == "") {
				var $next = $("input[name='id']").next(".error");
				$next.css("display", "block");
				return false;
			}
			if ($("input[name='name']").val() == "") {
				var $next = $("input[name='name']").next(".error");
				$next.css("display", "block");
				return false;
			}
			if ($("input[name='password']").val() == "") {
				var $next = $("input[name='password']").next(".error");
				$next.css("display", "block");
				return false;
			}
			if ($("input[name='confirmPassword']").val() == "") {
				var $next = $("input[name='confirmPassword']").next(".error");
				$next.css("display", "block");
				return false;
			}
			
			//암호와 확인이 틀리면 서버전송 막음
			var passVal = $("input[name='password']").val();
			var confirmVal = $("input[name='confirmPassword']").val();
			if(passVal != confirmVal){
					var $next = $("input[name='confirmPassword']").nextAll(".error").eq(1);
					$next.css("display", "block");
					return false;
				}

		});
	});
</script>
</head>
<body>
	<form action="join.do" method="post" name="f1">
		<p>
			<label>아이디 :</label><input type="text" name="id"> <span
				class="error">ID를 입력하세요.</span>
		</p>

		<p>
			<label>이름 :</label> <input type="text" name="name"> <span
				class="error">이름을 입력하세요.</span>
		</p>
		<p>
			<label>암호 :</label> <input type="password" name="password"> <span
				class="error">암호를 입력하세요.</span>
		</p>
		<p>
			<label>확인 :</label> <input type="password" name="confirmPassword">
			<span class="error">암호 확인란을 입력하세요.</span> 
			<span class="error">암호가 일치하지 않습니다.</span>
		</p>
		<p>
			<input type="submit" value="가입">
		</p>
	</form>
	<c:if test="${duplicateId == true }">
		<p class="error2">이미 사용중인 아이디 입니다.</p>
	</c:if>
</body>
</html>