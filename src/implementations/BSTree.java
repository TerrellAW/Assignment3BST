package implementations;

import utilities.BSTreeADT;
import utilities.Iterator;

/**
 * Class that contains the implementation methods for a Binary Search Tree.
 *
 * @param <E> The type of elements this list holds.
 * @author TerrellAW
 * @author Estefano Campana
 * @version 1.0
 */
public class BSTree<E extends Comparable<? super E>>
	implements BSTreeADT<E>
{
	/**
	 * Root node of <code>BSTree</code>.
	 */
	BSTreeNode<E> root;
	/**
	 * Height/layers of <code>BSTree</code>.
	 */
	int height;
	/**
	 * Count for amount of elements in <code>BSTree</code>.
	 */
	int count;

	/**
	 * Constructs the Binary Search Tree.
	 *
	 * @return Instance of the <code>BSTree</code> class.
	 */
	public BSTree() {
		this.root = null;
		this.height = 0;
		this.count = 0;
	}

	/**
	 * The node at the root of the Binary Search Tree will be returned.
	 * 
	 * @return node stored at the root of tree is returned
	 * @throws NullPointerException if the tree is empty and there is no root node.
	 */
	public BSTreeNode<E> getRoot()
			throws NullPointerException
	{
		if (this.root == null) {
			throw new NullPointerException("No root node found");
		}
		return this.root;
	}

	/**
	 * Determines the row height of the tree and returns that value as an integer
	 * value.
	 * 
	 * @return the height of the tree.
	 */
	public int getHeight()
	{
		return this.height;
	}

	/**
	 * The number of elements currently stored in the tree is counted and the value
	 * is returned.
	 * 
	 * @return number of elements currently stored in tree.
	 */
	public int size()
	{
		return this.count;
	}

	/**
	 * Maximum between two integers.
	 *
	 * @return larger of two given integers.
	 */
	int max(int a, int b) {
		return (a > b) ? a : b;
	}

	/**
	 * Calculates and updates the height of node and its children.
	 */
	private void updateHeights(BSTreeNode<E> node) {
		if (node == null) return; // Can't update if no nodes

		updateHeights(node.left); // Recursively handle all left nodes
		updateHeights(node.right); // Recursively handle all right nodes

		node.height = 1 + max(node.left != null ? node.left.height : 0,
							  node.right != null ? node.right.height : 0); // Update each node's height
		this.height = root.height; // Apply final height of tree
	}

	/**
	 * Checks if the tree is currently empty.
	 * 
	 * @return returns boolean true if the tree is empty otherwise false.
	 */
	public boolean isEmpty()
	{
		if (this.count == 0 || this.root == null) {
			return true;
		}
		return false;
	}

	/**
	 * Clears all elements currently stored in tree and makes the tree empty.
	 */
	public void clear()
	{
		this.root = null;
		this.height = 0;
		this.count = 0;
		// Garbage collector will deal with the rest
	}

	/**
	 * Method used to check if the tree contains the value passed.
	 * 
	 * @param entry The element to find in the tree
	 * @return returns True if element is currently in the tree. False if
	 *         the element is not found in the tree
	 * @throws NullPointerException if the element being passed in is null.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public boolean contains( E entry )
			throws NullPointerException 
	{
		if (entry == null) throw new NullPointerException("Cannot search for null values!");
		//opens an iterator to search for the value.
		Iterator<E> it = preorderIterator();
		//while there is nodes.
		while(it.hasNext()) 
		{
			//if a node equals the value to compare.
			if(it.next().equals(entry)) 
			{
				return true;
			}
		}
		//else return false.
		return false;
	}

	/**
	 * Retrieves a node from the tree given the object to search for.
	 * 
	 * @param entry element object being searched
	 * @return the node with the element located in tree, null if not found
	 * @throws NullPointerException if the element being passed in is null
	 */
	public BSTreeNode<E> search( E entry )
			throws NullPointerException 
	{
		return root;
	}

	/**
	 * Recursively inserts a node.
	 *
	 * @param node the starting point 
	 * @param newEntry the element being added.
	 * @return true if added successfully, false if duplicate
	 */
	private boolean insert(BSTreeNode<E> node, E newEntry) {
		int comparison = newEntry.compareTo(node.value);
		
		// Check if newEntry comes before the value of node
		if (comparison < 0) {
			if (node.left != null) {
				return insert(node.left, newEntry); // Go to next node
			}
			node.left = new BSTreeNode<E>(newEntry);
			return true; // Success
		// Check if newEntry comes after the value of node
		} else if (comparison > 0) {
			if (node.right != null) {
				return insert(node.right, newEntry); // Go to next node
			}
			node.right = new BSTreeNode<E>(newEntry);
			return true; // Success
		}
		return false; // Duplicate found
	}

	/**
	 * Adds a new element to the tree according to the natural ordering established
	 * by the Comparable implementation.
	 * 
	 * @param newEntry the element being added to the tree
	 * @return a boolean true if the element is added successfully else false
	 * @throws NullPointerException if the element being passed in is null
	 */
	public boolean add( E newEntry )
			throws NullPointerException 
	{
		// Null pointer if newEntry is null
		if (newEntry == null)
			throw new NullPointerException("Cannot add null to tree");

		// Create root if one does not exist
		if (isEmpty()) {
			this.root = new BSTreeNode<E>(newEntry);
			this.count++;
			this.height++;
			return true; // Success
		}

		boolean added = insert(root, newEntry);
		if (added) {
			this.count++;
			updateHeights(root);
		}
		return added;
	}

	/**
	 * Removes the smallest element in the tree.
	 * 
	 * @return the removed element or null if the tree is empty
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public BSTreeNode<E> removeMin() 
	{
		if(this.root == null || this.count == 0) return null;
		//gets the root
		BSTreeNode<E> cursor = getRoot();
		//traverse to the leftmost element.
		while(cursor != null) 
		{
			cursor = cursor.left;
		}
		//saves the node
		BSTreeNode<E> removed = cursor;
		//deletes the node.
		cursor = null;
		this.count--;
		// TODO: Figure out if height should decrease
		return removed;
	}

	/**
	 * Removes the largest element in the tree.
	 * 
	 * @return the removed element or null if the tree is empty.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public BSTreeNode<E> removeMax()
	{
		if(this.root == null || this.count == 0) return null;
		//gets the root
		BSTreeNode<E> cursor = getRoot();
		//traverse to the rightmost element.
		while(cursor != null) {
			cursor = cursor.right;
		}
		//saves the node into a variable.
		BSTreeNode<E> removed = cursor;
		//deletes the node.
		cursor = null;
		this.count--;
		// TODO: Figure out if height should decrease
		return removed;
	}

	/**
	 * Generates an in-order iteration over the contents of the tree. Elements are
	 * in their natural order.
	 * 
	 * @return an iterator with the elements in the natural order
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public Iterator<E> inorderIterator() 
	{
		return new InorderIterator<E>(getRoot());
	}

	/**
	 * Generates a pre-order iteration over the contents of the tree. Elements are
	 * order in such a way as the root element is first.
	 * 
	 * @return an iterator with the elements in a root element first order
	 * @author Estefano Campana
	 * @version 1.0
	 * 
	 */
	public Iterator<E> preorderIterator() 
	{
		return new PreorderIterator<E>(getRoot());
	}

	/**
	 * Generates a post-order iteration over the contents of the tree. Elements are
	 * order in such a way as the root element is last.
	 * 
	 * @return an iterator with the elements in a root element last order
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public Iterator<E> postorderIterator() 
	{
		return new PostorderIterator<E>(getRoot());
	}
}

