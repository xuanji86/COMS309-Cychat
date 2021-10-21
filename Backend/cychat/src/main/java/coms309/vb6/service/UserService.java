package coms309.vb6.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import coms309.vb6.model.User;
import coms309.vb6.repository.UserRepository;

@Service
public class UserService {

	@Autowired UserRepository userRepository;
	
	public User findById(int id) {
		return userRepository.findById(id);
	}
	
	public List<User>findAll(){
		return userRepository.findAll();
	}
}
