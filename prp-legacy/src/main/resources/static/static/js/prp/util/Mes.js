/**
 * Classe prp.util.Mes(ano, mes).
 * 
 */
prp.dependencias('jQuery');

(function (prp) {
	
	prp.util = prp.util || {};
	
	prp.util.Mes = function () {
		var ano, mes;
		if (arguments.length == 2) {
			ano = arguments[0];
			mes = arguments[1];
		} else {
			var data = arguments[0];
			if (jQuery.type(data) == "string") {
				data = Date.fromString(data);
			}
			mes = data.getMonth()+1;
			ano = data.getFullYear();
		}
		this._ano = ano;
		this._mes = mes;
	};

	prp.util.Mes.prototype.getProximoMes = function () {
		if (this._mes == 12) {
			return new prp.util.Mes(this._ano + 1, 1);
		}
		return new prp.util.Mes(this._ano, this._mes + 1);
	};
	
	prp.util.Mes.prototype.getMesAnterior = function () {
		if (this._mes == 1) {
			return new prp.util.Mes(this._ano - 1, 12);
		}
		return new prp.util.Mes(this._ano, this._mes - 1);
	};
	
	prp.util.Mes.prototype.toString = function () {
		return this._ano + '/' + this.getMesPorExtenso();
	};
	
	prp.util.Mes.prototype.getMesPorExtenso = function () {
		switch (this._mes) {
		    case 1: return "Janeiro";
		    case 2: return "Fevereiro";
		    case 3: return "Março";
		    case 4: return "Abril";
		    case 5: return "Maio";
		    case 6: return "Junho";
		    case 7: return "Julho";
		    case 8: return "Agosto";
		    case 9: return "Setembro";
		    case 10: return "Outubro";
		    case 11: return "Novembro";
		    case 12: return "Dezembro";
		    default: throw new Error('Mes invalido! '+this._mes);
		}
	};
	
	prp.util.Mes.prototype.getMes = function () {
		return this._mes;
	};
	
	prp.util.Mes.prototype.getAno = function () {
		return this._ano;
	};

})(prp);