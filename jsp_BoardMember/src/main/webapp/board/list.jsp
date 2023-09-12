<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
</head>
<body>
	
		<h1> 게시글 목록 </h1>
		<!-- search 구역 -->
		<div>
			<form action="/brd/pageList" method="get">
				<div>
				<c:set value="${ph.pgvo.type }" var="typed"></c:set>
				
					<select name="type">
						<option ${typed == null? 'selected':'' }>Choose...</option>
						<option value="t" ${typed eq 't'? 'selected':'' }>title</option>
						<option value="w" ${typed eq 'w'? 'selected':'' }>writer</option>
						<option value="c" ${typed eq 'c'? 'selected':'' }>content</option>
						<option value="tw" ${typed eq 'tw'? 'selected':'' }>title+writer</option>
						<option value="tc" ${typed eq 'tc'? 'selected':'' }>title+content</option>
						<option value="wc" ${typed eq 'wc'? 'selected':'' }>writer+content</option>
						<option value="twc" ${typed eq 'twc'? 'selected':'' }>title+writer+content</option>
					</select>
					<input type="text" name="keyword" value="${ph.pgvo.keyword }" placeholder="Search...">
					<input type="hidden" name="pageNo" value="${ph.pgvo.pageNo }">
					<input type="hidden" name="qty" value="${ph.pgvo.qty }">
					${ph.totalCount }
					<button type="submit"> Search </button>
				</div>
			</form>
		</div>
		
		<br>
			
		<table class="table table-hover">
		
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
			<td>${bvo.regdate }</td>
		</tr>
		
		
		</c:forEach>
		
		
		</table>
		
		<!-- 페이지네이션 표시 구역 -->
		<div>
		
		<!-- prev -->
		<c:if test="${ph.prev }">
		<a href="/brd/pageList?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"> ◁ | </a>
		</c:if>
		<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
			<a href="/brd/pageList?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i } </a>
			
		</c:forEach>
		
		<!-- next  -->
		<c:if test="${ph.next }">
		<a href="/brd/pageList?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"> | ▷ </a>
		</c:if>
		
		</div>
		
		<a href="/brd/register"><button type="button">글쓰기</button></a>
		<a href="/index.jsp"><button type="button">메인으로</button></a>

	
</body>
</html>