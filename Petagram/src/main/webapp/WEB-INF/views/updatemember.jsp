<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.mysql.cj.xdevapi.JsonArray"%>
<%@ page import="com.google.gson.JsonElement"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Petagram</title>
<link rel="stylesheet" href="${path}/resources/css/ChangeInfo.css">
<link rel="stylesheet" href="${path}/resources/css/header.css">
<link rel="stylesheet" href="${path}/resources/css/menu.css">
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

	<form action="updatemember" id="input_form" name="input_form"
		method="post">
		<h2>회원 정보 수정</h2>
		<div id="textForm">
			<h3>주인</h3>
			<h4>아이디</h4>
			<h4>이름</h4>
			<input type="text" name="membername" id="membername"
				value="${membername}" required>
			<p id="memo_membername"></p>

			<h4>이메일</h4>
			<input type="email" name="email" id="email" value="${email}" required>
			<p id="memo_email"></p>

			<h4>전화번호</h4>
			<input type="text" name="phone" id="phone"
				placeholder="000-0000-0000" value="${phone}" required><br>
			<p id="memo_phone"></p>

			<div id="petFormsContainer">
				<h3>애완동물</h3>
				<h4>이름</h4>
				<input type="text" name="petname" id="petname" value="${petname}"
					required>
				<p id="memo_petname"></p>

				<h4>나이</h4>
				<input type="text" name="age" id="age" value="${age}" required>
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
		<input type="submit" id="edit_btn" name="edit_btn" value="수정">
	</form>


	<script type="text/javascript">										
      											
    let my_arr_info = [${memberid},${id},${pw},${membername},${email},${phone},${petname},${age},${pettype},${gender}]; 
    

    var first_confirm = false;
    
    let nickName = document.getElementById("nickName");
    let email = document.getElementById("email");
    let phone = document.getElementById("phone");
    let aniName = document.getElementById("aniName");
    let aniAge = document.getElementById("aniAge");
    //입력창 객체 정의
   
   
   var memo_ = document.getElementById("memo_nickName");
   var memo_nickName = document.getElementById("memo_nickName");
   var memo_email = document.getElementById("memo_email");
   var memo_phone = document.getElementById("memo_phone");
   var memo_aniName = document.getElementById("memo_aniName");
   var memo_aniAge = document.getElementById("memo_aniAge");
   var memo_species = document.getElementById("memo_species");
   var memo_aniGender = document.getElementById("memo_aniGender");
   // 코멘트 해주는 객체 정의

   
   
   nickName.value = my_arr_info[2];
   email.value = my_arr_info[3];
   phone.value = my_arr_info[4];
   aniName.value = my_arr_info[5];
   aniAge.value = my_arr_info[6];
   //초기값 my_arr_info으로 세팅 기능
   
   

    let reg_email = /^[A-Za-z0-9]+@[A-Za-z0-9]+\.(co\.kr|com|ac\.kr|net)$/;
    let reg_phone = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/; // (두자리 or 세자리)-(세자리 or 네자리)-(네자리)
    let reg_aniAge = /^[0-9]{1,2}$/;
    //조건
   
   
   
   
    document.getElementById("edit_btn").onclick = function(){
   // 수정 버튼 클릭 시 실행되는 함수
        nickNameValue = nickName.value;
        emailValue = email.value;
        phoneValue = phone.value;
        aniNameValue = aniName.value;
        aniAgeValue = aniAge.value;
        // radio 값 제외하고 ...Value 변수에 입력창 값 저장
       
      
        memo_nickName.innerHTML = '';
        memo_email.innerHTML = '';
        memo_phone.innerHTML = '';
        memo_aniName.innerHTML = '';
        memo_aniAge.innerHTML = '';
        memo_aniGender.innerHTML ="";
        memo_species.innerHTML ="";
        //회원가입 코멘트 초기화
       
        confirm();
        if(first_confirm==false){   return;   }
      // radio 값 제외하고 유효성 확인

      
    function confirm(){         
        //유효성 검사 시작
      
      if(!(reg_email.test(emailValue))){
         first_confirm = false;
         memo_email.innerHTML = "이메일 형식이 아닙니다";
         document.getElementById("email").focus();
      }
      else if(!(reg_phone.test(phoneValue))){
         first_confirm = false;
         memo_phone.innerHTML = "'-'을 포함한 전화번호 형식을 입력하세요";
         document.getElementById("phone").focus();
      }
      else if(!(reg_aniAge.test(aniAgeValue))){
         first_confirm = false;
         memo_aniAge.innerHTML = "애완동물 나이를 확인해주세요";
         document.getElementById("aniAge").focus();
      }
        else{
         
         first_confirm = true;
      } // if문 끝      
   } //confirm() 끝
   

</script>
</body>

</html>