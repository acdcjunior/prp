
services.factory('MesesFactory', function ($resource) {
    return $resource('/rest/mes', {}, {
        query: { method: 'GET', isArray: true }
    });
});