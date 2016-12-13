/**
 * Created by zhuxuehuang on 16-4-27.
 */

indexApp.factory('noperceptionDao', function($http, $location, pageDao) {
    var dao = {};

    // 无感知列表
    dao.noperceptionList = function ($scope) {
        loadingAppend();
        var pageNo = pageDao.getPageNo();
        var customerName = defaultString($scope.customerName); // 客户名称
        var customerId = customerName.split('#') ? customerName.split('#')[0] : '';
        var params = {
            pageNo: pageNo,
            customerId: customerId
        };
        var url = "/noperception/list?" + $.param(params);
        $http.get(url).success(function(data, status, headers, config) {
            loadingRemove();
            if(data.result == 'FAIL'){
                jDialog.alert('提示', data.message);
                return;
            }
            $scope.noperceptions = data.records; //数据集
            $scope.begin = data.begin; //起始行
            $scope.data = data;
            pageDao.init($scope, data); //初始化分页栏
        }).error(function(data, status, headers, config) {
            jDialog.alert('提示: ', data, status);
        });
    };

    // 新增无感知接口
    dao.noperceptionAdd = function ($scope) {
        // 客户Id
        var customerId = defaultString($scope.customerId);
        var cascadeLabel = '';
        if (customerId) {
            var customerArr = customerId.split('#');
            customerId = customerArr[0];
            cascadeLabel = customerArr[1];
        }

        // 感应时长
        var time = 0;
        var type = defaultString($scope.dateType);
        if (type == '1') {
            time = $scope.timeH; // 小时
        } else if (type == '2') {
            time = $scope.timeD; // 天数
        }

        // 请求数据
        var params = {
            'customerId': customerId,
            'cascadeLabel': cascadeLabel,
            'dateType': type,
            'time': time,
            'remark': defaultString($scope.remark)
        };

        var url = "/noperception/add?" + $.param(params);
        $http.get(url).success(function(data, status, headers, config) {
            if(data.result == 'FAIL'){
                jDialog.alert('提示', data.message);
                return;
            }
            jDialog.alert('添加无感应', '添加无感应成功！');
            $location.path('noperception/list');

        }).error(function(data, status, headers, config) {
            jDialog.alert('提示: ', data, status);
        });
    };

    // 编辑无感知接口
    dao.noperceptionEdit = function ($scope, id) {
        // 感应时长
        var time = 0;
        var type = defaultString($scope.dateType);
        if (type == '1') {
            time = $scope.timeH; // 小时
        } else if (type == '2') {
            time = $scope.timeD; // 天数
        }

        // 请求数据
        var params = {
            'id': id,
            'customerId': $scope.customerId,
            'cascadeLabel': $scope.cascadeLabel,
            'dateType': type,
            'time': time,
            'remark': defaultString($scope.remark),
            'status':$scope.status
        };

        var url = "/noperception/edit?" + $.param(params);
        $http.get(url).success(function(data, status, headers, config) {
            if(data.result == 'FAIL'){
                jDialog.alert('提示', data.message);
                return;
            }
            jDialog.alert('编辑无感应', '编辑无感应成功！');
            $location.path('noperception/list');

        }).error(function(data, status, headers, config) {
            jDialog.alert('提示: ', data, status);
        });
    };

    // 编辑无感知接口
    dao.noperceptionShow = function ($scope, id) {
        var url = "/noperception/show?id=" + id;
        $http.get(url).success(function(data, status, headers, config) {
            if(data.result == 'FAIL'){
                jDialog.alert('提示', data.message);
                return;
            }
            var _data = data.data;
            $scope.customerId = _data.customerId;
            $scope.cascadeLabel = _data.cascadeLabel;
            $scope.customerName = _data.customerName;
            $scope.remark = _data.remark;
            $scope.dateType = _data.dateType;
            $scope.time = _data.time;
            $scope.timeDsp = _data.timeDsp;
            $scope.status = _data.status;
            $scope.statusDsp = _data.statusDsp;
            $scope.unit = _data.unit;

            // 策略类型
            var dateType = _data.dateType;
            var time = _data.time;
            if (dateType == 1) {
                $scope.timeH = time;
            } else if (dateType == 2) {
                $scope.timeD = time;
            }
        }).error(function(data, status, headers, config) {
            jDialog.alert('提示: ', data, status);
        });
    };

    // 批量开启无感应接口
    dao.noperceptionOpen = function ($scope, ids) {
        // 请求数据
        var params = {
            'id': ids,
            'status':"1"
        };

        var url = "/noperception/open?" + $.param(params);
        $http.get(url).success(function(data, status, headers, config) {
            if(data.result == 'FAIL'){
                jDialog.alert('提示', data.message);
                return;
            }
            jDialog.alert('开启无感应', '批量开启无感应成功！');
            $scope.list();
            // 默认不选中
            $(':checkbox[name="checkboxAll"]').prop('checked', false);
        }).error(function(data, status, headers, config) {
            jDialog.alert('提示: ', data, status);
        });
    };

    // 批量关闭无感应接口
    dao.noperceptionClose = function ($scope, ids) {
        // 请求数据
        var params = {
            'id': ids,
            'status':"2"
        };

        var url = "/noperception/open?" + $.param(params);
        $http.get(url).success(function(data, status, headers, config) {
            if(data.result == 'FAIL'){
                jDialog.alert('提示', data.message);
                return;
            }
            jDialog.alert('关闭无感应', '批量关闭无感应成功！');
            $scope.list();
            // 默认不选中
            $(':checkbox[name="checkboxAll"]').prop('checked', false);
        }).error(function(data, status, headers, config) {
            jDialog.alert('提示: ', data, status);
        });
    };

    return dao;
});