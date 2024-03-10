document.addEventListener('DOMContentLoaded', function() {
	// postId를 클릭한 postBox에서 가져와서 새로운 URL로 이동
	let postSubjects = document.querySelectorAll('.post-subject');
	let postImages = document.querySelectorAll('.post-img');
	let likeImages = document.querySelectorAll('.like-img');
	let likeNums = document.querySelectorAll('.like-num');
	
	postSubjects.forEach(function(postSubject) {
		postSubject.addEventListener('click', function() {
					let postId = postSubject.dataset.postId; 
					location.href = 'http://localhost:9080/api/post/' + postId;
			});
	});
	
	postImages.forEach(function(postImage) {
			postImage.addEventListener('click', function() {
					let postId = postImage.dataset.postId; 
					location.href = 'http://localhost:9080/api/post/' + postId;
			});
	});
	
	likeImages.forEach(function(likeImage) {
        likeImage.addEventListener('click', function() {
            let postId = likeImage.dataset.postId; 
            let memberId = likeImage.dataset.memberId; 
            fetch('http://localhost:9080/api/post?postId=' + postId + '&memberId=' + memberId, {
           		method: 'GET',
	            headers: {
	                'Content-Type': 'application/json',
	                // You can add more headers if needed
	            },
        	})
        	.then(() => {
           		location.reload();
        	})
        });
        
    });
});