/**
 * Created by Shin on 2015/11/24.
 * 登录后修改基本信息 accountInfoModifyController
 */
indexApp.controller('accountInfoModifyController', function($scope, $location, accountService){

    /**
     * validateFail 检验修改账号基本信息功能所涉及到的参数是否验证通过
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/27
     */
    function validateFail($scope){
        //联系人校验
        if (accountService.validateContactPersonFail($scope)){
            return true;
        }
        //联系方式校验
        if (accountService.validateContactWayFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * infoModify 修改账号基本信息
     * @author 沈亚芳
     * @date 2015/11/27
     */
    $scope.infoModify = function (){
        if(validateFail($scope)){
            return;
        }
        accountService.infoModify($scope);
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
        accountService.initTipsForPersonMobileChange();
    }
    init();
});