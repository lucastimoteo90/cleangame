app.controller('EasyRoomCtrl', function ($rootScope, $location, $scope,$RoomService, $EasyRoomService, $QuestionService) {
  
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





  $scope.panel = {}
  $scope.panel.time = 0;
  

  function loadResume(){
    $RoomService.getResume().then(function(response){
      $scope.resume = response.data;     
    })
  }

  function loadQuestion(){  
    loadResume();
    $scope.tip = null;
    $scope.panel.time = 0;  
    $EasyRoomService.getQuestion().then(function(response){
       if(response.data.id != null){
        $scope.question = response.data;
       }else{
         //$rootScope.loadMainContent('rooms/easy/congratulations')
       }
       
       console.log($scope.question)
    })
  }



  function moveStep1(){
    $scope.step2 = false;
    $scope.step1 = true;
    getQuestions();
  }

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


  $scope.getTip = function(){
    $QuestionService.getTip($scope.question.id).then(function(response){
      $scope.tip = response.data.tip
    })
  }

  $scope.skip = function(){
    $QuestionService.skip($scope.question.id).then(function(response){
      
      loadQuestion();
    })
  }

  $scope.restart = function(){
    $RoomService.restart().then(function(response){
      $rootScope.loadMainContent('rooms/easy/room')
    })
  }


  $scope.markAlternative = function(option){
     alternative = {};
     alternative.question = $scope.question.id;
     alternative.answer = $scope.question.answer;     
     alternative.md5answer = md5($scope.question.alternatives[option]);

     $QuestionService.markAlternative(alternative).then(function(response){
       alternative = response.data;
       
         loadQuestion();
       
  
     })
  }

  $scope.moveQuestionsTab = function(){
    if($scope.room.id != null){
      moveStep2()
    }
  }

  $scope.moveRoomTab = function(){
    if($scope.room.id != null){
      moveStep1()
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



  setInterval(function(){
    $scope.$apply(function () {
      $scope.panel.time++;
      if($scope.panel.time == 40){
        $scope.getTip();
      }
      if($scope.panel.time == 80){
        $scope.getTip();
      }


  });
   
  },1000)

  
 loadQuestion();
 loadResume();
  //Carregamento padrão
  //$rootScope.loadTemplate('./views/productsList.template.html');


  //renewNavs();
  // $rootScope.activetab = $location.path();
});

