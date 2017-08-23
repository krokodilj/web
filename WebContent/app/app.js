(function () {


	angular.module('snippets',['ngRoute','ngCookies'])
		.config(function($routeProvider){
				$routeProvider
					.when("/",{
						
						
					})
					.when("/login",{
						templateUrl:"app/views/login.html",
						controller:"loginController",
						controllerAs:"ctrl",
						resolve:{
							"permission":function(permissions){
								permissions.givePermission(['user']);
							}
						}
					})
					.when("/register",{
						templateUrl:"app/views/register.html",
						controller:"registerController",
						controllerAs:"ctrl",
						resolve:{
							"permission":function(permissions){
								permissions.givePermission(['user']);
							}
						}
					})
					.when("/add_snippet",{


					})
					.otherwise({
						redirectTo:"/"
					})
			})	
		
})();

