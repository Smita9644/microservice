package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import com.app.entity.Movie;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;

public interface MovieService {
	/**
	 * Here we are creating movie with auto generated movie_id
	 * 
	 * @param movie
	 * @return we return movie object
	 */
	public Movie addMovie(Movie movie) throws InvalidDataException;

	/**
	 * This method is for getting list of all the movies from table
	 * 
	 * @return list of movies.(if not present then empty list.)
	 */
	public List<Movie> getMovies();

	
	/**
	 * This method is for getting all the movies from table which have same
	 * format,language and category
	 * 
	 * @param String format,language and format
	 * @return List of movies(if not present then empty list)
	 */
	public List<Movie> getAllMoviesOfSelectedType(Specification<Movie> spec)
			throws InvalidDataException;

	/**
	 * This method is for getting list of distinct category from table
	 * 
	 * @return List of String having distinct category else empty list
	 */
	public List<String> getAllcategory();

	/**
	 * This method is for getting list of distinct language from table
	 * 
	 * @return List of String having distinct language else empty list
	 */
	public List<String> getAllLanguage();

	/**
	 * This method is for getting list of distinct format from table
	 * 
	 * @return List of String having distinct format else empty list
	 */
	public List<String> getAllFormat();

	/**
	 * in this method we are updating the specific movie from table
	 * 
	 * @param movie
	 * @return we return movie object
	 */
	public Movie updateMovie(Movie movie) throws InvalidDataException;

	/**
	 * delete the movie record by movie_id.
	 * 
	 * @param movieId
	 * @return movie with give id movieId deleted(if user will found). otherwise
	 * @throws NotFoundException
	 */
	public String deletMovie(int movieId) throws InvalidDataException, NotFoundException;

	/**
	 * Find the movie record by movie_id.
	 * 
	 * @param movieId
	 * @return movie record (if movie will found). otherwise
	 * @throws NotFoundException
	 */
	Movie getDetailsOfSelectedMovie(int movieId) throws NotFoundException;

}