indexApp.factory('operationlogDao', function ($http, pageDao) {
    var dao = {};
    //操作日志列表
    dao.list = function ($scope) {
        loadingAppend();
        var pageNo = pageDao.getPageNo();
        var startDate = $scope.startDate;
        var userName = $scope.userName;
        var keywords = defaultString($scope.keywords);
        var params = {
            pageNo: pageNo,
            date: startDate,
            userName: userName,
            keywords: keywords
        };
        var url = '/operationlog/list?' + $.param(params);
        $http.get(url)
            .success(function (data, status, headers, config) {
                loadingRemove();
                if (data.result == 'FAIL') {
                    jDialog.alert('提示: ', data.message);
                    return;
                }
                $scope.operationlogs = data.records;//数据集
                $scope.begin = data.begin;//起始行
                $scope.data = data;
                pageDao.init($scope, data);//初始化分页栏
            })
            .error(function (data, status, headers, config) {
            });
    };

    //根据id查找操作日志详情
    dao.show = function ($scope, id) {
        var url = "/operationlog/show?id=" + id;
        $http.get(url)
            .success(function (data, status, headers, config) {
                if (data.result == 'FAIL') {
                    jDialog.alert('提示: ', data.message);
                    return;
                }
                $scope.operationlog = data.data;//数据集
            })
            .error(function (data, status, headers, config) {
                jDialog.alert('提示: ', datastatus);
            });
    };

    return dao;
});