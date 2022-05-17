package com.pepcus.apicrud.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pepcus.apicrud.exception.ResourceNoteFoundException;
import com.pepcus.apicrud.model.Book;
import com.pepcus.apicrud.model.User;
import com.pepcus.apicrud.repository.UserRepository;
import com.pepcus.apicrud.service.UserService;

/**
 * 
 * 
 * @author Rajkumar.Saad
 * @since 05/12/2022
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	/**
	 * To save the user details.
	 * 
	 * @param user
	 * @return user
	 */
	@Override
	public User saveUser(User user) {
		user.setRegistrationOn(new Date());
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Integer id) {
		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			throw new ResourceNoteFoundException("user", "id", id);
		}
	}

	/**
	 * Activate user
	 * 
	 * @param user
	 * @return user
	 */
	@Override
	public User activateOn(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNoteFoundException("User", "id", userId));
		if (!(user.getDeactivationON() == null)) {
			user.setDeactivationON(null);

			return userRepository.save(user);
		} else {
			throw new ResourceNoteFoundException("this user is already register", "id", userRepository);
		}

	}

	/**
	 * Deactivate user
	 * 
	 * @param user
	 * @return user
	 */
	public User deactivationOn(Integer userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNoteFoundException("User", "id", userId));
		if (user.getDeactivationON() == null) {
			user.setDeactivationON(new Date());

		}
		return userRepository.save(user);
	}
        
        /**
	 * issue book to user
	 * 
	 * @param user
	 * @return user
	 */
	@Override
	public User issueBook(Integer id, List<Book> bookList) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNoteFoundException(" user", "id", bookList));

		List<Book> existingBookList = existingUser.getBookList();
		for (Book book : bookList) {
			existingBookList.add(book);
		}

		existingUser.setBookList(existingBookList);
		return userRepository.save(existingUser);

	}
        
        /**
	 * for sumition of book  from user
	 * 
	 * @param user
	 * @return user
	 */
	@Override
	public User returnBook(Integer id, List<Book> bookList) {
		User existingUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNoteFoundException(" user", "id", bookList));
		List<Book> existingBookList = existingUser.getBookList();
		for (Book book : bookList) {
			existingBookList.remove(book);
		}

		existingUser.setBookList(existingBookList);
		return userRepository.saveAndFlush(existingUser);

	}

}