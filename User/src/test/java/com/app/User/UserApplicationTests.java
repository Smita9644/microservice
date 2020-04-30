package com.app.User;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;

import com.app.controller.UserController;
import com.app.entity.User;
import com.app.exception.InvalidDataException;
import com.app.exception.InvalidUserException;
import com.app.exception.NotFoundException;
import com.app.repository.UserRepository;

@SpringBootTest
class UserApplicationTests {
	/** auto wired UserController for accessing the methods of user controller */
	@Autowired
	private UserController controller;

	/**
	 * By using mockbean annotation we avoid changes in database while performing
	 * test cases
	 */
	@MockBean
	private UserRepository repository;

	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * In this method we test is the user is successfully added in database or not
	 * 
	 * @throws InvalidDataException
	 */
	@Test
	void addUSer() throws InvalidDataException {
		// when we pass correct parameters then user get added in database
		User user = new User("Vishwajit", "vishwt@gmail.com", "vishwajit", "user");
		when(repository.save(user)).thenReturn(user);
		assertEquals(new ResponseEntity<User>(user, HttpStatus.CREATED), controller.addUser(user));

		// when we pass incorrect name then it will throw exception
		User u = new User("@#s", "Sapana@gmail.com", "Sapana", "user");
		Throwable exception = assertThrows(InvalidDataException.class, () -> controller.addUser(u));

		// if we pass empty field through object it will throw exception
		User userOne = new User("", "Sapana@gmail.com", "Sapana", "user");
		Throwable ex = assertThrows(InvalidDataException.class, () -> controller.addUser(userOne));

		// if we pass incorrect email format it will throw exception
		User userTwo = new User("sapana", "Sapana", "Sapana", "user");
		Throwable exe = assertThrows(InvalidDataException.class, () -> controller.addUser(userOne));
	}

	/**
	 * In this method we test is the user is successfully added in database or not
	 * 
	 * @throws InvalidDataException
	 * @throws NotFoundException
	 */
	@Test
	void updateUser() throws InvalidDataException, NotFoundException {
		// if we pass correct parameters to this method then user get updated
		User user = new User(19, "Vishwajit", "vishwt@gmail.com", "vishwajit", "user");
		when(repository.save(user)).thenReturn(user);
		when(repository.findById(19)).thenReturn(Optional.of(user));
		assertEquals(new ResponseEntity<User>(user, HttpStatus.OK), controller.updateUser(user));

		// when we pass incorrect name then it will throw exception
		User u = new User("@#s", "Sapana@gmail.com", "Sapana", "user");
		u.setUser_id(12);
		Throwable exception = assertThrows(InvalidDataException.class, () -> controller.updateUser(u));

		// if we pass empty field through object it will throw exception
		User userOne = new User(12, "", "Sapana@gmail.com", "Sapana", "user");
		Throwable ex = assertThrows(InvalidDataException.class, () -> controller.updateUser(userOne));
	}

	/**
	 * In this test case we tried to validate user by passing email id and password
	 * of the user
	 * 
	 * @throws InvalidDataException
	 * @throws InvalidUserException
	 */
	@Test
	void validateUser() throws NotFoundException, InvalidDataException, InvalidUserException {

		// if we login through the user which does not exits it throws exception
		User user = new User(2, "Smita", "smita@gmail.com", "smita", "user");
		Throwable exception = assertThrows(InvalidUserException.class,
				() -> controller.validateUser("Pranali332@gmail.com", "Pranali"));

		// if we pass email id and password of existing user the it returns the user
		User user1 = new User(20, "Pranali Patil", "Pranali@gmail.com", "Pranali", "user");
		when(repository.ValidateUser("Pranali@gmail.com", "Pranali")).thenReturn(user1);
		assertEquals(new ResponseEntity<User>(user1, HttpStatus.OK),
				controller.validateUser("Pranali@gmail.com", "Pranali"));

		// when we pass empty email and password
		Throwable ex = assertThrows(InvalidDataException.class, () -> controller.validateUser("", ""));
	}

	/** In this test case we tried to delete user from database */
	@Test
	void deleteUserById() throws NotFoundException, InvalidDataException {
		// when we pass correct userId for this method it return string that user is
		// deleted
		User user = new User(20, "Pranali Patil", "Pranali@gmail.com", "Pranali", "user");
		doNothing().when(repository).deleteById(20);
		when(repository.findById(20)).thenReturn(Optional.of(user));
		assertEquals(new ResponseEntity<String>("User with id 20 is deleted", HttpStatus.OK),
				controller.deleteUser(20));

	}

	/** In this test case we tried to delete user from database */
	@Test
	void deleteUser() throws InvalidDataException, NotFoundException {
		// when we pass incorrect userId for this method it return string that user is
		Throwable exception = assertThrows(NotFoundException.class, () -> controller.deleteUser(122));
	}
}
