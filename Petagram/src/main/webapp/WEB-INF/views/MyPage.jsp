<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/resources/css/mypage.css">
<link rel="stylesheet" href="${path}/resources/css/menu.css">
<link rel="stylesheet" href="${path}/resources/css/header.css">
<script src="${path}/resources/js/MyPage.js"></script>
<script src="${path}/resources/js/ReadPostAll.js"></script>
</head>

<link rel="icon" href="data:;base64,iVBORw0KGgo=">

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

	<div class="modalBackground">
		<div id="my_modal">
			<p class="message">정말 탈퇴하시겠습니까?</p>
			<button class="yes"
				data-member-id="${session.getAttribute('memberid')}">확인</button>
			<button class="no">취소</button>
		</div>
	</div>

	<div id="resignSuccess">
		<p class="message">탈퇴가 성공적으로 처리되었습니다</p>
		<button id="check">확인</button>
	</div>
	<!-- Home -->
	<section class="home" id="home">

		<h2>마이페이지</h2>
		<h3 class="subtitle subtitle1">게시물</h3>
		<div id="contents1">
			<section class="post">
				<c:forEach var="post" items="${myPosts}">
					<div class="post-box">
						<c:forEach var="image" items="${images}">
							<c:if test="${image.postId == post.postId}">
								<c:set var="src" value="${image.src}" />
							</c:if>
						</c:forEach>
						<img src=/image/${src} class="post-img"
							data-post-id="${post.postId}">
						<h2 class="post-title" data-post-id="${post.postId}">${post.title}</h2>
						<div class="profile">
							<img src="${path}/resources/images/user.png" class="profile-img">
							<p class="profile-name">
								<c:forEach var="member" items="${memberTotal}">
									<c:if
										test="${session.getAttribute('memberid') == member.memberId}">
            						${member.memberName}
        						</c:if>
								</c:forEach>
							</p>

						</div>
						<div class="like">
							<c:set var="likeImage"
								value="${path}/resources/images/bone_white.png" />

							<c:forEach var="personal" items="${likes}">
								<c:if test="${post.postId == personal.postId}">
									<c:set var="likeImage"
										value="${path}/resources/images/bone_red.png" />
								</c:if>
							</c:forEach>

							<img src="${likeImage}" class="like-img"
								data-member-id="${session.getAttribute('memberid')}"
								data-post-id="${post.postId}">

							<p class="like-num">
								<c:set var="likeNum" value="0" />
								<c:forEach var="like" items="${likeTotal}">
									<c:if test="${like.postId == post.postId}">
										<c:set var="likeNum" value="${likeNum + 1}" />
									</c:if>
								</c:forEach>
								${likeNum}
							</p>
						</div>
					</div>
				</c:forEach>
			</section>
		</div>
		<div>
			<button id="btn1">더보기</button>
		</div>
		<div class="calForm">
			<h3 class="subtitle">급여량계산</h3>
			<div>
				&nbsp;&nbsp;&nbsp; <label for="age">나이</label>&nbsp;&nbsp;&nbsp; <input
					type="text" id="age"><br> <span class="weight">
					&nbsp;&nbsp;&nbsp; <label for="weight">체중</label>&nbsp;&nbsp;&nbsp;
					<input type="text" id="weight"><br>
				</span>
				<div class="result">
					&nbsp;&nbsp;&nbsp; <label for="result">급여량</label> <input
						type="text" id="calResult">
					<button id="btn2">계산</button>
				</div>
			</div>
		</div>

		<div class="infoForm">
			<h3 class="subtitle">개인정보</h3>
			&nbsp;&nbsp;&nbsp; <label for="password">비밀번호</label> <input
				type="password" id="password"> <span class="update">
				<button id="fix">수정</button>
				<button id="cancel-btn">탈퇴</button>
			</span>
		</div>

	</section>

</body>
</html>
