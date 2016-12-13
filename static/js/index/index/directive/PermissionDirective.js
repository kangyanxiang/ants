/**
 * 权限指令(左侧菜单树)
 * @auth 许小满
 * @date 2015-10-23 14:33:20
 */
indexApp.directive('permissionmenu', function($cacheFactory) {
    return {
        restrict: 'EA',
        replace: false,
        link:function(scope, element, attrs){
            var code = attrs.permissionmenu;
            if(isBlank(code)){//空字符串时跳过
                return;
            }
            var permissionCache = $cacheFactory.get('permissionCache');//获取页面中的权限缓存对象
            var codeData = permissionCache.get('codes');
            //特殊权限控制，当电信侧管理员时屏蔽站点、模板、推送策略
            var portalMenuArray = ['site_manager','site_manager_list','site_manager_default','strategy_manager','strategy_manager_list'];
            var userType = codeData.userType;//获取缓存中的用户类型
            if(userType != 1){
                var portalMenuArrayLength = portalMenuArray.length;
                for(var i=0; i<portalMenuArrayLength; i++){
                    if(code == portalMenuArray[i]){
                        return;
                    }
                }
            }
            //特殊权限控制,执行结束

            var codeArray = codeData.codes;//获取缓存的权限编号数组
            var maxLength = codeArray != null ? codeArray.length : 0;
            var hasPermission = false;//判断是否有权限访问：true 有权限、false 无权限
            for(var i=0; i<maxLength; i++){
                if(code == codeArray[i]){
                    hasPermission = true;
                    break;
                }
            }
            if(hasPermission){//有权限操作时，显示控件
                element.show();
                element.addClass('show');
            }
        }
    };
});
/**
 * 权限指令(右侧内容区域)
 * @auth 许小满
 * @date 2015-10-23 14:33:20
 */
indexApp.directive('permission', function($cacheFactory) {
    return {
        restrict: 'EA',
        replace: false,
        link:function(scope, element, attrs){
            var code = attrs.permission;
            if(isBlank(code)) {//空字符串时跳过
                return;
            }
            var permissionCache = $cacheFactory.get('permissionCache');//获取页面中的权限缓存对象
            var codeData = permissionCache.get('codes');
            var codeArray = codeData.codes;
            var maxLength = codeArray !== null ? codeArray.length : 0;
            var hasPermission = false;//判断是否有权限访问：true 有权限、false 无权限
            for(var i=0; i<maxLength; i++){
                if(code == codeArray[i]){
                    hasPermission = true;
                    break;
                }
            }
            if(!hasPermission){//无权限操作时，删除控件
                element.remove();
            }
        }
    };
});