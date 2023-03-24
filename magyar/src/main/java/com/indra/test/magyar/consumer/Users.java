package com.indra.test.magyar.consumer;

import java.util.Optional;

import com.indra.test.magyar.db.User;

public interface Users {

	/**
	 * Adds user to database
	 * 
	 * @param aUser Considered user
	 */
	void add(Optional<User> aUser);

	/**
	 * Prints all users
	 */
	void printAll();

	/**
	 * Deletes all users
	 */
	void deleteAll();

}
