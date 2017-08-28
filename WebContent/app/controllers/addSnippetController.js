( function (){

	angular.module("snippets")
		.controller("addSnippetController",function(snippetService,languageService){

			var self = this

			self.languages 

			self.code
			self.desc
			self.url
			self.language
			self.minutes

			languageService.getLanguages().then(function(retval){
				self.languages=retval
			})

			self.create=function(){
				snippetService.addSnippet(self.code,self.desc,self.url,self.language,self.minutes).then(function(retval){
					self.error=!retval
				})
			}
		})

})();