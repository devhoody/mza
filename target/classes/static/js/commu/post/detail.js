window.addEventListener("load", function(){
	let commentBox = document.querySelector(".commentbox")
	let btnPost = commentBox.querySelector(".btn-post")
    let content = document.querySelector(".commentgroup"); // 
 	let deleteComment = document.querySelectorAll(".comment-delete");
 	let deletePost = document.querySelectorAll(".post-delete");
      console.log("deleteComment"+deleteComment);
      console.log("deletePost"+deletePost);
      
// ------------좋아 삭제  -------------

	let bottom = document.querySelectorAll(".bottom");
	for (let i =0 ; i<bottom.length; i++){
	    bottom[i].addEventListener("click", async function(e) {
			let btn = document.querySelector(".commu-likes")
			console.log("클릭체크")
	        let el = e.target;
	        e.preventDefault();
	
	        // 클릭된 요소가 "icon-commu-likes" 클래스를 가진 경우에만 처리
	        if (el.classList.contains("icon-commu-likes")) {
	            console.log("게시물 ID: " + el.dataset.post);
	            console.log("좋아요");
	
	            // 좋아요 처리를 위한 API 호출 등의 로직 추가
	            let url = `/api/commu/likes`;
	
	            let resp = await fetch(url, {
	                method: 'POST',
	                headers: {
	                    'Content-Type': 'application/json',
	                },
	                body: JSON.stringify({
	                    postId: el.dataset.post
	                })
	            });
	            if(resp.ok){
					console.log(btn)
					console.log("등록 성공")
//					btn.classList.add("icon-commu-likes-fill")
//					btn.classList.remove("icon-commu-likes")
					
				}
	            
	            
	        }
	           if (el.classList.contains("icon-commu-likes-fill")) {
			        
				    console.log("접속한 사람 : " + el.dataset.currentid);
				    console.log("postId: " + el.dataset.post);
				    
				    let delurl = `/api/commu/likes/posts/${el.dataset.post}`;
				    
				        let response = await fetch(delurl, {
				            method: 'DELETE',
				        });
				        
				        if(response.ok){
							console.log("삭제성공")
						}
					
				}
	    });	
   } 


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
            console.log("btnPost");
			
	    	let commentContent = commentBox.querySelector(".comment-content").value;
            console.log(commentContent);
	    	
	        let url = `/api/commus/comments`; //POST
	        
			fetch(url, {
	            method: 'POST',
	            headers: {
	            	'Content-Type': 'application/json',
	            },
	            body: JSON.stringify({ 
	                userId: commentBox.dataset.userid,
	                postId: commentBox.dataset.postid,		
					content: commentContent 
					
				}),
				
	        })
	        if (!response.ok) {
	            console.error('댓글을 등록하는 중에 문제가 발생했습니다.');
	            return;
        }

        let data = await response.json();

        if (data.success) {
            alert("댓글이 성공적으로 등록되었습니다.");
				window.location.href = `/commu/post/detail?&post-id=${el.dataset.post}`;
        } else {
            alert("댓글 등록에 실패했습니다.");
        }
    } 
		

		
		
		
		
});