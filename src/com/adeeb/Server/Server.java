package com.adeeb.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;

public class Server {
	static ServerSocket data_link;

	public static void main(String[] args) {
		try {
			data_link = new ServerSocket(9998);
			while (true) {
				System.out
						.println("Server running...\nWaiting for client connection.");
				final Socket current_client = data_link.accept();
				synchronized (current_client) {
					timestamp();
					System.out.println("Client "
							+ current_client.getRemoteSocketAddress()
									.toString() + " has connected");
				}
				Interact client_response_system = new Interact(current_client);
				client_response_system.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// function for printing time stamps
	public static void timestamp() {
		java.util.Date timestamp = new java.util.Date();
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		System.err.print(form.format(timestamp).toString() + " ");
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
				// ---Display Menu---//
				synchronized (client) {
					timestamp();
					System.out.println("server: start of menu");
				}
				BufferedWriter writer = new BufferedWriter(new PrintWriter(
						client.getOutputStream()));

				synchronized (client) {
					timestamp();
					System.out.println("server: after creating writer");
				}
				writer.write("1. option");
				writer.write("2. option");
				writer.write("3. option");
				writer.write("4. option");
				writer.write("5. Disconnect");
				writer.flush();//client.shutdownOutput();

				synchronized (client) {
					timestamp();
					System.out.println("server: end of menu");
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}

			while (true) {
				try {
					// getting choice from user
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(client.getInputStream()));

					synchronized (client){
						timestamp();
						System.out.println("reader?=ready: " + reader.ready());
					}

					String choice = reader.readLine();

					synchronized (client) {
						timestamp();
						System.out.println("reader?=ready: " + reader.ready());
					}
					synchronized (client) {
						timestamp();
						System.out.println("choice?=null: " + (choice == null));
					}

					if (choice.equals("1")) {

					} else if (choice.equals("2")) {

					} else if (choice.equals("3")) {

					} else if (choice.equals("4")) {

					} else if (choice.equals("5")) {
						// disconnect option
						break;
					} else {

					}
				} catch (Exception e) {
					e.printStackTrace();
					System.exit(0);
				}
			}
			// close socket connection
			if (!client.isClosed()) {
				try {
					client.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}