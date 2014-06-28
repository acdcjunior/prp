
app.controller('MovimentacaoListCtrl', ['$scope', '$routeParams', 'MovimentacoesFactory', 'MovimentacaoFactory', '$location',
    function ($scope, $routeParams, MovimentacoesFactory, MovimentacaoFactory, $location) {
        var parametroFiltroMes = $routeParams.anoMes;
        if (parametroFiltroMes) {
            $scope.filtroMes = parametroFiltroMes;
        } else {
            $scope.filtroMes = moment().format("YYYY-MM");
        }

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

app.controller('MovimentacaoDetailCtrl', ['$scope', '$routeParams', 'MovimentacaoFactory', '$location', '$modal', '$timeout',
    function ($scope, $routeParams, MovimentacaoFactory, $location, $modal, $timeout) {
        $scope.updateMovimentacao = function () {
            MovimentacaoFactory.update($scope.movimentacao);
            $scope.salvando = true;
            $timeout(function () {
                $location.path('/movimentacao-list/'+$scope.movimentacao.data.substring(0, 7));
            }, 2000);
        };

        $scope.cancel = function () {
            $location.path('/movimentacao-list');
        };

        $scope.movimentacao = MovimentacaoFactory.show({id: $routeParams.id});

        $scope.modalAlterarPrevisaoRealizada = function () {
            var modalInstance = $modal.open({
                templateUrl: 'movimentacao/movimentacao-detail-previsao.html',
                controller: 'ModalAlterarPrevisaoRealizadaCtrl',
                resolve: {
                    movimentacao: function () {
                        return $scope.movimentacao;
                    }
                }
            });
            modalInstance.result.then(function (previsaoSelecionada) {
                $scope.movimentacao.realiza = previsaoSelecionada;
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        };

    }
]);

app.controller('ModalAlterarPrevisaoRealizadaCtrl', ['$scope', '$modalInstance', '$location', 'PrevisoesFactory', 'movimentacao',
    function ($scope, $modalInstance, $location, PrevisoesFactory, movimentacao) {
        $scope.filtroPrevisaoMes = movimentacao.data.substring(0, 7);

        $scope.previsoes = PrevisoesFactory.query();

        $scope.previsaoAtual = movimentacao.realiza;

        $scope.selecionar = function (previsao) {
            $modalInstance.close(previsao);
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.editarPrevisao = function (pId) {
            $modalInstance.dismiss('cancel');
            $location.path('/previsao-detail/' + pId);
        };
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