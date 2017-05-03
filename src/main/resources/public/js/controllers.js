angular.module('app.controllers', []).controller('WineListController', function($scope, $state, popupService, $window, Wine) {
  $scope.wines = Wine.query(); //fetch all wines. Issues a GET to /api/vi/wines

  $scope.deleteWine = function(wine) { // Delete a Wine. Issues a DELETE to /api/v1/wines/:id
    if (popupService.showPopup('Are you sure?')) {
      wine.$delete(function() {
        $scope.wines = Wine.query();
        $state.go('wines');
      });
    }
  };
}).controller('WineViewController', function($scope, $stateParams, Wine) {
  $scope.wine = Wine.get({ id: $stateParams.id }); //Get a single wine. Issues a GET to /api/v1/wines/:id
}).controller('WineCreateController', function($scope, $state, $stateParams, Wine) {
  $scope.wine = new Wine();  //create new wine instance. Properties will be set via ng-model on UI

  $scope.addWine = function() { //create a new wine. Issues a POST to /api/v1/wines
    $scope.wine.$save(function() {
      $state.go('wines'); // on success go back to the list i.e. wines state.
    });
  };
}).controller('WineEditController', function($scope, $state, $stateParams, Wine) {
  $scope.updateWine = function() { //Update the edited wine. Issues a PUT to /api/v1/wines/:id
    $scope.wine.$update(function() {
      $state.go('wines'); // on success go back to the list i.e. wines state.
    });
  };

  $scope.loadWine = function() { //Issues a GET request to /api/v1/wines/:id to get a wine to update
    $scope.wine = Wine.get({ id: $stateParams.id });
  };

  $scope.loadWine(); // Load a wine which can be edited on UI
});
