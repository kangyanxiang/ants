/**
 * Created by Shin on 2015/12/11.
 * 项目管理-配置项目信息 projectEditController
 */
indexApp.controller('projectEditController', function($scope, $location, locationService, projectService) {


    /**
     * validateFail 编辑项目信息时的校验
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/12/11
     */
    function validateFail($scope){
        //校验项目名称
        if(projectService.validateProjectNameFail($scope)){
            return true;
        }
        //校验联系人
         if(projectService.validateContactNameFail($scope)){
             return true;
         }
        //校验联系方式
         if(projectService.validateContactWayFail($scope)){
             return true;
         }
        //校验所属平台
        if(projectService.validatePlatformFail($scope)){
            return true;
        }
        //校验地区：省
        if(projectService.validateProvinceIdFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * edit 项目管理-编辑项目信息
     * @param $scope
     * @author 沈亚芳
     * @date 2015/12/11
     */
    $scope.edit = function (){
        if(validateFail($scope)){
            return;
        }
        projectService.edit($scope);
    };


    /**
     * cityChange 点击省获取市数据集合
     * @param $event
     * @author 沈亚芳
     * @date 2016/07/22
     */
    $scope.cityChange = function($event){
        //获取provinceId（此值传入cityList）
        var provinceId = defaultString($scope.project.provinceId);
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
        var cityId = defaultString($scope.project.cityId);
        //获取区/县数据集合
        locationService.countyList($scope, cityId);
    };


    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2015/12/11
     */
    function init(){
        // 从访问地址获取id
        var id = $location.search()['id'];
        $scope.id = id;
        //获取登录平台的信息集合
        projectService.getAllPlatformInfo($scope);
        // 根据id来获取要编辑的项目的信息
        projectService.getProjectInfoById($scope, id);
        //初始化Tips
        projectService.initTipsForAddProjectChange();
        //初始化获取省数据集合
        locationService.provinceList($scope);
    }
    init();
});