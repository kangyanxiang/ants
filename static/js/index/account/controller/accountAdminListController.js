/**
 * Created by Shin on 2016/06/24.
 * 管理员账号-列表 accountAdminListController
 */
indexApp.controller('accountAdminListController', function($scope, accountService){
    /**
     * adminList 管理员账号列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/06/24
     */
    function list (){
        accountService.adminList($scope);
    }
    $scope.list = list;
    list();


    /**
     * delete 逻辑删除账号（管理员账号列表）
     * @param id
     * @author 沈亚芳
     * @date 2016/06/27
     */
    $scope.delete = function(id) {
        jDialog.confirm('删除管理员账号', '<div class="rows"><label class="w100">您确定删除该管理员账号吗？</label></div>', function(){
            accountService.delete($scope, id);
            jDialog.hide();
        });
    };


    /**
     * pwdReset 重置密码（管理员账号列表）
     * @param id
     * @author 沈亚芳
     * @date 2016/06/27
     */
    $scope.pwdReset = function(id){
        jDialog.confirm('密码重置', '<div class="rows"><label class="w100">您确定要将该账号的密码重置吗？</label></div>', function(){
            accountService.pwdReset($scope, id);
            jDialog.hide();
        });
    };


    /**
     * resetWidth 重置列表因fixed引起的宽度问题
     * @author 沈亚芳
     * @date 2016/06/24
     */
    resetWidth();
});
