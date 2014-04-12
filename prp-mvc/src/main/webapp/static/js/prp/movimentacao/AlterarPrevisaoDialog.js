/**
 * Classe prp.movimentacao.AlterarPrevisaoDialog.
 *
 */
prp.dependencias('jQuery', 'prp.movimentacao.Movimentacao');

(function (prp) {
	
	prp.movimentacao = prp.movimentacao || {};
	
	prp.movimentacao.AlterarPrevisaoDialog = function (movimentacao, callback) {
		this.previsaoSelecionadaAtualmente = movimentacao.realiza ? movimentacao.realiza.id : 0;
		this.callback = callback;
		
		this.inicializarElementos();
		
		this.divPrevisaoAtualmenteSelecionada.empty();

		var self = this;
		$.each(movimentacao.getPropertyMap(), function (key, value) {
			$('<div>').append($('<span>').text(key + ': ')).append(value).appendTo(self.divPrevisaoAtualmenteSelecionada);
		});
	
		this.carregarPrevisoesParaMes(movimentacao.getMesVO());
		
		this.alterarPrevisaoRealizadaDialog.dialog("open");
	};
	
	prp.movimentacao.AlterarPrevisaoDialog.html = '\
		<div class="alterarPrevisaoRealizadaDialog" title="Realizar Previsao">	 \
			<div class="previsaoAtual"></div>									 \
			<div class="previsoesSelecao">										 \
				<div class="botoes">											 \
					<button class="mesAnterior">2013/Janeiro</button>			 \
					<span class="mesAtual">2013/Fevereiro</span>				 \
					<button class="proximoMes">2013/Marco</button>				 \
				</div>															 \
				<div class="listaPrevisoes"></div>								 \
			</div>																 \
		</div>';
		
	prp.movimentacao.AlterarPrevisaoDialog.prototype.carregarCSS = function () {
		$('<link>', {type : 'text/css', rel : 'stylesheet'}).appendTo('head').attr('href', window.prp.URL + '/static/js/prp/movimentacao/AlterarPrevisaoDialog.css');
	};
	
	prp.movimentacao.AlterarPrevisaoDialog.prototype.inicializarElementos = function () {
		this.alterarPrevisaoRealizadaDialog = $(prp.movimentacao.AlterarPrevisaoDialog.html).appendTo('body');
		
		this.divPrevisaoAtualmenteSelecionada = this.alterarPrevisaoRealizadaDialog.find('.previsaoAtual');
		this.botaoMesAnterior = this.alterarPrevisaoRealizadaDialog.find('.mesAnterior');
		this.botaoProximoMes = this.alterarPrevisaoRealizadaDialog.find('.proximoMes');
		this.spanMesAtual = this.alterarPrevisaoRealizadaDialog.find('.mesAtual');
		this.listaPrevisoes = this.alterarPrevisaoRealizadaDialog.find('.listaPrevisoes');
		
		this.carregarCSS();
		
		this.alterarPrevisaoRealizadaDialog.dialog({
			autoOpen : false,
			modal : true,
			width : 810,
			buttons : {
				'Cancelar' : function() {
					$(this).dialog("close");
				}
			}
		});
		
	};

	prp.movimentacao.AlterarPrevisaoDialog.prototype.carregarPrevisoesParaMes = function (mes) {
		var self = this;
		this.spanMesAtual.text(mes);
		
		var mesAnterior = mes.getMesAnterior();
		this.botaoMesAnterior.text(mesAnterior+" < ").off('click').click(function() {
			self.carregarPrevisoesParaMes(mesAnterior);
		});
		var mesSeguinte = mes.getProximoMes();
		this.botaoProximoMes.text(" > "+mesSeguinte).off('click').click(function() {
			self.carregarPrevisoesParaMes(mesSeguinte);
		});
		
		this.botaoMesAnterior.prop('disabled', true);
		this.botaoProximoMes.prop('disabled', true);
		this.listaPrevisoes.text('Carregando...');
		
		prp.Previsao.getPrevisoes(mes, function (previsoes) {
			self.botaoMesAnterior.prop('disabled', false);
			self.botaoProximoMes.prop('disabled', false);
			self.listaPrevisoes.empty();
			
			$.each(previsoes, function (index, previsao) {
				var divPrevisao = $('<div>').addClass('clickavel').text(previsao.descricao);
				self.listaPrevisoes.append(divPrevisao);

				if (self.previsaoSelecionadaAtualmente == previsao.id) {
					divPrevisao.addClass('previsaoSelecionadaAtualmente');
				}
				
				divPrevisao.click(function () {
					self.alterarPrevisaoRealizadaDialog.dialog("close");
					self.callback(previsao);
				});
			});
			self.listaPrevisoes.append('<br><br> Encontradas '+previsoes.length+' previsoes.');
		});

	};
	
	$(document).keyup(function(e) {
		$('.alterarPrevisaoRealizadaDialog:visible').each(function () {
			if (e.which == 37) { /* LEFT  */ $(this).find('.mesAnterior:enabled').click(); }
			if (e.which == 39) { /* RIGHT */ $(this).find('.proximoMes:enabled').click(); }
		});
	});
	
})(prp);