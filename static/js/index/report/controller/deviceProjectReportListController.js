/**
 * Created by Shin on 2016/03/30.
 * 设备统计-趋势-详细列表 deviceChartDetailController
 */
indexApp.controller('deviceProjectReportListController', function($scope, $location, locationService, projectService, deviceReportService) {

    /**
     * 从浏览器中获取所需参数值
     * @author 沈亚芳
     * @date 2016/03/30
     */
    var startDate = $location.search()['startDate'];
    $scope.startDate = startDate;

    var endDate = $location.search()['endDate'];
    $scope.endDate = endDate;

    var provinceId = $location.search()['provinceId'];
    $scope.provinceId = provinceId;

    var cityId = $location.search()['cityId'];
    $scope.cityId = cityId;

    var countyId = $location.search()['countyId'];
    $scope.countyId = countyId;


    /**
     * validateListDateFail 设备统计-日期-校验
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function validateListDateFail($scope){
        //校验开始日期-日
        if(deviceReportService.validateListStartDateFail($scope)){
            return true;
        }
        //校验截止日期-日
        if(deviceReportService.validateListEndDateFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * deviceProjectReportList 项目型设备按类型统计
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function list () {
        if(validateListDateFail($scope)){
            return;
        }
        deviceReportService.deviceProjectReportList($scope);
    }
    $scope.list = list;
    list();


    /**
     * exportDeviceProjectExcle 导出->项目型设备按类型统计
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function exportDeviceProjectExcle () {
        if(validateListDateFail($scope)){
            return;
        }
        deviceReportService.exportDeviceProjectExcle($scope);
    }
    $scope.exportDeviceProjectExcle = exportDeviceProjectExcle;


    /**
     * cityChange 点击省获取市数据集合
     * @param $event
     * @author 沈亚芳
     * @date 2016/03/30
     */
    $scope.cityChange = function($event){
        //获取provinceId（此值传入cityList）
        var provinceId = defaultString($scope.provinceId);
        //获取市数据集合
        locationService.cityList($scope, provinceId);
    };


    /**
     * countyChange 点击市获取区/县数据集合
     * @param $event
     * @author 沈亚芳
     * @date 2016/03/30
     */
    $scope.countyChange = function($event){
        //获取cityId（此值传入countyList）
        var cityId = defaultString($scope.cityId);
        //获取区/县数据集合
        locationService.countyList($scope, cityId);
    };


    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function init(){

        //开始日期-日趋势图
        $('#startDate').datepicker();
        //截止日期-日趋势图
        $('#endDate').datepicker();

        //初始化获取省数据集合
        locationService.provinceList($scope);
        //初始化提示-设备统计-列表-日
        deviceReportService.initTipsForListDateChange();

        //获取登录平台的信息集合
        projectService.getAllPlatformInfo($scope);

    }
    init();


    /**
     * resetWidth 重置列表因fixed引起的宽度问题
     * @author 沈亚芳
     * @date 2016/03/30
     */
    resetWidth();
});