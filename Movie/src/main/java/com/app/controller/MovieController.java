package com.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.MediaType;
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

import com.app.entity.Movie;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;
import com.app.service.MovieService;
import com.app.specification.MovieSpecification;
import com.app.validation.ValidateMovie;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "MovieControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class MovieController {

	/** auto wired movie service for accessing the methods of MovieService */
	@Autowired
	MovieService movieService;

	/**
	 * We create object of ValidateMovie to apply validation to all fields of movie
	 * class
	 */
	ValidateMovie validateMovie = new ValidateMovie();

	/**
	 * Here we are creating movie with auto generated movieId
	 * 
	 * @param movie
	 * @return we return movie object
	 */
	@ApiOperation("create a new movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "movie created", response = Movie.class) })
	@PostMapping(value = "/upload")
	public Movie addMovie(@RequestBody Movie movie) throws InvalidDataException {
		// Here we validate all parameters of request body
		validateMovie.validateMovie(movie);
		return movieService.addMovie(movie);

	}

	/**
	 * In this method we are updating the movie from table
	 * 
	 * @param movie
	 * @return we return movie object
	 */
	@ApiOperation("updated the movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "movie updated", response = Movie.class) })
	@PutMapping("/updateMovie")
	public Movie updateMovie(@RequestBody Movie movie) throws InvalidDataException {
		// Here we validate all parameters of request body
		validateMovie.validateMovie(movie);
		return movieService.updateMovie(movie);
	}

	/**
	 * This method is for getting list of all the movies from table
	 * 
	 * @return list of movies.(if not present then empty list.)
	 */
	@ApiOperation("Get list of all the movies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All movie details", response = Movie.class) })
	@GetMapping("/movies")
	public List<Movie> getAllMovies() {
		return movieService.getMovies();
	}

	/**
	 * This method is for getting all the movies from table which have same
	 * format,category and language
	 * 
	 * @param format,category and language
	 * @return List of movies(if not present then empty list)
	 *      @throws NotFoundException
	 */
	@ApiOperation("Filter movies depending upon language,category and format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "All movies of selected language,category and format", response = Movie.class) })
	@GetMapping("/byall")
	public List<Movie> getAllMoviesOfSelectedType(@RequestParam(name = "category", required = false) String category,
			@RequestParam(name = "language", required = false) String language,
			@RequestParam(name = "format", required = false) String format)
			throws InvalidDataException, NotFoundException {
		MovieSpecification movieSpecification = new MovieSpecification();
		List<Movie> machines = movieService
				.getAllMoviesOfSelectedType(Specification.where(movieSpecification.hasLanguage(language))
						.and(movieSpecification.hasCategory(category)).and(movieSpecification.hasFormat(format)));
		if (machines == null)
			throw new NotFoundException("No record found");
		return machines;
	}

	/**
	 * This method is for getting list of distinct category from table
	 * 
	 * @return List of String having distinct category else empty list
	 */
	@ApiOperation("Get All disticnt category from movies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All distinct categories", response = String.class) })
	@GetMapping("/category")
	public List<String> getAllCategory() {
		return movieService.getAllcategory();
	}

	/**
	 * This method is for getting list of distinct language from table
	 * 
	 * @return List of String having distinct language else empty list
	 */
	@ApiOperation("Get All disticnt languages from movies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All distinct languages", response = String.class) })
	@GetMapping("/language")
	public List<String> getAllLanguage() {
		return movieService.getAllLanguage();
	}

	/**
	 * This method is for getting list of distinct format from table
	 * 
	 * @return List of String having distinct format else empty list
	 */
	@GetMapping("/format")
	@ApiOperation("Get All disticnt formats from movies")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "All distinct formats", response = String.class) })
	public List<String> getAllFormat() {
		return movieService.getAllFormat();
	}

	/**
	 * Find the movie record by movieId.
	 * 
	 * @param movieId
	 * @return movie record (if movie will found). otherwise @throws
	 *         NotFoundException
	 */
	@GetMapping("/movie/{movieId}")
	@ApiOperation("Get details of movie by id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "movie details", response = Movie.class) })
	public Movie getDetailsOfSelectedMovie(@PathVariable("movieId") int id) throws NotFoundException {
		return movieService.getDetailsOfSelectedMovie(id);

	}

	/**
	 * delete the movie record by movieId.
	 * 
	 * @param movieId
	 * @return String i.e movie with given movieId deleted(if movie will found).
	 *         otherwise @throws NotFoundException
	 */
	@DeleteMapping("/deleteMovie/{movieId}")
	@ApiOperation("Delete given movie")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "movie deleted", response = Movie.class) })
	public String deleteMovie(@PathVariable("movieId") int id) throws InvalidDataException, NotFoundException {
		return movieService.deletMovie(id);
	}
}