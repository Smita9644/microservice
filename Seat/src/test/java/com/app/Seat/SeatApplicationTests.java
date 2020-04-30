package com.app.Seat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.text.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.controller.SeatController;
import com.app.exception.NotFoundException;

@SpringBootTest
class SeatApplicationTests {

	@Autowired
	SeatController controller;
	
	/**In this method we are trying to apply test case on getSeatsOfSelectedScreen*/
	@Test
	void getSeatsOfSelectedScreen() throws NotFoundException, ParseException {
		
		//When we pass correct screen id it returns the List of seat which exits in that screen
		assertEquals(10,controller.GetAllSeatStatusOfSelectedScreen(1).size() );
		
		//When we pass incorrect scrren id it throws exception
		Throwable exception = assertThrows(NotFoundException.class, () -> controller.GetAllSeatStatusOfSelectedScreen(1111).size());
	}

}
