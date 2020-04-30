package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.app.entity.Screen;
import com.app.entity.User;
import com.app.exception.InvalidDataException;
import com.app.exception.InvalidUserException;
import com.app.exception.NotFoundException;
import com.app.service.UserService;
import com.app.validation.ValidateUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "UserControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
/** Using this annotation we mark this class as controller */
@RestController
public class UserController {
	
	/** auto wired userService for accessing the methods of user service */
	@Autowired
	UserService service;

	/** Here we create object of ValidateUser class to apply validations */
	ValidateUser validateUser = new ValidateUser();

	/**
	 * Create new user with auto generated id as a user_id
	 * 
	 * @param user
	 * @return User object
	 */
	@PostMapping("/addUser")
	@ApiOperation("create user ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "user created", response = User.class) })
	public ResponseEntity<User> addUser(@RequestBody User user) throws InvalidDataException {
		// Here we validate all the paramters of request body
		validateUser.validateUser(user);
		User userAdded = service.addUser(user);
		return new ResponseEntity<User>(userAdded, HttpStatus.CREATED);

	}

	/**
	 * In this method we are trying to delete user depending upon user_id
	 * 
	 * @param userId
	 * @return User object(if found)
	 * @throws NotFoundException
	 * 
	 */
	@DeleteMapping("/deleteUser/{userId}")
	@ApiOperation("delete user ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "user deleted", response = String.class) })
	public ResponseEntity<String> deleteUser(@PathVariable("userId") int id)
			throws InvalidDataException, NotFoundException {
		String response = service.deleteUser(id);
		return new ResponseEntity<String>(response, HttpStatus.OK);
	}

	/**
	 * Update the old user record.
	 * 
	 * @param user
	 * @return user object null (if the user_id not found).
	 * @throws NotFoundException 
	 */
	@PutMapping("/updateUser")
	@ApiOperation("update user ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "user updated", response = User.class) })
	public ResponseEntity<User> updateUser(@RequestBody User user) throws InvalidDataException, NotFoundException {
		// Here we validate all the paramters of request body
		validateUser.validateUser(user);
		User u = service.UpdateUser(user);
		return new ResponseEntity<User>(u, HttpStatus.OK);

	}

	/**
	 * In this method we are trying get all the details of specific user depending
	 * upon user_id
	 * 
	 * @param userId
	 * @return User object(if found)
	 * @throws NotFoundException
	 * 
	 */
	@GetMapping("/getById/{userId}")
	@ApiOperation("get user details by userId ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "user details", response = User.class) })
	public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId)
			throws InvalidDataException, NotFoundException {
		User user = service.getUserById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/**
	 * In this method we try to validate that user i.e we check the email and
	 * password of the user
	 * 
	 * @param String email
	 * @param String password
	 * @return User object(if it is correct) if email or pass is incorrect
	 *         throws @throws InvalidUserException
	 */
	@PostMapping("/login")
	@ApiOperation("validate user with given email and password")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "user validated", response = User.class) })
	public ResponseEntity<User> validateUser(@RequestParam("email") String email,
			@RequestParam("password") String password)
			throws InvalidDataException, NotFoundException, InvalidUserException {
		// Here we validate email and password
		validateUser.validateEmail(email);
		validateUser.validatePassword(password);
		User user = service.validateUser(email, password);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
