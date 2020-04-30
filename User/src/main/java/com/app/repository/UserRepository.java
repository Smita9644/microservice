package com.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.app.entity.User;

/**
 * This is User repository, It will interact with database using JpaRepository.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
	/**
	 * This method is for validating user i.e to check user with given email and
	 * password is present or not
	 * @param String email and password
	 * @return User object
	 */
	@Query(value = "select * from User u where u.user_email = :userEmail and u.user_password=:password", nativeQuery = true)
	User ValidateUser(@Param("userEmail") String userEmail, @Param("password") String password);

}
