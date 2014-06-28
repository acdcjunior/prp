
app.controller('PrevisaoListCtrl', ['$scope', 'PrevisoesFactory', 'PrevisaoFactory', '$location',
	function ($scope, PrevisoesFactory, PrevisaoFactory, $location) {
	
	    $scope.editPrevisao = function (pId) {
	        $location.path('/previsao-detail/' + pId);
	    };
	
	    $scope.deletePrevisao = function (pId) {
	    	PrevisaoFactory.delete({ id: pId });
	        $scope.previsoes = PrevisoesFactory.query();
	    };
	
	    $scope.createNewPrevisao = function () {
	        $location.path('/previsao-creation');
	    };
	
	    
	    $scope.previsoes = PrevisoesFactory.query();
	}
]);

app.controller('PrevisaoDetailCtrl', ['$scope', '$routeParams', 'PrevisaoFactory', '$location',
    function ($scope, $routeParams, PrevisaoFactory, $location) {
		$scope.updatePrevisao = function () {
			PrevisaoFactory.update($scope.previsao);
			$location.path('/previsao-list');
		};
	
		$scope.cancel = function () {
			$location.path('/previsao-list');
		};
	
		$scope.previsao = PrevisaoFactory.show({id: $routeParams.id});
	}
]);

app.controller('PrevisaoCreationCtrl', ['$scope', 'PrevisoesFactory', '$location',
    function ($scope, PrevisoesFactory, $location) {
	
		$scope.createNewPrevisao = function () {
			PrevisoesFactory.create($scope.previsao);
			$location.path('/previsao-list');
		};
	}
]);