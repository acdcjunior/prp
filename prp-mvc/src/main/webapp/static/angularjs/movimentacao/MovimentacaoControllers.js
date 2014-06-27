
app.controller('MovimentacaoListCtrl', ['$scope', 'MovimentacoesFactory', 'MovimentacaoFactory', '$location',
    function ($scope, MovimentacoesFactory, MovimentacaoFactory, $location) {

        $scope.editMovimentacao = function (pId) {
            $location.path('/movimentacao-detail/' + pId);
        };

        $scope.deleteMovimentacao = function (pId) {
            MovimentacaoFactory.delete({ id: pId });
            $scope.movimentacoes = MovimentacoesFactory.query();
        };

        $scope.createNewMovimentacao = function () {
            $location.path('/movimentacao-creation');
        };

        $scope.movimentacoes = MovimentacoesFactory.query();
    }
]);

app.controller('MovimentacaoDetailCtrl', ['$scope', '$routeParams', 'MovimentacaoFactory', '$location',
    function ($scope, $routeParams, MovimentacaoFactory, $location) {
        $scope.updateMovimentacao = function () {
            MovimentacaoFactory.update($scope.movimentacao);
            $location.path('/movimentacao-list');
        };

        $scope.cancel = function () {
            $location.path('/movimentacao-list');
        };

        $scope.movimentacao = MovimentacaoFactory.show({id: $routeParams.id});
    }
]);

app.controller('MovimentacaoCreationCtrl', ['$scope', 'MovimentacoesFactory', '$location',
    function ($scope, MovimentacoesFactory, $location) {

        $scope.createNewMovimentacao = function () {
            MovimentacoesFactory.create($scope.movimentacao);
            $location.path('/movimentacao-list');
        };
    }
]);