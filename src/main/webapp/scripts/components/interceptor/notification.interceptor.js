 'use strict';

angular.module('airNavigationStudioApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-airNavigationStudioApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-airNavigationStudioApp-params')});
                }
                return response;
            }
        };
    });
