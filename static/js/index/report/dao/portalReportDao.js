/**
 * Created by Shin on 2016/03/03.
 * portal统计 portalReportDao
 */
indexApp.factory('portalReportDao', function($http, $location, pageDao) {
    /**
     * service 定义个全局dao，并将其置空
     * @param 空
     * @author 沈亚芳
     * @date 2016/03/03
     */
    var dao = {};


    /**
     * portalReportList portal统计-列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/03
     */
    dao.list = function($scope){
        loadingAppend();
        var startDate = defaultString($scope.startDate);
        var endDate = defaultString($scope.endDate);
        var entityType = $scope.entityType;
        var provinceId = $scope.provinceId;
        var cityId = $scope.cityId;
        var countyId = $scope.countyId;
        var platformId = $scope.platformId;
        var projectName = defaultString($scope.projectName);

        //如果是undefined，则置''
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
        if(isBlank(entityType)){
            entityType = '';
        }

        //如果是汉字,用encodeURIComponent转码
        projectName = encodeURIComponent(projectName);
        entityType = encodeURIComponent(entityType);

        //    /portalreport/list
        var url ='/portalreport/list?startDate=' + startDate + '&endDate=' + endDate + '&provinceId=' + provinceId + '&cityId=' + cityId + '&countyId=' + countyId + '&entityType=' + entityType + '&platformId=' + platformId + '&projectName=' + projectName;

        $http.get(url)
            .success(function(data){
                loadingRemove();
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                portalReports = data.pvuvList;
                //数据记录集
                $scope.portalReports = data.pvuvList;

                //总计数据记录集
                $scope.totalPortals = data.totailPvUv;

                //将变更过的日期数据，在未进行点击“查询”按钮时将老的日期数据重新进行赋值
                $scope.oldStartDate = $scope.startDate;
                $scope.oldendDate = $scope.endDate;
                $scope.oldEntityType = $scope.entityType;
                $scope.oldPlatformId = $scope.platformId;
                $scope.oldProjectName = $scope.projectName;
                $scope.oldProvinceId = $scope.provinceId;
                $scope.oldCityId = $scope.cityId;
                $scope.oldCountyId = $scope.countyId;

            })
    };


    ///**
    // * totalList portal统计-总计汇总方法
    // * @param $scope
    // * @author 沈亚芳
    // * @date 2016/03/03
    // */
    //dao.totalList = function($scope){
    //    var startDate = defaultString($scope.startDate);
    //    var endDate = defaultString($scope.endDate);
    //    var provinceId = $scope.provinceId;
    //    var cityId = $scope.cityId;
    //    var countyId = $scope.countyId;
    //    var platformId = $scope.platformId;
    //    var projectName = defaultString($scope.projectName);
    //
    //    var myDate = new Date();
    //    //初始化开始日期
    //    var str1 = "" + myDate.getFullYear() + "-";
    //    if((myDate.getMonth()+1) >= 1 && (myDate.getMonth()+1) < 10){
    //        str1 += '0' + (myDate.getMonth()+1) + "-";
    //    }else {
    //        str1 += (myDate.getMonth()+1) + "-";
    //    }
    //    str1 += '01';
    //
    //    //初始化截止日期
    //    var str2 = "" + myDate.getFullYear() + "-";
    //    if((myDate.getMonth()+1) >= 1 && (myDate.getMonth()+1) < 10){
    //        str2 += '0' + (myDate.getMonth()+1) + "-";
    //    }else {
    //        str2 += (myDate.getMonth()+1) + "-";
    //    }
    //    if(myDate.getDate() >= 1 && myDate.getDate() < 10){
    //        str2 += '0' + myDate.getDate();
    //    }else {
    //        str2 += myDate.getDate();
    //    }
    //
    //    if(startDate == '' || startDate == 'undefined'){
    //        startDate = str1;
    //    }
    //    if(endDate == '' || endDate == 'undefined'){
    //        endDate = str2;
    //    }
    //
    //    //如果是undefined，则置''
    //    if(isBlank(provinceId)){
    //        provinceId = '';
    //    }
    //    if(isBlank(cityId)){
    //        cityId = '';
    //    }
    //    if(isBlank(countyId)){
    //        countyId = '';
    //    }
    //    if(isBlank(platformId)){
    //        platformId = '';
    //    }
    //
    //    //如果是汉字,用encodeURIComponent转码
    //    projectName = encodeURIComponent(projectName);
    //
    //    var url ='/portalreport/list?startDate=' + startDate + '&endDate=' + endDate + '&provinceId=' + provinceId + '&cityId=' + cityId + '&countyId=' + countyId + '&platformId=' + platformId + '&projectName=' + projectName;
    //
    //    $http.get(url)
    //        .success(function(data){
    //            if(data.result == 'FAIL'){
    //                jDialog.alert('提示：', data.message);
    //                return;
    //            }
    //            //数据记录集
    //            $scope.totalPortals = data.totailPvUv;
    //        })
    //};


    /**
     * exportPortalReports 导出portal统计=列表信息
     * @param $scope
     * @author 沈亚芳
     * @date 2016/02/22
     */
    dao.exportPortalReports = function($scope){
        $('#exportExcle').submit();
    };


    /**
     * commafy 格式化数字千分位
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/03
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
     * chartsPVDate PV趋势图-日
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/03
     */
    dao.chartsPVDate = function($scope, funPVDateLine){
        loadingAppend();
        var statType = 'D';
        var locationId = $scope.locationId;
        var startDate = defaultString($scope.PVDateReport.startDate);
        var endDate = defaultString($scope.PVDateReport.endDate);
        var platformId = $scope.platformId;
        var projectName = defaultString($scope.projectName);

        var provinceId = $scope.provinceId;
        var cityId = $scope.cityId;
        var countyId = $scope.countyId;

        var entityType = $scope.entityType;

        //如果是汉字,用encodeURIComponent转码
        projectName = encodeURIComponent(projectName);
        entityType = encodeURIComponent(entityType);

        var url = '/portalreport/trend?statType=' + statType + '&locationId=' + locationId + '&startDate=' + startDate + '&endDate=' + endDate + '&platformId=' + platformId + '&projectName=' + projectName + '&provinceId=' + provinceId + '&cityId=' + cityId + '&countyId=' + countyId + '&entityType=' + entityType;
        $http.get(url)
            .success(function(resp){
                loadingRemove();
                if(resp.result == 'FAIL'){
                    jDialog.alert('提示：', resp.message);
                    return;
                }


                //初始化（日数据，引导页PV数据，认证页PV数据，过渡页PV数据，导航页PV数据，页面总PV数据）
                var x_date = resp.x_date,
                    chart_guidePv_data = resp.guidePv,
                    chart_certificationPv_data = resp.certificationPv,
                    chart_transitionPv_data = resp.transitionPv,
                    chart_navigationPv_data = resp.navigationPv,
                    chart_totalPv_data = resp.totalPv;


                //var x_date = ['2016/02/01', '2016/02/02', '2016/02/03', '2016/02/04', '2016/02/05', '2016/02/06', '2016/02/07', '2016/02/08', '2016/02/09', '2016/02/10', '2016/02/11', '2016/02/12', '2016/02/13', '2016/02/14', '2016/02/15', '2016/02/16', '2016/02/17', ],
                //    chart_guidePv_data = [120, 150, 130, 120, 120, 150, 130, 120, 120, 150, 130, 120, 120, 150, 130, 120, 190],
                //    chart_certificationPv_data = [107, 139, 121, 101, 107, 139, 121, 101, 107, 139, 121, 101, 107, 139, 121, 101, 185],
                //    chart_transitionPv_data = [99, 127, 115, 97, 99, 127, 115, 97, 99, 127, 115, 97, 99, 127, 115, 97, 99],
                //    chart_navigationPv_data = [93, 125, 113, 87, 93, 125, 113, 87, 93, 125, 113, 87, 93, 125, 113, 87, 95],
                //    chart_totalPv_data = [419, 541, 479, 405, 419, 541, 479, 405, 419, 541, 479, 405, 419, 541, 479, 405, 569];

                funPVDateLine(x_date,  chart_guidePv_data , chart_certificationPv_data, chart_transitionPv_data, chart_navigationPv_data, chart_totalPv_data);

            })
    };


    /**
     * chartPVMonth PV趋势图-月
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/03
     */
    dao.chartPVMonth = function($scope, funPVMonthLine){
        loadingAppend();
        var statType = 'M';
        var locationId = $scope.locationId;
        var startDate = defaultString($scope.PVMonthReport.startMonth);
        var endDate = defaultString($scope.PVMonthReport.endMonth);
        var platformId = $scope.platformId;
        var projectName = defaultString($scope.projectName);

        var provinceId = $scope.provinceId;
        var cityId = $scope.cityId;
        var countyId = $scope.countyId;

        var entityType = $scope.entityType;

        //如果是汉字,用encodeURIComponent转码
        projectName = encodeURIComponent(projectName);
        entityType = encodeURIComponent(entityType);

        var url = '/portalreport/trend?statType=' + statType + '&locationId=' + locationId + '&startDate=' + startDate + '&endDate=' + endDate + '&platformId=' + platformId + '&projectName=' + projectName + '&provinceId=' + provinceId + '&cityId=' + cityId + '&countyId=' + countyId + '&entityType=' + entityType;

        $http.get(url)
            .success(function(resp){
                loadingRemove();
                if(resp.result == 'FAIL'){
                    jDialog.alert('提示：', resp.message);
                    return;
                }

                //初始化（日数据，引导页PV数据，认证页PV数据，过渡页PV数据，导航页PV数据，页面总PV数据）
                var x_date = resp.x_date,
                    chart_guidePv_data = resp.guidePv,
                    chart_certificationPv_data = resp.certificationPv,
                    chart_transitionPv_data = resp.transitionPv,
                    chart_navigationPv_data = resp.navigationPv,
                    chart_totalPv_data = resp.totalPv;


                //var x_date = ['2016/01', '2016/02'],
                //    chart_guidePv_data = [12536, 25637],
                //    chart_certificationPv_data = [9325, 19536],
                //    chart_transitionPv_data = [5625, 13569],
                //    chart_navigationPv_data = [3236, 11532],
                //    chart_totalPv_data = [30722, 70274];


                funPVMonthLine(x_date, chart_guidePv_data, chart_certificationPv_data, chart_transitionPv_data, chart_navigationPv_data, chart_totalPv_data);

            })

    };


    /**
     * chartsUVDate UV趋势图-日
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/03
     */
    dao.chartsUVDate = function($scope, funUVDateLine){
        loadingAppend();
        var statType = 'D';
        var locationId = $scope.locationId;
        var startDate = defaultString($scope.UVDateReport.startDate);
        var endDate = defaultString($scope.UVDateReport.endDate);
        var platformId = $scope.platformId;
        var projectName = defaultString($scope.projectName);

        var provinceId = $scope.provinceId;
        var cityId = $scope.cityId;
        var countyId = $scope.countyId;

        var entityType = $scope.entityType;

        //如果是汉字,用encodeURIComponent转码
        projectName = encodeURIComponent(projectName);
        entityType = encodeURIComponent(entityType);

        var url = '/portalreport/trend?statType=' + statType + '&locationId=' + locationId + '&startDate=' + startDate + '&endDate=' + endDate + '&platformId=' + platformId + '&projectName=' + projectName + '&provinceId=' + provinceId + '&cityId=' + cityId + '&countyId=' + countyId + '&entityType=' + entityType;
        $http.get(url)
            .success(function(resp){
                loadingRemove();
                if(resp.result == 'FAIL'){
                    jDialog.alert('提示：', resp.message);
                    return;
                }

                //初始化（日数据，引导页UV数据，认证页UV数据，过渡页UV数据，导航页UV数据，页面总UV数据）
                var x_date = resp.x_date,
                    chart_guideUv_data = resp.guideUv,
                    chart_certificationUv_data = resp.certificationUv,
                    chart_transitionUv_data = resp.transitionUv,
                    chart_navigationUv_data = resp.navigationUv,
                    chart_totalUv_data = resp.totalUv;


                //var x_date = ['2016/02/01', '2016/02/02', '2016/02/03', '2016/02/04', '2016/02/05', '2016/02/06', '2016/02/07', '2016/02/08', '2016/02/09', '2016/02/10', '2016/02/11', '2016/02/12', '2016/02/13', '2016/02/14', '2016/02/15', '2016/02/16', '2016/02/17', ],
                //    chart_guideUv_data = [120, 150, 130, 120, 120, 150, 130, 120, 120, 150, 130, 120, 120, 150, 130, 120, 190],
                //    chart_certificationUv_data = [107, 139, 121, 101, 107, 139, 121, 101, 107, 139, 121, 101, 107, 139, 121, 101, 185],
                //    chart_transitionUv_data = [99, 127, 115, 97, 99, 127, 115, 97, 99, 127, 115, 97, 99, 127, 115, 97, 99],
                //    chart_navigationUv_data = [93, 125, 113, 87, 93, 125, 113, 87, 93, 125, 113, 87, 93, 125, 113, 87, 95],
                //    chart_totalUv_data = [419, 541, 479, 405, 419, 541, 479, 405, 419, 541, 479, 405, 419, 541, 479, 405, 569];

                funUVDateLine(x_date,  chart_guideUv_data , chart_certificationUv_data, chart_transitionUv_data, chart_navigationUv_data, chart_totalUv_data);

            })
    };


    /**
     * chartUVMonth UV趋势图-月
     * @param $scope
     * @author 沈亚芳
     * @date 2016/02/25
     */
    dao.chartUVMonth = function($scope, funUVMonthLine){
        loadingAppend();
        var statType = 'M';
        var locationId = $scope.locationId;
        var startDate = defaultString($scope.UVMonthReport.startMonth);
        var endDate = defaultString($scope.UVMonthReport.endMonth);
        var platformId = $scope.platformId;
        var projectName = defaultString($scope.projectName);

        var provinceId = $scope.provinceId;
        var cityId = $scope.cityId;
        var countyId = $scope.countyId;

        var entityType = $scope.entityType;

        //如果是汉字,用encodeURIComponent转码
        projectName = encodeURIComponent(projectName);
        entityType = encodeURIComponent(entityType);

        var url = '/portalreport/trend?statType=' + statType + '&locationId=' + locationId + '&startDate=' + startDate + '&endDate=' + endDate + '&platformId=' + platformId + '&projectName=' + projectName + '&provinceId=' + provinceId + '&cityId=' + cityId + '&countyId=' + countyId + '&entityType=' + entityType;

        $http.get(url)
            .success(function(resp){
                loadingRemove();
                if(resp.result == 'FAIL'){
                    jDialog.alert('提示：', resp.message);
                    return;
                }

                //初始化（日数据，引导页UV数据，认证页UV数据，过渡页UV数据，导航页UV数据，页面总UV数据）
                var x_date = resp.x_date,
                    chart_guideUv_data = resp.guideUv,
                    chart_certificationUv_data = resp.certificationUv,
                    chart_transitionUv_data = resp.transitionUv,
                    chart_navigationUv_data = resp.navigationUv,
                    chart_totalUv_data = resp.totalUv;


                //var x_date = ['2015/10', '2015/11', '2015/12', '2016/01', '2016/02'],
                //    chart_guideUv_data = [9753, 19263, 29835, 12536, 25637],
                //    chart_certificationUv_data = [9325, 15736, 27563, 9325, 19536],
                //    chart_transitionUv_data = [7356, 12365, 21002, 5625, 13569],
                //    chart_navigationUv_data = [5324, 9327, 15732, 3236, 11532],
                //    chart_totalUv_data = [31758, 56691, 94132, 30722, 70274];


                funUVMonthLine(x_date, chart_guideUv_data, chart_certificationUv_data, chart_transitionUv_data, chart_navigationUv_data, chart_totalUv_data);

            })

    };


    /**
     * exportPortalDateDetail 导出-> portal页面访问按日
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    dao.exportPortalDateDetail = function($scope){
        $('#exportPortalDateDetail').submit();
    };


    /**
     * exportPortalMonthDetail 导出-> portal页面访问按月
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/30
     */
    dao.exportPortalMonthDetail = function($scope){
        $('#exportPortalMonthDetail').submit();
    };


    /**
     * 返回 dao
     * @author 沈亚芳
     * @date 2016/03/03
     */
    return dao;
});