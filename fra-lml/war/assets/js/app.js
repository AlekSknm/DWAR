var app = angular.module('hippiky', 
    [  'ui.router',
    'angular-google-gapi', 'login-controller']);



app.run(['GAuth', 'GApi', '$state', '$rootScope',
    function(GAuth, GApi, $state, $rootScope) {

          var CLIENT = '772002611802-p9cingfpivuj4iljgjkenaron9eshaqa.apps.googleusercontent.com';    
          var BASE = 'https://fra-lml.appspot.com/_ah/api';

      GApi.load('eventendpoint','v1',BASE);
     
      GAuth.setClient(CLIENT);
        GAuth.checkAuth().then(
            function () {
                if($state.includes('login')) {
                	
                }
                    $state.go('webapp.home');
            },
            function() {
                $state.go('login');
            }
        );

     
    }
]);