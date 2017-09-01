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
						$rootScope.name=response.data.name
						$rootScope.img_src=response.data.img_src
						$cookies.putObject("token",response.data)
						$http.defaults.headers.common.Authorization = response.data;
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
				$rootScope.name="guest"
				$rootScope.img_src="images/guest.png"
				$http.defaults.headers.common.Authorization = undefined;
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

			self.getUserName=function(){
				var token = $cookies.getObject('token')
				if(!token) return 'guest';
				return token.name;
			}

			self.getUserImgSrc=function(){
				var token = $cookies.getObject('token')
				if(!token) return 'images/guest.png';
				return token.img_src;
			}

			$rootScope.role=self.getUserRole();
			$rootScope.name=self.getUserName();
			$rootScope.img_src=self.getUserImgSrc();

		})
})();