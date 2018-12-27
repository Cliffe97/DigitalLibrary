/**
 * @author cliffegao
 */
package pa2.SPL_DIGITAL_LIB;
import java.util.*;

public class SplayTreeUtils <T>{
	/**
	 * @param v
	 * perform zig operation on a node
	 * O(1)
	 */
	public static <T> void zig(SplayTreeNode<T> v) {
		if(v == null) {
			System.out.println("null pointer");
			return;
		}
		v = v.parent;
		SplayTreeNode<T> y = v.left;
		v.left = y.right;
		if(y.right!=null) {
			y.right.parent = v;
		}
		y.parent = v.parent;
		if(v.parent == null) {
			y.parent = null;
		}else if(v == v.parent.right) {
			v.parent.right = y;
		}else {
			v.parent.left = y;
		}
		y.right = v;
		v.parent = y;
	}
	/**
	 * @param v
	 * perform zag operation on a node
	 * O(1)
	 */
	public static <T>void zag(SplayTreeNode<T> v) {
		if(v == null) {
			System.out.println("null pointer");
			return;
		}
		v = v.parent;
		SplayTreeNode<T> y = v.right;
		v.right = y.left;
		if(y.left != null) {
			y.left.parent = v;
		}
		y.parent = v.parent;
		if(v.parent == null) {
			y.parent = null;
		}else if(v == v.parent.left){
			v.parent.left = y;
		}else {
			v.parent.right = y;
		}
		y.left = v;
		v.parent = y;
	}
	/**
	 * @param root
	 * @param node
	 * @param mode
	 * insert an element
	 * O(n)
	 */
	public static <T>void insert(SplayTreeNode<Book> root, SplayTreeNode<Book> node, int mode) {
		if(node == null || (mode!=1&&mode!=0)) {
			System.out.println("can't insert");
			return;
		}
		if(mode == 1) {//if comparing by ISBN
			SplayTreeNode<Book> v = null;
			while(root!=null) {
				v = root;
				if(node.data.ISBN > root.data.ISBN) {
					root = root.right;
				}else if(node.data.ISBN < root.data.ISBN) {
					root = root.left;
				}else {
					root = root.right;
				}
			}
			node.parent = v;
			if(v != null) {
				if(node.data.ISBN<v.data.ISBN) {
					v.left = node;
				}else {
					v.right = node;
				}
			}
		}else {//if comparing by author
			SplayTreeNode<Book> v = null;
			while(root!=null) {
				v = root;
				if(node.data.author.compareTo(root.data.author)>0) {
					root = root.right;
				}else if(node.data.author.compareTo(root.data.author)<0) {
					root = root.left;
				}else {
					root = root.right;
				}
			}
			node.parent = v;
			if(v != null) {
				if(node.data.author.compareTo(v.data.author)<0) {
					v.left = node;
				}else {
					v.right = node;
				}
			}
		}
		splay(node);//splay the node
		
	}
	/**
	 * @param root
	 * @param node
	 * insertion for generic node
	 * O(n)
	 */
	public static <T>void insert(SplayTreeNode<T> root, SplayTreeNode<T> node) {
		if(node == null) {
			System.out.println("null pointer");
			return;
		}
		SplayTreeNode<T> v = null;
		while(root!=null) {
			v = root;
			if(node.compareTo(root)>0) {
				root = root.right;
			}else if(node.compareTo(root)<0) {
				root = root.left;
			}else {
				root = root.right;
			}
		}
		node.parent = v;
		if(v != null) {
			if(node.compareTo(v)>0) {
				v.right = node;
			}else {
				v.left = node;
			}
		}
	}
	/**
	 * @param root
	 * @param node
	 * @param mode
	 * @return a the rootNode after deletion
	 * O(n)
	 */
	public static SplayTreeNode<Book> delete(SplayTreeNode<Book> root, SplayTreeNode<Book> node, int mode){
		if(node == null) {
			System.out.println("null pointer");
			return null;
		}
		//System.out.println("trying to delete: "+ node.data.toString());
		if(root.left==null&&root.right==null&&root.data == node.data) {
			System.out.println("xdxd");
			root = null;
			return null;
		}
		SplayTreeNode<Book> v = null;
		if(mode == 1) {
			while(root!=null) {
				//System.out.println(root.data.toString());
				v = root;
				if(node.data.ISBN > root.data.ISBN) {
					System.out.println("right");
					root = root.right;
				}else if(node.data.ISBN < root.data.ISBN) {
					System.out.println("left");
					root = root.left;
				}else {
					break;
				}
			}
			if(v.data.ISBN == node.data.ISBN) {
				//System.out.println("found it!");
				splay(v);
			}else {
				System.out.println("Couldn't delete it.");
				return null;
			}
		}else {
			while(root!=null) {
				v = root;
				if(node.data.author.compareTo(root.data.author)>0) {
					root = root.right;
				}else if(node.data.author.compareTo(root.data.author)<0) {
					root = root.left;
				}else {
					break;
				}
			}
			if(v.data.ISBN == node.data.ISBN) {
				splay(v);
			}else {
				System.out.println("Couldn't delete it.");
				return null;
			}
		}
		//System.out.println(v.toString());
		SplayTreeNode<Book> predecessor = v.left;//get the max of left subtree
		if(predecessor!=null) {//if there is a left subtree
			v.left.parent = null;//remove v
			v.right.parent = null;
			while(predecessor.right!=null) {
				predecessor = predecessor.right;
			}
			splay(predecessor);//splay the max of left subtree
			predecessor.right = v.right;//attach two subtrees
			predecessor.right.parent = predecessor;
			return predecessor;
		}else {
			SplayTreeNode<Book> successor = v.right;
			v.right.parent = null;
			while(successor.left!=null) {
				successor = successor.left;
			}
			splay(successor);
			return successor;
		}
	}
	
