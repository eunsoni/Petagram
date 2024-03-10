window.onload = function(){
	//공지사항 작성 이벤트
	let writeInform = document.getElementById("writeInform");
	let modal = document.getElementById("modal");
	writeInform.onclick = function(){
		modal.style.display = 'block';
	}
	let closebtn = document.getElementById("closebtn");
	closebtn.onclick = function(){
		modal.style.display = 'none';
	}
	
	
	

	
	let enroll = document.getElementById("enroll");
	enroll.onclick = function(){
		let textTitle = document.getElementById("textTitle").value;
    	let text =  document.getElementById("text").value;
		fetch('http://localhost:9080/api/notice?textTitle=' + textTitle + '&text=' + text, {
           		method: 'GET',
	            headers: {
	                'Content-Type': 'application/json',
	            },
        	})
        	.then(() => {
           		location.href = 'http://localhost:9080/api/notice';
        	})
        	
	}
	
	var deleteButtons = document.querySelectorAll('.deletebtn');
    deleteButtons.forEach(function(deleteButton) {
        deleteButton.addEventListener('click', function() {
            var informId = deleteButton.dataset.informId; 
            fetch('http://localhost:9080/api/deleteNotice?informId=' + informId, {
           		method: 'GET',
	            headers: {
	                'Content-Type': 'application/json',
	                // You can add more headers if needed
	            },
        	})
        	.then(() => {
           		location.href = 'http://localhost:9080/api/notice';
        	})
        });
    });

	
}

