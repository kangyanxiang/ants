/**
 * Created by Shin on 2016/06/24.
 * 管理员账号-编辑 accountAdminEditController
 */
indexApp.controller('accountAdminEditController', function($scope, $location, accountService, locationService){
    /**
     * validateFail 检验编辑管理员账号
     * @param $scope
     * @author 沈亚芳
     * @date 2016/06/27
     */
    function validateFail($scope){
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
     * adminEdit 编辑管理员账号的信息
     * @author 沈亚芳
     * @date 2016/06/27
     */
    $scope.adminEdit = function (){
        if(validateFail($scope)){
            return;
        }
        accountService.adminEdit($scope);
    };


    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2016/06/27
     */
    function init(){
        // 从访问地址获取id
        var id = $location.search()['id'];

        accountService.projectList($scope);
        
        // 根据id来获取要编辑的账号的信息
        accountService.getAccountAdminById($scope, id);

        //初始化Tips
        accountService.initTipsForAddAdminCountChange();
    }
    init();
});