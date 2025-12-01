package implementations;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

import utilities.Iterator;

/**
 * Class that contains the Post-Order Iterator for a Binary Search Tree.
 * @param <E> Object with a generic type.
 * @author Estefano Campana
 * @version 1.0
 */
public class PostorderIterator<E> implements Iterator<E> 
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
	public PostorderIterator(BSTreeNode<E> root) 
	{
		postOrder(root);
	}
	/**
	 * Method that searches nodes inside a tree with the Post-Order traversal. Smallest value comes first, then the
	 * rest of the leafs in the same level, after that the parent of this subtree comes in, repeat the same for the
	 * right tree. Finally root comes in.
	 * @param node Current node of the tree.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	private void postOrder(BSTreeNode<E> node) 
	{
		if(node == null) return;
		//traverse the left tree.
		postOrder(node.left);
		//traverse the right tree.
		postOrder(node.right);
		//add the values to stack.
		res.add(node.value);
		
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
	 * Method used to retrieve the next value in the tree in the Post-Order traversal.
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
