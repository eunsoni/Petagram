<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.mysql.cj.xdevapi.JsonArray"%>
<%@ page import="com.google.gson.JsonElement"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${path}/resources/css/Join.css">
<link rel="stylesheet" href="${path}/resources/css/header.css">

</head>

<link rel="icon" href="data:;base64,iVBORw0KGgo=">

<body>

	<div id="header">
		<img id="logo" src="${path}/resources/images/logo.png"
			onclick="location.href = 'home'">
		<button type="button" class="btn"
			onclick="location.href = 'login'">로그인</button>
	</div>
	<form action="registermember" id="input_form" name="input_form"
		method="post">
		<h2>회원가입</h2>
		<div id="textForm">
			<h3>주인</h3>
			<h4>아이디</h4>
			
			<input type="text" name="id" id="id" required>
			<p id="memo_id"></p>

			<h4>비밀번호</h4>
			<input type="password" name="pw" id="pw" required>
			<p id="memo_pass"></p>

			<h4>이름</h4>
			<input type="text" name="memberName" id="memberName" required>
			<p id="memo_membername"></p>

			<h4>이메일</h4>
			<input type="email" name="email" id="email" required>
			<p id="memo_email"></p>

			<h4>전화번호</h4>
			<input type="text" name="phone" id="phone"
				placeholder="000-0000-0000" required><br>
			<p id="memo_phone"></p>

			<div id="petFormsContainer">
				<h3>애완동물</h3>
				<h4>이름</h4>
				<input type="text" name="petname" id="petname" required>
				<p id="memo_petname"></p>

				<h4>나이</h4>
				<input type="text" name="age" id="age" required>
				<p id="memo_age"></p>

				<h4>종류</h4>
				<label><input type="radio" name="pettype" id="pettype"
					value="강아지" required>강아지</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label><input
					type="radio" name="pettype" id="pettype" value="고양이" required>고양이</label>
				<p id="memo_pettype"></p>
				<h4>성별</h4>
				<label><input type="radio" name="gender" id="gender"
					value="수컷" required>수컷</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label><input type="radio" name="gender" id="gender"
					value="암컷" required>암컷</label><br>
				<p id="memo_gender"></p>
			</div>
		</div>
		
		<div id= "btns">
			<input type=submit id="join_btn" value="회원가입">		
		</div>
	</form>

	<%
	// JSP에서 파라미터 값을 가져옵니다.
	String parameterValue = request.getParameter("registerMessage");

	// 파라미터가 존재하는 경우 JavaScript로 alert를 생성합니다.
	if (parameterValue != null && !parameterValue.isEmpty()) {
	%>
	<script>
        alert("<%=parameterValue%>");
    </script>
	<%
	}
	%>

	<script>
    	let id = document.getElementById("id");
        let pw= document.getElementById("pw");
        let membername = document.getElementById("memberName");
        let email = document.getElementById("email");
        let phone = document.getElementById("phone");
        let petname = document.getElementById("petname");
        let age = document.getElementById("age");
    	
    	//입력창 클릭하면 내용 삭제 기능
    	id.onclick = function(){id.value = '';};
    	pw.onclick = function(){pw.value = '';};
    	membername.onclick = function(){membername.value = '';};
    	email.onclick = function(){email.value = '';};
    	phone.onclick = function(){phone.value = '';};
    	petname.onclick = function(){petname.value = '';};
    	age.onclick = function(){age.value = '';};
    </script>

</body>
</html>