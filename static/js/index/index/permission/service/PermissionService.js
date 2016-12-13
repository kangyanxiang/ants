indexApp.factory('permissionService',function(permissionDao, $cacheFactory){
    var service = {};

    //获取当前用户权限编号集合
    service.getCurUserPermissionCode = function(){
        var permissionCache = $cacheFactory('permissionCache');
        var codes = permissionDao.getCurUserPermissionCode();
        //console.log(codes);
        permissionCache.put('codes',codes);
    };

    return service;
});