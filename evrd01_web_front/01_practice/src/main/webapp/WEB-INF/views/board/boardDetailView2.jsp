<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PRAC</title>

<style type="text/css">
	#boardDetailTable{width:800px; margin:auto; border-collapse:collapse; border-left:hidden;}
	#boardDetailTable tr td{padding: 5px;}
	.replyTable{margin: auto; width: 500px;} 
</style>

</head>
<body>

	<c:import url="../common/menubar.jsp"/>
	
	<h1 align="center">${board.boardId }번의 글 상세보기</h1>
	
	<form action="bupView.bo" method="post">
		<table border="1" id="boardDetailTable">
			<tr>
				<th>번호</th>
				<td>
					${board.boardId }
					<input type="hidden" name="boardId" value="${board.boardId }">
					<input type="hidden" name="page" value="${ page }">
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					${ board.boardTitle }
					<input type="hidden" name="boardTitle" value="${board.boardTitle }">
				</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>
					${board.nickName }
					<input type="hidden" name="nickName" value="${ board.nickName }">
				</td>
			</tr>
			<tr>
				<th>작성날짜</th>
				<td>
					${board.boardCreateDate }
				</td>
			</tr>
			
			<tr>
				<th>내용</th>
				
				<% pageContext.setAttribute("newLineChar", "\r\n"); %>
				<td>
					${fn:replace(board.boardContent,newLineChar, "<br>") }
					<input type="hidden" name="boardContent" value="${ boardContent }">
				</td>
			</tr>
			
			
			<c:if test="${ !empty board.originalFileName }">
			<tr>
				<th>첨부파일</th>
				<td>
					<a href="${pageContext.servletContext.contextPath }/resources/buploadFiles/${ board.renameFileName }" download="${board.originalFilename }">${board.originalFileName }</a>
					<input type="hidden" name="originalFileName" value="${ board.originalFileName }">
					<input type="hidden" name="renameFileName" value="${board.renameFileName }">
				</td>
			</tr>
			</c:if>
			

			<!-- url 변수선언 -->
			<c:url var="bdelete" value="bdelete.bo">
				<c:param name="bId" value="${board.boardId }"/>
				<c:param name="renameFileName" value="${ board.renameFileName }"/>
			</c:url>
			<c:url var="blist" value="blist.bo">
				<c:param name="page" value="${ page }"/>
			</c:url>
			
			
			<!-- 로그인유저 수정삭제 버튼 보이기 -->
			<c:if test="${ loginUser.id eq board.boardWriter }">
			<tr>
				<td colspan="2" align="center">
					<button type="button">수정하기</button>
					<button type="button" onclick="location.href='${bdelete}'">삭제하기</button>
				</td>
			</tr>	
			</c:if>
	
		</table>
	</form>
	
	
	
	
	<!-- 시작페이지,목록보기 이동버튼 -->
	<p align="center">
		<button type="button" onclick="location.href='home.do'">시작페이지</button>
		<button type="button" onclick="location.href='${blist}'">목록보기</button>
	</p>



	
	<!-- 댓글창 -->
	<table class="replyTable">
		<tr>
			<td><textarea cols="55" rows="3" id="replyContent"></textarea></td>
			<td><button id="rSubmit">댓글 등록</button></td>
		</tr>
	</table>
	<table class="replyTable" id="rtb">
		<thead>
			<tr>
				<td colspan="2"><b id="rCount"></b></td>
			</tr>	
		</thead>
		<tbody>
			<!-- 쓴 댓글 보이는 부분 -->
		</tbody>
	</table>
	
	<script>
		// 댓글 등록 : jQuery ajax
		$('#rSubmit').click(function(){
			var rContent = $('#replyContent').val();
			var refBId = ${board.boardId};
			
			$.ajax({
				url:'addReply.bo',
				data:{replyContent:rContent, boardId:reBId},
				success:function(data){
					console.log(data);
					if(data == 'success'){
						$('#replyContent').val(' ');
					}
				},
				error:function(data){
					console.log(data);
				}
			});
		});
	
		// 등록한 댓글 읽어오기
		function getReplyList(){
			$.ajax({
				url:'rList.bo',
				data:{bId:${board.boardId}},
				success:function(data){
					console.log(data);
					
					$tableBody = $('#rtb tbody');
					$tableBody.html('');
					
					// 변수선언
					// var a; 자바스크립트 변수 // var $a; jQuery 변수
					var $tr;
					var $writer;
					var $content;
					var $date;
					$('#rCount').text('댓글('+data.length+')');
					
					if(data.length > 0){
						for(var i in data){
							$tr = $('<tr>');
							$writer = $('<td>').css('width','100px').text(data[i].nickName); 
							$content = $('<td>') .text(data[i].replyContent);
							$date = $('<td width="100px">').text(data[i].replyCreateDate);	
							
							$tr.append($writer);
							$tr.append($content);
							$tr.append($date);
							$tableBody.append($tr);
						}
					}else{
						$tr = $('<tr>');
						$content = $('<td colspan="3">').text('등록된 댓글이 없습니다.');
						
						$tr.append($content);
						$tableBody.append($tr);
					}
				},
				error:function(data){
					console.log(data);
				}
			});
		}
	
	
		// 다른 사람이 쓴 댓글도 볼 수 있게 5초마다 읽어오도록
		function(){
			getReplyList();
			
			setInterval(function(){
				getReplyList();
			}, 5000);
		});
	
	</script>



</body>
</html>