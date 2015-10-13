'use strict';

angular.module('airNavigationStudioApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


