package com.app.booking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.controller.BookingController;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;

@SpringBootTest
class BookingApplicationTests {

	/**
	 * auto wired BookingController for accessing the methods of booking controller
	 */
	@Autowired
	BookingController controller;

	/** In this test case we are trying to test the book Movie method */
	@Test
	void bookSeatsForSelectedMovie() throws InvalidDataException, NotFoundException, ParseException {

		// when we pass all the parameters correctly movie get booked gor selected seat
		List<Integer> seats = new ArrayList<Integer>();
		seats.add(5);
		assertEquals("booked", controller.bookTheMovie(1, 1, seats));

		// If we trying to book the seat which is already booked it throws exception
		seats.remove(0);
		seats.add(10);
		Throwable exception = assertThrows(InvalidDataException.class, () -> controller.bookTheMovie(1, 1, seats));

		// if we pass incorrect user id then it throws NotFoundException
		Throwable ex = assertThrows(NotFoundException.class, () -> controller.bookTheMovie(111, 1, seats));

		// if we pass incorrect screen id then it throws NotFoundException
		Throwable e = assertThrows(NotFoundException.class, () -> controller.bookTheMovie(1, 1343, seats));
	}

	/** Here we are trying to test cancel booking method */
	@Test
	void cancelBooking() throws NotFoundException {
		// when we pass correct booking id then the booking gets canceled
		assertEquals("booking with booking id 8 is canceled", controller.cancelTheBooking(8));

		// when we pass incorrect booking id then it throws NotFoundException
		Throwable e = assertThrows(NotFoundException.class, () -> controller.cancelTheBooking(111));
	}

}
