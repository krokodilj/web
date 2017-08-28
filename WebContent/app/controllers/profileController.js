( function (){
	angular.module("snippets")
		.controller("profileController",function($routeParams,userService,snippetService,languageService){

			var self = this

			self.user
			self.snippets
			self.languages
			self.language

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

			self.getLanguages=function(){
				languageService.getLanguages().then(function(retval){
					self.languages=retval
				})
			}

			self.addLanguage=function(){
				languageService.addLanguage(self.language).then(function(retval){
					if(!retval){	self.error=true	}
					else{	self.getLanguages()	}
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
