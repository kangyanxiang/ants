indexApp.controller('operationlogListController', function($scope, operationlogService, DateService, ngDialog) {
    // 页面加载初始化函数
    function init(){
        $scope.startDate = DateService.getToday(); //开始日期
    }
    init();


    /**
     * 操作日志列表
     * @Auther: zhuxuehuang
     * @Date: 2016-7-8
     */
    function list(){
        operationlogService.list($scope);
    }
    $scope.list = list;

    // 日期查询条件
    function initConditionForDate(){
        var $startDate = $("#startDate");

        //开始日期
        $startDate.datepicker({
            // minDate: '-7D',
            maxDate: '+0D',
            onSelect: function(dateText, inst) {
                $scope.startDate = dateText;
            }
        });
    }


    /**
     * 查看详情
     * @param id
     * @Auther: zhuxuehuang
     * @Date: 2016-7-8
     */
    $scope.show = function(id) {
        $scope.id = id;
        ngDialog.open({
            template: 'html/template/log/operationlogShow.html',
            controller: 'operationlogShowController',
            title: '操作日志详情',
            width: '45%',
            scope: $scope
       });
    };

    // 入口方法
    function main() {
        resetWidth(); //重置列表因fixed引起的宽度问题
        initConditionForDate();
        list();
    }
    main();

});