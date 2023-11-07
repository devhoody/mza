window.addEventListener("load", function(){
    let edit = document.querySelector(".edit");
    let idQuery = edit.querySelector(".id")
    let editBtn = edit.querySelector(".store-edit-button");
    
    let inputGrade = document.getElementById("grade");
 	let inputAlias = document.getElementById("alias");
 	let inputEmail = document.getElementById("email");
 	let inputPwd = document.getElementById("pwd");
 	let inputName = document.getElementById("name");
 	let inputPhone = document.getElementById("phone");
 	let inputLocation = document.getElementById("locCateList");

  let gradeSelect = document.querySelector("#grade");
  let locationSelect = document.querySelector("#locCateList");

  gradeSelect.onclick = function (e) {
    e.preventDefault();
    console.log("등급 선택창 클릭");

    let gradeQuery = gradeSelect.value;
    console.log(gradeQuery);
    inputGrade.value = gradeQuery;

    /*		inputGrade.addEventListener("change", function(){
			console.log(inputGrade.value);
			let gradeCurrent = document.getElementById("gradeCurrent");
			gradeCurrent.value=inputGrade.value;
			console.log(gradeCurrent.value);
		})*/
  };

  locationSelect.onclick = function (e) {
    e.preventDefault();
    console.log("지역 선택창 클릭");

    let locationQuery = locationSelect.value;
    console.log(locationQuery);
    inputLocation.value = locationQuery;
  };

  editBtn.onclick = async function (e) {
    e.preventDefault();

    let id = idQuery.value;
    let gradeValue = inputGrade.value;
    let aliasValue = inputAlias.value;
    let emailValue = inputEmail.value;
    let pwdValue = inputPwd.value;
    let nameValue = inputName.value;
    let phoneValue = inputPhone.value;
    let locationValue = inputLocation.value;

    console.log("test");
    console.log(id);
    console.log(gradeValue);
    console.log(aliasValue);
    console.log(emailValue);
    console.log(nameValue);
    console.log(phoneValue);
    console.log(locationValue);

    let data = {
      id: id,
      gradeId: gradeValue,
      alias: aliasValue,
      email: emailValue,
      pwd: pwdValue,
      name: nameValue,
      phone: phoneValue,
      locationId: locationValue,
    };
    let url = `/admin/api/users/${id}`;

    let response = await fetch(url, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    });
    console.log(response);
  };
});
