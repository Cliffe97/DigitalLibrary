package pa2.testSuite;

import pa2.SPL_DIGITAL_LIB.Book;
import pa2.SPL_DIGITAL_LIB.SplayTreeNode;
import pa2.SPL_DIGITAL_LIB.SplayTreeUtils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;


public class Utiltests {
	@Test
	public void testInsert() {
		Book book1 = new Book("A Book", "Ellis", 10);
		Book book2 = new Book("Another Book", "Micaela", 5);
		Book book3 = new Book("A Third Book", "Shuai", 15);
		Book book4 = new Book("A Fourth Book", "Ben", 8);
		Book book5 = new Book("A Fifth Book", "Shanshan", 9);
		Book book6 = new Book("A Sixth Book", "Andre", 3);

		SplayTreeNode<Book> node1 = new SplayTreeNode<Book>(book1);
		SplayTreeNode<Book> node2 = new SplayTreeNode<Book>(book2);
		SplayTreeNode<Book> node3 = new SplayTreeNode<Book>(book3);
		SplayTreeNode<Book> node4 = new SplayTreeNode<Book>(book4);
		SplayTreeNode<Book> node5 = new SplayTreeNode<Book>(book5);
		SplayTreeNode<Book> node6 = new SplayTreeNode<Book>(book6);
		
		SplayTreeUtils.insert(node1, node2, 1);
		SplayTreeUtils.insert(node2, node3, 1);
		SplayTreeUtils.insert(node3, node4, 1);
		SplayTreeUtils.insert(node4, node5, 1);
		SplayTreeUtils.insert(node5, node6, 1);

		assertTrue(node6.parent == null&&
				node6.right == node5&&
				node5.right == node1&&
				node5.left == node2&&
				node1.right == node3&&
				node3.right == null);
	}	
	@Test 
	public void testSearch2() {
		Book book1 = new Book("A Book", "Ellis", 10);
		Book book2 = new Book("Another Book", "Micaela", 5);
		Book book3 = new Book("A Third Book", "Shuai", 15);
		Book book4 = new Book("A Fourth Book", "Ben", 8);
		Book book5 = new Book("A Fifth Book", "Shanshan", 9);
		Book book6 = new Book("A Sixth Book", "Andre", 3);

		SplayTreeNode<Book> node1 = new SplayTreeNode<Book>(book1);
		SplayTreeNode<Book> node2 = new SplayTreeNode<Book>(book2);
		SplayTreeNode<Book> node3 = new SplayTreeNode<Book>(book3);
		SplayTreeNode<Book> node4 = new SplayTreeNode<Book>(book4);
		SplayTreeNode<Book> node5 = new SplayTreeNode<Book>(book5);
		SplayTreeNode<Book> node6 = new SplayTreeNode<Book>(book6);
		
		SplayTreeUtils.insert(node1, node2, 1);
		SplayTreeUtils.insert(node2, node3, 1);
		long number = 5;
		assertTrue(node2 == SplayTreeUtils.search(node3, number, 1));
	}
	@Test
	public void isbnSearch() throws FileNotFoundException {
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
		//System.out.println(rootNode.data.toString());
		SplayTreeNode<Book> search = SplayTreeUtils.search(rootNode, 9780123859655L, 1);
		//System.out.println(search.data.toString());
		scanner.close();
		assertTrue(search.data.ISBN == 9780123859655L);
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
	@Test
	public void authorSearch() throws FileNotFoundException {
		File file = new File("spltree_digi_lib_baselib.txt");
		Scanner scanner = new Scanner(file);
		String firstBook = scanner.nextLine();
		SplayTreeNode<Book> rootNode = buildBook(firstBook);
		while(scanner.hasNextLine()) {
			String book = scanner.nextLine();
			SplayTreeNode<Book> node = buildBook(book);
			SplayTreeUtils.insert(rootNode, node, 0);
			//System.out.println(rootNode.data.toString());
			rootNode = node;
		}
		//System.out.println(rootNode.data.toString());
		SplayTreeNode<Book> search = SplayTreeUtils.search(rootNode, "Keith Cooper & Linda Torczon", 0);
		//System.out.println(search.data.toString());
		scanner.close();
		assertTrue(search.data.ISBN == 9780120884780L);
	}
	@Test 
		public void deleteTest() {
		Book book1 = new Book("A Book", "Ellis", 10);
		Book book2 = new Book("Another Book", "Micaela", 5);
		Book book3 = new Book("A Third Book", "Shuai", 15);
		Book book4 = new Book("A Fourth Book", "Ben", 8);
		Book book5 = new Book("A Fifth Book", "Shanshan", 9);
		Book book6 = new Book("A Sixth Book", "Andre", 3);

		SplayTreeNode<Book> node1 = new SplayTreeNode<Book>(book1);
		SplayTreeNode<Book> node2 = new SplayTreeNode<Book>(book2);
		SplayTreeNode<Book> node3 = new SplayTreeNode<Book>(book3);
		SplayTreeNode<Book> node4 = new SplayTreeNode<Book>(book4);
		SplayTreeNode<Book> node5 = new SplayTreeNode<Book>(book5);
		SplayTreeNode<Book> node6 = new SplayTreeNode<Book>(book6);
		
		SplayTreeUtils.insert(node1, node2, 1);
		SplayTreeUtils.insert(node2, node3, 1);
		SplayTreeUtils.insert(node3, node4, 1);
		SplayTreeUtils.insert(node4, node5, 1);
		SplayTreeUtils.insert(node5, node6, 1);
		
		SplayTreeNode<Book> node = SplayTreeUtils.delete(node6, node6, 1);
		//System.out.println(node.toString());
		assertTrue(node == node2);
	}
	
	
}
