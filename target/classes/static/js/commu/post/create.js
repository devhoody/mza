
let uplodaPhoto = document.querySelector(".upload-photo");
let input = document.querySelector("input");
let preview = document.querySelector(".preview");

//	fileInput.oninput = function(e){
//	    console.log("파일입력 ");
//	
//	    const files = e.target.files;
//	    let text = "";
//	
//	    for(const file of files){
//	        text += `${file.name}: ${file.type || '알수없음'}\n`;
//	    }
//	
//	    let img = files[0];
//	
//	    console.log(text);
	
/* ---------- 입력한 파일 타입제한 ( 이미지만 받기 ) ---------- */

//	    if(img.type.indexOf("image/") != 0){
//	        console.log("이미지파일만 입력이 가능합니다.");
//	        return;
//	    }
/* ---------- 이미지 출력하기(미리보기 테스트) ---------- */


input.addEventListener("change", updateImageDisplay);

function updateImageDisplay() {
  while (preview.firstChild) {
    preview.removeChild(preview.firstChild);
  }

  const curFiles = input.files;
  if (curFiles.length === 0) {
    const para = document.createElement("p");
    para.textContent = "No files currently selected for upload";
    preview.appendChild(para);
  } else {
    const list = document.createElement("ol");
    preview.appendChild(list);

    for (const file of curFiles) {
      const listItem = document.createElement("li");
      const para = document.createElement("p");
      if (validFileType(file)) {
        para.textContent = `File name ${file.name}, file size ${returnFileSize(
          file.size,
        )}.`;
        const image = document.createElement("img");
        image.src = URL.createObjectURL(file);

        listItem.appendChild(image);
        listItem.appendChild(para);
      } else {
        para.textContent = `File name ${file.name}: Not a valid file type. Update your selection.`;
        listItem.appendChild(para);
      }

      list.appendChild(listItem);
    }
  }
}

// https://developer.mozilla.org/en-US/docs/Web/Media/Formats/Image_types
const fileTypes = [
  "image/apng",
  "image/bmp",
  "image/gif",
  "image/jpeg",
  "image/pjpeg",
  "image/png",
  "image/svg+xml",
  "image/tiff",
  "image/webp",
  "image/x-icon",
];

function validFileType(file) {
  return fileTypes.includes(file.type);
}

function returnFileSize(number) {
  if (number < 1024) {
    return `${number} bytes`;
  } else if (number >= 1024 && number < 1048576) {
    return `${(number / 1024).toFixed(1)} KB`;
  } else if (number >= 1048576) {
    return `${(number / 1048576).toFixed(1)} MB`;
  }
}


/* ---------- 이미지 출력하기(파일 읽기) ---------- */

//	    let reader = new FileReader();
//		
//		    reader.addEventListener("load", () => {
//		            let img = fileInput.nextElementSibling
//		            img.src = reader.result;
//		            // preview.src = reader.result;
//		        },
//		        false,
//	   		);
//		    reader.readAsDataURL(img);
//    		console.log(text);

			// 파일을 바이너리로 아직 읽은 건 아니라 
			// 이제 바이너리로 읽으려면 도구가 필요해 = filereader 

/* ---------- form 입력값 받기 ---------- */

//	let form = document.querySelector(".post-create form");
//	let areaId = form.querySelector("select[name=area_id]").value; // 이건 html에서 가져온거.
//	let title = form.querySelector("textarea[name=title]").value;
//	let content = form.querySelector("textarea[name=content]").value;
//	
//	console.log(areaId, title, content);


/* ---------- 이미지 저장하기(---------- */

//	    let savedImg = fileInput.files;
//	
//	    console.log(savedImg.name);
//	    
//	    
//    	for(let img of files)
//		savedImg.append("imgFiles", img);// entity 속성명과 안겹치게하자.
//	
//		let request = new XMLHttpRequest(); 
//			request.onload = () => {
//				 		let newOne = JSON.parse(request.responseText);
//			};
//		request.open("POST", "/api/posts");
//		request.send(formData);
//	}
//	
	
	
	
	
	
