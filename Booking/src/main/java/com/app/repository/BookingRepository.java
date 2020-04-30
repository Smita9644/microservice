package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.app.entity.Booking;

/**
 * This is Booking repository, It will interact with database using
 * JpaRepository.
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
