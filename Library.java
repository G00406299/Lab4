//The Library method represents a database hosted on the server.
//Each method has the synchronized keyword, which means that each thread running them must take turns.
//This ensures that the Library object is consistent even as it's updated by many different threads.

package lab4;

import java.util.ArrayList;
import java.util.List;

public class Library {
	List<Book> books = new ArrayList<>();
	Book b;
	
	//Add book
	public synchronized void addBook(String t, String a, int i, double p) {
		b = new Book(t, a, i, p);
		books.add(b);
	}
	
	//Search book
	public synchronized String searchBook(int i) {
		for (Book b : books) {
			if(b.ID() == i)
				return b.toString();
		}
		
		return "Book not found!\n";
	}
	
	//List books
	public synchronized String listBooks() {
		String s = "";
		for (Book b : books) {
			s += b.toString();
		}
		return s;
	}
	
	public synchronized String deleteBook(int i) {
		for (Book b : books) {
			if(b.ID() == i) {
				books.remove(b);
				return "Book successfully deleted.";
			}
		}
		return "Book not found!";
	}
}
