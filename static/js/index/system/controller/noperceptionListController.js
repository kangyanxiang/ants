/**
 * Created by zhuxuehuang on 16-4-27.
 */

indexApp.controller('noperceptionListController', function($scope, customerService, noperceptionService) {
    // 列表搜索
    function list() {
        noperceptionService.noperceptionList($scope);
    }
    $scope.list = list;

    // 初始化
    function init() {
        // 客户列表select2
        customerService.customerSelect2();
        resetWidth();
        list();
    }
    init();

    // 复选框全选和全不选
    $scope.selectAll = function () {
        var checkboxAll = $(':checkbox[name="checkboxAll"]');
        var checkboxItems = $(':checkbox[name="checkboxItem"]');
        if (checkboxAll.prop('checked')) {
            checkboxItems.prop('checked', true);
        } else {
            checkboxItems.prop('checked', false);
        }
    };

    // 获取选中的复选框值
    function getCheckboxVaules() {
        var checkboxValues = [];
        var checkboxItems = $(':checkbox[name="checkboxItem"]');
        checkboxItems.each(function () {
            var that = $(this);
            if (that.prop('checked')) {
                checkboxValues.push($(this).val());
            }
        });
        return checkboxValues;
    }

    // 关闭无感知
    $scope.noperceptionClose = function () {
        var checkboxValues = getCheckboxVaules();
        if (checkboxValues.length == 0) {
            jDialog.alert('关闭无感知','您未选择要关闭无感知的数据！请选择');
            return ;
        }
        noperceptionService.noperceptionClose($scope, checkboxValues.join(','));
    };

    // 开启无感知
    $scope.noperceptionOpen = function () {
        var checkboxValues = getCheckboxVaules();
        if (checkboxValues.length == 0) {
            jDialog.alert('开启无感知','您未选择要开启无感知的数据！请选择');
            return ;
        }
        noperceptionService.noperceptionOpen($scope, checkboxValues.join(','));
    };

});