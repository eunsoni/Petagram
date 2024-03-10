<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />

<html>
<head>
	<title>Petagram</title>
	<link rel="stylesheet" href="${path}/resources/css/menu.css">
	<link rel="stylesheet" href="${path}/resources/css/header.css">
	<link rel="stylesheet" href="${path}/resources/css/home.css">
	<script src="${path}/resources/js/home.js"></script>
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
	
	<div>
		<div id="postsTitle">
			<img src="${path}/resources/images/top5.png">
			<h2>Top 5 인기게시글</h2>
		</div>
		<div id="postsBody">
			<c:forEach var="popPost" items="${pophome.posts}" varStatus="status">
				<div class="p-post-box">
					<c:forEach var="image" items="${images}">
						<c:if test="${image.postId == popPost.postId}">
							<c:set var="src" value="${image.src}" />
						</c:if>
					</c:forEach>
					<c:if test="${status.index == 0}">
						<img src="${path}/resources/images/1st.png" class="grade">
					</c:if>
					<c:if test="${status.index == 1}">
						<img src="${path}/resources/images/2nd.png" class="grade">
					</c:if>
					<c:if test="${status.index == 2}">
						<img src="${path}/resources/images/3rd.png" class="grade">
					</c:if>
					<img src=/image/${src} class="post-img"
						data-post-id="${popPost.postId}">
					<a class="post-subject" data-post-id="${popPost.postId}">${popPost.title}</a>
					<div class="profile">
						<img src="${path}/resources/images/user.png" class="profile-img">
						<span class="profile-name">
							<c:forEach var="member" items="${pophome.members}">
								<c:if test="${popPost.memberId eq member.memberId}">
            						${member.memberName}
        						</c:if>
							</c:forEach>
						</span>
					</div>
					<div class="like">
						<c:set var="likeImage" value="${path}/resources/images/bone_white.png" />

						<c:forEach var="personal" items="${likes}">
							<c:if test="${popPost.postId == personal.postId}">
								<c:set var="likeImage" value="${path}/resources/images/bone_red.png" />
							</c:if>
						</c:forEach>

						<img src="${likeImage}" class="like-img"
							data-member-id="${session.getAttribute('memberid')}"
							data-post-id="${popPost.postId}">

						<p class="like-num">
							<c:set var="likeNum" value="0" />
							<c:forEach var="like" items="${likeTotal}">
								<c:if test="${like.postId == popPost.postId}">
									<c:set var="likeNum" value="${likeNum + 1}" />
								</c:if>
							</c:forEach>
							${likeNum}
						</p>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
	<div>
		<div id="postsTitle">
			<img src="${path}/resources/images/allPost.png">
			<h2>전체 게시글</h2>
		</div>
	
	<section class="home">
		<div id="postsBody2">
			<c:forEach var="post" items="${home.posts}">
				<div class="post-box">
					<c:forEach var="image" items="${images}">
						<c:if test="${image.postId == post.postId}">
							<c:set var="src" value="${image.src}" /> 
						</c:if>
					</c:forEach>
					<img src=/image/${src} class="post-img"
						data-post-id="${post.postId}">
					<a class="post-subject" data-post-id="${post.postId}">${post.title}</a>
					<div class="profile">
						<img src="${path}/resources/images/user.png" class="profile-img">
						<span class="profile-name">
							<c:forEach var="member" items="${home.members}">
								<c:if test="${post.memberId eq member.memberId}">
            						${member.memberName}
        						</c:if>
							</c:forEach>
						</span>
					</div>
					<span class="like">
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
					</span>
				</div>
			</c:forEach>
		</div>
	</section>
	</div>
		
	<section class="page-area">
		<ul class="paging">
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
