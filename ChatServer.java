import java.net.*;
import java.io.*;

public class ChatServer implements Runnable{
	//List of connected clients: contains server socket and ID
	private ChatServerThread clients[]     = new ChatServerThread[50];
	//Main server socket
	private ServerSocket 	 server 	   = null;
	//Individual client threads
	private Thread       	 thread 	   = null;
	//Number of connected clients
	private int          	 clientCount   = 0;
	//Is the server running and accepting client connections
	public boolean 		 	 running 	   = true;

	
	//Sets up chat server socket 'server' on port number 'port'
	public ChatServer(int port){  
		try{  
			System.out.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);  
			System.out.println("Server started: " + server);
			start(); 
		}
		catch(IOException ioe){  
			System.out.println("Can not bind to port " + port + ": " + ioe.getMessage()); 
		}
	}

	//Waits for clients to attempt to connect and accepts them
	public void run(){  
		while (running){  
			try{  
				System.out.println("Waiting for a client ..."); 
				addThread(server.accept()); 
			}
			catch(IOException ioe){  
				System.out.println("Server accept error: " + ioe); 
				stop(); 
			}
		}
		stop();
	}
	
	//Starts client thread, used when client connects
	public void start()  {
		if (thread == null){  
			thread = new Thread(this); 
			thread.start();
		}
	}

	//Ends server connection, used on error.
	public void stop()   { 
		if (thread != null){  
			running = false; 
			thread = null;
		}
	}


	//Returns client name by assigned ID
	private int findClient(int ID){  
		for (int i = 0; i < clientCount; i++)
			if (clients[i].getID() == ID)
				return i;
		return -1;
	}

	//'.bye' used by client to remove self from server.  
	//Otherwise accepts input from a single client and sends it to all other connected clients
	
	public synchronized void handle(int ID, String input){  
		if (input.equals(".bye")){  
			clients[findClient(ID)].send(".bye");
			remove(ID); 
		}
		else{
			for (int i = 0; i < clientCount; i++)
				clients[i].send(ID + ": " + input); 
		}
	}


	//Used on '.bye' to remove client from server.  Terminates their thread and removes them from the client list
	public synchronized void remove(int ID){
		int client_id = findClient(ID);
		if (client_id >= 0){  
			ChatServerThread toTerminate = clients[client_id];
			System.out.println("Removing client thread " + ID + " at " + client_id);
			if (client_id < clientCount-1){
				for (int i = client_id+1; i < clientCount; i++){
					clients[i-1] = clients[i];
				}
			}
			clientCount--;
			try{  
				toTerminate.close(); 
			}
			catch(IOException ioe){  
				System.out.println("Error closing thread: " + ioe); 
			}
	ChatServerThread.running = false; }
	}


	//Adds client to the server, opens client to accpet/send messages through data stream
	//starts client socket.  Updates client count list, ensures the client list is below the maximum. 
	private void addThread(Socket socket){  
		if (clientCount < clients.length){  
			System.out.println("Client accepted: " + socket);
			clients[clientCount] = new ChatServerThread(this, socket);
			try{  
				clients[clientCount].open(); 
				clients[clientCount].start();  
				clientCount++; 
			}
			catch(IOException ioe){  
				System.out.println("Error opening thread: " + ioe); } }
		else{
			System.out.println("Client refused: maximum " + clients.length + " reached.");
		}
	}


	public static void main(String args[]) { 
		ChatServer server = null;
		/*if (args.length != 1)
			System.out.println("Usage: java ChatServer port");
		else
			server = new ChatServer(Integer.parseInt(args[0]));
			*/
		server = new ChatServer(4442);
	}
}