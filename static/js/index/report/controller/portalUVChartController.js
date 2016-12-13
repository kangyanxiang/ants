/**
 * Created by Shin on 2016/2/19.
 * portal统计-趋势图 portalUVChartController
 */
indexApp.controller('portalUVChartController', function($scope,$location,portalReportService) {

    /**
     * dateUVChartLine UV日趋势统计图
     * @author 沈亚芳
     * @date 2016/03/03
     */
    function dateUVChartLine(x_date, chart_guideUv_data , chart_certificationUv_data, chart_transitionUv_data, chart_navigationUv_data, chart_totalUv_data){
        var data_UV_chart_line = echarts.init(document.getElementById('date_UV_chart_line'));

        var data_UV_chart_line_option = {
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
                        name: '引导页UV',
                        textStyle: {
                            color: '#66CBFF'
                        }
                    },
                    {
                        name: '认证页UV',
                        textStyle: {
                            color: '#66CC66'
                        }
                    },
                    {
                        name: '过渡页UV',
                        textStyle: {
                            color: '#FF6600'
                        }
                    },
                    {
                        name: '导航页UV',
                        textStyle: {
                            color: '#000000'
                        }
                    },
                    {
                        name: '总页面UV',
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
                    name: '引导页UV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#66CBFF'
                        }
                    },
                    data: chart_guideUv_data //[150, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                },
                {
                    name: '认证页UV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#66CC66'
                        }
                    },
                    data: chart_certificationUv_data //[220, 182, 191, 234, 290, 330, 310, 220, 182, 191, 234, 290, 330, 310, 220, 182, 191, 234, 290, 330, 310, 11, 44]
                },
                {
                    name: '过渡页UV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#FF6600'
                        }
                    },
                    data: chart_transitionUv_data //[110, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                },
                {
                    name: '导航页UV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#000000'
                        }
                    },
                    data: chart_navigationUv_data //[99, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                },
                {
                    name: '总页面UV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#d50000'
                        }
                    },
                    data: chart_totalUv_data //[75, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                }
            ]
        };

        data_UV_chart_line.setOption(data_UV_chart_line_option);
    }


    /**
     * dateUVChartLine UV月趋势统计图
     * @author 沈亚芳
     * @date 2016/03/03
     */
    function monthUVChartLine(x_date, chart_guideUv_data, chart_certificationUv_data, chart_transitionUv_data, chart_navigationUv_data, chart_totalUv_data){
        var data_UV_chart_line = echarts.init(document.getElementById('month_UV_chart_line'));

        var data_UV_chart_line_option = {
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
                        name: '引导页UV',
                        textStyle: {
                            color: '#66CBFF'
                        }
                    },
                    {
                        name: '认证页UV',
                        textStyle: {
                            color: '#66CC66'
                        }
                    },
                    {
                        name: '过渡页UV',
                        textStyle: {
                            color: '#FF6600'
                        }
                    },
                    {
                        name: '导航页UV',
                        textStyle: {
                            color: '#000000'
                        }
                    },
                    {
                        name: '总页面UV',
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
                    name: '引导页UV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#66CBFF'
                        }
                    },
                    data: chart_guideUv_data //[150, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                },
                {
                    name: '认证页UV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#66CC66'
                        }
                    },
                    data: chart_certificationUv_data //[220, 182, 191, 234, 290, 330, 310, 220, 182, 191, 234, 290, 330, 310, 220, 182, 191, 234, 290, 330, 310, 11, 44]
                },
                {
                    name: '过渡页UV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#FF6600'
                        }
                    },
                    data: chart_transitionUv_data //[110, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                },
                {
                    name: '导航页UV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#000000'
                        }
                    },
                    data: chart_navigationUv_data //[99, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                },
                {
                    name: '总页面UV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#d50000'
                        }
                    },
                    data: chart_totalUv_data //[75, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                }
            ]
        };

        data_UV_chart_line.setOption(data_UV_chart_line_option);
    }


    /**
     * validateUVDateFail UV趋势图-日-校验
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/02/25
     */
    function validateUVDateFail($scope){
        //校验开始日期-日
        if(portalReportService.validateUVStartDateFail($scope)){
            return true;
        }
        //校验截止日期-日
        if(portalReportService.validateUVEndDateFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * chartsDate UV-日趋势图
     * @author 沈亚芳
     * @date 2016/02/25
     */
    function portalUVDate(){
        if(validateUVDateFail($scope)){
            return;
        }
        portalReportService.chartsUVDate($scope,  dateUVChartLine);
    }
    $scope.chartsUVDate = portalUVDate;


    /**
     * validatePVMonthFail PV趋势图-月-校验
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/02/25
     */
    function validateUVMonthFail($scope){
        //校验开始日期-月
        if(portalReportService.validateUVStartMonthFail($scope)){
            return true;
        }
        //校验截止日期-月
        if(portalReportService.validateUVEndMonthFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * chartMonth UV-月趋势图
     * @author 沈亚芳
     * @date 2016/02/25
     */
    function portalUVMonth(){
        if(validateUVMonthFail($scope)){
            return;
        }
        portalReportService.chartUVMonth($scope, monthUVChartLine);
    }
    $scope.chartUVMonth = portalUVMonth;


    /**
     * exportPortalDateDetail 导出-> portal页面访问按日
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function exportPortalDateDetail (){
        if(validateUVDateFail($scope)){
            return;
        }
        portalReportService.exportPortalDateDetail($scope);
    }
    $scope.exportPortalDateDetail = exportPortalDateDetail;


    /**
     * exportPortalMonthDetail 导出-> portal页面访问按月
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function exportPortalMonthDetail (){
        if(validateUVMonthFail($scope)){
            return;
        }
        portalReportService.exportPortalMonthDetail($scope);
    }
    $scope.exportPortalMonthDetail = exportPortalMonthDetail;


    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2016/03/03
     */
    function init(){
        $scope.UVDateReport = {};
        $scope.UVMonthReport = {};

        var locationId = $location.search()['locationId'];
        $scope.locationId = locationId;

        var locationName = $location.search()['locationName'];
        $scope.locationName = locationName;

        var platformId = $location.search()['platformId'];
        $scope.platformId = platformId;

        var projectName = $location.search()['projectName'];
        $scope.projectName = projectName;

        var provinceId = $location.search()['provinceId'];
        $scope.provinceId = provinceId;

        var cityId = $location.search()['cityId'];
        $scope.cityId = cityId;

        var countyId = $location.search()['countyId'];
        $scope.countyId = countyId;

        var entityType = $location.search()['entityType'];
        $scope.entityType = entityType;

        //月趋势图：默认显示当前年一月至当前月的数据
        var myDate = new Date();
        var str1 = "" + myDate.getFullYear() + "-";
        str1 += "01";
        $scope.UVMonthReport.startMonth = str1;
        var str2 = "" + myDate.getFullYear() + "-";
        if((myDate.getMonth()+1) >=1 && (myDate.getMonth()+1) < 10 ){
            str2 += '0' + (myDate.getMonth()+1);
        }else{
            str2 += (myDate.getMonth()+1);
        }
        //if (myDate.getDate() >=1 && myDate.getDate() < 10){
        //    str2 += '0' + myDate.getDate();
        //} else {
        //    str2 += myDate.getDate();
        //}
        $scope.UVMonthReport.endMonth = str2;

        //将之前列表查询条件中的开始日期和截止日期带入到折线图页面
        var startDate = $location.search()['startDate'];
        var endDate = $location.search()['endDate'];
        $scope.UVDateReport.startDate = startDate;
        $scope.UVDateReport.endDate = endDate;

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


        // UV-日趋势图(首次页面加载时，显示统计图用的)
        portalReportService.chartsUVDate($scope, dateUVChartLine);
        // UV-月趋势图(首次页面加载时，显示统计图用的)
        portalReportService.chartUVMonth($scope, monthUVChartLine);


        //初始化提示-UV趋势图-日
        portalReportService.initTipsForUVDateChange();
        //初始化提示-UV趋势图-月
        portalReportService.initTipsForUVMonthChange();

    }
    init();

});