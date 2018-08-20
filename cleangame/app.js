var app = angular.module('cleangame', ['ngRoute']);

app.constant('ApiPath', "http://127.0.0.1:8080");
//app.constant('ApiPath', "http://177.105.44.236:8080");

app.config(function ($routeProvider, $locationProvider) {
  // remove o # da url

  $locationProvider.html5Mode(true);

  $routeProvider
    // para a rota '/', carregaremos o template home.html e o controller 'HomeCtrl'
    .when('/', {
      templateUrl: './views/skeleton.template.html',
      controller: 'SkeletonCtrl',
    })
    // caso não seja nenhum desses, redirecione para a rota '/'
    .otherwise({ redirectTo: '/' });


   

});



