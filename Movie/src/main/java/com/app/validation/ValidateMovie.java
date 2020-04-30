package com.app.validation;

import com.app.entity.Movie;
import com.app.exception.InvalidDataException;

/** This class use used to validate the movie */
public class ValidateMovie {
	public ValidateMovie() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * In the given method we validate the movie object
	 * 
	 * @param Movie object
	 * @throws InvalidDataException
	 */
	public void validateMovie(Movie movie) throws InvalidDataException {
		this.validateCategory(movie.getCategory());
		this.validateName(movie.getName());
		this.validateFormat(movie.getFormat());
		this.validateLanguage(movie.getLanguage());
		this.validateRating(movie.getRating());

	}

	/**
	 * In the given method we validate the name of the movie
	 * 
	 * @param String name
	 * @throws InvalidDataException
	 */
	public void validateName(String name) throws InvalidDataException {
		if (name == null)
			throw new InvalidDataException("movie name should not be null");
		else if (!name.matches("[a-zA-Z0-9 ]*[^@&#]"))
			throw new InvalidDataException("unable to add movie with wrong name");
		else if (name == "")
			throw new InvalidDataException("Name length is zero");
	}

	/**
	 * In the given method we validate the category of the movie
	 * 
	 * @param String category
	 * @throws InvalidDataException
	 */
	public void validateCategory(String category) throws InvalidDataException {
		if (category == null)
			throw new InvalidDataException("movie category should not be null");
		else if (category == "")
			throw new InvalidDataException("category length is zero");
		else if (!category.matches("[a-zA-Z ]*[^@&#0-9]"))
			throw new InvalidDataException("unable to add movie with wrong category");

	}

	/**
	 * In the given method we validate the language of the movie
	 * 
	 * @param String name
	 * @throws InvalidDataException
	 */
	public void validateLanguage(String language) throws InvalidDataException {
		if (language == null)
			throw new InvalidDataException("movie language should not be null");
		else if (language == "")
			throw new InvalidDataException("language length is zero");
		else if (!language.matches("[a-zA-Z]*[^@&#0-9]"))
			throw new InvalidDataException("unable to add movie with wrong language");

	}

	/**
	 * In the given method we validate the rating of the movie
	 * 
	 * @param Double rating
	 * @throws InvalidDataException
	 */
	public void validateRating(Double rating) throws InvalidDataException {
		if (rating == null || rating == 0.0)
			throw new InvalidDataException("movie rating should not be null");
		else if (rating < 0)
			throw new InvalidDataException("Rating has to be positive and  greater than 0");

	}

	/**
	 * In the given method we validate the format of the movie
	 * 
	 * @param String format
	 * @throws InvalidDataException
	 */
	public void validateFormat(String format) throws InvalidDataException {
		if (format == null)
			throw new InvalidDataException("movie format should not be null");
		else if (format == "")
			throw new InvalidDataException("format length is zero");
		else if (!format.matches("[A-Za-z0-9]*[^@&#]"))
			throw new InvalidDataException("unable to add movie with wrong format");

	}

}
