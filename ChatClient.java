

import java.net.*;
import java.io.*;

public class ChatClient implements Runnable{  
	private Socket socket              = null;
	private Thread thread              = null;
	private BufferedReader  console   = null;
	private DataOutputStream streamOut = null;
	private ChatClientThread client    = null;
	public boolean running			= true;

	public ChatClient(String serverName, int serverPort){  
		System.out.println("Establishing connection. Please wait ...");
		try{  
			socket = new Socket(serverName, serverPort);
			System.out.println("Connected: " + socket);
			start();
		} catch(UnknownHostException uhe){  
			System.out.println("Host unknown: " + uhe.getMessage()); }
		catch(IOException ioe){  
			System.out.println("Unexpected exception: " + ioe.getMessage()); }
	}

	public void run(){  
		while (running){  
			try{  
				streamOut.writeUTF(console.toString());
				streamOut.flush();
			} catch(IOException ioe){  
				System.out.println("Sending error: " + ioe.getMessage());
				stop();
				running = false;
			}
		}
		stop();
	}


	public void handle(String msg){  
		if (msg.equals(".bye")){  
			System.out.println("Good bye. Press RETURN to exit ...");
			stop();
		} else
			System.out.println(msg);
	}


	public void start() throws IOException{  
		console = new BufferedReader(new InputStreamReader(System.in));
		streamOut = new DataOutputStream(socket.getOutputStream());
		if (thread == null){  
			client = new ChatClientThread(this, socket);
			thread = new Thread(this);                   
			thread.start();
		}
	}


	public void stop(){  
		if (thread != null){  
			this.running = false;  
			thread = null;
		} try{  
			if (console   != null)  console.close();
			if (streamOut != null)  streamOut.close();
			if (socket    != null)  socket.close();
		} catch(IOException ioe){  
			System.out.println("Error closing ..."); 
			}
		client.running = false;
	}


	public static void main(String args[]){  
		ChatClient client = null;
		/* if (args.length != 2)
         System.out.println("Usage: java ChatClient host port");
      else
         client = new ChatClient(args[0], Integer.parseInt(args[1]));
		 */
		client = new ChatClient("localhost", 4443);
	}
}


