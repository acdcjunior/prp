/**
 * Classe prp.Categoria.
 */
prp.dependencias('jQuery');

(function (prp) {

	prp.Categoria = function (categoriaId) {
		this.id = categoriaId;
	};
	
	prp.Categoria.prototype.excluir = function (successCallback) {
		$.ajax({
			type: 'DELETE',
			url: window.prp.URL + 'categoria/' + this.id,
			success : successCallback,
			error : function (jqXHR, textStatus, errorThrown) {
	        	alert('Erro ao excluir categoria.\n\n' + errorThrown + ':\n' + jqXHR.responseText);
			}
		});
	};
	
})(prp);