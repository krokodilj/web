(function (){

	angular.module("snippets")
		.service("languageService",function($http){

			var self = this

			self.addLanguage=function(language){
				var ret = $http.post('api/snippets/languages',language).then(
					function(response){
						return true
					},function(error){
						return false;
					})

				return ret;
			}

			self.getLanguages=function(){

				var ret=$http.get('api/snippets/languages').then(
					function(response){
						return response.data
					},function(error){
						return false;
					})

				return ret;
			}


		})
})();