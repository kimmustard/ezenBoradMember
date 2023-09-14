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
		<!-- serach 구역  -->
		<div>
			<form action="/brd/pageList" method="get">
				<div>
				<c:set value="${ph.pgvo.type }" var="typed"></c:set>
				
					<select name="type">
						<option ${typed == null ? 'selected':'' }>선택하세요</option>
						<option value="t" ${typed eq 't'? 'selected':'' }>제목</option>
						<option value="w" ${typed eq 'w'? 'selected':'' }>작성자</option>
						<option value="c" ${typed eq 'c'? 'selected':'' }>내용</option>
						<option value="tw" ${typed eq 'tw'? 'selected':'' }>제목+작성자</option>
						<option value="tc" ${typed eq 'tc'? 'selected':'' }>제목+내용</option>
						<option value="wc" ${typed eq 'wc'? 'selected':'' }>작성자+내용</option>
						<option value="twc" ${typed eq 'twc'? 'selected':'' }>제목+작성자+내용</option>
					</select>
					<input type="text" name="keyword" value="${ph.pgvo.keyword }" placeholder="검색옵션 선택">
					<input type="hidden" name="pageNo" value="${ph.pgvo.pageNo }">
					<input type="hidden" name="qty" value="${ph.pgvo.qty }">
					${ph.totalCount }
					<button type="submit"> 검색 </button>
				</div>
			</form>
		</div>
		
		
		
		
		<table border="1">
		
		<tr>
		<th>글번호</th>
		<th>글제목</th>
		<th>작성자</th>
		<th>내용</th>
		<th>조회수</th>
		</tr>
	
		
		<c:forEach items="${list}" var="bvo">
		<tr>
			<td><a href="/brd/detail?bno=${bvo.bno }">${bvo.bno }</a></td>
			<td><a href="/brd/detail?bno=${bvo.bno }">${bvo.title }</a></td>
			<td>${bvo.writer }</td>
			<td>${bvo.content }</td>
			<td>${bvo.views }</td>
		</tr>
		
		
		</c:forEach>
				
		</table>
		

		<!-- 페이지네이션 표시 구역 -->
		<div>
		
		<!-- prev -->
		<c:if test="${ph.prev }">
		<a href="/brd/pageList?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"> ◁ | </a>
		</c:if>
		<c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
		
			<a href="/brd/pageList?pageNo=${i }&qty=${ph.pgvo.qty }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i } </a>
			
		</c:forEach>
		
		<!-- next  -->
		<c:if test="${ph.next }">
		<a href="/brd/pageList?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty }&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}"> | ▷ </a>
		</c:if>
		
		</div>
		
		
		
		<a href="/brd/register"><button type="button">글쓰기</button></a>
		<a href="/index.jsp"><button type="button">메인으로</button></a>

	
</body>
</html>