/**
 * Created by Shin on 2016/04/21.
 * 换肤配置 themeDao
 */
indexApp.factory('themeDao', function($http, $location, pageDao) {

    /**
     * service 定义个全局dao，并将其置空
     * @param 空
     * @author 沈亚芳
     * @date 2016/04/21
     */
    var dao = {};


    /**
     * themeSelectList 皮肤信息（下拉）
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/22
     */
    dao.themeSelectList = function($scope){
        var url = '/theme/findbyname';
        $http.get(url)
            .success(function(data){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                $scope.themes = data.records;
            })
    };


    /**
     * themeStrategyList 皮肤策略列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/21
     */
    dao.themeStrategyList = function($scope){
        loadingAppend();
        var pageNo = pageDao.getPageNo();

        var customerIdAndLevel = defaultString($scope.customerIdAndLevel);
        //以#拆分customerIdAndLevel
        var customerIdAndLevelArray = customerIdAndLevel.split('#');
        var customerId = customerIdAndLevelArray[0];
        var cascadeLabel = customerIdAndLevelArray[1];

        var themeId = defaultString($scope.themeId);
        var themeType = defaultString($scope.themeType);
        var content = defaultString($scope.content);

        if(isBlank(customerId)){
            customerId = '';
        }
        if(isBlank(cascadeLabel)){
            cascadeLabel = '';
        }
        if(isBlank(themeId)){
            themeId = '';
        }
        if(isBlank(themeType)){
            themeType = '';
        }

        //如果是汉字,用encodeURIComponent转码
        content = encodeURIComponent(content);

        var url ='/themestrategy/list?pageNo=' + pageNo + '&customerId=' + customerId + '&cascadeLabel=' + cascadeLabel + '&themeId=' + themeId + '&themeType=' + themeType + '&content=' + content;

        $http.get(url)
            .success(function(data){
                loadingRemove();
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                //数据记录集
                $scope.themeStrategys = data.records;
                //开始记录数
                $scope.begin = data.begin;
                $scope.data = data;
                //初始化分页栏
                pageDao.init($scope, data);
            })
    };


    /**
     * deleteStrategy 删除皮肤策略
     * @param $scope
     * @param id  删除要提交的参数为：id
     * @author 沈亚芳
     * @date 2016/04/21
     */
    dao.deleteStrategy = function($scope, id){
        var url ='/themestrategy/delete?id=' + id;

        $http.get(url)
            .success(function(data) {
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                jDialog.alert('皮肤策略删除：', '删除成功！');
                $scope.list();
            });
    };


    /**
     * themeStrategyAdd 添加皮肤策略
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/22
     */
    dao.themeStrategyAdd = function($scope){
        $('#themeStrategyAdd').attr('disabled',true);
        var customerIdAndLevel = defaultString($scope.themeStrategy.customerIdAndLevel);
        var customerIdAndLevelArray = customerIdAndLevel.split('#');
        var customerId = customerIdAndLevelArray[0];
        var cascadeLabel = customerIdAndLevelArray[1];

        var themeId = $scope.themeStrategy.themeId;
        var themeType = $scope.themeStrategy.themeType;
        var content = defaultString($scope.themeStrategy.content);
        var remark = defaultString($scope.themeStrategy.remark);

        if(isBlank(themeId)){
            themeId = '';
        }
        if(isBlank(themeType)){
            themeType = '';
        }

        content = encodeURIComponent(content);
        remark = encodeURIComponent(remark);

        var url = '/themestrategy/add?customerId=' + customerId + '&cascadeLabel=' + cascadeLabel + '&themeId=' + themeId + '&themeType=' + themeType + '&content=' + content + '&remark=' + remark;

        $http.post(url)
            .success(function(data){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    $('#themeStrategyAdd').removeAttr('disabled');
                    return;
                }
                jDialog.alert('新增皮肤策略:', '保存成功');
                $location.path('themeStrategy/list');
            })
    };


    /**
     * themeStrategyById 根据皮肤策略ID查询该皮肤策略的相关信息
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/25
     */
    dao.themeStrategyById = function($scope, id){
        var url ='/themestrategy/show?id=' + id;

        $http.get(url)
            .success(function(data){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                $scope.themeStrategy = data.data;
                //临时设置为空
                //$scope.themeStrategy = {};

                var themeType = $scope.themeStrategy.themeType;

                if (themeType == '1'){
                    $('#themeType1').attr('selected', true);
                } else if (themeType == '2') {
                    $('#themeType2').attr('selected', true);
                }

            })
    };


    /**
     * themeStrategyEdit 编辑皮肤策略
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/25
     */
    dao.themeStrategyEdit = function($scope){
        $('#themeStrategyEdit').attr('disabled',true);
        //var customerIdAndLevel = defaultString($scope.themeStrategy.customerIdAndLevel);
        //var customerIdAndLevelArray = customerIdAndLevel.split('#');
        //var customerId = customerIdAndLevelArray[0];
        //var cascadeLabel = customerIdAndLevelArray[1];

        var customerId = $scope.themeStrategy.customerId;
        var cascadeLabel = $scope.themeStrategy.cascadeLabel;

        var id = $scope.themeStrategy.id;
        var themeId = $scope.themeStrategy.themeId;
        var themeType = $('#themeType').val();
        var content = defaultString($scope.themeStrategy.content);
        var remark = defaultString($scope.themeStrategy.remark);

        if(isBlank(themeId)){
            themeId = '';
        }
        if(isBlank(themeType)){
            themeType = '';
        }

        content = encodeURIComponent(content);
        remark = encodeURIComponent(remark);

        var url = '/themestrategy/edit?id='+ id +'&customerId=' + customerId + '&cascadeLabel=' + cascadeLabel + '&themeId=' + themeId + '&themeType=' + themeType + '&content=' + content + '&remark=' + remark;

        $http.post(url)
            .success(function(data){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    $('#themeStrategyEdit').removeAttr('disabled');
                    return;
                }
                jDialog.alert('编辑皮肤策略:', '保存成功');
                $location.path('themeStrategy/list');
            })
    };


    /**
     * themeList 皮肤列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/21
     */
    dao.themeList = function($scope){
        loadingAppend();
        var pageNo = pageDao.getPageNo();

        var name = defaultString($scope.name);

        //如果是汉字,用encodeURIComponent转码
        name = encodeURIComponent(name);

        var url = '/theme/list?pageNo=' + pageNo + '&name=' + name;

        $http.get(url)
            .success(function(data){
                loadingRemove();
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                //数据记录集
                $scope.themes = data.records;
                //开始记录数
                $scope.begin = data.begin;
                $scope.data = data;
                //初始化分页栏
                pageDao.init($scope, data);
            })
    };


    /**
     * deleteTheme 删除皮肤
     * @param $scope
     * @param id  删除要提交的参数为：id
     * @author 沈亚芳
     * @date 2016/04/21
     */
    dao.deleteTheme = function($scope, id){
        var url ='/theme/delete?id=' + id;

        $http.get(url)
            .success(function(data) {
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                jDialog.alert('皮肤删除：', '删除成功！');
                $scope.list();
            });
    };


    /**
     * themeAdd 新增皮肤
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/25
     */
    dao.themeAdd = function($scope){
        $('#themeAdd').attr('disabled',true);

        var name = defaultString($scope.theme.name);
        var version = defaultString($scope.theme.version);
        var remark = defaultString($scope.theme.remark);


        name = encodeURIComponent(name);
        version = encodeURIComponent(version);
        remark = encodeURIComponent(remark);

        var url = '/theme/add?name=' + name + '&version=' + version + '&remark=' + remark;

        var options = {
            url: url,
            type: 'post',
            dataType: 'json',
            clearForm: false,
            resetForm: false,
            beforeSubmit: function(){
                return true;
            },
            success: function(data){
                if(data.result == 'FAIL'){
                    $('#themeAdd').attr('disabled',false);
                    jDialog.alert('提示:', data.message);
                    return;
                }
                jDialog.alert('新增皮肤:', '保存成功');
                $location.path('/theme/list');
                $scope.$apply();
            }
        };
        $('#themeAddForm').ajaxSubmit(options);
    };


    /**
     * themeById 根据皮肤ID查询该皮肤的相关信息
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/25
     */
    dao.themeById = function($scope, id){
        var url ='/theme/show?id=' + id;

        $http.get(url)
            .success(function(data){
                if(data.result == 'FAIL'){
                    jDialog.alert('提示：', data.message);
                    return;
                }
                $scope.theme = data.data;
            })
    };


    /**
     * themeEdit 编辑皮肤
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/25
     */
    dao.themeEdit = function($scope){
        $('#themeEdit').attr('disabled',true);

        var name = defaultString($scope.theme.name);
        var version = defaultString($scope.theme.version);
        var remark = defaultString($scope.theme.remark);


        name = encodeURIComponent(name);
        version = encodeURIComponent(version);
        remark = encodeURIComponent(remark);

        var url = '/theme/edit?name=' + name + '&version=' + version + '&remark=' + remark;

        var options = {
            url: url,
            type: 'post',
            dataType: 'json',
            clearForm: false,
            resetForm: false,
            beforeSubmit: function(){
                return true;
            },
            success: function(data){
                if(data.result == 'FAIL'){
                    $('#themeEdit').attr('disabled',false);
                    jDialog.alert('提示:', data.message);
                    return;
                }
                jDialog.alert('编辑皮肤:', '保存成功');
                $location.path('/theme/list');
                $scope.$apply();
            }
        };
        $('#themeEditForm').ajaxSubmit(options);
    };


    /**
     * 返回 dao
     * @author 沈亚芳
     * @date 2016/04/21
     */
    return dao;
});