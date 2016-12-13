/**
 * Created by zhuxh on 15-11-30.
 * 设备分配控制层  deviceAllocationController
 */

indexApp.controller('deviceAllocationController', function($scope, $location, deviceService, customerService) {

    //设备分配(分页)(调用的是查询客户列表(只拿到客户id，客户名，行业)方法)
    function list(){
    	customerService.customerNameAndIndustryList($scope);
    }
    $scope.list = list;
    list();
    
    //设备分配页面确定按钮
    $scope.deviceAllocationBt = function($scope){
    	var deviceIds = $location.search()['deviceIds'];//设备id集合
    	var customerIds = $location.search()['customerIds'];//客户id集合
    	deviceService.deviceAllocation($scope, deviceIds, customerIds);
    };
    
  //参数校验
    function validateFail($scope){
    	//校验客户id不能为空
        if(customerService.validateCustomerIdFail($scope)){
            return true;
        }
        return false;
    }

});
