
services.factory('MovimentacoesFactory', function ($resource) {
    return $resource('/rest/movimentacoes', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    });
});

services.factory('MovimentacaoFactory', function ($resource) {
    return $resource('/rest/movimentacoes/:id', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT', params: {id: '@id'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    });
});