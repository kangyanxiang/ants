/**
 * Created by Shin on 2016/04/21
 * 换肤配置 themeService
 */
indexApp.factory('themeService',function($rootScope,themeDao) {

    /**
     * service 定义个全局service，并将其置空
     * @param 空
     * @author 沈亚芳
     * @date 2016/04/21
     */
    var service = {};


    /**
     * validateCustomerIdAndLevelForThemeStrategyFail 校验客户名称
     * @param $scope 校验的参数：customerIdAndLevel
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/04/22
     */
    service.validateCustomerIdAndLevelForThemeStrategyFail = function($scope) {
        var $customerIdAndLevel = $('#customerIdAndLevel');
        var customerIdAndLevel = $scope.themeStrategy.customerIdAndLevel;
        //校验是否为空
        if(isBlank(customerIdAndLevel)){
            updateShowTipos($customerIdAndLevel, '请选择一个客户！');
            return true;
        }
        return false;
    };


    /**
     * validateThemeIdForThemeStrategyFail 校验皮肤名称
     * @param $scope 校验的参数：themeId
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/04/22
     */
    service.validateThemeIdForThemeStrategyFail = function($scope){
        var $themeId = $('#themeId');
        var themeId = $scope.themeStrategy.themeId;
        if(isBlank(themeId)){
            updateShowTipos($themeId, '请选择一个皮肤');
            return true;
        }
        return false;
    };


    /**
     * validateThemeTypeForThemeStrategyFail 校验类别
     * @param $scope 校验的参数：type
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/04/22
     */
    service.validateThemeTypeForThemeStrategyFail = function($scope){
        var $themeType = $('#themeType');
        var themeType = $themeType.val();
        if(isBlank(themeType)){
            updateShowTipos($themeType, '请选择一个类别');
            return true;
        }
        return false;
    };


    /**
     * validateContentForThemeStrategyFail 校验类别
     * @param $scope 校验的参数：content
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/04/22
     */
    service.validateContentForThemeStrategyFail = function($scope){
        var themeType = $('#themeType').val();
        var $content = $('#content');
        var content = $scope.themeStrategy.content;
        if(isBlank(content)){
            updateShowTipos($content, '内容不能为空');
            return true;
        }
        if (themeType == '1'){
            if(!chkString(content, defrules.domain)){
                updateShowTipos($content, '当前类别为域名，请输入符合域名要求的内容');
                return true;
            }
        } else if (themeType == '2'){
            if(!chkString(content, defrules.typeCode)){
                updateShowTipos($content, '当前类别为编码，请输入1-20位字母、数字');
                return true;
            }
        }
        return false;
    };


    /**
     * initTipsForThemeStrategyChange 各校验字段相关信息提示的初始化（新增/编辑皮肤策略）
     * @author 沈亚芳
     * @date 2016/04/22
     */
    service.initTipsForThemeStrategyChange = function(){
        var $customerIdAndLevel = $('#customerIdAndLevel');
        var $themeId = $('#themeId');
        var $themeType = $('#themeType');
        var $content = $('#content');
        initTipsArray([$customerIdAndLevel,$themeId,$themeType,$content]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$customerIdAndLevel,$themeId,$themeType,$content]);
        });
    };


    /**
     * validateNameForThemeFail 校验皮肤名称
     * @param $scope 校验的参数：name
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/04/25
     */
    service.validateNameForThemeFail = function($scope){
        var $name = $('#name');
        var name = $scope.theme.name;
        if(isBlank(name)){
            updateShowTipos($name, '皮肤名称不能为空');
            return true;
        }
        return false;
    };


    /**
     * validateVersionForThemeFail 校验版本号
     * @param $scope 校验的参数：version
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/04/25
     */
    service.validateVersionForThemeFail = function($scope){
        var $version = $('#version');
        var version = $scope.theme.version;
        if(isBlank(version)){
            updateShowTipos($version,'版本号不能为空');
            return true;
        }
        return false;
    };


    /**
     * validateThumbPicForThemeFail 校验缩略图-新增皮肤
     * @param $scope 校验的参数：thumbPic
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/04/25
     */
    service.validateThumbPicForThemeFail = function($scope){
        var $thumbPic = $('#thumbPic');
        var thumbPic = $thumbPic.val();
        if(isBlank(thumbPic)){
            updateShowTipos($thumbPic,'缩略图不能为空');
            return true;
        }
        var isPic = thumbPic.indexOf('.png') != -1 || thumbPic.indexOf('.jpg') != -1;
        if(!isPic){
            updateShowTipos($thumbPic,'缩略图格式不正确，格式要求：png或jpg图片');
            return true;
        }
        return false;
    };


    /**
     * validateThumbPicForThemeOfEditFail 校验缩略图-编辑皮肤
     * @param $scope 校验的参数：thumbPic
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/04/25
     */
    service.validateThumbPicForThemeOfEditFail = function($scope){
        var $thumbPic = $('#thumbPic');
        var thumbPic = $thumbPic.val();
        var isPic = thumbPic.indexOf('.png') != -1 || thumbPic.indexOf('.jpg') != -1;
        if (isNotBlank(thumbPic) && !isPic) {
            updateShowTipos($thumbPic, '缩略图格式不正确，格式要求：png或jpg图片');
            return true;
        }
        return false;
    };


    /**
     * validateThemeZipForThemeFail 校验皮肤压缩包-新增皮肤
     * @param $scope 校验的参数：themeZip
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/04/25
     */
    service.validateThemeZipForThemeFail = function($scope){
        var $themeZip = $('#themeZip');
        var themeZip = $themeZip.val();
        if(isBlank(themeZip)){
            updateShowTipos($themeZip,'皮肤压缩包不能为空');
            return true;
        }
        if(themeZip.indexOf('.zip') == -1){
            updateShowTipos($themeZip,'皮肤压缩包不正确，格式要求：zip');
            return true;
        }
        return false;
    };


    /**
     * validateThemeZipForThemeOfEditFail 校验皮肤压缩包-编辑皮肤
     * @param $scope 校验的参数：themeZip
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/04/25
     */
    service.validateThemeZipForThemeOfEditFail = function ($scope){
        var $themeZip = $('#themeZip');
        var themeZip = $themeZip.val();
        if(isNotBlank(themeZip) && themeZip.indexOf('.zip') == -1){
            updateShowTipos($themeZip, '组件压缩包格式不正确，格式要求：zip！');
            return true;
        }
        return false;
    };


    /**
     * initTipsForThemeChange 各校验字段相关信息提示的初始化（新增/编辑皮肤）
     * @author 沈亚芳
     * @date 2016/04/22
     */
    service.initTipsForThemeChange = function(){
        var $name = $('#name');
        var $version = $('#version');
        var $thumbPic = $('#thumbPic');
        var $themeZip = $('#themeZip');
        initTipsArray([$name,$version,$thumbPic,$themeZip]);
        //页面切换前去除所有提示的Tips
        $rootScope.$on('$locationChangeStart', function(){
            destroyTipsArray([$name,$version,$thumbPic,$themeZip]);
        });
    };


    /**
     * themeSelectList 皮肤信息（下拉）
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/22
     */
    service.themeSelectList = function($scope){
        themeDao.themeSelectList($scope);
    };


    /**
     * themeStrategyList 皮肤策略列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/21
     */
    service.themeStrategyList = function($scope){
        themeDao.themeStrategyList($scope);
    };


    /**
     * deleteStrategy 删除皮肤策略
     * @param $scope
     * @param id  删除要提交的参数为：id
     * @author 沈亚芳
     * @date 2016/04/21
     */
    service.deleteStrategy = function($scope, id){
        themeDao.deleteStrategy($scope, id);
    };


    /**
     * themeStrategyAdd 添加皮肤策略
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/22
     */
    service.themeStrategyAdd = function($scope){
        themeDao.themeStrategyAdd($scope);
    };


    /**
     * themeStrategyById 根据皮肤策略ID查询该皮肤策略的相关信息
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/25
     */
    service.themeStrategyById = function($scope, id){
        themeDao.themeStrategyById($scope, id);
    };


    /**
     * themeStrategyEdit 编辑皮肤策略
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/25
     */
    service.themeStrategyEdit = function($scope){
        themeDao.themeStrategyEdit($scope);
    };


    /**
     * themeList 皮肤列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/21
     */
    service.themeList = function($scope){
        themeDao.themeList($scope);
    };


    /**
     * deleteTheme 删除皮肤
     * @param $scope
     * @param id  删除要提交的参数为：id
     * @author 沈亚芳
     * @date 2016/04/21
     */
    service.deleteTheme = function($scope, id){
        themeDao.deleteTheme($scope, id);
    };


    /**
     * themeAdd 皮肤列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/25
     */
    service.themeAdd = function($scope){
        themeDao.themeAdd($scope);
    };


    /**
     * themeById 根据皮肤ID查询该皮肤的相关信息
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/25
     */
    service.themeById = function($scope, id){
        themeDao.themeById($scope, id);
    };


    /**
     * themeEdit 编辑皮肤
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/25
     */
    service.themeEdit = function($scope){
        themeDao.themeEdit($scope);
    };


    /**
     * 返回 service
     * @author 沈亚芳
     * @date 2016/04/21
     */
    return service;
});