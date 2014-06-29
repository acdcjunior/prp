
app.controller('MovimentacaoListCtrl', ['$scope', '$routeParams', 'MovimentacoesFactory', 'MovimentacaoFactory', '$location', '$modal',
    function ($scope, $routeParams, MovimentacoesFactory, MovimentacaoFactory, $location, $modal) {
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

        $scope.selecionarMovimentacao = function (mov) {
            if (mov.selecionada) {
                mov.selecionada = false;
            } else {
                mov.selecionada = true;
            }
        };

        $scope.alterarMovimentacoesSelecionadas = function () {
            var modalInstance = $modal.open({
                templateUrl: 'movimentacao/movimentacao-list-alterar-selecionadas.html',
                controller: 'ModalAlterarMovimentacoesSelecionadasCtrl',
                resolve: {
                    movimentacoesSelecionadas: function () {
                        return $scope.movimentacoesSelecionadas;
                    }
                }
            });
            modalInstance.result.then(function () {
                // recarrega lista de movimentacoes
                // $scope.movimentacoes = MovimentacoesFactory.query();
            }, function () {
                // console.log('Modal dismissed at: ' + new Date());
            });
        };

        $scope.removerSelecoes = function (mov) {
            for (var i = 0; i < $scope.movimentacoesSelecionadas.length; i++) {
                $scope.movimentacoesSelecionadas[i].selecionada = false;
            }
        };
    }
]);

app.controller('ModalAlterarMovimentacoesSelecionadasCtrl',
    ['$scope', '$modalInstance', 'MovimentacaoFactory', '$location', '$modal', 'PrevisoesFactory', 'movimentacoesSelecionadas',
    function ($scope, $modalInstance, MovimentacaoFactory, $location, $modal, PrevisoesFactory, movimentacoesSelecionadas) {

        $scope.alts = {
            alterarDescricao2: false,
            alterarPrevisao: false,
            descricao2: movimentacoesSelecionadas[0].descricao2,
            previsaoSelecionada: movimentacoesSelecionadas[0].realiza
        };

        $scope.limparPrevisaoSelecionada = function () {
            $scope.alts.previsaoSelecionada = undefined;
        };

        $scope.salvar = function () {
            for (var i = 0; i < movimentacoesSelecionadas.length; i++) {
                var mov = movimentacoesSelecionadas[i];
                if ($scope.alts.alterarPrevisao) {
                    mov.realiza = $scope.alts.previsaoSelecionada;
                }
                if ($scope.alts.alterarDescricao2) {
                    mov.descricao2 = $scope.alts.descricao2;
                }
                if ($scope.alts.alterarPrevisao || $scope.alts.alterarDescricao2) {
                    MovimentacaoFactory.update(mov);
                }
            }
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };

        $scope.modalAlterarPrevisaoRealizada = function () {
            var modalInstance = $modal.open({
                templateUrl: 'movimentacao/movimentacao-detail-previsao.html',
                controller: 'ModalAlterarPrevisaoRealizadaCtrl',
                resolve: {
                    mesInicial: function () {
                        return movimentacoesSelecionadas[0].data.substring(0, 7);
                    },
                    previsaoAtual: function () {
                        return undefined;
                    }
                }
            });
            modalInstance.result.then(function (previsaoSelecionada) {
                $scope.alts.previsaoSelecionada = previsaoSelecionada;
            }, function () {
                console.log('Modal dismissed at: ' + new Date());
            });
        };
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
                    mesInicial: function () {
                        return $scope.movimentacao.data.substring(0, 7);
                    },
                    previsaoAtual: function () {
                        return $scope.movimentacao.realiza;
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

app.controller('ModalAlterarPrevisaoRealizadaCtrl', ['$scope', '$modalInstance', '$location', 'PrevisoesFactory', 'mesInicial', 'previsaoAtual',
    function ($scope, $modalInstance, $location, PrevisoesFactory, mesInicial, previsaoAtual) {

        $scope.filtroPrevisaoMes = mesInicial;
        $scope.previsaoAtual = previsaoAtual;

        $scope.previsoes = PrevisoesFactory.query();


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