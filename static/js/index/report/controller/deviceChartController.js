/**
 * Created by Shin on 2016/03/30.
 * 设备统计-趋势 deviceChartController
 */
indexApp.controller('deviceChartController', function($scope, $location, deviceReportService) {

    /**
     * dateDeviceChartLine 设备按日趋势统计图
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function dateDeviceChartLine(x_date, chart_projectTotal_data, chart_sMTotal_data, chart_subTotal_data){
        var data_device_chart_line = echarts.init(document.getElementById('date_device_chart_line'));

        var data_device_chart_line_option = {
            tooltip:{
                trigger: 'axis'
            },
            dataZoom: [
                {
                    show: true,
                    realtime: true,
                    start: 0,
                    end: 100
                },
                {
                    type: 'inside',
                    realtime: true,
                    start: 0,
                    end: 100
                }
            ],
            legend: {
                orient: 'horizontal',
                x: 'right',
                data: [
                    {
                        name: '项目型',
                        textStyle: {
                            color: '#66CBFF'
                        }
                    },
                    {
                        name: '中小商户',
                        textStyle: {
                            color: '#66CC66'
                        }
                    },
                    {
                        name: '小计',
                        textStyle: {
                            color: '#d50000'
                        }
                    }

                ]
            },
            grid: {
                left: 40,
                right: 40,
                bottom: 60 ,
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: x_date //['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24']
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series:[
                {
                    name: '项目型',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#66CBFF'
                        }
                    },
                    data: chart_projectTotal_data
                },
                {
                    name: '中小商户',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#66CC66'
                        }
                    },
                    data: chart_sMTotal_data
                },
                {
                    name: '小计',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#d50000'
                        }
                    },
                    data: chart_subTotal_data
                }
            ]
        };

        data_device_chart_line.setOption(data_device_chart_line_option);
    }


    /**
     * monthDeviceChartLine 设备按月趋势统计图
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function monthDeviceChartLine(x_date, chart_projectTotal_data, chart_sMTotal_data, chart_subTotal_data){
        var data_device_chart_line = echarts.init(document.getElementById('month_device_chart_line'));

        var data_device_chart_line_option = {
            tooltip:{
                trigger: 'axis'
            },
            dataZoom: [
                {
                    show: true,
                    realtime: true,
                    start: 0,
                    end: 100
                },
                {
                    type: 'inside',
                    realtime: true,
                    start: 0,
                    end: 100
                }
            ],
            legend: {
                orient: 'horizontal',
                x: 'right',
                data: [
                    {
                        name: '项目型',
                        textStyle: {
                            color: '#66CBFF'
                        }
                    },
                    {
                        name: '中小商户',
                        textStyle: {
                            color: '#66CC66'
                        }
                    },
                    {
                        name: '小计',
                        textStyle: {
                            color: '#d50000'
                        }
                    }

                ]
            },
            grid: {
                left: 40,
                right: 40,
                bottom: 60 ,
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: x_date //['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24']
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series:[
                {
                    name: '项目型',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#66CBFF'
                        }
                    },
                    data: chart_projectTotal_data
                },
                {
                    name: '中小商户',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#66CC66'
                        }
                    },
                    data: chart_sMTotal_data
                },
                {
                    name: '小计',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#d50000'
                        }
                    },
                    data: chart_subTotal_data
                }
            ]
        };

        data_device_chart_line.setOption(data_device_chart_line_option);
    }


    /**
     * validateDeviceDateFail 设备发展按日趋势-校验
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function validateDeviceDateFail($scope){
        //校验开始日期-日
        if(deviceReportService.validateDeviceStartDateFail($scope)){
            return true;
        }
        //校验截止日期-日
        if(deviceReportService.validateDeviceEndDateFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * chartsDeviceDate 设备发展按日趋势
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function chartsDeviceDate(){
        if(validateDeviceDateFail($scope)){
            return;
        }
        deviceReportService.chartsDeviceDate($scope,  dateDeviceChartLine);
    }
    $scope.chartsDeviceDate = chartsDeviceDate;


    /**
     * validateDeviceMonthFail 设备发展按月趋势-校验
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function validateDeviceMonthFail($scope){
        //校验开始日期-月
        if(deviceReportService.validateDeviceStartMonthFail($scope)){
            return true;
        }
        //校验截止日期-月
        if(deviceReportService.validateDeviceEndMonthFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * chartsDeviceDate 设备发展按日趋势
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function chartsDeviceMonth(){
        if(validateDeviceMonthFail($scope)){
            return;
        }
        deviceReportService.chartsDeviceMonth($scope,  monthDeviceChartLine);
    }
    $scope.chartsDeviceMonth = chartsDeviceMonth;


    /**
     * exportDeviceChartDate 导出设备统计>趋势>按日导出
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function exportDeviceChartDate () {
        if(validateDeviceDateFail($scope)){
            return;
        }
        deviceReportService.exportDeviceChartDate($scope);
    }
    $scope.exportDeviceChartDate = exportDeviceChartDate;


    /**
     * exportDeviceChartMonth 导出设备统计>趋势>按月导出
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function exportDeviceChartMonth () {
        if(validateDeviceMonthFail($scope)){
            return;
        }
        deviceReportService.exportDeviceChartMonth($scope);
    }
    $scope.exportDeviceChartMonth = exportDeviceChartMonth;


    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function init(){

        $scope.deviceDateReport = {};
        $scope.deviceMonthReport = {};

        var locationId = $location.search()['locationId'];
        $scope.locationId = locationId;

        var locationName = $location.search()['locationName'];
        $scope.locationName = locationName;

        var provinceId = $location.search()['provinceId'];
        $scope.provinceId = provinceId;

        var cityId = $location.search()['cityId'];
        $scope.cityId = cityId;

        var countyId = $location.search()['countyId'];
        $scope.countyId = countyId;

        //将之前列表查询条件中的开始日期和截止日期带入到折线图页面
        var startDate = $location.search()['startDate'];
        var endDate = $location.search()['endDate'];
        $scope.deviceDateReport.startDate = startDate;
        $scope.deviceDateReport.endDate = endDate;
        //详细数据按钮链接默认为从浏览器地址中获取，且不随开始日期和截止日期ng-model的改变而改变，只有当点“查询”时才改变
        $scope.deviceDateReport.oldStartDate = startDate;
        $scope.deviceDateReport.oldEndDate = endDate;


        //月趋势图：默认显示当前年一月至当前月的数据
        var myDate = new Date();
        var str1 = "" + myDate.getFullYear() + "-";
        str1 += "01";
        $scope.deviceMonthReport.startMonth = str1;
        var str2 = "" + myDate.getFullYear() + "-";
        if((myDate.getMonth()+1) >=1 && (myDate.getMonth()+1) < 10 ){
            str2 += '0' + (myDate.getMonth()+1);
        }else{
            str2 += (myDate.getMonth()+1);
        }
        $scope.deviceMonthReport.endMonth = str2;

        var oldMonthHref ='#/report/deviceChartDetail?statType=M&locationId=' + locationId + '&locationName=' + locationName + '&provinceId=' + provinceId + '&cityId=' + cityId + '&countyId=' + countyId + '&startDate=' + str1 + '&endDate=' + str2 + '&backStartDate=' + startDate + '&backEndDate=' + endDate;
        $('#resetMonthUrl').attr('href', oldMonthHref);

        //开始日期-日趋势图
        $('#startDate').datepicker();
        //截止日期-日趋势图
        $('#endDate').datepicker();

        //开始日期-月趋势图
        $('#startMonth').datepicker({
            dateFormat: 'yy-mm'
        });
        //截止日期-月趋势图
        $('#endMonth').datepicker({
            dateFormat: 'yy-mm'
        });

        // 设备-日趋势图(首次页面加载时，显示统计图用的)
        deviceReportService.chartsDeviceDate($scope, dateDeviceChartLine);

        // 设备-日趋势图(首次页面加载时，显示统计图用的)
        deviceReportService.chartsDeviceMonth($scope, monthDeviceChartLine);

        //初始化提示-设备趋势图-日
        deviceReportService.initTipsForDeviceDateChange();
        //初始化提示-设备趋势图-月
        deviceReportService.initTipsForDeviceMonthChange();

    }
    init();

});