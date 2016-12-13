indexApp.controller('customerBindingDeviceController', function($scope, $location, $timeout, $interval, Upload, customerService) {

    //下载模板
    $scope.download = function(){
        var fileName = $('#test_select1').val();
        if(isBlank(fileName)){
        	var aId = $('#down');
        	aId.removeAttr('href', '');
        	$('#validate_messsage').empty().html('设备类型不能为空！').show();
            return;         
         }
    };

    /**
     * 确定 按钮
     */
    $scope.bindingDevice = function(file){
        var customerId = $scope.customerId;//客户id
        var customerName = $scope.customerName;//客户名
        var projectId = $scope.projectId;//项目id
        var fileName = file !== null && file !== undefined ? file.name : '';
        var deviceType = $('#test_select1').val();
        if(isBlank(deviceType)){
        	$('#validate_messsage').empty().html('设备类型不能为空！').show();
            return;
         }
        if(isBlank(fileName)){
            $('#validate_messsage').empty().html('请先上传Excel文件！').show();
            return;
        }
        var fileSuffixArray = fileName.split('.');
        var fileSuffix = fileSuffixArray.length > 1 ? $.trim(fileSuffixArray[1]) : '';
        if(! (fileSuffix == 'xls' || fileSuffix == 'xlsx') ){
            $('#validate_messsage').empty().html('文件格式错误，请上传Excel文件！').show();
            return;
        }
        if(fileSuffix == 'xlsx'){
        	$('#validate_messsage').empty().html('文件格式错误，请上传xls格式文件！').show();
            return;
        }
        $('#validate_messsage').empty().hide();
        file.upload = Upload.upload({
            url: '/device/bind?id=' + customerId + '&projectId=' + projectId + '&fileName=' + deviceType,
            method: 'POST',
            headers: {
                'my-header': 'my-header-value'
            },
            file: file,
            fileFormDataName: 'xlsFile'
        });
        file.upload.then(function (response) {
                $timeout(function () {
                    file.progress = 100;
                    file.result = response.data;
                });
            },
            function (response) {
                if (response.status > 0){
                    $scope.errorMsg = response.status + ': ' + response.data;
                }
            }
        );
        file.upload.progress(function (evt) {
            var curProgress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
            if(curProgress >= 90){
                curProgress = 90;
            }
            file.progress = curProgress;
        });
        file.upload.xhr(function (xhr) {
        });
    };

});