package com.app.service;

import java.util.List;

import com.app.entity.Booking;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;

public interface BookingService {
	/**
	 * In this method we are trying to book the movie depending upon userId and
	 * screen id and the no's of selected list
	 * 
	 * @param userid
	 * @param screenId
	 * @param seat
	 * @return Object of booking having details BookingId and user Info(if
	 *         successful) if user or screen id is incorrect @throws
	 *         NotFoundException
	 */
	public Booking bookTheMovie(int userId, int screenId, List<Integer> seat)
			throws InvalidDataException, NotFoundException;

	/**
	 * In this method we are canceling the booking with the help of bookingId
	 * 
	 * @param BookingId
	 * @return String i.e " booking with given id is cancelled " if booking with
	 *         given bookingId is not present @throw NotFoundException
	 * 
	 */
	public String deleteBooking(int bookingId) throws NotFoundException;
}
