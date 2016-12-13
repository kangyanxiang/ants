indexApp.factory('permissionDao',function($http, $cacheFactory){
    var dao = {};

    //alert('permissionDao.init');

    /**
     * 获取当前用户权限编号集合
     * @auth 许小满
     * @dae 2015-10-24 9:54:22
     */
    dao.getCurUserPermissionCode = function(){
        //使用jquery同步加载数据
        var url = '/permission/getcuruserpermissioncode';
        var codeData = {};
        $.ajax({
            type: 'get',
            url: url,
            cache:false,
            async:false,
            dataType: 'json',
            success: function(data){
                codeData.codes = data.data;//权限编号数组
                codeData.userType = data.userType;//用户类型
            }
        });
        return codeData;
        /*

        $http.get(url)
            .success(function(data, status, headers, config){
                if(data.result == 'FAIL'){
                    jDialog.alert('系统异常: ' , data.message);
                    return;
                }
                //alert(data.data);
                //return data.data;


                var permissionCache = $cacheFactory('permissionCache');
                permissionCache.put('codes','aadssdfd');
            });
        */
    };

    return dao;
});