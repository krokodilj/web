(function (){
	angular.module("snippets")
		.controller("registerController",function(userService,authService){

			var self=this

			self.step=1
			self.error=false
			self.usernameTaken=false

			self.username
			self.password
			self.email
			self.firstname
			self.lastname
			self.phone
			self.address

			self.register = function(){


				userService.register(self.username,self.password,self.email,
									 self.firstname,self.lastname,self,
									 self.phone,self.address)
				.then(function(retval){
				 	self.error=!retval;				 	
				})
				
				if(!self.error) self.step=2
			}

			self.checkUsername= function(){
				userService.checkUsername(self.username).then(function(retval){					
					self.usernameTaken=!retval
				})
			}

			self.skip=function(){
				authService.login(self.username,self.password)
			}

		});


})();