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
	
	<h1 align="center">${board.boradId }번 글의 상세보기</h1>
	<form action="bupview.bo" method="post">
		<table>
			<tr>
				<th>번호</th>
				<td>
					${ board.boardId }
					<input type="hidden" value="${ board.boardId }" name="boardId">
					<input type="hidden" value="${ page }" name="page">
				</td>
			</tr>
				<th>제목</th>
				<td>
					${board.boardTitle}
					<input type="hidden" value="${ board.boardTitle }" name="boardTitle">
				</td>			
			<tr>
				<th>작성자</th>
				<td>
					${ board.nickName }
					<input type="hidden" value="${ board.nickName }" name="nickName">
				</td>
			</tr>
			<tr>
				<th>작성날짜</th>
				<td>
					${ board.boardCreateDate }
					<input type="hidden" value="${ board.boardCreateDate }" name="boardCreateDate">
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<% pageContext.setAttribute("newLineChar", "\r\n"); %>
				<td>
					${ fn:replace(board.boardContent, newLineChar, "<br>") }
					<input type="hidden" value="${ board.boardContent }" name="boardContent">
				</td>
			</tr>
			
			<c:if test="${ !empty originalFileName }">
				<tr>
					<th>첨부파일</th>
					<td>
						<a href="${pageContext.servletContext.contextPath }/resoures/buploadFiles/${board.renameFileName}" download="${board.originalFileName }">${board.originalFileName}</a>
						<input type="hidden" value="${ board.originalFileName }" name="originalFileName">
						<input type="hidden" value="${ board.renameFileName }" name="board.renameFileName">
					</td>
				</tr>
			</c:if>
			
			<c:url var="bdelete" value="bdelete.bo">	<!-- c:url 변수선언함. 그리고 아래 button태그에서 끌어다 씀 -->
				 <c:param name="bId" value="${ board.boardId }"/>	<!-- 삭제버튼에 필요한 게시판번호 데이터보내기 -->
				 <c:param name="renameFileName" value="${ board.renameFileName }"/>
			</c:url>
			<c:url var="blist" value="blist.bo">  <!-- 게시판목록으로 가는 url설정 -->
				<c:param name="page" value="${ page }"/> <!-- 보던 게시판으로 돌아가기 위해 page번호 필요 for pagination -->
			</c:url>	
			
			<c:if test="${ loginUser.id eq board.boardWriter }">
				<tr>
					<td colspan="2" align="center">
						<button type="button" onclick="location.href=">수정하기</button>
						<button type="button" onclick="location.href='${bdelete}'">삭제하기</button>
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