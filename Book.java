//This is a simple book object. It's used by Library.java to form a list of books.
//This can also be implemented as a record, but it's a class for now for those unfamiliar
//with records.

package lab4;

public class Book {

	String title, author;
	int ID;
	double price;
	
	public Book(String t, String a, int i, double p) {
		title = t;
		author = a;
		ID = i;
		price = p;
	}

	//You'll need to override the toString method for later.
	@Override
	public String toString() {
		return title+" by "+author+". ID: "+ID+", Price: "+price+"\n";
	}

	public String title() {
		return title;
	}

	public String author() {
		return author;
	}

	public int ID() {
		return ID;
	}

	public double price() {
		return price;
	}
}
