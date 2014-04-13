/**
 * Classe prp.previsao.Previsao.
 */
prp.dependencias('jQuery', 'moment');

(function (prp) {
	
	prp.previsao = prp.previsao || {};
	
	prp.previsao.Previsao = function (json) {
		if (json != null) {
			jQuery.extend(this, json);
		}
	};
	
	prp.previsao.Previsao.URL = 'previsao/';
	
	/** @type {Integer}		  */ prp.previsao.Previsao.prototype.id = undefined;
	/** @type {String}		  */ prp.previsao.Previsao.prototype.data = moment().format('YYYY-MM-DD');
	/** @type {String}		  */ prp.previsao.Previsao.prototype.descricao = "";
	/** @type {Number}		  */ prp.previsao.Previsao.prototype.valor = 0.0;
	/** @type {Boolean}		  */ prp.previsao.Previsao.prototype.realizada = false;
	/** @type {prp.Categoria} */ prp.previsao.Previsao.prototype.categoria = undefined;
	
})(prp);