
app.controller('PrevisaoListCtrl', ['$scope', 'PrevisoesFactory', 'PrevisaoFactory', '$location',
	function ($scope, PrevisoesFactory, PrevisaoFactory, $location) {
	
	    $scope.editPrevisao = function (pId) {
	        $location.path('/previsao-detail/' + pId);
	    };
	
	    $scope.createNewPrevisao = function () {
	        $location.path('/previsao-creation');
	    };

	    $scope.previsoes = PrevisoesFactory.query();
	}
]);

app.controller('PrevisaoDetailCtrl', ['$scope', '$routeParams', 'PrevisaoFactory', '$location', '$timeout',
    function ($scope, $routeParams, PrevisaoFactory, $location, $timeout) {
		$scope.updatePrevisao = function () {
			PrevisaoFactory.update($scope.previsao);
            $scope.salvando = true;
            $timeout(function () {
                $location.path('/previsao-list/');
            }, 2000);
		};
	
		$scope.cancel = function () {
			$location.path('/previsao-list');
		};
	
		$scope.previsao = PrevisaoFactory.show({id: $routeParams.id});

        $scope.deletePrevisao = function () {
            if (confirm("Tem certeza que deseja excluir a previsao #"+$scope.previsao.id+" - '"+$scope.previsao.descricao+"'?")) {
                PrevisaoFactory.delete({ id: $scope.previsao.id });
                $scope.salvando = true;
                $timeout(function () {
                    $location.path('/previsao-list/');
                }, 2000);
            }
        };
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