package com.mockito.tests;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import coms309.vb6.model.User;
import coms309.vb6.repository.UserRepository;
import coms309.vb6.service.UserService;

public class TestUserService {

	@InjectMocks
	UserService userService;
	
	@Mock
	UserRepository userRepository;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getUserByIdTest() {
		when(userRepository.findById(1)).thenReturn(new User("Anji", "Xu", "axu@iastate.edu", "coms309"));

		User user = userService.findById(1);

		assertEquals("Anji", user.getFirstName());
		assertEquals("Xu", user.getLastName());
		assertEquals("axu@iastate.edu", user.getEmail());
		assertEquals("coms309", user.getPassword());	
	}

	@Test
	public void findAllTest() {
		List<User> userlist = new ArrayList<User>();
		User user1 = new User("Anji", "Xu", "axu@iastate.edu", "coms309");
		User user2 = new User("Xiwen", "Zhang", "xiwenz@iastate.edu", "coms309");
		User user3 = new User("Charles", "Lin", "clin@iastate.edu", "coms309");
		
		userlist.add(user1);
		userlist.add(user2);
		userlist.add(user3); 
		
		when(userRepository.findAll()).thenReturn(userlist);
		List<User>users = userService.findAll();
		assertEquals(3, users.size());
		verify(userRepository, times(1)).findAll();
		
	}		
	

}
