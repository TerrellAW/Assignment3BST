package implementations;

import java.io.Serializable;

import utilities.BSTreeADT;
import implementations.BSTreeNode;

/**
 * 
 *
 * @param <E> The type of elements this list holds.
 * @author TerrellAW
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
	 * Checks the current tree to see if the element passed in is stored in the
	 * tree. If the element is found in the tree the method returns true and if the
	 * element is not in the tree the method returns false.
	 * 
	 * @param entry the element to find in the tree
	 * @return returns boolean true if element is currently in the tree and false if
	 *         the element is not found in the tree
	 * @throws NullPointerException if the element being passed in is null
	 */
	public boolean contains( E entry )
			throws NullPointerException 
	{
		
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

	}

	/**
	 *
	 */
	private void rightRotate(BSTreeNode node) {
		// TODO
	}

	/**
	 *
	 */
	private void leftRotate(BSTreeNode node) {
		// TODO
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
			this.root = new BSTreeNode(newEntry);
			this.height++;
			this.count++;
			return true;
		}

		// Check if newEntry comes before the value of root
		if (newEntry.compareTo(root.value) < 0) {
			root.left = new BSTreeNode(newEntry);
			this.height++;
			this.count++;
		} else if (newEntry.compareTo(root.value) > 0) {
			root.right = new BSTreeNode(newEntry);
			this.height++;
			this.count++;
		}

		// Raise height of root to max
		root.height = 1 + max((root.left.height), (root.right.height));

		// Get balance
		int balance = getBalance(root);

		// Left left
		if (balance > 1 && newEntry.compareTo(root.value) < 0)
			rightRotate(root); // TODO: Implement this

		// Right right
		else if (balance < -1 && newEntry.compareTo(root.value) > 0)
			leftRotate(root); // TODO: Implement this

		// Left right
		else if (balance > 1 && newEntry.compareTo(root.left.value) > 0) {
			leftRotate(root.left); // TODO: Implement this
			rightRotate(root); // TODO: Implement this
		}

		// Right left
		else if (balance < -1 && newEntry.compareTo(root.right.value) < 0) {
			rightRotate(root.right); // TODO: Implement this
			leftRotate(root); // TODO: Implement this
		}

		return true;
	}

	/**
	 * Removes the smallest element in the tree according to the natural ordering
	 * established by the Comparable implementation.
	 * 
	 * @return the removed element or null if the tree is empty
	 */
	public BSTreeNode<E> removeMin() 
	{

	}

	/**
	 * Removes the largest element in the tree according to the natural ordering
	 * established by the Comparable implementation.
	 * 
	 * @return the removed element or null if the tree is empty
	 */
	public BSTreeNode<E> removeMax()
	{

	}

	/**
	 * Generates an in-order iteration over the contents of the tree. Elements are
	 * in their natural order.
	 * 
	 * @return an iterator with the elements in the natural order
	 */
	public Iterator<E> inorderIterator() 
	{

	}

	/**
	 * Generates a pre-order iteration over the contents of the tree. Elements are
	 * order in such a way as the root element is first.
	 * 
	 * @return an iterator with the elements in a root element first order
	 */
	public Iterator<E> preorderIterator() 
	{

	}

	/**
	 * Generates a post-order iteration over the contents of the tree. Elements are
	 * order in such a way as the root element is last.
	 * 
	 * @return an iterator with the elements in a root element last order
	 */
	public Iterator<E> postorderIterator() 
	{

	}
}
