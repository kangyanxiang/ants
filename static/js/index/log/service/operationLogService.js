indexApp.factory('operationlogService', function (operationlogDao) {
    var service = {};
    //操作日志列表
    service.list = function ($scope) {
        operationlogDao.list($scope);
    };

    //查看详情
    service.show = function ($scope, id) {
        operationlogDao.show($scope, id);
    };

    return service;
});