 angular.module('angularoauthexampleApp').controller('MainCtrl', function ($scope) {
     $scope.login=function() {
     	var client_id="772002611802-p9cingfpivuj4iljgjkenaron9eshaqa.apps.googleusercontent.com";
    	var scope="email";
     	var redirect_uri="http://fra-lml.appspot.com";
     	var response_type="token";
     	var url="https://accounts.google.com/o/oauth2/auth?scope="+scope+"&client_id="+client_id+"&redirect_uri="+redirect_uri+
     	"&response_type="+response_type;
     	window.location.replace(url);
     };
   });
 
 angular.module('angularoauthexampleApp', [
    ])
    .config(function ($routeProvider) {
      $routeProvider
        .when('/access_token=:accessToken', {
          template: '',
          controller: function ($location,$rootScope) {
            var hash = $location.path().substr(1);
           
           var splitted = hash.split('&');
           var params = {};
 
           for (var i = 0; i < splitted.length; i++) {
             var param  = splitted[i].split('=');
             var key    = param[0];
             var value  = param[1];
             params[key] = value;
             $rootScope.accesstoken=params;
          }
           $location.path("/about");
         }
       })
   });