/**
 * Created by Shin on 2016/03/16.
 * 设备统计 deviceReportDao
 */
indexApp.factory('deviceReportDao', function($http, $location, pageDao) {
    /**
     * service 定义个全局dao，并将其置空
     * @param 空
     * @author 沈亚芳
     * @date 2016/03/16
     */
    var dao = {};


    /**
     * list 设备统计-列表
     * @param 空
     * @author 沈亚芳
     * @date 2016/03/16
     */
    dao.list = function($scope){
        loadingAppend();
        var startDate = defaultString($scope.startDate);
        var endDate = defaultString($scope.endDate);
        var provinceId = $scope.provinceId;
        var cityId = $scope.cityId;
        var countyId = $scope.countyId;

        if(isBlank(provinceId)){
            provinceId = '';
        }
        if(isBlank(cityId)){
            cityId = '';
        }
        if(isBlank(countyId)){
            countyId = '';
        }

        var url = '/devicereport/list?startDate=' + startDate + '&endDate=' + endDate + '&provinceId=' + provinceId + '&cityId=' + cityId + '&countyId=' + countyId;

        $http.get(url)
            .success(function(data){
                loadingRemove();
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                //数据记录集
                $scope.deviceReports = data.devicesList;

                //总计数据记录集
                $scope.totalDevices = data.totalDevices;

                //将变更过的日期数据，在未进行点击“查询”按钮时将老的日期数据重新进行赋值
                $scope.oldStartDate = $scope.startDate;
                $scope.oldendDate = $scope.endDate;
                $scope.oldProvinceId = $scope.provinceId;
                $scope.oldCityId = $scope.cityId;
                $scope.oldCountyId = $scope.countyId;
            })
    };


    /**
     * exportDeviceReports 导出设备统计-列表信息
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/16
     */
    dao.exportDeviceReports = function($scope){
        $('#exportExcle').submit();
    };


    /**
     * commafy 格式化数字千分位
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    function commafy(num) {
        num = num + '';
        var re = /(-?\d+)(\d{3})/;
        while (re.test(num)) {
            num = num.replace(re, "$1,$2");
        }
        return num;
    }


    /**
     * chartsDeviceDate 设备发展按日趋势
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    dao.chartsDeviceDate = function($scope, funDeviceDateLine){
        loadingAppend();
        var statType = 'D';
        var locationId = $scope.locationId;
        var startDate = defaultString($scope.deviceDateReport.startDate);
        var endDate = defaultString($scope.deviceDateReport.endDate);
        var provinceId = $scope.provinceId;
        var cityId = $scope.cityId;
        var countyId = $scope.countyId;

        if(isBlank(provinceId)){
            provinceId = '';
        }
        if(isBlank(cityId)){
            cityId = '';
        }
        if(isBlank(countyId)){
            countyId = '';
        }

        var url ='/devicereport/chart?statType=' + statType + '&locationId=' + locationId + '&startDate=' + startDate + '&endDate=' + endDate + '&provinceId=' + provinceId + '&cityId=' + cityId + '&countyId=' + countyId;

        $http.get(url)
            .success(function(resp){
                loadingRemove();
                if(resp.result == 'FAIL'){
                    jDialog.alert('提示：', resp.message);
                    return;
                }
                //初始化（X轴数据，项目型数据，中小商户数据，小计数据）
                var x_date = resp.x_date,
                    chart_projectTotal_data = resp.projectTotal,
                    chart_sMTotal_data = resp.sMTotal,
                    chart_subTotal_data = resp.subTotal;

                //var x_date = ['2016/03/01', '2016/03/02', '2016/03/03', '2016/03/04', '2016/03/05', '2016/03/06', '2016/03/07', '2016/03/08', '2016/03/09', '2016/03/10', '2016/03/11', '2016/03/12', '2016/03/13', '2016/03/14', '2016/03/15', '2016/03/16', '2016/03/17'],
                //    chart_projectTotal_data = [120, 150, 130, 120, 120, 150, 130, 120, 120, 150, 130, 120, 120, 150, 130, 120, 190],
                //    chart_sMTotal_data = [107, 139, 121, 101, 107, 139, 121, 101, 107, 139, 121, 101, 107, 139, 121, 101, 185],
                //    chart_subTotal_data = [227, 289, 250, 221, 227, 289, 250, 221, 227, 289, 250, 221, 227, 289, 250, 221, 227];

                funDeviceDateLine(x_date, chart_projectTotal_data, chart_sMTotal_data, chart_subTotal_data);
            })
    };


    /**
     * chartsDeviceMonth 设备发展按月趋势
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    dao.chartsDeviceMonth = function($scope, funDeviceMonthLine){
        loadingAppend();
        var statType = 'M';
        var locationId = $scope.locationId;
        var startDate = defaultString($scope.deviceMonthReport.startMonth);
        var endDate = defaultString($scope.deviceMonthReport.endMonth);
        var provinceId = $scope.provinceId;
        var cityId = $scope.cityId;
        var countyId = $scope.countyId;

        if(isBlank(provinceId)){
            provinceId = '';
        }
        if(isBlank(cityId)){
            cityId = '';
        }
        if(isBlank(countyId)){
            countyId = '';
        }

        var url ='/devicereport/chart?statType=' + statType + '&locationId=' + locationId + '&startDate=' + startDate + '&endDate=' + endDate + '&provinceId=' + provinceId + '&cityId=' + cityId + '&countyId=' + countyId;

        $http.get(url)
            .success(function(resp){
                loadingRemove();
                if(resp.result == 'FAIL'){
                    jDialog.alert('提示：', resp.message);
                    return;
                }
                //初始化（X轴数据，项目型数据，中小商户数据，小计数据）
                var x_date = resp.x_date,
                    chart_projectTotal_data = resp.projectTotal,
                    chart_sMTotal_data = resp.sMTotal,
                    chart_subTotal_data = resp.subTotal;

                //var x_date = ['2016/01', '2016/02', '2016/03'],
                //    chart_projectTotal_data = [120, 150, 130],
                //    chart_sMTotal_data = [107, 139, 121],
                //    chart_subTotal_data = [227, 289, 250];

                funDeviceMonthLine(x_date, chart_projectTotal_data, chart_sMTotal_data, chart_subTotal_data);
            })
    };


    /**
     * exportDeviceChartDate 导出设备统计>趋势>按日导出
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    dao.exportDeviceChartDate = function($scope){
        $('#exportDeviceChartDate').submit();
    };


    /**
     * exportDeviceChartMonth 导出设备统计>趋势>按月导出
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    dao.exportDeviceChartMonth = function($scope){
        $('#exportDeviceChartMonth').submit();
    };


    /**
     * deviceKeyIndexList 地区设备关键指标数据列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    dao.deviceKeyIndexList = function($scope){
        loadingAppend();
        var locationId = $scope.locationId;
        var startDate = defaultString($scope.startDate);
        var endDate = defaultString($scope.endDate);
        var provinceId = $scope.provinceId;
        var cityId = $scope.cityId;
        var countyId = $scope.countyId;

        if(isBlank(provinceId)){
            provinceId = '';
        }
        if(isBlank(cityId)){
            cityId = '';
        }
        if(isBlank(countyId)){
            countyId = '';
        }

        var url = '/devicereport/keyindex?locationId=' + locationId + '&startDate=' + startDate + '&endDate=' + endDate + '&provinceId=' + provinceId + '&cityId=' + cityId + '&countyId=' + countyId;

        $http.get(url)
            .success(function(data){
                loadingRemove();
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                //数据记录集
                $scope.deviceKeyIndexs = data.devicesList;
                //开始记录数
                $scope.begin = data.begin;
                $scope.data = data;
                //初始化分页栏
                //pageDao.init($scope, data);
            })
    };


    /**
     * exportKeyIndexExcle 导出设备统计>关键指标列表数据
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    dao.exportKeyIndexExcle = function($scope){
        $('#exportKeyIndexExcle').submit();
    };


    /**
     * deviceProjectReportList 项目型设备按类型统计
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    dao.deviceProjectReportList = function($scope){
        loadingAppend();
        var startDate = defaultString($scope.startDate);
        var endDate = defaultString($scope.endDate);
        var provinceId = $scope.provinceId;
        var cityId = $scope.cityId;
        var countyId = $scope.countyId;
        var platformId = $scope.platformId;
        var projectName = defaultString($scope.projectName);


        if(isBlank(provinceId)){
            provinceId = '';
        }
        if(isBlank(cityId)){
            cityId = '';
        }
        if(isBlank(countyId)){
            countyId = '';
        }
        if(isBlank(platformId)){
            platformId = '';
        }

        //如果是汉字,用encodeURIComponent转码
        projectName = encodeURIComponent(projectName);

        var url = '/devicereport/projectreportlist?startDate=' + startDate + '&endDate=' + endDate + '&provinceId=' + provinceId + '&cityId=' + cityId + '&countyId=' + countyId + '&platformId=' + platformId + '&projectName=' + projectName;

        $http.get(url)
            .success(function(data){
                loadingRemove();
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                //数据记录集
                $scope.deviceProjectReports = data.DeviceTypeStatisticsList;

                //总计
                $scope.DeviceTypeStatisticsSum = data.DeviceTypeStatisticsSum;
            })
    };


    /**
     * exportDeviceProjectExcle 导出->项目型设备按类型统计
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    dao.exportDeviceProjectExcle = function($scope){
        $('#exportDeviceProjectExcle').submit();
    };


    /**
     * 返回 dao
     * @author 沈亚芳
     * @date 2016/03/16
     */
    return dao;
});