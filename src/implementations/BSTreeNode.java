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
	 * Key for finding the node.
	 */
	int key;
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
	BSTreeNode(int key, E value) {
		this.key = key;
		this.height = 1;
		this.value = value;
	}
}

