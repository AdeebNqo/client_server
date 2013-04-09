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
import java.io.File;
public class Server {
	static ServerSocket data_link;
	static String logfile = System.getProperty("user.dir") + "server_log.txt";
	static Database db = new Database();

	public static void main(String[] args) {
		try {
			File log = new File(logfile);
			if (!log.exists()){
				log.createNewFile();
			}
			data_link = new ServerSocket(Integer.parseInt(args[0]));
			while (true) {
				System.out.println("Waiting for connection...");
				//accepting the client
				final Socket current_client = data_link.accept();
				System.out.println(current_client.getRemoteSocketAddress().toString().substring(1)+" connected!");

				new Thread(){
					String address = current_client.getRemoteSocketAddress().toString();
					public void run() {
						try {
							//writing the menu to the client
							BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(current_client.getOutputStream()));
														
							Scanner reader = new Scanner(current_client.getInputStream());
							//reading group id
							int group_id = reader.nextInt();
							// run the interaction process until the client quits
							while (true) {
									// getting choice from user
									String choice="";
									System.out.println("waiting for input..");
									choice = reader.nextLine();
									System.out.println(choice);
									if (choice.equals("1")){
										System.out.println("Insert() called!");
										String[] data = reader.nextLine().split(",");
										db.Insert(Integer.parseInt(data[0]),data[1],Float.parseFloat(data[2]),Float.parseFloat(data[3]));
									}	
									else if (choice.equals("2")){
										System.out.println("View own group's stuff!");
										writeToSocket(db.select(group_id),writer);
									}
									else if (choice.equals("3")){
										System.out.println("View other group's stuff!");
										//wait fot client to enter group id
										int gid = reader.nextInt();
										writeToSocket(db.select(gid),writer);
									}
									else if (choice.equals("4")){

									}
									else if (choice.equals("5")){
										System.out.println();
									}
									else if (choice.equals("6")){
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
