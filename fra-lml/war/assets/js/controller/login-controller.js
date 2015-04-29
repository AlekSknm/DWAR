var controller = angular.module('login-controller', []);

controller.controller('login-controller', ['$scope', 'GAuth', 'GApi', '$state',
    function clientList($scope, GAuth, GApi, $state) {
    	if(GApi.isLogin()){
    		$state.go('webapp.home');
    	}

        $scope.doLogin = function() {
            GAuth.login().then(function(){
            	
            	GApi.executeAuth('infoendpoint', 'registerUser').then(function(resp) {
        			console.log('registerUser' + resp);
        			$state.go('webapp.home')
        		},function(resp) {
        			console.log('registerUser' + resp);
        		});
            	;
            });
        };
    }
])