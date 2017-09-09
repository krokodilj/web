(function () {


	angular.module('snippets',['ngRoute','ngCookies'])
		.config(function($routeProvider){
				$routeProvider
					.when("/",{
						templateUrl:"app/views/search.html",
						controller:"searchController",
						controllerAs:"ctrl",
						resolve:{
							"permission":function(permissionService){
								permissionService.givePermission(['user','ruser','admin']);
							}
						}
						
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
					.when('/snippet/:id',{
						templateUrl:"app/views/snippet.html",
						controller:"snippetController",
						controllerAs:"ctrl",
						resolve:{
							"permission":function(permissionService){
								permissionService.givePermission(['user','ruser','admin']);
							}
						}
					})
					.otherwise({
						redirectTo:"/"
					})
			})	
		.filter("orderBy", function() {
        	return function(items,field) {
	            var filtered = [];
	            angular.forEach(items,function(item) {
			      filtered.push(item);
			    });
	            if(field=="date")
				    filtered.sort(function (a, b) {
				      return (a[field] < b[field] ? 1 : -1);
				      });
				if(field=="positive")
					filtered.sort(function (a, b) {
					      return (a.grade.positive < b.grade.positive ? 1 : -1);
					     });					
			    
			    return filtered;
			}
   		})
   		.filter("date", function() {
        	return function(longdate) {
	            return new Date(longdate).toString().slice(0,24);
			}
   		})
		
})();

