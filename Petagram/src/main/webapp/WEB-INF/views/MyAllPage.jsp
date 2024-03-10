<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/resources/css/ReadPostAll.css">
<link rel="stylesheet" href="${path}/resources/css/menu.css">
<link rel="stylesheet" href="${path}/resources/css/header.css">
<script src="${path}/resources/js/ReadPostAll.js"></script>

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
	
	<section class="home">
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
								<c:if test="${session.getAttribute('memberid') == member.memberId}">
            						${member.memberName}
        						</c:if>
							</c:forEach>
						</p>

					</div>
					<div class="like">
						<c:set var="likeImage" value="${path}/resources/images/bone_white.png" />

						<c:forEach var="personal" items="${likes}">
							<c:if test="${post.postId == personal.postId}">
								<c:set var="likeImage" value="${path}/resources/images/bone_red.png" />
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
	</section>

	<section class="pagination-section">
		<ul class="pagination">
			<c:if test="${currentPage > 0}">
				<li><a href="?page=${currentPage - 1}">이전</a></li>
			</c:if>

			<c:forEach begin="0" end="${totalPages - 1}" varStatus="loop">
				<c:choose>
					<c:when test="${currentPage eq loop.index}">
						<li class="active"><span>${loop.index + 1}</span></li>
					</c:when>
					<c:otherwise>
						<li><a href="?page=${loop.index}">${loop.index + 1}</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test="${currentPage < totalPages - 1}">
				<li><a href="?page=${currentPage + 1}">다음</a></li>
			</c:if>
		</ul>
	</section>
</body>
</html>
