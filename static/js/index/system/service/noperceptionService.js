/**
 * Created by zhuxuehuang on 16-4-27.
 */

indexApp.factory('noperceptionService',function($rootScope, noperceptionDao) {
    var service = {};

    /**
     * 校验客户名称
     * @param $scope
     * @returns {boolean}
     * @Auther: zhuxuehuang
     * @Date: 2016-4-27
     */
    function validateCustomerNameFail($scope){
        var $customerName = $("#customerName");
        var jihe = defaultString($scope.customerId); //得到客户id和层级两个值
        var jiheArray = jihe.split('#'); //以#号分隔，获取客户id和层级
        var customerName = jiheArray[2]; //客户名
        if(isBlank(customerName)){
            updateShowTipos($customerName, '客户名称不能为空！');
            return true;
        }
        return false;
    }

    // 验证无感知策略类型和时长
    function validateDateTypeFail($scope) {
        var dateType = $scope.dateType;
        var $dateTypeH = $("#dateTypeH");
        var $dateTypeD = $("#dateTypeD");
        // 小时数
        if (dateType == 1) {
            if (isBlank($scope.timeH)) {
                updateShowTipos($dateTypeH, '请输入时长');
                return true;
            }

            var timeH = null;
            if (!/^\d+\.?\d*$/.test($scope.timeH)) {
                updateShowTipos($dateTypeH, '请输入有效时长（数字）');
                return true;
            }
            timeH = parseFloat($scope.timeH);
            if (!(timeH >= 0.5 && timeH <= 23.5 && timeH % 0.5 == 0)) {
                updateShowTipos($dateTypeH, '请输入0.5的倍数且不能超过24小时的时间');
                return true;
            }
        }
        // 天数
        if (dateType == 2) {
            if (isBlank($scope.timeD)) {
                updateShowTipos($dateTypeD, '请输入天数');
                return true;
            }
            
            if (!/^[1-9]\d*$/.test($scope.timeD)) {
                updateShowTipos($dateTypeD, '请输入大于等于1天的自然天数字');
                return true;
            }
        }
        return false;
    }

    /**
     * 初始化参数校验错误信息tips
     */
    service.initTips = function(){
        var $customerName = $("#customerName"); //客户名称
        var $dateTypeH = $('#dateTypeH'); // 时长
        var $dateTypeD = $('#dateTypeD'); // 天数
        initTipsArray([$customerName, $dateTypeH, $dateTypeD]);
        //页面切换前销毁tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$customerName, $dateTypeH, $dateTypeD]);
        });
    };

    // 表单数据校验
    service.validateNoperceptionFail = function ($scope, action) {
        action = action || 'edit';
        // 校验客户名称
        if(action === 'add' && validateCustomerNameFail($scope)){
            return true;
        }

        // 校验策略类型
        if (validateDateTypeFail($scope)) {
            return true;
        }
    };

    // 无感知列表
    service.noperceptionList = noperceptionDao.noperceptionList;

    // 添加无感知
    service.noperceptionAdd = noperceptionDao.noperceptionAdd;

    // 编辑无感知
    service.noperceptionEdit = noperceptionDao.noperceptionEdit;

    // 查看无感知
    service.noperceptionShow = noperceptionDao.noperceptionShow;

    // 开启无感知
    service.noperceptionOpen = noperceptionDao.noperceptionOpen;

    // 关闭无感知
    service.noperceptionClose = noperceptionDao.noperceptionClose;

    return service;
});