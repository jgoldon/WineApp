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
});