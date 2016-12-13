indexApp.config(function($routeProvider, $httpProvider) {
    $routeProvider
        .when('/', {//第一次加载时，默认模板(显示客户列表)
        	templateUrl : 'html/template/default.html',
            controller: 'defaultController'
        })
        .when('/customer/list', {//客户列表
            templateUrl: 'html/template/customer/customerList.html',
            controller: 'customerListController'
        })
        .when('/customer/add', {//添加客户
            templateUrl: 'html/template/customer/customerAdd.html',
            controller: 'customerAddController'
        })
        .when('/customer/show', {//根据id查客户详情
            templateUrl: 'html/template/customer/customerShow.html',
            controller: 'customerShowController'
        })
        .when('/customer/edit', {//编辑客户
            templateUrl: 'html/template/customer/customerEdit.html',
            controller: 'customerEditController'
        })
        .when('/project/customerDetails', {//客户详情
            templateUrl: 'html/template/project/projectCustomerDetails.html',
            controller: 'projectCustomerDetailsController'
        })
        .when('/customer/addSubordinateUnit', {//添加下级单位
            templateUrl: 'html/template/customer/addSubordinateUnit.html',
            controller: 'customeraddSubordinateUnitController'
        })
        .when('/account/pwdEdit', {//修改密码
            templateUrl: 'html/template/account/pwdEdit.html',
            controller: 'pwdEditController'
        })
        .when('/account/infoModify', {//修改账号基本信息
            templateUrl: 'html/template/account/accountInfoModify.html',
            controller: 'accountInfoModifyController'
        })
        .when('/platform/list', {//平台管理-平台列表
            templateUrl: 'html/template/platform/platformList.html',
            controller: 'platformListController'
        })
        .when('/platform/add', {//平台管理-添加新平台
            templateUrl: 'html/template/platform/platformAdd.html',
            controller: 'platformAddController'
        })
        .when('/platform/edit', {//平台管理-配置平台信息
            templateUrl: 'html/template/platform/platformEdit.html',
            controller: 'platformEditController'
        })
        .when('/project/list', {//项目管理-项目列表
            templateUrl: 'html/template/project/projectList.html',
            controller: 'projectListController'
        })
        .when('/project/add', {//项目管理-添加新项目
            templateUrl: 'html/template/project/projectAdd.html',
            controller: 'projectAddController'
        })
        .when('/project/edit', {//项目管理-配置项目信息
            templateUrl: 'html/template/project/projectEdit.html',
            controller: 'projectEditController'
        })
        .when('/project/projectCustomerList', {//项目管理-项目列表-该项目下的一级单位客户信息
            templateUrl: 'html/template/project/projectCustomerList.html',
            controller: 'projectCustomerListController'
        })
        .when('/project/projectCustomerNextLevelList', {//项目管理-项目列表-该项目下的二级单位及以下客户信息
            templateUrl: 'html/template/project/projectCustomerNextLevelList.html',
            controller: 'projectCustomerNextLevelListController'
        })
        .when('/account/telecomList', {//账号列表（电信侧管理员）
            templateUrl : 'html/template/account/accountTelecomList.html',
            controller  : 'accountTelecomListController'
        })
        .when('/account/customerList', {//账号列表（客户侧管理员）
            templateUrl : 'html/template/account/accountCustomerList.html',
            controller  : 'accountCustomerListController'
        })
        .when('/account/permissionAssignTelecom', {//账号管理（分配权限）（电信侧）
            templateUrl : 'html/template/account/permissionAssignTelecom.html',
            controller  : 'permissionAssignTelecomController'
        })
        .when('/account/permissionAssignCustomer', {//账号管理（分配权限）（客户侧）
            templateUrl : 'html/template/account/permissionAssignCustomer.html',
            controller  : 'permissionAssignCustomerController'
        })
        .when('/account/add', {//账号列表（电信侧管理员）(创建)
            templateUrl : 'html/template/account/accountAdd.html',
            controller  : 'accountAddController'
        })
        .when('/account/edit', {//账号列表（电信侧管理员）(编辑)
            templateUrl : 'html/template/account/accountEdit.html',
            controller  : 'accountEditController'
        })
        .when('/account/adminList',{//管理员账号-列表
            templateUrl : 'html/template/account/accountAdminList.html',
            controller  : 'accountAdminListController'
        })
        .when('/account/adminAdd',{//管理员账号-创建
            templateUrl : 'html/template/account/accountAdminAdd.html',
            controller  : 'accountAdminAddController'
        })
        .when('/account/adminEdit',{//管理员账号-编辑
            templateUrl : 'html/template/account/accountAdminEdit.html',
            controller  : 'accountAdminEditController'
        })
        .when('/staticuser/blackUserList', {//用户管理-黑名单管理-列表
            templateUrl : 'html/template/staticuser/blackUserList.html',
            controller  : 'blackUserController'
        })
        .when('/staticuser/blackUserAdd', {//用户管理-黑名单管理-添加黑名单
            templateUrl : 'html/template/staticuser/blackUserAdd.html',
            controller  : 'blackUserAddController'
        })
        .when('/staticuser/whiteUserList', {//用户管理-白名单管理-列表
            templateUrl : 'html/template/staticuser/whiteUserList.html',
            controller  : 'whiteUserListController'
        })
        .when('/staticuser/whiteUserAdd',{//用户管理-白名单管理-添加白名单
            templateUrl : 'html/template/staticuser/whiteUserAdd.html',
            controller  : 'whiteUserAddController'
        })
        .when('/staticUser/staticUserList', {//用户管理-用户列表
            templateUrl : 'html/template/staticuser/staticUserList.html',
            controller  : 'staticUserListController'
        })
        .when('/staticUser/staticUserAdd', {//用户管理-用户列表-添加静态用户
            templateUrl : 'html/template/staticuser/staticUserAdd.html',
            controller  : 'staticUserAddController'
        })
        .when('/staticUser/staticUserEdit', {//用户管理-用户列表-编辑静态用户
            templateUrl : 'html/template/staticuser/staticUserEdit.html',
            controller  : 'staticUserEditController'
        })
        .when('/portal/site/siteList', {//站点管理-站点列表
            templateUrl : 'html/template/portal/site/siteList.html',
            controller  : 'siteListController'
        })
        .when('/portal/site/defaultSiteList', {//站点管理-默认站点列表
            templateUrl : 'html/template/portal/site/defaultSiteList.html',
            controller  : 'defaultSiteListController'
        })
        .when('/portal/template/templateList', {//模板管理-模板列表
            templateUrl : 'html/template/portal/template/templateList.html',
            controller  : 'templateListController'
        })
        .when('/portal/component/componentList', {//组件管理-组件列表
            templateUrl : 'html/template/portal/component/componentList.html',
            controller  : 'componentListController'
        })
        .when('/portal/component/componentAdd', {//组件管理-添加新组件
            templateUrl : 'html/template/portal/component/componentAdd.html',
            controller  : 'componentAddController'
        })
        .when('/portal/strategy/strategyList', {//推送策略-策略列表
            templateUrl : 'html/template/portal/strategy/strategyList.html',
            controller  : 'strategyListController'
        })
        .when('/portal/strategy/strategyAdd', {//推送策略-添加推送策略
            templateUrl : 'html/template/portal/strategy/strategyAdd.html',
            controller  : 'strategyAddController'
        })
        .when('/portal/strategy/strategyEdit', {//推送策略-编辑推送策略
            templateUrl : 'html/template/portal/strategy/strategyEdit.html',
            controller  : 'strategyEditController'
        })
        .when('/report/portalReportList', {//统计分析-portal统计-列表
            templateUrl : 'html/template/report/portalReportList.html',
            controller  : 'portalReportListController'
        })
        .when('/report/portalPVChart', {//统计分析-portal统计-PV折线图
            templateUrl : 'html/template/report/portalPVChart.html',
            controller  : 'portalPVChartController'
        })
        .when('/report/portalUVChart', {//统计分析-portal统计-UV折线图
            templateUrl : 'html/template/report/portalUVChart.html',
            controller  : 'portalUVChartController'
        })
        .when('/report/deviceReportList', {//统计分析-设备统计-列表
            templateUrl : 'html/template/report/deviceReportList.html',
            controller  : 'deviceReportListController'
        })
        .when('/report/deviceKeyIndex', {//统计分析-设备统计-关键指标
            templateUrl : 'html/template/report/deviceKeyIndex.html',
            controller  : 'deviceKeyIndexController'
        })
        .when('/report/deviceChart', {//统计分析-设备统计-趋势
            templateUrl : 'html/template/report/deviceChart.html',
            controller  : 'deviceChartController'
        })
        .when('/report/deviceProjectReportList', {//统计分析-设备统计-项目型设备按类型统计
            templateUrl : 'html/template/report/deviceProjectReportList.html',
            controller  : 'deviceProjectReportListController'
        })
        .when('/sysconfig/list', {//系统参数配置
            templateUrl : 'html/template/system/sysConfigList.html',
            controller  : 'sysConfigController'
        })
        .when('/sysconfig/add',{//新增系统参数
            templateUrl : 'html/template/system/sysConfigAdd.html',
            controller  : 'sysConfigAddController'
        })
        .when('/sysconfig/edit',{//编辑系统参数
            templateUrl : 'html/template/system/sysConfigEdit.html',
            controller  : 'sysConfigEditController'
        })
        .when('/exceptionlog/list', {//异常日志列表
            templateUrl : 'html/template/log/exceptionlogList.html',
            controller  : 'exceptionlogListController'
        })
        .when('/operationlog/list', {//异常日志列表
            templateUrl : 'html/template/log/operationlogList.html',
            controller  : 'operationlogListController'
        })
        .when('/theme/list', {//系统配置-换肤配置-皮肤列表
            templateUrl : 'html/template/system/theme/themeList.html',
            controller  : 'themeListController'
        })
        .when('/theme/add', {//系统配置-换肤配置-皮肤列表-添加
            templateUrl : 'html/template/system/theme/themeAdd.html',
            controller  : 'themeAddController'
        })
        .when('/theme/edit', {//系统配置-换肤配置-皮肤列表-编辑
            templateUrl : 'html/template/system/theme/themeEdit.html',
            controller  : 'themeEditController'
        })
        .when('/themeStrategy/list', {//系统配置-换肤配置-皮肤策略-列表
            templateUrl : 'html/template/system/theme/themeStrategyList.html',
            controller  : 'themeStrategyListController'
        })
        .when('/themeStrategy/add', {//系统配置-换肤配置-皮肤策略-添加
            templateUrl : 'html/template/system/theme/themeStrategyAdd.html',
            controller  : 'themeStrategyAddController'
        })
        .when('/themeStrategy/edit', {//系统配置-换肤配置-皮肤策略-编辑
            templateUrl : 'html/template/system/theme/themeStrategyEdit.html',
            controller  : 'themeStrategyEditController'
        })
        .when('/requestLog/list', {//系统配置-请求日志-列表
            templateUrl : 'html/template/log/requestLogList.html',
            controller  : 'requestLogListController'
        })
        .when('/requestLog/show', {//系统配置-请求日志-详情
            templateUrl : 'html/template/log/requestLogShow.html',
            controller  : 'requestLogShowController'
        })
        .when('/device/list',{//设备列表
            templateUrl : 'html/template/device/deviceList.html',
            controller  : 'deviceListController'
        })
        .when('/device/show',{//设备列表-设备详情
            templateUrl : 'html/template/device/deviceShow.html',
            controller  : 'deviceShowController'
        })
        .when('/device/deviceAllocation',{//设备列表-设备详情-设备分配
            templateUrl : 'html/template/device/deviceAllocation.html',
            controller  : 'deviceAllocationController'
        })
        .when('/device/edit',{//设备列表-设备编辑
            templateUrl : 'html/template/device/deviceEdit.html',
            controller  : 'deviceEditController'
        })
	.when('/hotarea/list',{//设备列表-热点管理
            templateUrl : 'html/template/device/hotAreaList.html',
            controller  : 'hotAreaListController'
        })
        .when('/noperception/list',{//系统配置-无感知配置-列表
            templateUrl : 'html/template/system/noperceptionList.html',
            controller  : 'noperceptionListController'
        })
        .when('/noperception/add',{//系统配置-无感知配置-新增
            templateUrl : 'html/template/system/noperceptionAdd.html',
            controller  : 'noperceptionAddController'
        })
        .when('/noperception/edit',{//系统配置-无感知配置-编辑
            templateUrl : 'html/template/system/noperceptionEdit.html',
            controller  : 'noperceptionEditController'
        })
        .when('/noperception/show',{//系统配置-无感知配置-查看
            templateUrl : 'html/template/system/noperceptionShow.html',
            controller  : 'noperceptionShowController'
        })
        .otherwise({
            redirectTo: '/'
        });
});