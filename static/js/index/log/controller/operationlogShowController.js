indexApp.controller('operationlogShowController', function ($scope, operationlogService) {
    var id = $scope.id;

    operationlogService.show($scope, id);
});