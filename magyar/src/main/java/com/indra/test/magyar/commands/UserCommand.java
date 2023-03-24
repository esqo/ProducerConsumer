package com.indra.test.magyar.commands;

import java.io.Serializable;
import java.util.Optional;

import com.indra.test.magyar.db.User;

/**
 * 
 * Command to consume
 *
 */
public class UserCommand implements Serializable {
	
	/**
	 * Class version for java serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Considered user to add (Optional)
	 */
	private User user;

	/**
	 * Command to execute
	 */
	private Command command;

	public static UserCommand createForAdd(User aUser) {
		return new UserCommand(aUser, Command.CREATE);
	}

	public static UserCommand createForPrint() {
		return new UserCommand(null, Command.PRINT_ALL);
	}

	public static UserCommand createForDelete() {
		return new UserCommand(null, Command.DELETE_ALL);
	}

	/**
	 * 
	 * @param aUser    Considered user to add (Optional)
	 * @param aCommand Command to execute
	 */
	private UserCommand(User aUser, Command aCommand) {
		user = aUser;
		command = aCommand;
	}

	protected UserCommand() {
		super();
	}

	public Optional<User> getUser() {
		return Optional.ofNullable(user);
	}

	public Command getCommand() {
		return command;
	}

}
