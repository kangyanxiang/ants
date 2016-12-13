/**
 * Created by zhuxuehuang on 16-4-27.
 */

indexApp.controller('noperceptionAddController', function($scope, customerService, noperceptionService) {
    function init() {
        customerService.customerSelect2('customerName'); // 客户列表select2
        // 初始化tips
        noperceptionService.initTips();
    }
    init();

    // 默认选中0
    $scope.dateType = 0;
    $scope.time = 0;

    $scope.noperceptionAdd = function () {
        // 参数校验
        if(noperceptionService.validateNoperceptionFail($scope, 'add')) {
            return ;
        }
        noperceptionService.noperceptionAdd($scope);
    };
});