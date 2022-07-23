<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="${ pageContext.servletContext.contextPath }/resources/css/member-style.css"> 

<style>
	#myInfoTable td{text-align:center;}
</style>

</head>
<body>

	<c:import url="..common/menubar.jsp"/>
	
	<h1 align="center">'${ loginUser.name }'님의 정보 보기</h1>
	
	<div class="centerText">
		<table id="myInfoTable">
			<tr>
				<th>아이디</th>
				<td>${loginUser.id }</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${loginUser.name }</td>
			</tr>
			<tr>
				<th>닉네임</th>
				<td>${loginUser.nickName }</td>
			</tr>
			<tr>
				<th>성별</th>
				<c:if test="${loginUser.gender eq 'M' }">
					<td> 남성 </td>
				</c:if>
				<c:if test="${loginUser.gender eq  'F'}">
					<td> 여성 </td>
				</c:if>
			</tr>
			<tr>
				<th>나이</th>
				<td>${loginUser.age }</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${ loginUser.email }</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${loginUser.phone }</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>
					<c:forTokens var="ft" items="${loginUser.address }" delims="/" varStatus="vs">
						<c:if test="${vs.index eq 0 && ft >= '0' && ft <= '99999' }">
							(${ft })
						</c:if>
						<c:if test="${vs.index eq 0 && !(ft >= '0' && ft <= '99999')}">
							${ft }
						</c:if>
						<c:if test="${vs.index eq 1 }">
							${ft }
						</c:if>
						<c:if test="${vs.index eq 2 }">
							${ft }
						</c:if>
					</c:forTokens>
					
				</td>
			</tr>
			
			<tr>
				<td colspan="2"	align="center">
					<c:url var="mpwdUpdateView" value="mpwdUpdateView.me"/>
					<button type="button" onclick="${mpwdUpdateView}">비밀번호 수정</button>
					<button type="button" onclick="location.href='mupdateView.me'">정보수정</button>
					<c:url var="mdelete" value="mdelete.me"/>
					<button type="button" onclick="location.href='${ mdelete }'">회원탈퇴</button>
					<button type="button" onclick="home.do">시작페이지로</button>
				</td>
			</tr>
		</table>
	</div>




</body>
</html>