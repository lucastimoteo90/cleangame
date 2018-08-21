app.controller('MediumRoomCtrl', function ($rootScope, $location, $scope,$RoomService, $MediumRoomService) {
  
  label = {};
  label.newEasyRoomTitle = "New medium room";
  label.nameRoom = "Room name";
  label.descriptionRoom = "Room description";
  label.isPublicRoom = "Is public room?";
  label.btnCreateRoom = "Create!";
  label.git = "Git clone:"

  $scope.leader = {};

  $scope.label = label;

  $scope.step1 = true;
  $scope.step2 = false;

  $scope.question = {};
  $scope.questions = {};

  //Corrigir
  $scope.room = $RoomService.getActiveRoom();
  $MediumRoomService.setActiveRoom($RoomService.getActiveRoom());


 
  function moveStep2(){
    $scope.step2 = true;
    $scope.step1 = false;
    getQuestions();
  }
 
  $scope.createRoom = function(room){
    room.type = "MEDIUM"
    $MediumRoomService.insertNewRoom(room).then(function(response){
      if(response.status==201){
        $MediumRoomService.setActiveRoom(response.data)
        moveStep2();
      }      
    })
  }

  



  //Carregamento padrão
  //$rootScope.loadTemplate('./views/productsList.template.html');


  //renewNavs();
  // $rootScope.activetab = $location.path();
});

