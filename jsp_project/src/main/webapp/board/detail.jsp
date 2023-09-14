<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1> 게시글 상세정보 페이지 </h1>
	
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>${bvo.bno }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td>${bvo.title }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${bvo.content }</td>
		</tr>
		<tr>
			<th>게시날짜</th>
			<td>${bvo.regdate }</td>
		</tr>
		<tr>
			<th>수정날짜</th>
			<td>${bvo.moddate }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${bvo.views }</td>
		</tr>
	</table>
		<br>
		
		<a href="/brd/modify?bno=${bvo.bno}"><button type="button">수정</button></a>
		<a href="/brd/remove?bno=${bvo.bno}"><button type="button">삭제</button></a>
		<a href="/brd/pageList"><button type="button"> 리스트로 </button></a>
</body>
</html>