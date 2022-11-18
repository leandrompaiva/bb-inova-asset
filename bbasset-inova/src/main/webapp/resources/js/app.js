'use strict';

var BBAssetApp = {};

var App = angular.module('BBAssetApp', ['BBAssetApp.filters', 'BBAssetApp.services', 'BBAssetApp.directives']);



App.config(['$routeProvider', function ($routeProvider) {
	
	
    $routeProvider.when('/fundos', {
        templateUrl: 'fundos/index',
        controller: RouteController
    });
    
    
    
    $routeProvider.when('/fundosPerfilConservador', {
        templateUrl: 'fundos/retornaFundos',
        controller: PerfilConservadorController
    });    
    
    
    
    $routeProvider.when('/fundosPerfilModerado', {
        templateUrl: 'fundos/retornaFundos',
        controller: PerfilModeradoController
    });    
    
    
   
    $routeProvider.when('/fundosPerfilArrojado', {
        templateUrl: 'fundos/retornaFundos',
        controller: PerfilArrojadoController
    });  
    

    
    
    $routeProvider.otherwise({redirectTo: '/fundos'});
    
}]);
