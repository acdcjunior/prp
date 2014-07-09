/**
 * MovimentacaoController
 * 
 * Dependencias:
 * 	Movimentacao.js
 */
function MovimentacaoController($scope) {
	$scope.saldoCalculado = 0;
	
	$scope.controleMovimentacoes = 1;

    function incluirMovimentacao(novaMov, obs) {
        novaMov.__controle = $scope.controleMovimentacoes++;

        $scope.movimentacoes.push(novaMov);
        $scope.calcularSaldo();
        $scope.mensagens.unshift({msg: '@' + novaMov.__controle + ': ' + ' movimentacao ' +
            (obs !== undefined ? '(' + obs + ')' : '') + ' adicionada.'});
    }

    $scope.novaMovimentacao = function() {
		if ($scope.origemSelecionada == null) {
			alert('Selecione uma origem!');
			return;
		}
		var novaMov = new prp.movimentacao.Movimentacao();
        incluirMovimentacao(novaMov);
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


    // BOTAO IMPORTAR -------------------------------------------------------------------------------------------------
    function calcular(valorString) {
        var doubleValue = parseFloat(valorString.replace(".", "").replace(" D", "").replace(" C", "").replace(",", "."));
        switch(valorString[valorString.length-1]) {
            case "C":
                return doubleValue;
            case "D":
                return -doubleValue;
            default:
                throw new Error("Tipo de valor inesperado: "+ valorString[valorString.length-1]);
        }
    }

    $scope.importar = function () {
        var movs = prompt("Cole a lista de movimentacoes");
        if (movs != null && movs.length > 0) {
            var regex = /^(\d{2}\/\d{2}\/\d{4}) (\d+) (.*?) ([\d.,]+ [CD]) ([\d.,]+ [CD]) $/gm;
            var match;
            var novasMovimentacoes = [];
            while (match = regex.exec(movs)) {
                var novaMov = new prp.movimentacao.Movimentacao();
                novaMov.data = moment(match[1], "DD/MM/YYYY").format('YYYY-MM-DD');
                novaMov.numeroDocumento = match[2];
                novaMov.descricao1 = match[3];
                novaMov.valor = calcular(match[4]);
                novaMov.saldo = calcular(match[5]);
                novasMovimentacoes.push(novaMov);
            }
            for (var i = 0; i < novasMovimentacoes.length; i++) {
                var nMov = novasMovimentacoes[i];
                incluirMovimentacao(nMov, nMov.descricao1);
            }
        }
    };

    $scope.globalMovsCopiado = [];
    $scope.habilitarImportarGlobal = function () {
        $scope.globalMovsCopiado = globalMovs;
    }

    $scope.importarGlobal = function () {
        if (globalMovs != null && globalMovs.length > 0) {
            var novasMovimentacoes = [];
            for (var i = 0; i < globalMovs.length; i++) {
                var gMov = globalMovs[i];
                var novaMov = new prp.movimentacao.Movimentacao();

                novaMov.data = gMov.data;
                novaMov.numeroDocumento = gMov.numeroDocumento;
                novaMov.descricao1 = gMov.descricao1;
                novaMov.descricao2 = gMov.descricao2;
                novaMov.valor = gMov.valor;
                novaMov.saldo = gMov.saldo;
                novasMovimentacoes.push(novaMov);
            }
            for (var i = 0; i < novasMovimentacoes.length; i++) {
                var nMov = novasMovimentacoes[i];
                incluirMovimentacao(nMov, nMov.descricao1);
            }
        }
    };
	
}

tabIndent.render($('#source')[0]);
globalMovs = [];

function parse() {
    $('#erros').empty();
    var tr, td, colunasInvalidas, colunasInvalidasCount = 0, movs = [];

    var source = $('#source').val();
    var linhas = source.split(/\r?\n/);

    for(var i = 0; i < linhas.length; i++) {
        var colunas = linhas[i].split(/\t/);
        var data = colunas[0];
        var numDoc = colunas[1];
        var d1 = colunas[2];
        var d2 = colunas[3];
        var valor = colunas[4];
        var saldo = colunas[5];
        if (colunas.length < 5 || colunas.length > 6) {
            alert("Erro: numero de colunas inesperado: "+colunas.length+". Linha: "+i+". Espera-se 5 ou 6!");
            return;
        }
        if (colunas.length == 5) {
            colunas.splice(3, 0, ''); // insere '' na coluna 4, representando d2
        }
        colunasInvalidas = validarColunas(colunas[0], colunas[1], colunas[2],
                                          colunas[3], colunas[4], colunas[5]);
        colunasInvalidasCount += colunasInvalidas.length;
        tr = $('<tr>').appendTo('#output');
        for(var j = 0; j < 6; j++) {
            td = $('<td>').text(colunas[j]).appendTo(tr);
            if (colunasInvalidas.indexOf(j) > -1) {
                td.addClass('erro');
            }
        }
        movs.push({
            data: colunas[0],
            numeroDocumento: colunas[1],
            descricao1: colunas[2],
            descricao2: colunas[3],
            valor: paraDouble(colunas[4]),
            saldo: paraDouble(colunas[5])
        });
    }

    $('#erros').text('Total de erros encontrados: '+colunasInvalidasCount);

    if (colunasInvalidasCount === 0) {
        globalMovs = movs;
    } else {
        globalMovs = [];
    }

}

function paraDouble(valorString) {
    return parseFloat(valorString.replace(".", "").replace(",", "."));
}

function validarColunas(data, numDoc, d1, d2, valor, saldo) {
    var regexData = /^\d{4}(-\d{2}){2}$/;
    var regexNumero = /^\s*-?[\d.,]*\d+([,.]\d+)?\s*$/;

    var colunasInvalidas = [];
    if (!regexData.test(data)) { colunasInvalidas.push(0); }
    if (numDoc.length === 0) { colunasInvalidas.push(1); }
    if (d1.length === 0) { colunasInvalidas.push(2); }
    // d2 nao valida
    if (!regexNumero.test(valor)) {colunasInvalidas.push(4); }
    if (!regexNumero.test(saldo)) { colunasInvalidas.push(5); }
    return colunasInvalidas;
}

$('#parse').click(parse);