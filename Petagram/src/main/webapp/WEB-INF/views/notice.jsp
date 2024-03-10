<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<title>Petagram</title>
	<link rel="stylesheet" href="${path}/resources/css/menu.css">
	<link rel="stylesheet" href="${path}/resources/css/header.css">
	<link rel="stylesheet" href="${path}/resources/css/notice.css">
</head>

<body>
	<div id="header">
		<img id="logo" src="${path}/resources/images/logo.png"
			onclick="location.href = '/api/home'">
		<button type="button" class="btn"
			onclick="location.href = '/api/post/write'">게시글 작성</button>
		<button type="button" class="btn"
			onclick="location.href = '/api/login'">로그아웃</button>
	</div>

	<div id="menu">
		<menu>
			<li><a href="/api/post">게시글</a></li>
			<li><a href="/api/hospital">동물병원</a></li>
			<li><a href="/api/notice">공지사항</a></li>
			<li><a href="/api/mypage">마이페이지</a></li>
		</menu>
	</div>
   
   <div id="informlist">
		<h2 id="title">&nbsp;공지사항&nbsp;</h2>
		<c:if test="${authority == true}">
			<button id="writeInform" type="button">공지사항 작성</button>
		</c:if>
		
		<div id="maintext">
			<c:forEach var="inform" items="${informs}">
				<div id="inform">
					<div class="bullet">공지</div>
					<p class="subtitle">
						&nbsp;&nbsp;${inform.title}
						<c:if test="${authority == true}">
							<button class="deletebtn" type="button" data-inform-id="${inform.informid}">삭제</button>
						</c:if>
					</p>
					<div class="textbox">&nbsp;${inform.textbox}</div> 
				</div>
			</c:forEach>
		</div>
	</div>
	
	<div id="modal">
		<div id="modalbody">
			<input type="button" id="enroll" value="등록">
			<span id="closebtn">&times;</span>
			<textarea id="textTitle" placeholder="제목을 입력해주세요"></textarea>
			<textarea id="text" placeholder="공지사항 내용을 입력하세요"></textarea>
		</div>
	</div>
	<script src="${path}/resources/js/notice.js"></script>
</body>
</html>