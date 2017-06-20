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
            $scope.isLogged = true;
        }

		if($window.localStorage.getItem("isAdmin"))
		{
            $scope.isAdmin = $window.localStorage.getItem("isAdmin");
        }

        $rootScope.$on("userLogged", function(){
        	$scope.isLogged = true;
            $scope.isAdmin = $window.localStorage.getItem("isAdmin")
		})

		$scope.logout = function(){
            $window.localStorage.removeItem("token");
            $window.localStorage.removeItem("username");
            $window.localStorage.removeItem("isAdmin");
			$scope.isLogged = false;
			$scope.isAdmin = false;
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
				name: 'Recommendations',
				url: '#/recommends'
			},
			{
				name: 'Register',
				url: '#/register'

            },
            {
                name: 'Login',
                url: '#/login'
            },
			{
				name: 'Users',
				url: '#/users'
			}
		]

		$scope.shouldDisplay = function(page){
			if(page.name == 'Login' || page.name == 'Register'){
				return !$scope.isLogged;
			}

            if(page.name == 'Wines'){
                return $scope.isLogged;
            }

            if(page.name == 'Users'){
            	console.log("scope2", $scope.isLogged && $scope.isAdmin);
                return ($scope.isLogged && $scope.isAdmin) == 'true';
            }

            if(page.name == 'Recommendations'){
                console.log("scope2", $scope.isLogged && $scope.isAdmin);
                return ($scope.isLogged && $scope.isAdmin) == 'true';
            }

			return true;
		}
	});
