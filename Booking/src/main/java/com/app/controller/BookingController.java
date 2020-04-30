package com.app.controller;

import org.springframework.http.MediaType;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.BookedSeat;
import com.app.entity.Booking;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;
import com.app.service.BookingService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "BookingControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
public class BookingController {

	/** auto wired booking service for accessing the methods of bookingService */
	@Autowired
	BookingService bookingService;

	/**
	 * In this method we are trying to book the movie depending upon userId and
	 * screen id and the no's of selected seat
	 * 
	 * @param userId
	 * @param screenId
	 * @param seat
	 * @return Object of booking having details BookingId and user Info(if
	 *         successful) if user or screen id is incorrect @throws
	 *         NotFoundException
	 */
	@GetMapping("/book")
	@ApiOperation("Book selected seats with given screenId and userId")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "seats booked", response = String.class) })
	public Booking bookTheMovie(@RequestParam("userId") int userid, @RequestParam("screenId") int screenId,
			@RequestParam("SeatList") List<Integer> seat) throws InvalidDataException, NotFoundException {
		return bookingService.bookTheMovie(userid, screenId, seat);
	}

	/**
	 * In this method we are canceling the booking with the help of bookingId
	 * 
	 * @param BookingId
	 * @return String i.e " booking with given id is cancelled " if booking with
	 *         given bookingId is not present @throw NotFoundException
	 * 
	 */
	@DeleteMapping("/deleteBooking/{bookingId}")
	@ApiOperation("cancel the booking with given booking id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "booking canceled", response = String.class) })
	public String cancelTheBooking(@PathVariable("bookingId") int bookingId) throws NotFoundException {
		return bookingService.deleteBooking(bookingId);
	}

}
