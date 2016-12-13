/**
 * Created by Shin on 2016/03/16.
 * 设备统计-列表 deviceReportListController
 */
indexApp.controller('deviceReportListController', function($scope,$location,locationService,deviceReportService, DateService) {
    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2016/03/16
     */
    function init (){
        /**
         * 默认时间维度为“日”
         * @type {string}
         */
        $scope.statType = 'D';

        /**
         * 页面进来优先默认讲截止日期初始化为当前日期，开始日期为当前截止日期所在月份的第一天
         * @author 沈亚芳
         * @date 2016/03/16
         */
        //初始化开始日期
        $scope.startDate = DateService.getToday();
        //初始化截止日期
        $scope.endDate = DateService.getToday();

        $('#startDate').datepicker();
        $('#endDate').datepicker();

        //$('#startMonth').datepicker({
        //    dateFormat: 'yy-mm'
        //});
        //$('#endMonth').datepicker({
        //    dateFormat: 'yy-mm'
        //});

        //初始化获取省数据集合
        locationService.provinceList($scope);

        //初始化提示-设备统计-列表-日
        deviceReportService.initTipsForListDateChange();
        //初始化提示-设备统计-列表-月
        //deviceReportService.initTipsForListMonthChange();
    }
    init ();


    /**
     * 页面进来优先默认讲截止日期初始化为当前日期，开始日期为当前截止日期所在月份
     * @author 沈亚芳
     * @date 2016/03/16
     */
    var myDate2 = new Date();
    var str3 = "" + myDate2.getFullYear() + "-";
    if((myDate2.getMonth()+1) >=1 && (myDate2.getMonth()+1) < 10 ){
        str3 += '0' + (myDate2.getMonth()+1);
    }else{
        str3 += (myDate2.getMonth()+1);
    }
    $scope.startMonth = str3;
    $scope.endMonth = str3;



    /**
     * cityChange 点击省获取市数据集合
     * @param $event
     * @author 沈亚芳
     * @date 2016/03/16
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
     * @date 2016/03/16
     */
    $scope.countyChange = function($event){
        //获取cityId（此值传入countyList）
        var cityId = defaultString($scope.cityId);
        //获取区/县数据集合
        locationService.countyList($scope, cityId);
    };


    /**
     * validateListDateFail 设备统计-列表-校验
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/16
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
    function validateListMonthFail($scope){
        //校验开始日期-月
        if(deviceReportService.validateListStartMonthFail($scope)){
            return true;
        }
        //校验截止日期-月
        if(deviceReportService.validateListEndMonthFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * initConditionForDate 开始时间和结束时间限制
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/16
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
     * list 设备统计-列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/16
     */
    function deviceReportList (){
        //var statType = $('#statType').val();
        //if (statType == 'D') {
        //    if(validateListDateFail($scope)){
        //        return;
        //    }
        if(validateListDateFail($scope)){
            return;
        }
        //} else if (statType == 'M') {
        //    if(validateListMonthFail($scope)){
        //        return;
        //    }
        //}
        deviceReportService.list($scope);
        //initConditionForDate();
    }
    $scope.deviceReportList = deviceReportList;


    /**
     * exportDeviceReports 导出设备统计-列表信息
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/16
     */
    function exportDeviceReports(){
        if(validateListDateFail($scope)){
            return;
        }
        deviceReportService.exportDeviceReports($scope);
    }
    $scope.exportDeviceReports = exportDeviceReports;


    /**
     * statTypeChange 根据时间维度的值不同来控制隐藏和显示日期的控件（日、月）
     * @param $event
     * @author 沈亚芳
     * @date 2016/03/16
     */
    //$scope.statTypeChange = function($event){
    //    var statType = $scope.statType;
    //    if (statType == 'D') {
    //        //根据startMonth和endMonth来取对应的月份,并进行设置，然后赋值给startDate和endDate
    //        var startMonth = $scope.startMonth;
    //        var endMonth = $scope.endMonth;
    //
    //        var oldStartDate = $scope.startDate;
    //        var oldEndDate = $scope.endDate;
    //
    //        var oldStartDateArray = oldStartDate.split('-');
    //        var oldStartDateForStartMonth = oldStartDateArray[0] + '-' + oldStartDateArray[1];
    //
    //        if (startMonth == '') {
    //            $scope.startDate = '';
    //        } else {
    //            if (oldStartDateForStartMonth == startMonth){
    //                $scope.startDate = oldStartDate;
    //            } else {
    //                $scope.startDate = startMonth + '-' + '01';
    //            }
    //        }
    //
    //
    //        var oldEndDateArray = oldEndDate.split('-');
    //        var oldEndDateArrayForEndMonth = oldEndDateArray[0] + '-' + oldEndDateArray[1];
    //        if (endMonth == '') {
    //            $scope.endDate = '';
    //        } else {
    //            if (oldEndDateArrayForEndMonth == endMonth){
    //                $scope.endDate = oldEndDate;
    //            } else {
    //                var endMonthArray = endMonth.split('-');
    //                console.log('endMonthArray:' + endMonthArray);
    //                if (endMonthArray[1] == '01' || endMonthArray[1] == '03' || endMonthArray[1] == '05' || endMonthArray[1] == '07' || endMonthArray[1] == '08' || endMonthArray[1] == '10' || endMonthArray[1] == '12') {
    //                    $scope.endDate = endMonth + '-' + '31';
    //                } else if (endMonthArray[1] == '04' || endMonthArray[1] == '06' || endMonthArray[1] == '09' || endMonthArray[1] == '11') {
    //                    $scope.endDate = endMonth + '-' + '30';
    //                } else if (endMonthArray[1] == '02') {
    //                    var endMonthForYear = endMonthArray[0];
    //                    if ((endMonthForYear%4 == 0 && endMonthForYear%100 !== 0) || (endMonthForYear%100 == 0 && endMonthForYear%400 == 0)) {
    //                        $scope.endDate = endMonth + '-' + '29';
    //                    } else {
    //                        $scope.endDate = endMonth + '-' + '28';
    //                    }
    //                }
    //            }
    //        }
    //
    //        //控制显示和隐藏
    //        $('#statTypeForStartDate').show();
    //        $('#statTypeForEndDate').show();
    //        $('#statTypeForStartMonth').hide();
    //        $('#statTypeForEndMonth').hide();
    //
    //    } else if (statType == 'M') {
    //        //根据startDate和endDate来取对应的月份并赋值给startMonth和endMonth
    //        var startDate = $scope.startDate;
    //        var startDateArray = startDate.split('-');
    //        if (startDate == '') {
    //            $scope.startMonth = '';
    //        } else {
    //            $scope.startMonth = startDateArray[0] + '-' + startDateArray[1];
    //        }
    //
    //        var endDate = $scope.endDate;
    //        var endDateArray = endDate.split('-');
    //        if (endDate == '') {
    //            $scope.endMonth = '';
    //        } else {
    //            $scope.endMonth = endDateArray[0] + '-' + endDateArray[1];
    //        }
    //
    //        //控制显示和隐藏
    //        $('#statTypeForStartMonth').show();
    //        $('#statTypeForEndMonth').show();
    //        $('#statTypeForStartDate').hide();
    //        $('#statTypeForEndDate').hide();
    //    }
    //};

    // 入口方法
    function main() {
        deviceReportList();

        /**
         * resetWidth 重置列表因fixed引起的宽度问题
         * @author 沈亚芳
         * @date 2016/03/16
         */
        resetWidth();
    }
    main();

});