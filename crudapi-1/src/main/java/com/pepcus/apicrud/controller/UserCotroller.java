package com.pepcus.apicrud.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pepcus.apicrud.model.Book;
import com.pepcus.apicrud.model.User;
import com.pepcus.apicrud.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserCotroller {

	@Autowired
	private UserService userService;

	/*
	 * @Param user save user
	 */
	@PostMapping("/save")
	@Transactional
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {

		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
	}

	/*
	 * @Param user get user by id
	 */
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") Integer id) {
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
	}

	/*
	 * @Param deactivate user
	 */
	@PutMapping("/deactivate")
	public ResponseEntity<User> deactivateOn(@RequestParam Integer userId) {
		return new ResponseEntity<User>(userService.deactivationOn(userId), HttpStatus.CREATED);
	}

	/*
	 * @Param activate user
	 */
	@PutMapping("/activate")
	public ResponseEntity<User> activateOn(@RequestParam Integer userId) {
		return new ResponseEntity<User>(userService.activateOn(userId), HttpStatus.CREATED);
	}

	/*
	 * @Param issue book to user
	 */
	@PatchMapping("/issueBook")
	public ResponseEntity<User> issueBook(@RequestParam Integer id, @RequestBody List<Book> bookList) {

		return new ResponseEntity<User>(userService.issueBook(id, bookList), HttpStatus.OK);
	}
	
	/*
	 * @Param return book to user
	 */
	@PatchMapping("/returnBook")
	public ResponseEntity<User> returnBook(@RequestParam Integer id, @RequestBody List<Book> bookList) {

		return new ResponseEntity<User>(userService.returnBook(id, bookList), HttpStatus.OK);
	}
}
