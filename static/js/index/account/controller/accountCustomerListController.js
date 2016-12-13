/**
 * Created by Shin on 2015/11/25.
 * 客户侧账号 accountCustomerListController
 */
indexApp.controller('accountCustomerListController', function($scope, accountService, customerService) {

    /**
     * pwdReset 重置密码（电信侧和客户侧账号列表）
     * @param id
     * @author 沈亚芳
     * @date 2015/11/26
     */
    $scope.pwdReset = function(id){
        jDialog.confirm('密码重置', '<div class="rows"><label class="w100">您确定要将该账号的密码重置吗？</label></div>', function(){
            accountService.pwdReset($scope, id);
            jDialog.hide();
        });
    };


    /**
     * customerList 客户侧账号列表
     * @author 沈亚芳
     * @date 2015/11/26
     */
    function list (){
        accountService.customerList($scope);
    }
    $scope.list = list;
    list();


    /**
     * resetWidth 重置列表因fixed引起的宽度问题
     * @author 沈亚芳
     * @date 2015/11/26
     */
    resetWidth();

    function init() {
        // 客户下拉列表
        customerService.customerSelect2('customerName');
    }

    init();
});