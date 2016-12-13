/**
 * Created by Shin on 2016/04/21.
 * 请求日志-日志详情 requestLogShowController
 */
indexApp.controller('requestLogShowController', function($scope, requestLogService) {
    /**
     * show 日志详情
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/21
     */
    var id = $scope.id;
    requestLogService.show($scope, id);
});
