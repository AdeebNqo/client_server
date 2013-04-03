package com.adeeb.Server;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	static ServerSocket data_link;

	public static void main(String[] args) {
		try {
			data_link = new ServerSocket(9998);
			while (true) {
				System.err.println("Server running...\nWaiting for client connection.");
				final Socket current_client = data_link.accept();
				System.err.println("Client "+ current_client.getRemoteSocketAddress().toString()+ " has connected");
				
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
			
		}
	}
}
