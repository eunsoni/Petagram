<%@page import="java.util.Objects"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Petagram</title>
    <link href="${path}/resources/css/header.css" rel="stylesheet">
    <link href="${path}/resources/css/Login.css" rel="stylesheet">
</head>

<link rel="icon" href="data:;base64,iVBORw0KGgo=">

<body>
    <image id="logo" src="${path}/resources/images/logo.png" onclick="location.href = 'home'"/>
    <div id="outer">
        <h2>로그인</h2>
        <div id="inner">
            <!-- 로그인 폼 추가 -->
            <form action="login" method="post" id="loginForm">
                <h4>아이디</h4>
                <input type="text" id="inputId" name="inputId" placeholder="아이디" required><br><br>
                <h4>비밀번호</h4>
                <input type="password" id="inputPass" name="inputPass" placeholder="비밀번호" required><br><br>
                <div id="garo_btns">
                    <!-- submit 버튼을 사용하여 폼을 제출 -->
                    <input type="submit" id="logBtn" class="btn" value="로그인">
                </div>
            </form>
            <!-- 회원가입 버튼을 누를 때 회원가입 페이지로 이동 -->
            <input type="button" id="joinBtn" class="btn" value="회원가입" onclick="location.href = '/api/registermember'">
        </div>
    </div>
    <script type="text/javascript">
        window.onload = function() {
        	var messageFromModel = "${loginMessage}";

            try {
                // 가져온 값이 null이 아닌 경우 처리
                if (messageFromModel !== null ) {
                	checkInfo(messageFromModel);
                    console.log("세션에서 가져온 값: " + messageFromModel);
                } else {
                    // 값이 null인 경우 처리
                    console.log("세션에서 가져온 값이 null입니다.");
                }
            } catch (error) {
                // HttpSession이 null인 경우 또는 속성이 존재하지 않는 경우 처리
                console.error("아직 로그인 한 적이 없습니다. " + error.message);
            }
            
            var logId = document.getElementById("inputId");
            var logPass = document.getElementById("inputPass");// 로그인 창에서 각각 아이디, 비밀번호 입력을 담은 객체

            logId.onclick = function() { logId.value = ''; };
            logPass.onclick = function() { logPass.value = ''; };// 클릭하면 내용 자동 삭제 기능
        } // window.onload 종료
        
        function checkInfo(message) {
            if (message === "존재하지 않는 아이디입니다.") {
                alert(message);
            }else if (message === "비밀번호를 확인해주세요.") {
                alert(message);
            }
        }
    </script>  
</body>
</html>