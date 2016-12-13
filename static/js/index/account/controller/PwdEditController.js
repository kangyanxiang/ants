/**
 * Created by Shin on 2015/11/24.
 * 登录后修改密码 pwdEditController
 */
indexApp.controller('pwdEditController', function($scope, $location, accountService) {

    /**
     * validateFail 检验修改密码功能所涉及到的参数是否验证通过
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/27
     */
    function validateFail($scope){
        //旧密码的校验
        if(accountService.validateOldPasswordFail($scope)){
            return true;
        }
        //新密码的校验
        if(accountService.validateNewPasswordFail($scope)){
            return true;
        }
        //确认密码的校验
        if(accountService.validateSurePasswordFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * pwdEdit 修改密码功能
     * @author 沈亚芳
     * @date 2015/11/27
     */
    $scope.pwdEdit = function(){
        //旧密码、新密码、确认密码的校验是否通过
        if(validateFail($scope)){
            return;
        }
        accountService.pwdEdit($scope);
    };


    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2015/11/27
     */
    function init(){
        // 获取当前登录账号的信息
        accountService.getCurUserInfo($scope);
        // 初始化校验密码时错误的提示信息（功能：修改密码）
        accountService.initTipsForPwdChange();
    }
    init();
});
