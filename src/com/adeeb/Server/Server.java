package com.adeeb.Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.InputStreamReader;

public class Server {
	static ServerSocket data_link;

	public static void main(String[] args) {
		try {
			data_link = new ServerSocket(9998);
			while (true) {
				System.out.println("Server running...\nWaiting for client connection.");
				final Socket current_client = data_link.accept();
				System.out.println("Client "+ current_client.getRemoteSocketAddress().toString()+ " has connected");
				
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
			try{
				//---Display Menu---//
				System.err.println("server: start of menu");
				BufferedWriter writer = new BufferedWriter(new PrintWriter(client.getOutputStream()));
				System.err.println("server: after creating writer");	
				writer.write("1. option");
				writer.write("2. option");
				writer.write("3. option");
				writer.write("4. option");
				writer.write("5. Disconnect");
				client.shutdownOutput();
				System.err.println("server: end of menu");
			}catch(Exception e){
				e.printStackTrace();
				System.exit(0);	
			}
		
			while(true){
				try{
					//getting choice from user
					BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
					System.err.println("reader?=ready: "+reader.ready());
					String choice = reader.readLine();
					System.err.println("reader?=ready: "+reader.ready());
					System.err.println("choice?=null: "+(choice==null));
					if (choice.equals("1")){
	
					}
					else if (choice.equals("2")){

					}
					else if (choice.equals("3")){
	
					}
					else if (choice.equals("4")){
	
					}
					else if (choice.equals("5")){
						//disconnect option
						break;
					}
					else{
	
					}
				}catch(Exception e){
					e.printStackTrace();
					System.exit(0);
				}
			}
			//close socket connection
			if (!client.isClosed()){
				try{
					client.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}