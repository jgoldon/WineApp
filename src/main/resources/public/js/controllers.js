angular.module('app.controllers', []).controller('WineListController', function($scope, $state, popupService, $window, Wine) {
  $scope.wines = Wine.query(); //fetch all wines. Issues a GET to /api/vi/wines
  $scope.isAdmin = localStorage.getItem("isAdmin");
  $scope.deleteWine = function(wine) { // Delete a Wine. Issues a DELETE to /api/v1/wines/:id
    if (popupService.showPopup('Are you sure?')) {
      wine.$delete(function() {
        $scope.wines = Wine.query();
        $state.go('wines');
      });
    }
  };
}).controller('WineViewController', function($scope, $stateParams, Wine, Review) {
  $scope.wine = Wine.get({ id: $stateParams.id }, function () {
      $scope.reviews = Review.query({wineId: $scope.wine.id});
  }); //Get a single wine. Issues a GET to /api/v1/wines/:id
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
}).controller('UserRegisterController', function($scope, $state, $stateParams, User){
  $scope.user = new User();
    $scope.register = function() {
        $scope.user.$save(function() {
            $state.go('home');
        });
    };
}).controller('UserLoginController', function($scope, $state, $stateParams, Auth, $rootScope){
    $scope.auth = new Auth();
    $scope.login = function() {
        $scope.auth.$save(function(respone) {
            window.localStorage.setItem('token', respone.token);
            window.localStorage.setItem('isAdmin', respone.isAdmin);
            window.localStorage.setItem('username', $scope.auth.username);
            $rootScope.$broadcast("userLogged");
            $state.go('home');
        });
    };
}).controller('ReviewController', function($scope, $state, $stateParams, Review) {
    $scope.review = new Review();  //create new wine instance. Properties will be set via ng-model on UI
    $scope.wineId = $stateParams.id;
    $scope.addReview = function() { //create a new wine. Issues a POST to /api/v1/wines
        $scope.review.wineId = $scope.wineId;
        $scope.review.$save(function() {
            $state.go('wines'); // on success go back to the list i.e. wines state.
        });
    };
}).controller('RecommendController', function($scope, $state, Recommend){
    $scope.recommends = Recommend.query(); //create new instance of recommendation
}).controller('UserController', function($scope, $state, popupService, $window, User){
    $scope.users = User.query();
    $scope.deleteUser = function(user) { // Delete a user. Issues a DELETE to /api/v1/users/:id
        if (popupService.showPopup('Are you sure?')) {
            user.$delete(function() {
                $scope.users = User.query();
                $state.go('users');
            });
        }
    };
});