	/**
	 * @param root
	 * @param node
	 * delete for generic node
	 * O(n)
	 */
	public static <T>SplayTreeNode<T> delete(SplayTreeNode<T> root, SplayTreeNode<T> node) {
		if(node == null) {
			System.out.println("null pointer");
			return null;
		}
		if(root.left==null&&root.right==null&&root.data == node.data) {
			System.out.println("xdxd");
			root = null;
			return null;
		}
		SplayTreeNode<T> v = null;
		while(root!=null) {
			v = root;
			if(node.compareTo(root)>0) {
				root = root.right;
			}else if(node.compareTo(root)<0) {
				root = root.left;
			}else {
				break;
			}
		}
		if(v== node) {
			splay(v);
		}else {
			System.out.println("Couldn't delete it.");
			return null;
		}
		SplayTreeNode<T> predecessor = v.left;//get the max of left subtree
		if(predecessor!=null) {//if there is a left subtree
			v.left.parent = null;//remove v
			v.right.parent = null;
			while(predecessor.right!=null) {
				predecessor = predecessor.right;
			}
			splay(predecessor);//splay the max of left subtree
			predecessor.right = v.right;//attach two subtrees
			predecessor.right.parent = predecessor;
			return predecessor;
		}else {
			SplayTreeNode<T> successor = v.right;
			v.right.parent = null;
			while(successor.left!=null) {
				successor = successor.left;
			}
			splay(successor);
			return successor;
		}
		
	}
	/**
	 * @param node
	 * O(n)
	 * perform splay operation
	 */
	public static <T>void splay(SplayTreeNode<T> node) {
		if(node == null) {
			System.out.println("null pointer");
			return;
		}
		while(node.parent!=null) {
			//System.out.println(node.data.toString());
			if(node.parent.parent == null) {
				if(node == node.parent.left) {// if left child then do a zig
					zig(node);
				}else {
					zag(node);
				}
			}else {
				if(node == node.parent.left&&node.parent == node.parent.parent.left) {//left-left
					zig(node.parent);
					zig(node);
				}else if(node == node.parent.right&&node.parent == node.parent.parent.right) {//right-right
					zag(node.parent);
					zag(node);
				}else if(node == node.parent.right&&node.parent == node.parent.parent.left) {//right-left
					zag(node);
					zig(node);
				}else if(node == node.parent.left&&node.parent == node.parent.parent.right) {//left-right
					zig(node);
					zag(node);
				}
			}
		}
	}
	/**
	 * @param root
	 * @param searchItem
	 * @param mode
	 * search a node
	 * O(n)
	 */
	public static <T>SplayTreeNode<Book> search(SplayTreeNode<Book> root, T searchItem, int mode){
		if(root == null) {
			System.out.println("Tree empty!");
			return null;
		}
		if(searchItem == null) {
			System.out.println("Can't search");
			return null;
		}
		if(mode!=1&&mode!=0) {
			System.out.println("mode needs to be 0 or 1");
			return null;
		}
		SplayTreeNode<Book> v = null;
		if(mode == 1) {
			long isbn = (long) searchItem;
			while(root!=null) {
				v = root;
				if(isbn > root.data.ISBN) {
					root = root.right;
				}else if(isbn < root.data.ISBN) {
					root = root.left;
				}else {					
					splay(root);
					return root;
				}
			}
		}else {
			String author = searchItem.toString();
			while(root!=null) {
				v = root;
				if(editDistance(author, root.data.author)<5){
					System.out.println("Do you mean: "+ root.data.author);
					Scanner scanner = new Scanner(System.in);
					String y = scanner.next();
					if(y.equalsIgnoreCase("yes")){
						scanner.close();
						return root;
					}
				}
				if(author.compareTo(root.data.author) > 0) {
					root = root.right;
				}else if(author.compareTo(root.data.author) < 0) {
					root = root.left;
				}else {
					splay(root);
					return root;
				}
			}
		}
		splay(v);
		return v;
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