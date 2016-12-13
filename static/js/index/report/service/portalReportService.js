/**
 * Created by Shin on 2016/03/03
 * portal统计 portalReportService
 */
indexApp.factory('portalReportService',function($rootScope,portalReportDao) {

    /**
     * service 定义个全局service，并将其置空
     * @param 空
     * @author 沈亚芳
     * @date 2016/03/03
     */
    var service = {};


    /**
     * validateListStartDateFail portal统计-列表-开始日期
     * @param $scope 校验的参数：startDate
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.validateListStartDateFail = function($scope) {
        var $startDate = $('#startDate');
        var startDate = $scope.startDate;
        //校验是否为空
        if(isBlank(startDate)){
            updateShowTipos($startDate, '请选择开始日期！');
            return true;
        }
        return false;
    };


    /**
     * validateListEndDateFail portal统计-列表-截止日期
     * @param $scope 校验的参数：endDate
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.validateListEndDateFail = function($scope) {
        var startDate = $scope.startDate;
        var $endDate = $('#endDate');
        var endDate = $scope.endDate;
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
     * initTipsForListDateChange portal统计-列表-相关信息提示的初始化
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.initTipsForListDateChange = function(){
        var $startDate = $('#startDate');
        var $endDate = $('#endDate');
        initTipsArray([$startDate, $endDate]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$startDate, $endDate]);
        });
    };


    /**
     * validatePVStartDateFail 检验PV趋势图-日-开始日期
     * @param $scope 校验的参数：startDate
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.validatePVStartDateFail = function($scope) {
        var $startDate = $('#startDate');
        var startDate = $scope.PVDateReport.startDate;
        //校验是否为空
        if(isBlank(startDate)){
            updateShowTipos($startDate, '请选择开始日期！');
            return true;
        }
        return false;
    };


    /**
     * validatePVEndDateFail 检验PV趋势图-日-截止日期
     * @param $scope 校验的参数：endDate
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.validatePVEndDateFail = function($scope) {
        var startDate = $scope.PVDateReport.startDate;
        var $endDate = $('#endDate');
        var endDate = $scope.PVDateReport.endDate;
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
     * initTipsForPVDateChange PV趋势图-日-相关信息提示的初始化
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.initTipsForPVDateChange = function(){
        var $startDate = $('#startDate');
        var $endDate = $('#endDate');
        initTipsArray([$startDate, $endDate]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$startDate, $endDate]);
        });
    };


    /**
     * validatePVStartMonthFail 检验PV趋势图-月-开始日期
     * @param $scope 校验的参数：startMonth
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.validatePVStartMonthFail = function($scope) {
        var $startMonth = $('#startMonth');
        var startMonth = $scope.PVMonthReport.startMonth;
        //校验是否为空
        if(isBlank(startMonth)){
            updateShowTipos($startMonth, '请选择开始日期！');
            return true;
        }
        return false;
    };


    /**
     * validatePVEndMonthFail 检验PV趋势图-月-截止日期
     * @param $scope 校验的参数：endMonth
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.validatePVEndMonthFail = function($scope) {
        var startMonth = $scope.PVMonthReport.startMonth;
        var $endMonth = $('#endMonth');
        var endMonth = $scope.PVMonthReport.endMonth;
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
     * initTipsForPVMonthChange PV趋势图-月-相关信息提示的初始化
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.initTipsForPVMonthChange = function(){
        var $startMonth = $('#startMonth');
        var $endMonth = $('#endMonth');
        initTipsArray([$startMonth, $endMonth]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$startMonth, $endMonth]);
        });
    };


    /**
     * validateUVStartDateFail 检验UV趋势图-月-开始日期
     * @param $scope 校验的参数：startDate
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.validateUVStartDateFail = function($scope) {
        var $startDate = $('#startDate');
        var startDate = $scope.UVDateReport.startDate;
        //校验是否为空
        if(isBlank(startDate)){
            updateShowTipos($startDate, '请选择开始日期！');
            return true;
        }
        return false;
    };


    /**
     * validateUVEndDateFail 检验UV趋势图-日-截止日期
     * @param $scope 校验的参数：endDate
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.validateUVEndDateFail = function($scope) {
        var startDate = $scope.UVDateReport.startDate;
        var $endDate = $('#endDate');
        var endDate = $scope.UVDateReport.endDate;
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
     * initTipsForUVDateChange UV趋势图-日-相关信息提示的初始化
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.initTipsForUVDateChange = function(){
        var $startDate = $('#startDate');
        var $endDate = $('#endDate');
        initTipsArray([$startDate, $endDate]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$startDate, $endDate]);
        });
    };


    /**
     * validateUVStartMonthFail 检验UV趋势图-月-开始日期
     * @param $scope 校验的参数：startMonth
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.validateUVStartMonthFail = function($scope) {
        var $startMonth = $('#startMonth');
        var startMonth = $scope.UVMonthReport.startMonth;
        //校验是否为空
        if(isBlank(startMonth)){
            updateShowTipos($startMonth, '请选择开始日期！');
            return true;
        }
        return false;
    };


    /**
     * validateUVEndMonthFail 检验UV趋势图-月-截止日期
     * @param $scope 校验的参数：endMonth
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.validateUVEndMonthFail = function($scope) {
        var startMonth = $scope.UVMonthReport.startMonth;
        var $endMonth = $('#endMonth');
        var endMonth = $scope.UVMonthReport.endMonth;
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
     * initTipsForUVMonthChange UV趋势图-月-相关信息提示的初始化
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.initTipsForUVMonthChange = function(){
        var $startMonth = $('#startMonth');
        var $endMonth = $('#endMonth');
        initTipsArray([$startMonth, $endMonth]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$startMonth, $endMonth]);
        });
    };


    /**
     * portalReportList portal统计-列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.list = function($scope){
        portalReportDao.list($scope);
        //portalReportDao.totalList($scope);
    };


    /**
     * chartsPVDate PV趋势图-日
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.chartsPVDate = portalReportDao.chartsPVDate;


    /**
     * chartPVMonth PV趋势图-月
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.chartPVMonth = portalReportDao.chartPVMonth;


    /**
     * chartsPVDate UV趋势图-日
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/03
     */
    service.chartsUVDate = portalReportDao.chartsUVDate;


    /**
     * chartPVMonth UV趋势图-月
     * @param $scope
     * @author 沈亚芳
     * @date 2016/02/25
     */
    service.chartUVMonth = portalReportDao.chartUVMonth;


    /**
     * exportPortalReports 导出portal统计=列表信息
     * @param $scope
     * @author 沈亚芳
     * @date 2016/02/22
     */
    service.exportPortalReports = function($scope){
        portalReportDao.exportPortalReports($scope);
    };


    /**
     * exportPortalDateDetail 导出-> portal页面访问按日
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    service.exportPortalDateDetail = function($scope){
        portalReportDao.exportPortalDateDetail($scope);
    };


    /**
     * exportPortalMonthDetail 导出-> portal页面访问按月
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    service.exportPortalMonthDetail = function($scope){
        portalReportDao.exportPortalMonthDetail($scope);
    };


    /**
     * 返回 service
     * @author 沈亚芳
     * @date 2016/03/03
     */
    return service;
});