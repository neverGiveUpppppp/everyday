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
	
	<h1 align="center">${board.boardId }번 글의 상세보기</h1>
	
	
	<form action="" method="post">
		<table>
			<tr>
				<th>번호</th>
				<td>
					<input type="hidden" name="boardId" value="${ board.boardId }">
					<input type="hidden" name="page" value="${ page }">
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
					${ board.board CreateDate }
				</td>
			</tr>		
			<tr>
				<th>내용</th>
				<% pageContext.setAttribute("newLineChar", "\r\n"); %>
				<td>
					${fn:replace(board.boardContent, newLineChar, "<br>" }
					<input type="hidden" value="${ board.boardContent }" name="boardContent">
				</td>
				
			</tr>	
			
			<c:if test="${board.originalFileName != null }">	
				<tr>
					<th>첨부파일</th>
					<td>
						<a href="${pageContext.servletContext.contextPath }/resources/buploadFiles/${board.renameFileName}" download="${board.originalFileName }"> ${board.originalFileName }</a>
						<input type="hidden" name="renameFileName" value="${ board.renameFileName }">
						<input type="hidden" name="originalFileName" vlaue="${ board.originalFileName }">
					</td>
				</tr>
			</c:if>
			
			<!-- url변수선언 -->
			<c:url var="bdelete" value="bdelete.bo">
				<c:param name="bId" value="${ board.boardId }"/>
				<c:param name="renameFileName" value="${ board.renameFileName }"/>
			</c:url>
			<c:url var="blist" value="blist.bo">
				<c:param name="page" value="${ page }"/>
			</c:url>
			
			<!-- 로그인한 유저가 작성자라면, 수정삭제버튼 보이기 --> 
			<c:if test="${ loginUser.id eq ${ board.boardWriter }">
				<tr>
					<td colspan="2" align="center">
						<button type="button" onclick="locaiont.href='${ bupdate }'">수정하기</button>
						<button type="button" onclick="location.href='${ bdelete }'">삭제하기</button>
					</td>	
				</tr>
			</c:if>
			
			
		</table>
	</form>
	
	<p align="center">
		<button onclick="location.href='home.do'">시작화면으로</button>
		<button onclick="location.href='${blist}'">게시판 목록 보기</button>
	</p>
	<br>
	<br>
	
	
	<table class="replyTable">
		<tr>
			<td><textarea cols="55" rows="3" id="replyContent"></textarea></td>
			<td><button id="rSubmit">등록하기</button></td>
		</tr>
	</table>	
	<table class="replyTable" id="rtb">
		<thead>
			<tr>
				<td colspan="2"><b id="rCount"></b></td>
			</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>
	
	<script>
		
		// 댓글 등록 : jQuery ajax
		$('#rSumit').click(funtion(){
			var rContent = ${'#replyContent'}.val();
			var refBId = ${board.boardId };
			
			$.ajax({
				url:'addreplybo',
				data:{replyContent:rContent, boardId:reBId },
				success:function(data){
					console.log(data);
					if(data == 'success'){
						$('#replyContent').val(' ');
					}
				},
				error{
					console.log(data);
				}
			});
		});
		
	
		// 등록한 댓글 읽어오기
		function getReplyList() {
				
			$.ajax({
				url:'rList.bo',
				data:{bId,${board.boardId}},
				success:function(data){
					console.log(data);
					
				},
				error:function(data){
					console.log(data);
				}
			
			
			})
				
				
				
			
		}
	
	
	</script>
	
	

</body>
</html>

