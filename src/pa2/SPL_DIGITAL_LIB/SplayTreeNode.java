/**
 * @author Cliffe Gao
 * SplayTreeNode class
 */
package pa2.SPL_DIGITAL_LIB;

public class SplayTreeNode<T> implements Comparable<SplayTreeNode<T>>{
	public T data;
	public SplayTreeNode<T> left;
	public SplayTreeNode<T> right;
	public SplayTreeNode<T> parent;
	
	public SplayTreeNode(T data){
		this.data = data;
	}
	public SplayTreeNode(){
		this.data =null;
	}
	/**
	 * @return toString of the node
	 * O(1)
	 */
	public String toString() throws NullPointerException{
		String print = this.data.toString();
		if(left == null) {
			print += "\nLEFT    |    ";
		}else {
			print += "\nLEFT    |    "+this.left.data.toString();
		}
		if(right == null) {
			print += "\nRIGHT   |    ";
		}else {
			print += "\nRIGHT   |    "+this.right.data.toString();
		}
		return print;
	}
	/**
	 * @param node
	 * @return an integer
	 * O(1)
	 */
	@Override
	public int compareTo(SplayTreeNode<T> o) {
		// TODO Auto-generated method stub
		return ((Comparable<SplayTreeNode<T>>) data).compareTo((SplayTreeNode<T>) o.data);
	}
}