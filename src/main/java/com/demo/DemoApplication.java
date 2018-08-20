package com.demo;



//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.demo.repositories.QuestionRepository;
//import com.demo.repositories.RoomRepository;
//import com.demo.repositories.UserRepository;


//import io.jsonwebtoken.lang.Arrays;



@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
	/*
	@Autowired
    private RoomRepository roomRepository;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private QuestionRepository questionRepository;

	
	*/
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
	
    /*
       Question question = new Question();
       question.setAsk("Pergunta de Teste");
       question.setAlternative1("alternative1");
       question.setAlternative2("alternative2");
       question.setAlternative3("alternative3");
       question.setAlternative4("alternative4");
       question.setRightAnswer(1);
       
       Optional<Room> room = roomRepository.findById(1);
       
       question.setRoom(room.get());
       room.get().getQuestions().add(question);
       //questionRepository.save(question);
       //roomRepository.save(room);
       
       
       
    	
/*	   Room r1 = new Room("Teste DB 2");
	   User u1 = new User(null,"lucastimoteo90", "lucas");
	   
	   r1.getAdministrators().addAll(Arrays.asList(u1));
	   r1.getMembers().addAll(Arrays.asList(u1));
	   
	   u1.getRoomsAdministrator().addAll(Arrays.asList(r1));
	   u1.getRoomsMember().addAll(Arrays.asList(r1));
	   
	   roomRepository.saveAll(Arrays.asList(r1));
	   userRepository.saveAll(Arrays.asList(u1));
	*/   
	}
	
}
