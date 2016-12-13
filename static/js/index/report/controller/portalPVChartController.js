/**
 * Created by Shin on 2016/03/03.
 * portal统计-趋势图 portalPVChartController
 */
indexApp.controller('portalPVChartController', function($scope,$location,portalReportService) {

    /**
     * datePVChartLine PV日趋势统计图
     * @author 沈亚芳
     * @date 2016/03/03
     */
    function datePVChartLine(x_date, chart_guidePv_data , chart_certificationPv_data, chart_transitionPv_data, chart_navigationPv_data, chart_totalPv_data){
        var data_PV_chart_line = echarts.init(document.getElementById('date_PV_chart_line'));

        var data_PV_chart_line_option = {
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
                        name: '引导页PV',
                        textStyle: {
                            color: '#66CBFF'
                        }
                    },
                    {
                        name: '认证页PV',
                        textStyle: {
                            color: '#66CC66'
                        }
                    },
                    {
                        name: '过渡页PV',
                        textStyle: {
                            color: '#FF6600'
                        }
                    },
                    {
                        name: '导航页PV',
                        textStyle: {
                            color: '#000000'
                        }
                    },
                    {
                        name: '总页面PV',
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
                    name: '引导页PV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#66CBFF'
                        }
                    },
                    data: chart_guidePv_data //[150, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                },
                {
                    name: '认证页PV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#66CC66'
                        }
                    },
                    data: chart_certificationPv_data //[220, 182, 191, 234, 290, 330, 310, 220, 182, 191, 234, 290, 330, 310, 220, 182, 191, 234, 290, 330, 310, 11, 44]
                },
                {
                    name: '过渡页PV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#FF6600'
                        }
                    },
                    data: chart_transitionPv_data //[110, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                },
                {
                    name: '导航页PV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#000000'
                        }
                    },
                    data: chart_navigationPv_data //[99, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                },
                {
                    name: '总页面PV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#d50000'
                        }
                    },
                    data: chart_totalPv_data //[75, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                }
            ]
        };

        data_PV_chart_line.setOption(data_PV_chart_line_option);
    }


    /**
     * datePVChartLine PV月趋势统计图
     * @author 沈亚芳
     * @date 2016/02/22
     */
    function monthPVChartLine(x_date, chart_guidePv_data, chart_certificationPv_data, chart_transitionPv_data, chart_navigationPv_data, chart_totalPv_data){
        var data_PV_chart_line = echarts.init(document.getElementById('month_PV_chart_line'));

        var data_PV_chart_line_option = {
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
                        name: '引导页PV',
                        textStyle: {
                            color: '#66CBFF'
                        }
                    },
                    {
                        name: '认证页PV',
                        textStyle: {
                            color: '#66CC66'
                        }
                    },
                    {
                        name: '过渡页PV',
                        textStyle: {
                            color: '#FF6600'
                        }
                    },
                    {
                        name: '导航页PV',
                        textStyle: {
                            color: '#000000'
                        }
                    },
                    {
                        name: '总页面PV',
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
                    name: '引导页PV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#66CBFF'
                        }
                    },
                    data: chart_guidePv_data //[150, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                },
                {
                    name: '认证页PV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#66CC66'
                        }
                    },
                    data: chart_certificationPv_data //[220, 182, 191, 234, 290, 330, 310, 220, 182, 191, 234, 290, 330, 310, 220, 182, 191, 234, 290, 330, 310, 11, 44]
                },
                {
                    name: '过渡页PV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#FF6600'
                        }
                    },
                    data: chart_transitionPv_data //[110, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                },
                {
                    name: '导航页PV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#000000'
                        }
                    },
                    data: chart_navigationPv_data //[99, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                },
                {
                    name: '总页面PV',
                    type: 'line',
                    lineStyle: {
                        normal: {
                            color: '#d50000'
                        }
                    },
                    data: chart_totalPv_data //[75, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 120, 132, 101, 134, 90, 230, 210, 44, 66]
                }
            ]
        };

        data_PV_chart_line.setOption(data_PV_chart_line_option);
    }


    /**
     * validatePVDateFail PV趋势图-日-校验
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/03/03
     */
    function validatePVDateFail($scope){
        //校验开始日期-日
        if(portalReportService.validatePVStartDateFail($scope)){
            return true;
        }
        //校验截止日期-日
        if(portalReportService.validatePVEndDateFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * chartsDate PV-日趋势图
     * @author 沈亚芳
     * @date 2016/03/03
     */
    function portalPVDate(){
        if(validatePVDateFail($scope)){
            return;
        }
        portalReportService.chartsPVDate($scope,  datePVChartLine);
    }
    $scope.chartsPVDate = portalPVDate;


    /**
     * validatePVMonthFail PV趋势图-月-校验
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/12/22
     */
    function validatePVMonthFail($scope){
        //校验开始日期-月
        if(portalReportService.validatePVStartMonthFail($scope)){
            return true;
        }
        //校验截止日期-月
        if(portalReportService.validatePVEndMonthFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * chartMonth PV-月趋势图
     * @author 沈亚芳
     * @date 2016/02/25
     */
    function portalPVMonth(){
        if(validatePVMonthFail($scope)){
            return;
        }
        portalReportService.chartPVMonth($scope, monthPVChartLine);
    }
    $scope.chartPVMonth = portalPVMonth;


    /**
     * exportPortalDateDetail 导出-> portal页面访问按日
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function exportPortalDateDetail (){
        if(validatePVDateFail($scope)){
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
        if(validatePVMonthFail($scope)){
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

        $scope.PVDateReport = {};
        $scope.PVMonthReport = {};

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
        $scope.PVMonthReport.startMonth = str1;
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
        $scope.PVMonthReport.endMonth = str2;

        //将之前列表查询条件中的开始日期和截止日期带入到折线图页面
        var startDate = $location.search()['startDate'];
        var endDate = $location.search()['endDate'];
        $scope.PVDateReport.startDate = startDate;
        $scope.PVDateReport.endDate = endDate;

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


        // PV-日趋势图(首次页面加载时，显示统计图用的)
        portalReportService.chartsPVDate($scope, datePVChartLine);
        // PV-月趋势图(首次页面加载时，显示统计图用的)
        portalReportService.chartPVMonth($scope, monthPVChartLine);


        //初始化提示-PV趋势图-日
        portalReportService.initTipsForPVDateChange();
        //初始化提示-PV趋势图-月
        portalReportService.initTipsForPVMonthChange();
    }
    init();
});