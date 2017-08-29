(function (){
	angular.module("snippets")
		.service("commentService",function($http,authService){

			var self=this

			self.addComment=function(snippet_id,text){
				name=authService.getUserName()

				data={
					"owner":name,
					"text":text					
				}

				var ret=$http.post('api/snippets/'+snippet_id+"/comment",data).then(
					function(response){
						return true
					},function(error){
						return false
					})
				return ret
			}

			self.deleteComment=function(snippet_id,id){

			}

			

		})
})();