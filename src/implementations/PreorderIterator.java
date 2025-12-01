package implementations;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import utilities.Iterator;
/**
 * Class that contains the Pre-Order Iterator for a Binary Search Tree.
 * @param <E> Object with a generic type.
 * @author Estefano Campana
 * @version 1.0
 */
public class PreorderIterator<E> implements Iterator<E> 
{
	/**
	 * Stack object where the sequence of the Pre-Order traversal search will be saved.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	Deque<E> res = new ArrayDeque<E>();
	/**
	 * Constructor that accepts the root of the tree.
	 * @param root BSTreeNode class object that represents the root.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public PreorderIterator(BSTreeNode<E> root) 
	{
		preOrder(root);
	}
	/**
	 * Method that searches nodes inside a tree with the Pre-Order traversal. Root comes first, then subtree,
	 * and in the end the right tree comes in.
	 * @param node Current node of the tree.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	private void preOrder(BSTreeNode<E> node) 
	{
		if(node == null) return;
		//adds the root first.
		res.add(node.value);
		//traverse the left subtree.
		preOrder(node.left);
		//traverse the right subtree.
		preOrder(node.right);
	}
	/**
	 * Method used to check if there are more nodes in the tree. In other words, this method checks if Stack is empty,
	 * if it is empty then there are no more nodes.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	@Override
	public boolean hasNext() 
	{
		return !res.isEmpty();
	}
	/**
	 * Method used to retrieve the next value in the tree in the Pre-Order traversal.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	@Override
	public E next() throws NoSuchElementException 
	{
		if(!hasNext()) throw new NoSuchElementException("No more elements.");
		return res.remove();
	}
}