/**
 * Created by Shin on 2015/12/8.
 * 项目列表 projectListController
 */
indexApp.controller('projectListController', function($scope, locationService, projectService) {

    /**
     * platformList 用户管理-黑名单管理-列表
     * @author 沈亚芳
     * @date 2015/12/08
     */
    function list (){
        projectService.projectList($scope);
    }
    $scope.list = list;
    list();


    /**
     * delete 根据id删除对应的项目
     * @author 沈亚芳
     * @date 2015/12/08
     */
    $scope.delete = function(projectId) {
        jDialog.confirm('删除项目', '<div class="rows"><label class="w100">您确定要删除该项目吗？</label></div>', function(){
            projectService.delete($scope, projectId);
            jDialog.hide();
        });
    };


    /**
     * exportProjects 导出项目列表信息
     * @author 沈亚芳
     * @date 2016/06/08
     */
    function exportProjects () {
        projectService.exportProjects($scope);
    }
    $scope.exportProjects = exportProjects;


    /**
     * cityChange 点击省获取市数据集合
     * @param $event
     * @author 沈亚芳
     * @date 2016/07/22
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
     * @date 2016/07/22
     */
    $scope.countyChange = function($event){
        //获取cityId（此值传入countyList）
        var cityId = defaultString($scope.cityId);
        //获取区/县数据集合
        locationService.countyList($scope, cityId);
    };


    function init() {
        /**
         * resetWidth 重置列表因fixed引起的宽度问题
         * @author 沈亚芳
         * @date 2015/12/08
         */
        resetWidth();

        // 项目下拉列表
        projectService.projectSelect2('fkProjectId');

        //获取登录平台的信息集合
        projectService.getAllPlatformInfo($scope);

        //初始化获取省数据集合
        locationService.provinceList($scope);
    }

    init();
});
