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

	<h1 align="center">${board.boardId }번의 게시물 상세보기</h1>

	<form action="bupView.bo" method="post">
		<table border="1" id="boardDetailTable">
			<tr>
				<th>번호</th>
				<td>
					<input type="hidden" name="boardId" value="${ board.boardId  }">
					<input type="hidden" name="page" value="${ page }">
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
				
				</td>
			</tr>
			<tr>
				<th>작성날짜</th>
				<td>
					${ board.boardCreateDate}
					
				</td>	
			</tr>
			<tr>
				<th>내용</th>
				<td>
					${board.boardContent }
					<input type="hidden" name="boardContent" value="${ board.boardContent }">
				</td>
			</tr>
			
			<!-- 제목,작성자,작성날짜, 내용, 첨부파일,  -->
		</table>
	</form>
	
	<!-- url 변수선언 -->
	
	<!-- 로그인유저 수정삭제 버튼 보이기 -->
	
	<!-- 시작페이지,목록보기 이동버튼 -->
	
	<!-- 댓글창 -->
	
	
	
	<script>
		// 댓글등록

		
		// 등록한 댓글 읽어오기
					
					// 계속 이어붙기 때문에 공백 넣어줘야함
					
					// 변수선언
		
		// 다른 사람이 쓴 댓글도 볼 수 있게 5초마다 읽어오도록
		
	
	</script>
	
</body>
</html>

