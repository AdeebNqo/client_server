package com.adeeb.Server;

import java.net.Socket;
public class Client {
	public static void main(String[] args) {
		try {
			Socket client_socket = new Socket("localhost", 9998);
			//do something
			if (!client_socket.isClosed()) client_socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}