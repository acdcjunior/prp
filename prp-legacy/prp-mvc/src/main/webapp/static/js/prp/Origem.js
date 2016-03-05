/**
 * Classe Origem.
 */
prp.dependencias('prp.Ajax');

(function (prp) {
	
	prp.Origem = function (origemId) {
		this.id = origemId;
	};
	
	prp.Origem.URL = '/origem/';
	
	prp.Origem.prototype = {
		id : null,
		nome : null,
		alias : null
	};
	
	prp.Origem.findAll = function () {
		return prp.Ajax.getAgora(prp.Origem.URL);
	};
	
	prp.Origem.findUltimaMovimentacao = function (origemId) {
        var dados = prp.Ajax.getAgora(prp.Origem.URL + origemId + '/saldo');
        var ultimaMovimentacao = {
    		data: new Date(dados.UltimaMovimentacaoData).format(),
    		saldo: dados.UltimaMovimentacaoSaldo,
    		ordem: dados.UltimaMovimentacaoOrdem
        };
		return ultimaMovimentacao;
	};
	
})(prp);