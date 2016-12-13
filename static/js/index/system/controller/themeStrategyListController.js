/**
 * Created by Shin on 2016/04/21.
 * 换肤配置-皮肤策略-列表 themeStrategyListController
 */
indexApp.controller('themeStrategyListController', function($scope, themeService, customerService) {
    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2016/04/21
     */
    function init (){
        //获取当前客户集合
        customerService.customerSelect2('customerIdAndLevel');

        //皮肤信息（下拉）
        themeService.themeSelectList($scope);
    }
    init();


    /**
     * themeStrategyList 皮肤策略列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/21
     */
    function list (){
        themeService.themeStrategyList($scope);
    }
    $scope.list = list;
    list();


    /**
     * deleteStrategy 删除皮肤策略
     * @param $scope
     * @param id  删除要提交的参数为：id
     * @author 沈亚芳
     * @date 2016/04/21
     */
    $scope.deleteStrategy = function(id) {
        jDialog.confirm('删除皮肤策略', '<div class="rows"><label class="w100">您确定要删除该推送策略吗？</label></div>', function(){
            themeService.deleteStrategy($scope, id);
            jDialog.hide();
        });
    };


    /**
     * resetWidth 重置列表因fixed引起的宽度问题
     * @author 沈亚芳
     * @date 2016/04/21
     */
    resetWidth();
});