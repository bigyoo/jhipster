'use strict';

jhipsterApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/foo', {
                    templateUrl: 'views/foos.html',
                    controller: 'FooController',
                    resolve:{
                        resolvedFoo: ['Foo', function (Foo) {
                            return Foo.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
