angular.module('prp', ['prp.services', 'prp.controllers']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/previsao-list', 		    {templateUrl: 'previsao/previsao-list.html', 	    controller: 'PrevisaoListCtrl'});
        $routeProvider.when('/previsao-detail/:id',     {templateUrl: 'previsao/previsao-detail.html', 	    controller: 'PrevisaoDetailCtrl'});
        $routeProvider.when('/previsao-creation',	    {templateUrl: 'previsao/previsao-creation.html',     controller: 'PrevisaoCreationCtrl'});
        $routeProvider.when('/movimentacao-list', 		{templateUrl: 'movimentacao/movimentacao-list.html', 	controller: 'MovimentacaoListCtrl'});
        $routeProvider.when('/movimentacao-detail/:id', {templateUrl: 'movimentacao/movimentacao-detail.html', 	controller: 'MovimentacaoDetailCtrl'});
        $routeProvider.when('/movimentacao-creation',	{templateUrl: 'movimentacao/movimentacao-creation.html',	controller: 'MovimentacaoCreationCtrl'});
        $routeProvider.otherwise({redirectTo: '/user-list'});
    }
]);

/*
 * Services sao as Factory. Basicamente um repositorio que traz uma colecao e outro que atualiza um objeto somente.
 */
var services = angular.module('prp.services', ['ngResource']);

var app = angular.module('prp.controllers', []);