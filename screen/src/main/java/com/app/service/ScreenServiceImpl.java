package com.app.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.Movie;
import com.app.entity.Screen;
import com.app.entity.Seat;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;
import com.app.repository.MovieRepository;
import com.app.repository.ScreenRepository;
import com.app.repository.SeatRepository;

@Transactional
@Repository
public class ScreenServiceImpl implements ScreenService {

	/** auto wired MovieRepository for accessing the methods of movie repository */
	@Autowired
	private MovieRepository movieRepository;

	/**
	 * auto wired ScreenRepository for accessing the methods of screen repository
	 */
	@Autowired
	private ScreenRepository screenRepository;

	/** auto wired SeatRepository for accessing the methods of seat repository */
	@Autowired
	private SeatRepository seatRepository;

	/**
	 * In This method we get list of all the movies in the theater
	 * 
	 * @return List of Screens having movie id else Empty List
	 */
	@Override
	public List<Screen> getAllMoviesInTheater() {
		return (List<Screen>) screenRepository.findAll();
	}

	/**
	 * This method is for adding new movie in the theater with given date,time and
	 * movie id
	 * 
	 * @param int    movieId
	 * @param Date   dateOfShow
	 * @param String timeOfShow
	 * 
	 * @return Screen object if movie with given id is not present @throws
	 *         NotFoundException
	 */
	@Override
	public Screen addMovieToTheater(int id, Date date, String timeOfShow) throws NotFoundException {
		Movie movie = movieRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Unable to find movie with this movie id"));
		Screen screen = new Screen(movie, date, timeOfShow);
		List<Seat> seatList = new ArrayList<Seat>();
		for (int i = 1; i <= 10; i++) {
			Seat seatno = new Seat(screen, false);
			seatList.add(seatno);
		}
		seatRepository.saveAll(seatList);
		return screenRepository.save(screen);
	}

	/**
	 * This method is get all the shows of selected movie
	 * 
	 * @param movieId
	 * @return List of Screen(if found) else empty List if movie with is not
	 *         present@throws NotFoundException
	 * @throws NotFoundException 
	 */
	public List<Screen> getAllShowsOfSelectedMovie(int id) throws NotFoundException {
		Movie movie = movieRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Unable to find movie with this movie id"));
		List<Screen> t = screenRepository.getAllShowsOfSelectedMovie(id);
		return t;
	}

	/**
	 * This method is to get all available seats from given screen
	 * 
	 * @param screenId
	 * @return no of empty seats if screen with given screen id is not present
	 *         then @throws NotFoundException
	 */
	@Override
	public Integer getAvailableSeatsInSelectedScreen(int screenId) throws NotFoundException {
		// TODO Auto-generated method stub
		Screen screen = screenRepository.findById(screenId)
				.orElseThrow(() -> new NotFoundException("Unable to find seats with this screen id"));
		List<Seat> t = seatRepository.getAllSeatsOfSelectedScreen(screen);
		int count = 0;
		for (Seat seat : t) {
			if (seat.isStatus() == false) {
				count++;
			}
		}
		return count;
	}

	/**
	 * This method is to get the details of any screen in theater depending upon
	 * screen id
	 * 
	 * @param screenId
	 * @return Screen object if screen with given screen id is not present
	 *         then @throws NotFoundException
	 */
	@Override
	public Screen getScreenDetailsByScreenId(int screenId) throws NotFoundException {
		Screen screen = screenRepository.findById(screenId)
				.orElseThrow(() -> new NotFoundException("Unable to find screen with this screen id"));
		return screen;
	}

}
