(function (){
	angular.module("snippets")
		.controller("searchController",function(userService,snippetService,languageService){

			var self = this

			self.query
			self.lang

			self.users
			self.snippets
			self.languages

			languageService.getLanguages().then(function(retval){
				self.languages=retval
			})

			self.search=function(){

				var start
				if(self.start_date) start=new Date(self.start_date).getTime()

				var end
				if(self.end_date) end=new Date(self.end_date).getTime()	

				snippetService.search(self.query,self.lang,start,end).then(function(retval){
					self.snippets=retval
				})

				userService.search(self.query).then(function(retval){
					self.users=retval
				})

			}


			

		})
})();