/**
 * Created by zhuxuehuang on 16-4-27.
 */

indexApp.controller('noperceptionShowController', function($scope, $location, noperceptionService) {

    // 无感应id
    var id = $location.search()['id'];

    // 获取无感应数据
    noperceptionService.noperceptionShow($scope, id);
});