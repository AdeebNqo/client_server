package com.adeeb.Server;
import java.io.BufferedReader;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class Client {
	public static void main(String[] args) {
		try {
			System.err.println("client: establishing connection...");
			Socket client_socket = new Socket("localhost", 9998);
			System.err.println("client: connected!");
			//read the menu
			System.err.println("client: start of read menu");
			BufferedReader reader = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
			String line;
			System.err.println("client: before while loop");
			while((line = reader.readLine())!=null){
				System.out.println("line is "+line);
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