package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.app.entity.Screen;
import com.app.entity.Seat;

/**
 * This is Seat repository, It will interact with database using JpaRepository.
 */
public interface SeatRepository extends CrudRepository<Seat, Integer> {
	/**
	 * This method is for getting all the seats of selected screen\
	 * 
	 * @param screen id
	 * @return List off seat
	 */
	@Query(value = "select * from Seat m where m.screen_id=:screen", nativeQuery = true)
	List<Seat> getAllSeatsOfSelectedScreen(Screen screen);

	/**
	 * This method is for getting status all the seats of selected screen
	 * 
	 * @param screen id
	 * @return list 0f status
	 */
	@Query(value = "select status from Seat m where m.screen_id=:screen", nativeQuery = true)
	List<Boolean> getStatusAllSeatsOfSelectedScreen(Screen screen);

}
