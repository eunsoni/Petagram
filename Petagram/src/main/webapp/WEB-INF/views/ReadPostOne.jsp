<%@page import="dto.ReadPostOneDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Petagram</title>
<link href="${path}/resources/css/menu.css" rel="stylesheet">
<link href="${path}/resources/css/header.css" rel="stylesheet">
<link href="${path}/resources/css/ReadPostOne.css" rel="stylesheet">
<script src="${path}/resources/js/jquery-3.7.1.min.js"></script>
<script src="${path}/resources/js/ReadPostOne.js"></script>
</head>

<script>
	let sessionId = <%=session.getAttribute("memberid")%>;

	$(document).ready(function(){
   		showData();
   		likeBtnClick();
   		shareBtnClick();
   		commentFunc();
   		updateDelMenu();
   	});
	
   	function showData(){
   		$("#postTitle").html("${readPostOne.postInfo.title}");
		$("#userName").html("${readPostOne.memberName}");
		$("#date").html("${readPostOne.postInfo.regdate}");
		$("#postContent").html("<h4>${readPostOne.postInfo.content}</h4>");

		if(${image.size() != 0}){         
			let imageId = [];
			let src = [];
			let postId = [];
               
			<c:forEach items="${image}" var="one">
				imageId.push("${one.imageId}");
				src.push("${one.src}");
				postId.push("${one.postId}");
			</c:forEach>
      
			for (var i = 0; i < src.length; i++) {
				$("#postContent").append("<img id='postImg' src=/image/" + src[i] + " width=35% height=35%>");
			}
      
			$("#postContent").append("<br>");
			$("#postImg").css("padding-right", "10px");
		};
	};
	
	function likeBtnClick() {
	   $("#likeBtnText").html("${readPostOne.likeCnt}");
	   
	   $("#likeBtn").on("click", function(){
		   var data = {postId: ${readPostOne.postInfo.postId}, memberId: sessionId};
		   
		   $.ajax({
			   url: "/api/post/like",
			   type: "post",
			   data: JSON.stringify(data),
			   contentType: "application/json",
			   dataType: "json",
			   success: function(response){
				   $("#likeBtnText").html(response);},
			   error: function(request, e){
				   alert("코드: " + request.status + "메시지: " + request.responseText + "오류: " + e);}
		   });
	   });
	};
	
	function shareBtnClick() {
		let share = document.getElementById("share");
		
		share.onclick = () => {
			let currentUrl = location.href;
			let dummy = document.createElement("textarea");
			let url = location.href;
			
			document.body.appendChild(dummy);
			dummy.value = currentUrl;
			dummy.select();
			
			document.execCommand("copy");
			document.body.removeChild(dummy);
			
			alert("해당 URL이 복사되었습니다.");
		};
	};
	
	function commentFunc() {
		let postBtn = document.getElementById("postBtn");
		let inputComment = document.getElementById("inputComment");
		let showComment = document.getElementById("showComment");
		
		let comments = document.createElement("div");
		comments.setAttribute("id", "comments");
		
		let num = 1;
		let comment;
		let commentText;
		let deleteBtn;

		// 기존 데이터베이스에 있는 댓글 먼저 정렬
		if(${readPostOne.comments.size() > 0}){
			<c:forEach items="${readPostOne.comments}" var="one">            
				comment = document.createElement("span");
				comment.setAttribute("id", `${one.commentId}`);
				commentText = document.createElement("p");
				commentText.setAttribute("id", "commentText");
				
				deleteBtn = document.createElement("input");
				deleteBtn.setAttribute("type", "button");
				deleteBtn.setAttribute("id", "deleteBtn");
				deleteBtn.setAttribute("value", "삭제");
				deleteBtn.setAttribute("style", "border:none; background-color:#fcf3ea; color:grey; float:right;");
				deleteBtn.setAttribute("onclick", `deleteCommentBtn(${one.commentId})`);
				
				comment.appendChild(commentText);
				comment.appendChild(deleteBtn);
				
				commentText.innerHTML += "${one.content}";
				comment.innerHTML += "<br><hr>";
				comments.appendChild(comment);
				showComment.appendChild(comments);
				inputComment.value = "";
				num++;
			</c:forEach>
		}
      
		// 새로운 댓글 추가
		$("#postBtn").on("click", function(){
			var data = {content: $("#inputComment").val(), memberId: sessionId, postId: ${readPostOne.postInfo.postId}}
			
			if (inputComment.value != "") {            
				$.ajax({
					url: "/api/comment",
					data: JSON.stringify(data),
					type: "post",
					contentType: "application/json",
					dataType: "json",
					success: function(response){
						// alert("댓글 작성완료");
						location.reload();
					},
					error: function(request, e){
						alert("코드: " + request.status + "메시지: " + request.responseText + "오류: " + e);
					}
				});
			} else {
				alert("댓글을 입력해주세요.");
			}
		});
	};
   
	function deleteCommentBtn(commentId) {		
		if(sessionId == ${readPostOne.postInfo.memberId}){
			let isDelete = confirm("해당 댓글을 삭제하시겠습니까?");
			if (isDelete){
				$(`#${'${commentId}'}`).remove();
				$.ajax({
					url: "/api/comment/delete",
					data: {"commentId": `${'${commentId}'}`},
					type: "post",
					dataType: "json",
					success: function(response){
						location.reload();
					},
					error: function(request, e){
						alert("코드: " + request.status + "메시지: " + request.responseText + "오류: " + e);
					}
				});
			};
		} else{
			alert("해당 댓글을 삭제할 수 있는 권한이 없습니다.");
		}
	};
   
	function updateDelMenu() {
		$("#updatePostBtn").on("click", function(){
			if(sessionId == ${readPostOne.postInfo.memberId}){
				let isUpdate = confirm("해당 게시물을 수정하시겠습니까?");
            
				if (isUpdate) {
					location.href = "/api/post/edit/" + ${readPostOne.postInfo.postId};
				};
			} else {
				alert("해당 게시글을 수정할 수 있는 권한이 없습니다.");
			}
         
		});
      
		$("#deletePostBtn").on("click", function(){         
			var data = {postId: ${readPostOne.postInfo.postId}};
         
			if(sessionId == ${readPostOne.postInfo.memberId}){
				let isDelete = confirm("해당 게시물을 삭제하시겠습니까?");
				
				if (isDelete) {
					$.ajax({
						url: "/api/post/delete",
						data: JSON.stringify(data),
						type: "post",
						dataType: "json",
						contentType: "application/json",
						success: function(response){
							alert("게시물 삭제성공");
							location.href = "/api/post";
						},
						error: function(request, e){
							alert("코드: " + request.status + "메시지: " + request.responseText + "오류: " + e);
						}
					});
				};   
			} else {
				alert("해당 게시물을 삭제할 수 있는 권한이 없습니다.");
			}
		});
	};
