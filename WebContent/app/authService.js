(function () {
	angular.module("snippets")
		.service("authService",function($rootScope,$window,$http,$cookies){

			var self = this;			

			self.login=function(username,password){

				data={
					"username":username,
					"password":password
				}

				var ret=$http.post('api/users/login',data).then(
				function(response){					
						$rootScope.role=response.data.role
						$cookies.putObject("token",response.data)
						$window.location.href="#/"
						return true					
				},function(error){
						return false						
				})
				
				return ret
			}

			self.logout=function(){
				$cookies.remove("token")
				
				$rootScope.role="user"

				$window.location.href="#/login"
				
			}

			self.getCookie= function(){
				return $cookies.getObject('token')
			}

			self.getUserRole=function(){
				var token = $cookies.getObject('token')
				if(!token) return 'user';
				return token.role;
			}

			$rootScope.role=self.getUserRole();


		})
})();