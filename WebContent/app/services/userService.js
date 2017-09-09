(function () {
	angular.module("snippets")
		.service("userService",function($http){

			var self=this;

			self.register=function(un,ps,em,fn,ln,ph,ad){
				
				
				data={
					"username":un,
					"password":ps,
					"email":em,
					"fname":fn,
					"lname":ln,
					"phone":ph,
					"address":ad
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

			self.uploadImage=function(username,file){
			
				data= new FormData()
				data.append("username",username)
				data.append("file",file)

				var ret=$http.post('api/users/upload',data,{headers: {'Content-Type': undefined}}).then(
					function(response){
						return true
					},function(error){					
						return false
					})
				return ret
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

			self.blockUser=function(username){
				var ret=$http.put("api/users/block/"+username).then(
					function(response){
						return true;
					},function(error){
						return false;
					})
				return ret
			}

			self.search=function(query){
				query= query=="" ? undefined:query

				var ret= $http.get("api/users/by_username/"+query).then(
					function(response){
						return response.data
					})
				return ret
			}

		})
})();