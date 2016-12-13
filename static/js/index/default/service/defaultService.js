/**
 * Created by zhuxh on 16-1-25.
 */

indexApp.factory('defaultService', function (defaultDao) {
    // 函数引用
    var service = {};

    service.projectCountAPI = defaultDao.projectCountAPI;

    service.userCountAPI = defaultDao.userCountAPI;

    service.deviceCountAPI = defaultDao.deviceCountAPI;

    return service;
});