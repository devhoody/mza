window.addEventListener("load", function(){
	
	let commuLocFilterSection = document.querySelector(".location");
	let locUl = commuLocFilterSection.querySelector("ul");
	let currentLoc = locUl.querySelector("li:first-child a");
	let content = document.querySelector(".post-layout ");
	
    let findButton = document.querySelector(".query-filter .btn-search");

	let postMiddle = document.querySelectorAll(".middle"); 
	let link = document.querySelector("#link");  // class로 변경하지 말기 !!! 

	let commuDeleteList = document.querySelectorAll(".commu-delete");
      console.log("commuDeleteList"+commuDeleteList);

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
		
	}
	
// ------------- 좋아요 클릭 -> EventListener 페이지에 존재하는 요소로 받기! -------------------
 
  content.addEventListener("click", async function (e) {
        let el = e.target;
        e.preventDefault();

        // 부모 요소인 content 내에서 좋아요 버튼이 클릭되었는지 확인
        // likes -> likes-fill
        if (!el.classList.contains("icon-commu-likes")) 
 			return;
            console.log("postId: " + el.dataset.post);
//		    console.log("userId(로그인한 사용자): " + el.dataset.currentid);
            console.log("like");
            
            
      	let url = `/api/commu/likes`;
        
        let response = await fetch(url, {
			method:'POST',
			headers: {
            	'Content-Type': 'application/json',
            },
            body: JSON.stringify({
//				userId: el.dataset.currentid,
                postId: el.dataset.post
			})
		})
		
	   let newOne = await response.json();
		 console.log(newOne);

        
    });
    
    // likes-fill -> likes
	content.addEventListener("click", function (e) {
        let el = e.target;
        
        e.preventDefault();

        // 부모 요소인 content 내에서 좋아요 버튼이 클릭되었는지 확인
        if (!el.classList.contains("icon-commu-likes-fill")) 
         		 return;
			
            console.log("postId: " + el.dataset.id);
            console.log("likefill");
        
    });
		
		
// ------------- 지역카테고리 선택시 -> query전달 함수 -> data json으로 전달 -------------

	locUl.onclick = async (e)=> {
		e.preventDefault();
		
		let el = e.target;
		
		 if(!el.classList.contains("location"))
		 return;
		console.log("location 클릭!");
	
	
		currentLoc.classList.remove("selected");
		el.classList.add("selected");
		currentLoc = el;
		
        console.log("el.href="+el.href);
        console.log("el.dataset.id= "+el.dataset.id);
        
        let response = await fetch(`/api/commus?loc=${el.dataset.id}`);
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
	
	                            <div>
	                                <a class="icon icon-more-horiz icon-size-commu:2 icon-color:va" href=""> 세로 확장</a>
	                            </div>
	                        </section><!-- date_info -->
	                    </section><!-- top -->
	
	                    <section class ="middle"> 
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
	                                    		
	                                    <img src="/image/ranking2.JPG"
	                                    th:src="@{/css/image/commu/post/{img} (img=${p.img2})}" 
	                                    		alt="Responsive Image2">
	                                    		
	                                    <img src="/image/ranking3.JPG"
	                                    th:src="@{/css/image/commu/post/{img} (img=${p.img3})}" 
	                                    		alt="Responsive Image3">
	                                </picture>  
	                            </section><!-- picture -->
	                    </section><!-- middle -->
	
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
	                                                type="button"> 좋아요</button>
	                                    </div>
	
	                                    <div>
	                                        <span> ${p.postLikeCount} </span>
	                                    </div>
	                            </section><!-- like -->
	            
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

