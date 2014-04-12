var prp = prp || {};

/**
 * Documenta e verifica as dependencias da classe.
 * 
 * Uso: prp.dependencias('jQuery', 'prp.Ok', 'prp.Ajax');
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

function parseDate(input) {
	var parts = input.split('-');
	return new Date(parts[0], parts[1]-1, parts[2]); // months are 0-based
}

icones = {};

icones.previsao = {
	previsao   : function () { return $('<img>', {src: window.prp.URL + '/static/img/flat/binoculars.png'     , 'class': 'imagemIcone', alt: 'Previsao'}); },
	realizar   : function () { return $('<img>', {src: window.prp.URL + '/static/img/flat/binoculars_lock.png', 'class': 'imagemIcone clickavel', alt: 'Marcar como realizada'}); },
	realizada  : function () { return $('<img>', {src: window.prp.URL + '/static/img/flat/binoculars_shield.png', 'class': 'imagemIcone', alt: 'Previsao jah marcada como realizada'}); },
	adicionar  : function () { return $('<img>', {src: window.prp.URL + '/static/img/flat/binoculars_add.png' , 'class': 'imagemIcone clickavel', alt: 'Adicionar nova previsao'}); },
	visualizar : function () { return $('<img>', {src: window.prp.URL + '/static/img/flat/binoculars_zoom.png', 'class': 'imagemIcone clickavel', alt: 'Visualizar previsao'}); }
};

icones.categoria = {
	categoria : function () { return $('<img>', {src: window.prp.URL + '/static/img/flat/open_folder.png'      , 'class': 'imagemIcone', alt: 'Categoria'}); },
	excluir   : function () { return $('<img>', {src: window.prp.URL + '/static/img/flat/open_folder_close.png', 'class': 'imagemIcone clickavel', alt: 'Excluir Categoria'}); }
};

$(function () {
	$("#nav").menu({position: {at: "left bottom"}});
});
$(document).tooltip({
    items: "img, [title]",
    content : function() {
		var element = $(this);
		if (element.is("[title]")) {
			return element.attr("title");
		}
		if (element.is("img")) {
			return element.attr("alt");
		}
	}
});