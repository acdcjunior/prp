prp.dependencias('jQuery', 'moment', 'prp.previsao.Previsao', 'prp.previsao.PrevisaoRepository');

function PrevisaoAddController($scope, $compile) {
	
	$scope.categorias = categorias;
	
	$scope.previsao = new prp.previsao.Previsao({categoria: categoriaInicial, data: dataInicial, descricao: descricaoInicial, valor: -100.55});
	
	$scope.desabilitar = function () { $scope.desabilitarTodos = true; };
	$scope.habilitar = function () { $scope.desabilitarTodos = false; };
	$scope.habilitar();
	
    $scope.dadosValidos = function () {
    	if ($.trim($scope.previsao.descricao).length == 0) {
    		alert("Descricao deve ser diferente de vazio!");
    		return false;
    	}
    	if ($scope.previsao.valor == null || $scope.previsao.valor.toString().match(/^-?[0-9.]+$/) === null) {
    		alert("Valor deve ser preenchido e conter apenas numeros!");
    		return false;
    	}
    	if ($scope.previsao.categoria === null) {
    		alert("Selecione uma categoria!");
    		return false;
    	}
    	return true;
    };
    
    $scope.salvar = function () {
    	$scope.gravar($scope.previsao.data);
    };
    
    // Map contendo as datas ja criadas, para desabilitar os botoes que as criaria de novo iguaizinhas
    $scope.mapDatasCriadas = {};

	$scope.gravar = function (dataRealizacao) {
		// verifica se dados sao validos
    	if (!moment(dataRealizacao, 'YYYY-MM-DD').isValid()) {
    		alert("A data "+dataRealizacao+" solicitaca eh invalida!");
    		return;
    	}
    	if (!$scope.dadosValidos()) {
    		return;
    	}
    	if ($scope.mapDatasCriadas[dataRealizacao]) {
    		alert('Data ja criada agora!');
    		return;
    	}
    	
    	$scope.mapDatasCriadas[dataRealizacao] = true;
    	
    	// formata os dados conforme necessario
    	var dataMoment = moment(dataRealizacao, 'YYYY-MM-DD');
		var descricao = $scope.previsao.descricao.replace("$MES", dataMoment.format('YYYY.MM'));
		descricao = descricao.replace("$mes", dataMoment.format('YYYY.MM'));
    	var previsao = new prp.previsao.Previsao({
    		categoria: {id: $scope.previsao.categoria.id},
    		data: dataRealizacao,
    		descricao: descricao,
    		valor: $scope.previsao.valor
    	});

    	// altera a UI, mostrando que uma gravacao estah em curso
    	$scope.desabilitar();
    	var divGravacao = $('<div>').appendTo('#status');
    	var imgLoading = $('<img>', {'src': window.prp.URL + "static/img/loading.gif", style: "margin-bottom: -3px; margin-right: 5px;", alt: "Aguarde..."}).appendTo(divGravacao);

    	// solicita a gravacao ao repositorio
    	prp.previsao.PrevisaoRepository.add(previsao, function (data, textStatus, jqXHR) {
    		imgLoading.attr('src', window.prp.URL + "static/img/pack/yes.png");
    		imgLoading.attr('alt', "Sucesso!");
    		
        	var ano = dataMoment.format('YYYY');
        	var previsaoCriada = jqXHR.getResponseHeader('Location');
        	
        	divGravacao.append('Previsao criada com sucesso para <b>'+dataMoment.format('YYYY-MM-DD')+'<b>: <i>"'+descricao+'"</i>.');
        	divGravacao.append('&nbsp;');
        	divGravacao.append('<a href="'+ previsaoCriada +'">Visualizar previsao criada.</a>');
        	divGravacao.append('&nbsp;');
        	divGravacao.append('<a href="'+ window.prp.URL + 'ano/' + ano + '">Ir para o balanco de '+ano+'.</a>');
        	
        	divGravacao.append('&nbsp;');
        	var mesPrev = moment(dataMoment).subtract('months', 1).format('YYYY-MM-DD');
        	var prev = '<button '+
        	' ng-click="gravar(\''+ mesPrev + '\')" '+
        	' class="ui-btn ui-mini ui-btn-inline ui-icon-carat-l ui-btn-icon-left" '+
        	' ng-class="{\'ui-disabled\': mapDatasCriadas[\''+mesPrev+'\']}" '+
        	' ng-disabled="mapDatasCriadas[\''+mesPrev+'\']">Criar para '+mesPrev+'</button>';
        	divGravacao.append($compile(prev)($scope));

        	divGravacao.append('&nbsp;');
        	var mesNext = moment(dataMoment).add('months', 1).format('YYYY-MM-DD');
        	var next = '<button '+
        	' ng-click="gravar(\''+ mesNext + '\')" '+
        	' class="ui-btn ui-mini ui-btn-inline ui-icon-carat-r ui-btn-icon-right" '+
        	' ng-class="{\'ui-disabled\': mapDatasCriadas[\''+mesNext+'\']}" '+
        	' ng-disabled="mapDatasCriadas[\''+mesNext+'\']">Criar para '+mesNext+'</button>';
        	divGravacao.append($compile(next)($scope));
        	
        	// faz scroll ateh o botao criado
            $('html, body').scrollTop(divGravacao.offset().top);
		});
    };
    
}