package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.entity.Screen;

/**
 * This is Screen repository, It will interact with database using
 * JpaRepository.
 */
public interface ScreenRepository extends CrudRepository<Screen, Integer> {
	/**
	 * This method is for getting all the shows of selected movie
	 * 
	 * @param movieId
	 * @return List of screen
	 */
	@Query(value = "select * from Screen m where m.movie_id=:movie", nativeQuery = true)
	List<Screen> getAllShowsOfSelectedMovie(int movie);

}
