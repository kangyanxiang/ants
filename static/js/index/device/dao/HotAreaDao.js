/**
 * Created by zhuxuehuang on 16-6-16.
 */


/**
 * 热点管理列表（搜索）
 * @param $scope
 * @Auther: zhuxuehuang
 * @Date: 2016-6-8
 */

indexApp.factory('hotAreaDao', function($http, $location, pageDao, locationDao) {

    var dao = {};

    /**
     * 热点管理列表
     * @Auther: zhuxuehuang
     * @Date: 2016-6-20
     */
    dao.hotAreaList = function ($scope) {
        loadingAppend();
        var pageNo = pageDao.getPageNo();
        var provinceId = defaultString($scope.provinceId);//省id
        var cityId = defaultString($scope.cityId);//市id
        var countyId = defaultString($scope.countyId);//区/县id
        var customerIdAndLevel = defaultString($scope.customerIdAndLevel);
        //以#拆分customerIdAndLevel
        var customerIdAndLevelArray = customerIdAndLevel.split('#');
        var customerId = customerIdAndLevelArray[0];
        var cascadeLabel = customerIdAndLevelArray[1];
        if (isBlank(customerId)) {
            customerId = '';
        }
        if (isBlank(cascadeLabel)) {
            cascadeLabel = '';
        }

        var keywords = defaultString($scope.keywords); // 设备名称关键字
        var mac = defaultString($scope.mac); // apMac
        var deviceState = defaultString($scope.deviceState); // 设备状态
        if(pageNo == "上一页" || pageNo == "下一页"){
            return;
        }

        var params = {
            pageNo: pageNo,
            customerId: customerId,
            cascadeLabel: cascadeLabel,
            deviceState: deviceState,
            keywords: keywords,
            mac: mac,
            provinceId: provinceId,
            cityId: cityId,
            countyId: countyId
        };
        var url = "/hotArea/list?" + $.param(params);
        $http.get(url).success(function(data, status, headers, config){
            loadingRemove();
            if(data.result == 'FAIL'){
                jDialog.alert('提示', data.message);
                return;
            }
            $scope.hotAreas = data.records;//数据集
            $scope.begin = data.begin;//起始行
            $scope.data = data;
            pageDao.init($scope, data);//初始化分页栏
        });
    };

    /**
     * 删除所选热点信息
     * @param $scope
     * @param ids
     * @Auther: zhuxuehuang
     * @Date: 2016-6-16
     */
    dao.delete = function ($scope, ids) {
        var url = '/hotArea/delete?mac=' + ids;
        $http.get(url).success(function(data, status, headers, config) {
            if(data.result == 'FAIL'){
                jDialog.alert('提示：', data.message);
                return;
            }
            jDialog.alert('热点删除：', data.message || '删除成功');
            $scope.list();
        });
    };

    return dao;
});