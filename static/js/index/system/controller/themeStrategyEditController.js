/**
 * Created by Shin on 2016/04/21.
 * 换肤配置-皮肤策略-编辑 themeStrategyEditController
 */
indexApp.controller('themeStrategyEditController', function($scope, $location, themeService, customerService) {

    /**
     * validateFail 检验编辑皮肤策略功能所涉及到的参数是否验证通过
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/04/24
     */
    function validateFail($scope){
        //校验皮肤名称
        if(themeService.validateThemeIdForThemeStrategyFail($scope)){
            return true;
        }
        //校验类别
        if(themeService.validateThemeTypeForThemeStrategyFail($scope)){
            return true;
        }
        //校验内容
        if(themeService.validateContentForThemeStrategyFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * themeStrategyEdit 编辑皮肤策略
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/25
     */
    function themeStrategyEdit (){
        if(validateFail($scope)){
            return;
        }
        themeService.themeStrategyEdit($scope);
    }
    $scope.themeStrategyEdit = themeStrategyEdit;


    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2016/04/25
     */
    function init (){
        var id = $location.search()['id'];

        //皮肤信息（下拉）
        themeService.themeSelectList($scope);

        //根据皮肤策略ID查询该皮肤策略的相关信息
        themeService.themeStrategyById($scope, id);

        //信息校验提示
        themeService.initTipsForThemeStrategyChange();

        //获取当前客户集合
        customerService.customerSelect2('customerIdAndLevel');
    }
    init();
});