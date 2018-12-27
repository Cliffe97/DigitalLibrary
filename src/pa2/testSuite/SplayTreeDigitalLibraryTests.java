package pa2.testSuite;

import pa2.SPL_DIGITAL_LIB.*;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

public class SplayTreeDigitalLibraryTests {

	@Test
	public void testPopular() {
		SplayTreeDigitalLibrary digilib = new SplayTreeDigitalLibrary();
		String[] args = {"author", "Keith Cooper & Linda Torczon", "n", "isbn", "9780262640688", "n", "exit"};
		digilib.main(args);

		SplayTreeDigitalLibrary digilib2 = new SplayTreeDigitalLibrary();
		String[] args2 = {"popular", "exit"};
		String output = digilib2.main(args2);

		String expectedOutput = "Welcome to the SPLTREE_DIGITAL_LIBRARY.\nLoading library...  DONE.\n\nPlease enter ‘author’ to search by author name, ‘ISBN’ to search by reference ISBN, ‘popular’ to see the top books, ‘return’ to return a book, or ‘exit’ to leave the program: popular\nEngineering: A Compiler, 2nd Edition, Keith Cooper & Linda Torczon, 9780120884780\nThe Elements of Computing Systems: Building a Modern Computer from First Principles, Noam Nisan & Shimon Schocken, 9780262640688\n\nPlease enter ‘author’ to search by author name, ‘ISBN’ to search by reference ISBN, ‘popular’ to see the top books, ‘return’ to return a book, or ‘exit’ to leave the program: exit";
		
		assertTrue(output.equals(expectedOutput));
		
	}
	
	@Test
	public void testPopular2() {
		SplayTreeDigitalLibrary digilib = new SplayTreeDigitalLibrary();
		String[] args = {"author", "Keith Cooper & Linda Torczon", "n", "isbn", "9780262640688", "n", "exit"};
		digilib.main(args);

		SplayTreeDigitalLibrary digilib2 = new SplayTreeDigitalLibrary();
		//System.out.println("Author root: "+ digilib2.authorSplayTree.data.toString());
		String[] args2 = {"popular", "exit"};
		String output = digilib2.main(args2);
		String expectedOutput = "Welcome to the SPLTREE_DIGITAL_LIBRARY.\nLoading library...  DONE.\n\nPlease enter ‘author’ to search by author name, ‘ISBN’ to search by reference ISBN, ‘popular’ to see the top books, ‘return’ to return a book, or ‘exit’ to leave the program: popular\nEngineering: A Compiler, 2nd Edition, Keith Cooper & Linda Torczon, 9780120884780\nThe Elements of Computing Systems: Building a Modern Computer from First Principles, Noam Nisan & Shimon Schocken, 9780262640688\n\nPlease enter ‘author’ to search by author name, ‘ISBN’ to search by reference ISBN, ‘popular’ to see the top books, ‘return’ to return a book, or ‘exit’ to leave the program: exit";
		
		System.out.println("My output: \n\n"+ output);
		System.out.println("\n\nExpected output: \n\n"+expectedOutput);
		
		File file1 = new File("spltree_digi_lib_isbn.txt");
		File file2 = new File("spltree_digi_lib_auth.txt");
		File file3 = new File("spltree_digi_lib_borrowed.txt");
		if(file1.delete()&&file2.delete()&&file3.delete()) {
			System.out.println("deleted files");
		}
		assertTrue(output.equals(expectedOutput));
		//assertTrue(true);
	}
	
	@Test
	public void testBorrow() {
		SplayTreeDigitalLibrary digilib = new SplayTreeDigitalLibrary();
		String[] args = {"isbn", "9780262640688", "y", "exit"};
		digilib.main(args);
		Scanner scanner;
		String result = null;
		try {
			scanner = new Scanner(new File("spltree_digi_lib_borrowed.txt"));
			result = scanner.nextLine();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String expected = "The Elements of Computing Systems: Building a Modern Computer from First Principles	Noam Nisan & Shimon Schocken	9780262640688";
		File file1 = new File("spltree_digi_lib_isbn.txt");
		File file2 = new File("spltree_digi_lib_auth.txt");
		File file3 = new File("spltree_digi_lib_borrowed.txt");
		if(file1.delete()&&file2.delete()&&file3.delete()) {
			System.out.println("deleted files");
		}
		assertTrue(result.equals(expected));
	}
	/*
	 * test if deleted from file
	 */
	@Test
	public void testReturn() {
		SplayTreeDigitalLibrary digilib = new SplayTreeDigitalLibrary();
		String[] args = {"isbn", "9780262640688", "y", "exit"};
		digilib.main(args);
		
		SplayTreeDigitalLibrary digilib2 = new SplayTreeDigitalLibrary();
		String[] args2 = {"return","Noam Nisan & Shimon Schocken","exit"};
		digilib2.main(args2);
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("spltree_digi_lib_borrowed.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file1 = new File("spltree_digi_lib_isbn.txt");
		File file2 = new File("spltree_digi_lib_auth.txt");
		File file3 = new File("spltree_digi_lib_borrowed.txt");
		if(file1.delete()&&file2.delete()&&file3.delete()) {
			//System.out.println("deleted files");
		}
		assertTrue(!scanner.hasNextLine());
	}
	/*
	 * test if output is right for return
	 */
	@Test
	public void testReturn2() {
		SplayTreeDigitalLibrary digilib = new SplayTreeDigitalLibrary();
		String[] args = {"isbn", "9780262640688", "y", "exit"};
		digilib.main(args);
		
		SplayTreeDigitalLibrary digilib2 = new SplayTreeDigitalLibrary();
		String[] args2 = {"return","Noam Nisan & Shimon Schocken","exit"};
		String output = digilib2.main(args2);
		String expectedOutput =  "Welcome to the SPLTREE_DIGITAL_LIBRARY.\nLoading library...  DONE.\n\nPlease enter ‘author’ to search by author name, ‘ISBN’ to search by reference ISBN, ‘popular’ to see the top books, ‘return’ to return a book, or ‘exit’ to leave the program: "
				+ "return\nPlease enter the author for the book you are returning: Noam Nisan & Shimon Schocken\nThank you for returning this book.\n\nPlease enter ‘author’ to search by author name, ‘ISBN’ to search by reference ISBN, ‘popular’ to see the top books, ‘return’ to return a book, or ‘exit’ to leave the program: exit";
		File file1 = new File("spltree_digi_lib_isbn.txt");
		File file2 = new File("spltree_digi_lib_auth.txt");
		File file3 = new File("spltree_digi_lib_borrowed.txt");
		if(file1.delete()&&file2.delete()&&file3.delete()) {
			//System.out.println("deleted files");
		}
		assertTrue(output.equals(expectedOutput));
	}
	
	
	public void preOrder(SplayTreeNode<Book> v) {
		System.out.println(v.data.toString());
		if(v.left!=null) {
			preOrder(v.left);
		}
		if(v.right!=null) {
			preOrder(v.right);
		}
	}
	
	
}