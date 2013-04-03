package com.adeeb.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
public class Client {
	public static void main(String[] args) {
		try {
			Socket client_socket = new Socket("localhost", 9998);
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
			
			//closing input and output stream writers
			in.close(); out.close();
			//if socket is still open, close it
			if (!client_socket.isClosed()) client_socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}