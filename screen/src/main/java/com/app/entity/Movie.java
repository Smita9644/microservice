package com.app.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Movie entity having all the details of movie.
 *  Lambok library provides
 * @setter Setters for all the fields of entity
 * @getter getters for all the fields of entity
 * @EqualsAndHashcode and @toString for this movie entity
 */
@Component("Model")
@Entity
@Table(name = "Movie")
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Movie {
	/** Movie id is the primary key */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "movie_id")
	private int movie_id;

	/** This field indicate name of the movie */
	@Column(length = 50, name = "movie_name")
	@NotNull
	private String name;

	/** This field indicate in which category the movie belongs */
	@Column(length = 50, name = "movie_category")
	@NotNull
	private String category;

	/** This field is the rating given for the movie */
	@Column(name = "movie_rating")
	@NotNull
	private double rating;

	/** This movie indicate in which format the movie going to display on screen */
	@Column(length = 50, name = "movie_format")
	@NotNull
	private String format;

	/** This field indicate the language used in the movie */
	@Column(length = 50, name = "movie_language")
	@NotNull
	private String language;

	public Movie() {
		// TODO Auto-generated constructor stub
	}

	public Movie(String name, String category, double rating, String format, String language) {
		super();
		this.name = name;
		this.category = category;
		this.rating = rating;
		this.format = format;
		this.language = language;

	}

	public Movie(int id) {
		super();
		this.movie_id = id;
	}

	public Movie(int movie_id, @NotNull String name, @NotNull String category, @NotNull double rating,
			@NotNull String format, @NotNull String language) {
		super();
		this.movie_id = movie_id;
		this.name = name;
		this.category = category;
		this.rating = rating;
		this.format = format;
		this.language = language;
	}

	
}
