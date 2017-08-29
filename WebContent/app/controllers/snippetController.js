(function (){

	angular.module("snippets")
		.controller("snippetController",function($routeParams,snippetService,commentService){

			var self = this

			self.snippet

			self.getSnippet=function(){
				snippetService.getSnippet($routeParams.id).then(function(retval){
					self.snippet=retval
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

			self.addComment=function(){
				commentService.addComment($routeParams.id,self.text).then(function(retval){
					if(!retval) { alert("unable to comment ERROR") }
					else{ self.getSnippet()}
				})
			}



			self.getSnippet()


		})

})();