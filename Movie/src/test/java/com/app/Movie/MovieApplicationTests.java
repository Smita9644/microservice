package com.app.Movie;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.app.controller.MovieController;
import com.app.entity.Movie;
import com.app.entity.Screen;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;
import com.app.repository.MovieRepository;
import com.app.repository.ScreenRepository;
import com.app.repository.SeatRepository;
import com.app.service.MovieService;
import com.app.specification.MovieSpecification;

@SpringBootTest
class MovieApplicationTests {

	/** auto wired MovieController for accessing the methods of movie controller */
	@Autowired
	MovieController controller;

	/**
	 * By using mockbean annotation we avoid changes in database while performing
	 * test cases
	 */
	@MockBean
	MovieRepository repository;

	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * In this method we are perform test cases for addmovie method of movie
	 * controller
	 */
	@Test
	void addMovie() throws NotFoundException, InvalidDataException {

		// if we pass all the filed of movie object correctly its get added into
		// database
		Movie movie = new Movie("Sahho", "Action", 3.8, "3D", "Tamil");
		when(repository.save(movie)).thenReturn(movie);
		assertEquals(movie, controller.addMovie(movie));

		// if we pass empty name for mavie then it throws exception
		Movie m = new Movie("", "Action", 3.8, "3D", "Tamil");
		Throwable ex = assertThrows(InvalidDataException.class, () -> controller.addMovie(m));

		// if we pass any filed incorrect then it throws exception
		Movie movieOne = new Movie("Sahho", "@Action", 3.8, "3D", "Tamil");
		Throwable exception = assertThrows(InvalidDataException.class, () -> controller.addMovie(movieOne));

	}

	/**
	 * In this method we are perform test cases for update movie method of movie
	 * controller
	 */
	@Test
	void updateMovie() throws NotFoundException, InvalidDataException {
		// When we pass all parameters correctly then its get updated
		Movie movie = new Movie(1, "Ek tha tiger", "Action", 3.8, "3D", "Hindi");
		when(repository.save(movie)).thenReturn(movie);
		assertEquals(movie, controller.addMovie(movie));

		// if we pass wrong parameter through object it throws exception
		Movie m = new Movie(1, "Ek tha tiger", "@@Action", 3.8, "3D", "Hindi");
		Throwable ex = assertThrows(InvalidDataException.class, () -> controller.updateMovie(m));

		// if we pass any empty parameter from object it throws exception
		Movie movieOne = new Movie(1, "", "Action", 3.8, "3D", "Hindi");
		Throwable exception = assertThrows(InvalidDataException.class, () -> controller.updateMovie(movieOne));

	}

	/**
	 * In this method we are perform test cases for getDetailsOfMovie method of
	 * movie controller
	 */
	@Test
	void getDetailsOfMovie() throws NotFoundException {
		// when we pass correct movie id it returns the details of that movie
		Movie movie = new Movie(1, "Ek tha tiger", "Action", 3, "2D", "Hindi");
		when(repository.findById(movie.getMovie_id())).thenReturn(Optional.of(movie));
		assertEquals(movie, controller.getDetailsOfSelectedMovie(1));

		// When we pass incorrect movie id it throws notFoundException
		Throwable exception = assertThrows(NotFoundException.class, () -> controller.getDetailsOfSelectedMovie(199999));
	}

	/**
	 * In this method we are perform test cases for deleteMovieById method of movie
	 * controller
	 */
	@Test
	void deleteMovieById() throws InvalidDataException, NotFoundException {
		// When we pass correct movie id then movie gets deleted
		Movie movie = new Movie(8, "Ek tha tiger", "Action", 3.8, "3D", "Hindi");
		when(repository.findById(8)).thenReturn(Optional.of(movie));
		doNothing().when(repository).deleteById(1);
		when(repository.findById(8)).thenReturn(Optional.of(movie));
		assertEquals("movie with given id 8 deleted", controller.deleteMovie(8));

		// When we pass incorrect movie id then method throws exception
		Throwable exception = assertThrows(NotFoundException.class, () -> controller.deleteMovie(111));

	}

	/**
	 * In this method we perform test cases on the method which filter the movies in
	 * the table
	 */
	@Test
	void getMoviesOfSelectedType() throws InvalidDataException, NotFoundException {
		// Here we are trying to filter movie with category as Action,Language as Hindi
		// and format As 3D
		// and we get successfully result i.e the movies which belongs in each parameter
		Movie movie = new Movie(1, "Ek tha tiger", "Action", 3.0, "2D", "Hindi");
		List<Movie> movies = new ArrayList<Movie>();
		movies.add(movie);

		MovieSpecification spec = new MovieSpecification();
		// Mockito.when(service.getAllMoviesOfSelectedType(spec.hasCategory("Action").and(spec.hasFormat("2D").and(spec.hasLanguage("Hindi"))))).thenReturn(movies);
		Specification<Movie> specs = Specification.where(spec.hasLanguage("Hindi")).and(spec.hasCategory("Action"))
				.and(spec.hasFormat("2D"));
		when(repository.findAll(specs)).thenReturn(movies);
		assertEquals(movies, controller.getAllMoviesOfSelectedType("Action", "Tamil", "3D"));

	}

}
