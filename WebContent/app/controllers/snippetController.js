(function (){

	angular.module("snippets")
		.controller("snippetController",function($location,$routeParams,snippetService,commentService){

			var self = this

			self.snippet //has comment list and grades
			self.text	
			self.filter='id'

			self.getSnippet=function(){
				snippetService.getSnippet($routeParams.id).then(function(retval){
					self.snippet=retval
					self.date= new Date(retval.date).toString().slice(0,24)
					
				})
			}

			self.lockSnippet=function(){
				snippetService.lockSnippet($routeParams.id).then(function(retval){
					if(!retval) { alert("lock snippet ERROR") }
					else{ self.getSnippet()}
				})
			}

			self.unlockSnippet=function(){
				snippetService.unlockSnippet($routeParams.id).then(function(retval){
					if(!retval) { alert("unlock snippet ERROR") }
					else{ self.getSnippet()}
				})
			}

			self.deleteSnippet=function(){
				if (confirm("Delete it????"))
					snippetService.deleteSnippet($routeParams.id).then(function (retval){
						if(!retval) {alert("delete snippet error")}
						else{
							$location.path("#/")
						}
					
				})
			}

			self.addComment=function(){
				commentService.addComment($routeParams.id,self.text).then(function(retval){
					if(!retval) { alert("unable to comment ERROR") }
					else{ self.text="";self.getSnippet()}
				})
			}

			self.deleteComment=function(id){
				if (confirm("Delete it????")){
					commentService.deleteComment($routeParams.id,id).then(function(retval){
						if(!retval) { alert("delete comment ERROR") }
						else{ self.getSnippet()}
					})
				}
			}

			self.addGrade=function(comment_id,grade){//boolean je grade
				commentService.addGrade($routeParams.id,comment_id,grade).then(function(retval){
					if(!retval) { alert("add grade ERROR") }
					else{ self.getSnippet()}
				})
			}

			self.getSnippet()


		})

})();