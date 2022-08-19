<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	#boardDetailTable{width: 800px; margin: auto; border-collapse: collapse; border-left: hidden; border-right: hidden;}
	#boardDetailTable tr td{padding: 5px;}
	.replyTable{margin: auto; width: 500px;} 
	
</style>
</head>
<body>

	<c:import url="../common/menubar.jsp"/>

	<h1 align="center">${ board.boardId }번 글 상세보기</h1>

	<h3 align="center">총 게시글 갯수 : ${pi.listCount }</h3>
	

	<form>
		<table>
			
			<!-- 번호 제목 작성자 작성날짜 내용 첨부파일 -->
		
		
		</table>
	</form>


	<!-- 시작페이지,목록보기 이동버튼 -->
		
		
	<!-- 댓글창 -->
		
		
	<script>
	
	
	</script>
	

</body>
</html>