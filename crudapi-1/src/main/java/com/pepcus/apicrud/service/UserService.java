package com.pepcus.apicrud.service;

import java.util.List;

import com.pepcus.apicrud.model.Book;
import com.pepcus.apicrud.model.User;

public interface UserService {
	  User saveUser(User user);
	  User getUserById(Integer id);
	  User activateOn (Integer id);
	  User deactivationOn(Integer id);
	  User issueBook(Integer id ,List<Book> bookList);
	  User returnBook(Integer id ,List<Book> bookList);
}