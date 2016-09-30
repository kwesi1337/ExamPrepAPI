var WorldApp= angular.module('WorldApp', ['ngRoute']);

WorldApp.controller('WorldController', ['WorldFactory', function (WorldFactory){
        
        var self = this;
        
        console.log("loading");
        
        self.title = "World Info App";
        
        self.countries = [];
        
        self.cities = [];
        
        self.getAllCountries = function (){
            
            WorldFactory.getAllCountries().then(function (success) {
                
                self.countries = success.data;
                
                
            }, function (error){
                
                
                console.log("Something went wrong");
                
            });
        };
        
        
        self.getAllCities = function (countryCode){
            
            WorldFactory.getAllCities(countryCode).then(function (success){
                
                self.cities = success.data;
                
            }, function (error){
                
                console.log("something went wrong");
                
            });
                           };
                           
                           
}]);

WorldApp.factory('WorldFactory', ['$http', function($http) {
        
        var getAllCountries = function(){
            
            
            var url = "api/country/all";
            return $http.get(url);
            
            
};

var getAllCountriesPopulation = function (population) {
    
    var url = "api/country/" + population;
    
    return $http.get(url);
};

var getAllCities = function (countryCode){
    
    var url ="api/country/city/" + countryCode;
    return $http.get(url);
};

return  {
    getAllCountries: getAllCountries, getAllCountriesPopulation: getAllCountriesPopulation, getAllCities: getAllCities
    
};

}]);
WorldApp.config(function ($routeProvider){
    
    $routeProvider
            .when("/all", {
                
                templateUrl: "app/views/all_countries.html",
                controller: "WorldController"
    })
            .when ("/details", {
                
                templateUrl: "app/views/country_details.html",
        controller: "WorldController"
    })
    
            .otherwise({
                
                redirectTo: "/all"
    });
});

WorldApp.filter(function() {
    
    return function (input){
        
        return "output";
    };
});