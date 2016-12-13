/**
 * Created by zhuxuehuang on 16-4-27.
 */

indexApp.controller('noperceptionEditController', function($scope, $location, noperceptionService) {

    // 无感应id
    var id = $location.search()['id'];

    // 实始化
    function init() {
        // 初始化tips
        noperceptionService.initTips();
        // 获取无感知数据
        noperceptionService.noperceptionShow($scope, id);
    }
    init();

    // 无感知编辑
    $scope.noperceptionEdit = function () {
        // 参数校验
        if(noperceptionService.validateNoperceptionFail($scope, 'edit')) {
            return ;
        }
        noperceptionService.noperceptionEdit($scope, id);
    };
});