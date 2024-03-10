document.addEventListener('DOMContentLoaded', function() {
    // postId를 클릭한 postBox에서 가져와서 새로운 URL로 이동
    let postTitles = document.querySelectorAll('.post-title');
    let postImages = document.querySelectorAll('.post-img');
    let likeImages = document.querySelectorAll('.like-img');
    
    postTitles.forEach(function(postTitle) {
        postTitle.addEventListener('click', function() {
            let postId = postTitle.dataset.postId; 
            location.href = 'http://localhost:9080/api/post/' + postId; //To Read Post One
        });
    });
    
    postImages.forEach(function(postImage) {
        postImage.addEventListener('click', function() {
            let postId = postImage.dataset.postId; 
            location.href = 'http://localhost:9080/api/post/' + postId; //To Read Post One
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
