/**
 * Created by Shin on 2016/03/30.
 * 设备统计-关键指标 deviceKeyIndexController
 */
indexApp.controller('deviceKeyIndexController', function($scope, $location, deviceReportService) {

    /**
     * 从浏览器中获取所需参数值
     * @author 沈亚芳
     * @date 2016/03/30
     */
    var locationId = $location.search()['locationId'];
    $scope.locationId = locationId;

    var locationName = $location.search()['locationName'];
    $scope.locationName = locationName;

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
     * deviceKeyIndexList 地区设备关键指标数据列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function list () {
        if(validateListDateFail($scope)){
            return;
        }
        deviceReportService.deviceKeyIndexList($scope);
    }
    $scope.list = list;
    list();


    /**
     * exportKeyIndexExcle 导出设备统计>关键指标列表数据
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function exportKeyIndexExcle () {
        if(validateListDateFail($scope)){
            return;
        }
        deviceReportService.exportKeyIndexExcle($scope);
    }
    $scope.exportKeyIndexExcle = exportKeyIndexExcle;


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
        //初始化提示-设备统计-列表-日
        deviceReportService.initTipsForListDateChange();
    }
    init();


    /**
     * resetWidth 重置列表因fixed引起的宽度问题
     * @author 沈亚芳
     * @date 2016/03/30
     */
    resetWidth();


    /**
     * newWidth 因fixed，右侧有返回按钮，因此重置宽度
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function newWidth (){
        var width = document.body.clientWidth;
        $("#reset").css('width', width-225);
    }
    newWidth ();
});