/**
 * Created by Shin on 2016/04/21.
 * 请求日志-列表 requestLogListController
 */
indexApp.controller('requestLogListController', function($scope, requestLogService, DateService, ngDialog) {
    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2016/03/03
     */
    function init (){
        /**
         * 页面进来优先默认讲截止日期初始化为当前日期，开始日期为当前截止日期所在月份的第一天
         * @author 沈亚芳
         * @date 2016/04/21
         */
        //初始化开始日期
        $scope.startDate = DateService.monthEarly();
        //初始化截止日期
        $scope.endDate = DateService.getToday();

        //开始时间
        $('#startDate').datepicker();
        //截止时间
        $('#endDate').datepicker();

        //初始化提示-portal统计-列表
        requestLogService.initTipsForListDateChange();
    }
    init();
    

    /**
     * initConditionForDate 开始时间和结束时间限制
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/21
     */
    function initConditionForDate(){
        var $startDate = $("#startDate");
        var $endDate = $("#endDate");

        $startDate.datepicker({
            onSelect: function(dateText, inst) {
                $endDate.datepicker('option', 'minDate',new Date(dateText.replace('-',',')));
                $scope.startDate = dateText;
            }
        });
        $endDate.datepicker({
            onSelect: function(dateText, inst) {
                $startDate.datepicker('option', 'maxDate',new Date(dateText.replace('-',',')));
                $scope.endDate = dateText;
            }
        });
    }


    /**
     * validateListDateFail portal统计-列表-校验
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/04/21
     */
    function validateListDateFail($scope){
        //校验开始日期-日
        if(requestLogService.validateListStartDateFail($scope)){
            return true;
        }
        //校验截止日期-日
        if(requestLogService.validateListEndDateFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * list 请求日志
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/21
     */
    function list (){
        if(validateListDateFail($scope)){
            return;
        }
        requestLogService.list($scope);
        initConditionForDate();
    }
    $scope.list = list;


    /**
     * show 日志详情
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/21
     */
    $scope.show = function(id){
        $scope.id = id;
        ngDialog.open({
            template: 'html/template/log/requestLogShow.html',
            controller: 'requestLogShowController',
            scope : $scope,
            title: '请求日志详情',
            width: '45%'
        });
    };

    // 入口方法
    function main() {
        list();

        /**
         * resetWidth 重置列表因fixed引起的宽度问题
         * @author 沈亚芳
         * @date 2016/04/21
         */
        resetWidth();
    }
    main();
});
