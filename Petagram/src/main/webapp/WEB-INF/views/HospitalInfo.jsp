<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Petagram</title>
	<link href="${path}/resources/css/menu.css" rel="stylesheet">
	<link href="${path}/resources/css/header.css" rel="stylesheet">
	<link href="${path}/resources/css/HospitalInfo.css" rel="stylesheet">
	<script src="${path}/resources/js/jquery-3.7.1.min.js"></script>
	<script src="${path}/resources/js/HospitalInfo.js"></script>
	<script 
		type="text/javascript"
		src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=59yii1cbup&submodules=geocoder">
	</script>
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

	<div class="contents">
		<div id="map"></div>
		<div class="content">
			<span id="search">
				<input type="text" id="searchInput" placeholder="주소를 입력하세요">
				<input type="submit" id="searchBtn" value="검색">
			</span>
			<div id="showInfo"></div>
		</div>
	</div>
</body>
</html>