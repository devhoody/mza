window.addEventListener("load", function(){
	let commentBox = document.querySelector(".commentbox")
	let btnPost = commentBox.querySelector(".btn-post")
	let content = document.querySelector(".commentgroup"); //
	let deleteComment = document.querySelectorAll(".comment-delete");
	let deletePost = document.querySelectorAll(".post-delete");
	let currentUserId = document.querySelector("#userid");
	console.log("currentUserId" + currentUserId);
	console.log("currentUserId: " + currentUserId.content);0
	console.log("deleteComment"+deleteComment);
	console.log("deletePost"+deletePost);
// ------------ 포스팅 삭제  -------------

	deletePost.forEach((postDelete) => {

		postDelete.addEventListener("click", async (e) => {
			e.preventDefault();
			let el = e.target;
			console.log("클릭클릭 ");
			console.log(el.dataset.post);

			let url =`/api/commus/posts/${el.dataset.post}`;

			fetch(url,{
				method: 'DELETE'
			})
				.then(response => response.text())
				.then(data => {
					if (data === '') {
						alert("삭제가 완료되었습니다.");
						window.location.href = `/commu/main`;
					}
				})
				.catch(error => {
					console.error("삭제 요청 실패:", error);
				});

		});
	});


// ------------ 댓글 삭제  -------------

	deleteComment.forEach((commuDelete) => {
		commuDelete.addEventListener("click", async (e) => {
			e.preventDefault();
			let el = e.target;
			console.log("클릭클릭 ");
			console.log(el.dataset.comment);

			let url =`/api/commus/comments/${el.dataset.comment}`;

			fetch(url,{
				method: 'DELETE'
			})
				.then(response => response.text())
				.then(data => {
					if (data === '') {
						alert("삭제가 완료되었습니다.");
						window.location.href = `/commu/post/detail?&post-id=${el.dataset.post}`;
					}
				})
				.catch(error => {
					console.error("삭제 요청 실패:", error);
				});

		});
	});
// ------------------------ 댓글 등록 -------------------------

	btnPost.onclick =  async function (e) {
		e.preventDefault();
		console.log("댓글등록 버튼 클릭 !");

		let commentContent = commentBox.querySelector(".comment-content").value;
		console.log(commentContent);

		let url = `/api/commus/comments`; //POST

		let response = await fetch(url, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify({
				postId: commentBox.dataset.postid,
				content: commentContent

			}),

		})
		if (!response.ok) {
			console.error('댓글을 등록하는 중에 문제가 발생했습니다.');
			return;
		}

		let data = await response.json();
		bind(data);

	}


// ------------- 데이터 요청 후 응답기다리는...! -------------


	});