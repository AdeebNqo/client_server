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
		String server = /*"10.42.0.81";*/"localhost";
		System.out.println("client: init");
		try {
			System.err.println("client: establishing connection...");
			Socket client_socket = new Socket(server, 9998);
			BufferedWriter output = new BufferedWriter(new PrintWriter(client_socket.getOutputStream()));
			BufferedReader input = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
			System.err.println("client: connected!");
			
			/*
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
			 
			 
			 //writing stuff example
			output.write("Hello World!");
			output.flush();
			
			//some busy waiting to that client process does not terminate
			while(true){
				
			}
			
			//input.close();
			//output.close();
			//if (!client_socket.isClosed()) client_socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
