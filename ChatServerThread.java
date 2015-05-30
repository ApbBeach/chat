import java.net.*;
import java.io.*;

public class ChatServerThread extends Thread{  
	private ChatServer       server    = null;
	private Socket           socket    = null;
	private int              ID        = -1;
	private DataInputStream  streamIn  = null;
	private DataOutputStream streamOut = null;
	public static boolean	 running   = true;

	public ChatServerThread(ChatServer server1, Socket socket1)
	{  super();
	server = server1;
	socket = socket1;
	ID     = socket.getPort();
	}

	//Used by server to send messages to clients
	public void send(String msg){   
		try{  
			streamOut.writeUTF(msg);
			streamOut.flush();
		}
		catch(IOException ioe){  
			System.out.println(ID + " ERROR sending: " + ioe.getMessage());
			server.remove(ID);
			running = false;
		}
	}

	//Gets client's ID (Socket port number)
	public int getID(){
		int client_id = ID;
		return client_id;
	}

	//TO-DO
	public void run(){  
		System.out.println("Server Thread " + ID + " running.");
		while (running){  
			try{  
				server.handle(ID, streamIn.readUTF());
			}
			catch(IOException ioe){  
				System.out.println(ID + " ERROR reading: " + ioe.getMessage());
				server.remove(ID);
				running = false;
			}
		}
		try {
			close();
		} catch (IOException e) {
			System.out.println("Error closing server: " + e.getMessage());
			e.printStackTrace();
		}
	}

	//opens client to accpet/send messages through data stream
	public void open() throws IOException{  
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}

	//Closes client socket connection to the server as well as their input and output streams 
	public void close() throws IOException{  
		if (socket != null)    socket.close();
		if (streamIn != null)  streamIn.close();
		if (streamOut != null) streamOut.close();
	}
}
