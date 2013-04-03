package com.adeeb.Server;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static ServerSocket data_link;

	public static void main(String[] args) {
		try {
			data_link = new ServerSocket(9998);
			while (true) {
				System.err
						.println("Server running...\nWaiting for client connection.");
				final Socket current_client = data_link.accept();
				System.err.println("Client "
						+ current_client.getRemoteSocketAddress().toString()
						+ " has connected");
				Interact client_response_system = new Interact(current_client);
				client_response_system.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Inner class that will requests for each client
	 */
	static class Interact extends Thread {
		Socket client;

		public Interact(Socket some_client) {
			this.client = some_client;
		}

		public void run() {
			// run the interaction process until the client quits
			try {
				System.err.println("abt to display menu");
				display_menu(client.getOutputStream());
				System.err.println("done displaying menu!");
				// getting menu selection from the client
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		/*
		 * Method for displaying the server menu to a client
		 */
		public void display_menu(OutputStream outstream) {
			try {
				BufferedWriter writer = new BufferedWriter(new PrintWriter(
						outstream));
				writer.write("1. Display own data\n");
				writer.write("2. Display some group's data\n");
				writer.write("3. Display all data\n");
				writer.write("4. Retrieve raw data(whatever that means)\n");
				writer.write("5. disconnect\n");
				writer.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}