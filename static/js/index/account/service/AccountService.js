/**
 * Created by Shin on 2015/11/24.
 * 账号管理 Service
 */
indexApp.factory('accountService',function($rootScope, accountDao) {
    /**
     * service 定义个全局service，并将其置空
     * @param 空
     * @author 沈亚芳
     * @date 2015/11/24
     */
    var service = {};


    /**
     * validateOldPasswordFail 校验旧密码（功能：修改密码）
     * @param $scope 校验的参数：oldPassword
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.validateOldPasswordFail = function($scope) {
        var $oldPassword = $('#oldPassword');
        var oldPassword = $scope.oldPassword;
        //校验是否为空
        if(isBlank(oldPassword)){
            updateShowTipos($oldPassword, '旧密码不能为空！');
            return true;
        }
        ////校验旧密码格式是否正确
        //if (!chkString(oldPassword, defrules.password)) {
        //    updateShowTipos($oldPassword, '密码格式为：4-20位字符，包括字母、数字、下划线！');
        //    return true;
        //}
        return false;
    };


    /**
     * validateNewPasswordFail 校验新密码（功能：修改密码）
     * @param $scope 校验的参数：newPassword
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.validateNewPasswordFail = function($scope) {
        var $newPassword = $('#newPassword');
        var oldPassword = $scope.oldPassword;
        var newPassword = $scope.newPassword;
        //校验新密码是否为空
        if(isBlank(newPassword)){
            updateShowTipos($newPassword, '新密码不能为空！');
            return true;
        }
        //校验新密码格式是否正确
        if (!chkString(newPassword, defrules.passwordNew)){
            updateShowTipos($newPassword, "密码必须由1-50位字符组成，包含字母、数字、下划线、连接符、@、$");
            return true;
        }
        //if (newPassword.length > 50) {
        //    updateShowTipos($newPassword, '密码格式为：1-50位任意字符！');
        //    return true;
        //}
        //校验新密码是否与旧密码相同
        if (newPassword == oldPassword){
            updateShowTipos($newPassword, '新密码与旧密码不能相同，请重新输入新密码！');
            return true;
        }
        return false;
    };


    /**
     * validateSurePasswordFail 校验确认密码（功能：修改密码）
     * @param $scope 校验的参数：surePassword
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.validateSurePasswordFail = function($scope) {
        var $surePassword = $('#surePassword');
        var newPassword = $scope.newPassword;
        var surePassword = $scope.surePassword;
        //校验确认密码是否为空
        if(isBlank(surePassword)){
            updateShowTipos($surePassword, '确认密码不能为空！');
            return true;
        }
        //校验确认密码格式是否正确
        if (!chkString(surePassword, defrules.passwordNew)){
            updateShowTipos($surePassword, "密码必须由1-50位字符组成，包含字母、数字、下划线、连接符、@、$");
            return true;
        }
        if($.trim(newPassword) != $.trim(surePassword)){
            updateShowTipos($surePassword, '确认密码与新密码不一致，请重新输入！');
            return true;
        }
        return false;
    };


    /**
     * initTipsForPwdChange 初始化校验密码时错误的提示信息（功能：修改密码）
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.initTipsForPwdChange = function() {
        var $oldPassword = $('#oldPassword');
        var $newPassword = $('#newPassword');
        var $surePassword = $('#surePassword');
        initTipsArray([$oldPassword, $newPassword, $surePassword]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function (){
            destroyTipsArray([$oldPassword, $newPassword, $surePassword]);
        });
    };


    /**
     * validateContactPersonFail 校验联系人（功能：修改账号基本信息）
     * @param $scope 校验的参数：contactPerson
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.validateContactPersonFail = function($scope){
        var $contactPerson = $('#contactPerson');
        var contactPerson = $scope.account.contactPerson;
        if (!chkString(contactPerson, defrules.contact)){
            updateShowTipos($contactPerson, '联系人格式为：1-20位汉字、字母，不含特殊字符！');
            return true;
        }
        return false;
    };


    /**
     * validateContactWayFail 检验联系方式（功能：修改账号基本信息）
     * @param $scope 校验的参数：contactWay
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.validateContactWayFail = function($scope){
        var $contactWay = $('#contactWay');
        var contactWay = $scope.account.contactWay;
        if(!chkString(contactWay, defrules.cellphone)){
            updateShowTipos($contactWay, '联系方式为手机号，格式为：11位以1开头的符合手机号码规则的数字');
            return true;
        }
        return false;
    };


    /**
     * initTipsForPersonMobileChange 检验联系人和联系方式错误时的提示信息（功能：修改账号基本信息）
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.initTipsForPersonMobileChange = function(){
        var $contactPerson = $('#contactPerson');
        var $contactWay =$('#contactWay');
        initTipsArray([$contactPerson, $contactWay]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$contactPerson, $contactWay]);
        });
    };


    /**
     * validateAccountUserNameFail 校验账号名称（功能：添加账号（电信侧））
     * @param $scope 校验的参数：userName
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.validateAccountUserNameFail = function($scope){
        var $userName = $('#userName');
        var userName = $scope.account.userName;
        if(isBlank(userName)){
            updateShowTipos($userName, '账号名称不能为空！');
            return true;
        }
        if(!chkString(userName, defrules.userName)){
            updateShowTipos($userName, '账号的格式为：1-50位字母、数字、下划线、连接符组成');
            return true;
        }
        return false;
    };


    /**
     * validateAccountRoleIdFail 校验角色（功能：添加账号（电信侧））
     * @param $scope 校验的参数：roleId
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.validateAccountRoleIdFail = function($scope){
        var $roleId = $('#roleId');
        var roleId = $scope.account.roleId;
        if(isBlank(roleId)){
            updateShowTipos($roleId, '请选择一个角色');
            return true;
        }
        return false;
    };


    /**
     * validateAccountProvinceIdFail 校验省（功能：添加账号（电信侧））
     * @param $scope 校验的参数：provinceId
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.validateAccountProvinceIdFail = function($scope){
        var roleId = $scope.account.roleId;
        var $provinceId = $('#provinceId');
        var provinceId = $scope.account.provinceId;

        if (roleId == '2' || roleId == '3' || roleId == '4'){
            if(isBlank(provinceId)){
                updateShowTipos($provinceId, '请选择一个省');
                return true;
            }
        }
        return false;
    };


    /**
     * validateAccountCityAreaByRoleFail 根据角色校验市、区县（功能：添加账号（电信侧））
     * @param $scope 校验的参数：cityId、areaId
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.validateAccountCityAreaByRoleFail = function($scope){
        var roleId = $scope.account.roleId;

        //当角色选的是市级或区县级，那对应的地区级别就得校验
        if (roleId == '3') {
            var $cityId = $('#cityId');
            var cityId = $scope.account.cityId;
            if(isBlank(cityId)){
                updateShowTipos($cityId, '请选择一个市');
                return true;
            }
            return false;
        } else if (roleId == '4') {
            var $areaId = $('#areaId');
            var areaId = $scope.account.areaId;
            if(isBlank(areaId)){
                updateShowTipos($areaId, '请选择一个区（县）');
                return true;
            }
            return false;
        }
        return false;
    };


    /**
     * validateAccountContactPersonFail 校验联系人（功能：添加账号（电信侧））（功能：编辑账号（电信侧））
     * @param $scope 校验的参数：contactPerson
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.validateAccountContactPersonFail = function($scope){
        var $contactPerson = $('#contactPerson');
        var contactPerson = $scope.account.contactPerson;
        if(isBlank(contactPerson)){
            updateShowTipos($contactPerson, '联系人信息不允许为空');
            return true;
        }
        if(!chkString(contactPerson, defrules.contact)){
            updateShowTipos($contactPerson, '联系人的格式为：1-20位汉字、字母，不含特殊字符！');
            return true;
        }
        return false;
    };


    /**
     * validateAccountContactWayFail 检验联系方式（功能：添加账号（电信侧））（功能：编辑账号（电信侧））
     * @param $scope 校验的参数：contactWay
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.validateAccountContactWayFail = function($scope){
        var $contactWay = $('#contactWay');
        var contactWay = $scope.account.contactWay;
        if(!chkString(contactWay, defrules.cellphone)){
            updateShowTipos($contactWay, '联系方式为手机号，格式为：11位以1开头的符合手机号码规则的数字');
            return true;
        }
        return false;
    };


    /**
     * validateAccountRemarkFail 校验备注（功能：添加账号（电信侧））（功能：编辑账号（电信侧））
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.validateAccountRemarkFail = function($scope){
        var $remark = $('#remark');
        var remark = $scope.account.remark;
        if (remark === undefined) {
            remark = '';
        }
        if(checkLengthFail(remark, 100)){
            updateShowTipos($remark, '备注的格式为：0-100位任意字符');
            return true;
        }
        return false;
    };


    /**
     * initTipsForAddTelecomAccountChange 各校验字段相关信息提示的初始化（功能：添加账号（电信侧））（功能：编辑账号（电信侧））
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.initTipsForAddTelecomAccountChange = function(){
        var $userName = $('#userName');
        var $roleId = $('#roleId');
        var $projectId = $('#projectId');
        var $provinceId = $('#provinceId');
        var $cityId = $('#cityId');
        var $areaId = $('#areaId');
        var $contactPerson = $('#contactPerson');
        var $contactWay = $('#contactWay');
        var $remark = $('#remark');
        initTipsArray([$userName, $roleId, $provinceId, $cityId, $areaId, $contactPerson, $contactWay, $remark]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$userName, $roleId, $provinceId , $cityId, $areaId, $contactPerson, $contactWay, $remark]);
        });
    };


    /**
     * validateAccountUserNameForAdminFail 校验账号名称（功能：管理员账号）
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/06/24
     */
    service.validateAccountUserNameForAdminFail = function($scope){
        var $userName = $('#userName');
        var userName = $scope.account.userName;
        if(isBlank(userName)){
            updateShowTipos($userName, '账号不能为空！');
            return true;
        }
        if(!chkString(userName, defrules.userName)){
            updateShowTipos($userName, '账号的格式为：1-50位字母、数字、下划线、连接符组成');
            return true;
        }
        return false;
    };


    /**
     * validateAccountDeptNameForAdminFail 校验部门（功能：管理员账号）
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/06/24
     */
    service.validateAccountDeptNameForAdminFail = function($scope){
        var $deptName = $('#deptName');
        var deptName = $scope.account.deptName;
        if (isBlank(deptName)) {
            deptName = '';
        }
        if(deptName.length > 50){
            updateShowTipos($deptName, '部门格式为：1-50位任意字符');
            return true;
        }
        return false;
    };


    /**
     * validateAccountContactPersonForAdminFail 校验姓名（功能：管理员账号）
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/06/24
     */
    service.validateAccountContactPersonForAdminFail = function($scope){
        var $contactPerson = $('#contactPerson');
        var contactPerson = $scope.account.contactPerson;
        if(isBlank(contactPerson)){
            updateShowTipos($contactPerson, '姓名不能为空！');
            return true;
        }
        if(contactPerson.length > 50){
            updateShowTipos($contactPerson, '姓名格式为：1-50位任意字符');
            return true;
        }
        return false;
    };


    /**
     * validateAccountContactWayForAdminFail 校验联系电话（功能：管理员账号）
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/06/24
     */
    service.validateAccountContactWayForAdminFail = function($scope){
        var $contactWay = $('#contactWay');
        var contactWay = $scope.account.contactWay;
        if(isBlank(contactWay)){
            updateShowTipos($contactWay, '联系电话不能为空！');
            return true;
        }
        if(contactWay.length > 50){
            updateShowTipos($contactWay, '联系电话格式为：1-50位任意字符');
            return true;
        }
        return false;
    };


    /**
     * validateAccountRemarkForAdminFail 校验备注（功能：管理员账号）
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/06/24
     */
    service.validateAccountRemarkForAdminFail = function($scope){
        var $remark = $('#remark');
        var remark = $scope.account.remark;
        if (isBlank(remark)) {
            remark = '';
        }
        if(remark.length > 100){
            updateShowTipos($remark, '备注格式为：1-100位任意字符');
            return true;
        }
        return false;
    };


    /**
     * initTipsForAddAdminCountChange 各校验字段相关信息提示的初始化（功能：添加管理员账号）
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.initTipsForAddAdminCountChange = function(){
        var $userName = $('#userName');
        var $deptName = $('#deptName');
        var $contactPerson = $('#contactPerson');
        var $contactWay = $('#contactWay');
        var $remark = $('#remark');
        initTipsArray([$userName,$deptName,$contactPerson,$contactWay,$remark]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$userName,$deptName,$contactPerson,$contactWay,$remark]);
        });
    };


    /**
     * getCurUserInfo 获取当前登录账号的相关信息
     * @param $scope
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.getCurUserInfo = function($scope){
        accountDao.getCurUserInfo($scope);
    };


    /**
     * pwdEdit 修改密码功能
     * @param $scope
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.pwdEdit = function ($scope) {
        accountDao.pwdEdit($scope);
    };


    /**
     * infoModify 修改账号基本信息
     * @param $scope
     * @author 沈亚芳
     * @date 2015/11/27
     */
    service.infoModify = function ($scope) {
        accountDao.infoModify($scope);
    };


    /**
     * delete 逻辑删除账号（电信侧账号列表）（管理员账号列表）
     * @param $scope
     * @param id
     * @author 沈亚芳
     * @date 2015/11/26
     */
    service.delete = function($scope, id){
        accountDao.delete($scope, id);
    };


    /**
     * pwdReset 重置密码（电信侧和客户侧账号列表）（管理员账号列表）
     * @param $scope
     * @param id
     * @author 沈亚芳
     * @date 2015/11/26
     */
    service.pwdReset = function($scope, id){
        accountDao.pwdReset($scope, id);
    };


    /**
     * customerList 客户侧账号列表
     * @param $scope
     * @author 沈亚芳
     * @date 2015/11/26
     */
    service.customerList = function($scope){
        accountDao.customerList($scope);
    };


    /**
     * list 电信侧账号列表
     * @param $scope
     * @author 沈亚芳
     * @date 2015/11/26
     */
    service.list = function($scope){
        accountDao.list($scope);
    };


    /**
     * roleList 获取角色信息，用于select，数据权限由后端进行控制
     * @param $scope
     * @author 沈亚芳
     * @date 2015/11/26
     */
    service.roleList = function($scope){
        accountDao.roleList($scope);
    };

    
    service.projectList = function($scope){
    	accountDao.projectList($scope);
    }
    
    

    /**
     * add 添加账号（电信侧管理员）
     * @param $scope
     * @author 沈亚芳
     * @date 2015/11/30
     */
    service.add = function($scope){
        accountDao.add($scope);
    };


    /**
     * getAccountById 根据id来获取要编辑的账号的信息
     * @param $scope
     * @param id
     * @author 沈亚芳
     * @date 2015/11/30
     */
    service.getAccountById = function($scope, id){
        accountDao.getAccountById($scope, id);
    };


    /**
     * edit 编辑账号信息（电信侧管理员）
     * @param $scope
     * @author 沈亚芳
     * @date 2015/11/30
     */
    service.edit = function($scope){
        accountDao.edit($scope);
    };


    /**
     * getPermissionById 根据账号ID查找它已有的权限，用于已有权限的显示
     * @param $scope
     * @author 沈亚芳
     * @date 2015/12/01
     */
    service.getPermissionById = function($scope){
        accountDao.getPermissionById($scope);
    };


    /**
     * getPermission 根据当前登录账号来查找权限-左侧可选权限的数据
     * @param $scope
     * @author 沈亚芳
     * @date 2015/12/01
     */
    service.getPermission = function($scope){
        accountDao.getPermission($scope);
    };


    /**
     * getExistPermission 根据id从数据中获取已分配的权限，用于左侧那些已经被选中的权限的显示样式的变更
     * @param $scope
     * @author 沈亚芳
     * @date 2015/12/01
     */
    service.getExistPermission = function($scope){
        accountDao.getExistPermission($scope);
    };


    /**
     * permissionAssign 分配权限的保存（电信侧和客户侧）（管理员账号）
     * @param $scope
     * @author 沈亚芳
     * @date 2015/12/01
     */
    service.permissionAssign = function($scope){
        accountDao.permissionAssign($scope);
    };


    /**
     * adminList 管理员账号列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/06/24
     */
    service.adminList = function($scope){
        accountDao.adminList($scope);
    };


    /**
     * adminAdd 创建管理员账号
     * @param $scope
     * @author 沈亚芳
     * @date 2016/06/24
     */
    service.adminAdd = function($scope){
        accountDao.adminAdd($scope);
    };


    /**
     * getAccountAdminById 根据id来获取要编辑的管理员账号的信息
     * @param $scope,id
     * @author 沈亚芳
     * @date 2016/06/27
     */
    service.getAccountAdminById = function($scope, id){
        accountDao.getAccountAdminById($scope, id);
    };


    /**
     * adminEdit 编辑管理员账号的信息
     * @param $scope
     * @author 沈亚芳
     * @date 2016/06/27
     */
    service.adminEdit = function($scope){
        accountDao.adminEdit($scope);
    };


    /**
     * 返回 service
     * @author 沈亚芳
     * @date 2015/11/24
     */
    return service;
});