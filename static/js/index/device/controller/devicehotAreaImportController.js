indexApp.controller('hotAreaImportController', function ($scope, $timeout, $interval, Upload, staticUserService, customerService) {
    /**
     * 上传 按钮
     */
    $scope.uploadXls = function (file) {
        var fileName = file !== null && file !== undefined ? file.name : '';
        if (isBlank(fileName)) {
            $('#validate_messsage').empty().html('请先上传Excel文件！').show();
            return ;
        }
        var fileSuffixArray = fileName.split('.');
        var fileSuffix = fileSuffixArray.length > 1 ? $.trim(fileSuffixArray[1]) : '';
        if (!(fileSuffix == 'xls' || fileSuffix == 'xlsx')) {
            $('#validate_messsage').empty().html('文件格式错误，请上传Excel文件！').show();
            return ;
        }
        $('#validate_messsage').empty().hide();

        file.upload = Upload.upload({
            url: '/hotArea/importHot',
            method: 'POST',
            headers: {
                'my-header': 'my-header-value'
            },
            //fields: {username: $scope.username},
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
                if (response.status > 0) {
                    $scope.errorMsg = response.status + ': ' + response.data;
                }
            }
        );
        file.upload.progress(function (evt) {
            var curProgress = Math.min(100, parseInt(100.0 * evt.loaded / evt.total));
            if (curProgress >= 90) {
                curProgress = 90;
            }
            file.progress = curProgress;
        });
        file.upload.xhr(function (xhr) {
        });
    };

});