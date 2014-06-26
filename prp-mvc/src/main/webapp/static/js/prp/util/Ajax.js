/**
 * Classe utilitaria prp.Ajax; com funcoes estaticas para requisicoes Ajax.
 * 
 */
prp.dependencias('jQuery');

(function(prp) {
	
	prp.Ajax = {};
	
	function doAjax(metodo, url, dataParametro, callback, async) {
		if (url[0] == '/') {
			url = url.substring(1);
		}
		if (async == undefined) {
			async = true;
		}
		var data = dataParametro;
		if ($.type(data) != 'string') {
			data = JSON.stringify(dataParametro);
		}
		$.ajax(
			{
				type: metodo,
				url: window.prp.URL + url,
				data : data,
                dataType: (metodo === "GET" ? "json" : undefined),
				contentType: "application/json",
				success : function (data, textStatus, jqXHR) {
					callback(data, textStatus, jqXHR);
				},
				error : function (jqXHR, textStatus, errorThrown) {
		        	var msgErro = 'ERRO ao executar Ajax!' +
					    			'\nMetodo: '+ metodo +
					    			'\nURL: ' + url +
					    			'\ndata: ' + data + 
					    			'\n\nErro lancado: ' + errorThrown + ':\n' + jqXHR.responseText;
		        	console.error(msgErro);
		        	alert(msgErro);
				},
				async : async
			}
		);
	};
	
	function doAjaxAgora(metodo, url, dataParametro) {
		var returnValue = null;
		var doReturn = function(data) {
			returnValue = data;
		};
		doAjax(metodo, url, dataParametro, doReturn, false);
		return returnValue;
	}
	
	/**
	 * Executa um GET assincrono na URL passada.
	 */
	prp.Ajax.get = function (url, callback) {
		doAjax('GET', url, undefined, callback);
	};

	/**
	 * Executa um GET <b>sincrono</b> na URL passada.
	 */
	prp.Ajax.getAgora = function (url) {
		return doAjaxAgora('GET', url);
	};
	
	/**
	 * Executa um POST assincrono na URL passada.
	 */
	prp.Ajax.post = function (url, data, callback) {
		doAjax('POST', url, data, callback);
	};
	
	/**
	 * Executa um POST <b>sincrono</b> na URL passada.
	 */
	prp.Ajax.postAgora = function (url, data) {
		return doAjaxAgora('POST', url, data);
	};
	
	/**
	 * Executa um PUT assincrono na URL passada.
	 */
	prp.Ajax.put = function (url, data, callback) {
		doAjax('PUT', url, data, callback);
	};
	
	/**
	 * Executa um PUT <b>sincrono</b> na URL passada.
	 */
	prp.Ajax.putAgora = function (url, data) {
		return doAjaxAgora('PUT', url, data);
	};
	
	/**
	 * Executa um PATCH assincrono na URL passada.
	 */
	prp.Ajax.patch = function (url, data, callback) {
		doAjax('PATCH', url, data, callback);
	};
	
	/**
	 * Executa um DELETE assincrono na URL passada.
	 */
	prp.Ajax.del = function (url, callback) {
		doAjax('DELETE', url, undefined, callback);
	};

})(prp);