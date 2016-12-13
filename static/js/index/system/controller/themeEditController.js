/**
 * Created by Shin on 2016/04/21.
 * 换肤配置-皮肤列表-编辑 themeEditController
 */
indexApp.controller('themeEditController', function($scope, $location, themeService) {

    /**
     * validateFail 检验编辑皮肤功能所涉及到的参数是否验证通过
     * @param $scope
     * @returns {boolean}
     * @author 沈亚芳
     * @date 2016/04/25
     */
    function validateFail($scope){
        //校验皮肤名称
        if(themeService.validateNameForThemeFail($scope)){
            return true;
        }
        //校验版本号
        if(themeService.validateVersionForThemeFail($scope)){
            return true;
        }
        //校验皮肤压缩包
        if(themeService.validateThemeZipForThemeOfEditFail($scope)){
            return true;
        }
        //校验缩略图
        if(themeService.validateThumbPicForThemeOfEditFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * themeEdit 编辑皮肤
     * @author 沈亚芳
     * @date 2016/04/25
     */
    function themeEdit (){
        if(validateFail($scope)){
            return;
        }
        themeService.themeEdit($scope);
    }
    $scope.themeEdit = themeEdit;


    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2016/04/25
     */
    function init (){
        var id = $location.search()['id'];

        //根据id查询对应皮肤的信息
        themeService.themeById($scope, id);

        //初始化提示
        themeService.initTipsForThemeChange($scope);
    }
    init();

});