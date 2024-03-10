<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Petagram</title>
<c:set var="path" value="${pageContext.request.contextPath}" />
<script src="${path}/resources/js/CreatePost.js"></script>
<script src="${path}/resources/js/jquery-3.7.1.min.js"></script>
<link href="${path}/resources/css/home.css" rel="stylesheet">
<link href="${path}/resources/css/menu.css" rel="stylesheet">
<link href="${path}/resources/css/header.css" rel="stylesheet">
<link href="${path}/resources/css/createpost.css" rel="stylesheet"
	type="text/css">

<%-- <script>
    let memberId = <%=session.getAttribute("memberid") %>;
    // 세션에 회원 ID를 설정
    sessionStorage.setItem('memberid', '3'); // 임의의 회원 ID인 3으로 설정
    // 세션에서 회원 ID를 가져와서 출력
    var loggedInMember = sessionStorage.getItem('memberid');
    console.log('로그인된 회원 ID:', loggedInMember); // 개발자 도구 콘솔에 출력
  </script> --%>


</head>
<link rel="icon" href="data:;base64,iVBORw0KGgo=">
<body>
	<div id="header">
		<img id="logo" src="${path}/resources/images/logo.png"
			onclick="location.href = '/post/home'">
		<button type="button" class="btn"
			onclick="location.href = '/post/write'">게시글 작성</button>
		<button type="button" class="btn"
			onclick="location.href = '/post/login'">로그아웃</button>
		<%-- <c:set var="loggedInMember" value="1" /> <!-- 임의의 memberid인 1으로 설정 -->
        <p>로그인된 회원 ID: <c:out value="${loggedInMember}" /></p> --%>



		<%-- 세션에서 회원 정보 가져오기 --%>
		<%--   <c:if test="${not empty sessionScope.loggedInMember}">
            세션에 로그인된 회원 정보가 있을 경우
            <c:set var="loggedInMember" value="${sessionScope.loggedInMember}" />
            <p>로그인된 회원 ID: <c:out value="${loggedInMember.memberId}" /></p>
        </c:if>
        세션이 없거나 로그인되지 않은 경우
        <c:if test="${empty sessionScope.loggedInMember}">
            <p>로그인 되지 않았습니다.</p>
        </c:if> --%>
	</div>


	<div id="menu">
		<menu>
			<li><a href="/api/post">게시글</a></li>
			<li><a href="/api/hospital">동물병원</a></li>
			<li><a href="/api/notice">공지사항</a></li>
			<li><a href="/api/mypage">마이페이지</a></li>
		</menu>
	</div>

	<br>
	<br>
	<br>
	<section id="container">
		<div class="post">
			<form id="postForm" action="/api/post/write" method="post"
				enctype="multipart/form-data" accept-charset="utf-8">

				<div>
					<h2 id="posth2">게시글 작성</h2>
				</div>
				<div class="postInside">
					<p>제목</p>
					<input type="text" id="postTitle" name="title" required>
					<p>내용</p>
					<textarea id="postContent" name="content" required></textarea>
					<div class="box">
						<input type="file" id="postImage" name="imageFile"
							accept="image/*" value="파일첨부" multiple>


					</div>
					<!--   <div>
                        <p>종류</p>
                        <span id="aniType">
                            <input type="radio" id="dog" name="postType" value="강아지" checked>
                            <label for="dog">강아지</label>
                            <input type="radio" id="cat" name="postType" value="고양이">
                            <label for="cat">고양이</label>
                        </span>
                    </div> -->
					<br> <br>
					<div>
						<input type="submit" value="작성">
					</div>
				</div>
			</form>
		</div>
	</section>
</body>

</html>
