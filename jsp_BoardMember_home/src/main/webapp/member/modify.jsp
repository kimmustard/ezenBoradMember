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

	<h1> 회원 수정페이지</h1>
	
	<form action="/mem/update" method="post">
	ID : <input type="text" name="id" value="${ses.id }" readonly="readonly"> <br>
	PW : <input type="text" name="pwd" value="${ses.pwd }"> <br>
	Email : <input type="text" name="email" value="${ses.email }"> <br>
	age : <input type="text" name="age" value="${ses.age }"> <br>
	<button type="submit"> 수정완료 </button> <br>
	</form>
	
	<a href="/mem/remove"> <button type="button"> 회원탈퇴 </button> </a> <br>
 	<a href="/index.jsp"> <button> 돌아가기 </button> </a>
</body>
</html>