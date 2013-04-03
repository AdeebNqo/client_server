package com.adeeb.Server;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {
		try {
			Socket client_socket = new Socket("localhost", 9998);
			BufferedReader input = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
			String line="";
			while((line=input.readLine())!=null){
				System.out.println(line);
			}
			input.close();
			
			//Reading data from user via console
			BufferedReader user_input = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("You've just entered "+(user_input.readLine()));
			
			client_socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}