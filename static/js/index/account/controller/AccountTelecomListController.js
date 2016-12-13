/**
 * Created by Shin on 2015/11/23.
 * 电信侧管理员列表页面 accountTelecomListController
 */
indexApp.controller('accountTelecomListController', function($scope, accountService, locationService) {

    /**
     * list 电信侧账号列表
     * @param
     * @author 沈亚芳
     * @date 2015/11/26
     */
    function list (){
        accountService.list($scope);
    }
    $scope.list = list;
    list();


    /**
     * delete 逻辑删除账号（电信侧账号列表）
     * @param id
     * @author 沈亚芳
     * @date 2015/11/30
     */
    $scope.delete = function(id) {
        jDialog.confirm('删除账号', '<div class="rows"><label class="w100">您确定要删除该账号吗？</label></div>', function(){
            accountService.delete($scope, id);
            jDialog.hide();
        });
    };


    /**
     * pwdReset 重置密码（电信侧和客户侧账号列表）
     * @param id
     * @author 沈亚芳
     * @date 2015/11/30
     */
    $scope.pwdReset = function(id){
        jDialog.confirm('密码重置', '<div class="rows"><label class="w100">您确定要将该账号的密码重置吗？</label></div>', function(){
            accountService.pwdReset($scope, id);
            jDialog.hide();
        });
    };


    /**
     * cityChange 点击省获取市数据集合
     * @param $event
     * @author 沈亚芳
     * @date 2015/11/30
     */
    $scope.cityChange = function($event){
        //获取provinceId（此值传入cityList）
        var provinceId = defaultString($scope.provinceId);
        //获取市数据集合
        locationService.cityList($scope, provinceId);
    };


    /**
     * countyChange 点击市获取区/县数据集合
     * @param $event
     * @author 沈亚芳
     * @date 2015/11/30
     */
    $scope.countyChange = function($event){
        //获取cityId（此值传入countyList）
        var cityId = defaultString($scope.cityId);
        //获取区/县数据集合
        locationService.countyList($scope, cityId);
    };


    /**
     * init 初始化相关函数
     * @author 沈亚芳
     * @date 2015/11/26
     */
    function init(){
        //页面加载时即获取角色下拉列表的信息
        accountService.roleList($scope);

        //初始化获取省数据集合
        locationService.provinceList($scope);
    }
    init();


    /**
     * resetWidth 重置列表因fixed引起的宽度问题
     * @author 沈亚芳
     * @date 2015/11/26
     */
    resetWidth();
});