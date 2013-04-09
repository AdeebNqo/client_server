libs = ".:mysql-connector-java-5.1.18-bin.jar"
Server.class: Server.java Database.class Client.class
	@javac Server.java
Client.class: Client.java
	@javac Client.java
Database.class: Database.java
	@javac -cp $(libs) Database.java
run_server:
	@java -cp $(libs) Server 9001
run_client:
	@java -cp $(libs) Client 9001
clean:
	@rm Database.class Client.class Server.class
