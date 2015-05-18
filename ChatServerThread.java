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

	public int getID(){
		int client_id = ID;
		return client_id;
	}


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
	}


	public void open() throws IOException{  
		streamIn = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
		streamOut = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
	}


	public void close() throws IOException{  
		if (socket != null)    socket.close();
		if (streamIn != null)  streamIn.close();
		if (streamOut != null) streamOut.close();
	}
}
