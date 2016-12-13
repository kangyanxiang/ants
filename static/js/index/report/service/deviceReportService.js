/**
 * Created by Shin on 2016/03/16
 * 设备统计 deviceReportService
 */
indexApp.factory('deviceReportService',function($rootScope,deviceReportDao) {
    /**
     * service 定义个全局service，并将其置空
     * @param 空
     * @author 沈亚芳
     * @date 2016/03/16
     */
    var service = {};


    /**
     * validateListStartDateFail 设备统计-列表-开始日期
     * @param $scope 校验的参数：startDate
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/16
     */
    service.validateListStartDateFail = function ($scope) {
        var $startDate = $('#startDate');
        var startDate = $scope.startDate;
        //校验是否为空
        if (isBlank(startDate)) {
            updateShowTipos($startDate, '请选择开始日期！');
            return true;
        }
        return false;
    };


    /**
     * validateListEndDateFail 设备统计-列表-截止日期
     * @param $scope 校验的参数：endDate
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/16
     */
    service.validateListEndDateFail = function ($scope) {
        var startDate = $scope.startDate;
        var $endDate = $('#endDate');
        var endDate = $scope.endDate;
        //校验是否为空
        if (isBlank(endDate)) {
            updateShowTipos($endDate, '请选择截止日期！');
            return true;
        }
        //校验截止日期是否小于开始日期
        if (endDate < startDate) {
            updateShowTipos($endDate, '截止日期不能早于开始日期！');
            return true;
        }
        return false;
    };


    /**
     * initTipsForListDateChange 设备统计-列表-相关信息提示的初始化
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.initTipsForListDateChange = function () {
        var $startDate = $('#startDate');
        var $endDate = $('#endDate');
        initTipsArray([$startDate, $endDate]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function () {
            destroyTipsArray([$startDate, $endDate]);
        });
    };


    /**
     * validateDeviceStartDateFail 检验趋势图-日-开始日期
     * @param $scope 校验的参数：startDate
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/30
     */
    service.validateDeviceStartDateFail = function($scope) {
        var $startDate = $('#startDate');
        var startDate = $scope.deviceDateReport.startDate;
        //校验是否为空
        if(isBlank(startDate)){
            updateShowTipos($startDate, '请选择开始日期！');
            return true;
        }
        return false;
    };


    /**
     * validateDeviceEndDateFail 检验趋势图-日-截止日期
     * @param $scope 校验的参数：endDate
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/30
     */
    service.validateDeviceEndDateFail = function($scope) {
        var startDate = $scope.deviceDateReport.startDate;
        var $endDate = $('#endDate');
        var endDate = $scope.deviceDateReport.endDate;
        //校验是否为空
        if(isBlank(endDate)){
            updateShowTipos($endDate, '请选择截止日期！');
            return true;
        }
        //校验截止日期是否小于开始日期
        if(endDate < startDate){
            updateShowTipos($endDate, '截止日期不能早于开始日期！');
            return true;
        }
        return false;
    };


    /**
     * initTipsForDeviceDateChange 趋势图-日-相关信息提示的初始化
     * @author 沈亚芳
     * @date 2016/03/30
     */
    service.initTipsForDeviceDateChange = function(){
        var $startDate = $('#startDate');
        var $endDate = $('#endDate');
        initTipsArray([$startDate, $endDate]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$startDate, $endDate]);
        });
    };


    /**
     * validateDeviceStartMonthFail 检验趋势图-月-开始日期
     * @param $scope 校验的参数：startMonth
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.validateDeviceStartMonthFail = function($scope) {
        var $startMonth = $('#startMonth');
        var startMonth = $scope.deviceMonthReport.startMonth;
        //校验是否为空
        if(isBlank(startMonth)){
            updateShowTipos($startMonth, '请选择开始日期！');
            return true;
        }
        return false;
    };


    /**
     * validateDeviceEndMonthFail 检验趋势图-月-截止日期
     * @param $scope 校验的参数：endMonth
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.validateDeviceEndMonthFail = function($scope) {
        var startMonth = $scope.deviceMonthReport.startMonth;
        var $endMonth = $('#endMonth');
        var endMonth = $scope.deviceMonthReport.endMonth;
        //校验是否为空
        if(isBlank(endMonth)){
            updateShowTipos($endMonth, '请选择截止日期！');
            return true;
        }
        //校验截止日期是否小于开始日期
        if(endMonth < startMonth){
            updateShowTipos($endMonth, '截止日期不能早于开始日期！');
            return true;
        }
        return false;
    };


    /**
     * initTipsForDeviceMonthChange 趋势图-月-相关信息提示的初始化
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.initTipsForDeviceMonthChange = function(){
        var $startMonth = $('#startMonth');
        var $endMonth = $('#endMonth');
        initTipsArray([$startMonth, $endMonth]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$startMonth, $endMonth]);
        });
    };


    /**
     * list 设备统计-列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/16
     */
    service.list = function($scope){
        deviceReportDao.list($scope);
    };


    /**
     * exportDeviceReports 导出设备统计-列表信息
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/16
     */
    service.exportDeviceReports = function($scope){
        deviceReportDao.exportDeviceReports($scope);
    };


    /**
     * chartsDeviceDate 设备发展按日趋势
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    service.chartsDeviceDate = deviceReportDao.chartsDeviceDate;


    /**
     * chartsDeviceMonth 设备发展按日趋势
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    service.chartsDeviceMonth = deviceReportDao.chartsDeviceMonth;


    /**
     * exportDeviceChartDate 导出设备统计>趋势>按日导出
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    service.exportDeviceChartDate = function($scope){
        deviceReportDao.exportDeviceChartDate($scope);
    };


    /**
     * exportDeviceChartMonth 导出设备统计>趋势>按月导出
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    service.exportDeviceChartMonth = function($scope){
        deviceReportDao.exportDeviceChartMonth($scope);
    };


    /**
     * deviceKeyIndexList 地区设备关键指标数据列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    service.deviceKeyIndexList = function($scope){
        deviceReportDao.deviceKeyIndexList($scope);
    };


    /**
     * exportKeyIndexExcle 导出设备统计>关键指标列表数据
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    service.exportKeyIndexExcle = function($scope){
        deviceReportDao.exportKeyIndexExcle($scope);
    };


    /**
     * deviceProjectReportList 项目型设备按类型统计
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    service.deviceProjectReportList = function($scope){
        deviceReportDao.deviceProjectReportList($scope);
    };


    /**
     * exportDeviceProjectExcle 导出->项目型设备按类型统计
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    service.exportDeviceProjectExcle = function($scope){
        deviceReportDao.exportDeviceProjectExcle($scope);
    };


    /**
     * 返回 service
     * @author 沈亚芳
     * @date 2016/03/16
     */
    return service;
});