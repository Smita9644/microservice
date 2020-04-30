package com.app.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Movie;
import com.app.entity.Screen;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;
import com.app.service.ScreenService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "ScreenControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
/** Using this annotation we mark this class as controller */
@RestController
public class ScreenController {
	
	/** auto wired movie service for accessing the methods of MovieService */
	@Autowired
	private ScreenService screenService;

	/**
	 * In This method we get list of all the movies in the theater
	 * 
	 * @return List of Screens having movie id else Empty List
	 */
	@GetMapping("/getAllMovies")
	@ApiOperation("Get all movies in the theater")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "all movies in theater", response = Screen.class) })
	public List<Screen> getAllMoviesInTheater() {

		return screenService.getAllMoviesInTheater();
	}

	/**
	 * This method is for adding new movie in the theater with given date,time and
	 * movie id
	 * 
	 * @throws InvalidDataException
	 * @throws NotFoundException
	 */
	@PostMapping("/addMovieToTheater")
	@ApiOperation("Add movie in the theater")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "movie added in threater", response = Screen.class) })
	public Screen addMovieToTheater(@RequestParam("movie") int id, @RequestParam("dateOfShow") Date date,
			@RequestParam("timeOfShow") String timeOfShow) throws InvalidDataException, NotFoundException {
		return screenService.addMovieToTheater(id, date, timeOfShow);
	}

	/**
	 * This method is get all the shows of selected movie
	 * 
	 * @param screenId
	 * @return List of Screen(if found) else empty List
	 * @throws InvalidDataException
	 * @throws NotFoundException 
	 */
	@GetMapping("/AllShowsOfMovie/{movieId}")
	@ApiOperation("Get all shows of selected movie from theater")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "All shows details of selected movie", response = Screen.class) })
	public List<Screen> getAllShowsOfSelectedMovie(@PathVariable("movieId") int id) throws InvalidDataException, NotFoundException {
		return screenService.getAllShowsOfSelectedMovie(id);
	}

	/**
	 * This method is to get all available seats from given screen
	 * 
	 * @param screenId
	 * @return no of empty seats
	 * @throws InvalidDataException
	 * @throws NotFoundException
	 */
	@GetMapping("/AllAvailableSeats/{screenId}")
	@ApiOperation("Get all available seats of the selected seat")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "count of all available seats", response = Integer.class) })
	public Integer getAvailableSeatsInSelectedScreen(@PathVariable("screenId") int screen_id)
			throws InvalidDataException, NotFoundException {
		return screenService.getAvailableSeatsInSelectedScreen(screen_id);
	}

	/**
	 * This method is to get the details of any screen in theater depending upon
	 * screen id
	 * 
	 * @param screenId
	 * @return Screen object
	 * @throws InvalidDataException if screen id does't not exits @throws
	 *                              NotFoundException
	 */
	@GetMapping("/getScreenDetails/{screenId}")
	@ApiOperation("Get details of selected scrren")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Details of screen", response = Screen.class) })
	public Screen getScreenDetailsByScreenId(@PathVariable("screenId") int screenId)
			throws InvalidDataException, NotFoundException {
		return screenService.getScreenDetailsByScreenId(screenId);
	}
}