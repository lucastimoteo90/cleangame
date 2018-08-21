package com.demo;




import java.util.concurrent.Executor;

import javax.jms.ConnectionFactory;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

//import com.demo.repositories.QuestionRepository;
//import com.demo.repositories.RoomRepository;
//import com.demo.repositories.UserRepository;


//import io.jsonwebtoken.lang.Arrays;



@SpringBootApplication
@EnableAsync
@EnableJms
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
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		
		//JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);

        // Send a message with a POJO - the template reuse the message converter
       // System.out.println("Sending an email message.");
      //  jmsTemplate.convertAndSend("mailbox","teste");
   
		
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
    
    @Bean
    public JmsListenerContainerFactory<?> myFactory(ConnectionFactory connectionFactory,
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

    @Bean // Serialize message content to json using TextMessage
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    
    
    
	
    @Bean(name = "fileExecutor")
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("GithubLookup-");
        executor.initialize();
        return executor;
    }
    
    
}
