<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>prac</title>

<style type="text/css">
	#boardDetailTable{ width:800px; margin:auto; border-collapse:collapse; border-left:hidden; border-right:hidden;}
	#boardDetailTable tr td{ padding: 5px;}
	.replyTable{margin: auto; width:500px;}
</style>

</head>
<body>
	<c:import url="../common/menubar.jsp"/>
	
	<h1 align="center">${board.boardId }글의 상세보기</h1>
	<form action="bupView.bo" method="post">
		<table border="1" id="boardDetailTable">
			<tr>
				<th>번호</th>
				<td>
					${board.boardId }
					<input type="hidden" value="${ board.boardId }" name="boardId">
					<input type="hidden" value="${ page }" name="page">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					${ board.boardTitle }
					<input type="hidden" name="boardTitle" value="${ board.boardTitle }">
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					${ board.nickName }
					<input type="hidden" name="nickName" value="${ board.nickName }">
				</td>
			</tr>
			<tr>
				<th>작성날짜</th>
				<td>
					${ board.boardCreateDate }
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<% pageContext.setAttribute("newLineChar","\r\n"); %>
				<td>
					${ fn:replace(board.boardContent, newLineChar, "<br>") }
					<input type="hidden" value="${ board.boardContent }" name="boardContent">
				</td>
			</tr>
			
			<c:if test="${ !empty board.originalFileName }">
				<tr>
					<th>첨부파일</th>
					<td>
						<a href="${pageContext.servletContext.contextPath }/resources/buploadFiles/${board.renameFileName}" download="${ board.originalFileName }">${ board.originalFileName }</a>
						<input type="hidden" name="originalFileName" value="${board.originalFileName }">
						<input type="hidden" name="renameFileName" value="${board.renameFileName }">
					</td>		
				</tr>
			</c:if>
			
			
		</table>
	</form>
	
	<p align="center">
		<button onclick="location.href='home.do'">시작 페이지 이동</button>
		<button onclick="location.href='${ blist }'">목록 보기로 이동</button>
	</p>



</body>
</html>