

import java.net.*;
import java.util.Scanner;
import java.io.*;



public class ChatClient{  
	private Socket socket;
	private Thread thread              = null;
	private BufferedReader  console    = null;
	public boolean running			   = true;
	private ChatGUI cg;
	private String server, username;
	private int port;
	private ObjectInputStream streamIn;		// to read from the socket
	private ObjectOutputStream streamOut;		// to write on the socket
	
	ChatClient(String server, int port, String username) {
		this(server, port, username, null);
	}

	public ChatClient(String server, int port, String username, ChatGUI cg){  
		this.server = server;
		this.port = port;
		this.username = username;
		// save if we are in GUI mode or not
		this.cg = cg;
	}

	//Handles message sending/recieving.
	public void handle(String msg){  
		cg.append(msg + "\n");		
	}

	//Starts the clients connection to server/port
	public boolean start() throws IOException{  
		
			// try to connect to the server
				try {
					socket = new Socket(server, port);
				} 
				// if it failed not much I can so
				catch(Exception ec) {
					handle("Error connectiong to server:" + ec);
					return false;
				}
				
				String msg = "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort();
				handle(msg);
			
				/* Creating both Data Stream */
				try
				{
					streamIn  = new ObjectInputStream(socket.getInputStream());
					streamOut = new ObjectOutputStream(socket.getOutputStream());
				}
				catch (IOException eIO) {
					handle("Exception creating new Input/output Streams: " + eIO);
					return false;
				}

				// creates the Thread to listen from the server 
				new ListenFromServer().start();
				// Send our username to the server this is the only message that we
				// will send as a String. All other messages will be ChatMessage objects
				try
				{
					streamOut.writeObject(username);
				}
				catch (IOException eIO) {
					handle("Exception doing login : " + eIO);
					stop();
					return false;
				}
				// success we inform the caller that it worked
				return true;
		
	}

	//Closes socket/input/output stream
	public void stop(){  
		try { 
			if(streamIn != null) streamIn.close();
		}
		catch(Exception e) {} // not much else I can do
		try {
			if(streamOut != null) streamOut.close();
		}
		catch(Exception e) {} // not much else I can do
        try{
			if(socket != null) socket.close();
		}
		catch(Exception e) {} // not much else I can do
		
	}

	/*
	 * To send a message to the server
	 */
	void sendMessage(String msg) {
		try {
			streamOut.writeObject(msg);
		}
		catch(IOException e) {
			handle("Exception writing to server: " + e);
		}
	}

	public static void main(String args[]) throws IOException{  
		// default values
				int portNumber = 5554;
				String serverAddress = "localhost";
				String userName = "Anonymous";

				// create the Client object
				ChatClient client = new ChatClient(serverAddress, portNumber, userName);
				// test if we can start the connection to the Server
				// if it failed nothing we can do
				if(!client.start())
					return;
				
				// wait for messages from user
				Scanner scan = new Scanner(System.in);
				// loop forever for message from the user
				while(true) {
					System.out.print("> ");
					// read message from user
					String msg = scan.nextLine();
					// logout if message is LOGOUT
					
						client.sendMessage(msg);
					
				}
	}

//listens for messages sent from server
class ListenFromServer extends Thread {

	public void run() {
		while(true) {
			try {
				String msg = (String) streamIn.readObject();
				// if console mode print the message and add back the prompt
				if(cg == null) {
					System.out.println(msg);
					System.out.print("> ");
				}
				else {
					handle(msg);
				}
			}
			catch(IOException e) {
				handle("Server has close the connection: " + e);
				break;
			}
			// can't happen with a String object but need the catch anyhow
			catch(ClassNotFoundException e2) {
			}
		}
	}
}
}



