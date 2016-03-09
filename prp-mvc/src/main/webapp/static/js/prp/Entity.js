/**
 * Classe prp.Entity.
 * 
 */
prp.dependencias('jQuery', 'prp.Ajax');

(function (prp) {

	prp.Entity = function (id, carregarPropriedadesAgora) {
		this.id = id != null ? id : 0;
		this._propriedadesCarregadas = false;
		
		if (carregarPropriedadesAgora) {
			this.carregarPropriedadesAgora();
		}
	};
	
	// Exemplo: prp.Entity.URL = 'previsao/';
	
	prp.Entity.prototype.getUrl = function (suffix) {
		if (this.constructor.URL == null) {
			throw new Error("URL da entidade "+ this.constructor.name + " não definida!");
		}
		return this.constructor.URL + suffix;
	};
	
	prp.Entity.prototype.isPropriedadesCarregadas = function () {
		return this._propriedadesCarregadas;
	};
	
	/**
	 * Busca as propriedades AGORA, se nao foram buscadas ainda.
	 */
	prp.Entity.prototype.carregarPropriedadesAgora = function() {
		if (this.id == 0 || this._propriedadesCarregadas) {
			return;
		}
		var json = prp.Ajax.getAgora(this.getUrl(this.id));
		$.extend(this, json);
		this._propriedadesCarregadas = true;
	};
	
	
	/**
	 * Busca as propriedades, se nao foram buscadas ainda, e chama callback quando a busca eh terminada.
	 */
	prp.Entity.prototype.carregarPropriedades = function(callback) {
		if (this.id == 0) {
			return;
		}
		if (this._propriedadesCarregadas) {
			callback();
		}
		var self = this;
		var successCalback = function (json) {
			$.extend(self, json);
			self._propriedadesCarregadas = true;
			callback();
		};
		prp.Ajax.get(this.getUrl(this.id), successCalback);
	};
	
})(prp);