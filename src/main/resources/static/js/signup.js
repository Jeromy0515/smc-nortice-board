function sendUserData() {
	
	var userData = {
		name: $("#floatingName").val(),
		id: $("#floatingId").val(),
		password: $("#floatingPassword").val()
	}
	
	$.ajax({
		url: location.origin + "/create_account",
		data: userData,
		type: "POST",
		success: function(data) {
			console.log(data)
			location.href = "/auth/login"
		},
		error: function(error) {
			console.log(error)
		}
	})
	
}

function checkOverlap() {
	
	$.ajax({
		url: location.origin + "/create_account/overlap",
		data: {
			"id": $("#floatingId").val()
		},
		type: "POST",
		success: function(data){
			console.log(data)
			
			const isOverlap = JSON.parse(data).isOverlap
			console.log(isOverlap)
			
			if($("#floatingId").val().length < 5) {
				
				$("#idInvalidFeedback").text("아이디는 5글자 이상 입력하셔야 합니다.")
				$("#floatingId").attr("class","form-control is-invalid")
				
				return
			}
			
			if(isOverlap === 'true'){
				$("#idInvalidFeedback").text("이미 존재하는 아이디입니다.")
				$("#floatingId").attr("class","form-control is-invalid")
			}else{
				$("#floatingId").attr("class","form-control is-valid")
			}
			
		},
		error: function(error){
			console.log(error)
		}
	})
	
}

function signup(){
	
	var password = $("#floatingPassword").val()
	var passwordCheck = $("#floatingPasswordCheck").val()
	
	console.log("password : " + password + " password check : " + passwordCheck)	
	
	if($("#floatingId").attr("class") != "form-control is-valid") {
		
		$("#idInvalidFeedback").text("아이디 중복확인을 하셔야합니다.")
		$("#floatingId").attr("class","form-control is-invalid")
		
		return
	}
	
	if(!/^(?=.*[A-z])(?=.*[^A-z1-9])(?=.*[0-9]).{8,}/.test(password)){
		
		$("#passwordInvalidFeedback").text("8자 이상, 하나 이상의 문자, 숫자, 특수문자가 포함되어야 합니다.")
		$("#floatingPassword").attr("class","form-control is-invalid")
		
		return	
	}
	
	
	if(password != passwordCheck){
		
		$("#passwordCheckInvalidFeedback").text("비밀번호가 일치하지 않습니다.")
		$("#floatingPasswordCheck").attr("class","form-control is-invalid")
		
		return
	}
	
	sendUserData()
	
}