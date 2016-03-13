
services.factory('PrevisoesFactory', function ($resource) {
    return $resource('/rest/previsoes', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    });
});

services.factory('PrevisaoFactory', function ($resource) {
    return $resource('/rest/previsoes/:id', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT', params: {id: '@id'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    });
});