window.addEventListener("load", function(){
	let commentBox = document.querySelector(".commentbox")
	let btnPost = commentBox.querySelector(".btn-post")
    let content = document.querySelector(".commentgroup"); // 
 	let deleteComment = document.querySelectorAll(".comment-delete");
 	let deletePost = document.querySelectorAll(".post-delete");
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
	        
			let response = fetch(url, {
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
	
	        if (data.success) {
	            alert("댓글이 성공적으로 등록되었습니다.");
					window.location.href = `/commu/post/detail?&post-id=${el.dataset.post}`;
	        } else {
	            alert("댓글 등록에 실패했습니다.");
	        }
    } 
		

// ------------- 데이터 요청 후 응답기다리는...! -------------

	function bind(postList){
		console.log(" --function bind 시작 부분-- ")
		
        content.innerHTML = ""; // section을 위한 DOM 객체를 직접 생성해서 append 한다.

        for (let c of commentList){
            let template = `
            
              <section class="comment-list-box md:comment-list-box">
                        <h2 class="d:none"> 댓글 </h2>
        
                        <section class ="top"> 
                            <h1 class="d:none"> 작성자 정보 & 작성시간 & 확장메뉴</h1>
        
                            <section class="member_info">
                                <h1 class="d:none"> 작성자 등급 & 닉네임</h1>
        
                                <div class="badge">
 								<img src="/css/image/icon/badge${c.gradeId}.svg" style="height:20px;" alt="등급2" />                                     
                                    style="height:20px;" alt="등급2" /> 
                                </div>
                                        
                                <div class="nickname">
                                    <span > 
                                        ${c.alias} 
                                    </span>
                                </div>
                            </section>
        
                            <section class="date_info md:date_info">
                                <h1 class="d:none"> 작성 시간 & 확장메뉴</h1>
                                <div>
                                    <span>    
                                        ${c.createdDate}
                                    </span> 
                                </div>
       							 <!--
                                <div>
                                    <a class="icon  icon-commu-more icon-size-commu:2 icon-color:base-5" href=""> 세로 확장</a>
                                </div>
                                 -->
                                <div > 
									 <!--	 [수정조회] commu/post/edit  http://localhost:8000/commu/post/edit?postId=5-->

                					<a class="commu-edit" 
                						th:href="@{edit?(commentId=${c.commentId})}"
                							th:classappend="${#authentication.principal.id == c.userId} ? 'icon icon-size:0 icon-color:base-4 icon-commu-edit' : 'd:none'"
                							th:data-post="${c.postId}"
                							value="" > </a>
	                            </div>

                                 
                                 <div > 
	                           		
                           		<button class="commu-delete commu-delete"
            					th:classappend="${#authentication.principal.id ==c.userId} ? 'icon icon-size:0 icon-color:base-4 icon-commu-delete' : 'd:none'" 
            							th:data-post="${c.postId}"
            							th:data-postUser="${c.userId}"
            							type="submit" value="" > </button>
                           		</div>
                            </section>
                            
                        </section> <!-- top 끝 -->
        
                        <section class="middle">
                            <h1 class="d:none"> 댓글 내용</h1>
                            <p> ${c.content} </p>
                        </section> <!-- middle 끝 -->
	   
            `;
	        

	        content.insertAdjacentHTML("beforeend", template); // 때려부수지 않고 새로 짓는거
	        }
	    }
		
		
		
});