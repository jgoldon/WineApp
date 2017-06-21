angular.module('app.services', []).factory('Wine', function($resource) {
  return $resource('/api/v1/wines/:id', { id: '@id' }, {
    update: {
      method: 'PUT'
    }
  });
}).service('popupService',function($window){
    this.showPopup=function(message){
        return $window.confirm(message);
    }
}).factory('User', function($resource) {
    return $resource('/api/v1/users/:id', { id: '@id' }, {
        update: {
            method: 'PUT'
        }
    });
}).factory('Auth', function($resource) {
    return $resource('/api/v1/users/auth/:id', { id: '@id' }, {
        update: {
            method: 'PUT'
        }
    });
}).factory('Review', function($resource) {
    return $resource('/api/v1/reviews/:id', { id: '@id' }, {
        update: {
            method: 'PUT'
        },
        query:{
            method:'GET',isArray:true
        }
    });
}).factory('Recommend', function($resource){
    return $resource('/api/v1/recommends')
}).factory('Oddity', function($resource){
    return $resource('/api/v1/oddities')
});
