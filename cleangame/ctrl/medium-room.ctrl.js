app.controller('MediumRoomCtrl', function ($rootScope, $location, $scope,$RoomService, $MediumRoomService) {
  
  label = {};
  label.newEasyRoomTitle = "New easy room";
  label.nameRoom = "Room name";
  label.descriptionRoom = "Room description";
  label.isPublicRoom = "Is public room?";
  label.btnCreateRoom = "Create!";
  label.newQuestion = "New Question"
  label.correctAlternative = "Correct alternative";
  label.fakeAlternative = "Fake alternative";
  label.btnInsertQuestion = "Insert question!"

  $scope.leader = {};

  $scope.label = label;

  $scope.step1 = true;
  $scope.step2 = false;

  $scope.question = {};
  $scope.questions = {};

  //Corrigir
  $scope.room = $RoomService.getActiveRoom();
  $EasyRoomService.setActiveRoom($RoomService.getActiveRoom());


 
  function moveStep2(){
    $scope.step2 = true;
    $scope.step1 = false;
    getQuestions();
  }
 

  function getQuestions(){
    $EasyRoomService.getQuestions().then(function(response){
      $scope.questions = response.data;
    })
  }

  $scope.moveQuestionsTab = function(){
    if($scope.room.id != null){
      moveStep2()
    }
  }

  $scope.createRoom = function(room){
    room.type = "EASY"
    $EasyRoomService.insertNewRoom(room).then(function(response){
      if(response.status==201){
        $EasyRoomService.setActiveRoom(response.data)
        moveStep2();
      }      
    })
  }

  $scope.insertQuestion = function(question){
    question.md5correct = md5(question.correct);
    $EasyRoomService.insertQuestion(question).then(function(response){
       $scope.question = angular.copy($scope.leader); 
       getQuestions()      
    })
  }

  $scope.editQuestion = function(question){
    $scope.question = question;
  }



  //Carregamento padrão
  //$rootScope.loadTemplate('./views/productsList.template.html');


  //renewNavs();
  // $rootScope.activetab = $location.path();
});

