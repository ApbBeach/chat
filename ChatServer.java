import java.net.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;

public class ChatServer {
	//List of connected clients: contains server socket and ID
	//Implemented separate thread into main server file 
	//Changed from array to ArrayList
	//List of connected clients
	private ArrayList < ClientThread > clients;
	private int port;
	private static int uniqueId;
	//Is the server running and accepting client connections
	private boolean running = true;

	//Sets up chat server socket 'server' on port number 'port'
	public ChatServer(int port) {
		// the port
		this.port = port;
		// ArrayList for the Client list
		clients = new ArrayList < ClientThread > ();
	}


	//Starts client thread, used when client connects
	public void start() throws Exception {
		running = true;
		//Main server socket
		System.out.println("Binding to port " + port + ", please wait  ...");
		ServerSocket server = new ServerSocket(port);

		//Waits for clients to attempt to connect and accepts them
		while (running) {
			System.out.println("Waiting for a client ...");
			//Main server socket
			Socket socket = server.accept(); // accept connection
			//check if stopped
			if (!running) break;
			ClientThread client = new ClientThread(socket); // make a thread of it
			clients.add(client); // save it in the ArrayList
			client.start();
		}

		try {
			server.close();
			for (int i = 0; i < clients.size(); ++i) {
				ClientThread tc = clients.get(i);
				try {
					tc.streamIn.close();
					tc.streamOut.close();
					tc.socket.close();
				} catch (IOException ioE) {
					// not much I can do
				}
			}
		} catch (Exception e) {
			System.out.println("Exception closing the server and clients: " + e);
		}
	}

	//Ends server connection, used on error.
	public void stop() {
		running = false;
		try {
			new Socket("localhost", port);
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}



	public synchronized void handle(String input) {
		System.out.println(input);
		for (int i = clients.size(); --i >= 0;) {
			ClientThread ct = clients.get(i);
			if (!ct.writeMsg(input + "\n")) {
				clients.remove(i);
			}
		}
	}


	//Terminates their thread and removes them from the client list
	public synchronized void remove(int ID) {
		for (int i = 0; i < clients.size(); ++i) {
			ClientThread ct = clients.get(i);
			if (ct.id == ID) {
				clients.remove(i);
				return;
			}
		}
	}

	public static void main(String args[]) throws Exception {
		int portNumber = 5554;
		ChatServer server = new ChatServer(portNumber);
		server.start();
	}


	//Adds client to the server, opens client to accpet/send messages through data stream
	//starts client socket.  Updates client count list, ensures the client list is below the maximum. 
	class ClientThread extends Thread {
		// the socket where to listen/talk
		Socket socket;
		ObjectInputStream streamIn;
		ObjectOutputStream streamOut;
		int id;
		String username;
		String cm;
		ClientThread(Socket socket) {
			id = ++uniqueId;
			this.socket = socket;
			try {
				streamOut = new ObjectOutputStream(socket.getOutputStream());
				streamIn = new ObjectInputStream(socket.getInputStream());
				username = (String) streamIn.readObject();
				System.out.println(username + " just connected.");
			} catch (IOException e) {
				System.out.println("Exception creating new Input/output Streams: " + e);
				return;
			}
			catch (ClassNotFoundException e) {}
			
		}

		public void run() {
			boolean running = true;
			while (running) {
				try {
					cm = (String) streamIn.readObject();
				} catch (IOException e) {
					System.out.println(username + " Exception reading Streams: " + e);
					break;
				} catch (ClassNotFoundException e2) {
					break;
				}
				String message = cm;

					handle(username + ": " + message);

				
				
				

			}
			remove(id);
			close();
		}

		//Ends server connection
		private void close() {
			try {
				if (streamOut != null) streamOut.close();
				if (streamIn != null) streamIn.close();
				if (socket != null) socket.close();
			} catch (Exception e) {}
		}

		private boolean writeMsg(String msg) {
			if (!socket.isConnected()) {
				close();
				return false;
			}
			try {
				streamOut.writeObject(msg);
			}
			catch (IOException e) {
				System.out.println("Error sending message");
			}
			return true;
		}
	}
}