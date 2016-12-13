/**
 * Created by 牛华凤 on 2015/11/26.
 * 项目管理 projectDao
 */
indexApp.factory('projectDao',function($http, $location, locationDao, pageDao){
    /**
     * dao 定义个全局dao，并将其置空
     * @param 空
     * @author 牛华凤
     * @date 2015/11/26
     */
    var dao = {};


    /**
     * projectNameList 获取项目名称集合，用于select
     * @param $scope
     * @author 牛华凤
     * @date 2015/11/26
     */
    dao.projectNameList = function($scope){
        var keywords = defaultString($scope.customer.projectId);
        var url = "/project/projectList?keywords=" + keywords;
        $http.get(url).success(function(data, status, headers, config){
            if(data.result == 'FAIL'){
                jDialog.alert(data.message);
                return;
            }
            projectNames = data.data;
            $scope.projectNames = data.data;
        });
    };


    /**
     * projectList 项目管理-项目列表
     * @param $scope 获取列表时要提交的参数为：pageNo、keywords
     * @author 沈亚芳
     * @date 2015/12/08
     */
    dao.projectList = function($scope){
        loadingAppend();
        var pageNo = pageDao.getPageNo();
        var keywords = defaultString($scope.keywords);
        var platformId = $scope.platformId;
        if(isBlank(platformId)){
            platformId = '';
        }
        if(pageNo == "上一页" || pageNo == "下一页"){
        	return;
        }

        var provinceId = $scope.provinceId;
        var cityId = $scope.cityId;
        var areaId = $scope.areaId;
        if(isBlank(provinceId)){
            provinceId = '';
        }
        if(isBlank(cityId)){
            cityId = '';
        }
        if(isBlank(areaId)){
            areaId = '';
        }

        var url ='project/list?pageNo=' + pageNo + '&platformId=' + platformId + '&keywords=' + keywords + '&provinceId=' + provinceId + '&cityId=' + cityId + '&areaId=' + areaId;
        $http.get(url)
            .success(function(data){
                loadingRemove();
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    return;
                }
                projects = data.records;
                //数据记录集
                $scope.projects = data.records;
                //开始记录数
                $scope.begin = data.begin;
                $scope.data = data;
                //初始化分页栏
                pageDao.init($scope, data);
            })
    };


    /**
     * delete 根据id删除对应的项目
     * @param $scope 获取列表时要提交的参数为：id
     * @author 沈亚芳
     * @date 2015/12/08
     */
    dao.delete = function($scope, projectId){

        var url = 'project/delete?id=' + projectId;

        $http.get(url)
            .success(function(data, status, headers, config) {
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                jDialog.alert('项目删除：', data.message || '删除成功');
                $scope.list();
            });
    };


    /**
     * getAllPlatformInfo 获取所有登录平台信息，用于select
     * @param $scope
     * @author 沈亚芳
     * @date 2015/12/11
     */
    dao.getAllPlatformInfo = function($scope){
        var url ='platform/platforminfo';
        $http.get(url)
            .success(function(data){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示', data.message);
                    return;
                }
                platforms = data.data;
                $scope.platforms = data.data;
            })

    };


    /**
     * add 项目管理-添加新项目
     * @param $scope 添加项目时要提交的参数为：projectName、contact、contactWay、platfrom、remark
     * @author 沈亚芳
     * @date 2015/12/08
     */
    dao.add = function($scope){
        $('#projectAdd').attr('disabled',true);
        var projectName = defaultString($scope.project.projectName);
        var contact = defaultString($scope.project.contact);
        var contactWay = defaultString($scope.project.contactWay);
        var platform = $scope.project.platformId;
        var remark = defaultString($scope.project.remark);

        var provinceId = $scope.project.provinceId;
        var cityId = $scope.project.cityId;
        var areaId = $scope.project.areaId;

        //如果是汉字,用encodeURIComponent转码
        projectName = encodeURIComponent(projectName);
        contact = encodeURIComponent(contact);
        remark = encodeURIComponent(remark);

        if(isBlank(platform)){
            platform = '';
        }
        if(isBlank(provinceId)){
            provinceId = '';
        }
        if(isBlank(cityId)){
            cityId = '';
        }
        if(isBlank(areaId)){
            areaId = '';
        }
        if(isBlank(remark)){
            remark = '';
        }

        var url ='project/add?projectName=' + projectName + '&contact=' + contact + '&contactWay=' + contactWay + '&platform=' + platform + '&provinceId=' + provinceId + '&cityId=' + cityId + '&areaId=' + areaId + '&remark=' + remark;

        $http.get(url)
            .success(function(data){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    $('#projectAdd').removeAttr('disabled');
                    return;
                }
                jDialog.alert('添加新项目:', '添加成功！');
                $scope.list();
            })
    };

    /**
     * getProjectInfoById 根据id获取该项目的信息
     * @param $scope
     * @author 沈亚芳
     * @date 2015/12/11
     */
    dao.getProjectInfoById = function($scope, id){
        var url = 'project/projectShow?id=' + id;
        $http.get(url)
            .success(function(data, status, headers, config) {
                if(data.result == 'FAIL') {
                    jDialog.alert('提示:', data.message);
                    return;
                }
                $scope.project = data.data;
                if($scope.project.provinceId === undefined){
                    $scope.project.provinceId = '';
                } else {
                    $scope.project.provinceId = $scope.project.provinceId + '';

                    if ($scope.project.cityId === undefined){
                        $scope.project.cityId = '';
                    } else {
                        $scope.project.cityId = $scope.project.cityId + '';
                        locationDao.cityList($scope, $scope.project.provinceId);

                        if($scope.project.countyId === undefined){
                            $scope.project.countyId = '';
                        } else {
                            $scope.project.countyId = $scope.project.countyId + '';
                            locationDao.countyList($scope, $scope.project.cityId);
                        }
                    }
                }
            });
    };


    /**
     * edit 项目管理-编辑项目信息
     * @param $scope 添加项目时要提交的参数为：projectName、contact、contactWay、platfrom、remark
     * @author 沈亚芳
     * @date 2015/12/11
     */
    dao.edit = function($scope){
        $('#projectEdit').attr('disabled',true);
        var id = $scope.id;
        var projectName = defaultString($scope.project.projectName);
        var contact = defaultString($scope.project.contact);
        var contactWay = defaultString($scope.project.contactWay);
        var platform = $scope.project.platformId;
        var remark = defaultString($scope.project.remark);

        var provinceId = $scope.project.provinceId;
        var cityId = $scope.project.cityId;
        var areaId = $scope.project.countyId;

        //如果是汉字,用encodeURIComponent转码
        projectName = encodeURIComponent(projectName);
        contact = encodeURIComponent(contact);
        remark = encodeURIComponent(remark);

        if(isBlank(platform)){
            platform = '';
        }
        if(isBlank(provinceId)){
            provinceId = '';
        }
        if(isBlank(cityId)){
            cityId = '';
        }
        if(isBlank(areaId)){
            areaId = '';
        }
        if(isBlank(remark)){
            remark = '';
        }

        var url ='project/edit?id=' + id + '&projectName=' + projectName + '&contact=' + contact + '&contactWay=' + contactWay + '&platform=' + platform + '&provinceId=' + provinceId + '&cityId=' + cityId + '&areaId=' + areaId + '&remark=' + remark;

        $http.get(url)
            .success(function(data){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    $('#projectEdit').removeAttr('disabled');
                    return;
                }
                jDialog.alert('编辑项目信息:', '修改成功！');
                $location.path('project/list');
            })
    };


    /**
     * 根据当前账号获得项目id、项目名称
     */
    dao.getProject = function($scope){
        var url = "project/show";
        $http.get(url).success(function(data){
            if(data.result == 'FAIL'){
                jDialog.alert('提示:', data.message);
                return;
            }
            project = data.data;
            $scope.project = data.data;
        });
    };


    /**
     * projectCustomerList 项目下的一级客户列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/01
     */
    dao.projectCustomerList = function($scope){
        loadingAppend();
        var pageNo = pageDao.getPageNo();
        var customerId = '';
        var projectId = $scope.projectId;
        var cascadeLevel = '';
        var customerName = defaultString($scope.customerName);
        //如果是汉字,用encodeURIComponent转码
        customerName = encodeURIComponent(customerName);

        var url ='/project/customerList?pageNo=' +pageNo + '&customerId=' + customerId + '&projectId=' + projectId + '&cascadeLevel=' + cascadeLevel + '&customerName=' + customerName;

        $http.get(url)
            .success(function(data){
                loadingRemove();
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    return;
                }
                projectCustomers = data.records;
                //数据记录集
                $scope.projectCustomers = data.records;

                //开始记录数
                $scope.begin = data.begin;
                $scope.data = data;
                //初始化分页栏
                pageDao.init($scope, data);
            })
    };


    /**
     * projectCustomerNextList 项目下的二级及以下客户列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/04
     */
    dao.projectCustomerNextList = function($scope){
        loadingAppend();
        var pageNo = pageDao.getPageNo();
        var customerId = $scope.customerId;
        var projectId = $scope.projectId;
        var cascadeLevel = $scope.cascadeLevel;
        var customerName = defaultString($scope.customerName);
        //如果是汉字,用encodeURIComponent转码
        customerName = encodeURIComponent(customerName);

        var url ='/project/customerList?pageNo=' +pageNo + '&customerId=' + customerId + '&projectId=' + projectId + '&cascadeLevel=' + cascadeLevel + '&customerName=' + customerName;

        $http.get(url)
            .success(function(data){
                loadingRemove();
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    return;
                }
                projectNextCustomers = data.records;
                //数据记录集
                $scope.projectNextCustomers = data.records;
                //开始记录数
                $scope.begin = data.begin;
                $scope.data = data;
                //初始化分页栏
                pageDao.init($scope, data);
            })
    };

    /**
     * 设备列表(分页)
     * @Auther: 朱学煌
     * @Date: 2016-4-7
     */
    dao.customerDeviceList = function($scope) {
        loadingAppend();
        var pageNo = $scope.pageNo2;
        if (isNaN(pageNo)) {
            pageNo = 1
        }
        var customerId = defaultString($scope.customerId); //客户id
        var url = "/device/deviceList?pageNo=" + pageNo + "&customerId=" + customerId;
        $http.get(url).success(function(data, status, headers, config){
            loadingRemove();
            if(data.result == 'FAIL'){
                jDialog.alert('提示', data.message);
                return;
            }
            $scope.devices = data.records; //数据集
            $scope.begin = data.begin; //起始行
            $scope.data = data;
            $scope.pageNo2 = pageNo; // 当前页
            pageDao.dialogPageInit($scope, data, $scope.dialogDeviceList);
        });
    };

    /**
     * Portal站点缩略图列表(无分页)
     * @Auther: 朱学煌
     * @Date: 2016-4-7
     */
    dao.customerSiteList = function($scope) {
        loadingAppend();
        var customerId = defaultString($scope.customerId); //客户id
        var url = "/site/siteList?pageNo=1&customerId=" + customerId;
        $http.get(url).success(function (data, status, headers, config) {
            loadingRemove();
            if (data.result == 'FAIL') {
                jDialog.alert('提示', data.message);
                return;
            }
            $scope.sites = data.records; //数据集
        });
    };

    /**
     * Portal策略列表(无分页)
     * @Auther: 朱学煌
     * @Date: 2016-4-7
     */
    dao.customerStrategyList = function($scope) {
        var customerId = defaultString($scope.customerId); //客户id
        var url = "/strategy/customer/list?pageNo=1&customerId=" + customerId;
        $http.get(url).success(function (data, status, headers, config) {
            if (data.result == 'FAIL') {
                jDialog.alert('提示', data.message);
                return;
            }
            $scope.strategys = data.data; //数据集
        });
    };


    /**
     * exportProjects 导出项目列表信息
     * @param $scope
     * @author 沈亚芳
     * @date 2016/06/08
     */
    dao.exportProjects = function($scope){
        $('#exportExcle').submit();
    };


    return dao;
});
