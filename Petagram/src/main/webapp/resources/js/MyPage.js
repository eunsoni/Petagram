document.addEventListener('DOMContentLoaded', function() {
    let modal = document.querySelector('#my_modal');
	let resignSuccess = document.querySelector('#resignSuccess');
	let modalBackground = document.querySelector('.modalBackground');
	let cancelBtn = document.querySelector('#cancel-btn');
	let check = document.querySelector('#check');
	let yes = document.querySelector('.yes');
	let no = document.querySelector('.no');
	let fix = document.querySelector('#fix');
	
	let btn1 = document.querySelector('#btn1');
	
	btn1.addEventListener('click', function() {
		location.href = "http://localhost:9080/api/myall";
	});
	
	let btn2 = document.querySelector('#btn2');
	//ReadPostAll 페이지로 이동
	btn2.addEventListener('click', function() {
		let age = Number(document.querySelector('#age').value);
	    let weight = Number(document.querySelector('#weight').value);
	    let answer = calculateMeal(age, weight);
	    let calResult = document.querySelector('#calResult');
	    calResult.value = answer;
	});
	
	fix.addEventListener('click', function() {
		let password = document.querySelector('#password').value;
		if (password === "") {
        	alert("비밀번호를 입력해주세요");
        	return; 
    	}
		
		fetch('http://localhost:9080/api/validate?password=' + password, {
        	method: 'GET',
       		headers: {
            	'Content-Type': 'application/json',
        	},
    	})
    	
    	.then(response => {
	        if (response.ok) {
	            return response.text(); 
	        }
    	})
		
		.then(data => {
        	if (data == "Validation successful") { 
            	location.href='http://localhost:9080/api/updatemember'
        	} else {
            	alert("비밀번호가 틀렸습니다");
        	}
    	})
	});
	
	cancelBtn.addEventListener('click', function() {
		let password = document.querySelector('#password').value;
		if (password === "") {
        	alert("비밀번호를 입력해주세요");
        	return; 
    	}
		
		fetch('http://localhost:9080/api/validate?password=' + password, {
        	method: 'GET',
       		headers: {
            	'Content-Type': 'application/json',
        	},
    	})
    	
    	.then(response => {
	        if (response.ok) {
	            return response.text(); 
	        }
    	})
		
		.then(data => {
        	if (data === "Validation successful") { 
            	modal.style.display = 'block';
				modalBackground.style.display = 'block';
        	} else {
            	alert("비밀번호가 틀렸습니다");
        	}
    	})
	});
	
	yes.addEventListener('click', function() {
		modal.style.display = 'none';
		resignSuccess.style.display = 'block';
		let memberid = yes.dataset.memberId; 
		
        fetch('http://localhost:9080/api/delete?memberid=' + memberid, {
       		method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                // You can add more headers if needed
            },
    	})

	});

	check.addEventListener('click', function() {
		resignSuccess.style.display = 'none';
		modalBackground.style.display = 'none';
		location.href = "http://localhost:9080/api/home"; //To Main Page
	});

	no.addEventListener('click', function() {
		modal.style.display = 'none';
		modalBackground.style.display = 'none';
	});
});

function calculateMeal(age, weight) {
	if (age < 2) {
		const mealAmount = weight * 80;
		return `${mealAmount}g`;
	} else if (age >= 2) {
		const mealAmount = weight * 40;
		return `${mealAmount}g`;
	}
}