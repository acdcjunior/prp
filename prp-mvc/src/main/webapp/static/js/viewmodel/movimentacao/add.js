/**
 * MovimentacaoController
 * 
 * Dependencias:
 * 	Movimentacao.js
 */
function MovimentacaoController($scope) {
	$scope.saldoCalculado = 0;
	
	$scope.controleMovimentacoes = 1;
	
	$scope.novaMovimentacao = function() {
		if ($scope.origemSelecionada == null) {
			alert('Selecione uma origem!');
			return;
		}
		var novaMov = new prp.movimentacao.Movimentacao();
		novaMov.__controle = $scope.controleMovimentacoes++;

		$scope.movimentacoes.push(novaMov);
		$scope.calcularSaldo();
		$scope.mensagens.unshift({msg: '@'+ novaMov.__controle +': '+'Nova movimentacao (em branco) adicionada.'});
	};
	
	$scope.calcularSaldo = function() {
		if ($scope.movimentacoes.length == 0) {
			return;
		}
		$scope.saldoCalculado = $scope.movimentacoes[0].saldo - $scope.movimentacoes[0].valor;
		$.each($scope.movimentacoes, function (i, v) {
			$scope.saldoCalculado = (parseFloat($scope.saldoCalculado) + parseFloat(v.valor)).toFixed(2);
			v.saldoCalculado = $scope.saldoCalculado; 
		});
	};
	
	$scope.remover = function (mov) {
		if (confirm('Tem certeza que deseja excluir a movimentacao @'+mov.__controle+'?')) {
			var i = $scope.movimentacoes.indexOf(mov);
			$scope.movimentacoes.splice(i, 1)[0];
			$scope.mensagens.unshift({msg: '@'+ mov.__controle +': '+'apagada.'});
		}
		$('[role=tooltip]').remove();
	};
	
    $scope.subir = function(mov) {
        var i = $scope.movimentacoes.indexOf(mov);
        if (i > 0) {
        	var anterior = $scope.movimentacoes[i-1];
        	$scope.movimentacoes.splice(i-1, 2, mov, anterior);
        	$scope.mensagens.unshift({msg: '@'+ mov.__controle +': '+'subiu.'});
        }
    };
    
    $scope.descer = function(mov) {
    	var i = $scope.movimentacoes.indexOf(mov);
    	if (i < $scope.movimentacoes.length - 1) {
    		var posterior = $scope.movimentacoes[i+1];
    		$scope.movimentacoes.splice(i, 2, posterior, mov);
    		$scope.mensagens.unshift({msg: '@'+ mov.__controle +': '+'desceu.'});
    	}
    };
    
    $scope.copiarDataAnterior = function(mov) {
        var i = $scope.movimentacoes.indexOf(mov);
        var anterior = $scope.movimentacoes[i-1];
        $scope.mensagens.unshift({msg: '@'+ mov.__controle +': '+'Data anterior "'+anterior.data+'" copiada sobre "'+ mov.data + '".'});
        mov.data = anterior.data;
    };
    
    $scope.copiarDataSeguinte = function(mov) {
    	var i = $scope.movimentacoes.indexOf(mov);
    	var seguinte = $scope.movimentacoes[i+1];
    	$scope.mensagens.unshift({msg: '@'+ mov.__controle +': '+'Data seguinte "'+seguinte.data+'" copiada sobre "'+ mov.data + '".'});
    	mov.data = seguinte.data;
    };
    
    $scope.copiarPrevisaoAnterior = function(mov) {
    	var i = $scope.movimentacoes.indexOf(mov);
    	var anterior = $scope.movimentacoes[i-1];
    	$scope.mensagens.unshift({msg: '@'+ mov.__controle +': '+ 'Previsao anterior "'+anterior.realiza.descricao+'" (#'+anterior.realiza.id+') copiada sobre '+ (mov.realiza ? '"'+mov.realiza.descricao+'" (#'+mov.realiza.id+')' : '<vazia>') + '.'});
    	mov.realiza = anterior.realiza;
    };
    
    $scope.copiarPrevisaoSeguinte = function(mov) {
    	var i = $scope.movimentacoes.indexOf(mov);
    	var seguinte = $scope.movimentacoes[i+1];
    	$scope.mensagens.unshift({msg: '@'+ mov.__controle +': '+'Previsao seguinte "'+seguinte.realiza.descricao+'" (#'+seguinte.realiza.id+') copiada sobre '+ (mov.realiza ? '"'+mov.realiza.descricao+'" (#'+mov.realiza.id+')' : '<vazia>') + '.'});
    	mov.realiza = seguinte.realiza;
    };
    
    $scope.$watch('movimentacoes', function (newVal) {
    	$scope.calcularSaldo();
    }, true);
	
	/* ORIGENS */
	
	$scope.origens = prp.Origem.findAll();
	
	$scope.origemSelecionada = null;
	$scope.dataUltimaMovimentacao = null;
	$scope.saldoUltimaMovimentacao = 0;
	$scope.ordemUltimaMovimentacao = 0;
	
	$scope.$watch('origemSelecionada', function (newVal) {
		if ($scope.origemSelecionada != null) {
	    	var idOrigem = $scope.origemSelecionada;
	    	
	    	var ultimaMovimentacao = prp.movimentacao.MovimentacaoRepository.findUltimaMovimentacaoByOrigem(idOrigem);
	    	
        	$scope.dataUltimaMovimentacao = ultimaMovimentacao.data;
        	$scope.saldoUltimaMovimentacao = ultimaMovimentacao.saldo;
        	
        	$scope.movimentacoes.push(adicionarReadOnly(ultimaMovimentacao));
        	
        	$scope.calcularSaldo();
        	$scope.atualizaMesUltimaMovimentacao();
        	
        	$scope.mensagens.unshift({msg: "Origem #"+idOrigem+" selecionada."});
		}
	});
	
	function adicionarReadOnly(mov) {
		mov._readOnly = true;
		return mov;
	}
	
	$scope.movimentacoes = [];
	
	$scope.enviar = function () {
		if ($scope.origemSelecionada == null) {
			alert('Selecione uma origem!');
			return;
		}
		if ($scope.movimentacoes.length === 1) {
			alert('Nenhuma nova movimentacao foi criada!');
			return;
		}
		if (confirm("Ok para salvar, cancelar para desistir.")) {
			var movimentacoes = angular.toJson($scope.movimentacoes);
			
			var idOrigem = $scope.origemSelecionada;
			
			prp.movimentacao.MovimentacaoRepository.saveList(idOrigem, movimentacoes, function () {
				if (confirm('Salvamento concluido!\nDeseja ir para a pagina do mes das movimentacoes adicionadas agora?')) {
					var mes = new prp.util.Mes($scope.dataUltimaMovimentacao); 
					window.location.href = window.prp.URL + "movimentacao/"+mes.getAno()+"/"+mes.getMes();
				}
				$scope.mensagens.unshift({msg: "Salvamento concluído!"});
			});
		}
	};
	
	$scope.mesUltimaMovimentacao = "";
	
	$scope.atualizaMesUltimaMovimentacao = function() {
		if ($scope.dataUltimaMovimentacao == null) {
			$scope.mesUltimaMovimentacao = "";
		}
    	var mes = parseInt($scope.dataUltimaMovimentacao.split('-')[1]);
    	switch (mes) {
	        case 1: $scope.mesUltimaMovimentacao = "Janeiro".toUpperCase(); break;
	        case 2: $scope.mesUltimaMovimentacao = "Fevereiro".toUpperCase(); break;
	        case 3: $scope.mesUltimaMovimentacao = "Março".toUpperCase(); break;
	        case 4: $scope.mesUltimaMovimentacao = "Abril".toUpperCase(); break;
	        case 5: $scope.mesUltimaMovimentacao = "Maio".toUpperCase(); break;
	        case 6: $scope.mesUltimaMovimentacao = "Junho".toUpperCase(); break;
	        case 7: $scope.mesUltimaMovimentacao = "Julho".toUpperCase(); break;
	        case 8: $scope.mesUltimaMovimentacao = "Agosto".toUpperCase(); break;
	        case 9: $scope.mesUltimaMovimentacao = "Setembro".toUpperCase(); break;
	        case 10: $scope.mesUltimaMovimentacao = "Outubro".toUpperCase(); break;
	        case 11: $scope.mesUltimaMovimentacao = "Novembro".toUpperCase(); break;
	        case 12: $scope.mesUltimaMovimentacao = "Dezembro".toUpperCase(); break;
	        default: alert("!VALOR INVALIDO!: "+mes);
    	}
    };
    
    $scope.setPrevisaoRealizada = function (movimentacao, $event) {
    	var $td = $($event.target).closest('td');
		
		var callback = function(previsao) {
			$scope.$apply(function () {
				$scope.mensagens.unshift({msg: '@'+ movimentacao.__controle +': '+'Previsao "'+previsao.descricao+'" (#'+previsao.id+') setada sobre '+ (movimentacao.realiza ? '"'+movimentacao.realiza.descricao+'" (#'+movimentacao.realiza.id+')' : '<vazia>') + '.'});
				movimentacao.realiza = previsao;
			});
			
			$td.css({ backgroundColor : 'yellow' });
			$td.effect("pulsate", { times:3 }, 600, function() {
				$td.animate({ backgroundColor : 'white' }, 1000);
			});
		};
		
		new prp.movimentacao.AlterarPrevisaoDialog(movimentacao, callback);
	};
	
	/* MENSAGENS */
	$scope.mensagens = [];
	
}