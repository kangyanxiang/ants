indexApp.controller('exceptionlogListController', function($scope, exceptionlogService, ngDialog) {
    var curDate = getCurDateHour();
    var defaultStartDate = curDate + ':00';//开始日期(默认)
    var defaultEndDate = curDate + ':55';//结束日期(默认)
	$scope.startDate = defaultStartDate;//开始日期
    $scope.endDate = defaultEndDate;//结束日期

    //异常日志列表
    function list(){
    	exceptionlogService.list($scope);
    }
    $scope.list = list;

    //日期查询 条件
    function initConditionForDate(){
        var $startDate = $("input[data-ng-model='startDate']");
        var $endDate = $("input[data-ng-model='endDate']");
        $startDate.datetimepicker({
            format:	'Y-m-d H:i',
            value: defaultStartDate,
            step: 5,
            onShow:function(){
                var endDate = $endDate.val();
                this.setOptions({
                    maxDate: endDate ? endDate : false
                })},
            a: ''
        });
        $endDate.datetimepicker({
            format:	'Y-m-d H:i',
            value: defaultEndDate,
            step: 5,
            onShow:function(){
                var startDate = $startDate.val();
                this.setOptions({
                    minDate: startDate ? startDate : false
                })},
            a: ''
        });

        /*$startDate.datepicker({
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
        $startDate.datepicker('option', 'maxDate',new Date($scope.endDate.replace('-',',')));
        $endDate.datepicker('option', 'minDate',new Date($scope.startDate.replace('-',',')));
         */
    }


    
    //详情
    $scope.show = function(id){
        $scope.id = id;
        ngDialog.open({
            template: 'html/template/log/exceptionlogShow.html',
            controller: 'exceptionlogShowController',
            title: '异常日志详情',
            width: '45%',
            scope: $scope
       });
    };

    /**
     * 页面加载初始化函数
     * @author 许小满
     * @date 2016/06/14
     */
    function init(){
        resetWidth();//重置列表因fixed引起的宽度问题
        initConditionForDate();
        list();
    }

    init();
});