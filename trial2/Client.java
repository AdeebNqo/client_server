//package com.adeeb.Server;
import java.io.BufferedReader;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.OutputStreamWriter;

public class Client {
	public static void main(String[] args) {
		String server ="localhost";
		try {
			System.err.println("client: establishing connection...");
			Socket client_socket = new Socket(server, 9998);
			BufferedWriter output = new BufferedWriter(new PrintWriter(client_socket.getOutputStream()));
			BufferedReader input = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
			System.err.println("client: connected!");
			
			/*
			 *
			 * Communication between client and server
			 * 
			 */
			 
			 //reading menu
			 String line;
			 while((line=input.readLine())!=null){
				 System.out.println(line);
				 if (!input.ready()){
					 break;
				 }
			 }
			 
			 
			 //writing stuff from console to server
			Scanner console_input = new Scanner(System.in);
			String console_line="";
			while(!console_line.equalsIgnoreCase("q")){
				//readline and send it to server
				console_line = console_input.nextLine();
				output.write(console_line+"\r\n");
				output.flush();
			}

			//releasing resources
			if (!client_socket.isClosed()){
				client_socket.shutdownInput();
				client_socket.shutdownOutput();
				client_socket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
