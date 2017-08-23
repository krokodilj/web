(function () {

	angular.module("snippets")
		.service("permissions",function($window,$rootScope){

			var self = this

			self.redirects={
				"user":"#/login",
				"ruser":"#/",
				"admin":"#/"
			}

			self.givePermission= function(roles){

				var role=$rootScope.role

				if (!roles.includes(role)){
					$window.location=self.redirects[role]
				}

			}
			


		})

})();