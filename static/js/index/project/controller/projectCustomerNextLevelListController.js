/**
 * Created by Shin on 2016/03/01.
 * 项目列表-该项目下的二级单位及以下客户信息 projectCustomerNextLevelListController
 */
indexApp.controller('projectCustomerNextLevelListController', function($scope, $location, projectService, customerService) {

    //function backClick(){
    //    var projectId = $location.search()['projectId'];
    //    console.log(projectId);
    //    var projectName = $location.search()['projectName'];
    //    console.log(projectName);
    //    var backHref = '#/project/projectCustomerList' + '?id=' + projectId + '&projectName=' + projectName;
    //    console.log(backHref);
    //    //$location.path(backHref);
    //    //window.location.replace(backHref);
    //}
    //$scope.backClick = backClick;

    /**
     * 放置init方法外获取projectId
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/07
     */
    var projectId = $location.search()['projectId'];
    $scope.projectId = projectId;

    var customerId = $location.search()['customerId'];
    $scope.customerId = customerId;

    var cascadeLevel = $location.search()['cascadeLevel'];
    $scope.cascadeLevel = cascadeLevel;


    /**
     * projectCustomerNextList 项目下的二级以下客户列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/07
     */
    function list (){
        projectService.projectCustomerNextList($scope);
    }
    $scope.list = list;
    list();


    /**
     * init 初始化函数
     * @author 沈亚芳
     * @date 2016/03/01
     */
    function init(){

        var projectName = $location.search()['projectName'];
        $scope.projectName = projectName;

        var linkCustomerName = $location.search()['customerName'];
        $scope.linkCustomerName = linkCustomerName;

        var customerId = $location.search()['customerId'];
        $scope.customerId = customerId;

    }
    init();


    /**
     * newWidth 因fixed，右侧有返回按钮，因此重置宽度
     * @author 沈亚芳
     * @date 2016/03/01
     */
    function newWidth (){
        var width = document.body.clientWidth;
        $("#reset").css('width', width-225);
    }
    newWidth ();


    /**
     * resetWidth 重置列表因fixed引起的ListTitle宽度问题
     * @author 沈亚芳
     * @date 2016/03/01
     */
    resetWidth();

    /**
     * 客户详情弹出层
     * @param customerId
     */
    $scope.customerDetails = function (customerId) {
        customerService.customerDetails($scope, customerId);
    }
});