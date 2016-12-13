indexApp.controller('deviceEditController', function ($scope, $location, locationService, deviceService) {

    var id = $location.search()['id'];//设备id

    deviceService.findByDeviceId($scope, id);

    //根据一级行业id获取二级行业集合
    $scope.change = function ($event) {
        var industryId1 = $scope.device.parentIndustryId;//获取父行业id(一级行业id)
        industryService.industry2List($scope, industryId1);//获取二级行业列表
    };

    //点击省获取市数据集合
    $scope.cityChange = function ($event) {
        var provinceId = $scope.device.provinceId;//省id
        locationService.cityList($scope, provinceId);//获取市数据集合
    };

    //点击市获取区/县数据集合
    $scope.countyChange = function ($event) {
        var cityId = $scope.device.cityId;//市id
        locationService.countyList($scope, cityId);//获取区/县数据集合
    };


    //参数校验
    function validateFail($scope) {
        //校验省
        if (deviceService.validateProvinceFail($scope)) {
            return true;
        }
        //校验市
        if (deviceService.validateCityFail($scope)) {
            return true;
        }
        //得到区县的个数
        var optionSize = $("#countyId option").size();
        //获得select最终的值
        var optionValue = $("#countyId").val();
        //判断当区县(option)的个数大于1并且select的最终得值不为空，则校验
        if (optionSize > 1 && optionValue == '') {
            //校验区/县
            if (deviceService.validateCountyFail($scope)) {
                return true;
            }
        }
        // 校验详细地址
        if (deviceService.validateAddressFail($scope)) {
            return true;
        }
        //校验备注
        if (deviceService.validateRemarkFail($scope)) {
            return true;
        }
        return false;
    }

    /**
     * 设备信息编辑
     * @Auther: zhuxuehuang
     * @Date: 2016-5-30
     */
    $scope.deviceEdit = function () {
        // 参数校验
        if(validateFail($scope)){
            return;
        }
        deviceService.deviceEdit($scope, id);
    };

    /**
     * 初始化方法
     */
    function init() {
        $scope.device = {};

        locationService.provinceList($scope);//获取省数据集合

        deviceService.initTips();
    }

    init();

});
