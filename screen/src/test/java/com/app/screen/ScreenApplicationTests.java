package com.app.screen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.app.controller.ScreenController;
import com.app.entity.Movie;
import com.app.entity.Screen;
import com.app.entity.Seat;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;
import com.app.repository.MovieRepository;
import com.app.repository.ScreenRepository;
import com.app.repository.SeatRepository;
import com.netflix.discovery.converters.Auto;

@SpringBootTest
class ScreenApplicationTests {

	/**
	 * auto wired ScreenController for accessing the methods of screen controller
	 */
	@Autowired
	ScreenController controller;

	/**
	 * By using mockbean annotation we avoid changes in database while performing
	 * test cases
	 */
	@MockBean
	ScreenRepository repository;

	/**
	 * By using mockbean annotation we avoid changes in database while performing
	 * test cases
	 */
	@MockBean
	SeatRepository seatRepo;

	/** This test case is to check the available seats of selected screen */
	@Test
	void getAllAvailableSeatsOfSelectedScreen() throws InvalidDataException, NotFoundException {
		// when we pass correct screen id to get all available seats it return the count
		// of the empty seat
		Movie movie = new Movie(1, "Ek tha tiger", "Action", 3.8, "3D", "Hindi");
		Date date = new Date(12 / 03 / 2020);
		Screen screen = new Screen(1, movie, date, "9.00AM");
		List<Seat> seat = new ArrayList<>();
		seat.add(new Seat(1, screen, false));
		seat.add(new Seat(1, screen, false));
		seat.add(new Seat(1, screen, false));
		seat.add(new Seat(1, screen, false));
		when(seatRepo.getAllSeatsOfSelectedScreen(screen)).thenReturn(seat);
		when(repository.findById(1)).thenReturn(Optional.of(screen));
		assertEquals(4, controller.getAvailableSeatsInSelectedScreen(1));

		// When we pass incorrect screen id it throw exception
		Throwable exception = assertThrows(NotFoundException.class,
				() -> controller.getAvailableSeatsInSelectedScreen(111));

	}

	/**
	 * This test case are for the method that returns List of all the shows of
	 * selected movie
	 */
	@Test
	void getAllAvailableShowsOfSelectedMovie() throws InvalidDataException, NotFoundException, ParseException {
		// If we pass correct movie Id it returns List of all the shows of that movie
		Movie movie = new Movie(1, "Ek tha tiger", "Action", 3, "2D", "Hindi");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date date = format.parse("2020-12-03 00:00:00.0");
		Screen screen = new Screen(1, movie, date, "9.00AM");
		List<Screen> list = new ArrayList<>();
		list.add(screen);
		when(repository.getAllShowsOfSelectedMovie(1)).thenReturn(list);
		assertEquals(list, controller.getAllShowsOfSelectedMovie(1));

		// if we pass incorrect movieId it throws an exception
		Throwable exception = assertThrows(NotFoundException.class, () -> controller.getAllShowsOfSelectedMovie(111));

	}

	/** This method is for getting all the shows in the theater */
	@Test
	void getAllShowsInTheater() throws ParseException {
		// it returns the list of all the shows
		Movie movie = new Movie(1, "Ek tha tiger", "Action", 3, "2D", "Hindi");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date date = format.parse("2020-12-03 00:00:00.0");
		Screen screen = new Screen(1, movie, date, "9.00AM");
		List<Screen> list = new ArrayList<>();
		list.add(screen);
		when(repository.findAll()).thenReturn(list);
		assertEquals(list, controller.getAllMoviesInTheater());
	}

	/** This method is for getting details of selected screen */
	@Test
	void getDetailsOfScreenById() throws ParseException, InvalidDataException, NotFoundException {
		// When we pass correct screen id it returns the object of string
		Movie movie = new Movie(1, "Ek tha tiger", "Action", 3, "2D", "Hindi");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date date = format.parse("2020-12-03 00:00:00.0");
		Screen screen = new Screen(1, movie, date, "9.00AM");
		when(repository.findById(1)).thenReturn(Optional.of(screen));
		assertEquals(screen, controller.getScreenDetailsByScreenId(1));

		// when we pass incorrect screen id it throws exception
		Throwable exception = assertThrows(NotFoundException.class, () -> controller.getScreenDetailsByScreenId(111));
	}

	/** This method is for adding movie in theater */
	@Test
	void addMovieToScreen() throws ParseException, InvalidDataException, NotFoundException {
		// when we pass movie id date and time movie gets added in theater
		Movie movie = new Movie(1, "Ek tha tiger", "Action", 3, "2D", "Hindi");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
		Date date = format.parse("2020-12-03 00:00:00.0");
		Screen screen = new Screen(1, movie, date, "12.00AM");
		Screen s = new Screen(movie, date, "12.00AM");
		List<Seat> seatList = new ArrayList<Seat>();
		for (int i = 1; i <= 10; i++) {
			Seat seatno = new Seat(screen, false);
			seatList.add(seatno);
		}

		when(repository.save(s)).thenReturn(screen);
		when(seatRepo.saveAll(seatList)).thenReturn(seatList);
		assertEquals(screen, controller.addMovieToTheater(1, date, "12.00AM"));

		// when we pass incorrect movie id then it throws an exception
		Throwable exception = assertThrows(NotFoundException.class,
				() -> controller.addMovieToTheater(111, date, "9.00AM"));

	}

}
