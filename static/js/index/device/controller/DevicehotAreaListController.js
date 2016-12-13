/**
 * Created by zhuxuehuang on 16-6-8.
 */


indexApp.controller('hotAreaListController', function ($scope, $location, customerService, deviceService, locationService, hotAreaService, ngDialog) {
    // 热点列表(分页)
    function list(){
        hotAreaService.hotAreaList($scope);
    }
    $scope.list = list;
    list();

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
            //获取当前值，如果拿到的值为空，则return
            var mac = $(this).val();
            if (mac == ''){
                return;
            }
            if (that.prop('checked')) {
                checkboxValues.push($(this).val());
            }
        });
        return checkboxValues;
    }

    //根据省id获取市数据集合
    $scope.cityChange = function ($event) {
        var provinceId = $scope.provinceId;//省id
        locationService.cityList($scope, provinceId);//获取市数据集合
    };

    //根据市id获取区/县数据集合
    $scope.countyChange = function ($event) {
        var cityId = $scope.cityId;//市id
        locationService.countyList($scope, cityId);//获取区/县数据集合
    };

    // 初始化
    function init(){
        $scope.device = {};

        //获取省数据集合
        locationService.provinceList($scope);

        //获取当前客户集合
        customerService.customerSelect2('customerIdAndLevel');

        // 重置标题宽度
        resetWidth();
    }
    init();

    /**
     * 删除所选热点信息
     */
    $scope.deleteHotArea = function () {
        var ids = getCheckboxVaules();

        // 判断是否已选择热点
        if (ids.length <= 0) {
            jDialog.alert('删除热点', '您未选择要删除的热点数据！请选择');
            return ;
        }

        jDialog.confirm('删除热点', '<div class="rows"><label class="w100">您确定要删除所选的热点吗？</label></div>', function() {
            hotAreaService.delete($scope, ids.join(','));
            jDialog.hide();
        });
    };

    /**
     * 导出热点列表数据
     * @Auther: zhuxuehuang
     * @Date: 2016-6-20
     */
    $scope.exportHotArea = function () {
        // 省市区
        $('input[name="provinceId"]').val(defaultString($scope.provinceId));
        $('input[name="cityId"]').val(defaultString($scope.cityId));
        $('input[name="countyId"]').val(defaultString($scope.countyId));
        var customerIdAndLevelVal = $('#customerIdAndLevel').val();
        // 表单隐藏域客户id附值
        $('#customerId').val(customerIdAndLevelVal ? customerIdAndLevelVal.split('#')[0] : '');
        $('#exportExcle').submit();
    };

    /**
     * 导入热点excel数据
     * @Auther: zhuxuehuang
     * @Date: 2016-6-20
     */
    $scope.importHotArea = function() {
        ngDialog.open({
            template : 'html/template/device/hotAreaImport.html',
            controller : 'hotAreaImportController',
            scope : $scope,
            title: '热点下瘦AP导入',
            width: '45%'
        });
    };
});