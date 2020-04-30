package com.app.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
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

import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 * Screen entity provides all the information of the movie show. 
 * Lambok library provides
 * @setter Setters for all the fields of entity
 * @getter getters for all the fields of entity
 * @EqualsAndHashcode and @toString for this Screen entity
 */
@Entity
@Table(name = "screen")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Screen {
	/**screenId is the primary key */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int screen_id;
     /**Here we map the movieId to screen entity to get which movie is going to disply in the screen*/
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "movie_id")
	@NotNull
	private Movie movie;
	
   /**This field indicate the date on which the movie is going to display in screen*/
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Column(name = "date_of_show")
	@NotNull
	private Date dateOfShow;

	/**This field indicate the time of the show*/
	@Column(name = "time_of_show", nullable = false)
	String timeOfShow;

	public Screen() {
		// TODO Auto-generated constructor stub
	}

	public Screen(Movie movie, Date dateOfShow, String timeOfShow) {
		super();
		this.movie = movie;
		this.dateOfShow = dateOfShow;
		this.timeOfShow = timeOfShow;
	}

}