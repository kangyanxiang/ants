/**
 * Created by Shin on 2016/03/01.
 * 项目列表-该项目下的客户信息 projectCustomerListController
 */
indexApp.controller('projectCustomerListController', function($scope, $location, projectService, customerService) {

    /**
     * 放置init方法外获取projectId
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/07
     */
    var projectId = $location.search()['projectId'];
    $scope.projectId = projectId;


    /**
     * projectCustomerList 项目下的一级客户列表
     * @param $scope
     * @author 沈亚芳
     * @date 2016/03/01
     */
    function list (){
        projectService.projectCustomerList($scope);
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