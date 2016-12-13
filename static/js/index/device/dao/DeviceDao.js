/**
 * Created by zhuxh on 15-11-30.
 * 设备dao层  deviceDao
 */

indexApp.factory('deviceDao', function($http, $location, pageDao, locationDao){

    var dao = {};

    /**
     * 设备列表(分页)
     */
    dao.deviceList = function($scope){
        loadingAppend();
        var pageNo = pageDao.getPageNo();
        var provinceId = defaultString($scope.provinceId);//省id
        var cityId = defaultString($scope.cityId);//市id
        var countyId = defaultString($scope.countyId);//区/县id
        //var customerName = defaultString($scope.customerName);//客户名称

        var customerIdAndLevel = defaultString($scope.customerIdAndLevel);
        //以#拆分customerIdAndLevel
        var customerIdAndLevelArray = customerIdAndLevel.split('#');
        var customerId = customerIdAndLevelArray[0];
        var cascadeLabel = customerIdAndLevelArray[1];

        if(isBlank(customerId)){
            customerId = '';
        }
        if(isBlank(cascadeLabel)){
            cascadeLabel = '';
        }

        var deviceName = defaultString($scope.deviceName);//设备名称
        var apMac = defaultString($scope.apMac);//apMac
        var ssid = defaultString($scope.ssid);//ssid
        var devId = defaultString($scope.devId);
        if(pageNo == "上一页" || pageNo == "下一页"){
        	return;
        }
        var url = "/device/list?pageNo=" + pageNo + "&provinceId=" + provinceId + "&cityId=" + cityId + "&countyId=" + countyId + 
        "&deviceName=" + deviceName + "&customerId=" + customerId + "&cascadeLabel=" + cascadeLabel + "&apMac=" + apMac + "&ssid=" + ssid + "&devId=" + devId;

        $http.get(url).success(function(data, status, headers, config){
            loadingRemove();
            if(data.result == 'FAIL'){
                jDialog.alert('提示', data.message);
                return;
            }
            $scope.devices = data.records;//数据集
            $scope.begin = data.begin;//起始行
            $scope.data = data;
            pageDao.init($scope, data);//初始化分页栏
        });
    };
    
    /**
     * 根据id查找设备详情
     */
    dao.findByDeviceId = function($scope, id){
    	var url = "/device/show?id=" + id;
    	$http.get(url).success(function(data, status, headers, config){
            if(data.result == 'FAIL'){
                jDialog.alert('提示', data.message);
                return;
            }
            $scope.device = data.data;//数据集
            // 地区详情值交换
            $scope.device.address = $scope.device.fixAddr;
            // 地区回填
            if ($scope.device.provinceId !== undefined) {
                locationDao.cityList($scope, $scope.device.provinceId);
            }
            if ($scope.device.cityId !== undefined) {
                locationDao.countyList($scope, $scope.device.cityId);
            }
        });
    };
    
    /**
     * 设备分配
     */
    dao.deviceAllocation = function($scope, deviceIds, customerIds){
    	$('#deviceId').attr('disabled',true);
    	var toCustomerId = $("input[name='radio']:checked").parent().find("input[name='customerId']").val();//得到要分配设备的客户id
    	if(isBlank(toCustomerId)){//判断要分配设备的客户id是空的话提示
    		jDialog.alert('设备分配', '请选择要分配设备的客户！');
            return;
    	}
    	var url = "/device/transfer?ids=" + deviceIds + "&customerIds=" + customerIds + "&toCustomerId=" + toCustomerId;
    	$http.post(url).success(function(data, status, headers, config){
            if(data.result == 'FAIL'){
                jDialog.alert('提示', data.message);
                $('#deviceId').removeAttr('disabled');
                return;
            }
            jDialog.alert('设备分配', '分配成功！');
            $location.path('device/list');
        });
    };

    /**
     * 设备信息编辑接口
     * @param $scope
     * @param deviceId
     * @Auther: zhuxuehuang
     * @Date: 2016-5-30
     */
    dao.deviceEdit = function ($scope, deviceId) {
        $('#btnSubmit').attr('disabled', true);
        var provinceId = $scope.device.provinceId;//省id
        var cityId = $scope.device.cityId;//市id
        var countyId = defaultString($scope.device.countyId);//区/县id
        var address = defaultString($scope.device.address);//详细地址
        var remark = defaultString($scope.device.remarks);//备注

        var params = {
            deviceId: deviceId,
            provinceId: provinceId,
            cityId: cityId,
            countyId: countyId,
            address: address,
            remark: remark
        };
        var url = "/device/edit?" + $.param(params);
        $http.get(url).success(function(data, status, headers, config){
            if(data.result == 'FAIL'){
                jDialog.alert('提示', data.message);
                $('#btnSubmit').removeAttr('disabled');
                return;
            }
            jDialog.alert('编辑设备', '编辑设备信息成功！');
            $location.path('device/list');
        });
    };
    return dao;
});
