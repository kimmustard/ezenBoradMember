<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> 회원가입 페이지 </h1>
	
	<form action="/mem/register" method="post">
		
		아이디 : <input type="text" name="id"> <br>
		비밀번호 : <input type="text" name="pwd"> <br>
		이메일 : <input type="text" name="email"> <br>
		나이 : <input type="text" name="age"> <br>
		
		<button type="submit"> 회원가입 </button> <br>
	</form>
	<a href="/index.jsp"><button type="button"> 취소 </button></a>
</body>
</html>