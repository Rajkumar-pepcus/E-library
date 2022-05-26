package com.pepcus.apicrud.controller;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.pepcus.apicrud.model.Student;
import com.pepcus.apicrud.model.User;
import com.pepcus.apicrud.service.impl.UserServiceImpl;

public class UserControllerTest {
  
	 @InjectMocks
	 private UserCotroller userCotroller;
	 
	 @Mock
	 private UserServiceImpl userServiceImpl;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testSaveStudent() throws Exception {
		User user = new User();
		 user.setId(1);
		 user.setName("");
		 user.setRegistrationOn(new Date());
		 user.getDeactivationON();
		 user.setCurrentBookIssue(null);
		 user.setBookList(null);
		
System.out.println("userCotroller: "+userCotroller);
		Mockito.when(userServiceImpl.saveUser(Mockito.any(User.class))).thenReturn(user);
		ResponseEntity<User> rs =userCotroller.saveUser(user);

		Assert.assertNotNull(rs);
	}
	
	@Test
	public void getUserByIdTest() throws Exception {
		User user = new User();
		 user.setId(1);
		 user.setName("");
		 user.setRegistrationOn(new Date());
		 user.getDeactivationON();
		 user.setCurrentBookIssue(null);
		 user.setBookList(null);
		System.out.println("studentController: " +  userCotroller);
		ResponseEntity<User> rs =  userCotroller.getUserById(1);
		Mockito.when(userServiceImpl.getUserById(1)).thenReturn(user);

		Assert.assertNotNull(rs);
	}
	
	public void testDeactivateOn() {
		User user = new User();
		 user.setId(1);
		 user.setName("Raj");
		 user.setRegistrationOn(new Date());
		 user.setDeactivationON(new Date());
		 user.setCurrentBookIssue(null);
		 user.setBookList(null);
		System.out.println("studentController: " +  userCotroller);
		ResponseEntity<User> rs =  userCotroller.deactivateOn(user.getId());
		Mockito.when(userServiceImpl.deactivationOn(2)).thenReturn(user);

	}
	
}

