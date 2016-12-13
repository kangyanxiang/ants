indexApp.controller('deviceShowController', function($scope, $location, deviceService) {

	var id = $scope.id || $location.search()['id']; //设备id => ngDialog.open直接传值，设备详情页URL传值

	deviceService.findByDeviceId($scope, id);
	 
});
