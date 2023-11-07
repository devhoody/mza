window.addEventListener("load", function(){
	
	let postcreate = document.querySelector(".post-create");
	let posttitle = postcreate.querySelector(".posting-title");
	let title = posttitle.querySelector(".title");
	
	title.oninput = (e) =>{
			e.preventDefault();
			
			let el = e.target;
			
			console.log(el.value);

	}
});