</script>

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

	<div class="allElement">
		<div id="postBody">
			<div class="moreHoriz">
				<button class="moreHorizBtn">
					<img class="moreHorizImg"
						src="${path}/resources/images/more_horiz.svg" width="24"
						height="24"></img>
				</button>
				<div class="option">
					<button class="optionBtn" id="updatePostBtn" value="update">게시글
						수정</button>
					<button class="optionBtn" id="deletePostBtn" value="delete">게시글
						삭제</button>
				</div>
			</div>

			<h2 id="postTitle">게시글 제목</h2>
			<hr width="300px" align="left" />
			<div id="info">
				<p id="userName" style="display: inline;">작성자</p>
				<p id="date" style="display: inline;">2023.11.08. 15:48</p>
			</div>
			<div id="postContent">
				<p>게시글 내용</p>
			</div>
		</div>

		<div id="btns">
			<button id="likeBtn" type="button">
				<span> <img src="${path}/resources/images/bone.png">
					<p id="likeBtnText" style="display: inline;">0</p>
				</span>
			</button>
			<button id="share" type="button">
				<img src="${path}/resources/images/link.png">
			</button>
		</div>

		<div id="commentDiv">
			<p>댓글</p>
			<hr width="85%" align="left">
			<textarea id="inputComment" rows="5" placeholder="댓글을 입력하세요."></textarea>
			<br> <input type="button" id="postBtn" value="등록">
			<div id="showComment"></div>
		</div>
	</div>
</body>
</html>