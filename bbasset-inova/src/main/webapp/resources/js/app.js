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




	function abrirPopup(url, w, h) {
		var newW = w + 100;
		var newH = h + 100;
		var left = (screen.width - newW) / 2;
		var top = (screen.height - newH) / 2;
		var newwindow = window.open(url, 'name', 'width=' + newW + ',height='+ newH + ',left=' + left + ',top=' + top);
		newwindow.resizeTo(newW, newH);
	
	
		newwindow.moveTo(left, top);
		newwindow.focus();
		return false;
	}

