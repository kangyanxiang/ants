//切换验证码
function change() {
	var img =document.getElementById("authCodeImg");
	img.src=img.src+"?";
}

var loginpage = {
	init : function(){//初始化函数
		var _this = this;
		_this.initTips();//初始化tips
		_this.bindEvent();//绑定事件
	},
	initTips : function(){//初始化tips
		initTips($('#username'));//用户名
		initTips($('#password'));//密码
		initTips($('#code'));//验证码
	},
	bindEvent : function(){//绑定事件
		//var _this = this;
		//alert('bindEvent');
		$(".login").bind('click',function(){//登录函数
			//alert('loginFunc');
			//1. 校验用户名、密码是否正确
			var $userName = $('#username');//用户名
			var userName = $userName.val();
			if(userName === null || userName === undefined || $.trim(userName) === ''){
				updateTips($userName,'用户名不能为空！');
				showTips($userName);
				return;
			}

			var $password = $('#password');//密码
			var password = $password.val();
			if(password === null || password === undefined || $.trim(password) === ''){
				updateTips($password,'密码不能为空！');
				showTips($password);
				return;
			}
			//2. 校验验证码是否正确
			var $code = $('#code');
			var code = $code.val();
			if(code === null || code === undefined || $.trim(code) === '' ){
				updateTips($code,'验证码不能为空！');
				showTips($code);
				return;
			}
			var url = '/auth/validationcode?code=' + code;
			$.ajax({
				type: 'POST',
				url: url,
				dataType: 'json',
				success: function(data){
					if(data.result == 'OK'){
						//3. 提交表单
						$("#loginForm").submit();
					} else {
						updateTips($code,data.message);
						showTips($code);
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown){
					//alert(XMLHttpRequest.status);
				}
			});
		});
		$('#username').bind('focus',function(){
			$("#errorDiv").hide();
		});
		$('#password').bind('focus',function(){
			$("#errorDiv").hide();
		});
		$('#code').bind('focus',function(){
			$("#errorDiv").hide();
		});
		// 回车登录
            $('#username').on('keypress', function(e){
                if(e.keyCode == 13){ // Enter
                    $(this).blur();
                    $('.login').click();
                }
            });
            $('#password').on('keypress', function(e){
                  if(e.keyCode == 13){ // Enter
                      $(this).blur();
                      $('.login').click();
                  }
              });
            $('#code').on('keypress', function(e){
                  if(e.keyCode == 13){ // Enter
                      $(this).blur();
                      $('.login').click();
                  }
              });
	}
};
$(window).ready(function(){
	loginpage.init();
});