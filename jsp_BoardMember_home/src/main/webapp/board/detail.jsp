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
		<h1> 게시글 상세 페이지 </h1>
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
			<th>게시날짜</th>
			<td>${bvo.regdate }</td>
		</tr>
		<tr>
			<th>수정날짜</th>
			<td>${bvo.moddate }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${bvo.content }</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${bvo.views }</td>
		</tr>
		
		
		   
		</table>
		
		<br>
		<a href="/brd/modify?bno=${bvo.bno}"><button type="button">게시글 수정</button></a>
		<a href="/brd/remove?bno=${bvo.bno}"><button type="button">게시글 삭제</button></a>
		
		<br>
		<hr>
		<h1> 댓글 목록 </h1>
		
		<hr>
		<br>
		
		<c:forEach items="${cvoList }" var="cvo">
		<table border="1">
			
		<tr>
			<th>작성자</th>
			<td>${cvo.writer }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${cvo.content }</td>
		</tr>
		<tr>
			<th>등록일</th>
			<td>${cvo.regdate }</td>
		</tr>
		
		</table>
		<button type="button"> 삭제 </button>
		<br>
		</c:forEach>
		
		<br>
		<form action="brd/comment" method="post">
		<input type="hidden" name="bcno" value="${bvo.bno }">
		<input type="hidden" name="writer" value="${ses.id }">
		댓글입력 : <input type="text" name="content"><br>
		<button type="submit"> 댓글 작성 </button>
		</form>
		
		<br>
		<br>
		
		
		<a href="/brd/pageList"><button type="button">리스트</button></a>
		
</body>
</html>