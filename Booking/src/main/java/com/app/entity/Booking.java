package com.app.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Booking entity having bookingId and userId. Lambok library provides
 * 
 * @setter Setters for all the fields of entity
 * @getter getters for all the fields of entity
 * @EqualsAndHashcode and @toString for this Booking entity
 */
@Entity
@Table(name = "booking")
@ToString
@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
public class Booking {
	/** bookingId is primary key starting from 1 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "booking_id")
	private int bookingId;
	
	/** This field contains user id of user which is going to book the seat */
	@OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@NotNull
	private User user;

	public Booking(User user) {
		this.user = user;
	}

	public Booking() {
		// TODO Auto-generated constructor stub
	}
}
