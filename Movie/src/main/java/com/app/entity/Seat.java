package com.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.JoinColumn;

/**
 * Seat entity seat id and status of the seat . Lambok library provides
 * 
 * @setter Setters for all the fields of entity
 * @getter getters for all the fields of entity
 * @EqualsAndHashcode and @toString for this User entity
 */
@Entity
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Seat {

	/** seatid is the primary key */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int seat_id;

	/**
	 * here we map scrren_id with seat to get information i.e for which this seat is
	 * allocateed
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "screen_id")
	Screen screen;

	/**
	 * This field indicate status of the seat i.e false means seats is vacant true
	 * means seat is allocated to user
	 */
	boolean status;

	public Seat(Screen screen, boolean status) {
		super();
		this.screen = screen;
		this.status = status;
	}

	public Seat() {
		// TODO Auto-generated constructor stub
	}
	
	
}
