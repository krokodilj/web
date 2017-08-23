(function (){
	angular.module("snippets")
		.controller("loginController",function(authService){

			var self = this;

			self.error=false
			self.username
			self.password
			
			self.login=function(){
				authService.login(self.username,self.password).then(function(retval){
					
					self.error=!retval
					
				})

			}


		})

})();