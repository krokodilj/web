(function (){
	angular.module("snippets")
		.controller("registerController",function($scope,userService,authService){

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

			self.n="images/guest.png"

			self.register = function(){


				userService.register(self.username,self.password,self.email,
									 self.firstname,self.lastname,
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

			self.upload=function(){
				userService.uploadImage(self.username,self.file).then(function(retval){
					if(!retval){ alert("image upload ERROR")}
					else { authService.login(self.username,self.password)	}
				})							
			}

			$scope.readFile = function(input) {				
               	self.input=input
                var reader = new FileReader();

	            reader.onload = function (e) {
	                self.n=e.target.result	
	                $scope.$apply()	                    
	            }

	            reader.readAsDataURL(input.files[0]);
	            self.file = input.files[0]


        	}

		});

})();