package com.indra.test.magyar.producer;

import java.util.concurrent.BlockingQueue;

import com.indra.test.magyar.commands.UserCommand;
import com.indra.test.magyar.db.User;

public class CommandProducer implements Runnable {

	private final BlockingQueue<UserCommand> commandsQueue;

	private final int numberOfRepetitions;

	public CommandProducer(BlockingQueue<UserCommand> theCommandsQueue, int aNumberOfRepetitions) {
		super();
		commandsQueue = theCommandsQueue;
		numberOfRepetitions = aNumberOfRepetitions;
	}

	public void createUser(User aUser) {
		commandsQueue.add(UserCommand.createForAdd(aUser));
	}

	public void printAllUsers() {
		commandsQueue.add(UserCommand.createForPrint());
	}

	public void deleteAllUsers() {
		commandsQueue.add(UserCommand.createForDelete());
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < numberOfRepetitions; i++) {
				createUser(new User("a1", "Robert"));
				createUser(new User("a2", "Martin"));
				Thread.sleep(500);
				printAllUsers();
				Thread.sleep(500);
				deleteAllUsers();
				Thread.sleep(500);
				printAllUsers();
			}
			System.out.println("Producer finished...");
		} catch (InterruptedException ex) {
			System.out.println("Shutting down producer ...");
		}

	}

}
