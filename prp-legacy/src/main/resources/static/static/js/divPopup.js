function divPopup(pai, funcaoConteudo) {
	$('.divPopup').remove();
	
	var $pai = $(pai);

	var divPopup = $('<div>', {title: ""}).addClass("divPopup");
	divPopup.css({position: 'absolute'});
	
	var botaoFechar = $('<div>', {title: "Fechar", class:"divPopup-fechar", text: 'x'});
	botaoFechar.click(function (e){
		divPopup.remove();
	});
	
	divPopup.append(botaoFechar);
	
	funcaoConteudo(divPopup);
	
	divPopup.appendTo($pai);
	
	var altura = $pai.offset().top - 25 - divPopup.height();
	divPopup.css('top', altura);
}