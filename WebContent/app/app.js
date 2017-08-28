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
							"permission":function(permissionService){
								permissionService.givePermission(['user']);
							}
						}
					})
					.when("/register",{
						templateUrl:"app/views/register.html",
						controller:"registerController",
						controllerAs:"ctrl",
						resolve:{
							"permission":function(permissionService){
								permissionService.givePermission(['user']);
							}
						}
					})
					.when("/add_snippet",{
						templateUrl:"app/views/add_snippet.html",
						controller:"addSnippetController",
						controllerAs:"ctrl",
						resolve:{
							"permission":function(permissionService){
								permissionService.givePermission(['user','ruser','admin']);
							}
						}

					})
					.when("/profile/:username",{
						templateUrl:"app/views/profile.html",
						controller:"profileController",
						controllerAs:"ctrl",
						resolve:{
							"permission":function(permissionService){
								permissionService.givePermission(["ruser","admin"])
							}
						}
					})
					.otherwise({
						redirectTo:"/"
					})
			})	
		
})();

