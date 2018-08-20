app.controller('SkeletonCtrl', function ($rootScope, $location, $scope, $UserService) {
  $rootScope.user = {};
  $rootScope.user.isLoged = false;
  
  $rootScope.msg = {};

  /**Uso jquery compatibilidade com bootstrap */
 
  

  function getUser() {
    $UserService.getUserData().then(function (response) {
      /**Verifica se usuario esta logado */
      if (response.status == 200) {
        $rootScope.user = response.data;
        $rootScope.user.isLoged = true;
      } else {
       
      }
    })
  }

$rootScope.openLoginToggle = function(){
  $('#dropdown-toggle-login').dropdown('toggle');
}

$rootScope.newUser = function(){
  $("#modalNewUser").modal('show');
}

  /*Funções de uso geral do sistema*/
$rootScope.logIn = function (login) {
    $UserService.login(login).then(function (data) {
      $UserService.getUserData().then(function (response) {
        if (response.status == 200) {
           getUser();
           $('.dropdown.open').removeClass('open');
           $rootScope.loadMainContent('rooms');
        }else{
          $rootScope.user.isLoged = false;
          $rootScope.msg.errorLogin = "Falha no login, tente novamente!";
          $rootScope.openLoginToggle()
        }
      })
    })
  }

  $rootScope.logOut = function (login) {
    $UserService.logout();
    location.reload();
  }


  
  getUser();

  $rootScope.loadMainContent = function (template) {
    $rootScope.divmaincontent = './views/' + template + '.template.html';
  }
  $rootScope.loadMainContent('home');



  //Carregamento padrão
  //$rootScope.loadTemplate('./views/productsList.template.html');


  //renewNavs();
  // $rootScope.activetab = $location.path();
});

