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
				System.out.println(current_client.getRemoteSocketAddress().toString()+" connected!");

				new Thread(){
					String address = current_client.getRemoteSocketAddress().toString();
					public void run() {
						try {
							//writing the menu to the client
							BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(current_client.getOutputStream()));
							writeToSocket("1. Insert\n2. Delete\n3. View group's data\n4. View other group's data\n5. View all other data\n6. View raw data?\n7. Exit", writer);
							
							Scanner reader = new Scanner(current_client.getInputStream());
							// run the interaction process until the client quits
							while (true) {
									// getting choice from user
									String choice="";
									choice = reader.nextLine();
									if (choice.equals("1")){
										System.out.println("Insert() called!");
									}	
									else if (choice.equals("2")){
										System.out.println("Delete called!");
									}
									else if (choice.equals("3")){
										System.out.println("View own group's stuff!");
									}
									else if (choice.equals("4")){
										System.out.println("View other group's stuff!");
									}
									else if (choice.equals("5")){

									}
									else if (choice.equals("6")){
										System.out.println();
									}
									else if (choice.equals("7")){
										break;
									}
							}
							// close socket connection
							if (!current_client.isClosed()) {
									System.out.println("Disconnecting "+address);
									writer.close();
								}
						}catch(java.util.NoSuchElementException e){
							System.err.println("Client connection lost: "+address);
						}
						catch (Exception e) {
								e.printStackTrace();
							}

		}
					
				}.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	Method for reading from socket
	*/
	public static String readFromSocket(Scanner input){
		String reply = "";
		while(input.hasNextLine()){
			reply += input.nextLine();
			if (input.hasNextLine()){
				reply+="\n";
			}
		}
		return reply;
	}
	/*

	Method for writing to socket

	When you have to write multiple lines at the same time,
	the format of the output is as follows:

		line1\nline2\n...\nlineN

	The lines should be one string and seperated by spaces.
	
	*/
	public static void writeToSocket(String output,BufferedWriter writer_object){
		try{
			writer_object.write(output+"\r\n");
			writer_object.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
