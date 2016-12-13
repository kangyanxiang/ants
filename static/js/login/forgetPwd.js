var userName, cellphone, userId, authCode, newpwd, newpwdq;
var forgetPwdPage = {
    SMSTIME: null,
    SMSSECO: 60,
    init : function(){
        var _this = this;
        _this.initTips();
        _this.sendSms();//绑定事件
        _this.next();
        _this.commit();
    },
    initTips : function(){//初始化tips
        initTips($('#userName'));
        initTips($('#cellphone'));
        initTips($('#authCode'));
        initTips($('#newpwd'));
        initTips($('#newpwdq'));
    },
    sendSms : function(){
        var _this = this;
        if(_this.SMSTIME!=null) window.clearTimeout(_this.SMSTIME);

        $('#sendSms').on('click', function(){

            if($(this).hasClass('disabled')){
                //_this.showMessage('请等待');
                return false;
            }

            var $userName = $('#userName');
            userName = $userName.val();
            if(userName === null || userName === undefined || $.trim(userName) === ''){
                updateTips($userName,'用户名不能为空！');
                showTips($userName);
                return;
            }

            var $cellphone = $('#cellphone');
            cellphone = $cellphone.val();
            if (cellphone === null || cellphone === undefined || $.trim(cellphone) === '') {
                updateTips($cellphone, '手机号不能为空！');
                showTips($cellphone);
                return;
            }
            if (!chkString(cellphone, defrules.cellphone)) {
                updateTips($cellphone, '手机号格式不正确，请输入11位以1开头符合手机号码规则的数字');
                showTips($cellphone);
                return;
            }

            $(this).attr('disabled',true);
            $(this).css('cursor', 'none');
            _this.resetSendSMS();

            var checkUserUrl = '/authcode/send?userName=' + userName + '&cellphone=' + cellphone;
            $.ajax({
                type: 'GET',
                url: checkUserUrl,
                dataType: 'json',
                success: function(data){
                    if(data.result == 'OK'){
                        userId = data.data;
                        $('#next').removeAttr('disabled');
                        $('#sendSms').css('cursor', 'pointer');
                    } else {
                        $('#userError').text(data.message);
                        $('#next').attr('disabled',true);
                    }
                }
            });
        });
    },
    resetSendSMS : function () {
        var _this = this;
        var $sendSms = $('#sendSms');
        if(_this.SMSSECO > 0){
            _this.SMSSECO = _this.SMSSECO-1;
            $sendSms.html('倒计时'+_this.SMSSECO+'秒');
            _this.SMSTIME = window.setTimeout(function(){_this.resetSendSMS();}, 1000);
        }else{
            window.clearTimeout(_this.SMSTIME);
            _this.SMSSECO = 60;
            $sendSms.removeAttr('disabled');
            $(this).css('cursor', 'pointer');
            $sendSms.html('获取验证码');
        }
    },
    next : function(){
        $('#next').on('click', function(){
            var $authCode = $('#authCode');
            authCode = $authCode.val();
            if(authCode === null || authCode === undefined || $.trim(authCode) === ''){
                updateTips($authCode,'验证码不能为空！');
                showTips($authCode);
                return;
            }

            var $cellphone = $('#cellphone');
            cellphone = $cellphone.val();
            if (cellphone === null || cellphone === undefined || $.trim(cellphone) === '') {
                updateTips($cellphone, '手机号不能为空！');
                showTips($cellphone);
                return;
            }
            if (!chkString(cellphone, defrules.cellphone)) {
                updateTips($cellphone, '手机号格式不正确，请输入11位以1开头符合手机号码规则的数字');
                showTips($cellphone);
                return;
            }

            if(isBlank(userId)){
                userId = '';
            }
            var nextUrl = '/authcode/iscorrect?cellphone=' + cellphone + '&authCode=' + authCode + '&userId=' + userId;
            $.ajax({
                type: 'GET',
                url: nextUrl,
                dataType: 'json',
                success: function(data){
                    if(data.result == 'OK'){
                        $('#first').hide();
                        $('#second').show();
                    } else {
                        $('#userError').text(data.message);
                    }
                }
            })
        });
    },
    commit : function(){
        $('#commit').on('click', function(){
            var $newpwd = $('#newpwd');
            newpwd= $newpwd.val();
            if(newpwd === null || newpwd === undefined || $.trim(newpwd) === ''){
                updateTips($newpwd,'新密码不能为空！');
                showTips($newpwd);
                return;
            }
            if (!chkString(newpwd, defrules.passwordNew)) {
                updateTips($newpwd, "密码必须由1-50位字符组成，包含字母、数字、下划线、连接符、@、$");
                showTips($newpwd);
                return;
            }

            var $newpwdq = $('#newpwdq');
            newpwdq = $newpwdq.val();
            if(newpwdq === null || newpwdq === undefined || $.trim(newpwdq) === ''){
                updateTips($newpwdq,'确认密码不能为空！');
                showTips($newpwdq);
                return;
            }
            if($.trim(newpwdq) != $.trim(newpwd)){
                updateTips($newpwdq,'确认密码与新密码输入不一致！');
                showTips($newpwdq);
                return;
            }

            if(isBlank(userName)){
                userName = '';
            }

            var commitUrl = '/authcode/editpwd?userName=' + userName +'&newpwd=' + newpwd + '&newpwdq=' + newpwdq;
            $.ajax({
                type: 'GET',
                url: commitUrl,
                dataType: 'json',
                success: function(data){
                    if(data.result == 'OK'){
                        $('#pwdError').text('密码修改成功！正在跳转至登录页...').css('color', 'green');
                        var linkUrl = '/auth/login';
                        setTimeout(function(){
                            window.location.replace(linkUrl);
                        }, 2000);
                    } else {
                        $('#pwdError').text(data.message);
                    }
                }
            })
        });
    }
};
$(window).ready(function(){
    forgetPwdPage.init();
});