package implementations;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import utilities.Iterator;

/**
 * Class that contains the In-Order Iterator for a Binary Search Tree.
 * @param <E> Object with a generic type.
 * @author Estefano Campana
 * @version 1.0
 */
public class InorderIterator<E> implements Iterator<E> 
{
	/**
	 * Stack object where the sequence of the In-Order traversal search will be saved.
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
	public InorderIterator(BSTreeNode<E> root) 
	{
		//recursive function to traverse through the tree.
		inOrder(root);
	}
	
	/**
	 * Method that searches nodes inside a tree with the In-Order traversal. Smallest value comes first, then its parent,
	 * then the right subtree of this parent, after that the root comes in and repeat the same process for the right tree.
	 * @param node Current node of the tree.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	private void inOrder(BSTreeNode<E> node) 
	{
		//if the node is null do not do anything.
		if(node == null) return;
		//traverse the left subtree first.
		inOrder(node.left);
		//add the value into stack.
		res.add(node.value);
		//traverse the right subtree after.
		inOrder(node.right);
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
	 * Method used to retrieve the next value in the tree in the In-Order traversal.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	@Override
	public E next() throws NoSuchElementException 
	{
		if(!hasNext()) throw new NoSuchElementException("No more elements.");
		//returns the head in the stack, which has all the nodes inside a tree.
		return res.remove();
	}
}