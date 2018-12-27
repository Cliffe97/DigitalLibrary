/**
 * @author cliffegao
 */
package pa2.SPL_DIGITAL_LIB;

public class Book{
	public String title;
	public String author;
	public long ISBN;
	/**
	 * constructor
	 * O(1)
	 * @param title
	 * @param author
	 * @param ISBN
	 */
	public Book(String title, String author, long ISBN) {
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
	}
	/**
	 * @return the toString of book
	 * O(1)
	 */
	public String toString() {
		String print = this.title + ", "+this.author+", "+this.ISBN;
		return print;
	}
}
