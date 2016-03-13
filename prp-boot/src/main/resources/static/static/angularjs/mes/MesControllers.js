
app.controller('MesListController', ['$scope', '$routeParams', 'MesesFactory', 'MovimentacaoFactory', '$location', '$modal',
    function ($scope, $routeParams, MesesFactory, MovimentacaoFactory, $location, $modal) {

        $scope.filtroGeral = $routeParams.filtro;
        $scope.filtroMes = $routeParams.mes;

        if (typeof $scope.filtroMes === "undefined" && typeof $scope.filtroGeral === "undefined") {
            $scope.filtroMes = moment().format("YYYY-MM");
        }

        $scope.verConta = function (anoMes) {
            $location.search("origem", 1).search('mes', anoMes).path('/movimentacao-list/');
        };
        $scope.verFatura = function (anoMes) {
            $location.search("origem", 2).search('mes', anoMes).path('/movimentacao-list/');
        };

        $scope.meses = MesesFactory.query();

    }
]);