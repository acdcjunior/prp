$(function() {
	
	$(".alteraRealiza").click(function () {
		var idMovimentacao = $(this).data('id');
		var celulaClicada = $(this).parent();
		
		var movimentacao = prp.movimentacao.MovimentacaoRepository.findById(idMovimentacao);
		
		var callback = function(previsao) {
			movimentacao.realiza = previsao;
			
			prp.movimentacao.MovimentacaoRepository.save(movimentacao, function () {
				celulaClicada.find('span').text(previsao.descricao);
				celulaClicada.find('img').data('previsao', previsao.id);
				
				celulaClicada.css({ backgroundColor : 'yellow' });
				celulaClicada.effect("pulsate", { times:3 }, 600, function() {
					celulaClicada.animate({ backgroundColor : 'white' }, 1000);
				});
			});
		};
		
		new prp.movimentacao.AlterarPrevisaoDialog(movimentacao, callback);
	});
	
});