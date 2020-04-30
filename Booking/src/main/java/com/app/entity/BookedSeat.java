package com.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * BookedSeat entity having bookingId and selected seats. Lambok library
 * provides
 * 
 * @setter Setters for all the fields of entity
 * @getter getters for all the fields of entity
 * @EqualsAndHashcode and @toString for this BookedSeat entity
 */
@Entity
@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
public class BookedSeat {
	/** This is primary key which starts from 1 */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int selectedSeatId;
	/**
	 * This field contains booking id generated for every user at the time of
	 * booking
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "bookingId", foreignKey = @ForeignKey(name = "bookingId"))
	@NotNull

	private Booking booking;
	/** This field contains selected seats by user at the time of booking */
	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
	@JoinColumn(name = "seat_id", foreignKey = @ForeignKey(name = "seat_id"))
	@NotNull
	private Seat seat;
	
    /**Parameterized constructor for booking entity*/
	public BookedSeat(Booking booking, Seat seat) {
		super();
		this.booking = booking;
		this.seat = seat;
	}

	public BookedSeat() {
		// TODO Auto-generated constructor stub
	}
}