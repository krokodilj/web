( function (){
	angular.module("snippets")
		.controller("profileController",function($routeParams,userService,snippetService){

			var self = this

			self.user
			self.snippets

			self.getUser=function(){

				userService.getUser($routeParams.username).then(function(retval){
					self.user=retval					
				})
			}

			self.getSnippets=function(){

				snippetService.getByOwner($routeParams.username).then(function(retval){
					self.snippets=retval
					
				})
			}
			
			self.remove=function(id){
				if (confirm("Delete it????"))
					snippetService.deleteSnippet(id).then(function (retval){
						if(!retval) {alert("delete snippet error")}
						else{
							self.getSnippets()
						}
					
				})
			}

			self.getUser();

			self.getSnippets();

		})

})();
