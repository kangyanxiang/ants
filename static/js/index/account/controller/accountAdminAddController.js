/**
 * Created by Shin on 2016/06/24.
 * 管理员账号-创建 accountAdminAddController
 */
indexApp.controller('accountAdminAddController', function($scope, accountService){

    /**
     * validateFail 检验创建管理员账号
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/06/24
     */
    function validateFail($scope){
        //校验账号名
        if(accountService.validateAccountUserNameForAdminFail($scope)){
            return true;
        }
        //校验部门
        if(accountService.validateAccountDeptNameForAdminFail($scope)){
            return true;
        }
        //校验联系人
        if(accountService.validateAccountContactPersonForAdminFail($scope)){
            return true;
        }
        //校验联系方式
        if(accountService.validateAccountContactWayForAdminFail($scope)){
            return true;
        }
        //校验备注
        if(accountService.validateAccountRemarkForAdminFail($scope)){
            return true;
        }
        return false;
    }



    /**
     * adminAdd 创建管理员账号
     * @author 沈亚芳
     * @date 2016/06/24
     */
    $scope.adminAdd = function () {
        if(validateFail($scope)){
            return;
        }
        accountService.adminAdd($scope);
    };


    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2016/06/24
     */
    function init(){
        $scope.account = {};

        //初始化Tips
        accountService.initTipsForAddAdminCountChange();
    }
    init();
});