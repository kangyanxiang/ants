/**
 * Created by zhuxuehuang on 16-4-5.
 */

indexApp.controller('projectCustomerDetailsController', function($scope, $location, customerService, projectService, pageDao) {

    //客户id
    var customerId = $scope.customerId;

    // 是否第一次操作
    $scope.projectCustomerDetailsHasData = {
        'customer': false,
        'device': false,
        'strategy': false
    };

    // 标题宽度问题
    // resetWidth();

    /**
     * 客户详情tab操作
     * @param customerId
     * @param tabDivName
     */
    function showCustomerList(customerId, tabDivName) {
        $scope.tabDivName = tabDivName;

        if (tabDivName === 'device') {
            // 设备列表分页
            if (!$scope.projectCustomerDetailsHasData['device']) {
                projectService.customerDeviceList($scope);
                $scope.projectCustomerDetailsHasData['device'] = true;
            }
        } else if (tabDivName === 'strategy') {
            // 站点策略
            if (!$scope.projectCustomerDetailsHasData['strategy']) {
                projectService.customerStrategyList($scope);
                projectService.customerSiteList($scope);
                $scope.projectCustomerDetailsHasData['strategy'] = true;
            }
        } else {
            // 客户详情
            if (!$scope.projectCustomerDetailsHasData['customer']) {
                customerService.findByCustomerId($scope, customerId);
                $scope.projectCustomerDetailsHasData['customer'] = true;
            }
        }
    }

    $scope.showCustomerList = showCustomerList;

    showCustomerList(customerId, 'customer');

    // 设备列表分页
    $scope.dialogDeviceList = function () {
        projectService.customerDeviceList($scope);
    };

});
