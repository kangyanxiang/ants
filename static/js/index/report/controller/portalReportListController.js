/**
 * Created by Shin on 2016/03/03.
 * portal统计-列表 portalReportListController
 */
indexApp.controller('portalReportListController', function($scope,$location,locationService,projectService,portalReportService, DateService) {
    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2016/03/03
     */
    function init (){
        /**
         * 页面进来优先默认讲截止日期初始化为当前日期，开始日期为当前截止日期所在月份的第一天
         * @author 沈亚芳
         * @date 2016/03/03
         */
        //初始化开始日期
        $scope.startDate = DateService.getYesterday();
        //初始化截止日期
        $scope.endDate = DateService.getYesterday();

        //tab切换
        $('.navs').on('click', 'li', function (e) {
            var $el = $(this);
            var idx = $el.index();
            $el.parent().find('.active').removeClass('active');
            $el.addClass('active');
            var $panel = $('.nav-panel').hide().eq(idx).show();
            $panel.trigger('fninit');
            var panelId = $panel.attr('id');
            if (panelId == 'portalReportPV01'){
                $('#portalReportPV001').show();
                $('#portalReportUV001').hide();
            }else if (panelId == 'portalReportUV01'){
                $('#portalReportUV001').show();
                $('#portalReportPV001').hide();
            }
        });

        //开始日期
        $('#startDate').datepicker();
        //截止日期
        $('#endDate').datepicker();

        //获取登录平台的信息集合
        projectService.getAllPlatformInfo($scope);

        //初始化获取省数据集合
        locationService.provinceList($scope);

        //初始化提示-portal统计-列表
        portalReportService.initTipsForListDateChange();
    }
    init ();


    /**
     * cityChange 点击省获取市数据集合
     * @param $event
     * @author 沈亚芳
     * @date 2016/03/03
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
     * @date 2016/03/03
     */
    $scope.countyChange = function($event){
        //获取cityId（此值传入countyList）
        var cityId = defaultString($scope.cityId);
        //获取区/县数据集合
        locationService.countyList($scope, cityId);
    };


    /**
     * initConditionForDate 开始时间和结束时间限制
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/03
     */
    //function initConditionForDate(){
    //    var $startDate = $("#startDate");
    //    var $endDate = $("#endDate");
    //
    //    $startDate.datepicker({
    //        onSelect: function(dateText, inst) {
    //            $endDate.datepicker('option', 'minDate',new Date(dateText.replace('-',',')));
    //            $scope.startDate = dateText;
    //        }
    //    });
    //    $endDate.datepicker({
    //        onSelect: function(dateText, inst) {
    //            $startDate.datepicker('option', 'maxDate',new Date(dateText.replace('-',',')));
    //            $scope.endDate = dateText;
    //        }
    //    });
    //}


    /**
     * validateListDateFail portal统计-列表-校验
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/12/22
     */
    function validateListDateFail($scope){
        //校验开始日期-日
        if(portalReportService.validateListStartDateFail($scope)){
            return true;
        }
        //校验截止日期-日
        if(portalReportService.validateListEndDateFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * portalReportList portal统计-列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/03
     */
    function portalReportList (){
        if(validateListDateFail($scope)){
            return;
        }
        portalReportService.list($scope);
        //initConditionForDate();
    }
    $scope.portalReportList = portalReportList;
    portalReportList();


    /**
     * exportPortalReports 导出portal统计列表信息
     * @author 沈亚芳
     * @date 2016/03/03
     */
    function exportPortalReports(){
        if(validateListDateFail($scope)){
            return;
        }
        portalReportService.exportPortalReports($scope);
    }
    $scope.exportPortalReports = exportPortalReports;
    

    /**
     * newWidth 重置列表因fixed引起的宽度问题(考虑到PV和UV有显示和隐藏的问题，所以不能公用的resetWidth方法)
     * @author 沈亚芳
     * @date 2016/03/03
     */
    function newWidth (){
        var h = $(".content-portalReport");
        var listWidth = h.outerWidth( true );
        $(".listtitle").css('width', listWidth-20);
    }

    // 入口方法
    function main() {
        newWidth ();
    }
    main();

});