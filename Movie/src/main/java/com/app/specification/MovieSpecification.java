package com.app.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.app.entity.Movie;

/**
 * We create this class for applying filters on movies using jpa specification
 */
public class MovieSpecification implements Specification<Movie> {

	public MovieSpecification() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * In this method we are trying to get all the movies of given category
	 */
	public static Specification<Movie> hasCategory(String category) {

		return (movie, cq, cb) -> category == null ? cb.conjunction() : cb.equal(movie.get("category"), category);
	}

	/**
	 * In this method we are trying to get all the movies of given language
	 */
	public static Specification<Movie> hasLanguage(String language) {
		return (movie, cq, cb) -> language == null ? cb.conjunction() : cb.equal(movie.get("language"), language);
	}

	/**
	 * In this method we are trying to get all the movies of given format
	 */
	public static Specification<Movie> hasFormat(String format) {
		return (movie, cq, cb) -> format == null ? cb.conjunction() : cb.equal(movie.get("format"), format);
	}

}
