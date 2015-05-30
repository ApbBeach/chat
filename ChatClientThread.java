import java.net.*;
import java.io.*;

public class ChatClientThread extends Thread{  
	private Socket           socket   = null;
	private ChatClient       client   = null;
	private DataInputStream  streamIn = null;
	public boolean			running = true;

	public ChatClientThread(ChatClient _client, Socket _socket){  
		client   = _client;
		socket   = _socket;
		open();  
		start();
	}
	
	//Opens client thread to recieve input.
	public void open(){  
		try{  
			streamIn  = new DataInputStream(socket.getInputStream());
		} catch(IOException ioe){  
			System.out.println("Error getting input stream: " + ioe);
			client.stop();
		}
	}
	
	//Closes client thread to recieve input.
	public void close(){  
		try{  
			if (streamIn != null) streamIn.close();
		} catch(IOException ioe){  
			System.out.println("Error closing input stream: " + ioe);
		}
	}
	
	//Uses handle to read messages sent to client and display them in standard output
	public void run(){  
		while (running){  
			try{  
				client.handle(streamIn.readUTF());
			}
			catch(IOException ioe){  
				System.out.println("Listening error: " + ioe.getMessage());
				client.stop();
			}
		}
		close();
	}
	
	
	
}
