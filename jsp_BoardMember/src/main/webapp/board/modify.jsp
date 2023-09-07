<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1> 수정 페이지 </h1>
	
	<form action="/brd/edit" method="post">
		<tr>
	<table border="1">
			<th>글번호</th>
			<td><input type="text" name="bno" readonly="readonly" value="${bvo.bno }"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td> <input type="text" name="title" value="${bvo.title }"></td>
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
			<th>내용</th>
			<td> <textarea rows="3" cols="30" name="content" >${bvo.content }</textarea></td>
		</tr>
		
		   
		</table>
		<button type="submit">확인</button>
	</form>
		<a href="/brd/list"><button type="button">취소</button></a>
</body>
</html>