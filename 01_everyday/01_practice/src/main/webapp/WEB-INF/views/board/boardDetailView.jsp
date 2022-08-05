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
					${ board.boardId }
					<input type="hidden" value="${ board.boardId }" name="boardId"> <!-- boardId넣는 이유 : 모델어트리뷰트로 받아올려는 것 -->
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
					${board.nickName }
					<input type="hidden" name="nickName" value="${ board.nickName }">
				</td>
			</tr>
			<tr>
				<th>작성날짜</th>
				<td>${ board.boardCreateDate }</td>
			</tr>
			
			<tr>
				<th>내용</th>
				<% pageContext.setAttribute("newLineChar", "\r,\n"); %>
				<td>
					${fn:replace(board.boardContent, newLineChar, "<br>" }
					<input type="hidden" name="boardContent" value="${ board.boardContent }">				
				</td>
			</tr>
			
			<c:if test="${ !empty board.originalFileName }">
			<tr>
				<th>첨부파일</th>
				<a href="${pageContext.servletContext.contextPath }/resources/buploadFiles/${board.renameFileName}" download="${board.originalFileName }">${originalFileName }</a>
				<input type="hidden" name="originalFileName" value="${board.originalFileName }">
				<input type="hidden" name="renameFileName" value="${board.renameFileName }">
			</tr>
			</c:if>
		</table>
	</form>
	
	<!-- url 변수선언 -->
	<c:url var="bdelete" value="bdelete.bo">	<!-- c:url 변수선언함. 그리고 아래 button태그에서 끌어다 씀 -->
		 <c:param name="bId" value="${ board.boardId }"/>	<!-- 삭제버튼에 필요한 게시판번호 데이터보내기 -->
		 <c:param name="renameFileName" value="${ board.renameFileName }"/> <!-- 게시판이 삭제되면 안에 있는 파일도 같이 삭제되어야하니 같이 데이터전송  -->
	</c:url>
	<c:url var="blist" value="blist.bo">
		<c:param name="page" value="${page }"></c:param>
	</c:url>
	
	<!-- 로그인유저 수정삭제 버튼 보이기 -->
	<c:if test="${ loginUser.id eq board.nickname }">
		<tr>
			<td colspan="2" align="center">
				<button>수정하기</button>
				<button type="button" onclick="location.href='${bdelete}'">삭제하기</button>
			</td>
		</tr>
	</c:if>
	
	<!-- 시작페이지,목록보기 이동버튼 -->
	<p align="center">
		<button type="button" onclick="location.href='home.do'">시작 화면으로</button>
		<button type="button" onclick="location.href='blist.bo'">목록으로</button>
	</p>
	
	<!-- 댓글창 -->
	<table class="replyTable">
		<tr>
			<td><textarea cols="55" rows="3" id="replyContent"></textarea></td>
			<td><button id="rSubmit">등록하기</button>
		</tr>
	</table>
	<table class="replyTable" id="rtb">
		<thead>
			<tr> 
				<td colspan="2"><b id="rCount"></b></td> <!-- b태그 사이에 '댓글'이라고 나옴 -->
			</tr>
		</thead>
		<tbody>
			<!-- 쓴 댓글 내용 나오는 부분 -->
		</tbody>
	</table>
	
	
	
	<script>
		// 댓글등록
		$('rSubmit').on('click',function(){
			var rContent = $('#replyContent').val();
			var refBId = $(board.boardId);
			
			$.ajax({
				url:'addReply.bo',
				data:{replyContent:rContent, boardId:refBId},
				success:function(){
					console.log(data);
					if(data == 'success'){
						$('#replyContent').val(' ');
					}
				},
				error:function(){
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
					
					// 계속 이어붙기 때문에 공백 넣어줘야함
					$tableBody = $('#rtb tbody');
					$tableBody.html('');
					
					// 변수선언
					var $tr;
					var $writer;
					var $content;
					var $data;
					$('#rCount').text('댓글('+ data.length + ')');
					
					if(data.length > 0){
						for(var i in data){
							$tr = $('<td>');
							$writer = $('<td>').css('width','100px').text(data[i].nickName);
							$content = $('<td>').text(data[i].replyContent);
							$data = $('<td width="100px">').text(data[i].replyCreateDate);
							
							$tr.append($writer);
							$tr.append($content);
							$tr.append($date);
							$tableBody.append($tr);
							
						}
					}else{
						$tr = $('<td>');
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
		$(function(){
			getReplyList();
			
			setInterval(function(){
				getReplyList();
			}, 5000);
		});
		
	
	</script>
	
</body>
</html>

