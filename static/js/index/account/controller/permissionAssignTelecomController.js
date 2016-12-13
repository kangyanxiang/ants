/**
 * Created by Shin on 2015/11/27.
 * 账号管理之分配权限 permissionAssignTelecomController
 */
indexApp.controller('permissionAssignTelecomController', function($scope,$location,accountService){

    /**
     * permissionAssign 分配权限的保存（电信侧）
     * @author 沈亚芳
     * @date 2015/12/01
     */
    $scope.permissionAssign = function(){
        accountService.permissionAssign($scope);
    };


    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2015/12/01
     */
    function init(){
        // 从访问地址获取id
        var id = $location.search()['id'];
        $scope.id = id;
        // 根据id来获取要编辑的账号的信息
        accountService.getAccountById($scope, id);
        // 根据当前登录账号来查找权限-左侧可选权限的数据
        accountService.getPermission($scope);
    }
    init();
});