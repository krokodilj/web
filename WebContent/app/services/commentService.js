(function (){
	angular.module("snippets")
		.service("commentService",function($http,authService){

			var self=this

			self.addComment=function(snippet_id,text){
				name=authService.getUserName()
				owner_img=authService.getUserImgSrc()
				data={
					"owner":name,
					"owner_img":owner_img,
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

				var ret = $http.delete('api/snippets/'+snippet_id+'/comment/'+id).then(
					function(response){
						return true
					},function(error){
						return false
					})

				return ret

			}

			self.addGrade=function(snippet_id,comment_id,grade){
				name=authService.getUserName();

				data={
					"user":name,
					"grade":grade
				}

				var ret=$http.post('api/snippets/'+snippet_id+"/comment/"+comment_id+"/grade",data).then(
					function(response){
						return true
					},function(error){
						return false
					})

				return ret
			}

			

		})
})();