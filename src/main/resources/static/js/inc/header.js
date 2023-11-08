window.addEventListener("load", function() {
	let menuNav = document.querySelector(".main-menu")
	let webUL = menuNav.querySelector("ul");
	
	let formSection = document.querySelector(".query-filter")
	let content = document.querySelector(".menu-card-list .content")
	let currentCategory = categoryUL.querySelector("li:first-child a") 
	
	categoryUL.onclick = function(e){
		e.preventDefault();

		let el = e.target;

		if(el.tagName != "A")
			return;
		
		console.log(el.className);
		//el.className += " current"
		
		currentCategory.classList.remove("current");
		el.classList.add("current");
		currentCategory = el;
		
		// 업무로직
		console.log(el.dataset.id);
		
		// /api/menus?c=
		let request = new XMLHttpRequest();
		request.open("GET", `/api/menus?c=${el.dataset.id}`, true);
		request.onload = function() {
						
			//console.log(request.responseText);
			let list = JSON.parse(request.responseText);		
			//alert(request.responseText);		
			
			content.innerHTML = "";
			for(let m of list){
				let template = `	
				<section class="menu-card">
				<h1>
					<a class="heading-3" href="detail.html"> ${m.korName}</a></h1>
				<h2 class="heading-2 font-weight:normal color:base-5">${m.engName}</h2>
				<div class="price-block d:flex align-items:flex-end">
					<span class="font-weight:bold">${m.price}</span>원
					<span class="soldout-msg ml:auto mr:auto md:d:none">SOLD OUT</span></div>
				<div class="img-block">
					<a href="detail?id=${m.id}"><img src="/image/menu/product/${m.img}"></a>
				</div>
				<div class="like-block d:flex justify-content:flex-end">
					<a class="icon icon-color:base-4 ${iconHeart}">좋아요</a>
					<span class="font-weight:bold ml:1">${m.likeCount}</span>
				</div>
				<div class="pay-block d:flex">
					<a class="icon md:icon:none icon-cart icon-color:base-0 color:base-0 btn-type:icon btn-cart md:btn-type:text"
						href="">담기</a>
					<a class="icon md:icon:none icon-card icon-color:base-0 color:base-0 btn-type:icon btn-card md:btn-type:text"
						href="">주문하기</a>
				</div>
			</section>		
			`;
			
			content.insertAdjacentHTML("beforeend", template);
			}
		};
		request.send();		//send가 버튼
		
	}
	});
