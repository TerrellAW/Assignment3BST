package implementations;

import java.io.Serializable;

/**
 * Node that stores a value in a data structure.
 * 
 * @param <E> The type of elements this list holds.
 * @author TerrellAW
 * @author Youssif Al-Halawche
 * @version 1.0
 */
public class BSTreeNode<E> implements Serializable
{
	/**
	 *  Serialize Id
	 */
	private static final long serialVersionUID = 8405078044740114276L;
	/**
	 * 
	 * Height of the node.
	 */
	int height;
	/**
	 * Value stored in the node.
	 */
	E value;
	/**
	 * Attached child nodes.
	 */
	BSTreeNode<E> left, right;

	/**
	 * Constructs the node object.
	 *
	 * @return Instance of the <code>BSTreeNode</code> class.
	 * @author TerrellAW
	 * @version 1.0
	 */
	BSTreeNode(E value) {
		this.height = 1;
		this.value = value;
	}

	/**
	 * Returns the element stored in the node.
	 *
	 * @return the element stored in the node.
	 * @author Estefano Campana
	 * @version 1.0
	 */
	public E getElement() {
		return this.value;
	}
}

