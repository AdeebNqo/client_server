package com.adeeb.Server;
import java.io.BufferedReader;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class Client {
	public static void main(String[] args) {
		String server = /*"10.42.0.81";*/"localhost";
		System.out.println("client: init");
		try {
			System.err.println("client: establishing connection...");
			Socket client_socket = new Socket(server, 9998);
			System.err.println("client: connected!");
			
			/*
			 * Communication between client and server
			 * 
			 */
			System.err.println("client: trying to acquire the input and output streams");
			BufferedReader in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
			BufferedWriter out = new BufferedWriter(new PrintWriter(client_socket.getOutputStream()));
			System.err.println("client: done acquiring streams");
			
			String curr_line ="";
			//reading menu
			System.err.println("client: before while loop in 'reading menu'");
			while((curr_line=in.readLine())!=null){
				System.out.println(curr_line+"\n");
			}
			client_socket.shutdownInput();
			
			System.err.println("client: before make a option");
			//make a option			
			BufferedWriter writer = new BufferedWriter(new PrintWriter(client_socket.getOutputStream()));
			writer.write("5");
			client_socket.shutdownOutput();	
			System.out.println("after shutting down client_socket");

			if (!client_socket.isClosed()) client_socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}