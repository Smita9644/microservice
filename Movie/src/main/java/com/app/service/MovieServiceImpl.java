package com.app.service;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.app.entity.Movie;
import com.app.entity.Screen;
import com.app.entity.Seat;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;
import com.app.repository.MovieRepository;
import com.app.repository.ScreenRepository;
import com.app.repository.SeatRepository;
import com.app.validation.ValidateMovie;

@Transactional
@Repository
public class MovieServiceImpl implements MovieService {

	/**
	 * auto wired ScreenRepository for accessing the methods of screen repository
	 */
	@Autowired
	private ScreenRepository screenRepository;

	/** auto wired SeatRepository for accessing the methods of seat repository */
	@Autowired
	private SeatRepository seatRepository;

	/** auto wired MovieRepository for accessing the methods of movie repository */
	@Autowired
	MovieRepository movieRepository;

	/**
	 * Here we are creating movie with auto generated movie_id
	 * 
	 * @param movie
	 * @return we return movie object
	 */
	@Override
	public Movie addMovie(Movie movie) throws InvalidDataException {
		return this.movieRepository.save(movie);

	}

	/**
	 * This method is for getting list of all the movies from table
	 * 
	 * @return list of movies.(if not present then empty list.)
	 */
	@Override
	public List<Movie> getMovies() {
		// TODO Auto-generated method stub
		return (List<Movie>) this.movieRepository.findAll();
	}


	/**
	 * This method is for getting list of distinct category from table
	 * 
	 * @return List of String having distinct category else empty list
	 */
	@Override
	public List<String> getAllcategory() {
		return movieRepository.getDistinctCategoty();

	}

	/**
	 * This method is for getting list of distinct language from table
	 * 
	 * @return List of String having distinct language else empty list
	 */
	@Override
	public List<String> getAllLanguage() {
		return movieRepository.getDistinctLanguage();

	}

	/**
	 * This method is for getting list of distinct format from table
	 * 
	 * @return List of String having distinct format else empty list
	 */
	@Override
	public List<String> getAllFormat() {
		return movieRepository.getDistinctFormat();
	}

	/**
	 * This method is for getting all the movies from table which have same
	 * format,language and category
	 * 
	 * @param String format,language and format
	 * @return List of movies(if not present then empty list)
	 */
	@Override
	public List<Movie> getAllMoviesOfSelectedType(Specification<Movie> spec)
			throws InvalidDataException {
		return movieRepository.findAll(spec);

	}


	/**
	 * In this method we are updating the specific movie from table
	 * 
	 * @param movie
	 * @return we return movie object
	 */
	public Movie updateMovie(Movie movie) throws InvalidDataException {
		return this.movieRepository.save(movie);

	}

	/**
	 * Delete the movie record by movie_id.
	 * 
	 * @param movieId
	 * @return String i.e " movie with give id movieId deleted"(if user will found). otherwise
	 * @throws NotFoundException
	 */
	@Override
	public String deletMovie(int movieId) throws InvalidDataException, NotFoundException {

		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new NotFoundException("Unable to find movie with given movie id"));
		List<Screen> screen = screenRepository.getAllShowsOfSelectedMovie(movie.getMovie_id());
		for (Screen sc : screen) {
			int screenId = sc.getScreen_id();
			System.out.println(screenId);
			List<Seat> seats = seatRepository.getAllSeatsOfSelectedScreen(sc);
			for (Seat seat2 : seats) {
				System.out.println("seat deleted");
				seatRepository.deleteById(seat2.getSeat_id());
			}
			screenRepository.deleteById(screenId);
		}
		
		return "movie with given id " + movieId + " deleted";
	}

	/**
	 * Find the movie record by movie_id.
	 * 
	 * @param movieId
	 * @return movie record (if movie will found). otherwise
	 * @throws NotFoundException
	 */
	@Override
	public Movie getDetailsOfSelectedMovie(int movieId) throws NotFoundException {
		Movie movie = movieRepository.findById(movieId)
				.orElseThrow(() -> new NotFoundException("Unable to find movie with given movie id"));
		return movie;
	}
}