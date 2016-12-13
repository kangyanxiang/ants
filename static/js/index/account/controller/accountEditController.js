/**
 * Created by Shin on 2015/11/27.
 * 编辑电信侧管理员 accountEditController
 */
indexApp.controller('accountEditController', function($scope, $location, accountService){

    /**
     * validateFail 检验编辑账号（电信侧）功能所涉及到的参数是否验证通过
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/30
     */
    function validateFail($scope){
        //校验联系人
        if(accountService.validateAccountContactPersonFail($scope)){
            return true;
        }
        //校验联系方式
        if(accountService.validateAccountContactWayFail($scope)){
            return true;
        }
        //校验备注
        if(accountService.validateAccountRemarkFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * edit 编辑账号信息（电信侧管理员）
     * @author 沈亚芳
     * @date 2015/11/30
     */
    $scope.edit = function (){
        if(validateFail($scope)){
            return;
        }
        var projectId = $scope.account.projectId;
        if(projectId == null){
        	jDialog.alert('编辑账号:', '项目名称不能为空');
        	return;
        }
        accountService.edit($scope);
    };


    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2015/11/30
     */
    function init(){
        // 从访问地址获取id
        var id = $location.search()['id'];
        
        accountService.projectList($scope);
        
        // 根据id来获取要编辑的账号的信息
        accountService.getAccountById($scope, id);
        // 初始化Tips
        accountService.initTipsForAddTelecomAccountChange();
    }
    init();
});