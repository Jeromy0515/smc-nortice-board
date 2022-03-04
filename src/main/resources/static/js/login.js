function login() {
	
	var userData = {
		id: $("#floatingId").val(),
		password: $("#floatingPassword").val()
	}
	
	$.ajax({
		url: location.origin + "/auth/login",
		data: userData,
		type: "POST",
		success: function(data) {
			console.log(data)
			
			const isLogin = data.isLogin
			console.log(document.cookie)
			if(isLogin === 'true'){
				location.href = '/home'
			}else {
				
				$("#idInvalidFeedback").text("아이디 혹은 패스워드가 일치하지 않습니다.")
				$("#passwordInvalidFeedback").text("아이디 혹은 패스워드가 일치하지 않습니다.")
				$("#floatingId").attr("class", "form-control is-invalid")
				$("#floatingPassword").attr("class", "form-control is-invalid")
				
			}
		},
		error: function(error) {
			console.log(error)	
		}
	})
	
}

