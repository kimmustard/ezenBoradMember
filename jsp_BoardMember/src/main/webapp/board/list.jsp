<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
		<h1> 게시글 목록 </h1>
		<table border="1">
		
		<tr>
		<th>글번호</th>
		<th>글제목</th>
		<th>작성자</th>
		<th>내용</th>
		</tr>
	
		
		<c:forEach items="${list}" var="bvo">
		<tr>
			<td><a href="/brd/detail?bno=${bvo.bno }">${bvo.bno }</a></td>
			<td><a href="/brd/detail?bno=${bvo.bno }">${bvo.title }</a></td>
			<td>${bvo.writer }</td>
			<td>${bvo.content }</td>
		</tr>
		
		
		</c:forEach>
		
		
		</table>
		
		<a href="/brd/register"><button type="button">글쓰기</button></a>
		<a href="/index.jsp"><button type="button">메인으로</button></a>

	
</body>
</html>