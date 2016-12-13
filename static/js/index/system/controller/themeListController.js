/**
 * Created by Shin on 2016/04/21.
 * 换肤配置-皮肤列表-列表 themeListController
 */
indexApp.controller('themeListController', function($scope, themeService) {

    /**
     * themeList 皮肤列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/21
     */
    function list (){
        themeService.themeList($scope);
    }
    $scope.list = list;
    list();


    /**
     * deleteTheme 删除皮肤策略
     * @param $scope
     * @param id  删除要提交的参数为：id
     * @author 沈亚芳
     * @date 2016/04/21
     */
    $scope.deleteTheme = function(id) {
        jDialog.confirm('删除皮肤', '<div class="rows"><label class="w100">您确定要删除该皮肤吗？</label></div>', function(){
            themeService.deleteStrategy($scope, id);
            jDialog.hide();
        });
    };

});