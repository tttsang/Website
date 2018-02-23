$(function(){
	/*登录*/
	var $oInnerLogin = $('#myModal').find('button.btn-login')
	var $oLoginName = $('#myModal').find('input.input-name')
	var $oLoginPassword = $('#myModal').find('input.input-password')
	var $oLableError = $('#myModal').find('label.label-error')
	$oInnerLogin.click(function(){
		$.ajax({
			type: 'Post',
			url: 'http://bingexxx.net/api/login',
			data: {
				id: $oLoginName.val(),
				password: $oLoginPassword.val()
			},
			success: function(data){
				if (data.code==0) {
					sessionStorage.setItem('admin',true)
					window.location.href = "/admin"
				} else{
					$oLableError.html(data.message)
				}
			},
			error: function(data){
				window.location.href = "/500"
			},
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true
		})
		

	})
	
	/*模态框关闭时输入框内容清空*/
   	$('#myModal').on('hide.bs.modal',
   	function() {
        $oLoginName.val("")
        $oLoginPassword.val("")
        $oLableError.html("")
    })
})
