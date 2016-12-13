/**
 * Created by Shin on 2015/11/27.
 * 创建电信侧管理员 accountAddController
 */
indexApp.controller('accountAddController', function($scope, accountService, locationService){

    /**
     * validateFail 检验添加账号（电信侧）功能所涉及到的参数是否验证通过
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/30
     */
    function validateFail($scope){
        //校验账号名
        if(accountService.validateAccountUserNameFail($scope)){
            return true;
        }
        //校验角色
        if(accountService.validateAccountRoleIdFail($scope)){
            return true;
        }
        //校验省
        if(accountService.validateAccountProvinceIdFail($scope)){
            return true;
        }
        //根据所选角色来校验地区中的市和区（县）
        if(accountService.validateAccountCityAreaByRoleFail($scope)){
            return true;
        }
        //校验联系人
        if(accountService.validateAccountContactPersonFail($scope)){
            return true;
        }
        //校验联系方式
        if(accountService.validateAccountContactWayFail($scope)){
            return true;
        }
        //校验备注
        if(accountService.validateAccountRemarkFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * cityChange 点击省获取市数据集合
     * @param $event
     * @author 沈亚芳
     * @date 2015/11/30
     */
    $scope.cityChange = function($event){
        //获取provinceId（此值传入cityList）
        var provinceId = defaultString($scope.account.provinceId);
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
        var cityId = defaultString($scope.account.cityId);
        //获取区/县数据集合
        locationService.countyList($scope, cityId);
    };


    /**
     * roleChange 根据角色选择的值不同来控制隐藏和显示地区这一行
     * @param $event
     * @author 沈亚芳
     * @date 2015/11/30
     */
    $scope.roleChange = function($event){
        var roleId = $scope.account.roleId;
        if (roleId == '6') {
            $('#areaSelect').hide();
        }else{
            $('#areaSelect').show();
        }

    };
    
    /**
     * add 添加账号（电信侧管理员）
     * @author 沈亚芳
     * @date 2015/11/30
     */
    $scope.add = function (){
        if(validateFail($scope)){
            return;
        }
        var projectId = $scope.account.projectId;
        if(projectId == null){
        	jDialog.alert('添加账号:', '项目名称不能为空');
        	return;
        }
        accountService.add($scope);
    };


    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2015/11/30
     */
    function init(){
        $scope.account = {};
        
        accountService.projectList($scope);
        //页面加载时即获取角色下拉列表的信息
        accountService.roleList($scope);
        //初始化Tips
        accountService.initTipsForAddTelecomAccountChange();
        //初始化获取省数据集合
        locationService.provinceList($scope);
    }
    init();
});
