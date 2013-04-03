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
			BufferedReader input = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
			String line="";
			while((line=input.readLine())!=null){
				System.out.println(line);
			}
			//giving the server some data/a response
			BufferedReader user_choice = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter user_data = new BufferedWriter(new PrintWriter(client_socket.getOutputStream()));
			user_data.write(user_choice.readLine());
			//user_data.write("\n");
			//user_data.close();
			client_socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}