/**
 * Created by Shin on 2015/12/1.
 * 账号管理：客户侧分配权限 permissionAssignCustomerController
 */
indexApp.controller('permissionAssignCustomerController', function($scope,$location,accountService){

    /**
     * permissionAssign 分配权限的保存（客户侧）
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
        var userName = $location.search()['userName'];
        var customerName = $location.search()['customerName'];
        $scope.id = id;
        $scope.userName = userName;
        $scope.customerName = customerName;

        // 根据id来获取要编辑的账号的信息
        accountService.getAccountById($scope, id);
        // 根据当前登录账号来查找权限-左侧可选权限的数据
        accountService.getPermission($scope);
    }
    init();
});