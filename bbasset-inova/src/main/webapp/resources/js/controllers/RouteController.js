'use strict';


var RouteController = function($scope, $http) {};


var PerfilConservadorController = function($scope, $http) {
    $scope.fetchPerfilConservador = function() {
        $http.get('fundos/perfilConservadorlist.json').success(function(fundosList){
            $scope.fundos = fundosList;
        });
    };
    $scope.fetchPerfilConservador();
};



var PerfilModeradoController = function($scope, $http) {
    $scope.fetchPerfilModerado = function() {
        $http.get('fundos/perfilModeradolist.json').success(function(fundosList){
        	 $scope.fundos = fundosList;
        });
    };
    $scope.fetchPerfilModerado();
};



var PerfilArrojadoController = function($scope, $http) {
    $scope.fetchPerfilArrojado = function() {
        $http.get('fundos/perfilArrojadolist.json').success(function(fundosList){
        	$scope.fundos = fundosList;
        });
    };
    $scope.fetchPerfilArrojado();
};