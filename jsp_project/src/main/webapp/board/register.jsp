<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> 게시글 작성 페이지 </h1>
	
	<form action="/brd/insert" method="post"> 
	제목 : <input type="text" name="title"> <br>
	작성자 : <input type="text" name="writer"> <br>
	내용 : <textarea rows="3" cols="30" name="content"></textarea> <br>
	<button type="submit"> 작성완료 </button> <br>
	</form>
	<a href="index.jsp"><button> 돌아가기 </button></a>
</body>
</html>