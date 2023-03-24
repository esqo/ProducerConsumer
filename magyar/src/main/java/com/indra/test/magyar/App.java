package com.indra.test.magyar;

import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import com.indra.test.magyar.commands.UserCommand;
import com.indra.test.magyar.consumer.UsersImpl;
import com.indra.test.magyar.producer.CommandProducer;

/**
 * Starter app
 *
 */
public class App {
	
	public static void main(String[] args) throws IOException, InterruptedException  {
		BlockingQueue<UserCommand> sharedQueue = new LinkedBlockingQueue<>();

		Thread prodThread = new Thread(new CommandProducer(sharedQueue, 2));
		Thread consThread = new Thread(new UsersImpl(sharedQueue));

		prodThread.start();
		prodThread.join();
		consThread.start();
	}
}
