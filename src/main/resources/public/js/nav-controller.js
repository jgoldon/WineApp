angular.module('navController', [])
	.controller('nav', function($scope, $state, $rootScope, $window) {
		$scope.title = 'WineApp';

		// returns true if the current router url matches the passed in url
		// so views can set 'active' on links easily
		$scope.isUrl = function(url) {
			if (url === '#') return false;
			return ('#' + $state.$current.url.source + '/').indexOf(url + '/') === 0;
		};

		if($window.localStorage.getItem("token"))
		{
            $scope.isLogged =true;
        }

        $rootScope.$on("userLogged", function(){
        	$scope.isLogged = true;
		})

		$scope.logout = function(){
            $window.localStorage.removeItem("token");
            $window.localStorage.removeItem("username");
			$scope.isLogged = false;
            $state.go('home');
		}

		$scope.pages = [
			{
				name: 'Home',
				url: '#/'
			},
			{
				name: 'Wines',
				url: '#/wines'
			},
			{
				name: 'Register',
				url: '#/register'
			},
            {
                name: 'Login',
                url: '#/login'
            }
		]
	});
