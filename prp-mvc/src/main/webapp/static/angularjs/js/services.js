var services = angular.module('prp.services', ['ngResource']);

services.factory('PrevisoesFactory', function ($resource) {
    return $resource('/prp/rest/previsoes', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST' }
    });
});

services.factory('PrevisaoFactory', function ($resource) {
    return $resource('/prp/rest/previsoes/:id', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT', params: {id: '@id'} },
        delete: { method: 'DELETE', params: {id: '@id'} }
    });
});