/**
 * Created by zhuxh on 15-11-30.
 */

indexApp.factory('deviceService', function($rootScope, deviceDao){
    var service = {};

    //AP列表(分页)
    service.deviceList = function($scope){
        deviceDao.deviceList($scope);
    };
    
    /**
     * 根据id查找设备详情
     */
    service.findByDeviceId = function($scope, id){
    	deviceDao.findByDeviceId($scope, id);
    };
    
    /**
     * 设备分配
     */
    service.deviceAllocation = function($scope, deviceIds, customerIds){
    	//alert("设备分配service层");
    	deviceDao.deviceAllocation($scope, deviceIds, customerIds);
    };

    //校验省
    service.validateProvinceFail = function($scope){
        var $province = $('#provinceId');
        var province = $scope.device.provinceId;
        if(isBlank(province)){
            updateShowTipos($province, '区域省不能为空！');
            return true;
        }
        return false;
    };

    //校验市
    service.validateCityFail = function($scope){
        var $city = $('#cityId');
        var city = $scope.device.cityId;
        if(isBlank(city)){
            updateShowTipos($city, '区域市不能为空！');
            return true;
        }
        return false;
    };

    //校验区/县
    service.validateCountyFail = function($scope){
        var $county = $('#countyId');
        var county = $scope.device.countyId;
        if(isBlank(county)){
            updateShowTipos($county, '区域区/县不能为空！');
            return true;
        }
        return false;
    };

    // 校验详细地址
    service.validateAddressFail = function($scope){
        var $address = $('#address');
        var address = $scope.device.address;
        if(isNotBlank(address) && address.length > 60){
            updateShowTipos($address, '详细地址长度不能超过60个字符！');
            return true;
        }
        return false;
    };

    // 校验备注
    service.validateRemarkFail = function($scope){
        var $remark = $('#remark');
        var remark = $scope.device.remark;
        if(isNotBlank(remark) && remark.length > 500){
            updateShowTipos($remark, '备注长度不能超过500个字符！');
            return true;
        }
        return false;
    };

    /**
     * 初始化设备监控tips
     * @Auther: 朱学煌
     * @Date: 2016-4-14
     */
    service.initTipsDeviceMonitor = function(){
        var $deviceMac = $('#deviceMac');
        initTipsArray([$deviceMac]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function() {
            destroyTipsArray([$deviceMac]);
        });
    };

    /**
     * 校验Mac地址格式是否合法
     * @param $scope
     * @returns {boolean}
     * @Auther: 朱学煌
     * @Date: 2016-4-14
     */
    service.validateDeviceMacFail = function($scope){
        var $deviceMac = $('#deviceMac');
        var deviceMac = $scope.apMac;
        if (!isBlank(deviceMac) && !/^[a-fA-F0-9]{12}$/.test(deviceMac)) {
            updateShowTipos($deviceMac, '设备MAC由数字0-9，字母A-F或a-f组合的12位字符');
            return true;
        }
        return false;
    };

    /**
     * ssid下拉列表select2
     * @param selectId
     * @param customerId
     * @param ajaxUrl
     * @returns {*}
     * @Auther: 朱学煌
     * @Date: 2016-4-11
     */
    service.deviceSsidSelect2 = function(selectId, customerId, ajaxUrl){
        var _ajaxUrl = ajaxUrl || '/devicemonitor/ssidList';
        var _selectId = selectId || 'deviceSsid';
        return deviceSsidSelect(_selectId, customerId, _ajaxUrl);
    };

    /**
     * ssid下拉列表select2实现
     * @param selectId
     * @param customerId
     * @param ajaxUrl
     * @returns {*|jQuery}
     * @Auther: 朱学煌
     * @Date: 2016-4-11
     */
    function deviceSsidSelect(selectId, customerId, ajaxUrl) {
        // if (!customerId) {
        //     var select2 = $('#' + selectId).select2({
        //             placeholder: '请选择SSID'
        //         });
        //     return select2;
        // }
        var select2 = $('#' + selectId).select2({
            placeholder: '请选择SSID',
            allowClear: true,
            ajax: {
                url: ajaxUrl,
                dataType: 'json',
                delay: 0, // 延时可能清空时未恢复
                data: function (params) {
                    return {
                        customerId: customerId,
                        keywords: params.term
                    };
                },
                processResults: function (data, params) {
                    var _data = data.data;
                    var ret_data = [];
                    for (var i = 0,len = _data.length; i<len; i++) {
                        var val = _data[i];
                        ret_data.push({
                            'id': val,
                            'text': val
                        });
                    }
                    return {
                        results: ret_data
                    };
                },
                cache: true
            },
            escapeMarkup: function (markup) {
                return markup;
            },
            templateResult: function (data) {
                if (data.loading) return '正在搜索...';
                var markup = "<div>" + data.text + "</div>";
                return markup;
            },
            templateSelection: function (data) {
                return data.text;
            }
        }).val('').trigger('change');
        return select2;
    }

    /**
     * 设备监控列表
     * @param $scope
     * @Auther: 朱学煌
     * @Date: 2016-4-11
     */
    service.deviceMonitorList = function ($scope) {
        deviceDao.deviceMonitorList($scope);
    };

    /**
     * 设备信息编辑
     * @param $scope
     * @param id
     * @Auther: zhuxuehuang
     * @Date: 2016-5-30
     */
    service.deviceEdit = function ($scope, id) {
        deviceDao.deviceEdit($scope, id);
    };

    //初始化参数校验错误信息tips
    service.initTips = function() {
        var $provinceId = $('#provinceId');//省
        var $cityId = $('#cityId');//市
        var $countyId = $('#countyId');//区/县
        var $address = $('#address');//详细地址
        var $remark = $('#remark');//备注
        initTipsArray([$provinceId, $cityId, $countyId, $address, $remark]);

        //页面切换前销毁tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$provinceId, $cityId, $countyId, $address, $remark]);
        });
    };

    return service;
});
