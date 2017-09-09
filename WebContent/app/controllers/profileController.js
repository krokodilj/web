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
					snippetService.getByOwner(self.user.username).then(function(retval){
					self.snippets=retval
					
					})				
				})
			}


			self.getLanguages=function(){
				languageService.getLanguages().then(function(retval){
					self.languages=retval
				})
			}

			self.addLanguage=function(){
				languageService.addLanguage(self.language).then(function(retval){
					if(!retval){	alert("Already defined")	}
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

			self.blockUser=function(){
				userService.blockUser(self.user.username).then(function(retval){
					if(!retval) {alert("block user ERROR")}
					else{self.getUser()}
				})
			}

			self.getUser();


		})

})();
