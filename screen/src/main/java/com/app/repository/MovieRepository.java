package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.entity.Movie;

/**
 * This is Movie repository, It will interact with database using JpaRepository.
 */
public interface MovieRepository extends JpaRepository<Movie, Integer>,JpaSpecificationExecutor<Movie>{

	
	/**
	 * Find all the distinct category
	 * 
	 * @return List of String
	 */
	@Query(value = "select  DISTINCT  c.movie_category  from Movie c", nativeQuery = true)
	List<String> getDistinctCategoty();

	/**
	 * Find all the distinct language
	 * 
	 * @return List of String
	 */
	@Query(value = "select  DISTINCT  c.movie_language  from Movie c", nativeQuery = true)
	List<String> getDistinctLanguage();

	/**
	 * Find all the distinct format
	 * 
	 * @return List of String
	 */
	@Query(value = "select  DISTINCT  c.movie_format  from Movie c", nativeQuery = true)
	List<String> getDistinctFormat();

	
}
