/***
 * @author cliffegao
 */
package pa2.SPL_DIGITAL_LIB;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class SplayTreeDigitalLibrary {
	
	public SplayTreeNode<Book> authorSplayTree;
	public SplayTreeNode<Book> isbnSplayTree;
	public SplayTreeNode<Book> borrowedSplayTree;
	/**
	 * O(n^2)
	 * main function
	 */
	public String main(String[] args) {
		String output = mainFunction(args);
		return output;
		 // You MUST return all output to the console here
	}
	/**
	 * O(n^2)
	 * @param args
	 * @return the final output
	 */
	public String mainFunction(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String output = "";
		loadFiles();
		//postPrint(isbnSplayTree);
		String greetings = "Welcome to the SPLTREE_DIGITAL_LIBRARY.\nLoading library...  DONE.\n";
		System.out.print(greetings);
		output += greetings;
		int count = 0;
		if(args == null) {
			System.out.println("need to have input from args.");
		}
		while(count<args.length) {
			String input = null;
			String prompt = "\nPlease enter ‘author’ to search by author name, ‘ISBN’ to search by "
						+ "reference ISBN, ‘popular’ to see the top books, ‘return’ to return a book, "
						+ "or ‘exit’ to leave the program: ";
			if(args[count]!=null) {
				input = args[count];
			}else {
				input = scanner.next();
			}
			System.out.println(prompt+input);
			output += prompt;
			output += input;
			if(input.equals("author")) {
				String p1 = "You have selected Search by Author. Please enter the author name: ";
				System.out.print(p1);
				output+="\n"+p1;
				count ++;
				String author = null;
				if(args[count]!=null) {
					author = args[count];//get the author name
				}else {
					author = scanner.next();
				}
				output+= author;
				authorSearch(author);
				System.out.println(authorSplayTree.data.author);
				if(authorSplayTree.data.author.equals(author)) {//if found the author
					String p2 = "The following entry matched your search term:";
					System.out.println(p2);
					output+="\n"+p2;
					String p3 = authorSplayTree.data.toString();
					System.out.println(p3);
					output+="\n"+p3;
					String p4 = "\nWould you like to borrow this book? (y/n) ";
					System.out.print(p4);
					output+="\n"+p4;
					count ++;
					String yn = null;
					if(args[count]==null) {
						yn = scanner.next();
					}else {
						yn = args[count];
					}
					output += yn + "\n\n";
					System.out.println(yn);;
					if(yn.equals("y")) {// if borrow
						//add book node to borrowed tree
						System.out.println("y");
						buildBorrowTree(authorSplayTree);
					}
				}
			}else if(input.equals("isbn")) {
				String p11= "You have selected Search by ISBN. Please enter the ISBN:";
				System.out.print(p11);
				output+="n"+p11;
				count ++;
				long l = 0;
				if(args[count]==null) {
					l = Long.parseLong(scanner.next());
				}else {
					l = Long.parseLong(args[count]);

				}
				System.out.println(l);
				output += args[count];
				isbnSearch(l);
				if(isbnSplayTree.data.ISBN == l ) {// if found the isbn
					String p2 = "The following entry matched your search term:";
					System.out.println(p2);
					output+="\n"+p2;
					String p3 = isbnSplayTree.data.toString();
					System.out.println(p3);
					output+="\n"+p3;
					String p4 = "\nWould you like to borrow this book? (y/n) ";
					System.out.print(p4);
					output+="\n"+p4;
					count ++;
					String yn = null;
					if(args[count]==null) {
						yn = scanner.next();
					}else {
						yn = args[count];
					}
					output += yn + "\n";
					System.out.println(yn);
					if(yn.equals("y")) {// if borrow
						buildBorrowTree(isbnSplayTree);
					}
				}
				
			}else if(input.equals("popular")) {
				output += popular();				
			}else if(input.equals("return")) {
				String p4 = "\nPlease enter the author for the book you are returning: ";
				count ++;
				String a1 = null;
				if(args[count]==null) {
					a1 = scanner.next();
				}else {
					a1 = args[count];
				}
				output += p4+a1;
				System.out.println("\n"+p4+a1);
				String oo = returnBook(a1);
				output+="\n"+oo;
				System.out.println(oo);
			}else if(input.equals("exit")) {
				//postPrint(isbnSplayTree);
				try {
					writeFiles();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				count ++;
			}
			count++;
		}
		scanner.close();
		return output;
	}
	/**
	 * try to read from 3 files, if not found, then build from baselib
	 * O(n)
	 */
	public void loadFiles()  {
			File isbnFile = new File("spltree_digi_lib_isbn.txt");
			File authorFile = new File("spltree_digi_lib_auth.txt");
			File borrowFile = new File("spltree_digi_lib_borrowed.txt");
			if(isbnFile.exists()&&authorFile.exists()&&borrowFile.exists()) {
				try {
					isbnSplayTree = buildSingleTree(1, isbnFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					authorSplayTree = buildSingleTree(0, authorFile);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Scanner scanner = new Scanner(borrowFile);
					if(scanner.hasNextLine()){
						try {
							borrowedSplayTree  = buildSingleTree(1, borrowFile);
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					scanner.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}else {
				try {
					authorSplayTree = buildSingleTree(0, new File("spltree_digi_lib_baselib.txt"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					isbnSplayTree = buildSingleTree(1, new File("spltree_digi_lib_baselib.txt"));					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
	/**
	 * O(n)
	 * it will update the files from three trees
	 */
	public void writeFiles() throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer1 = new PrintWriter("spltree_digi_lib_isbn.txt", "UTF-8");
		postOrder(isbnSplayTree, writer1);
		writer1.close();
		PrintWriter writer2 = new PrintWriter("spltree_digi_lib_auth.txt", "UTF-8");
		postOrder(authorSplayTree, writer2);
		writer2.close();
		PrintWriter writer3 = new PrintWriter("spltree_digi_lib_borrowed.txt", "UTF-8");
		if(borrowedSplayTree!=null) {
			postOrder(borrowedSplayTree, writer3);
			writer3.close();
		}else {
			writer3.print("");
			writer3.close();
		}
	}
	/**
	 * O(n)
	 * Traverse the tree and print it in the file
	 * @param root
	 * @param writer
	 */
	public void postOrder(SplayTreeNode<Book> root, PrintWriter writer) {
		if(root.left!=null) {
			postOrder(root.left, writer);
		}
		if(root.right!=null) {
			postOrder(root.right, writer);
		}
		writer.println(root.data.title+"\t"+root.data.author+"\t"+root.data.ISBN);		//System.out.println(root.data.toString());
	}
	/**
	 * @param root
	 * O(n)
	 * for testing
	 */
	public void postPrint(SplayTreeNode<Book> root) {
		if(root.left!=null) {
			postPrint(root.left);
		}
		if(root.right!=null) {
			postPrint(root.right);
		}
		System.out.println(root.data.toString());
	}
	
	/**
	 * build the tree
	 * @return the root of the tree
	 * O(n)
	 */
	public SplayTreeNode<Book> buildSingleTree(int mode, File file) throws FileNotFoundException{
		//File file = new File("spltree_digi_lib_baselib.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			String firstBook = scanner.nextLine();
			SplayTreeNode<Book> rootNode = buildBook(firstBook);
			while(scanner.hasNextLine()) {
				String book = scanner.nextLine();
				SplayTreeNode<Book> node = buildBook(book);
				SplayTreeUtils.insert(rootNode, node, mode);
				rootNode = node;
			}
			scanner.close();
			return rootNode;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * build a book object from a string
	 * @return the node 
	 * O(n)
	 */
	public SplayTreeNode<Book> buildBook(String bookLine){
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
	/**
	 * @param author
	 * author search
	 * @return a search match
	 * O(n)
	 */
	public void authorSearch(String author){
		SplayTreeNode<Book> result = SplayTreeUtils.search(authorSplayTree, author, 0);
		authorSplayTree = result;
	}
	/**
	 * @param isbn
	 * author search
	 * @return a search match
	 * O(n)
	 */
	public void isbnSearch(long isbn){
		SplayTreeNode<Book> result = SplayTreeUtils.search(isbnSplayTree, isbn, 1);
		isbnSplayTree = result;

	}
	/**
	 * @param author
	 * perform the return book operation
	 * O(n)
	 * @return the output
	 */
	public String returnBook(String author) {
		String output = "";
		SplayTreeNode<Book> result = SplayTreeUtils.search(borrowedSplayTree, author, 0);
		if(result == null || !result.data.author.equals(author)) {
			output = "/nSorry, no books were found with your search term.";
		}else {
			//System.out.println("Result : "+ result.data.toString());
			output = "Thank you for returning this book.\n";
			if(borrowedSplayTree.right == null&&borrowedSplayTree.left==null&&borrowedSplayTree.data.author.equals(author)) {
				borrowedSplayTree = null;
			}else {
				SplayTreeUtils.delete(borrowedSplayTree, result, 0);// delete from borrow tree
			}
			//System.out.println(borrowedSplayTree.data.toString());
			SplayTreeNode<Book> b1 = new SplayTreeNode<Book>(result.data);
			SplayTreeNode<Book> b2= new SplayTreeNode<Book>(result.data);
			SplayTreeUtils.insert(authorSplayTree, b1, 0);
			authorSplayTree = b1;//update root
			SplayTreeUtils.insert(isbnSplayTree, b2, 1);
			isbnSplayTree = b2;//update root
		}
		return output;
	}
	/**
	 * O(1)
	 * @return two last searched book
	 */
	public String popular() {
		String output = "";
		String book1 = authorSplayTree.data.toString();
		String book2 = isbnSplayTree.data.toString();
		book1 += "\n"+book2;
		output = "\n"+book1+"\n";
		System.out.println(book1);
		return output;
	}
	/**
	 * @param book
	 * O(n)
	 * if borrow then build the borrow tree
	 */
	public void buildBorrowTree(SplayTreeNode<Book> book){
		SplayTreeNode<Book> newBook = new SplayTreeNode<Book>(book.data);
		SplayTreeUtils.insert(borrowedSplayTree, newBook, 0);// insert into borrowed tree, using author so sort
		borrowedSplayTree = newBook;//update the root of borrowed tree
		SplayTreeNode<Book> newIsbnRoot = SplayTreeUtils.delete(isbnSplayTree, book, 1);//delete from isbn tree
		isbnSplayTree = newIsbnRoot;//update isbn root
		SplayTreeNode<Book> newAuthorRoot = SplayTreeUtils.delete(authorSplayTree, book, 0);//delete from author tree
		authorSplayTree = newAuthorRoot;//update author root
	}
	
	/**
     *  @param a word 
     *  @param a word
     *  @return a int that represents the min distance between two words
     */
	public static int editDistance(String first, String second) {
		int[][] distance = new int[first.length()+1][second.length()+1];
		first = first.toLowerCase();
		second = second.toLowerCase();
		//if one string if empty
		for(int i = 0; i < first.length() + 1; i ++) {
			distance[i][0] = i;
		}
		for(int i = 0; i < second.length() + 1; i ++) {
			distance[0][i] = i;
		}
		for(int i = 0; i < first.length(); i ++) {
			for(int j = 0; j < second.length(); j ++) {
				//if same, go for the diagonal
				if(first.charAt(i) == second.charAt(j)) {
					distance[i+1][j+1] = distance[i][j];
				}else {
					//three situations
					int replace = distance[i][j];
					int insert = distance[i][j+1];
					int delete = distance[i+1][j];
					distance[i+1][j+1] = Math.min(replace+2, Math.min(insert+1, delete+1));
				}
			}
		}
		return distance[first.length()][second.length()];
	}

}
