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
	
	<form action="/brd/insert" method="post" enctype="multipart/form-data"> 
	제목 : <input type="text" name="title"> <br>
	작성자 : <input type="text" name="writer" value="${ses.id}" readonly="readonly"> <br>
	내용 : <textarea rows="3" cols="30" name="content"></textarea> <br>
	이미지파일 : <input type="file" name="image_file" accept="image/png, image/jpg, image/jpeg, image/gif"> <br>
	<button type="submit"> 작성완료 </button> <br>
	</form>
	<a href="/index.jsp"><button> 돌아가기 </button></a>
</body>
</html>