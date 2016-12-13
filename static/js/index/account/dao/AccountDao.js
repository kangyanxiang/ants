/**
 * Created by Shin on 2015/11/24.
 * 账号管理 Dao
 */
indexApp.factory('accountDao', function($http, $location, pageDao) {
    /**
     * dao 定义个全局dao，并将其置空
     * @param 空
     * @author 沈亚芳
     * @date 2015/11/24
     */
    var dao = {};


    /**
     * getCurUserInfo 获取当前登录账号的相关信息,并将data数据放入$scope
     * @param $scope
     * @author 沈亚芳
     * @date 2015/11/26
     */
    dao.getCurUserInfo = function($scope) {
        var url = '/user/getcuruserinfo';
        $http.get(url)
            .success(function(data, status, headers, config) {
                if(data.result == 'FAIL') {
                    jDialog.alert('提示:', data.message);
                    return;
                }
                $scope.account = data.data;

            });
    };


    /**
     * pwdEdit 修改密码功能，修改成功之后返回系统主页： /
     * @param $scope 修改要提交的参数为：oldPassword、newPassword、surePassword
     * @author 沈亚芳
     * @date 2015/11/27
     */
    dao.pwdEdit = function($scope){
        var oldPassword = $scope.oldPassword;
        var newPassWord = $scope.newPassword;
        var surePassword = $scope.surePassword;
        var url = '/account/pwdedit?oldPassword=' + oldPassword + '&newPassword=' + newPassWord + '&qpassword=' + surePassword;
        $http.get(url)
            .success(function(data, status, headers, config){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                //jDialog.alert('修改账号密码：', '密码修改成功！');
                //$location.path('/');

                jDialog.confirm('修改账号密码', '<div class="rows"><label class="w100">密码修改成功</label></div>', function(){
                    window.top.location.href = '/logout';
                    jDialog.hide();
                });
            });
    };


    /**
     * infoModify 修改账号基本信息，修改成功之后返回系统主页： /
     * @param $scope 修改要提交的参数为：contactPerson、contactWay
     * @author 沈亚芳
     * @date 2015/11/27
     */
    dao.infoModify = function($scope){
        $('#accountInfoModify').attr('disabled',true);
        //如果为空的情况下，用defaultString给予一个默认空值，以解决未定义
        var contactPerson = defaultString($scope.account.contactPerson);
        var contactWay = defaultString($scope.account.contactWay);
        //如果是汉字,用encodeURIComponent转码
        contactPerson = encodeURIComponent(contactPerson);
        var url = '/account/infomodify?contactPerson=' + contactPerson + '&contactWay=' + contactWay;
        $http.get(url)
            .success(function(data, status, headers, config){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    $('#accountInfoModify').removeAttr('disabled');
                    return;
                }
                jDialog.alert('修改账号基本信息', '修改成功');
                $location.path('/');
            });
    };


    /**
     * delete 逻辑删除账号（电信侧账号列表）（管理员账号列表）
     * @param $scope
     * @param id  删除要提交的参数为：id
     * @author 沈亚芳
     * @date 2015/11/26
     */
    dao.delete = function($scope, id){
        var url ='/account/delete?id=' + id;
        $http.get(url)
            .success(function(data, status, headers, config) {
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                jDialog.alert('账号删除：', '删除成功！');
                $scope.list();
            });
    };


    /**
     * pwdReset 重置密码（电信侧和客户侧账号列表）（管理员账号列表）
     * @param $scope
     * @param id 重置密码要提交的参数为：id
     * @author 沈亚芳
     * @date 2015/11/26
     */
    dao.pwdReset = function($scope, id){
        $('#pwdEdit').attr('disabled',true);
        var url = '/account/pwdreset?id=' + id;
        $http.get(url)
            .success(function(data, status, headers, config){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    $('#pwdEdit').removeAttr('disabled');
                    return;
                }
                var defaultPassword = data.defaultPassword;
                jDialog.alert('账号密码重置:', '密码重置成功，新密码是：' + defaultPassword);

            });
    };


    /**
     * customerList 客户侧账号列表
     * @param $scope 获取列表时要提交的参数为：pageNo、keywords
     * @author 沈亚芳
     * @date 2015/11/26
     */
    dao.customerList = function($scope){
        loadingAppend();
        var pageNo = pageDao.getPageNo();
        var keywords = defaultString($scope.keywords); // 客户名称
        var account = defaultString($scope.account); // 账号
        var params = {
            pageNo: pageNo,
            keywords: keywords,
            account: account
        };
        var url = '/accountcustomer/list?' + $.param(params);
        $http.get(url)
            .success(function(data, status, headers, config){
                loadingRemove();
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    return;
                }
                //数据记录集
                $scope.customerAccounts = data.records;
                //开始记录数
                $scope.begin = data.begin;
                $scope.data = data;
                //初始化分页栏
                pageDao.init($scope, data);
            });
    };


    /**
     * list 电信侧账号列表
     * @param $scope 获取列表时要提交的参数为：pageNo、keywords、roleId、provinceId、cityId、areaId
     * @author 沈亚芳
     * @date 2015/11/26
     */
    dao.list = function($scope){
        var pageNo = pageDao.getPageNo();
        var keywords = defaultString($scope.keywords);
        var roleId = $scope.roleId;
        var provinceId = $scope.provinceId;
        var cityId = $scope.cityId;
        var areaId = $scope.areaId;
        if(isBlank(roleId)){
            roleId = '';
        }
        if(isBlank(provinceId)){
            provinceId = '';
        }
        if(isBlank(cityId)){
            cityId = '';
        }
        if(isBlank(areaId)){
            areaId = '';
        }
        var url = '/account/list?pageNo=' + pageNo + '&keywords=' + keywords + '&roleId=' + roleId + '&provinceId=' + provinceId + '&cityId=' + cityId + '&areaId=' + areaId;
        $http.get(url)
            .success(function(data, status, headers, config){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    return;
                }
                accounts = data.records;
                //数据记录集
                $scope.accounts = data.records;
                //开始记录数
                $scope.begin = data.begin;
                $scope.data = data;
                //初始化分页栏
                pageDao.init($scope, data);
            });
    };


    /**
     * roleList 获取角色信息，用于select，数据权限由后端进行控制
     * @param $scope
     * @author 沈亚芳
     * @date 2015/11/26
     */
    dao.roleList = function ($scope) {
        var url = '/account/rolelist';
        $http.get(url)
            .success(function(data, status, headers, config){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    return;
                }
                $scope.roles = data.data;
            });
    };

    dao.projectList = function($scope){
    	var url = '/project/projectlist';
        $http.get(url)
            .success(function(data, status, headers, config){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    return;
                }
                $scope.projects = data.data;
            });
    }

    /**
     * add 添加账号（电信侧管理员），添加成功后返回至电信侧账号管理列表页面：account/telecomList
     * @param $scope 获取列表时要提交的参数为：userName、roleId、provinceId、cityId、areaId、contactPerson、contactWay、remark
     * @author 沈亚芳
     * @date 2015/11/30
     */
    dao.add = function ($scope) {
        $('#accountAdd').attr('disabled',true);
        var userName = defaultString($scope.account.userName);
        //后端需要的值名是userType，其实就是roleId的值
        var roleId = $scope.account.roleId;
        var provinceId = $scope.account.provinceId;
        var projectId = $scope.account.projectId;
        var cityId = $scope.account.cityId;
        var areaId = $scope.account.areaId;
        var contactPerson = defaultString($scope.account.contactPerson);
        var contactWay = defaultString($scope.account.contactWay);
        var remark = defaultString($scope.account.remark);
        
        //如果是汉字,用encodeURIComponent转码
        contactPerson = encodeURIComponent(contactPerson);
        remark = encodeURIComponent(remark);
        //isBlank undefined的处理
        if(isBlank(roleId)){
            roleId = '';
        }
        if(isBlank(provinceId)){
            provinceId='';
        }
        if(isBlank(cityId)){
            cityId = '';
        }
        if(isBlank(areaId)){
            areaId = '';
        }
        if(isBlank(remark)){
            remark = '';
        }
        //当创建集团管理员账号时，省市区全部置空
        if (roleId == '6'){
            provinceId = '';
            cityId = '';
            areaId = '';
        }
        var url = '/account/add?userName=' + userName + '&userType=' + roleId + '&fkProjectId=' + projectId + '&provinceId=' + provinceId + '&cityId=' + cityId + '&areaId=' + areaId + '&contactPerson=' + contactPerson + '&contactWay=' + contactWay + '&remark=' + remark;
        $http.get(url)
            .success(function(data, status, headers, config){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    $('#accountAdd').removeAttr('disabled');
                    return;
                }
                var defaultPassword = data.defaultPassword;
                jDialog.alert('添加账号（电信侧）:', '添加成功，账号是：' + userName + '，密码是：' + defaultPassword);
                $location.path('account/telecomList');
            });
    };


    /**
     * getAccountById 根据id来获取要编辑的账号的信息
     * @param $scope
     * @author 沈亚芳
     * @date 2015/11/30
     */
    dao.getAccountById = function($scope, id){
        var url = '/account/show?id=' + id;
        $http.get(url)
            .success(function(data, status, headers, config){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    return;
                }
                $scope.account = data.data;
            });
    };


    //编辑账号（电信侧）
    /**
     * edit 编辑账号信息（电信侧管理员），编辑成功后返回至电信侧账号列表页面：account/telecomList
     * @param $scope 编辑时要提交的参数为：id、contactPerson、contactWay、remark
     * @author 沈亚芳
     * @date 2015/11/30
     */
    dao.edit = function($scope){
        $('#accountEdit').attr('disabled',true);
        var id = $scope.account.id;
        var contactPerson = defaultString($scope.account.contactPerson);
        var contactWay = defaultString($scope.account.contactWay);
        var remark = defaultString($scope.account.remark);
        var projectId = $scope.account.projectId;
        if(isBlank(remark)){
            remark = '';
        }
        var url = '/account/edit?id=' + id + '&projectId=' + projectId + '&contactPerson=' + contactPerson + '&contactWay=' + contactWay + '&remark=' + remark;
        $http.get(url)
            .success(function(data, status, headers, config){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    $('#accountEdit').removeAttr('disabled');
                    return;
                }
                jDialog.alert('编辑账号（电信侧）:', '账号信息编辑成功');
                $location.path('account/telecomList');
            });
    };


    /**
     * getPermissionById 根据账号ID查找它已有的权限，用于已有权限的显示
     * @param $scope
     * @param id
     * @author 沈亚芳
     * @date 2015/12/01
     */
    dao.getPermissionById = function ($scope, id){
        var url = 'account/showpermission?id=' + id;
        $http.get(url)
            .success(function(data, status, headers, config){
                if (data.result == 'FAIL') {
                    jDialog.alert('提示: ' , data.message);
                    return;
                }
                $scope.rightpermissions=data.data;
            });
    };


    /**
     * getPermission 根据当前登录账号来查找权限-左侧可选权限的数据
     * @param $scope
     * @author 沈亚芳
     * @date 2015/12/01
     */
    dao.getPermission = function($scope){
        var url = 'account/getpermissionlist';
        $http.get(url)
            .success(function(data, status, headers, config){
                if (data.result == 'FAIL') {
                    jDialog.alert('提示: ' , data.message);
                    return;
                }
                $scope.permissions = data.data;
                //已经被分配的权限的操作
                dao.getExistPermission($scope);
            });
    };


    /**
     * getExistPermission 根据id从数据中获取已分配的权限，用于左侧那些已经被选中的权限的显示样式的变更
     * @param $scope
     * @author 沈亚芳
     * @date 2015/12/01
     */
    dao.getExistPermission = function ($scope){
        loadingAppend();
        var id = $scope.id;
        var url = 'account/showpermission?id=' + id;
        $http.get(url)
            .success(function(data, status, headers, config){
                loadingRemove();
                if (data.result == 'FAIL') {
                    jDialog.alert('提示: ' , data.message);
                    return;
                }
                //已经被分配的权限数据
                var existPermission = data.data;
                var _PSS_ = existPermission.split(',');
                //若没有分配的权限数据，则置空，注意是[]，而不是''
                if(isBlank(existPermission)){
                    _PSS_ = [];
                }
                //将_PSS_放入$scope
                $scope._PSS_ = _PSS_;
                var $pss = $('.jsd-origin');
                for (var i = 0; i < _PSS_.length; i++) {
                    var $el = $pss.find('[mid=' + _PSS_[i] + ']');
                    $el.addClass('gave');
                }
                $('.jsd-gave').empty().html($pss.html());
                bindEvent();
            });
    };


    /**
     * bindEvent 权限操作前端事件bindEvent
     * @author 沈亚芳
     * @date 2015/12/01
     */
    function bindEvent (){
        // 添加权限(从左到右)
        $('.jsd-origin').on('click', '.leaf:not(.gave)', function (e) {
            var $el = $(this);
            $el.addClass('gave');
            if ($el.hasClass('limb')) {
                $el.next().find('.leaf').addClass('gave');
            }
            var $pel = $el.parent();
            //if ($pel.is('.leafs')) { // 二级菜单
            //	$pel.prev().addClass('gave');
            //	var $pel3 = $pel.parent();
            //	if ($pel3.parent().is('.leafs')) { // 三级菜单
            //		$pel3.parent().prev().addClass('gave');
            //	}
            //}
            var pleaf;
            if ($pel.is('.leafs')) {
                pleaf = $pel.prev('.leaf');
                pleaf.addClass('gave');
                if (pleaf.parents('.leafs')) {
                    pleaf.parents('.leafs').prev().addClass('gave');
                }
            } else if ($pel.is('.nothingleaf')) {
                pleaf = $pel.parents('.leafs');
                pleaf.prev().addClass('gave');
            }

            $('.jsd-gave').empty().html($('.jsd-origin').html());
        });

        // 删除权限（从右到左）
        $('.jsd-gave').on('click', '.gave', function (e) {
            var $el = $(this);
            $el.removeClass('gave');
            if ($el.hasClass('limb')) {
                $el.next().find('.leaf').removeClass('gave');
            }
            var $pel = $el.parent();
            if ($pel.parent().is('.leafs') && $pel.parent().find('.gave').length === 0) { // 二级菜单
                $pel.parent().prev().removeClass('gave');
                // 一级菜单
                if ($pel.parent().is('.leafs') && $pel.parent().find('.gave').length === 0) {
                    $pel.parent().prev().removeClass('gave');
                }
            }
            $('.jsd-origin').empty().html($('.jsd-gave').html());
        });
    }


    /**
     * permissionAssign 分配权限的保存（电信侧和客户侧）（管理员账号），保存成功后，根据角色的类型进行跳转至对应的页面
     * @param $scope 保存权限分配时要提交的参数是：id、permissionIds
     * @author 沈亚芳
     * @date 2015/12/01
     */
    dao.permissionAssign = function($scope){
        loadingAppend();
        $('#submit').attr('disabled',true);
        var pss = [];
        var $pss = $('.jsd-gave .leaf.gave');
        for (var i = 0; i < $pss.length; i++) {
            pss.push($pss.eq(i).attr('mid'));
        }
        var pssstr = '|' + pss.join('|') + '|';
        var flag = false;
        for (var j = 0; j < $scope._PSS_.length; j++) {
            if (pssstr.indexOf('|' + $scope._PSS_[j] + '|') < 0) {
                flag = true;
                break;
            }
        }
        //给那种类型的角色账号分配
        var userType = $scope.account.userType;
        /*提交时用的格式assignstr*/
        var permissionIds =pss.join(',');
        var id=$scope.account.id;
        /*url单引号中的地址由后端提供*/
        var url = 'account/assign?id=' + id + '&permissionIds=' + permissionIds;
        $http.get(url)
            .success(function (data, status, headers, config) {
                loadingRemove();
                if (data.result == 'FAIL') {
                    jDialog.alert('提示: ' , data.message);
                    $('#submit').removeAttr('disabled');
                    return;
                }
                jDialog.alert("分配权限", "该账号权限分配成功");
                if (userType == 2 || userType == 3 || userType == 4 || userType == 6){
                    $location.path('account/telecomList');
                } else if (userType == 1) {
                    $location.path('account/adminList');
                } else {
                    $location.path('account/customerList');
                }

            })
            .error(function (data, status, headers, config) {
            });
    };


    /**
     * adminList 管理员账号列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/06/24
     */
    dao.adminList = function($scope){
        var pageNo = pageDao.getPageNo();
        var keywords = defaultString($scope.keywords);
        var url = 'accountadmin/list?pageNo=' + pageNo + '&keywords=' + keywords;
        $http.get(url)
            .success(function(data){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    return;
                }
                accountadmins = data.records;
                //数据记录集
                $scope.accountadmins = data.records;
                //开始记录数
                $scope.begin = data.begin;
                $scope.data = data;
                //初始化分页栏
                pageDao.init($scope, data);
            })
    };


    /**
     * adminAdd 创建管理员账号
     * @param $scope
     * @author 沈亚芳
     * @date 2016/06/24
     */
    dao.adminAdd = function($scope){
        $('#adminAdd').attr('disabled',true);
        var userName = encodeURIComponent(defaultString($scope.account.userName));
        var deptName = encodeURIComponent(defaultString($scope.account.deptName));
        var contactPerson = encodeURIComponent(defaultString($scope.account.contactPerson));
        var contactWay = encodeURIComponent(defaultString($scope.account.contactWay));
        var remark = encodeURIComponent(defaultString($scope.account.remark));

        var url = '/accountadmin/add?userName=' + userName + '&deptName=' + deptName + '&contactPerson=' + contactPerson + '&contactWay=' + contactWay + '&remark=' + remark;

        $http.get(url)
            .success(function(data){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    $('#adminAdd').removeAttr('disabled');
                    return;
                }
                var defaultPassword = data.defaultPassword;
                jDialog.alert('创建账号:', '创建成功，账号是：' + userName + '，密码是：' + defaultPassword);
                $location.path('account/adminList');
            })
    };


    /**
     * getAccountAdminById 根据id来获取要编辑的管理员账号的信息
     * @param $scope,id
     * @author 沈亚芳
     * @date 2016/06/27
     */
    dao.getAccountAdminById = function($scope, id){
        var url = '/accountadmin/show?id=' + id;
        $http.get(url)
            .success(function(data, status, headers, config){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    return;
                }
                $scope.account = data.data;
            });
    };


    /**
     * adminEdit 编辑管理员账号的信息
     * @param $scope
     * @author 沈亚芳
     * @date 2016/06/27
     */
    dao.adminEdit = function($scope){
        $('#adminEdit').attr('disabled',true);
        var id = $scope.account.id;
        var deptName = encodeURIComponent(defaultString($scope.account.deptName));
        var contactPerson = encodeURIComponent(defaultString($scope.account.contactPerson));
        var contactWay = encodeURIComponent(defaultString($scope.account.contactWay));
        var remark = encodeURIComponent(defaultString($scope.account.remark));

        var url = '/accountadmin/edit?id=' + id + '&deptName=' + deptName + '&contactPerson=' + contactPerson + '&contactWay=' + contactWay + '&remark=' + remark;

        $http.get(url)
            .success(function(data, status, headers, config){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示:', data.message);
                    $('#adminEdit').removeAttr('disabled');
                    return;
                }
                jDialog.alert('编辑管理员账号:', '账号信息编辑成功');
                $location.path('account/adminList');
            });
    };


    /**
     * 返回 dao
     * @author 沈亚芳
     * @date 2015/12/01
     */
    return dao;
});