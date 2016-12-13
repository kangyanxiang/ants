/**
 * Created by Shin on 2016/04/21.
 * 换肤配置-皮肤列表-添加 themeAddController
 */
indexApp.controller('themeAddController', function($scope, themeService) {

    /**
     * validateFail 检验添加皮肤功能所涉及到的参数是否验证通过
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
        //校验缩略图
        if(themeService.validateThumbPicForThemeFail($scope)){
            return true;
        }
        //校验皮肤压缩包
        if(themeService.validateThemeZipForThemeFail($scope)){
            return true;
        }
        return false;
    }


    /**
     * themeAdd 皮肤列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/04/25
     */
    function themeAdd (){
        if(validateFail($scope)){
            return;
        }
        themeService.themeAdd($scope);
    }
    $scope.themeAdd = themeAdd;


    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2016/04/22
     */
    function init (){
        $scope.theme = {};

        //初始化提示
        themeService.initTipsForThemeChange($scope);
    }
    init();
});