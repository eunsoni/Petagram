window.onload=function(){

	let Id = document.getElementById("Id");
    let Pass= document.getElementById("Pass");
    let nickName = document.getElementById("nickName");
    let email = document.getElementById("email");
    let phone = document.getElementById("phone");
    let aniName = document.getElementById("aniName");
    let aniAge = document.getElementById("aniAge");
	
	//입력창 클릭하면 내용 삭제 기능
	Id.onclick = function(){Id.value = '';}
	Pass.onclick = function(){Pass.value = '';}
	nickName.onclick = function(){nickName.value = '';}
	email.onclick = function(){email.value = '';}
	phone.onclick = function(){phone.value = '';}
	aniName.onclick = function(){aniName.value = '';}
	aniAge.onclick = function(){aniAge.value = '';}
	
    let reg_Id = /^[A-Za-z0-9]{2,12}$/;
    let reg_Pass = /^[A-Za-z0-9]{10,25}$/;
    let reg_email = /^[A-Za-z0-9]+@[A-Za-z0-9]+\.(co\.kr|com|ac\.kr|net)$/;
    let reg_phone = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/; // (두자리 or 세자리)-(세자리 or 네자리)-(네자리)
    let reg_aniAge = /^[0-9]{1,2}$/;
	

	var memo_Id = document.getElementById("memo_Id");
	var memo_Pass = document.getElementById("memo_Pass");
	var memo_nickName = document.getElementById("memo_nickName");
	var memo_email = document.getElementById("memo_email");
	var memo_phone = document.getElementById("memo_phone");
	var memo_aniName = document.getElementById("memo_aniName");
	var memo_aniAge = document.getElementById("memo_aniAge");
	var memo_species = document.getElementById("memo_species");
	var memo_aniGender = document.getElementById("memo_aniGender");

    document.getElementById("join_btn").onclick = function(){
   
       	IdValue = Id.value;
        PassValue = Pass.value;
        nickNameValue = nickName.value;
        emailValue = email.value;
        phoneValue = phone.value;
        aniNameValue = aniName.value;
        aniAgeValue = aniAge.value;
        
        memo_Id.innerHTML ="";
        memo_Pass.innerHTML ="";
        memo_aniGender.innerHTML ="";
        memo_species.innerHTML ="";
        memo_nickName.innerHTML = '';
        memo_email.innerHTML = '';
        memo_phone.innerHTML = '';
        memo_aniName.innerHTML = '';
        memo_aniAge.innerHTML = '';
        //정보수정 코멘트 초기화
        
        confirm();
        if(first_confirm==false){
			return;
		}
		
        if(document.querySelector('input[name="species"]:checked') == null){
			memo_species.innerHTML = "애완동물 종을 입력해주세요";
			return;
		}
		else if(document.querySelector('input[name="aniGender"]:checked') == null){
			memo_aniGender.innerHTML = "애완동물 성별을 입력해주세요";
			return;
			}
        
        speciesValue = document.querySelector('input[name="species"]:checked').value;
        aniGenderValue = document.querySelector('input[name="aniGender"]:checked').value;
        // 회원가입 창에서 입력창에 입력한 값 (...Value) 변수에 저장 
        
        document.getElementById("input_in").style = "display:none";
		// 회원가입 완료 후 입력창 지우고 메모 남김
        
        alert(nickNameValue+"님 회원가입을 축하합니다");
		    let childWindow;
		    childWindow = window.open("Login.html","myPopup");
		    window.close();
	}

        // 조건 설정        
    function confirm(){			
        //유효성 검사 시작
        var Id_exist = false;
        for(let i=0;i<arr_info.length;i++){
			if(IdValue == arr_info[i][0]){
				memo_Id.innerHTML = "이미 사용중인 아이디입니다."
				Id_exist=true;
				first_confirm=false;
				document.getElementById("Id").focus();
				break;			
			}// if문 종료
		}// for문 종료
		
		if(Id_exist == false){
			 
			if(IdValue==''){
				first_confirm = false;
				memo_Id.innerHTML = "아이디를 입력하세요";
				document.getElementById("Id").focus();
			}
			else if(PassValue==''){
				first_confirm = false;
				memo_Pass.innerHTML = "비밀번호를 입력하세요";
				document.getElementById("Pass").focus();
			}
	      	else if(nickNameValue==''){
				first_confirm = false;
				memo_nickName.innerHTML = "닉네임을 입력하세요";
				document.getElementById("nickName").focus();
			}
			else if(emailValue==''){
				first_confirm = false;
				memo_email.innerHTML = "이메일을 입력하세요";
				document.getElementById("email").focus();
			}
			else if(phoneValue==''){
				first_confirm = false;
				memo_phone.innerHTML = "전화번호를 입력하세요";
				document.getElementById("phone").focus();
			}
			else if(aniNameValue == ''){
				first_confirm = false;
				memo_aniName.innerHTML = "애완동물 이름을 입력하세요";
				document.getElementById("aniName").focus();
			}
			else if(aniAgeValue == ''){
				first_confirm = false;
				memo_aniAge.innerHTML = "애완동물 나이를 입력하세요";
				document.getElementById("aniAge").focus();
			}
	        else if(!(reg_Id.test(IdValue))){
				first_confirm = false;
				memo_Id.innerHTML = "아이디는 영문, 숫자를 포함한 6~12 자리를 입력해주세요 ";
				document.getElementById("Id").focus();
			}
			else if(!(reg_Pass.test(PassValue))){
				first_confirm = false;
				memo_Pass.innerHTML = "비밀번호는 영문, 숫자를 포함한 10~25 자리를 입력해주세요 ";
				document.getElementById("Pass").focus();
			}
			else if(!(reg_email.test(emailValue))){
				first_confirm = false;
				memo_email.innerHTML = "이메일 형식이 아닙니다.";
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
			}
		}
	}  
}