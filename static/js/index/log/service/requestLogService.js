/**
 * Created by Shin on 2016/04/21
 * 请求日志 requestLogService
 */
indexApp.factory('requestLogService',function($rootScope,requestLogDao) {

    /**
     * service 定义个全局service，并将其置空
     * @param 空
     * @author 沈亚芳
     * @date 2016/04/21
     */
    var service = {};


    /**
     * validateListStartDateFail 请求日志-列表-开始日期
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
     * validateListEndDateFail 请求日志-列表-截止日期
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
     * initTipsForListDateChange 请求日志-列表-相关信息提示的初始化
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
     * list 请求日志
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/21
     */
    service.list = function($scope){
        requestLogDao.list($scope);
    };


    /**
     * show 日志详情
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/21
     */
    service.show = function($scope, id){
        requestLogDao.show($scope, id);
    };


    /**
     * 返回 service
     * @author 沈亚芳
     * @date 2016/04/21
     */
    return service;
});
