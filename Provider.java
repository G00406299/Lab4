//The provider's been made a bit bare-bones by creating the ServerThread and moving the
//server portion of the communication over there.
//It makes things much cleaner.

//Note that in order to test this, you'll need the following set up in eclipse:
// Workspace 1 - Provider.Java, ServerThread.Java, Book.Java, Library.Java.
// Workspace 2 - Requester.Java
// You can then run Provider.Java and Requester.Java
package lab4;

import java.io.*;
import java.net.*;
public class Provider{
	
	
	public static void main(String args[])
	{
		ServerSocket providerSocket;
		try 
		{
			providerSocket = new ServerSocket(2004, 10);
			
			while(true)
			{
			
				//2. Wait for connection
				System.out.println("Waiting for connection");
			
				Socket connection = providerSocket.accept();
				ServerThread T1 = new ServerThread(connection);
				T1.start();
			} 
			
			//providerSocket.close();
		}
		
		catch (IOException e1) 
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
			
		
	}
	
}
