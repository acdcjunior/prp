$(function() {
	
    function jsonPrevisao(json, divPopup) {
		var table = $('<table>');
		var soma = 0;
		jQuery.each(json, function (i, v) {
			var tr = $('<tr>');
			$('<td>').text(v.categoria.nome).appendTo(tr);
			$('<td>').text(v.descricao).appendTo(tr);
			$('<td>').text(v.valor).appendTo(tr);
			$('<td>').text(v.realizada).appendTo(tr);
			soma += v.valor;
			$('<td>').text(soma).appendTo(tr);
			tr.appendTo(table);
		});
		table.appendTo(divPopup);
    }
    
	var ano = parseInt($('#ano').text());
	
	$('.totalPrevisto').each(function(i, e) {
		$(e).click(function () {
			var mes = i+1;
			var url = window.prp.URL +  '/previsao/'+ano+'/'+mes;
			
			var celula = this;
			jQuery.getJSON(url, function (data) {
				divPopup(celula, function (div) {
					jsonPrevisao(data, div);
				});
			});
		});
	});
	
	
    function jsonMovimentacao(json, divPopup) {
		var table = $('<table>');
		var soma = 0;
		jQuery.each(json, function (i, v) {
			var tr = $('<tr>');
			$('<td>').text(new Date(v.data).format()).appendTo(tr);
			$('<td>').text(v.descricao1).appendTo(tr);
			$('<td>').text(v.realiza.descricao).appendTo(tr);
			$('<td>').text(v.valor).appendTo(tr);
			soma += v.valor;
			$('<td>').text(soma).appendTo(tr);
			tr.appendTo(table);
		});
		table.appendTo(divPopup);
    }
	
	$(document).on('click', '.celula', function (event) {
		if (!$(event.target).is('.valorNoMes,qtdMovimentacoes,qtdPrevisoes,td')) {
			return;
		}
		var celula = $(this);
		var categoriaId = celula.data('categoria');
		var ano = celula.data('ano');
		var mes = celula.data('mes');
		
		var previsoes_url = window.prp.URL +  '/previsao/' + categoriaId+'/'+ano+'/'+mes;
		var movimentacoes_url = window.prp.URL +  '/movimentacao/previsao/' + categoriaId + '/' + ano + '/' + mes;
		
		jQuery.getJSON(previsoes_url, function (previsoesJson) {
			jQuery.getJSON(movimentacoes_url, function (movimentacoesJson) {
				var celulaCategoriaNoMesClicada = celula.get(0); 
				divPopup(celulaCategoriaNoMesClicada, function (divPopup) {
					popupCelula(categoriaId, ano, mes, previsoesJson, movimentacoesJson, divPopup, celulaCategoriaNoMesClicada);
				});
			});
		});
	});
	
	$(document).on('click', '.naoCategorizada', function (event) {
		var celula = $(this);
		var ano = celula.data('ano');
		var mes = celula.data('mes');
		
		var movimentacoes_url = window.prp.URL +  '/movimentacao/semcategoria/' + ano + '/' + mes;
		
		jQuery.getJSON(movimentacoes_url, function (movimentacoesJson) {
			var celulaCategoriaNoMesClicada = celula.get(0); 
			divPopup(celulaCategoriaNoMesClicada, function (divPopup) {
				popupMovimentacoesSemCategoria(ano, mes, movimentacoesJson, divPopup);
			});
		});
	});
	
	function realizar(idPrevisao, imgStatus, celulaCategoriaNoMesClicada) {
		
		new prp.Previsao(idPrevisao).realizar(
			function (categoriaNoMes) {
				var imgOK = imgStatus.clone().attr('src', function (i, srcAnterior) {
            		return srcAnterior.replace('flat/binoculars_lock.png', 'yes16.png');
            	}).css('cursor', '').removeClass('clickavel');
				
				imgStatus.after(imgOK);
				imgStatus.remove();
            	
            	imgOK.after(icones.previsao.realizada());
            	
            	atualizarCelulaCategoriaNoMes(celulaCategoriaNoMesClicada, categoriaNoMes);
    		}
		);
		
	}
	
	function atualizarCelulaCategoriaNoMes(celulaCategoriaNoMes, categoriaNoMes) {
		var $celulaCategoriaNoMes = $(celulaCategoriaNoMes);
		
		$celulaCategoriaNoMes.find('.valorNoMes').text(categoriaNoMes.valor);
		$celulaCategoriaNoMes.find('.qtdMovimentacoes').text(categoriaNoMes.qtdMovimentacoes);
		$celulaCategoriaNoMes.find('.qtdPrevisoes').text(categoriaNoMes.qtdPrevisoes);
		
		if (categoriaNoMes.previsao) {
			$celulaCategoriaNoMes.addClass('previsao');
			$celulaCategoriaNoMes.attr('title', function (index, oldTitle) {
				return oldTitle.replace('realizado', 'previsto');
			});
		} else {
			$celulaCategoriaNoMes.removeClass('previsao');
			$celulaCategoriaNoMes.attr('title', function (index, oldTitle) {
				return oldTitle.replace('previsto', 'realizado');
			});
		}
	}
	
	function popupCelula(categoriaId, ano, mes, previsoesJson, movimentacoesJson, divPopup, celulaCategoriaNoMesClicada) {
		divPopup.append($('<h4>').append(icones.previsao.previsao()).append('Previsoes em '+ano+'-'+mes));
		
		var table = $('<table>');
		var soma = 0;
		jQuery.each(previsoesJson, function (i, previsao) {
			var tr = $('<tr>');
			$('<td>').text(previsao.categoria.nome).appendTo(tr);
			$('<td>').text(previsao.descricao).appendTo(tr);
			$('<td>').text(previsao.valor).appendTo(tr);
			
			var tdRealizada = $('<td>').appendTo(tr);
			if (previsao.realizada) {
				tdRealizada.append(icones.previsao.realizada());
			} else {
				var iconeRealizar = icones.previsao.realizar().click(function () {
					realizar(previsao.id, $(this), celulaCategoriaNoMesClicada);
				});
				tdRealizada.append(iconeRealizar);
			}
			
			soma += previsao.valor;
			$('<td>').text(soma).appendTo(tr);
			
			var tdAcoes = $('<td>').appendTo(tr);

			var iconeVisualizar = icones.previsao.visualizar();
			tdAcoes.append(iconeVisualizar);
			
			iconeVisualizar.wrap('<a href="'+window.prp.URL +  'previsao/'+previsao.id+'"></a>');
			
			tr.appendTo(table);
			
			var somaMov = 0;
			var trMov = $('<tr>');
			trMov.appendTo(table);
			var tableMov = $('<table>');
			$('<td colspan="4" style="padding-left: 25px;">').append(tableMov).appendTo(trMov);
			
			jQuery.each(movimentacoesJson, function (i, movimentacao) {
				if (movimentacao.realiza.id == previsao.id) {
					var tableMovTr = $('<tr>').appendTo(tableMov);
					$('<td>').text(new Date(movimentacao.data).format()).appendTo(tableMovTr);
					$('<td>').text(movimentacao.descricao1).appendTo(tableMovTr);
					$('<td>').text(movimentacao.valor).appendTo(tableMovTr);
					somaMov += movimentacao.valor;
					$('<td>').text(somaMov).appendTo(tableMovTr);
				}
			});
		});
		table.appendTo(divPopup);
		
		
		divPopup.append($('<hr>'));
		
		var linkAddPrv = $('<a>');
		linkAddPrv.attr('href', window.prp.URL + 'previsao/add?categoria=' + categoriaId + '&ano=' + ano + '&mes=' +mes);
		
		linkAddPrv.append(icones.previsao.adicionar().attr({alt: 'Adicionar nova previsao para ' + ano + '-' + mes + ' nesta categoria.'}).css({width: 48}));
		
		$('<div>').append(linkAddPrv).appendTo(divPopup);
	}
	
	
	
	function popupMovimentacoesSemCategoria(ano, mes, movimentacoes, divPopup) {
		var somaMov = 0;
		function construirLinhaMovimentacao(movimentacao) {
			var tr = $('<tr>');
			$('<td>').text(new Date(movimentacao.data).format()).appendTo(tr);
			$('<td>').text(movimentacao.descricao1).appendTo(tr);
			$('<td>').text(movimentacao.valor).appendTo(tr);
			somaMov += movimentacao.valor;
			$('<td>').text(somaMov).appendTo(tr);
			return tr;
		}
		
		$('<h4>').append('Movimentacoes sem categoria em '+ano+'-'+mes).appendTo(divPopup);
		$('<h6>').append('Total: '+movimentacoes.length).appendTo(divPopup);
		
		var tableMov = $('<table>');
		jQuery.each(movimentacoes, function (i, movimentacao) {
			var linha = construirLinhaMovimentacao(movimentacao);
			tableMov.append(linha);
		});
		tableMov.appendTo(divPopup);
	}

});
$(function () {
	
	var meses = $('.mes').map(function() {
		return $(this).text();
	}).get();
	var iniciais = $('.saldoInicial').map(function() {
		return parseFloat($(this).text());
	}).get();
	var finais = $('.saldoFinal').map(function() {
		return parseFloat($(this).text());
	}).get();
	
	 var lineChartData = {
	     labels : meses,
	     datasets : [
	         {
	             fillColor : "rgba(11,40,255,0.5)",
	             strokeColor : "rgba(11,40,255,1)",
	             pointColor : "rgba(11,40,255,1)",
	             pointStrokeColor : "#fff",
	             data : finais
	         },
	         {
	             fillColor : "rgba(210,255,210,0.5)",
	             strokeColor : "rgba(150,250,150,1)",
	             pointColor : "rgba(150,250,150,1)",
	             pointStrokeColor : "#fff",
	             data : iniciais
	         },
	         {
	             fillColor : "rgba(220,220,220,0.2)",
	             strokeColor : "rgba(120,120,120,1)",
	             pointColor : "rgba(220,220,220,0.8)",
	             pointStrokeColor : "#fff",
	             data : [0,0,0,0,0,0,0,0,0,0,0,0]
	         }
	     ]
	     
	 };

	var options = {
	         bezierCurve : false
	};
		 
	new Chart(document.getElementById("canvas").getContext("2d")).Line(lineChartData, options);
		    
});
$(document).on('click', '#resetsaldos', function () {
	$.ajax({
		type: 'GET',
		url: window.prp.URL + 'ano/resetsaldos',
		success: function() {
			alert('PRONTO! Recarregue a pagina para exibir as alteracoes.');
		},
		error: function() {
			alert('ERRO!');
		}
	});
});