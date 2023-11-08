window.addEventListener("load", function(){
	// 지역별 카테고리 조회
	let commuLocFilterSection = document.querySelector(".location");
	let locUl = commuLocFilterSection.querySelector("ul");
	let currentLoc = locUl.querySelector("li:first-child a");
	
	let content = document.querySelector(".post-layout ");
	let bottom = document.querySelector(".bottom");
	
	// 검색 버튼 
    let findButton = document.querySelector(".query-filter .btn-search");
	// 삭제 버튼 (-> 나중에 모달로 바꿔야...! )	
	let commuDeleteList = document.querySelectorAll(".commu-delete");
      console.log("commuDeleteList :  "+commuDeleteList);


 		

// ------------------------- 포스팅 삭제  -------------------------

 commuDeleteList.forEach((commuDelete) => {
      commuDelete.addEventListener("click", async (e) => {
		     e.preventDefault();
		      let el = e.target;
		      console.log("클릭클릭 ");
			
			let url =`/api/commus/posts/${el.dataset.post}`;
//		      console.log(url);
//		      console.log(el.dataset.post);
		
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


// ------------------------- 검색하기 -------------------------
    findButton.onclick = async function (e) {
	
    let inputQuery = document.querySelector(".query-filter .input-query");
        e.preventDefault();

        let query = inputQuery.value;	
		console.log(query)
        
		let response = await fetch(`/api/commus?q=${query}`);
		let json = await response.json();
		bind(json);
		console.log(json)

	}
	
// ------------- 좋아요 클릭 -> EventListener 페이지에 존재하는 요소로 받기! -------------------
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

		
// ------------- 지역카테고리 선택시 -> query전달 함수 -> data json으로 전달 -------------

	locUl.onclick = async (e)=> {
		e.preventDefault();
		
		let el = e.target;
		
	    let currentUserId = el.dataset.currentUserId;
        console.log("currentUserId= "+currentUserId);
		 if(!el.classList.contains("location"))
		 return;
		console.log("location 클릭!");
	
	
		currentLoc.classList.remove("selected");
		el.classList.add("selected");
		currentLoc = el;
		
        console.log("el.href="+el.href);
        console.log("el.dataset.id= "+el.dataset.id);
        
        let url = `/api/commus?loc=${el.dataset.id}`
        if (el.dataset.id == undefined) {
			url = `/api/commus?`
		}
		
        let response = await fetch(url);
        let json = await response.json();
        bind(json); 		


};
	
	
	
// ------------- 데이터 요청 후 응답기다리는...! -------------

	function bind(postList){
		
        content.innerHTML = ""; // section을 위한 DOM 객체를 직접 생성해서 append 한다.

        for (let p of postList){
            let iconLike = p.isLike ? "icon-commu-likes-fill" : "icon-commu-likes";

            let template = `
	            
			<div class = "post-card container md:container" >

	                    <section class ="top"> 
	                        <h1 class="d:none"> 작성자 정보 & 작성시간 & 확장메뉴</h1>
	
	                        <section class="member_info">
	                            <h1 class="d:none"> 작성자 등급 & 닉네임</h1>
	
	                            <div class="badge">
	                                <img src="/image/icon/grade2.svg" style="height:20px;" alt="등급2" /> 
	                            </div>
	                                
	                            <div class="nickname" >
	                                <span> 
	                                    ${p.alias}
	                                </span>
	                            </div>
	                        </section> 	<!-- member_info-->
	
	                        <section class="date_info md:date_info">
	                            <h1 class="d:none"> 작성 시간 & 확장메뉴</h1>
	
	                            <div>
	                                <span>    
	                                   ${p.createdDate}   
	                                </span> 
	                            </div>

	                            
	                             <div > 
                					<button class="commu-delete" 
                							data-post="${p.postId}"
                							type="submit" value="" >  </button>
	                            </div>
	                        </section><!-- date_info -->
	                    </section><!-- top -->
	
						<a
						id="link"
						href="post/detail?post-id=${p.postId}"> <!-- detail 조회 링크 -->
	
		                    <section class ="middle"
		                    		data-post="${p.postId}"
		                    		> 
		                    		
		                        <h1 class="d:none"> 게시글-제목, 내용, 사진  </h1>
	
	                            <section class="title">
	                                <h1 class="d:none" > 제목</h1>
	                                <p> ${p.title} </p>
	                            </section><!-- title -->
	
	                            <section class="content">
	                                <h1 class="d:none"> 내용</h1>
	                                <p> ${p.content} </p>
	                            </section> <!-- content -->
	
	                            <section class="picture md:picture">
	                                <h1 class="d:none"> 사진</h1>
	                                <picture>
	                                    <img 
	                                    src="/image/ranking1.JPG"
	                                    th:src="@{/css/image/commu/post/{img} (img=${p.img1})}" 
	                                    		alt="Responsive Image1">
	                                    		
	                                </picture>  
	                            </section><!-- picture -->
	                    </section><!-- middle -->
	                    
					</a> <!-- detail 조회 링크 -->

	                    <section class ="bottom"> 
	                        <h1 class="d:none"> 조회수 좋아요 댓글 </h1>
	
	                            <section class="view">
	                                <h1 class="d:none"> 조회수 </h1>
	
	                                    <div>
	                                        <span class="deco icon-commu-view icon-size:1 icon-color:main" href=""> 조회수</span>
	                                    </div>
	
	                                    <div>
	                                        <span>${p.hit} </span>
	                                    </div>
	                            </section><!-- view -->
	
	                            <section class="like">
	                                <h1 class="d:none"> 좋아요 </h1>
	
	                                    <div>
	                                        <button class=" ${iconLike} icon icon-size:1 icon-hover icon-pointer icon-color:main" 
                                                    classappend ="${p.isLike}? 'icon-commu-likes-fill': 'icon-commu-likes' "
                                    				data-post="${p.postId}"
	                                                type="button"> 좋아요</button>
	                                    </div>
	
	                                    <div>
	                                        <span> ${p.postLikeCount} </span>
	                                    </div>
	                            </section><!-- like -->
	        	                    </section><!— like —>
	            
	                            <section class="comment">
	                                <h1 class="d:none"> 댓글 </h1>
	
	                                    <div>
	                                        <span class="deco icon-comment icon-size:1 icon-color:main" href=""> 댓글</span>
	                                    </div>
	
	                                    <div>
	                                        <span> ${p.commentCount} </span>
	                                    </div>
	                            </section><!-- comment -->
	                            
	                    </section><!-- bottom -->
                  </div>  
            `;
	        

	        content.insertAdjacentHTML("beforeend", template); // 때려부수지 않고 새로 짓는거
	        }
	    }
});	