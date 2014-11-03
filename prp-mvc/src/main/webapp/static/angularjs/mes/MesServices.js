
services.factory('MesesFactory', function ($resource) {
    return $resource('/prp/rest/mes', {}, {
        query: { method: 'GET', isArray: true }
    });
});