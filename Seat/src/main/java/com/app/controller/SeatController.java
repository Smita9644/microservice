package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Screen;
import com.app.entity.Seat;
import com.app.exception.NotFoundException;
import com.app.repository.ScreenRepository;
import com.app.repository.SeatRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "SeatControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class SeatController {
	
	/** auto wired SeatRepository for accessing the methods of seat repository */
	@Autowired
	private SeatRepository repository;

	/**
	 * auto wired ScreenRepository for accessing the methods of screen repository
	 */
	@Autowired
	private ScreenRepository screenRepository;

	/**
	 * In this method we are trying to get status of all the seats of selected
	 * screen
	 * 
	 * @param screenId
	 * @return List of seats of selected screen else return empty list if screenId
	 *         is wrong it @throws NotFoundException
	 */

	@GetMapping("getAllSeats/{screenId}")
	@ApiOperation("Get all seats of selected scrren")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "status of seat", response = Screen.class) })
	public List<Seat> GetAllSeatStatusOfSelectedScreen(@PathVariable("screenId") int screenId)
			throws NotFoundException {
		// Here we check screen with given screen id is present screen table or not
		Screen screen = screenRepository.findById(screenId)
				.orElseThrow(() -> new NotFoundException("Unable to find screen with given screen id"));

		return repository.getAllSeatsOfSelectedScreen(screen);

	}

}
