package com.indra.test.magyar.consumer;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.indra.test.magyar.commands.UserCommand;
import com.indra.test.magyar.db.HibernateUtil;
import com.indra.test.magyar.db.User;

public class UsersImpl implements Users, Runnable {

	private final BlockingQueue<UserCommand> commandsQueue;

	public UsersImpl(BlockingQueue<UserCommand> theCommandsQueue) {
		super();
		commandsQueue = theCommandsQueue;
	}

	public void add(Optional<User> aUser) {
		Transaction transaction = null;
		if (aUser.isPresent()) {
			try (Session session = HibernateUtil.getSessionFactory().openSession()) {
				transaction = session.beginTransaction();
				session.save(aUser.get());
				transaction.commit();
			} catch (Exception e) {
				if (transaction != null) {
					transaction.rollback();
				}
				e.printStackTrace();
			}
		} else {
			System.out.println("Missing user in create request.");
		}
	}

	public void printAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<User> theUsers = session.createQuery("from User", User.class).list();
			theUsers.forEach(eachUser -> System.out.println(eachUser.toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteAll() {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			if (session.createQuery("DELETE FROM User").executeUpdate() > 0) {
				System.out.println("All users has been deleted.");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void run() {
		while (commandsQueue.size() > 0) {
			try {
				UserCommand theCommand = commandsQueue.take();
				System.out.println("Consumed: " + theCommand.getCommand());

				switch (theCommand.getCommand()) {
				case CREATE:
					add(theCommand.getUser());
					break;
				case PRINT_ALL:
					printAll();
					break;
				case DELETE_ALL:
					deleteAll();
					break;
				default:
					System.out.println("Unknown command: " + theCommand.getCommand());
				}
			} catch (InterruptedException ex) {
				HibernateUtil.shutdown();
				System.out.println("Shutting down consumer...");
			} 
		}
		HibernateUtil.shutdown();
		System.out.println("Consumer finished...");
	}
}
