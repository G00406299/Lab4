package lab4;
import java.net.Socket;
import java.io.*;


public class ServerThread extends Thread{

	Socket myConnection;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	
	public ServerThread(Socket s)
	{
		myConnection = s;
	}
	
	public void run()
	{
		int loop = 0;
		int cont = 0;
		int id;
		String title, author;
		double price;
		
		Library lib = new Library();
		
		//If you look closely at the below code and compare it to Requester.java, you'll notice that it greatly
		//resembles it.
		//You can use the code you write in Requester.java to create the below code fairly easily.
		//It is recommended that you start off writing things server side, then use the code you write server-side
		//to create the code for the client - as it is just the server side code "reversed".
		try
		{
			out = new ObjectOutputStream(myConnection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(myConnection.getInputStream());
		
			//Server Comms
			do {
				
				//Main Menu
				sendMessage("Please select an option\n 1: Add a book. \n 2: Search for a book. \n 3: List all books. \n 4: Delete book. \n -1 to EXIT.");
				message = (String)in.readObject();
				cont = Integer.parseInt(message);
			
				if(message.equalsIgnoreCase("1")) //Add Book
				{
					sendMessage("How many books would you like to add?");
					//If it helps, you can effectively view in.readObject as input.next() from the scanner.
					//It functions similarly - send a message, get the response and put it in a variable.
					loop = Integer.parseInt((String)in.readObject());
					
					for(int i = 1; i <= loop; i++)
					{
						sendMessage("Please enter Book ID for Num "+i+": ");
						id = Integer.parseInt((String)in.readObject());
						sendMessage("Please enter Book Title for Num "+i+": ");
						title = (String)in.readObject();
						sendMessage("Please enter Book Author for Num "+i+": ");
						author = (String)in.readObject();
						sendMessage("Please enter Book Price for Num "+i+": ");
						price = Double.parseDouble((String)in.readObject());
						
						lib.addBook(title, author, id, price);
						sendMessage("Book added!");
					}
				}
				else if(message.equalsIgnoreCase("2")) //Search Book
				{
					
					sendMessage("Please enter an ID to search for: ");
					id = Integer.parseInt((String)in.readObject());
					
					sendMessage(lib.searchBook(id));
				}
				else if(message.equalsIgnoreCase("3")) //List Books
				{
					
					sendMessage("Listing books...");
					
					sendMessage(lib.listBooks());
				}
				else if(message.equalsIgnoreCase("4")) //Delete Book Book
				{
					
					sendMessage("Please enter an ID to delete: ");
					id = Integer.parseInt((String)in.readObject());
					
					sendMessage(lib.deleteBook(id));
				}
				else if(!message.equalsIgnoreCase("-1"))
				{
					sendMessage("Please enter a valid option.");
				}
			}while(cont != -1);
			sendMessage("Closing connection.");
			
			
			in.close();
			out.close();
		}
		catch(ClassNotFoundException classnot)
		{
					System.err.println("Data received in unknown format");
		}
		catch(IOException e)
		{
		}
	}
	
	void sendMessage(String msg)
	{
		try{
			out.writeObject("server> "+msg);
			out.flush();
			System.out.println("server>" + msg);
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
	
}
