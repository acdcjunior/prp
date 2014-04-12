/**
 * Classe prp.movimentacao.Movimentacao.
 */
prp.dependencias('jQuery', 'prp.Ajax', 'prp.util.Mes');

(function (prp) {
	
	prp.movimentacao = prp.movimentacao || {};
	
	prp.movimentacao.Movimentacao = function (json) {
		if (json != null) {
			jQuery.extend(this, json);
		}
	};
	
	prp.movimentacao.Movimentacao.URL = 'movimentacao/';
	
	prp.movimentacao.Movimentacao.prototype.id = null; // int
	prp.movimentacao.Movimentacao.prototype.data = new Date().format(); // string
	prp.movimentacao.Movimentacao.prototype.numeroDocumento = ""; // string
	prp.movimentacao.Movimentacao.prototype.descricao1 = ""; // string
	prp.movimentacao.Movimentacao.prototype.descricao2 = ""; // string
	prp.movimentacao.Movimentacao.prototype.descricao3 = ""; // string
	prp.movimentacao.Movimentacao.prototype.valor = 0.0; // decimal
	prp.movimentacao.Movimentacao.prototype.origem = null; // Origem
	prp.movimentacao.Movimentacao.prototype.saldo = null; // decimal
	prp.movimentacao.Movimentacao.prototype.realiza = null; // Previsao
	prp.movimentacao.Movimentacao.prototype.anteriorId = null; // int
	
	prp.movimentacao.Movimentacao.prototype.getMes = function () {
		return new prp.util.Mes(this.data).getMes();
	};
	
	prp.movimentacao.Movimentacao.prototype.getAno = function () {
		return new prp.util.Mes(this.data).getAno();
	};
	
	prp.movimentacao.Movimentacao.prototype.getMesVO = function () {
		var ano = this.getAno();
		var mes = this.getMes();
		return new prp.util.Mes(ano, mes);
	};
	
	prp.movimentacao.Movimentacao.prototype.getPropertyMap = function () {
		var propertyMap = {};
		propertyMap['id'] = this.id;
		propertyMap['data'] = this.data;
		propertyMap['numeroDocumento'] = this.numeroDocumento;
		propertyMap['descricao1'] = this.descricao1;
		propertyMap['descricao2'] = this.descricao2;
		propertyMap['descricao3'] = this.descricao3;
		propertyMap['valor'] = this.valor;
		propertyMap['origem'] = this.origem ? this.origem.nome : "(Sem origem)";
		propertyMap['saldo'] = this.saldo;
		propertyMap['ordemSaldo'] = this.ordemSaldo;
		propertyMap['realiza'] = this.realiza ? this.realiza.descricao : "(Sem previsão realizada)";
		return propertyMap;
	};
	
})(prp);