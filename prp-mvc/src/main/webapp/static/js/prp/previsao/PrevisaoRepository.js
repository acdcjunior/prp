prp.dependencias('jQuery', 'prp.Ajax', 'prp.previsao.Previsao');

/**
 * @class prp.previsao.PrevisaoRepository
 */
(function (prp) {
	
	prp.previsao = prp.previsao || {};

	/**
	 * @public
	 * @constructor
	 */
	prp.previsao.PrevisaoRepository = {};
	
	/**
	 * @public
	 * @static
	 */
	prp.previsao.PrevisaoRepository.add = function (previsao, asyncCallback) {
		var url = prp.previsao.Previsao.URL;
		if (asyncCallback == null) {
			return prp.Ajax.putAgora(url, previsao);
		}
		prp.Ajax.put(url, previsao, function (a, b, c) {
			asyncCallback(a, b, c);
		});
	};
	
})(prp);