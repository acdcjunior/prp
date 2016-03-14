define('prp-mock', ['jQuery'], function() {
	
	window.prp = window.prp || {};
	
	/**
	 * Documenta e verifica as dependencias da classe.
	 * 
	 * Uso: prp.dependencias(jQuery, prp.Ok, prp.Ajax);
	 */
	prp.dependencias = function () {
	    for (var i = 0, n = arguments.length; i < n; i++) {
	    	var dependencia = arguments[i];
	    	var actual;
	    	try {
	    		actual = eval(dependencia);
	    	} catch(err) {
	    		console.error('Erro ao avaliar dependencia "'+dependencia+'".');
	    		console.info(err.stack);
	    		throw err;
	    	}
	        if (actual == null) {
	            var err = new ReferenceError('Impossivel continuar, dependencia "'+dependencia+'" eh null!');
	            console.info(err.stack);
	            throw err;
	        }
	
	    }
	};
	
	Date.prototype.format = function() {
		var yyyy = this.getFullYear().toString();
		var mm = (this.getMonth() + 1).toString(); // getMonth() is zero-based
		var dd = this.getDate().toString();
		return yyyy + '-' + (mm[1] ? mm : "0" + mm[0]) + '-' + (dd[1] ? dd : "0" + dd[0]);
	};
	
	Date.fromString = function(strDate) {
		var dateParts = strDate.split("-");
		return new Date(dateParts[0], (dateParts[1] - 1), dateParts[2]);
	};
	
});