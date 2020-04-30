package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.BookedSeat;

/**
 * This is BookedSeat repository, It will interact with database using
 * JpaRepository.
 */
public interface BookedSeatRepository extends JpaRepository<com.app.entity.BookedSeat, Integer> {
	
	@Query(value = "select * from booked_seat m where m.booking_id=:bookingId", nativeQuery = true)
	List<BookedSeat> bookedSeat(int bookingId);

}
