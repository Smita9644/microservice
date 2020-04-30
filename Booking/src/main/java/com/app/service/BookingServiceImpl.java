package com.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.entity.BookedSeat;
import com.app.entity.Booking;
import com.app.entity.Screen;
import com.app.entity.Seat;
import com.app.entity.User;
import com.app.exception.InvalidDataException;
import com.app.exception.NotFoundException;
import com.app.repository.BookedSeatRepository;
import com.app.repository.BookingRepository;
import com.app.repository.ScreenRepository;
import com.app.repository.SeatRepository;
import com.app.repository.UserRepository;

@Transactional
@Repository
public class BookingServiceImpl implements BookingService {

	/**
	 * auto wired BookingRepository for accessing the methods of booking repository
	 */
	@Autowired
	BookingRepository bookingRepository;

	/** auto wired UserRepository for accessing the methods of user repository */
	@Autowired
	private UserRepository userRepository;

	/**
	 * auto wired ScreenRepository for accessing the methods of screen repository
	 */
	@Autowired
	private ScreenRepository screenRepository;

	/** auto wired SeatRepository for accessing the methods of seat repository */
	@Autowired
	private SeatRepository seatRepository;

	/**
	 * auto wired bookedSeatRepository for accessing the methods of booked seat
	 * repository
	 */
	@Autowired
	private BookedSeatRepository bookedSeatRepo;

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
	@Override
	public Booking bookTheMovie(int userId, int screenId, List<Integer> seats)
			throws InvalidDataException, NotFoundException {

		/** from user id we get the specific user */
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new NotFoundException("Unable to find user by given user id"));

		/** from screen id we get the specific theater */
		Screen screen = screenRepository.findById(screenId)
				.orElseThrow(() -> new NotFoundException("Unable to find screen with given screen id"));

		List<Seat> seatsBooked = new ArrayList<Seat>();
		for (Integer seatId : seats) {
			Seat s = seatRepository.findById(seatId)
					.orElseThrow(() -> new NotFoundException("Unable to find seat with given seat id"));
			seatsBooked.add(s);
		}
		List<Seat> t = seatRepository.getAllSeatsOfSelectedScreen(screen);
		int count = 0;
		for (Seat seat : t) {
			if (seat.isStatus() == false) {
				count++;
			}
		}
		Booking b = new Booking(user);
		System.out.println(count);
		if (seatsBooked.size() <= count) {
			for (Seat seat : seatsBooked) {
				if (seat.isStatus()) {
					throw new InvalidDataException("Seat with id " + seat.getSeat_id() + " is already booked");
				}
				seat.setStatus(true);
			}
			seatRepository.saveAll(seatsBooked);
			Booking booking = bookingRepository.save(b);

			for (Seat seat : seatsBooked) {
				System.out.println(seat.toString());
				BookedSeat bookedSeat = new BookedSeat(b, seat);
				bookedSeatRepo.save(bookedSeat);
			}
			return booking;

		} else {
			return null;
		}

	}

	/**
	 * In this method we are canceling the booking with the help of bookingId
	 * 
	 * @param BookingId
	 * @return String i.e " booking with given id is cancelled "
	 */
	@Override
	public String deleteBooking(int bookingId) throws NotFoundException {

		Booking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new NotFoundException("Unable to find booking  by given booking id"));
		List<Seat> seat = new ArrayList<Seat>();
		List<BookedSeat> bookedSeat = bookedSeatRepo.bookedSeat(bookingId);
		for (BookedSeat bookedSeat2 : bookedSeat) {
			seat.add(bookedSeat2.getSeat());
			for (Seat s : seat) {
				s.setStatus(false);
				seatRepository.save(s);
			}
			bookedSeatRepo.deleteById(bookedSeat2.getSelectedSeatId());
		}

		return "booking with booking id " + bookingId + " is canceled";
	}

}
