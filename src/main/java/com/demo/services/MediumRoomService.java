package com.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	
	public MediumRoom insert(MediumRoom room) {
		room.setId(null);
		UserSS user = UserService.authenticated();
		if(user == null) {
			throw new AuthorizationException("Token Inválido");
		}	
	
		//Insere usuário que fez a requisição como administrador
		List<User> administrators = new ArrayList<User>();
		administrators.add(userService.findById(user.getID()));
		
		room.setAdministrators(administrators);
		
		return repository.save(room);
	}

}
