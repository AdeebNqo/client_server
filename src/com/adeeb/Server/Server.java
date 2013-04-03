package com.adeeb.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
				System.err.println("server: started response system for client");
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
			try{
				// run the interaction process until the client quits
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				BufferedWriter out = new BufferedWriter(new PrintWriter(client.getOutputStream()));
				System.err.println("server: before while loop of menu system");
				while(true){
					//provide menu
					/*------Menu-------*/
					out.write("1. View own group's data");
					out.write("2. View another group's data");
					out.write("3. View all data");
					out.write("4. Disconnect");
					break;
				}
				System.err.println("afte menu while loop");
				//closing the input and output stream reader and writer
				in.close(); out.close();
				System.err.println("server: closed the input and output streams");
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
