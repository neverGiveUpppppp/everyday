<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>prac</title>

<style type="text/css">
	#tb{margin:auto; width:700px; border-collapse:collapse;}
	#tb tr td{padding: 5px}
	#buttonTab{border-left: hidden; border-right:hidden;}

</style>

</head>
<body>


	<c:import url="..common/menubar.jsp"/>
	
	<h1 align="center">게시글 목록</h1>
	<h3 align="center"> 총 게시글 갯수 : ${pi.listCount }</h3>
	
	<table border="1" id="tb">
		<!-- 제목 tr -->
		<tr style="background: yellowgreen;">
			<th>번호</th>
			<th width="300">제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>첨부파일</th>
		</tr>
		<!-- 내용 tr -->
		<c:forEach var="b" items="${list}">
			<tr class="contentTr">
				<td align="center">${b.boardId }</td>
				<td align="center">${b.boardTitle }</td>
				<td align="center">${b.boardWriter}</td>
				<td algin="center">${b.boardCreateDate }</td>
				<td algin="center">${b.boardCount }</td>
				<td align="center"></td>			
				<td align="left">
					<c:if test="${ !empty b.originalFileName }">
						◎
					</c:if>
				</td>		
			</tr>
		</c:forEach>	
		<!-- 글쓰기 버튼 tr  -->
		<tr>
			<td colspan="6" align="right" id="buttonTab">
				<c:if test="${!empty loginUser }">
					&nbsp; &nbsp; &nbsp; <!-- &nbsp; : 공백을 표시하기 위한 특수문자 -->
					<button onclick="location.href='binsertView.bo';">글쓰기</button>
				</c:if>
			</td>
		</tr>
		
		<!-- 페이징처리 tr -->
		<tr>
			<td>
			<!-- prev -->
			<c:if test="${ pi.currentPage <= 1 }">
				[이전] &nbsp;
			</c:if>
			<c:if test="${ pi.currentPage > 1 }">
				<c:url var="before" value="blist.bo">
					<c:param name="page" value="${ pi.currentPage - 1 }"/>
				</c:url>
				<a href="${ before }">[이전]</a> &nbsp;
			</c:if>
			
			<!-- 번호 -->
			
			
			
			<!-- next -->
			</td>
		</tr>
		
	</table>
	
	
	<script>
	
	
	</script>


</body>
</html>


