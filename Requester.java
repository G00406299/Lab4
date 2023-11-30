package sockets.st;

import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Requester{
	Socket requestSocket;
	ObjectOutputStream out;
 	ObjectInputStream in;
 	String message;
 	String response;
 	Scanner input;
	Requester(){
		
		input = new Scanner(System.in);
	}
	void run()
	{
		int loop = 0;
		int num = 0;
		int cont = 0;
		double price;
		try{
			//This bit's given to you by the Skeleton Code on the VLE.

			//1. creating a socket to connect to the server
			
			requestSocket = new Socket("127.0.0.1", 2004);
			System.out.println("Connected to localhost in port 2004");
			//2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			//3: Communicating with the server
			

			//Client Comms
			//For every sendMessage and in.readObject, there must be a corresponding in.readObject/sendMessage on
			//the provider, and vice versa.
			//Communications between the requester and the provider are essentially a game of pong - messages are
			//bounced between the client and the server again and again until the client chooses to close the connection.

			try 
			{
				do {
					//Reads in initial message and responds, selecting an option.
					message = (String)in.readObject();
					System.out.println(message);
					response = input.next();
					cont = Integer.parseInt(response);
					sendMessage(response);
					
					if(response.equalsIgnoreCase("1"))
					{
						message = (String)in.readObject(); //How many books would you like to add?
						System.out.println(message);
						loop = input.nextInt();
						sendMessage(""+loop);
						
						for(int i = 1; i <= loop; i++) { //Enter Nums
							message = (String)in.readObject();
							System.out.println(message);
							num = input.nextInt();
							sendMessage(""+num); //ID
							message = (String)in.readObject();
							System.out.println(message);
							sendMessage(input.next()); // Title
							message = (String)in.readObject();
							System.out.println(message);
							sendMessage(input.next()); //Author
							message = (String)in.readObject();
							System.out.println(message);
							price = input.nextDouble(); //Price
							sendMessage(""+price);
							
							message = (String)in.readObject();
							System.out.println(message);
						}
					}
					else if(response.equalsIgnoreCase("2"))
					{
						message = (String)in.readObject(); 
						System.out.println(message);
						num = input.nextInt(); //Search ID
						sendMessage(""+num);
						
						message = (String)in.readObject(); //Receive Result
						System.out.println(message);
					}
					else if(response.equalsIgnoreCase("3"))
					{
						message = (String)in.readObject(); //Listing Books...
						System.out.println(message);

						message = (String)in.readObject(); //Receive Result
						System.out.println(message);
					}
					else if(response.equalsIgnoreCase("4"))
					{
						message = (String)in.readObject(); 
						System.out.println(message);
						num = input.nextInt(); //Delete ID
						sendMessage(""+num);
						
						message = (String)in.readObject(); //Receive Result
						System.out.println(message);
					}
					else if (!response.equalsIgnoreCase("-1"))
					{
						message = (String)in.readObject();
						System.out.println(message);
					}
				}while(cont != -1);
				message = (String)in.readObject(); //Ending message
				System.out.println(message);
			} 
			
			
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}
	}
	void sendMessage(String msg)
	{
		try{
			out.writeObject(msg);
			out.flush();
			System.out.println("client> " + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	public static void main(String args[])
	{
		Requester client = new Requester();
		client.run();
	}
}