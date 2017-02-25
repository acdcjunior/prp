/**
 * Classe prp.Previsao.
 */
prp.dependencias('jQuery', 'prp.Ajax', 'prp.util.Mes', 'prp.Entity');

(function (prp) {

	prp.Previsao = function (id, carregarPropriedadesAgora) {
		prp.Entity.call(this, id, carregarPropriedadesAgora); // super
	};
	
	// herança
	prp.Previsao.prototype = Object.create(prp.Entity.prototype);
	prp.Previsao.prototype.constructor = prp.Previsao;
	
	prp.Previsao.URL = 'previsao/';
	
	prp.Previsao.prototype.realizar = function (callback) {
		prp.Ajax.put(this.getUrl(this.id + '/realizada'), 'true', callback);
	};
	
	prp.Previsao.prototype.excluir = function (callback) {
		prp.Ajax.del(this.getUrl(this.id), callback);
	};
	
	/* STATIC METHODS */
	
	prp.Previsao.getPrevisoes = function (mesVO, callback) {
		if (!(mesVO instanceof prp.util.Mes)) {
			throw new Error("Mes passado não é um prp.util.Mes: "+mesVO);
		}
		var url = new prp.Previsao().getUrl(mesVO.getAno() + '/' + mesVO.getMes());
		prp.Ajax.get(url, callback);
	};

})(prp);