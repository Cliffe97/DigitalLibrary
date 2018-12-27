/**
 * @author cliffegao
 */
package pa2.SPL_DIGITAL_LIB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("spltree_digi_lib_baselib.txt");
		Scanner scanner = new Scanner(file);
		String firstBook = scanner.nextLine();
		SplayTreeNode<Book> rootNode = buildBook(firstBook);
		while(scanner.hasNextLine()) {
			String book = scanner.nextLine();
			SplayTreeNode<Book> node = buildBook(book);
			SplayTreeUtils.insert(rootNode, node, 1);
			//System.out.println(rootNode.data.toString());
			rootNode = node;
		}
		System.out.println(rootNode.data.toString());
		SplayTreeNode<Book> search = SplayTreeUtils.search(rootNode, 9780262640688L, 1);
		System.out.println(search.data.toString());
		try {
			PrintWriter writer = new PrintWriter("xd.txt", "UTF-8");
			writer.println("xdxd");
			writer.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scanner.close();
	}
	public static SplayTreeNode<Book> buildBook(String bookLine){
		String book = bookLine.trim();
	    String title = bookLine.substring(0, bookLine.indexOf("\t"));//get the title
	    book = book.substring(bookLine.indexOf("\t"));//delete the title from the original string
	    book = book.trim();
	    String author = book.substring(0, book.indexOf("\t"));// get the author
	    book = book.trim();
	    book = book.substring(book.indexOf("\t"));//delete author
	    book = book.trim();
	    String isbn = book;
	    long number = Long.parseLong(isbn);
	    Book newBook = new Book(title, author, number);
	    SplayTreeNode<Book> root = new SplayTreeNode<Book>(newBook);
		return root;
	}
}
