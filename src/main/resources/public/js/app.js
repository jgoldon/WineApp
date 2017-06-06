(function() {
	var app = angular.module('app', ['ui.router', 'navController', 'ngAnimate', 'ui.bootstrap', 'ngResource', 'app.controllers', 'app.services']);

	// define for requirejs loaded modules
	define('app', [], function() { return app; });

	// function for dynamic load with requirejs of a javascript module for use with a view
	// in the state definition call add property `resolve: req('/views/ui.js')`
	// or `resolve: req(['/views/ui.js'])`
	// or `resolve: req('views/ui')`
	function req(deps) {
		if (typeof deps === 'string') deps = [deps];
		return {
			deps: function ($q, $rootScope) {
				var deferred = $q.defer();
				require(deps, function() {
					$rootScope.$apply(function () {
						deferred.resolve();
					});
					deferred.resolve();
				});
				return deferred.promise;
			}
		}
	}

	app.config(function($stateProvider, $urlRouterProvider, $controllerProvider, $httpProvider){
		var origController = app.controller;
		app.controller = function (name,  constructor){
			$controllerProvider.register(name, constructor);
			return origController.apply(this, arguments);
		};

		var viewsPrefix = 'views/';

        $httpProvider.interceptors.push(['$q', '$location', '$window', function($q, $location, $window) {
            return {
                'request': function (config) {
                    config.headers = config.headers || {};
                    if ($window.localStorage.getItem("token")) {
                        config.headers.Authorization = $window.localStorage.getItem("token");
                    }
                    return config;
                },
                'responseError': function(response) {
                    if(response.status === 401 || response.status === 403) {
                        $location.path('/signin');
                    }
                    return $q.reject(response);
                }
            };
        }]);

		// For any unmatched url, send to /
		$urlRouterProvider.otherwise("/");

		$stateProvider
			// you can set this to no template if you just want to use the html in the page
			.state('home', {
				url: "/",
				templateUrl: viewsPrefix + "home.html",
				data: {
					pageTitle: 'Home'
				}
			})
            .state('register', {
                url: "/register",
                templateUrl: viewsPrefix + "register.html",
                data: {
                    pageTitle: 'Register'
                },
				controller: 'UserRegisterController'
            })
            .state('login', {
                url: "/login",
                templateUrl: viewsPrefix + "login.html",
                data: {
                    pageTitle: 'Login'
                },
                controller: 'UserLoginController'
            })
			.state('wines',{
	        url:'/wines',
	        templateUrl: viewsPrefix + 'wines.html',
	        controller:'WineListController'
	    }).state('viewWine',{
	       url:'/wines/:id/view',
	       templateUrl: viewsPrefix + 'wine-view.html',
	       controller:'WineViewController'
	    }).state('newWine',{
	        url:'/wines/new',
	        templateUrl: viewsPrefix + 'wine-add.html',
	        controller:'WineCreateController'
	    }).state('editWine',{
	        url:'/wine/:id/edit',
	        templateUrl: viewsPrefix + 'wine-edit.html',
	        controller:'WineEditController'
	    }).state('addReview',{
	    	url:'/wine/:id/reviews/new',
			templateUrl: viewsPrefix + 'review-add.html',
			controller:'ReviewController'
		})
	})
	.directive('updateTitle', ['$rootScope', '$timeout',
		function($rootScope, $timeout) {
			return {
				link: function(scope, element) {
					var listener = function(event, toState) {
						var title = 'Project Name';
						if (toState.data && toState.data.pageTitle) title = toState.data.pageTitle + ' - ' + title;
						$timeout(function() {
							element.text(title);
						}, 0, false);
					};

					$rootScope.$on('$stateChangeSuccess', listener);
				}
			};
		}
	]);
}());
