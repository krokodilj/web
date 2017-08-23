(function () {
	angular.module("snippets")
		.controller("navbarController",function(authService){

			var self=this;

			self.role=authService.getUserRole();

			self.logout=authService.logout
			
		});

})();
