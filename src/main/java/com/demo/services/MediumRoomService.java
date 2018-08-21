package com.demo.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.demo.domain.EasyRoom;
import com.demo.domain.GitClone;
import com.demo.domain.MediumRoom;
import com.demo.domain.Room;
import com.demo.domain.User;
import com.demo.repositories.RoomRepository;
import com.demo.security.UserSS;
import com.demo.services.exception.AuthorizationException;
import com.demo.services.exception.ObjectNotFoundException;

@Service
public class MediumRoomService {

	@Autowired
	private RoomRepository repository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private ApplicationContext context;

	public MediumRoom insert(MediumRoom room) {
		room.setId(null);
		UserSS user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Token Inválido");
		}

		// Insere usuário que fez a requisição como administrador
		List<User> administrators = new ArrayList<User>();
		administrators.add(userService.findById(user.getID()));

		room.setAdministrators(administrators);

		repository.save(room);
		
		JmsTemplate jmsTemplate = context.getBean(JmsTemplate.class);
		
		GitClone gitClone = new GitClone();
		gitClone.setRoom(room);
		gitClone.setUserid(user.getID());
		
		jmsTemplate.convertAndSend("gitClone",gitClone);
		
		return repository.save(room);
	}

	

	public MediumRoom findById(Integer id) throws ObjectNotFoundException {
		Optional<Room> obj = repository.findById(id);
		return (MediumRoom) obj.orElseThrow(() -> new ObjectNotFoundException("User id not find"));
	}

}
