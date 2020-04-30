package com.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.app.entity.Screen;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;

public interface ScreenService {

	/**
	 * In This method we get list of all the movies in the theater
	 * 
	 * @return List of Screens having movie id else Empty List
	 */
	public List<Screen> getAllMoviesInTheater();

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
	public Screen addMovieToTheater(int id, Date date, String timeOfShow)
			throws InvalidDataException, NotFoundException;

	/**
	 * This method is get all the shows of selected movie
	 * 
	 * @param movieId
	 * @return List of Screen(if found) else empty List if movie with is not
	 *         present@throws NotFoundException
	 * @throws NotFoundException 
	 */
	public List<Screen> getAllShowsOfSelectedMovie(int movieId) throws InvalidDataException, NotFoundException;

	/**
	 * This method is to get all available seats from given screen
	 * 
	 * @param screenId
	 * @return no of empty seats if screen with given screen id is not present
	 *         then @throws NotFoundException
	 */
	public Integer getAvailableSeatsInSelectedScreen(int screenId) throws InvalidDataException, NotFoundException;

	/**
	 * This method is to get the details of any screen in theater depending upon
	 * screen id
	 * 
	 * @param screenId
	 * @return Screen object if screen with given screen id is not present
	 *         then @throws NotFoundException
	 */
	public Screen getScreenDetailsByScreenId(int screenId) throws InvalidDataException, NotFoundException;
}
