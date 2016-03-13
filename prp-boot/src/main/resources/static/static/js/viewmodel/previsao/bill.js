prp.dependencias('jQuery', 'moment', 'prp.previsao.Previsao', 'prp.previsao.PrevisaoRepository');

var app = angular.module('myApp', ['ngGrid']);
app.controller('MyCtrl', function($scope) {
    $scope.myData = window.billPrevisoes;
    
    $scope.calc = function() {
        var x = 0;
        angular.forEach($scope.myData, function (row) {
          x = x + parseFloat(row.valor);
          (function (n) {
          row.total = n;
          })(x);
        });
      };
      
      $scope.calc();
      
      $scope.gridOptions = { data: 'myData' };
      
      $scope.recalc = function() {
        console.log($scope.gridOptions);
        var x = 0;
        angular.forEach($scope.gridOptions.$gridScope.renderedRows, function (row) {
          x = x + parseFloat(row.entity.valor);
          (function (n) {
          row.entity.total = n;
          })(x);
        });
      };
      
      $scope.$on('ngGridEventEndCellEdit', function () {
          $scope.recalc();
      });
      $scope.$on('ngGridEventSorted', function () {
          $scope.recalc();
      });
});