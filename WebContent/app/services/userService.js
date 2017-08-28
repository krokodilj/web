(function () {
	angular.module("snippets")
		.service("userService",function($http){

			var self=this;

			self.register=function(un,ps,em,fn,ln,ph,ad){
				
				
				data={
					"username":un,
					"password":ps,
					"email":em
				}
				var ret= $http.post('api/users/register',data).then(
				function(response){
					return true
				},function(error){					
					return false
				})
				return ret
			}

			self.checkUsername=function(username){
				
				var ret= $http.get('api/users/check/'+username).then(
				function(response){					
					return true
				},function(error){					
					return false
				});
				return ret		

			}

			self.uploadPicture=function(){
				
			}

			self.getUser=function(username){
				var ret = $http.get('api/users/'+username).then(
				function(response){
					return response.data
				},function(error){
					return undefined
				});
				return ret;
			}

			self.block=function(){

			}



		})
})();