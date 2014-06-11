angular.module('prp', ['prp.services', 'prp.controllers']).
    config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/previsao-list', 		{templateUrl: 'previsao-list.html', 		controller: 'PrevisaoListCtrl'});
        $routeProvider.when('/previsao-detail/:id', {templateUrl: 'previsao-detail.html', 		controller: 'PrevisaoDetailCtrl'});
        $routeProvider.when('/previsao-creation',	{templateUrl: 'previsao-creation.html',	controller: 'PrevisaoCreationCtrl'});
        $routeProvider.otherwise({redirectTo: '/user-list'});
    }
]);