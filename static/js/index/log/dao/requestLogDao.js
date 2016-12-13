/**
 * Created by Shin on 2016/04/21.
 * 请求日志 requestLogDao
 */
indexApp.factory('requestLogDao', function($http, $location, pageDao) {
    /**
     * service 定义个全局dao，并将其置空
     * @param 空
     * @author 沈亚芳
     * @date 2016/04/21
     */
    var dao = {};


    /**
     * list 请求日志
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/21
     */
    dao.list = function($scope){
        loadingAppend();
        var pageNo = pageDao.getPageNo();
        var keywords = defaultString($scope.keywords);
        var startDate = $scope.startDate;
        var endDate = $scope.endDate;

        //如果是汉字,用encodeURIComponent转码
        keywords = encodeURIComponent(keywords);

        var url = '/requestlog/list?pageNo=' + pageNo + '&keywords=' + keywords + '&startDate=' + startDate + '&endDate=' + endDate;

        $http.get(url)
            .success(function(data){
                loadingRemove();
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                //数据记录集
                $scope.requestLogs = data.records;
                //开始记录数
                $scope.begin = data.begin;
                $scope.data = data;
                //初始化分页栏
                pageDao.init($scope, data);
            })

    };


    /**
     * show 日志详情
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/21
     */
    dao.show = function ($scope, id){
        var url = '/requestlog/show?id=' + id;

        $http.get(url)
            .success(function(data){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                $scope.requestLog = data.data;
            })
    };


    /**
     * 返回 dao
     * @author 沈亚芳
     * @date 2016/04/21
     */
    return dao;
});