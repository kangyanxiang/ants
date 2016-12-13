/**
 * Created by zhuxh on 15-11-30.
 * 设备控制层  deviceListController
 */

indexApp.controller('deviceListController', function($scope, $location, deviceService, locationService, customerService, ngDialog) {

	//初始化
    function init(){
        $scope.device = {};
        locationService.provinceList($scope);//获取省数据集合

		//获取当前客户集合
		customerService.customerSelect2('customerIdAndLevel');
    }
    init();

    //AP列表(分页)
    function list(){
        deviceService.deviceList($scope);
    }
    $scope.list = list;
    list();
    
  //根据省id获取市数据集合
    $scope.cityChange = function($event){
        var provinceId = $scope.provinceId;//省id
        locationService.cityList($scope, provinceId);//获取市数据集合
    };

    //根据市id获取区/县数据集合
    $scope.countyChange = function($event){
        var cityId = $scope.cityId;//市id
        locationService.countyList($scope, cityId);//获取区/县数据集合
    };
    
    /**
     * 获取要设备分配的数据
     */
    $scope.deviceAllocation = function($scope){
    	var $box = $(".datacontent input[type='checkbox']:checked");
    	var maxSize = $box.size();
    	if(maxSize <= 0){
    		jDialog.alert('设备分配提示','您未选择要设备分配的数据！请选择');
    		return;
    	}
    	var customerIds = "";//声明老客户id集合
    	var deviceIds = "";//声明设备id集合
    	
    	//循环得到客户id集合
    	$box.each(function(i){
    		var customerId = $(this).parent().find("input[name='customerId']").val();//得到每一个客户id
    		customerIds = customerIds + customerId;
    		if(i < maxSize - 1){
    			customerIds += ',';
    		}
    	});
    	
    	//循环得到设备id集合
    	$box.each(function(j){
    		var deviceId = $(this).parent().find("input[name='deviceId']").val();//得到每一个设备id
    		deviceIds = deviceIds + deviceId;
    		if(j < maxSize - 1){
    			deviceIds += ',';
    		}
    	});
    	var url = "/device/deviceAllocation";//跳转到设备分配页面
    	var params = { deviceIds : deviceIds ,  customerIds : customerIds };//将设备id集合和客户id集合传过去
    	$location.path(url).search(params);
    };
    
    //全选、全不选
    $scope.selectAll = function(){
    	allCheckBoxs = document.getElementsByName("checkbox");//得到前台页面上单选框的name值
    	var check = document.getElementsByName("allBox");//得到全选按钮的name值
    	if(check[0] .checked){
    		for(var i =0;i<allCheckBoxs.length;i++){
  			  if(!allCheckBoxs[i].disabled){
  				  allCheckBoxs[i].checked = true;
  			  }
  		  }
    	} else {
    		for(var j =0;j<allCheckBoxs.length;j++){
  			  allCheckBoxs[j].checked = false;
  		    }
    	}
    };

	/**
	 * resetWidth 重置列表因fixed引起的宽度问题
	 * @author 沈亚芳
	 * @date 2015/12/16
	 */
	resetWidth();
    
    //单选
    $scope.radio = function($event, customerId){
    	var $subBox = $("input[name='checkbox']");//选中要设备分配的数据
    	var isAllChoosed = $("input[name='checkbox']:checked").length;
    	var isAllChoosed1 = $subBox.length == isAllChoosed;
    	if(isAllChoosed1 === true){
			var selectObj = document.getElementById("selectAll1");
			selectObj.checked  = true;
		}else{
			$("#selectAll1").attr("checked", false);
		}
    };

    // 详情
    $scope.show = function(id) {
        $scope.id = id;
        ngDialog.open({
            template: 'html/template/device/deviceShow.html',
            controller: 'deviceShowController',
            scope: $scope,
            title: '设备详情',
            width: '45%'
        });
    };
    
});
