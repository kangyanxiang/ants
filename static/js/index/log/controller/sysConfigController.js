indexApp.controller('sysConfigController', function(sysConfigService, $scope, $location, customerService, ngDialog) {
    function list(){
    	sysConfigService.list($scope);
    }
    $scope.list = list;
    list();
    //查看
    $scope.show = function(id){
        $scope.id = id;
        ngDialog.open({
            template: 'html/template/system/sysConfigShow.html',
            controller: 'sysConfigShowController',
            title: '系统参数配置详细信息',
            width: '45%',
            scope : $scope
        });
    };
    //删除
    $scope.delete=function(id){
    	jDialog.confirm('删除参数配置', '<div class="rows"><label class="w100">您确定要删除该参数吗？</label></div>', function(){
    		sysConfigService.remove($scope,id);
            jDialog.hide();
        });
    };

    /**
     * resetWidth 重置列表因fixed引起的宽度问题
     * @author 沈亚芳
     * @date 2015/12/08
     */
    resetWidth();
});