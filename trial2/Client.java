//package com.adeeb.Server;
import java.io.BufferedReader;
import java.net.Socket;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;

public class Client {
	static final int GroupId=50;
	static BufferedWriter output = null;
	static BufferedReader input = null; 
	public static void main(String[] args) {
		String server ="localhost";
		try {
			System.err.println("client: establishing connection...");
			Socket client_socket = new Socket(server, Integer.parseInt(args[0]));
			output = new BufferedWriter(new PrintWriter(client_socket.getOutputStream()));
			input = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
			System.err.println("client: connected!");
			
			/*
			 *
			 * Communication between client and server
			 * 
			 */

			 //writing groupId
			writeToSocket(""+GroupId);
			 //writing stuff from console to server
			Scanner console_input = new Scanner(System.in);
			String console_line="";
			while(!console_line.equalsIgnoreCase("q")){
				//readline and send it to server
				
				//menu for client
				System.out.println("-------------------------------------");
				System.out.println("1. Insert data from text file");
				System.out.println("2. View group's data");
				System.out.println("3. View other group's data");
				System.out.println("4. View all data");
				System.out.println("5. Exit");
				System.out.println("-------------------------------------");
				
				console_line = console_input.nextLine();
				if (console_line.equals("1")){
					insertFromTextFile("Group1.txt");
				}
				else if (console_line.equals("2")){
					writeToSocket("2");
					System.out.println("done choosing two!");
					String xline;
					while((xline=input.readLine())!=null){
						System.out.println(xline);
					}
				}
				else if (console_line.equals("3")){
					writeToSocket("3");
					writeToSocket(""+console_input.nextInt());
				}
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
	/*
	 * Method for writing into the socket
	 */
	 static public void writeToSocket(String text){
		try{
			output.write(text);
			output.write("\r\n");
			output.flush();
		}catch(Exception e){
			e.printStackTrace();
		}
	 }
	/*
	Method for inserting data from text file to database
	*/
	static public void insertFromTextFile(String TextFilename){
		try{
         		Scanner input=new Scanner(new FileInputStream(TextFilename));
	 		float Light=0;
	 		float Temp=0;
	 		String time="";
	 		String FirstLine=input.nextLine();
	 		while(input.hasNextLine()){
				time = input.nextLine();
				Temp = Float.parseFloat(input.nextLine().split(" = ")[1]);
        			Light = Float.parseFloat(input.nextLine().split(" = ")[1]);            
           			output.write("1\r\n");
				output.flush();
				//writing the data to the server
				output.write(GroupId+","+time+","+Temp+","+Light);
				output.write("\r\n"); output.flush();	
				}
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
	
