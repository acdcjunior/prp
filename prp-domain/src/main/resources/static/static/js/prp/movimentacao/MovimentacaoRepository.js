/**
 * Classe prp.movimentacao.MovimentacaoRepository.
 */
prp.dependencias('jQuery', 'prp.Ajax', 'prp.movimentacao.Movimentacao');

(function (prp) {
	
	prp.movimentacao = prp.movimentacao || {};
	
	prp.movimentacao.MovimentacaoRepository = {};
	
	prp.movimentacao.MovimentacaoRepository.findById = function (idMovimentacao, asyncCallback) {
		var url = prp.movimentacao.Movimentacao.URL + idMovimentacao;
		return fetchSingleMovimentacao(url, asyncCallback);
	};
	
	prp.movimentacao.MovimentacaoRepository.save = function (movimentacao, asyncCallback) {
		if ($.type(movimentacao) == "array") {
			return prp.movimentacao.MovimentacaoRepository.saveList(movimentacao, asyncCallback);
		}
		var url = prp.movimentacao.Movimentacao.URL + movimentacao.id;
		if (asyncCallback == null) {
			return prp.Ajax.putAgora(url, movimentacao);
		}
		prp.Ajax.put(url, movimentacao, function (a, b, c) {
			asyncCallback(a, b, c);
		});
	};
	
	prp.movimentacao.MovimentacaoRepository.saveList = function (idOrigem, movimentacoes, asyncCallback) {
		var url = prp.Origem.URL + "/" + idOrigem;
		if (asyncCallback == null) {
			return prp.Ajax.postAgora(url, movimentacoes);
		}
		prp.Ajax.post(url, movimentacoes, function (a, b, c) {
			asyncCallback(a, b, c);
		});
	};
	
	prp.movimentacao.MovimentacaoRepository.findLimitrofesMes = function (ano, mes, asyncCallback) {
		var url = prp.movimentacao.Movimentacao.URL + "limitrofes/"+ano+"/"+mes;
		if (asyncCallback == null) {
			return prp.Ajax.getAgora(url);
		}
		prp.Ajax.get(url, asyncCallback);
	};
	
	prp.movimentacao.MovimentacaoRepository.findUltimaMovimentacaoByOrigem = function (idOrigem, asyncCallback) {
		var url = "origem/"+idOrigem+"/ultimamovimentacao";
		return fetchSingleMovimentacao(url, asyncCallback);
	};
	
	function fetchSingleMovimentacao(url, asyncCallback) {
		if (asyncCallback == null) {
			var propriedadesMovimentacao = prp.Ajax.getAgora(url);
			return (function(){ return new prp.movimentacao.Movimentacao(propriedadesMovimentacao); })();
		}
		prp.Ajax.get(url, function (propriedadesMovimentacao) {
			var movimentacao = new prp.movimentacao.Movimentacao(propriedadesMovimentacao);
			asyncCallback(movimentacao);
		});
	}
	
	
	
})(prp);