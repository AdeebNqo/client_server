//package com.adeeb.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.io.OutputStreamWriter;

public class Server {
	static ServerSocket data_link;

	public static void main(String[] args) {
		try {
			data_link = new ServerSocket(9998);
			while (true) {
				System.out.println("Waiting for connection...");
				//accepting the client
				final Socket current_client = data_link.accept();
				
				new Thread(){
					public void run() {
						try {
							//writing the menu to the client
							BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(current_client.getOutputStream()));
							writer.write("1. Insert");
							writer.write("2. Delete");
							writer.write("3. View group's data");
							writer.write("4. View other group's data");
							writer.write("5. View all other data");
							writer.write("6. View raw data?");
							writer.write("7. Exit");
							writer.flush();
							
							System.err.println("server: before declaring reader");
							Scanner reader = new Scanner(current_client.getInputStream());
							System.err.println("server: before takin in choice");
							// run the interaction process until the client quits
							while (true) {
									// getting choice from user
									String choice="";
									choice = reader.nextLine();
									if (choice.equals("1.")){
									
									}	
									else if (choice.equals("2.")){
									
									}
									else if (choice.equals("3.")){
				
									}
									else if (choice.equals("4.")){
				
									}
									else if (choice.equals("5.")){
										break;
									}
							}
							// close socket connection
							if (!current_client.isClosed()) {
									current_client.close();
								}
						}catch (Exception e) {
								e.printStackTrace();
								System.exit(0);
							}

		}
					
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
