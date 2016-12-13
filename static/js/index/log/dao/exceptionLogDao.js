indexApp.factory('exceptionlogDao',function($http, pageDao){
    var dao = {};
  //异常日志列表
    dao.list = function($scope){
    	//alert(1);
		loadingAppend();
    	var pageNo = pageDao.getPageNo();
    	var startDate = $scope.startDate;
    	var endDate = $scope.endDate;
    	var moduleName = defaultString($scope.moduleName);//模块名称
    	var serviceName = defaultString($scope.serviceName);//service 名称
    	var errorMessage = encodeURIComponent(defaultString($scope.errorMessage));//异常信息
    	var interfaceUrl = defaultString($scope.interfaceUrl);//接口地址
    	var interfaceParam = defaultString($scope.interfaceParam);//接口参数
    	var interfaceReturnValue = encodeURIComponent(defaultString($scope.interfaceReturnValue));//接口返回值
    	var url = '/exceptionlog/list?pageNo=' + pageNo + '&startDate=' + startDate + '&endDate=' +  endDate;
		if(isNotBlank(moduleName)){
			url += '&moduleName=' + moduleName;
		}
		if(isNotBlank(serviceName)){
			url += '&serviceName=' + serviceName;
		}
		if(isNotBlank(errorMessage)){
			url += '&errorMessage=' + errorMessage;
		}
		if(isNotBlank(interfaceUrl)){
			url += '&interfaceUrl=' + interfaceUrl;
		}
		if(isNotBlank(interfaceParam)){
			url += '&interfaceParam=' + interfaceParam;
		}
		if(isNotBlank(interfaceReturnValue)){
			url += '&interfaceReturnValue=' + interfaceReturnValue;
		}
    	 $http.get(url)
         .success(function(data, status, headers, config){
				 loadingRemove();
             if(data.result == 'FAIL'){
                 jDialog.alert('提示: ' , data.message);
                 return;
             }
            exceptionlogLists = data.records;
            $scope.exceptionlogs = data.records;//数据集
            $scope.begin = data.begin;//起始行
            $scope.data = data;
            pageDao.init($scope, data);//初始化分页栏
        })
        .error(function(data, status, headers, config){
        });
    };
    
    //根据id查找请求日志详情
	dao.show = function($scope, id){
		 var url = "/exceptionlog/show?id=" + id;
	        $http.get(url)
	            .success(function(data, status, headers, config){
	                if(data.result == 'FAIL'){
	                	jDialog.alert('提示: ', data.message);
	                    return;
	                }
	                $scope.exceptionlog = data.data;//数据集
	            })
	            .error(function(data, status, headers, config){
	            	jDialog.alert('提示: ', datastatus);
	            });
	    };
    
    
    return dao;
});