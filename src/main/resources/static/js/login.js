function login() {
	
	var userData = {
		id: $("#floatingInput").val(),
		password: $("#floatingPassword").val()
	}
	
	$.ajax({
		url: "/login",
		data: userData,
		type: "POST",
		success: function(data) {
			console.log(data)
			
			const isLogin = JSON.parse(data).isLogin
			
			if(isLogin === 'true')
				location.href = '/home'
		},
		error: function(error) {
			console.log(error)	
		}
	})
	
}

