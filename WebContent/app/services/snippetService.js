(function (){

	angular.module("snippets")
		.service("snippetService",function($http,$window,authService){
			var self = this

			self.getSnippet=function(id){
				
				var ret=$http.get('api/snippets/'+id).then(
				function(response){
					return response.data
				},function(error){
					return undefined
				})

				return ret
			}

			self.getByOwner=function(username){
				
				var ret=$http.get('api/snippets/by_owner/'+username).then(
				function(response){
					return response.data
				})

				return ret
			}

			self.addSnippet=function(cd,dc,url,lg,min){

				name=authService.getUserName()

			
				data={
					"owner":name=="" ? "guest":name,
					"code":cd,
					"minutes":min,
					"desc":dc,
					"language":lg,
					"url":url
				}

				var ret=$http.post('api/snippets',data).then(
				function(response){
					$window.location.href="#/snippet/"+response.data
					return true
				},function(error){
					return false
				})

				return ret;
			}

			self.deleteSnippet=function(id){
				var ret = $http.delete("api/snippets/"+id).then(
				function(response){
					return true
				},function(error){
					return false
				})

				return ret
			}

		})
})();