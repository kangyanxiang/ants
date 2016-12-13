/**
 * Created by 牛华凤 on 2015/11/26.
 * 项目管理 projectService
 */
indexApp.factory('projectService',function($rootScope, $location, projectDao){
    /**
     * service 定义个全局service，并将其置空
     * @param 空
     * @author 牛华凤
     * @date 2015/11/26
     */
    var service = {};


    /**
     * validateProjectNameFail 校验项目名称 （功能：添加新项目）（功能：编辑项目信息）
     * @param $scope 校验的参数：projectName
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/12/11
     */
    service.validateProjectNameFail = function($scope) {
        var $projectName = $('#projectName');
        var projectName = $scope.project.projectName;
        //校验是否为空
        if (isBlank(projectName)){
            updateShowTipos($projectName, '项目名称不能为空！');
            return true;
        }
        //校验格式是否正确
        if (!chkString(projectName, defrules.wifiProjectName)){
            updateShowTipos($projectName, '项目名称格式为：1-50位汉字，字母，下划线、数字，不含特殊字符！');
            return true;
        }
        return false;
    };


    /**
     * validateContactNameFail 校验联系人 （功能：添加新项目）（功能：编辑项目信息）
     * @param $scope 校验的参数：contact
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/12/11
     */
    service.validateContactNameFail = function($scope){
        var $contact = $('#contact');
        var contact = $scope.project.contact;
        //校验是否为空
        //if (isBlank(contact)){
        //    updateShowTipos($contact, '联系人不能为空！');
        //    return true;
        //}
        //校验格式是否正确
        if (!chkString(contact, defrules.contactNew)){
            updateShowTipos($contact, '联系人必须由20位以内字符组成，包括汉字、字母，不含特殊字符！');
            return true;
        }
        return false;
    };


    /**
     * validateContactWayFail 校验联系方式
     * @param $scope 校验的参数：contactWay
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/12/11
     */
    service.validateContactWayFail = function($scope){
        var $contactWay = $('#contactWay');
        var contactWay = $scope.project.contactWay;
        //校验是否为空
        //if (isBlank(contactWay)){
        //    updateShowTipos($contactWay, '联系方式不能为空！');
        //    return true;
        //}
        //校验格式是否正确
        if (!chkString(contactWay, defrules.contactWay)){
            updateShowTipos($contactWay, '联系方式格式为：11位以1开头符合手机号码规则的数字！');
            return true;
        }
        return false;
    };


    /**
     * validatePlatformFail 校验所属平台
     * @param $scope 校验的参数：platfrom
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/12/11
     */
    service.validatePlatformFail = function($scope){
        var $platform = $('#platform');
        var platform = $scope.project.platformId;
        //校验是否为空
        if (isBlank(platform)){
            updateShowTipos($platform, '请选择一个平台！');
            return true;
        }
        return false;
    };


    /**
     * validateProvinceIdFail 校验地区：省
     * @param $scope 校验的参数：platfrom
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/12/11
     */
    service.validateProvinceIdFail = function($scope){
        var $provinceId = $('#provinceId');
        var provinceId = $scope.project.provinceId;
        //校验是否为空
        if (isBlank(provinceId)){
            updateShowTipos($provinceId, '请选择一个省！');
            return true;
        }
        return false;
    };


    /**
     * initTipsForAddProjectChange 各校验字段相关信息提示的初始化（功能：添加新项目）（功能：编辑项目信息）
     * @author 沈亚芳
     * @date 2015/12/11
     */
    service.initTipsForAddProjectChange = function(){
        var $projectName = $('#projectName');
        var $contact = $('#contact');
        var $contactWay = $('#contactWay');
        var $platform = $('#platform');
        var $provinceId = $('#provinceId');
        initTipsArray([$projectName, $contact, $contactWay, $platform, $provinceId]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$projectName, $contact, $contactWay, $platform, $provinceId]);
        });
    };


    /**
     * projectNameList 获取项目名称集合，用于select
     * @param $scope
     * @author 牛华凤
     * @date 2015/11/26
     */
    service.projectNameList = function($scope){
        projectDao.projectNameList($scope);
    };


    /**
     * projectList 项目管理-项目列表
     * @author 沈亚芳
     * @date 2015/12/08
     */
    service.projectList = function($scope){
        projectDao.projectList($scope);
    };


    /**
     * delete 根据id删除对应的项目
     * @author 沈亚芳
     * @date 2015/12/08
     */
    service.delete = function($scope, projectId){
        projectDao.delete($scope, projectId);
    };


    /**
     * getAllPlatformInfo 获取所有登录平台信息，用于select
     * @param $scope
     * @author 沈亚芳
     * @date 2015/12/11
     */
    service.getAllPlatformInfo = function($scope){
        projectDao.getAllPlatformInfo($scope);
    };


    /**
     * add 项目管理-添加新项目
     * @param $scope
     * @author 沈亚芳
     * @date 2015/12/08
     */
    service.add = function($scope){
        projectDao.add($scope);
    };


    /**
     * getProjectInfoById 根据id获取该项目的信息
     * @param $scope
     * @author 沈亚芳
     * @date 2015/12/11
     */
    service.getProjectInfoById = function($scope, id){
        projectDao.getProjectInfoById($scope, id);
    };


    /**
     * edit 项目管理-编辑项目信息
     * @param $scope
     * @author 沈亚芳
     * @date 2015/12/11
     */
    service.edit = function($scope){
        projectDao.edit($scope);
    };


    /**
     * 根据当前账号获得项目id、项目名称
     */
    service.getProject = function($scope){
        projectDao.getProject($scope);
    };



    /**
     * projectCustomerList 项目下的一级客户列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/01
     */
    service.projectCustomerList = function($scope){
        projectDao.projectCustomerList($scope);
    };


    /**
     * projectCustomerNextList 项目下的二级及以下客户列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/04
     */
    service.projectCustomerNextList = function($scope){
        projectDao.projectCustomerNextList($scope);
    };


    /**
     * exportProjects 导出项目列表信息
     * @param $scope
     * @author 沈亚芳
     * @date 2016/06/08
     */
    service.exportProjects = function($scope){
        projectDao.exportProjects($scope);
    };


    /**
     * 客户详情-设备列表
     * @param $scope
     * @Auther: 朱学煌
     * @Date: 2016-4-7
     */
    service.customerDeviceList = function($scope) {
        projectDao.customerDeviceList($scope);
    };

    /**
     * 客户详情-策略列表
     * @param $scope
     * @Auther: 朱学煌
     * @Date: 2016-4-7
     */
    service.customerStrategyList = function($scope) {
        projectDao.customerStrategyList($scope);
    };

    /**
     * 客户详情-客户信息
     * @param $scope
     * @Auther: 朱学煌
     * @Date: 2016-4-7
     */
    service.customerSiteList = function($scope) {
        projectDao.customerSiteList($scope);
    };

    // 请选择项目select2实现
    service.projectSelect2 = function (selectId) {
        selectId = selectId || 'fkProjectId';
        var ajaxUrl = '/project/projectList';
        return projectSelect(selectId, ajaxUrl);
    };

    /**
     * 项目名称下拉列表 select2 异步请求数据
     */
    function projectSelect(selectId, ajaxUrl) {
        var userId = $location.search()['userId'];
        req_data = {'userId': userId};

        var $select = $('#' + selectId);

        var project_select = $select.select2({
            language: "zh-CN",
            placeholder: '请选择项目',
            allowClear: true,
            ajax: {
                url: ajaxUrl,
                dataType: 'json',
                delay: 0, // 延时可能清空时未恢复
                data: function (params) {
                    return {
                        keywords: params.term,
                        userId: req_data.userId ? req_data.userId : ''
                    };
                },
                processResults: function (data, params) {
                    var _data = data.data;
                    for (var i = 0,len = _data.length; i<len; i++) {
                        var _item = _data[i];
                        _item['id'] = _item.projectId;
                        _item['text'] = _item.projectName;
                    }
                    return {
                        results: _data
                    };
                },
                cache: true
            },
            escapeMarkup: function (markup) {
                return markup;
            },
            templateResult: function (data) {
                if (data.loading) return '正在搜索...';
                var markup = "<div>" + data.projectName + "</div>";
                return markup;
            },
            templateSelection: function (data) {
                return data.projectName || data.text;
            }
        });

        return project_select;
    }

    return service;

});
