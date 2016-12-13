/**
 * Created by zhuxuehuang on 16-6-16.
 */

indexApp.factory('hotAreaService', function($rootScope, hotAreaDao) {
    var service = {};

    /**
     * 热点管理列表
     * @Auther: zhuxuehuang
     * @Date: 2016-6-8
     */
    service.hotAreaList = function ($scope) {
        hotAreaDao.hotAreaList($scope);
    };

    /**
     * 删除所选热点
     * @param $scope
     * @param ids
     * @Auther: zhuxuehuang
     * @Date: 2016-6-16
     */
    service.delete = function ($scope, ids) {
        hotAreaDao.delete($scope, ids);
    };

    return service;
});