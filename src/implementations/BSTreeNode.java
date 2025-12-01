package implementations;

/**
 * 
 * 
 * @param <E> The type of elements this list holds.
 * @author TerrellAW
 */
public class BSTreeNode<E>
{
	/**
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
	 */
	BSTreeNode(E value) {
		this.height = 1;
		this.value = value;
	}

	public E getElement() {
		return this.value;
	}
}

