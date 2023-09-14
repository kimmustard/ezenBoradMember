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

	<h1> 게시글 수정 페이지 </h1>
	
	<form action="/brd/edit" method="post">
	<table border="1">
	
	<tr>
		<th>글번호</th>
		<td> <input type="text" name="bno" value="${bvo.bno }" readonly="readonly"></td>
	</tr>	
	<tr>
		<th>제목</th>
		<td> <input type="text" name="title" value="${bvo.title }"></td>
	</tr>		
	<tr>
		<th>내용</th>
		<td> <input type="text" name="content" value="${bvo.content }"></td>
	</tr>
	<tr>
		<th>게시날짜</th>
		<td>${bvo.regdate }</td>
	</tr>
	<tr>
		<th>수정날짜</th>
		<td>${bvo.moddate }</td>
	</tr>
	
	
	
	</table>
		<button type="submit"> 수정완료 </button>
	</form>
	<a href="/board/list.jsp"> <button type="button"> 리스트 </button></a>
	
</body>
</html>