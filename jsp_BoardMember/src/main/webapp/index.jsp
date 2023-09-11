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
	<h1> Index Page </h1>
	
	<form action="/mem/login" method="post">
		ID : <input type="text" name="id">
		PW : <input type="text" name="pwd">
		<button type="submit"> 로그인 </button>
	</form>
	<br>
	<hr>
	
	<div>
	<c:if test="${ses.id ne null }">
		${ses.id } login 하였습니다. <br>
		계정생성일 : ${ses.regdate } <br>		
		마지막접속 : ${ses.lastlogin } <br>
		<a href="/mem/modify"><button> 회원정보 수정 </button></a>
		<a href="/mem/list"><button> 회원정보 리스트 </button></a>
		<a href="/mem/logout"><button> 로그아웃 </button></a>
		<a href="/brd/register"><button> 게시판 글쓰기 </button></a>
	</c:if>
	</div>
	
		<a href="/mem/join"><button> 회원가입 </button></a> 
		
		<br>
	
		<a href="/brd/pageList"><button> 게시판 리스트 </button></a>
		
		<script type="text/javascript">
		const msg_login = `<c:out value="${msg_login}" />`;
		console.log(msg_login);
		if(msg_login === '0'){
			alert("로그인 정보가 일치하지 않습니다.");
		}
		</script>

</body>
</html>